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
public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {
    
    JuegoAhorcadoFijo (String palabraSecreta ){
        super();
        this.palabraSecreta = palabraSecreta.toUpperCase();
        inicializarPalabraSecreta();
    }
        
    @Override
    public void inicializarPalabraSecreta(){
        iniciarGuiones();
    }
        
    @Override
    public void actualizarPalabraActual(char letra){
        letra = Character.toUpperCase(letra);
        char [] caracteres = palabraActual.toCharArray();

        for (int i = 0;  i < palabraSecreta.length(); i++){
            if (palabraSecreta.charAt(i) == letra){
                caracteres[i] = letra;

            }
            this.palabraActual = new String (caracteres); 
        }
    }

    @Override
    public boolean verificarLetra (char letra){
        char letraUpper = Character.toUpperCase(letra);
        return palabraSecreta.indexOf(letraUpper) >= 0; 
    }
    
    @Override
    public boolean hasGanado(){
        return palabraActual.equals(palabraSecreta);
    }

    @Override
    public void jugar() {
    }

}

