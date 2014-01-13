/**
 * Basic graphic component, base of each graphic component
 * This class specify method that sub class must be implemented and provide usefull method already written
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 */

package fr.istic.evc.graphic2D.base;

import fr.istic.evc.graphic2D.factory.Graphic2DFactory;
import fr.istic.evc.utils.ErrorManager;
import fr.istic.evc.utils.size.Size;

public abstract class Component implements Item{

	// ------------------------------------------------------------------------------------------------------------------------------------
	//																ATTRIBUTES
	// ------------------------------------------------------------------------------------------------------------------------------------	
	
	// Unique ID of the component
	protected String id;
	
	// Parent of the component (Window or component)
	protected Item parent;
	
	// Position and size of the component
	protected int realWidth, realHeight;
	protected Size width, height;
	
	// Factory which create the class
	protected Graphic2DFactory factory;
	
	
	
	

	// ------------------------------------------------------------------------------------------------------------------------------------
	//																INTERFACE
	// ------------------------------------------------------------------------------------------------------------------------------------	
		
	// Update graphically the size of the component 
	public void updateSize() {}
	
	// Link to window (Graphic way to link component to window)
	public void linkToWindow(Window window) {}
	
	// Load command (get window, instanciate command, associate command to action)
	public void loadCommand(ErrorManager errorManager) {}
	
	
	
	

	// ------------------------------------------------------------------------------------------------------------------------------------
	//																METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	
	// Get window owner of this component
	public Item getWindow() {
		Item c = getParent();
		if ( c != null ) {
			return c.getWindow();
		} else {
			return this;
		}
	}
	
	
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	//																ACCESSORS / MUTATORS
	// ------------------------------------------------------------------------------------------------------------------------------------	
		
	
	// Id
	public String getId() { return id; }
	public void setId(String id) { this.id = id; } 
	
	// Parent
	public Item getParent() { return parent; }
	public void setParent(Item parent) { this.parent = parent; }
	
	// Size
    public Size getWidth() { return width; }
	public Size getHeight() { return height; }
	public void setSize(Size width, Size height) { this.width = width; this.height = height; }
	
	// Real position
	public void setRealPositionX(int positionX) {}
	public void setRealPositionY(int positionY) {}
	
	// Real size
	public void setRealWidth (int width) { realWidth = width; }
	public void setRealHeight (int height) { realHeight = height; }
	
	// Factory
	public Graphic2DFactory getFactory() { return factory; }
	public void setFactory(Graphic2DFactory factory) { this.factory = factory; }

}
