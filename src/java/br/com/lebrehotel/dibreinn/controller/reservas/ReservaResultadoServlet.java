package br.com.lebrehotel.dibreinn.controller.reservas;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jSilverize
 */
@WebServlet(name = "ReservasServlet", urlPatterns = {"/erp/reservas/resultado"})
public class ReservaResultadoServlet extends HttpServlet {

  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {

    String status = request.getParameter("status");
    
    String msgOk = "Tudo traquilo! Reserva efetuada com sucesso.";
    String msgFalha = "Desculpe :/ Houve algum erro com nosso sistema";
    
    if(status.equals("ok")) {
      request.setAttribute("mensagem", msgOk);
    } else {
      request.setAttribute("mensagem", msgFalha);
    }

    RequestDispatcher rd = request.getRequestDispatcher("/erp/reservas/resultado.jsp");
    rd.forward(request, response);

  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {

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
  // </editor-fold>

}
