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
