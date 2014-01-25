package network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.rmi.RemoteException;

import command.I_Command;

public class MulticastSender implements Serializable {
	


	/* ---------- Attributes ---------- */

    private static final long serialVersionUID = 1L;
    private int createDiffusionPort, updateDiffusionPort , deleteDiffusionPort;
    private String groupName ;

    private InetAddress diffusionAddress ;
    private transient MulticastSocket diffusionSocket ;
	


	/* ---------- Constructors ---------- */

    public MulticastSender (final String ng, final int createDiffusionPort, final int updateDiffusionPort, final int deleteDiffusionPort) throws RemoteException {
        this.createDiffusionPort = createDiffusionPort;
        this.updateDiffusionPort = updateDiffusionPort;
        this.deleteDiffusionPort = deleteDiffusionPort;
        
        groupName = ng ;
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
    }
	


	/* ---------- Methods ---------- */

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
    public void createObject (I_Command cmd) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream () ;
        ObjectOutputStream oos ;
       try {
            oos = new ObjectOutputStream (baos) ;
            oos.writeObject (cmd) ;
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
    public void updateObject(I_Command cmd) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream () ;
        ObjectOutputStream oos ;
       try {
            oos = new ObjectOutputStream (baos) ;
            oos.writeObject (cmd) ;
            oos.flush () ;
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

	public void deleteObjects(I_Command cmdDelete) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream () ;
        ObjectOutputStream oos ;
       try {
            oos = new ObjectOutputStream (baos) ;
            oos.writeObject (cmdDelete) ;
            oos.flush () ;
       } catch (IOException e) {
           e.printStackTrace();
       }
         DatagramPacket paquet = new DatagramPacket (baos.toByteArray (), baos.toByteArray ().length,
                 diffusionAddress,
                 deleteDiffusionPort) ;
         try {
             diffusionSocket.send (paquet);
         } catch (IOException e) {
             e.printStackTrace () ;
         }
		
	}
}
