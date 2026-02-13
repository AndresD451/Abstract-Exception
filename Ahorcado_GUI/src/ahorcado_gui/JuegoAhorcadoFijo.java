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
        this.palabraSecreta = palabraSecreta.toLowerCase();
        inicializarPalabraSecreta();
    }
        
        public void inicializarPalabraSecreta(){
            iniciarGuiones();
        }
        
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

public boolean verificarLetra (char letra){
    letra = Character.toUpperCase(letra);
    return palabraSecreta.indexOf(letra) >=0;
    
}

public boolean hasGanado(){
    return palabraActual.equals(palabraSecreta);
}

}

