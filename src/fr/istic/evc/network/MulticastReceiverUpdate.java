package fr.istic.evc.network;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import fr.istic.evc.Command.I_Command;
import fr.istic.evc.project.Client;
import fr.istic.evc.project.IEntity;

public class MulticastReceiverUpdate extends Thread implements Runnable {
	


	/* ---------- Attributes ---------- */

    private transient MulticastSocket receptionSocket ;
	private IEntity client;
	


	/* ---------- Constructors ---------- */

    public MulticastReceiverUpdate (final Client client, final String groupName, final int diffusionPort) {
        this.client = client;
    	receptionSocket = null ;
        try {
           InetAddress adresseDiffusion = InetAddress.getByName (groupName) ;
            receptionSocket = new MulticastSocket (diffusionPort) ;
            receptionSocket.joinGroup (adresseDiffusion) ;
            // pour pouvoir envoyer du multicast aussi en local
            receptionSocket.setLoopbackMode (false) ;
        } catch (Exception e) {
            e.printStackTrace () ;
        }
    }
	


	/* ---------- Methods ---------- */

    public void receiveMessage() {
        try {
            byte [] message = new byte [1024] ;
            DatagramPacket paquet = new DatagramPacket (message, message.length) ;
            receptionSocket.receive (paquet) ;
            ByteArrayInputStream bais = new ByteArrayInputStream (paquet.getData ()) ;
            ObjectInputStream ois = new ObjectInputStream (bais) ;
           
            // Get object
            I_Command cmd = (I_Command)ois.readObject () ;
            cmd.execute(client);
            
            
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
