/**
 * A common specification between a component and a window 
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 */

package fr.istic.evc.graphic2D.base;

import fr.istic.evc.graphic2D.factory.Graphic2DFactory;

public interface Item {
	
	// Factory
	void setFactory(Graphic2DFactory factory);
	Graphic2DFactory getFactory();
	
	// Parent
	void setParent(Item parent);
	Item getParent();
	
	// Get graphical object of a component
	Object getComponent();
	
	// Get window owner of the component or the window itself
	Item getWindow();
}
