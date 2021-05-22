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
public class ServidorUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception{
        // TODO code application logic here
        
        System.out.println("SERVER EN ESPERA DEL CLIENTE...");
        
        try {
            DatagramSocket server = new DatagramSocket(5001);
            byte[] buffer = new byte[1024];
            
              while (true) {   
                
                DatagramPacket peticion = new DatagramPacket(buffer,buffer.length);
                server.receive(peticion);
                
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();
                
                /*System.out.println("IP: "+peticion.getAddress());
                System.out.println("Puerto: "+peticion.getPort());*/
                System.out.println("Mensaje del cliente: "+ new String(peticion.getData(),0,peticion.getLength()));
                //server.close();
                
                byte [] buf = new byte[1024];
                
                String mensaje = new String(peticion.getData()); 
                
                buf = mensaje.getBytes();
                
                DatagramPacket respuesta = new DatagramPacket(buf, mensaje.length(), direccion, puertoCliente);
                server.send(respuesta);
                
            }
            
        } catch (SocketException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
}
