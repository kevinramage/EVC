package fr.istic.evc.device;

import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOr;
import javax.vecmath.Color3f;

import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;

import fr.istic.evc.object3D.base.presentation.PObject;
import fr.istic.evc.object3D.base.presentation.interfaces.IPObject;



public class Mouse extends Behavior implements IDevice {
	
	private BranchGroup branchGroup;
	private WakeupOr wEvents ;
	private int buttonsInUse ;
	private boolean button1Pressed, button2Pressed, button3Pressed ;
	private TransformGroup objectInInteraction ;
	int x1, y1, x2, y2 ;
	
	
	public Mouse() {
		
	}
	
	@Override
	public void setBranchGroup(BranchGroup branchGroup) {
		this.branchGroup = branchGroup;
	}

	@Override
	public void initialize() {
		WakeupOnAWTEvent wAWTEvent = new WakeupOnAWTEvent(AWTEvent.MOUSE_EVENT_MASK) ;
		WakeupOnAWTEvent wAWTEvent2 = new WakeupOnAWTEvent (AWTEvent.MOUSE_MOTION_EVENT_MASK) ;
		WakeupCriterion [] conditions = { wAWTEvent, wAWTEvent2 } ;
		wEvents = new WakeupOr (conditions) ;
		wakeupOn (wEvents) ;
		buttonsInUse = 0 ;
		button1Pressed = false ;
		button2Pressed = false ;
		button3Pressed = false ;
	}

	@Override
	public void processStimulus(@SuppressWarnings("rawtypes") Enumeration criteria) {
		while (criteria.hasMoreElements ()) {
			WakeupOnAWTEvent w = (WakeupOnAWTEvent)criteria.nextElement () ;
			AWTEvent events[] = w.getAWTEvent () ;
			for (int i = 0 ; i < events.length ; i++) {
				if (events [i].getID () == MouseEvent.MOUSE_PRESSED) {
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON1) {
						button1Pressed = true ;
					}
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON2) {
						button2Pressed = true ;
					}
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON3) {
						button3Pressed = true ;
					}
					if (buttonsInUse == 0) {
						PickCanvas pickShape = new PickCanvas ((Canvas3D)events [i].getSource (), branchGroup) ;
						pickShape.setShapeLocation ((MouseEvent)events [i]) ;
						x1 = ((MouseEvent)events [i]).getX () ;
						y1 = ((MouseEvent)events [i]).getY () ;
						PickResult [] sgPath = pickShape.pickAllSorted () ;
						//System.out.println("shPath: " + sgPath.length);
						if (sgPath != null) {
							//System.out.println (sgPath [1]) ;
							try {
								Shape3D shape  = (Shape3D)sgPath [1].getNode (PickResult.SHAPE3D) ;
								IPObject object = (PObject)shape.getParent().getParent().getParent();
								object.setDiffuseColor(new Color3f(1, 1, 0));
								object.setAmbientColor(new Color3f(1, 1, 0));
								
							} catch (Exception e) {
								System.out.println (e) ;
							}
						}
					}
					buttonsInUse++ ;
				} else if (events [i].getID () == MouseEvent.MOUSE_RELEASED) {
					buttonsInUse-- ;
					if (buttonsInUse == 0) {
						objectInInteraction = null ;
					}
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON1) {
						button1Pressed = false ;
					}
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON2) {
						button2Pressed = false ;
					}
					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON3) {
						button3Pressed = false ;
					}
				} else if (events [i].getID () == MouseEvent.MOUSE_DRAGGED) {
					if (objectInInteraction != null) {
						double dx = 0, dy = 0, dz = 0 ;
						double dh = 0, dp = 0, dr = 0 ;
						x2 = ((MouseEvent)events [i]).getX () ;
						y2 = ((MouseEvent)events [i]).getY () ;
						if (button1Pressed) { // rotation
							dh = Math.PI * (x2 - x1) / 40.0 ;
							dp = Math.PI * (y1 - y2) / 40.0 ;
							dr = (dh - dp) / 2.0 ;
						}
						if (button2Pressed) { // zoom
							dz = (x1 - x2 + y2 - y1) / 40.0 ;
						}
						if (button3Pressed) { // translation dans le plan de l'�cran
							dx = (x2 - x1) / 40.0 ;
							dy = (y1 - y2) / 40.0 ;
						}
						//translate (dx, dy, dz) ;
						//rotate (dh, dp, dr) ;
						x1 = x2 ;
						y1 = y2 ;
					}
				}
			}
		}
		wakeupOn (wEvents) ;
	}
}
