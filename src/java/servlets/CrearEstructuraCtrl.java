/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Estructura;
import modelo.Torneo;
import modelo.Usuario;
import services.Partidos;

/**
 *
 * @author DELL
 */
public class CrearEstructuraCtrl extends HttpServlet {

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
        Torneo torneo = (Torneo) session.getAttribute("torneoSession");
        ArrayList<Usuario> jugadores = (ArrayList<Usuario>) session.getAttribute("jugadoresSesion");
        ArrayList<Usuario> arbitros = (ArrayList<Usuario>) session.getAttribute("arbitrosSesion");
        torneo.getEstructura().crearEstructura(torneo.getCantidadJugadores());
        int cantidadPartidos = torneo.getEstructura().getCantidadPartidos();
        String fechaHora = (String) session.getAttribute("fechaHoraTorneoSession");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date fechaInicialTorneo = null;
        try {
            fechaInicialTorneo = format.parse(fechaHora);
        } catch (ParseException ex) {
            Logger.getLogger(ModificarUsuarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Partidos partidos = new Partidos(cantidadPartidos, jugadores, arbitros,fechaInicialTorneo);
        request.setAttribute("partidos", partidos.getData());
        request.getRequestDispatcher("crearEstructuraVista.jsp").forward(request, response);
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
