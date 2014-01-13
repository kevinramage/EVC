package fr.istic.evc.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.rmi.RemoteException;

import fr.istic.evc.Command.Command;
import fr.istic.evc.object3D.base.abstraction.IAObject;

public class MulticastSender implements Serializable {

    private static final long serialVersionUID = 1L;
    private int createDiffusionPort, updateDiffusionPort ;
    private String groupName ;

    private InetAddress diffusionAddress ;
    private transient MulticastSocket diffusionSocket ;

    public MulticastSender (final String ng, final int createDiffusionPort, final int updateDiffusionPort) throws RemoteException {
        this.createDiffusionPort = createDiffusionPort;
        this.updateDiffusionPort = updateDiffusionPort;
        
        groupName = ng ;
        System.out.println ("Diffuseur sur le port " + createDiffusionPort + " a destination du groupe " + groupName) ;
        diffusionAddress = null ;
        diffusionSocket = null ;
        try {
            diffusionAddress = InetAddress.getByName (groupName) ;
            diffusionSocket = new MulticastSocket () ;
            diffusionSocket.setTimeToLive (64) ;
            diffusionSocket.setLoopbackMode (false) ;
        } catch (IOException e) {
            e.printStackTrace () ;
        }
        System.out.println ("socket : " + diffusionSocket.getLocalPort() + " " + diffusionSocket.getInetAddress ()) ;
    }

    public void diffuseMessage (String name) {
       ByteArrayOutputStream baos = new ByteArrayOutputStream () ;
       ObjectOutputStream oos ;
      try {
           oos = new ObjectOutputStream (baos) ;
           oos.writeObject (name) ;
           oos.flush () ;
      } catch (IOException e) {
          e.printStackTrace();
      }
        DatagramPacket paquet = new DatagramPacket (baos.toByteArray (), baos.toByteArray ().length,
                diffusionAddress,
                createDiffusionPort) ;
        try {
            diffusionSocket.send (paquet);
        } catch (IOException e) {
            e.printStackTrace () ;
        }
    }
    public void createObject (IAObject o) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream () ;
        ObjectOutputStream oos ;
       try {
            oos = new ObjectOutputStream (baos) ;
            oos.writeObject (o) ;
            oos.flush () ;
       } catch (IOException e) {
           e.printStackTrace();
       }
         DatagramPacket paquet = new DatagramPacket (baos.toByteArray (), baos.toByteArray ().length,
                 diffusionAddress,
                 createDiffusionPort) ;
         try {
             diffusionSocket.send (paquet);
         } catch (IOException e) {
             e.printStackTrace () ;
         }
    }
    public void updateObject(Command cmd) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream () ;
        ObjectOutputStream oos ;
       try {
            oos = new ObjectOutputStream (baos) ;
            oos.writeObject (cmd) ;
            oos.flush () ;
            System.out.println("Paquet envoyé");
            System.out.println("MulticastSender.updateObject()");
       } catch (IOException e) {
           e.printStackTrace();
       }
         DatagramPacket paquet = new DatagramPacket (baos.toByteArray (), baos.toByteArray ().length, diffusionAddress, updateDiffusionPort) ;
         try {
             diffusionSocket.send (paquet);
         } catch (IOException e) {
             e.printStackTrace () ;
         }
    }
}
