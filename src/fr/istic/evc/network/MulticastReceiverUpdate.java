package fr.istic.evc.network;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import fr.istic.evc.Command.CmdChangeColor;
import fr.istic.evc.Command.Command;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.project.Client;

public class MulticastReceiverUpdate extends Thread implements Runnable {

    private transient MulticastSocket receptionSocket ;
	private Client client;

    public MulticastReceiverUpdate (final Client client, final String groupName, final int diffusionPort) {
        this.client = client;
    	receptionSocket = null ;
        try {
           InetAddress adresseDiffusion = InetAddress.getByName (groupName) ;
            receptionSocket = new MulticastSocket (diffusionPort) ;
            receptionSocket.joinGroup (adresseDiffusion) ;
            // pour pouvoir envoyer du multicast aussi en local
            receptionSocket.setLoopbackMode (false) ;
            //System.out.println ("reception socket : " + receptionSocket.getLocalPort() + " " + receptionSocket.getInetAddress ()) ;
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
            Command cmd = (CmdChangeColor)ois.readObject () ;
            cmd.execute(client.getObjects());
            
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
