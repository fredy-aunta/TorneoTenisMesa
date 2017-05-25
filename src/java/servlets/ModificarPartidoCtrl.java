/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import db.PartidoDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Partido;
import modelo.Usuario;
import services.ValidarSesion;

/**
 *
 * @author DELL
 */
public class ModificarPartidoCtrl extends HttpServlet {
    PartidoDB partidoDB = new PartidoDB();

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
        int idPartido;
        try {
        idPartido = Integer.parseInt(request.getParameter("idPartido"));
        } catch (NumberFormatException e) {
            response.sendRedirect("/TorneoTenisMesa/ConsultarTorneosCtrl");
            return;
        }
        Partido partido =  new Partido();
        partido.setIdPartido(idPartido);
        String fechaHora = request.getParameter("fechaHora");
        DateFormat format = new SimpleDateFormat("yyyy-M-dd hh:mm");
        Date date = null;
        try {
            date = format.parse(fechaHora);
        } catch (ParseException ex) {
            Logger.getLogger(ModificarUsuarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        partido.setFechaHora(date);
        
        int registrosActualizados = partidoDB.update(partido);
        if(registrosActualizados == 1){
            request.setAttribute("mensaje", "Actualizado");
        }else{
            request.setAttribute("mensajeError", "Ocurrio un error");
        }
        int tipo = ValidarSesion.getTipoUsuarioSesion(session);
        request.setAttribute("tipo",tipo);
        partido = partidoDB.buscarPartido(idPartido);
        request.setAttribute("partido", partido);
        request.getRequestDispatcher("/arbitro/consultarPartidoVista.jsp").forward(request, response);
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
