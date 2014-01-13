package fr.istic.evc.network;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import fr.istic.evc.object3D.base.abstraction.AObject;
import fr.istic.evc.object3D.base.abstraction.IAObject;
import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.project.Client;

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
            IAObject object = new AObject();
            object = (IAObject)ois.readObject () ;
            
            // 
            ICObject controller = new CObject();
            controller.setAbstraction(object);
            
            controller.reload();
            client.addObject(controller);
            
            
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
