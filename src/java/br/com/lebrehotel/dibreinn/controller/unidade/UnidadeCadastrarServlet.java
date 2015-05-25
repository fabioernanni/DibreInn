package br.com.lebrehotel.dibreinn.controller.unidade;

import br.com.lebrehotel.dibreinn.model.unidade.Unidade;
import br.com.lebrehotel.dibreinn.model.unidade.UnidadeDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thiago, udimberto.sjunior
 */
@WebServlet(name = "UnidadeCadastrarServlet", urlPatterns = {"/erp/unidades/adicionar"})
public class UnidadeCadastrarServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {

    RequestDispatcher rd = request.getRequestDispatcher("/erp/unidades/adicionar.jsp");
    rd.forward(request, response);
    
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
    try {
      
      Unidade u = new Unidade();

      u.setNome(request.getParameter("unidadeNome"));
      u.setCnpj(request.getParameter("unidadeCnpj"));
      u.setTipo(Integer.parseInt(request.getParameter("unidadeTipo")));

      u.setCep((request.getParameter("unidadeCep")));
      u.setLogradouro(request.getParameter("unidadeLogradouro"));
      u.setNumero(request.getParameter("unidadeNumero"));
      u.setComplemento(request.getParameter("unidadeComplemento"));
      u.setBairro(request.getParameter("unidadeBairro"));
      u.setCidade(request.getParameter("unidadeCidade"));
      u.setEstado(request.getParameter("unidadeEstado"));
      
      //Endereco end = new Endereco();
      //end.setCep((request.getParameter("unidadeCep")));
      //end.setLogradouro(request.getParameter("unidadeLogradouro"));
      //end.setNumero(request.getParameter("unidadeNumero"));
      //end.setComplemento(request.getParameter("unidadeComplemento"));
      //end.setBairro(request.getParameter("unidadeBairro"));
      //end.setCidade(request.getParameter("unidadeCidade"));
      //end.setEstado(request.getParameter("unidadeEstado"));

      UnidadeDAO unidadeBD = new UnidadeDAO();
      unidadeBD.cadastrarUnidade(u);
      
      response.sendRedirect("listar");
      
    } catch (Exception ex) {
      System.out.println(ex);
      response.sendRedirect("../erro");
    }

  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }

}
