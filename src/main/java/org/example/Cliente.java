package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import  java.net.*;


public class Cliente {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MarcoCliente mimarco = new MarcoCliente();
        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MarcoCliente extends JFrame {

    public MarcoCliente() {
        setBounds(600, 300, 280, 350);
        LaminaMarcoCliente milamina = new LaminaMarcoCliente();
        add(milamina);
        setVisible(true);
    }
}

class LaminaMarcoCliente extends JPanel implements ActionListener {

    public LaminaMarcoCliente() {
        tfNick=new JTextField(8);
        add(tfNick);
        JLabel texto = new JLabel("CLIENTE");
        add(texto);
        tfIP=new JTextField(8);
        add (tfIP);
        taCliente=new JTextArea(12,20);
        add(taCliente);
        tfTexto = new JTextField(20);
        add(tfTexto);
        btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(this);
        add(btnEnviar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnEnviar) {
            //System.out.println("Se ha tecleado:"+tfTexto.getText().trim());
            try {
                Socket socketCliente=new Socket("192.168.18.3",9999);
                PaqueteEnvio datos=new PaqueteEnvio();
                datos.setNick(tfNick.getText().trim());
                datos.setIP(tfIP.getText().trim());
                datos.setMensaje(tfTexto.getText().trim());

                    ObjectOutputStream flujoSalida = new ObjectOutputStream((socketCliente.getOutputStream()));

                    flujoSalida.writeObject(datos);
                    socketCliente.close();


            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException(ex);
            }

        }
    }

    public JTextField tfTexto,tfNick,tfIP;
    public JButton btnEnviar;
    public JTextArea taCliente;

}