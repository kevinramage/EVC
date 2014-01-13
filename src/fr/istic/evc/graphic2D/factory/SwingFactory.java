/**
 * A swing implementation of a graphic 2D factory
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
import fr.istic.evc.graphic2D.swing.SwingCameraPanel;
import fr.istic.evc.graphic2D.swing.SwingContainer;
import fr.istic.evc.graphic2D.swing.SwingMainView;
import fr.istic.evc.graphic2D.swing.SwingMenu;
import fr.istic.evc.graphic2D.swing.SwingMenuItem;
import fr.istic.evc.graphic2D.swing.SwingWindow;

public class SwingFactory implements Graphic2DFactory {

	@Override
	public Window createWindow() {
		SwingWindow window = new SwingWindow();
		window.setFactory(this);
		return new SwingWindow();
	}

	@Override
	public Menu createMenu() {
		SwingMenu menu = new SwingMenu();
		menu.setFactory(this);
		return menu;
	}

	@Override
	public MenuItem createMenuItem() {
		SwingMenuItem menuItem = new SwingMenuItem();
		menuItem.setFactory(this);
		return menuItem;
	}

	@Override
	public Container createContainer() {
		SwingContainer container = new SwingContainer();
		container.setFactory(this);
		return container;
	}

	@Override
	public MainView createMainView() {
		MainView mainView = new SwingMainView();
		mainView.setFactory(this);
		return mainView;
	}

	@Override
	public CameraPanel createCameraPanel() {
		CameraPanel cameraPanel = new SwingCameraPanel();
		cameraPanel.setFactory(this);
		return cameraPanel;
	}
}
