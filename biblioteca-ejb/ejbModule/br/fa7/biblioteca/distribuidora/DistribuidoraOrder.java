package br.fa7.biblioteca.distribuidora;

import java.util.ArrayList;
import java.util.List;

import br.fa7.biblioteca.model.Pedido;
import br.fa7.biblioteca.model.PedidoLivro;

public class DistribuidoraOrder {

	private Integer Id;
	
	private Integer clientId = 12345;
	
	private String callBackUrl = "http://localhost:8090/biblioteca-war/api/distribuidora-callback";
	
	private String clientOrderId;
	
	private List<DistribuidoraOrderItem> items;

	private String orderId;
	
	private String requestDate;
	
	@Override
	public String toString() {
		return "DistribuidoraOrder [clientId=" + clientId + ", callBackUrl=" + callBackUrl + ", clientOrderId="
				+ clientOrderId + ", items=" + items + "]";
	}
	
	public DistribuidoraOrder() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DistribuidoraOrder(Pedido pedido) {
		this.clientOrderId = pedido.getId().toString();
		this.items = new ArrayList<>();
		if(pedido.getPedidoLivros()!=null){
			for(PedidoLivro pedidoLivro: pedido.getPedidoLivros()){
				this.items.add(new DistribuidoraOrderItem(pedidoLivro));
			}
		}
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	public String getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public List<DistribuidoraOrderItem> getItems() {
		return items;
	}

	public void setItems(List<DistribuidoraOrderItem> items) {
		this.items = items;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}			
}
