package fr.istic.evc.device;

import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Node;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOr;

import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;

import fr.istic.evc.graphic2D.CameraManager;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.presentation.interfaces.IPObject;


/**
 * Implement the mouse device in order to pilot the world 3D from a mouse
 * @author Kevin RAMAGE Thomas CARO
 *
 */
public class Mouse extends Behavior implements IDevice {
	
	
	// ---------------------------------------------------------
	// 						Attributes
	// ---------------------------------------------------------	
	protected List<ICObject> objectsSelected;
	protected BranchGroup scene;
	protected WakeupOr wEvents ;
	protected int buttonsInUse ;
	protected boolean button1Pressed, button2Pressed, button3Pressed ;
	protected int x1, y1, x2, y2 ;
	protected double speed;
	protected CameraManager cameraManager;
	
	
	
	// ---------------------------------------------------------
	// 						Methods
	// ---------------------------------------------------------
	
	/**
	 * The constructor of the mouse
	 * @param speed the speed of the mouse. Value must be between 0 and 10
	 */
	public Mouse(int speed) {
		if ( speed < 0 ) speed = 0;
		if ( speed > 10 ) speed = 10;
		this.objectsSelected = new ArrayList<ICObject>();
		this.speed = 40.0 + (speed - 5) * 5;
	}
	public Mouse() {
		this(5);
	}
	
	/**
	 * Device need scene 3D in order to do picking in the world 3D
	 */
	@Override
	public void setBranchGroup(BranchGroup scene) {
		this.scene = scene;
	}

	/**
	 * Initialize the behaviour
	 */
	@Override
	public void initialize() {
		WakeupOnAWTEvent wAWTEvent = new WakeupOnAWTEvent(AWTEvent.MOUSE_EVENT_MASK) ;
		WakeupOnAWTEvent wAWTEvent2 = new WakeupOnAWTEvent (AWTEvent.MOUSE_MOTION_EVENT_MASK) ;
		WakeupOnAWTEvent wAWTEvent3 = new WakeupOnAWTEvent (AWTEvent.MOUSE_WHEEL_EVENT_MASK) ;
		WakeupCriterion [] conditions = { wAWTEvent, wAWTEvent2, wAWTEvent3} ;
		wEvents = new WakeupOr (conditions) ;
		wakeupOn (wEvents) ;
		buttonsInUse = 0 ;
		button1Pressed = false ;
		button2Pressed = false ;
		button3Pressed = false ;
	}

	/**
	 * Process a event
	 */
	@Override
	public void processStimulus(@SuppressWarnings("rawtypes") Enumeration criteria) {
		while (criteria.hasMoreElements ()) {
			WakeupOnAWTEvent w = (WakeupOnAWTEvent)criteria.nextElement () ;
			AWTEvent events[] = w.getAWTEvent () ;
			for (int i = 0 ; i < events.length ; i++) {
				
				// MOUSE PRESSED
				if (events [i].getID () == MouseEvent.MOUSE_PRESSED) {
					
					// Detect button pressed
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON1) {
						button1Pressed = true ;
					}
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON2) {
						button2Pressed = true ;
					}
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON3) {
						button3Pressed = true ;
					}
					
					// Get the x, y
					x1 = ((MouseEvent)events [i]).getX () ;
					y1 = ((MouseEvent)events [i]).getY () ;
					
					// First click
					if (buttonsInUse == 0) {
						
						// Pick
						PickCanvas pickShape = new PickCanvas ((Canvas3D)events [i].getSource (), scene) ;
						pickShape.setShapeLocation ((MouseEvent)events [i]) ;
						 
						// Pick
						PickResult [] sgPath = pickShape.pickAllSorted () ;
						if (sgPath != null) {
							try {
								// Get 3D object
								IPObject pObject = pickObject(sgPath[0]);
								
								// Get controller object
								ICObject cObject = pObject.getController();
								
								// Is pickable
								if (cObject.isPickable()) {
								
									// Select
									if ( button1Pressed) {
										if (!objectsSelected.contains(cObject)) {
											objectsSelected.add(cObject);
											cObject.select();
										}
									// Unselect
									} else if (button3Pressed) {
										if (objectsSelected.contains(cObject)) {
											objectsSelected.remove(cObject);
											cObject.unselect();
										}
									}
								}
								
							} catch (Exception e) {
								System.out.println (e) ;
							}
						}
					}
					buttonsInUse++ ;
					
				// MOUSE RELEASE
				} else if (events [i].getID () == MouseEvent.MOUSE_RELEASED) {
					buttonsInUse-- ;
					
					// Detect button release
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON1) {
						button1Pressed = false ;
					}
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON2) {
						button2Pressed = false ;
					}
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON3) {
						button3Pressed = false ;
					}
					
				// MOUSE DRAGGED
				} else if (events [i].getID () == MouseEvent.MOUSE_DRAGGED) {
					
					// Get values
					double dx = 0, dy = 0, dz = 0 ;
					double dh = 0, dp = 0, dr = 0 ;
					
					// Get X Y
					x2 = ((MouseEvent)events [i]).getX () ;
					y2 = ((MouseEvent)events [i]).getY () ;
					
					// Calculate delta
					dx = (x2 - x1) / speed;
					dy = (y1 - y2) / speed;
					
					// Object selected
					if ( objectsSelected.size() > 0) {
						
						for ( ICObject obj : objectsSelected)  {
							
							// Translate the object
							cameraManager.translateObject(obj, dx, dy, dz);
						}
						
					// None object selected
					} else {
						
						// Calculate euler angle
						dh = dy / 10;
						dp = dx / 10;
						dr = 0;
						
						// Rotate the world
						cameraManager.rotateWorld(dh, dp, dr);
					}
					
					// Init delta
					x1 = x2;
					y1 = y2;
					
				} else if (events[i].getID() == MouseEvent.MOUSE_WHEEL) {
					
					MouseWheelEvent event = (MouseWheelEvent) events[i];
					
					// Object selected
					if ( objectsSelected.size() > 0) {
						
						for ( ICObject obj : objectsSelected)  {
							
							// Translate the object
							cameraManager.translateObject(obj, 0, 0, event.getWheelRotation());
						}
						
					// None object selected
					} else {
						
						// Translate world
						cameraManager.translateWorld(0, 0, event.getWheelRotation());
					}
				}
			}
		}
		wakeupOn (wEvents) ;
	}
	@Override
	public void setCameraManager(CameraManager cameraManager) {
		this.cameraManager = cameraManager;
	}
	
	protected IPObject pickObject(PickResult pickResult) {
		Node node = pickResult.getNode(PickResult.SHAPE3D);
		while ( !(node instanceof IPObject)) {
			node = node.getParent();
		}
		return (IPObject)node;
	}
}
