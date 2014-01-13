package fr.istic.evc.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class View implements ActionListener {

	public enum VIEW_TYPE { FRONT, BACK, TOP, BOTTOM, LEFT, RIGHT };
	private VIEW_TYPE type;
	private static Map<VIEW_TYPE, Transform3D> transforms;
	private TransformGroup transformGroup;
	
	public View (TransformGroup transformGroup, VIEW_TYPE type){
		this.transformGroup = transformGroup;
		this.type = type;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Transform3D transform3D = transforms.get(type);
		transformGroup.setTransform(transform3D);
	}

	static {
		transforms = new HashMap<VIEW_TYPE, Transform3D>();
		transforms.put(VIEW_TYPE.FRONT, getFrontView());
		transforms.put(VIEW_TYPE.BACK, getBackView());
		transforms.put(VIEW_TYPE.LEFT, getLeftView());
		transforms.put(VIEW_TYPE.RIGHT, getRightView());
		transforms.put(VIEW_TYPE.TOP, getTopView());
		transforms.put(VIEW_TYPE.BOTTOM, getBottomView());
	}
	
	private static Transform3D getFrontView() {
		Transform3D transform3D = new Transform3D();
		Vector3d translataion = new Vector3d(0, 0, 30);
		transform3D.setEuler(new Vector3d(0,0,0));
		transform3D.setTranslation(translataion);
		return transform3D;
	}
	private static Transform3D getBackView() {
		Transform3D transform3D = new Transform3D();
		Vector3d translataion = new Vector3d(0, 0, -30);
		transform3D.setEuler(new Vector3d(0,Math.PI,0));
		transform3D.setTranslation(translataion);
		return transform3D;
	}
	private static Transform3D getLeftView() {
		Transform3D transform3D = new Transform3D();
		Vector3d translataion = new Vector3d(-30, 0, 0);
		transform3D.setEuler(new Vector3d(0,-Math.PI / 2,0));
		transform3D.setTranslation(translataion);
		return transform3D;
	}	
	private static Transform3D getRightView() {
		Transform3D transform3D = new Transform3D();
		Vector3d translataion = new Vector3d(30, 0, 0);
		transform3D.setEuler(new Vector3d(0,Math.PI / 2,0));
		transform3D.setTranslation(translataion);
		return transform3D;
	}		
	private static Transform3D getTopView() {
		Transform3D transform3D = new Transform3D();
		Vector3d translataion = new Vector3d(0, 10, 0);
		transform3D.setEuler(new Vector3d(0, 0,-Math.PI / 2));
		transform3D.setTranslation(translataion);
		return transform3D;
	}	
	private static Transform3D getBottomView() {
		Transform3D transform3D = new Transform3D();
		Vector3d translataion = new Vector3d(0, -10, 0);
		transform3D.setEuler(new Vector3d(0, 0,Math.PI / 2));
		transform3D.setTranslation(translataion);
		return transform3D;
	}		
}
