package network;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class WorldListener extends Thread implements Runnable {
	
	/* ---------- Attributes ---------- */
	private transient MulticastSocket socket;
	private InetAddress diffusionAddress;
	private ICallback callback;
	
	
	/* ---------- Constructor ---------- */
	public WorldListener(String diffusionAddress, int port, ICallback call) {
    	socket = null ;
    	callback = call;
        try {
        	this.diffusionAddress = InetAddress.getByName (diffusionAddress) ;
            socket = new MulticastSocket (port) ;
            socket.joinGroup (this.diffusionAddress) ;
            socket.setLoopbackMode (false) ;
        } catch (Exception e) {
            e.printStackTrace () ;
        }
	}
	
	/* ---------- Methods ---------- */
    public void receiveMessage() {
        try {
            byte [] message = new byte [1024] ;
            
            System.out.println("WorldListener.receiveMessage()");
            
            // Receive a client request
            DatagramPacket paquet = new DatagramPacket (message, message.length) ;
            socket.receive (paquet) ;
            ByteArrayInputStream bais = new ByteArrayInputStream (paquet.getData ()) ;
            ObjectInputStream ois = new ObjectInputStream (bais);
            
            // Send the world name
            String worldName = ois.readObject().toString();
            callback.call(worldName);
            
            System.out.println("WorldListener.receiveMessage()2");
            
            
        } catch (Exception e) {
            e.printStackTrace () ;
        }
    }
	
    @Override
    public void run () {
        while (true) {
        	receiveMessage();
        }
    }
}
