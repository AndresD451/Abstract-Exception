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
        agregarPalabra("JAVA");
        agregarPalabra("PROGRAMACION");
        agregarPalabra("GITHUB");
        agregarPalabra("COMPUTADORA");
        agregarPalabra("MOUSE");
        agregarPalabra("CARGADOR");
        agregarPalabra("TECLADO");
        agregarPalabra("CAMARA");
    }
    
    public boolean agregarPalabra(String palabra) { 
        if (palabra == null) return false;

        String limpia = palabra.trim().toUpperCase();

        if (!limpia.isEmpty() && !palabras.contains(limpia)) {
            palabras.add(limpia);
            return true;
        }
        return false; 
    }
    
    public String obtenerPalabraAzar(){
        if (palabras == null || palabras.isEmpty()){
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
