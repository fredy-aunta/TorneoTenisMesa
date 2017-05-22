/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import db.PartidoDB;
import java.io.IOException;
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
import modelo.Partido;
import modelo.Torneo;
import modelo.Usuario;
import services.UsuarioService;

/**
 *
 * @author DELL
 */
public class AsociarUsuariosCtrl extends HttpServlet {
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
        String[] idsPartidosTorneo = request.getParameterValues("idPartidoTorneo[]");
        String[] idsJugador1 = request.getParameterValues("idJugador1[]");
        String[] idsJugador2 = request.getParameterValues("idJugador2[]");
        String[] fechas = request.getParameterValues("fechas[]");
        ArrayList<Usuario> arbitros = (ArrayList<Usuario>) session.getAttribute("arbitrosSesion");
        Torneo torneo = (Torneo) session.getAttribute("torneoSession");
        Partido partido;
        ArrayList<Integer> idsPartidos = new ArrayList<Integer>();
        for (int i = 0; i < idsPartidosTorneo.length; i++) {
            String idPartidosTorneo = idsPartidosTorneo[i];
            String idJugador1 = idsJugador1[i];
            String idJugador2 = idsJugador2[i];
            String fecha = fechas[i];
            
            partido = new Partido();
            try {
                DateFormat format = new SimpleDateFormat("yyyy-M-dd hh:mm");
                Date date = null;
                try {
                    date = format.parse(fecha);
                } catch (ParseException ex) {
                    Logger.getLogger(ModificarUsuarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }
                partido.setFechaHora(date);
                if (!idJugador1.equals("0")){
                    partido.setIdJugador1(Integer.parseInt(idJugador1));
                }
                if (!idJugador2.equals("0")){
                    partido.setIdJugador2(Integer.parseInt(idJugador2));
                }
                partido.setIdPartidoTorneo(Integer.parseInt(idPartidosTorneo));
                partido.setIdArbitro(UsuarioService.getRandom(arbitros).getIdUsuario());
                idsPartidos.add(partidoDB.insert(partido, torneo.getIdTorneo()));
            } catch (NumberFormatException e) {
            }
        }
        if (idsPartidos.size() == idsPartidosTorneo.length) {
            session.removeAttribute("torneoSession");
            session.removeAttribute("fechaHoraTorneoSession");
            session.removeAttribute("jugadoresSesion");
            session.removeAttribute("arbitrosSesion");
            response.sendRedirect("/TorneoTenisMesa/ConsultarTorneosCtrl");
        } else {
//            Message error
        }
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
