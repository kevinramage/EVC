/**
 * A component which represent the window of the application. It contains all the components
 * It have many responsabilities like the position of each component.
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 */

package fr.istic.evc.graphic2D.base;

import java.util.ArrayList;
import java.util.List;

import fr.istic.evc.graphic2D.factory.Graphic2DFactory;
import fr.istic.evc.utils.Alignment;
import fr.istic.evc.utils.Alignment.ALIGNMENT;
import fr.istic.evc.utils.position.Position;

public abstract class Window implements Item {

	// ------------------------------------------------------------------------------------------------------------------------------------
	//																ATTRIBUTES
	// ------------------------------------------------------------------------------------------------------------------------------------	
	
	// Position and size
	protected Position x, y;
	protected int width, height;
	
	// Factory
	protected Graphic2DFactory factory;
	
	// Component and alignment of the components
	protected List<Component> components;
	protected Alignment alignement; 
	
	
	
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	//																METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	
	// Constructor
	public Window() {
		components = new ArrayList<Component>();
	}
	

	// Add a component to the list
	public void addComponent(Component c) {
		c.setParent(this);
		components.add(c);
	}
	
	// Calculate the allocable width
	protected int calculateWidth() {
		
		// Window width
		int allocableWidth = getWindowWidth();
		
		// Components
		for (Component c : components) {
			allocableWidth = c.getWidth().reduceAllocableSize(allocableWidth);
		}
		
		return allocableWidth;
	}
	
	// Calculate the allocable height
	protected int calculateHeight() {
		
		// Window height
		int allocableHeight = getWindowHeight();
		
		// Components
		for (Component c : components) {
			allocableHeight = c.getHeight().reduceAllocableSize(allocableHeight);
		}
		
		return allocableHeight;
	}

	// Assign width to components
	protected void assignComponentsWidth(int totalSize) {
		
		for ( Component c : components) {
			
			// Get exact width
			int width = c.getWidth().getExactSize(totalSize);
			
			// Assign width
			c.setRealWidth(width);
		}
	}
	
	// Assign height to components
	protected void assignComponentsHeight(int totalSize) {
		
		for ( Component c : components) {
			
			// Get exact height
			int height = c.getHeight().getExactSize(totalSize);

			// Assign height
			c.setRealHeight(height);
		}
	}
	
	// Assign x position to components
	protected void assignComponentsPositionX() {
		int positionY = 0;
		
		for ( Component c : components) {
			c.setRealPositionY(positionY);
			if ( alignement.getAlignment() == ALIGNMENT.HORIZONTAL) {
				positionY += c.getHeight().getExactSize();
			}
		}
	}
	
	// Assign y position to components
	protected void assignComponentsPositionY() {
		int positionY = 0;
		
		for ( Component c : components) {
			c.setRealPositionY(positionY);
			if ( alignement.getAlignment() == ALIGNMENT.VERTICAL) {
				positionY += c.getHeight().getExactSize();
			}
		}
	}


	public void updateSize() {
		
		// Width
		int allocableWidth = calculateWidth();
		assignComponentsWidth(allocableWidth);
		
		// Height
		int allocableHeight = calculateHeight();
		assignComponentsHeight(allocableHeight);
		
		// Position X
		assignComponentsPositionX();
		
		// Position Y
		assignComponentsPositionY();
		
		for ( Component c : components) {
			c.updateSize();
		}
	}
	
	
	
	
	
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	//																INTERFACE
	// ------------------------------------------------------------------------------------------------------------------------------------	
		
	
	// Show a window (define window as visible window)
	public abstract void show();
	
	// Get the real graphical object
	public abstract Object getComponent();
	
	// Command to close the window
	public abstract void exit();
	
	// Calculate the exact window width
	protected abstract int getWindowWidth();
	
	// Calculate the exact window height
	protected  abstract int getWindowHeight();
	
	
	
	
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	//																ACCESSORS / MUTATORS
	// ------------------------------------------------------------------------------------------------------------------------------------	
		
	// Position
	public Position getPositionX() { return x; }
	public Position getPositionY() { return y; }
	public void setPosition(Position posX, Position posY) { x = posX; y = posY; }
	
	// Size
	public int getWidth(){ return width; }
	public int getHeight(){ return height; }
	public void setSize(int width, int height) { this.width = width; this.height = height; }
	
	// Factory
	public Graphic2DFactory getFactory() { return factory; }
	public void setFactory(Graphic2DFactory factory) { this.factory = factory; }
	
	// Parent
	public Item getParent() { return null; }
	public void setParent(Item p) {}
	
	// Window
	public Item getWindow() { return this; }
	
	// Alignment
	public Alignment getAlignment() { return alignement; }
	public void setAlignment(Alignment alignement) { this.alignement = alignement; }
}
