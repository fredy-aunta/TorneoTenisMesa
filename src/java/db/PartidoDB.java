/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Estructura;
import modelo.FactoriaEstructura;
import modelo.Partido;
import modelo.Torneo;
import services.DBManager;
import servlets.ModificarUsuarioCtrl;

/**
 *
 * @author DELL
 */
public class PartidoDB {
    private final String TABLE_NAME = "Partido";
    private final String SQL_INSERT = "INSERT INTO partido (fechaHora, idTorneo, idPartidoTorneo) VALUES (?,?,?)";
    private final String SQL_INSERT_ID = "SELECT @@identity AS id";
    private final String SQL_INSERT_USUARIO_PARTIDO = "INSERT INTO usuariopartido (idPartido,idUsuario) VALUES (?,?);";
    private final String SQL_PARTIDOS = "SELECT p.idPartido, p.fechaHora, p.idPartidoTorneo," +
"	group_concat(up.idUsuario order by p.idPartidoTorneo ASC, u.tipo ASC SEPARATOR ' ') usuarios," +
"    group_concat(up.resultado order by up.idUsuarioPartido ASC, u.tipo ASC SEPARATOR ' ') resultados," +
"    group_concat(u.tipo order by p.idPartidoTorneo ASC, u.tipo ASC SEPARATOR ' ') tipos" +
" FROM " + TABLE_NAME + " p" +
" join usuarioPartido up on up.idPartido = p.idPartido" +
" join usuario u on u.idUsuario = up.idUsuario" +
" where p.idTorneo = ?" +
" group by p.idPartido";
    private final String SQL_PARTIDO = "SELECT p.idPartido, p.fechaHora, p.idPartidoTorneo," +
"	group_concat(up.idUsuario order by p.idPartidoTorneo ASC, u.tipo ASC SEPARATOR ' ') usuarios," +
"    group_concat(up.resultado order by p.idPartidoTorneo ASC, u.tipo ASC SEPARATOR ' ') resultados," +
"    group_concat(u.tipo order by p.idPartidoTorneo ASC, u.tipo ASC SEPARATOR ' ') tipos" +
" FROM " + TABLE_NAME + " p" +
" join usuarioPartido up on up.idPartido = p.idPartido" +
" join usuario u on u.idUsuario = up.idUsuario" +
" where up.idPartido = ?" +
" group by p.idPartido";

    private final String SQL_UPDATE = "UPDATE Partido SET fechaHora=? WHERE idPartido=?";
    private final String SQL_SUBIR_RESULTADO = "UPDATE usuariopartido SET resultado = ? WHERE idPartido = ? AND idUsuario = ?";
    private final String SQL_NEXT_USUARIO_PARTIDO = "SELECT up.idUsuarioPartido FROM usuariopartido up\n" +
        "JOIN partido p ON p.idPartido = up.idPartido\n" +
        "WHERE p.idPartidoTorneo = ?\n" +
        "AND p.idTorneo = ?\n" +
        "AND up.idUsuario = 0\n" +
        "ORDER BY up.idUsuarioPartido ASC\n" +
        "LIMIT 1";
    private final String SQL_UPDATE_NEXT_USUARIO_PARTIDO = "UPDATE usuariopartido SET idUsuario=? WHERE idUsuarioPartido=?;";

    public PartidoDB() {
    }
    
    public int insert(Partido partido, int idTorneo){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs;
        int rows, idPartido = 0;
        ArrayList<Integer> idUsuarios;
        try {
            int index = 1;
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(index++, partido.getFechaHoraFull());
            statement.setInt(index++, idTorneo);
            statement.setInt(index++, partido.getIdPartidoTorneo());
            System.out.println("Ejecutando query: " + SQL_INSERT);
            rows = statement.executeUpdate();
            System.out.println("Registros Afectados :" + rows);
            /**
             * Obtiene el id del torneo que se acabo de insertar
             */
            statement = connection.prepareStatement(SQL_INSERT_ID);
            rs = statement.executeQuery();
            rs.next();
            idPartido = rs.getInt(1);
            idUsuarios = new ArrayList<Integer>();
            idUsuarios.add(partido.getIdJugador1());
            idUsuarios.add(partido.getIdJugador2());
            idUsuarios.add(partido.getIdArbitro());
            for (int i = 0; i < idUsuarios.size(); i++) {
                index = 1;
                Integer idUsuario = idUsuarios.get(i);
                statement = connection.prepareStatement(SQL_INSERT_USUARIO_PARTIDO);
                statement.setInt(index++, idPartido);
                statement.setInt(index, idUsuario);
                System.out.println("Ejecutando query: " + SQL_INSERT_USUARIO_PARTIDO);
                rows = statement.executeUpdate();
                System.out.println("Registros Afectados :" + rows);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return idPartido;
    }
    
    public ArrayList<Partido> getAllPartidos(int idTorneo){
        ArrayList<Partido> partidos = new ArrayList<Partido>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Partido partido;
        ArrayList<Integer> idUsuarios, resultados;
        ArrayList<String> tipos;
        String resultadosString;
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_PARTIDOS);
            statement = connection.prepareStatement(SQL_PARTIDOS);
            statement.setInt(1, idTorneo);
            rs = statement.executeQuery();
            int i;
            while(rs.next()){
                idUsuarios = new ArrayList<Integer>();
                resultados = new ArrayList<Integer>();
                i = 1;
                partido = new Partido();
                partido.setIdPartido(rs.getInt(i++));
                DateFormat format = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
        Date date = null;
        String fechaHora = rs.getString(i++);
        try {
            date = format.parse(fechaHora);
        } catch (ParseException ex) {
            Logger.getLogger(ModificarUsuarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        partido.setFechaHora(date);
                partido.setIdPartidoTorneo(rs.getInt(i++));
                Scanner scanner = new Scanner(rs.getString(i++));
                while (scanner.hasNextInt()) {
                    idUsuarios.add(scanner.nextInt());
                }
                resultadosString = rs.getString(i++);
                if (resultadosString != null && !resultadosString.equals("")) {
                    scanner = new Scanner(resultadosString);
                    while (scanner.hasNextInt()) {
                        resultados.add(scanner.nextInt());
                    }
                }
                tipos = new ArrayList<String>(Arrays.asList(rs.getString(i++).split(" ")));
                for (int j = 0; j < idUsuarios.size(); j++) {
                    String tipo = tipos.get(j);
                    int resultado = 0;
                    if (!resultados.isEmpty() && resultados.size() > j) {
                        resultado = resultados.get(j);
                    }
                    int idUsuario = idUsuarios.get(j);
                    if (tipo.equals("Jugador")) {
                        if (partido.getIdJugador1() == 0) {
                            partido.setIdJugador1(idUsuario);
                            partido.setResultado1(resultado);
                        } else if (partido.getIdJugador2() == 0) {
                            partido.setIdJugador2(idUsuario);
                            partido.setResultado2(resultado);
                        }
                    } else if (tipo.equals("Arbitro")) {
                        if (partido.getIdArbitro() == 0) {
                            partido.setIdArbitro(idUsuario);
                        }
                    }
                }
                partidos.add(partido);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return partidos;
    }
    
    /**
     * @param idPartido
     * @return 
     */
    public Partido buscarPartido(int idPartido){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Integer> idUsuarios, resultados;
        ArrayList<String> tipos;
        String resultadosString;
        Partido partido = null;
        try {
            int i = 1;
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_PARTIDO);
            statement = connection.prepareStatement(SQL_PARTIDO);
            statement.setInt(i++, idPartido);
            rs = statement.executeQuery();
            if (rs.next()) {
                idUsuarios = new ArrayList<Integer>();
                resultados = new ArrayList<Integer>();
                i = 1;
                partido = new Partido();
                partido.setIdPartido(rs.getInt(i++));
                DateFormat format = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
        Date date = null;
        String fechaHora = rs.getString(i++);
        try {
            date = format.parse(fechaHora);
        } catch (ParseException ex) {
            Logger.getLogger(ModificarUsuarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        partido.setFechaHora(date);
                partido.setIdPartidoTorneo(rs.getInt(i++));
                Scanner scanner = new Scanner(rs.getString(i++));
                while (scanner.hasNextInt()) {
                    idUsuarios.add(scanner.nextInt());
                }
                resultadosString = rs.getString(i++);
                if (resultadosString != null && !resultadosString.equals("")) {
                    scanner = new Scanner(resultadosString);
                    while (scanner.hasNextInt()) {
                        resultados.add(scanner.nextInt());
                    }
                }
                tipos = new ArrayList<String>(Arrays.asList(rs.getString(i++).split(" ")));
                for (int j = 0; j < idUsuarios.size(); j++) {
                    String tipo = tipos.get(j);
                    int resultado = 0;
                    if (!resultados.isEmpty() && resultados.size() > j) {
                        resultado = resultados.get(j);
                    }
                    int idUsuario = idUsuarios.get(j);
                    if (tipo.equals("Jugador")) {
                        if (partido.getIdJugador1() == 0) {
                            partido.setIdJugador1(idUsuario);
                            partido.setResultado1(resultado);
                        } else if (partido.getIdJugador2() == 0) {
                            partido.setIdJugador2(idUsuario);
                            partido.setResultado2(resultado);
                        }
                    } else if (tipo.equals("Arbitro")) {
                        if (partido.getIdArbitro() == 0) {
                            partido.setIdArbitro(idUsuario);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return partido;
    }
    
    public int update(Partido partido){
        Connection connection = null;
        PreparedStatement statement = null;
        int rows = 0;
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_UPDATE);
            statement = connection.prepareStatement(SQL_UPDATE);
            int index = 1;
            statement.setString(index++, partido.getFechaHoraFull());
            statement.setInt(index++, partido.getIdPartido());
            rows = statement.executeUpdate();
            System.out.println("Registros actualizados:" + rows);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return rows;
    }
    
    public boolean subirResultados(Partido partido){
        Connection connection = null;
        PreparedStatement statement = null;
        boolean subidos = false;
        int index = 1;
        int rows;
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_SUBIR_RESULTADO);
            statement = connection.prepareStatement(SQL_SUBIR_RESULTADO);
            index = 1;
            statement.setInt(index++, partido.getResultado1());
            statement.setInt(index++, partido.getIdPartido());
            statement.setInt(index++, partido.getIdJugador1());
            rows = statement.executeUpdate();
            System.out.println("Registros actualizados:" + rows);
            System.out.println("Ejecutando query:" + SQL_SUBIR_RESULTADO);
            statement = connection.prepareStatement(SQL_SUBIR_RESULTADO);
            index = 1;
            statement.setInt(index++, partido.getResultado2());
            statement.setInt(index++, partido.getIdPartido());
            statement.setInt(index++, partido.getIdJugador2());
            rows += statement.executeUpdate();
            System.out.println("Registros actualizados:" + rows);
            if (rows > 0) {
                subidos = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return subidos;
    } 
    
    public boolean definirSiguientePartido(Torneo torneo, Partido partido){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        int idUsuarioPartido, index, rows = 0, idNextUsuario = partido.getIdGanador();
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(SQL_NEXT_USUARIO_PARTIDO);
            index = 1;
            Estructura e = torneo.getEstructura();
            e.crearEstructura(torneo.getCantidadJugadores());
            statement.setInt(index++, e.getIdSiguientePartido(partido.getIdPartidoTorneo()));
            statement.setInt(index++, torneo.getIdTorneo());
            rs = statement.executeQuery();
            rs.next();
            idUsuarioPartido = rs.getInt(1);
            System.out.println("Ejecutando query:" + SQL_UPDATE_NEXT_USUARIO_PARTIDO);
            statement = connection.prepareStatement(SQL_UPDATE_NEXT_USUARIO_PARTIDO);
            index = 1;
            statement.setInt(index++, idNextUsuario);
            statement.setInt(index++, idUsuarioPartido);
            rows = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return (rows > 0);
    }
}
