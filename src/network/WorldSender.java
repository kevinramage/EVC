package network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class WorldSender implements Serializable {
	
	
	/* ---------- Attributes ---------- */
	private static final long serialVersionUID = 1L;
	private MulticastSocket socket;
	private InetAddress diffusionAddress;
	private int port;
	

	/* ---------- Constructor ---------- */
	public WorldSender (String diffusionAddress, int port) {
		this.port = port;
        diffusionAddress = null ;
        try {
            this.diffusionAddress = InetAddress.getByName (diffusionAddress) ;
            socket = new MulticastSocket (port) ;
            socket.setTimeToLive (64) ;
            socket.setLoopbackMode (false) ;
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
        DatagramPacket paquet = new DatagramPacket (baos.toByteArray (), baos.toByteArray ().length, diffusionAddress, port) ;
        try {
            socket.send (paquet);
        } catch (IOException e) {
            e.printStackTrace () ;
        }
    }
}
