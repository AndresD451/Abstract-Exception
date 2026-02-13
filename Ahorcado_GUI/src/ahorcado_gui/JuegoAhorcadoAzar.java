package ahorcado_gui;

import java.util.Random;

public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {

    private AdminPalabrasSecretas admin;

    public JuegoAhorcadoAzar(AdminPalabrasSecretas admin) {
        super();
        this.admin = admin;
        inicializarPalabraSecreta();
    }

    @Override
    public void inicializarPalabraSecreta() {
        palabraSecreta = admin.obtenerPalabraAzar().toUpperCase();
        palabraActual = "_".repeat(palabraSecreta.length());
    }

    @Override
    public boolean verificarLetra(char letra) {
        return palabraSecreta.indexOf(Character.toUpperCase(letra)) >= 0;
    }

    @Override
    public void actualizarPalabraActual(char letra) {
        char[] actual = palabraActual.toCharArray();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == Character.toUpperCase(letra)) {
                actual[i] = Character.toUpperCase(letra);
            }
        }
        palabraActual = new String(actual);
    }

    @Override
    public boolean hasGanado() {
        return palabraActual.equals(palabraSecreta);
    }

    @Override
    public void jugar() {
    }
}
