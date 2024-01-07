package org.example;


import java.io.Serializable;

public class PaqueteEnvio implements Serializable {
    public static final long serialVersionUID = 1L;
    public String nick,IP,mensaje;

    PaqueteEnvio(String nick,String IP, String mensaje){
        this.nick=nick;
        this.IP=IP;
        this.mensaje=mensaje;
    }

    public PaqueteEnvio() {

    }

    public String getIP() {
        return IP;
    }
    public  String getNick(){
        return  nick;
    }
    public String getMensaje(){
        return mensaje;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
    public void setNick(String nick){
        this.nick=nick;
    }
    public void setMensaje(String mensaje){
        this.mensaje=mensaje;
    }
}
