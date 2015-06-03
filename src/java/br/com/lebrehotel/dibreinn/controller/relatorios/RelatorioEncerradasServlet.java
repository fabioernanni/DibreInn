package br.com.lebrehotel.dibreinn.controller.relatorios;

import br.com.lebrehotel.dibreinn.controller.*;
import br.com.lebrehotel.dibreinn.model.unidade.UnidadeDAO;
import br.com.lebrehotel.dibreinn.model.relatorio.*;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Date;
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
@WebServlet(name = "RelatorioEncerradasServlet", urlPatterns = {"/erp/relatorios/estadias-encerradas"})
public class RelatorioEncerradasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UnidadeDAO unidadeBD = new UnidadeDAO();
        RelatorioDAO relatorioBD = new RelatorioDAO();
        int tu = unidadeBD.listarUnidades().size();
        List<Relatorio> r = relatorioBD.reservasStatus("E");
        for(Relatorio i : r){
            
        }
        
        String estadiasEncerradasPorUnidades
                = "{unidade: 'São Paulo I', vendas: 100}, "
                + "{unidade: 'Rio de Janeiro I', vendas: 180}, "
                + "{unidade: 'Bahia I', vendas: 90}, "
                + "{unidade: 'Ceará I', vendas: 30}, ";
        request.setAttribute("estadiasEncerradasPorUnidades", estadiasEncerradasPorUnidades);

        String estadiasEncerradasGeral
                = "{label: 'São Paulo I', value: 100}, "
                + "{label: 'Rio de Janeiro I', value: 180}, "
                + "{label: 'Bahia I', value: 90}, "
                + "{label: 'Ceará I', value: 30},";
        request.setAttribute("estadiasEncerradasGeral", estadiasEncerradasGeral);

        RequestDispatcher rd = request.getRequestDispatcher("/erp/relatorios/estadias-encerradas.jsp");
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
    }// </editor-fold>

}
