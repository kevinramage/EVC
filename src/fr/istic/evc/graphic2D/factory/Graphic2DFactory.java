/**
 * Specification of a factory in order to create graphic 2D in abstract way
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 */

package fr.istic.evc.graphic2D.factory;

import fr.istic.evc.graphic2D.base.CameraPanel;
import fr.istic.evc.graphic2D.base.Container;
import fr.istic.evc.graphic2D.base.MainView;
import fr.istic.evc.graphic2D.base.Menu;
import fr.istic.evc.graphic2D.base.MenuItem;
import fr.istic.evc.graphic2D.base.Window;

public interface Graphic2DFactory {
	
	Window createWindow();
	Container createContainer();
	Menu createMenu();
	MenuItem createMenuItem();
	MainView createMainView();
	CameraPanel createCameraPanel();
}
