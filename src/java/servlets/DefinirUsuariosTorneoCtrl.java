/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import db.TorneoDB;
import db.UsuarioDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

/**
 *
 * @author DELL
 */
public class DefinirUsuariosTorneoCtrl extends HttpServlet {
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
        String[] jugadoresSeleccionados, arbitrosSeleccionados, names;
        jugadoresSeleccionados = request.getParameterValues("jugadoresSelec[]");
        arbitrosSeleccionados = request.getParameterValues("arbitrosSelec[]");
        ArrayList<Usuario> jugadores = construirUsuarios(new ArrayList<String>(Arrays.asList(jugadoresSeleccionados)));
        ArrayList<Usuario> arbitros = construirUsuarios(new ArrayList<String>(Arrays.asList(arbitrosSeleccionados)));
        torneoDB.definirUsuarios(jugadores, arbitros);
        session.setAttribute("jugadoresSesion", jugadores);
        session.setAttribute("arbitrosSesion", arbitros);
        response.sendRedirect("/TorneoTenisMesa/admin/CrearEstructuraCtrl");
    }
    
    private ArrayList<Usuario> construirUsuarios(ArrayList<String> usuariosString){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        Usuario usuario;
        for (String idUsuarioString : usuariosString) {
            try {
                usuario = new Usuario();
                int idUsuario = Integer.parseInt(idUsuarioString);
                usuario.setIdUsuario(idUsuario);
                usuarios.add(usuario);
            } catch (NumberFormatException e) {
            }
        }
        return usuarios;
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
