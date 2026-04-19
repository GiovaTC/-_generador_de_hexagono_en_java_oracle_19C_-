package dao;

import conexion.ConexionOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class HexagonoDAO {

    public void guardarHexagono(String contenido, int tamano) {
        String sql = "INSERT INTO HEXAGONO_LOG (CONTENIDO,TAMANO) VALUES (?, ?)";

        try (Connection conn = ConexionOracle.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, contenido);
            ps.setInt(2, tamano);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}   
