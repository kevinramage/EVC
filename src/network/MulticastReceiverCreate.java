package network;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import project.Client;

import command.I_Command;

public class MulticastReceiverCreate extends Thread implements Runnable {

    private transient MulticastSocket receptionSocket ;
    private Client client;

    public MulticastReceiverCreate (final Client client, final String groupName, final int diffusionPort) {
    	receptionSocket = null ;
    	this.client = client;
        try {
           InetAddress adresseDiffusion = InetAddress.getByName (groupName) ;
            receptionSocket = new MulticastSocket (diffusionPort) ;
            receptionSocket.joinGroup (adresseDiffusion) ;
            // pour pouvoir envoyer du multicast aussi en local
            receptionSocket.setLoopbackMode (false) ;
           // System.out.println ("reception socket : " + receptionSocket.getLocalPort() + " " + receptionSocket.getInetAddress ()) ;
        } catch (Exception e) {
            e.printStackTrace () ;
        }
    }
    
    public void receiveMessage() {
        try {
            byte [] message = new byte [1024] ;
            DatagramPacket paquet = new DatagramPacket (message, message.length) ;
            receptionSocket.receive (paquet) ;
            ByteArrayInputStream bais = new ByteArrayInputStream (paquet.getData ()) ;
            ObjectInputStream ois = new ObjectInputStream (bais) ;
           
            // Get object
            I_Command cmd = (I_Command)ois.readObject();
            client.addObject(cmd);
            
            
        } catch (Exception e) {
            e.printStackTrace () ;
        }
    }

    public void run () {
        while (true) {
        	receiveMessage();
        }
    }

}
