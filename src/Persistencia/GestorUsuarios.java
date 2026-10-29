package Persistencia;

import Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private List<Usuario> listaUsuarios;
    public GestorUsuarios() {
        this.listaUsuarios = new ArrayList<>();
    }
    public List<Usuario> getListaUsuarios() {return listaUsuarios;}
    public void agregarUsuario(Usuario usuario) {listaUsuarios.add(usuario);}
}
