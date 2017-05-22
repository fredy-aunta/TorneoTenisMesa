/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import db.PartidoDB;
import db.TorneoDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.FactoriaEstructura;
import modelo.Partido;
import modelo.Torneo;

/**
 *
 * @author DELL
 */
public class IngresarResultadoPartidoCtrl extends HttpServlet {
    PartidoDB partidoDB = new PartidoDB();
    TorneoDB torneoDB = new TorneoDB();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String resultado1 = request.getParameter("resultado1");
        String resultado2 = request.getParameter("resultado2");
        String idPartido = request.getParameter("idPartido");
        String idJugador1 = request.getParameter("idJugador1");
        String idJugador2 = request.getParameter("idJugador2");
        String idPartidoTorneo = request.getParameter("idPartidoTorneo");
        
        Partido partido = new Partido();
        Torneo torneo = null;
        try {
            partido.setResultado1(Integer.parseInt(resultado1));
            partido.setResultado2(Integer.parseInt(resultado2));
            partido.setIdPartido(Integer.parseInt(idPartido));
            partido.setIdJugador1(Integer.parseInt(idJugador1));
            partido.setIdJugador2(Integer.parseInt(idJugador2));
            partido.setIdPartidoTorneo(Integer.parseInt(idPartidoTorneo));
            torneo = torneoDB.buscarTorneoPartido(Integer.parseInt(idPartido));
        } catch (NumberFormatException e) {
//            redirect back
        }
        
        boolean subidos = partidoDB.subirResultados(partido);
        if (partido.terminado() && torneo.getEstructura().getIdEstructura() == FactoriaEstructura.ESTRUCTURA_ARBOL) {
            partidoDB.definirSiguientePartido(torneo,partido);
        }
        request.setAttribute("subidos", subidos);
        request.setAttribute("partido", partido);
        request.getRequestDispatcher("/arbitro/resultadoPartidoVista.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
    }

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
        processRequest(request, response);
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
