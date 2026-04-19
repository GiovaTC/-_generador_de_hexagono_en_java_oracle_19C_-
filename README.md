# -_generador_de_hexagono_en_java_oracle_19C_- :.
🧩 Generador de Hexágono en Java + Oracle 19c:

<img width="1024" height="1024" alt="image" src="https://github.com/user-attachments/assets/c8d83ff6-5761-4626-8199-ad93c1a7aa45" />  

```
📐 Arquitectura Propuesta:
Capas del sistema
Vista (Swing GUI) → Muestra el hexágono
Servicio → Genera el patrón
DAO (JDBC) → Guarda en Oracle
Oracle 19c → Persistencia

🧠 Lógica del Hexágono:
Un hexágono de texto se construye en dos secciones principales:

🔼 Parte superior (creciente)
🔽 Parte inferior (decreciente) .

📦 1. Script Oracle 19c:
CREATE TABLE HEXAGONO_LOG (
    ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    CONTENIDO CLOB,
    TAMANO NUMBER,
    FECHA_REGISTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

⚙️ 2. Clase de Conexión (JDBC):
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionOracle {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "system";
    private static final String PASS = "oracle";

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

🧮 3. Servicio (Logica del Hexagono):
package servicio;

public class HexagonoService {

    public String generarHexagono(int n) {
        StringBuilder sb = new StringBuilder();

        // Parte superior
        for (int i = 0; i < n; i++) {
            sb.append(" ".repeat(n - i));
            sb.append("* ".repeat(n + i));
            sb.append("\n");
        }

        // Parte inferior
        for (int i = n - 2; i >= 0; i--) {
            sb.append(" ".repeat(n - i));
            sb.append("* ".repeat(n + i));
            sb.append("\n");
        }

        return sb.toString();
    }
}

💾 4. DAO (Guardar en Oracle):
package dao;

import conexion.ConexionOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class HexagonoDAO {

    public void guardarHexagono(String contenido, int tamano) {
        String sql = "INSERT INTO HEXAGONO_LOG (CONTENIDO, TAMANO) VALUES (?, ?)";

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

🖥️ 5. Interfaz Gráfica (Swing):
package vista;

import servicio.HexagonoService;
import dao.HexagonoDAO;

import javax.swing.*;
import java.awt.*;

public class HexagonoGUI extends JFrame {

    private JTextArea area;
    private JTextField txtTamano;

    private HexagonoService servicio = new HexagonoService();
    private HexagonoDAO dao = new HexagonoDAO();

    public HexagonoGUI() {
        setTitle("Generador de Hexágono");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel top = new JPanel();
        top.add(new JLabel("Tamaño:"));
        txtTamano = new JTextField(5);
        top.add(txtTamano);

        JButton btnGenerar = new JButton("Generar");
        top.add(btnGenerar);

        panel.add(top, BorderLayout.NORTH);

        area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(area);
        panel.add(scroll, BorderLayout.CENTER);

        add(panel);

        btnGenerar.addActionListener(e -> generarHexagono());
    }

    private void generarHexagono() {
        try {
            int n = Integer.parseInt(txtTamano.getText());

            String resultado = servicio.generarHexagono(n);
            area.setText(resultado);

            // Guardar en BD
            dao.guardarHexagono(resultado, n);

            JOptionPane.showMessageDialog(this, "Hexágono guardado en Oracle");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}

🚀 6. Clase Principal:
package main;

import vista.HexagonoGUI;

public class Main {
    public static void main(String[] args) {
        new HexagonoGUI().setVisible(true);
    }
}

🎯 Resultado Esperado:
Ejemplo con n = 4:

    * * * * 
   * * * * * 
  * * * * * * 
 * * * * * * * 
  * * * * * * 
   * * * * * 
    * * * *

: . / .  
