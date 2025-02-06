package controller;

import model.ConexionDB;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioController {

    public Usuario autenticarUsuario(String usuario, String contraseña) {
    try (Connection con = ConexionDB.conectar(); 
         PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios WHERE usuario=? AND contraseña=?")) {

        pst.setString(1, usuario);
        pst.setString(2, contraseña);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            return new Usuario(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("usuario"),
                rs.getString("contraseña"),
                rs.getString("rol"),
                rs.getString("correo"),
                rs.getString("celular"),
                rs.getString("fecha_creacion")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

}
