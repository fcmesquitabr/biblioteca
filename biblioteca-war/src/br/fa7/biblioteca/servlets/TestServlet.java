package br.fa7.biblioteca.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.fa7.biblioteca.model.Aluno;
import br.fa7.biblioteca.model.ReservaLivro;
import br.fa7.biblioteca.service.ReservaService;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private ReservaService reservaService;
	
    public TestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(reservaService != null){
			List<ReservaLivro> listaReservas = reservaService.list();
			System.out.println(listaReservas);
		}else{
			System.out.println("EJB null");
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
