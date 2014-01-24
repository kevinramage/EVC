package fr.istic.evc.network;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import fr.istic.evc.Command.CmdCreateCObject;
import fr.istic.evc.Command.I_Command;
import fr.istic.evc.Command.I_CreateCommand;
import fr.istic.evc.object3D.base.abstraction.AObject;
import fr.istic.evc.object3D.base.abstraction.I_AObject;
import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.project.Client;

public class MulticastReceiverDelete extends Thread implements Runnable {

    private transient MulticastSocket receptionSocket ;
    private Client client;

    public MulticastReceiverDelete (final Client client, final String groupName, final int diffusionPort) {
    	receptionSocket = null ;
    	this.client = client;
        try {
           InetAddress adresseDiffusion = InetAddress.getByName (groupName) ;
            receptionSocket = new MulticastSocket (diffusionPort) ;
            receptionSocket.joinGroup (adresseDiffusion) ;
            // pour pouvoir envoyer du multicast aussi en local
            receptionSocket.setLoopbackMode (true) ;
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
            client.removeObjects(cmd);
            
            
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
