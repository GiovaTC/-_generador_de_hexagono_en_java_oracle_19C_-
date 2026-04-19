package servicio;

public class HexagonoService {

    public String generarHexagono(int n) {
        StringBuilder sb = new StringBuilder();

        // parte superior .
        for (int i = 0; i < n; i++) {
            sb.append(" ".repeat(n - i));
            sb.append("* ".repeat(n + i));
            sb.append("\n");
        }

        // parte inferior .
        for (int i = n - 2; i >= 0; i--) {
            sb.append(" ".repeat(n - i));
            sb.append("* ".repeat(n + i));
            sb.append("\n");
        }

        return sb.toString();
    }
}
