package org.example;

import javax.swing.*;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.*;

public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}

class MarcoServidor extends JFrame implements Runnable{
	
	public MarcoServidor(){
		setBounds(1200,300,280,350);
		JPanel milamina= new JPanel();
		milamina.setLayout(new BorderLayout());
		taTexto=new JTextArea();
		milamina.add(taTexto,BorderLayout.CENTER);
		add(milamina);
		setVisible(true);
		Thread hiloServidor=new Thread(this);
		hiloServidor.start();
		}
	
	public 	JTextArea taTexto;

	@Override
	public void run() {
		//Se abre el puerto 9999 y se pone a la escucha
		try {
			ServerSocket socketServidor=new ServerSocket(9999);
			String nick,IP,mensaje;
			PaqueteEnvio datosRecibidos;
			while (true) {
				//Socket para recibir de N clientes
				Socket miSocketServidor = socketServidor.accept();
				ObjectInputStream flujoEntrada = new ObjectInputStream(miSocketServidor.getInputStream());
				datosRecibidos=(PaqueteEnvio) flujoEntrada.readObject();
				//miSocketServidor.close();
				nick=datosRecibidos.getNick();
				IP=datosRecibidos.getIP();
				mensaje=datosRecibidos.getMensaje();
				taTexto.append("\n" + nick+" : "+mensaje+" para " +IP);

				//Socket para enviar al cliente con Ip anterior
				Socket enviaCliente=new Socket(IP,9090);
				ObjectOutputStream paqueteSalida= new ObjectOutputStream(enviaCliente.getOutputStream());
				paqueteSalida.writeObject(datosRecibidos);

				enviaCliente.close();
				miSocketServidor.close();
			}

		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
