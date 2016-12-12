package br.fa7.biblioteca.mdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.fa7.biblioteca.distribuidora.DistribuidoraOrder;
import br.fa7.biblioteca.model.Pedido;
import br.fa7.biblioteca.model.SituacaoPedido;
import br.fa7.biblioteca.service.PedidoService;

@MessageDriven(name="pedidosMDB", mappedName="pedidosMDB",
	activationConfig={
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), 
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/Pedidos"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class PedidoMDB implements MessageListener{

	@EJB
	PedidoService pedidoService;
	
	public static final String URL_WEBSERVICE_DISTRIBUIDORA = "http://localhost:8080/order";
	
	@Override
	public void onMessage(Message message) {		
		
		if(message == null) return;
		aguardarParaProcessarMensagem();
		
		try {
			TextMessage textMessage = null;
			if (message instanceof TextMessage) {
				textMessage = (TextMessage) message;
				enviarPedidoParaDistribuidora(Integer.parseInt(textMessage.getText()));
			} else {
				System.out.println("Mensagem inválida na fila");
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}	
	}

	private void aguardarParaProcessarMensagem() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	private void enviarPedidoParaDistribuidora(Integer pedidoId) {
		Pedido pedido = pedidoService.find(pedidoId);
		fazerPost(pedido);
	}
	
	private void fazerPost(Pedido pedido){
		 
		try {
			if(pedido == null) return;
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(getHttpPostRequestDistribuidora(pedido));
			
			if (isCodigoRespostaDeErro(response)) {
				throw new RuntimeException("Transmissão à distribuidora falhou: codigo de erro HTTP : "
						+ response.getStatusLine().getStatusCode());
			}

			atualizarSituacaoPedidoParaEncaminhado(pedido);
			
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {
			System.out.println("Erro de URL mal formada");
			e.printStackTrace();

		} catch (IOException e) {
			System.out.println("Erro de IO");
			e.printStackTrace();

		} catch (Exception e){
			System.out.println("Outro erro não identificado!");
			e.printStackTrace();
		}
	}

	private void atualizarSituacaoPedidoParaEncaminhado(Pedido pedido) {
		pedido.setSituacaoPedido(new SituacaoPedido());
		pedido.getSituacaoPedido().setId(SituacaoPedido.ENCAMINHADO_DISTRIBUIDORA);
		pedidoService.update(pedido);
	}

	private boolean isCodigoRespostaDeErro(HttpResponse response) {
		return response.getStatusLine().getStatusCode() != 200;
	}

	private HttpUriRequest getHttpPostRequestDistribuidora(Pedido pedido) throws UnsupportedEncodingException{
		HttpPost postRequest = new HttpPost(URL_WEBSERVICE_DISTRIBUIDORA);
		StringEntity input = new StringEntity(getJsonString(pedido));
		input.setContentType("application/json");
		postRequest.setEntity(input);
		return postRequest;
	}

	private String getJsonString(Pedido pedido) {
		DistribuidoraOrder distribuidoraOrder = new DistribuidoraOrder(pedido);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		
		try {
			jsonInString = mapper.writeValueAsString(distribuidoraOrder);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}		
		return jsonInString;
	}
}
