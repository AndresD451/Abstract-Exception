/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ahorcado_gui;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author user
 */

public class Ahorcado_GUI extends JFrame {
    // Lógica
    private JuegoAhorcadoBase juegoActual;
    private AdminPalabrasSecretas admin;

    // Componentes Visuales
    private JLabel lblPalabra, lblIntentos, lblMensajes;
    private JTextArea txtFigura;
    private JTextField txtEntrada;
    private JButton btnAdivinar, btnModoFijo, btnModoAzar, bthAgregarPalabra;

    public Ahorcado_GUI() {
        admin = new AdminPalabrasSecretas();
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Juego del Ahorcado");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        // Panel Superior: Selección de Modo
        JPanel pnlModos = new JPanel();
        btnModoFijo = new JButton("Modo Fijo");
        btnModoAzar = new JButton("Modo Azar");
        pnlModos.add(btnModoFijo);
        pnlModos.add(btnModoAzar);
        add(pnlModos, BorderLayout.NORTH);

        // Panel Central: Figura y Palabra
        JPanel pnlCentro = new JPanel(new GridLayout(2, 1));
        txtFigura = new JTextArea();
        txtFigura.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtFigura.setEditable(false);
        lblPalabra = new JLabel("_ _ _ _", SwingConstants.CENTER);
        lblPalabra.setFont(new Font("SansSerif", Font.BOLD, 24));
        pnlCentro.add(new JScrollPane(txtFigura));
        pnlCentro.add(lblPalabra);
        add(pnlCentro, BorderLayout.CENTER);

        // Panel Inferior: Entrada de texto y Mensajes
        JPanel pnlInferior = new JPanel(new GridLayout(3, 1));
        lblIntentos = new JLabel("Intentos restantes: 6");
        lblMensajes = new JLabel("Seleccione un modo para empezar");
        JButton btnAgregarPalabra = new JButton("Añadir Palabra al Diccionario");
        
        JPanel pnlInput = new JPanel();
        txtEntrada = new JTextField(2);
        btnAdivinar = new JButton("Adivinar");
        pnlInput.add(new JLabel("Letra:"));
        pnlInput.add(txtEntrada);
        pnlInput.add(btnAdivinar);
        

        pnlInferior.add(lblIntentos);
        pnlInferior.add(pnlInput);
        pnlInferior.add(lblMensajes);
        pnlInferior.add(btnAgregarPalabra);
        
        add(pnlInferior, BorderLayout.SOUTH);

        // --- Eventos ---
        btnModoFijo.addActionListener(e -> iniciarFijo());
        btnModoAzar.addActionListener(e -> iniciarAzar());
        btnAdivinar.addActionListener(e -> procesarLetra());
        btnAgregarPalabra.addActionListener(e -> agregarPalabra());
    }

    private void agregarPalabra(){
        try {
                // 1. Pedir la palabra al usuario con un Pop-up de entrada
                String nuevaPalabra = JOptionPane.showInputDialog(this, 
                        "Escriba la nueva palabra secreta:", 
                        "Configuración de Diccionario", 
                        JOptionPane.QUESTION_MESSAGE);

                // 2. Validar que el usuario no haya dado a "Cancelar"
                if (nuevaPalabra != null && !nuevaPalabra.trim().isEmpty()) {

                    // 3. Llamar al admin para agregarla al ArrayList
                    admin.agregarPalabra(nuevaPalabra);

                    JOptionPane.showMessageDialog(this, "¡Palabra agregada con éxito!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
    }
    
    private void iniciarFijo() {
        String p = JOptionPane.showInputDialog("Ingrese la palabra secreta:");
        if (p != null && !p.isEmpty()) {
            juegoActual = new JuegoAhorcadoFijo(p);
            actualizarPantalla();
            lblMensajes.setText("Modo Fijo iniciado.");
        }
    }

    private void iniciarAzar() {
        juegoActual = new JuegoAhorcadoAzar(admin);
        actualizarPantalla();
        lblMensajes.setText("Modo Azar iniciado.");
    }

    private void procesarLetra() {
        try {
            // Validación inicial: Si no hay juego seleccionado
            if (juegoActual == null) {
                throw new Exception("Primero debes seleccionar un modo de juego.");
            }

            String input = txtEntrada.getText().trim().toUpperCase();

            // Validación: Si el campo está vacío
            if (input.isEmpty()) {
                throw new Exception("Por favor, ingresa una letra.");
            }

            char letra = input.charAt(0);

            // 1. Lanzamos nuestra propia excepción si la letra ya existe
            if (juegoActual.esLetraRepetida(letra)) {
                throw new LetraRepetidaException("¡Cuidado! Ya intentaste con la letra " + letra);
            }

            // 2. Si pasa las validaciones, procesamos
            juegoActual.procesarEntrada(letra);
            actualizarPantalla();
            verificarEstado();

        } catch (LetraRepetidaException e) {
            // Atrapa específicamente el error de letra duplicada
            JOptionPane.showMessageDialog(this, e.getMessage(), "Letra Repetida", JOptionPane.WARNING_MESSAGE);

        } catch (Exception e) {
            // Atrapa cualquier otro error (como el de juego no iniciado o campo vacío)
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de entrada", JOptionPane.ERROR_MESSAGE);

        } finally {
            // Este bloque siempre se ejecuta: limpia el campo para el siguiente intento
            txtEntrada.setText("");
            txtEntrada.requestFocus(); 
        }
    }

    private void actualizarPantalla() {
        lblPalabra.setText(juegoActual.palabraActual);
        lblIntentos.setText("Intentos restantes: " + (juegoActual.limiteIntentos - juegoActual.intentos));
        txtFigura.setText(juegoActual.figuraAhorcado.get(juegoActual.intentos));
    }

    private void verificarEstado() {
        if (juegoActual.hasGanado()) {
            JOptionPane.showMessageDialog(this, "¡VICTORIA! Palabra: " + juegoActual.palabraSecreta);
            juegoActual = null;
        } else if (juegoActual.intentos >= juegoActual.limiteIntentos) {
            JOptionPane.showMessageDialog(this, "DERROTA. La palabra era: " + juegoActual.palabraSecreta);
            juegoActual = null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ahorcado_GUI().setVisible(true));
    }
}
