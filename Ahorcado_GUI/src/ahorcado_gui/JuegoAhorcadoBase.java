/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcado_gui;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public abstract class JuegoAhorcadoBase implements JuegoAhorcado {
    
    protected String palabraSecreta;
    protected String palabraActual;
    protected int intentos;
    protected int limiteIntentos = 6;
    protected ArrayList<Character> letrasUsadas;
    protected ArrayList<String> figuraAhorcado;
    
    public JuegoAhorcadoBase(){
        this.letrasUsadas = new ArrayList<>();
        this.figuraAhorcado = new ArrayList<>();
        this.intentos = limiteIntentos;
        
    }
    
    public abstract void actualizarPalabraActual(char letra);
    public abstract boolean verificarLetra(char letra);
    public abstract boolean hasGanado();
    
    private void preperarFiguras(){
        figuraAhorcado.add("  +---+\n  |   |\n      |\n      |\n      |\n      |\n========="); // 0 errores
        figuraAhorcado.add("  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n========="); // 1 error (cabeza)
        figuraAhorcado.add("  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n========="); // 2 errores (tronco)
        figuraAhorcado.add("  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n========="); // 3 errores (brazo izq)
        figuraAhorcado.add("  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n========="); // 4 errores (brazo der)
        figuraAhorcado.add("  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n========="); // 5 errores (pierna izq)
        figuraAhorcado.add("  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="); // 6 errores (pierna der)
    }
    
    protected boolean esLetraRpetida(char letra){
        return letrasUsadas.contains(Character.toLowerCase(letra));
    }
}
