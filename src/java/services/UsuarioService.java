/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import java.util.ArrayList;
import java.util.Random;
import modelo.Usuario;

/**
 *
 * @author DELL
 */
public class UsuarioService {

    public UsuarioService() {
    }
    
    public static Usuario getRandom(ArrayList<Usuario> usuarios) {
        int rnd = new Random().nextInt(usuarios.size());
        return usuarios.get(rnd);
    }
}
