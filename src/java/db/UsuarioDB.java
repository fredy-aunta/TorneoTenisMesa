/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;
import services.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;
import servlets.ModificarUsuarioCtrl;
/**
 *
 * @author sebastian
 */
public class UsuarioDB {
    private final String TABLE_NAME = "Usuario";
    
    private final String SQL_INSERT = "INSERT INTO " + TABLE_NAME +" (nombre,apellido,cedula,estado,nombreUsuario,clave,tipo,telefono,fechaNacimiento) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String SQL_INSERT_ID = "SELECT @@identity AS id";
    private final String SQL_VALID = "SELECT * FROM " + TABLE_NAME + " WHERE nombreUsuario = ? AND clave = ? AND tipo = ?";
    private final String SQL_USUARIOS = "SELECT * FROM " + TABLE_NAME + " u ORDER BY u.idUsuario DESC";
    private final String SQL_JUGADORES = "SELECT * FROM " + TABLE_NAME + " u WHERE tipo = 'Jugador' ORDER BY u.idUsuario DESC";
    private final String SQL_ARBITROS = "SELECT * FROM " + TABLE_NAME + " u WHERE tipo = 'Arbitro' ORDER BY u.idUsuario DESC";
    private final String SQL_SELECT_ID = "SELECT * FROM " + TABLE_NAME + " WHERE idUsuario = ?";
    private final String SQL_UPDATE = "UPDATE usuario SET nombre=?, apellido=?, nombreUsuario=?, clave=?, telefono=? WHERE idUsuario=?";
    
    private final String SQL_UPDATE_PASSWORD_ID = "UPDATE auth_user SET password= ? WHERE user_id = ?;";
    private final String SQL_UPDATE_PASSWORD = "UPDATE auth_user SET password= ? WHERE username = ?;";
    private final String SQL_DELETE = "DELETE FROM auth_user WHERE user_id = ?";
    private final String SQL_SELECT = "SELECT * FROM " + TABLE_NAME + " ORDER BY user_id";
    private final String SQL_SELECT_NAME = "SELECT * FROM " + TABLE_NAME + " WHERE nombre = ?";

    public UsuarioDB() {
    }
    /**
     * 
     * @param usuario
     * @return int
     */
    public int insert(Usuario usuario){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs;
        int rows, idUser = 0;
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(SQL_INSERT);
            int index = 1;
            statement.setString(index++, usuario.getNombre());
            statement.setString(index++, usuario.getApellido());
            statement.setString(index++, usuario.getCedula());
            statement.setInt(index++, 1);
            statement.setString(index++, usuario.getNombreUsuario());
            statement.setString(index++, usuario.getClave());
            statement.setString(index++, usuario.getTipo());
            statement.setString(index++, usuario.getTelefono());
            statement.setString(index++, usuario.getFechaNacimientoFormat());
            System.out.println("Ejecutando query: " + SQL_INSERT);
            rows = statement.executeUpdate();
            System.out.println("Registros Afectados :" + rows);
            /**
             * Obtiene el id del cliente que se acabo de insertar
             */
            statement = connection.prepareStatement(SQL_INSERT_ID);
            rs = statement.executeQuery();
            rs.next();
            idUser = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return idUser;
    }
    public Usuario buscarUsuario(String nombreUsuario, String clave, String tipo){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            int i = 1;
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_VALID);
            statement = connection.prepareStatement(SQL_VALID);
            statement.setString(i++, nombreUsuario);
            statement.setString(i++, clave);
            statement.setString(i++, tipo);
            rs = statement.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                i=1;
                usuario.setIdUsuario(rs.getInt(i++));
                usuario.setNombre(rs.getString(i++));
                usuario.setApellido(rs.getString(i++));
                usuario.setCedula(rs.getString(i++));
                usuario.setEstado(rs.getBoolean(i++));
                usuario.setNombreUsuario(rs.getString(i++));
                usuario.setClave(rs.getString(i++));
                usuario.setTipo(rs.getString(i++));
                usuario.setTelefono(rs.getString(i++));
                DateFormat format = new SimpleDateFormat("yyyy-M-dd");
                Date date = null;
                String fechaNacimiento = rs.getString(i++);
                try {
                    date = format.parse(fechaNacimiento);
                } catch (ParseException ex) {
                    Logger.getLogger(ModificarUsuarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }
                usuario.setFechaNacimiento(date);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return usuario;
    }
    public ArrayList<Usuario> getAllUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_USUARIOS);
            statement = connection.prepareStatement(SQL_USUARIOS);
            rs = statement.executeQuery();
            int i;
            while(rs.next()){
                i=1;
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt(i++));
                usuario.setNombre(rs.getString(i++));
                usuario.setApellido(rs.getString(i++));
                usuario.setCedula(rs.getString(i++));
                usuario.setEstado(rs.getBoolean(i++));
                usuario.setNombreUsuario(rs.getString(i++));
                usuario.setClave(rs.getString(i++));
                usuario.setTipo(rs.getString(i++));
                usuario.setTelefono(rs.getString(i++));
                DateFormat format = new SimpleDateFormat("yyyy-M-dd");
                Date date = null;
                String fechaNacimiento = rs.getString(i++);
                try {
                    date = format.parse(fechaNacimiento);
                } catch (ParseException ex) {
                    Logger.getLogger(ModificarUsuarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }
                usuario.setFechaNacimiento(date);
                
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return usuarios;
    }
    
    public Usuario buscarUsuario(int idUsuario){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            int i = 1;
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_SELECT_ID);
            statement = connection.prepareStatement(SQL_SELECT_ID);
            statement.setInt(i++, idUsuario);
            rs = statement.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                i=1;
                usuario.setIdUsuario(rs.getInt(i++));
                usuario.setNombre(rs.getString(i++));
                usuario.setApellido(rs.getString(i++));
                usuario.setCedula(rs.getString(i++));
                usuario.setEstado(rs.getBoolean(i++));
                usuario.setNombreUsuario(rs.getString(i++));
                usuario.setClave(rs.getString(i++));
                usuario.setTipo(rs.getString(i++));
                usuario.setTelefono(rs.getString(i++));
                 DateFormat format = new SimpleDateFormat("yyyy-M-dd");
                Date date = null;
                String fechaNacimiento = rs.getString(i++);
                try {
                    date = format.parse(fechaNacimiento);
                } catch (ParseException ex) {
                    Logger.getLogger(ModificarUsuarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }
                usuario.setFechaNacimiento(date);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return usuario;
    }
    
    public int update(Usuario usuario){
        Connection connection = null;
        PreparedStatement statement = null;
        int rows = 0;
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_UPDATE);
            statement = connection.prepareStatement(SQL_UPDATE);
            int index = 1;
            statement.setString(index++, usuario.getNombre());
            statement.setString(index++, usuario.getApellido());
            statement.setString(index++, usuario.getNombreUsuario());
            statement.setString(index++, usuario.getClave());
            statement.setString(index++, usuario.getTelefono());
            statement.setInt(index++, usuario.getIdUsuario());
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
    
    public ArrayList<Usuario> getJugadores(){
        ArrayList<Usuario> jugadores = new ArrayList<Usuario>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Usuario usuario;
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_JUGADORES);
            statement = connection.prepareStatement(SQL_JUGADORES);
            rs = statement.executeQuery();
            int i;
            while(rs.next()){
                i=1;
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt(i++));
                usuario.setNombre(rs.getString(i++));
                usuario.setApellido(rs.getString(i++));
                usuario.setCedula(rs.getString(i++));
                usuario.setEstado(rs.getBoolean(i++));
                usuario.setNombreUsuario(rs.getString(i++));
                usuario.setClave(rs.getString(i++));
                usuario.setTipo(rs.getString(i++));
                usuario.setTelefono(rs.getString(i++));
                 DateFormat format = new SimpleDateFormat("yyyy-M-dd");
                Date date = null;
                String fechaNacimiento = rs.getString(i++);
                try {
                    date = format.parse(fechaNacimiento);
                } catch (ParseException ex) {
                    Logger.getLogger(ModificarUsuarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }
                usuario.setFechaNacimiento(date);
                
                jugadores.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return jugadores;
    }
    
    public ArrayList<Usuario> getArbitros(){
        ArrayList<Usuario> arbitros = new ArrayList<Usuario>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Usuario usuario;
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_ARBITROS);
            statement = connection.prepareStatement(SQL_ARBITROS);
            rs = statement.executeQuery();
            int i;
            while(rs.next()){
                i=1;
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt(i++));
                usuario.setNombre(rs.getString(i++));
                usuario.setApellido(rs.getString(i++));
                usuario.setCedula(rs.getString(i++));
                usuario.setEstado(rs.getBoolean(i++));
                usuario.setNombreUsuario(rs.getString(i++));
                usuario.setClave(rs.getString(i++));
                usuario.setTipo(rs.getString(i++));
                usuario.setTelefono(rs.getString(i++));
                 DateFormat format = new SimpleDateFormat("yyyy-M-dd");
                Date date = null;
                String fechaNacimiento = rs.getString(i++);
                try {
                    date = format.parse(fechaNacimiento);
                } catch (ParseException ex) {
                    Logger.getLogger(ModificarUsuarioCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }
                usuario.setFechaNacimiento(date);
                
                arbitros.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return arbitros;
    }
    /*
    public int delete(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        int rows = 0;
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            statement = connection.prepareStatement(SQL_DELETE);
            int index = 1;
            statement.setInt(index, id);
            rows = statement.executeUpdate();
            System.out.println("Registros borrados:" + rows);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return rows;
    }
    
    public boolean buscarUser(String nombreUsuario, String contrasena, int idTipoUsuario){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean existe=false;
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_SELECT_NAME);
            statement = connection.prepareStatement(SQL_SELECT_NAME);
            statement.setString(1, nombreUsuario);
            rs = statement.executeQuery();
            rs.next();
            String contrasenaDB = rs.getString(3);
            int idTipoUsuarioDB = rs.getInt(5);
            if(contrasenaDB.equals(contrasena) && idTipoUsuarioDB == idTipoUsuario){
                existe=true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return existe;
    }
    
    public String getPassword(String username){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String password = "";
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_SELECT_NAME);
            statement = connection.prepareStatement(SQL_SELECT_NAME);
            statement.setString(1, username);
            rs = statement.executeQuery();
            rs.next();
            password = rs.getString(3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return password;
    }
    
    public String getPassword(int userId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String password = "";
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_SELECT_ID);
            statement = connection.prepareStatement(SQL_SELECT_ID);
            statement.setInt(1, userId);
            rs = statement.executeQuery();
            rs.next();
            password = rs.getString(3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return password;
    }
    
    public boolean setPassword(String username, String newPassword){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean wasChanged = false;
        int rows = 0;
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_UPDATE_PASSWORD);
            statement = connection.prepareStatement(SQL_UPDATE_PASSWORD);
            int index = 1;
            statement.setString(index++, newPassword);
            statement.setString(index++, username);
            rows = statement.executeUpdate();
            System.out.println("Registros actualizados:" + rows);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        if (rows > 0) {
            wasChanged = true;
        }
        return wasChanged;
    }
    
    public boolean setPassword(int userId, String newPassword){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean wasChanged = false;
        int rows = 0;
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_UPDATE_PASSWORD_ID);
            statement = connection.prepareStatement(SQL_UPDATE_PASSWORD_ID);
            int index = 1;
            statement.setString(index++, newPassword);
            statement.setInt(index++, userId);
            rows = statement.executeUpdate();
            System.out.println("Registros actualizados:" + rows);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        if (rows > 0) {
            wasChanged = true;
        }
        return wasChanged;
    }
    public int getId(String username){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        int idUser = 0;
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_SELECT_NAME);
            statement = connection.prepareStatement(SQL_SELECT_NAME);
            int index = 1;
            statement.setString(index++, username);
            rs = statement.executeQuery();
            rs.next();
            idUser = rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return idUser;
    }
    
    public ArrayList<Usuario> getAllUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Usuario usuario = null;
        Cliente cliente;
        try {
            connection = DBManager.getConnection();
            System.out.println("Ejecutando query:" + SQL_USUARIO_CLIENTE);
            statement = connection.prepareStatement(SQL_USUARIO_CLIENTE);
            rs = statement.executeQuery();
            while(rs.next()){
                switch(rs.getInt(5)){
                    case Usuario.ADMINISTRADOR:
                        usuario = new Usuario();
                        usuario.setIdUsuario(rs.getInt(1));
                        usuario.setNombreUsuario(rs.getString(2));
                        usuario.setContrasena(rs.getString(3));
                        usuario.setHabilitado(rs.getInt(4));
                        usuario.setIdTipoUsuario(rs.getInt(5));
                        break;
                    case Usuario.CLIENTE:
                        cliente = FactoriaCliente.getCliente(rs.getInt(13));
                        cliente.setIdUsuario(rs.getInt(1));
                        cliente.setNombreUsuario(rs.getString(2));
                        cliente.setContrasena(rs.getString(3));
                        cliente.setHabilitado(rs.getInt(4));
                        cliente.setIdTipoUsuario(rs.getInt(5));
                        cliente.setIdCliente(rs.getInt(6));
                        cliente.setIdentificacion(rs.getString(7));
                        cliente.setNombres(rs.getString(9));
                        cliente.setTelefonos(rs.getString(10));
                        cliente.setDireccion(rs.getString(11));
                        cliente.setCorreo(rs.getString(12));
                        cliente.setIdTipoCliente(rs.getInt(13));
                        usuario = cliente;
                        break;
                }
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.close(connection);
        }
        return usuarios;
    }*/
}
