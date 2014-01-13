//package fr.istic.evc.test;
//
//import java.awt.AWTEvent;
//import java.awt.GraphicsConfiguration;
//import java.awt.event.KeyEvent;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseWheelEvent;
//import java.awt.event.MouseWheelListener;
//import java.util.Enumeration;
//
//import javax.media.j3d.Behavior;
//import javax.media.j3d.BranchGroup;
//import javax.media.j3d.J3DGraphics2D;
//import javax.media.j3d.TransformGroup;
//import javax.media.j3d.WakeupCriterion;
//import javax.media.j3d.WakeupOnAWTEvent;
//import javax.media.j3d.WakeupOr;
//
//import com.sun.j3d.utils.picking.PickCanvas;
//import com.sun.j3d.utils.picking.PickResult;
//
//public class Canvas3D extends javax.media.j3d.Canvas3D {
//
//	private static final long serialVersionUID = 1L;
//	
//	
//	public Canvas3D(GraphicsConfiguration arg0) {
//		super(arg0);
//	}
//	@Override
//	public void postRender() {
//		super.postRender();
//		
//		J3DGraphics2D graphics = getGraphics2D();
//		graphics.flush(true);
//	}
//}
//class MWL implements MouseWheelListener {
//
//	private CameraManager cameraActive;
//	
//	public MWL(CameraManager cameraActive) {
//		this.cameraActive = cameraActive;
//	}
//	
//	@Override
//	public void mouseWheelMoved(MouseWheelEvent e) {
//		cameraActive.translateZ(e.getWheelRotation());
//	}
//}
//class KeyBehaviour extends Behavior {
//
//	private CameraManager cameraManager;
//	
//	public KeyBehaviour(CameraManager cameraManager) {
//		this.cameraManager = cameraManager;
//	}
//	
//	@Override
//	public void initialize() {
//		wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
//	}
//
//	@Override
//	public void processStimulus(@SuppressWarnings("rawtypes") Enumeration criteria) {
//		while ( criteria.hasMoreElements() ) {
//			WakeupOnAWTEvent w = (WakeupOnAWTEvent)criteria.nextElement();
//			AWTEvent [] events = w.getAWTEvent();
//			for ( AWTEvent event : events) {
//				if ( event.getID() == KeyEvent.KEY_PRESSED) {
//					int keyCode = ((KeyEvent)event).getKeyCode();
//					switch ( keyCode ) {
//						case KeyEvent.VK_UP:
//							cameraManager.translateZ(-1);
//						break;
//						
//						case KeyEvent.VK_DOWN:
//							cameraManager.translateZ(1);
//						break;
//						
//						case KeyEvent.VK_LEFT:
//							cameraManager.translateX(-1);
//						break;
//						
//						case KeyEvent.VK_RIGHT:
//							cameraManager.translateX(1);
//						break;
//					}
//				}
//			}
//		}
//		
//		wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
//	}
//	
//}
//
//class MouseInteractor extends Behavior{
//	
//	private BranchGroup branchGroup;
//	private WakeupOr wEvents ;
//	private int buttonsInUse ;
//	private boolean button1Pressed, button2Pressed, button3Pressed ;
//	private TransformGroup objectInInteraction ;
//	int x1, y1, x2, y2 ;
//	
//	
//	public MouseInteractor(BranchGroup branchGroup) {
//		this.branchGroup = branchGroup;
//	}
//
//	@Override
//	public void initialize() {
//		WakeupOnAWTEvent wAWTEvent = new WakeupOnAWTEvent(AWTEvent.MOUSE_EVENT_MASK) ;
//		WakeupOnAWTEvent wAWTEvent2 = new WakeupOnAWTEvent (AWTEvent.MOUSE_MOTION_EVENT_MASK) ;
//		WakeupCriterion [] conditions = { wAWTEvent, wAWTEvent2 } ;
//		wEvents = new WakeupOr (conditions) ;
//		wakeupOn (wEvents) ;
//		buttonsInUse = 0 ;
//		button1Pressed = false ;
//		button2Pressed = false ;
//		button3Pressed = false ;
//	}
//
//	@Override
//	public void processStimulus(@SuppressWarnings("rawtypes") Enumeration criteria) {
//		while (criteria.hasMoreElements ()) {
//			WakeupOnAWTEvent w = (WakeupOnAWTEvent)criteria.nextElement () ;
//			AWTEvent events[] = w.getAWTEvent () ;
//			for (int i = 0 ; i < events.length ; i++) {
//				if (events [i].getID () == MouseEvent.MOUSE_PRESSED) {
//					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON1) {
//						button1Pressed = true ;
//					}
//					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON2) {
//						button2Pressed = true ;
//					}
//					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON3) {
//						button3Pressed = true ;
//					}
//					if (buttonsInUse == 0) {
//						PickCanvas pickShape = new PickCanvas ((Canvas3D)events [i].getSource (), branchGroup) ;
//						pickShape.setShapeLocation ((MouseEvent)events [i]) ;
//						x1 = ((MouseEvent)events [i]).getX () ;
//						y1 = ((MouseEvent)events [i]).getY () ;
//						PickResult [] sgPath = pickShape.pickAllSorted () ;
//						if (sgPath != null) {
//							//System.out.println (sgPath [0]) ;
//							try {
//								System.out.println (sgPath [0].getObject ()) ;
//								objectInInteraction = (TransformGroup)sgPath [0]
//								      .getNode (PickResult.TRANSFORM_GROUP) ;
//							} catch (Exception e) {
//								System.out.println (e) ;
//							}
//						}
//					}
//					buttonsInUse++ ;
//				} else if (events [i].getID () == MouseEvent.MOUSE_RELEASED) {
//					buttonsInUse-- ;
//					if (buttonsInUse == 0) {
//						objectInInteraction = null ;
//					}
//					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON1) {
//						button1Pressed = false ;
//					}
//					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON2) {
//						button2Pressed = false ;
//					}
//					if (((MouseEvent)events [i]).getButton () == MouseEvent.BUTTON3) {
//						button3Pressed = false ;
//					}
//				} else if (events [i].getID () == MouseEvent.MOUSE_DRAGGED) {
//					if (objectInInteraction != null) {
//						double dx = 0, dy = 0, dz = 0 ;
//						double dh = 0, dp = 0, dr = 0 ;
//						x2 = ((MouseEvent)events [i]).getX () ;
//						y2 = ((MouseEvent)events [i]).getY () ;
//						if (button1Pressed) { // rotation
//							dh = Math.PI * (x2 - x1) / 40.0 ;
//							dp = Math.PI * (y1 - y2) / 40.0 ;
//							dr = (dh - dp) / 2.0 ;
//						}
//						if (button2Pressed) { // zoom
//							dz = (x1 - x2 + y2 - y1) / 40.0 ;
//						}
//						if (button3Pressed) { // translation dans le plan de l'écran
//							dx = (x2 - x1) / 40.0 ;
//							dy = (y1 - y2) / 40.0 ;
//						}
//						//translate (dx, dy, dz) ;
//						//rotate (dh, dp, dr) ;
//						x1 = x2 ;
//						y1 = y2 ;
//					}
//				}
//			}
//		}
//		wakeupOn (wEvents) ;
//	}
//}