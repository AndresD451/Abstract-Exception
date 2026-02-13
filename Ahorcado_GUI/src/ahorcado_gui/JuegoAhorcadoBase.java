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
        this.intentos = 0;
        preperarFiguras();
    }
    
    protected void iniciarGuiones(){
        StringBuilder sb = new StringBuilder();
        for (int contador=0; contador<palabraSecreta.length(); contador++){
            sb.append("_");
        }
        this.palabraActual = sb.toString();
    }
    
    public abstract void actualizarPalabraActual(char letra);
    public abstract boolean verificarLetra(char letra);
    public abstract boolean hasGanado();
    
    private void preperarFiguras(){
        figuraAhorcado.add("  +---+\n  |   |\n      |\n      |\n      |\n      |\n========="); 
        figuraAhorcado.add("  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n========="); 
        figuraAhorcado.add("  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n========="); 
        figuraAhorcado.add("  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n========="); 
        figuraAhorcado.add("  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n========="); 
        figuraAhorcado.add("  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n========="); 
        figuraAhorcado.add("  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="); 
    }
    
    public void procesarEntrada(char letra){
        letra = Character.toUpperCase(letra);
        
        if (esLetraRepetida(letra)){
            try {            
                throw new EntradaInvalidaException("La letra '" + letra + "' ya fue utilizada.");
            } catch (EntradaInvalidaException ex) {
                System.getLogger(JuegoAhorcadoBase.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        
        letrasUsadas.add(letra);
        
        if (verificarLetra(letra)){
            actualizarPalabraActual(letra);
        }else{
            intentos++;
        }
    }
    
    protected boolean esLetraRepetida(char letra){
        return letrasUsadas.contains(Character.toUpperCase(letra));
    }
}
