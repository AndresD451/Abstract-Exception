/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcado_gui;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author hermi
 */
public class AdminPalabrasSecretas {
    private ArrayList<String> palabras;
    
    public AdminPalabrasSecretas(){
        this.palabras = new ArrayList<>();
    }
    
    public void agregarPalabra(String palabra){
        String palabraLimpia = palabra.trim().toUpperCase();
        
        if (!palabraLimpia.isEmpty() && !palabra.contains(palabraLimpia)){
            palabras.add(palabraLimpia);
            System.out.println("Palabra agregada: " + palabraLimpia);
        }else{
            System.err.println("La palabra '" + palabraLimpia +"' ya existe.");
        }
    }
    
    public String obtenerPalabraAzar(){
        if (palabras.isEmpty()){
            return "AHORCADO";
        }
        
        Random random = new Random();
        int indiceAleatorio = random.nextInt(palabras.size());
        return palabras.get(indiceAleatorio);
    }
    
    public int totalPalabras(){
        return palabras.size();
    }
    
    
    
    
}
