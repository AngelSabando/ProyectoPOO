package controller;

import model.ConexionDB;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class TramiteController {

    // Obtener trámites asociados a un abogado
    public DefaultTableModel obtenerTramites(int abogadoId) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"ID", "Cliente", "Tipo", "Descripción", "Estado"});

        try (Connection con = ConexionDB.conectar();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM tramites WHERE abogado_id = ?")) {

            pst.setInt(1, abogadoId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("cliente"),
                    rs.getString("tipo"),
                    rs.getString("descripcion"),
                    rs.getString("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener trámites: " + ex.getMessage());
        }
        return modelo;
    }

    // Agregar un nuevo trámite
    public boolean agregarTramite(int abogadoId, String cliente, String tipoTramite, String subtipoTramite, 
                              String descripcion, String estado, String documentos, 
                              Date fechaInicio, Date fechaEstimada, Double precio) {
    String sql = "INSERT INTO tramites (abogado_id, cliente, tipo_tramite, subtipo_tramite, descripcion, estado, documentos, fecha_inicio, fecha_estimada, precio) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection con = ConexionDB.conectar();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setInt(1, abogadoId);
        pst.setString(2, cliente);
        pst.setString(3, tipoTramite);
        pst.setString(4, subtipoTramite);
        pst.setString(5, descripcion);
        pst.setString(6, estado);
        pst.setString(7, documentos);
        pst.setDate(8, fechaInicio != null ? new java.sql.Date(fechaInicio.getTime()) : null);
        pst.setDate(9, fechaEstimada != null ? new java.sql.Date(fechaEstimada.getTime()) : null);
        pst.setObject(10, precio); // Permitir que sea NULL

        pst.executeUpdate();
        return true;
    } catch (SQLException ex) {
        System.out.println("Error al agregar trámite: " + ex.getMessage());
        return false;
    }
}



    // Modificar un trámite existente
    public boolean modificarTramite(int id, String cliente, String tipo, String descripcion, String estado) {
        String sql = "UPDATE tramites SET cliente=?, tipo=?, descripcion=?, estado=? WHERE id=?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, cliente);
            pst.setString(2, tipo);
            pst.setString(3, descripcion);
            pst.setString(4, estado);
            pst.setInt(5, id);

            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al modificar trámite: " + ex.getMessage());
            return false;
        }
    }

    // Eliminar un trámite
    public boolean eliminarTramite(int id) {
        String sql = "DELETE FROM tramites WHERE id = ?";

        try (Connection con = ConexionDB.conectar(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            int filasAfectadas = pst.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar trámite: " + ex.getMessage());
            return false;
        }
    }

    
    public void obtenerTramitesPorTipo(String tipo, DefaultTableModel modelo, int abogadoId) {
        String sql = "SELECT id, cliente, subtipo_tramite, descripcion, estado, fecha_creacion FROM tramites WHERE abogado_id = ? AND tipo_tramite = ?";

        try (Connection con = ConexionDB.conectar(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, abogadoId);
            pst.setString(2, tipo);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("cliente"),
                    rs.getString("subtipo_tramite"), // Ahora muestra el subtipo de trámite
                    rs.getString("descripcion"),
                    rs.getString("estado"),
                    rs.getTimestamp("fecha_creacion")
                });
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener trámites: " + ex.getMessage());
        }
    }



    public void obtenerTodosLosTramites(DefaultTableModel modelo, int abogadoId) {
        String sql = "SELECT * FROM tramites WHERE abogado_id = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, abogadoId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("cliente"),
                    rs.getString("tipo_tramite"),
                    rs.getString("subtipo_tramite"),
                    rs.getString("descripcion"),
                    rs.getString("estado"),
                    rs.getTimestamp("fecha_creacion")
                });
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener reportes: " + ex.getMessage());
        }
    }
}
