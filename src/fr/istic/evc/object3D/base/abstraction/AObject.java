/**
 * Class which contains the abstraction of a 3D object
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 * 
 */

package fr.istic.evc.object3D.base.abstraction;

import javax.vecmath.Color3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;

public class AObject implements I_AObject{
	
	/* ---------- Attributes---------- */
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected Vector3d position;
	protected Quat4f orientation;
	protected String geometry;
	protected Vector3d scale;
	protected boolean isPickable;
	protected Color3f ambientColor, diffuseColor, selectColor, backupColor;
	
	
	
	/* ---------- Constructors---------- */
	
	public AObject() {
		
		diffuseColor = new Color3f();
		this.selectColor = new Color3f(1.0f, 1.0f, 0.0f);
	}


	/* ---------- Setters ---------- */
	
	/**
	 * Set the object id
	 */
	public void setId(String id) {
		this.id = id;
		position = new Vector3d();
	}
	
	@Override
	public void setPosition(Vector3d position) {
		this.position = position;
	}

	@Override
	public void setOrientation(Quat4f orientation) {
		this.orientation = orientation;
	}

	@Override
	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}

	@Override
	public void setScale(Vector3d scale) {
		
	}

	@Override
	public void IsPickable(boolean b) {
		isPickable = b;
	}

	@Override
	public void setAmbientColor(Color3f ambientColor) {
		this.ambientColor = ambientColor;
	}

	@Override
	public void setDiffuseColor(Color3f diffuseColor) {
		this.diffuseColor = diffuseColor;
	}

	@Override
	public void setSelectColor(Color3f selectColor) {
		this.selectColor = selectColor;
	}

	@Override
	public void setBackupColor(Color3f backupColor) {
		this.backupColor = backupColor;
	}
	


	/* ---------- Getters ---------- */
	
	/**
	 * Get the geometry of the object
	 * @return the name of the primitve geometry or the url of the object
	 */
	public String getGeometry() {
		return geometry;
	}


	@Override
	public String getId() {
		return id;
	}


	@Override
	public Color3f getAmbientColor() {
		return ambientColor;
	}


	@Override
	public Color3f getDiffuseColor() {
		return diffuseColor;
	}


	@Override
	public Vector3d getPosition() {
		return position;
	}


	@Override
	public Color3f getSelectColor() {
		return selectColor;
	}
	
	@Override
	public Color3f getBackupColor() {
		return backupColor;
	}
	
	
	
	
}