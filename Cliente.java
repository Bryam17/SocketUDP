/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servidorecoupd;
import java.io.*;
import java.net.*;

/**
 *
 * @author bryam
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("------------CLIENTE------------\n");
        
        try {
            
            DatagramSocket ClienteUDP = new DatagramSocket();
            int puerto = 5001;
            InetAddress host = InetAddress.getByName("localhost");
            String cadena;
            
            do{
                
                
                BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Ingrese una cadena: ");
                cadena = entrada.readLine();
                
                byte [] mensaje= cadena.getBytes();
            
                DatagramPacket peticion = new DatagramPacket(mensaje, cadena.length(), host, puerto);
                ClienteUDP.send(peticion);

                DatagramPacket respuesta = new DatagramPacket(mensaje, cadena.length());
                ClienteUDP.receive(respuesta);

                System.out.println("Mensaje del server: " + new String(respuesta.getData(), 0, respuesta.getLength()));
                
            }while(!"".equals(cadena));
            
            //ClienteUDP.close();
            
        } catch (SocketException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
   }
    }
    
}
