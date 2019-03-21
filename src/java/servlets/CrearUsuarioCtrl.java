    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import db.UsuarioDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CrearUsuario", urlPatterns = {"/Admin/CrearUsuario"})
public class CrearUsuarioCtrl extends HttpServlet {
    UsuarioDB usuarioDB = new UsuarioDB();
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
        String tipo = request.getParameter("tipo");
        if (tipo != null ){
        int tipoUsuario = Integer.parseInt(tipo);
            switch(tipoUsuario){
                case 1://Jugador
                    tipo = Usuario.TIPO_JUGADOR;
                    break;
                case 2://Arbitro
                    tipo = Usuario.TIPO_ARBITRO;
                break;
                case 3://Administrador
                    tipo = Usuario.TIPO_ADMINISTRADOR;
                break;
                case 4://Apostador
                    tipo = Usuario.TIPO_APOSTADOR;
                break;
            }
            Usuario usuario = new Usuario();
            usuario.setCedula(request.getParameter("cc"));
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setApellido(request.getParameter("apellido"));
            usuario.setNombreUsuario(request.getParameter("nombreUsuario"));
            usuario.setClave(request.getParameter("clave"));
            usuario.setTelefono(request.getParameter("telefono"));
            
            usuario.setTipo(tipo);
            int idUsuario = usuarioDB.insert(usuario);
            request.setAttribute("idUsuario", idUsuario);
        }
        request.getRequestDispatcher("/admin/crearUsuarioVista.jsp").forward(request, response);
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
