/**
 * Class which contains the presentation of a 3D object
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 * 
 */

package object3D.presentation;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Enumeration;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

import object3D.controller.interfaces.ICObject;
import object3D.presentation.interfaces.IPObject;

import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

public class PObject extends TransformGroup implements IPObject {

	// ---------------------------------------------------------
	// Attributes
	// ---------------------------------------------------------

	protected static String[] geometries = new String[] { "cube", "sphere", "cone" };
	protected ICObject controller;
	protected BranchGroup branchGroup;
	protected Primitive primitive;

	// ---------------------------------------------------------
	// Methods
	// ---------------------------------------------------------

	public PObject(ICObject controller) {
		this.controller = controller;
		this.branchGroup = new BranchGroup();

		setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		setCapability(TransformGroup.ENABLE_PICK_REPORTING);
	}

	@Override
	public String toString() {
		return controller.getId();
	}

	// ---------------------------------------------------------
	// Setters
	// ---------------------------------------------------------
	@Override
	public void setGeometry(String geometry) {
		if (geometry.endsWith(".wrl")) {

			VrmlLoader loader = new VrmlLoader () ;
			 try { 
				 Scene scene = loader.load ("resources/object/" + geometry) ;
				 addChild(scene.getSceneGroup());
			 
			 } catch (FileNotFoundException e) { e.printStackTrace () ; }

			
		} else if (Arrays.asList(geometries).contains(geometry)) {
			primitive = getPrimitive(geometry);
			primitive.setCapability(javax.media.j3d.Geometry.ALLOW_INTERSECT);
			Appearance appearance = primitive.getAppearance();
			appearance.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
			Material material = appearance.getMaterial();
			material.setCapability(Material.ALLOW_COMPONENT_WRITE);
			material.setShininess(0);
			branchGroup.removeAllChildren();
			branchGroup.addChild(primitive);
			removeAllChildren();
			addChild(branchGroup);
			setCapability(TransformGroup.ENABLE_PICK_REPORTING);
		} else {
			System.err.println("PObject - Impossible to load geometry");
		}
	}

	@Override
	public void setAmbientColor(Color3f ambientColor) {
		if ( primitive != null) {
			Material material = primitive.getAppearance().getMaterial();
			material.setAmbientColor(ambientColor);
			primitive.getAppearance().setMaterial(material);
		}
	}

	@Override
	public void setDiffuseColor(Color3f diffuseColor) {
		if ( primitive != null) {
			Material material = primitive.getAppearance().getMaterial();
			material.setDiffuseColor(diffuseColor);
			primitive.getAppearance().setMaterial(material);
		}
	}

	@Override
	public void setPosition(Vector3d position) {
		Transform3D transform3d = new Transform3D();
		getTransform(transform3d);
		transform3d.setTranslation(position);
		setTransform(transform3d);
	}
	
	@Override
	public void setTransparency(float transparency) {
		if( primitive != null) {
			TransparencyAttributes ta = new TransparencyAttributes();
			ta.setTransparencyMode(TransparencyAttributes.BLEND_ONE);
			ta.setTransparency(transparency);
			primitive.getAppearance().setTransparencyAttributes(ta);
		}
	}

	private static Primitive getPrimitive(String geometry) {
		switch (geometry) {
		case "cube":
			return new Box();
		case "sphere":
			return new Sphere();
		case "cone":
			return new Cone();
		}
		return null;
	}


	@Override
	public void setOrientation(Quat4d orientation) {
		
		Transform3D transform3d = new Transform3D();
		getTransform(transform3d);
		
		// Get translation
		Vector3d translation = new Vector3d();
		transform3d.get(translation);
		
		transform3d.set(orientation);
		transform3d.setTranslation(translation);
		setTransform(transform3d);
	}

	@Override
	public void setScale(Vector3d scale) {
		// TODO Auto-generated method stub
		System.err.println("not implemented");
	}

	public void setShininess(float shininess) {
		Material material = primitive.getAppearance().getMaterial();
		material.setShininess(shininess);
	}

	@Override
	public ICObject getController() {
		return this.controller;
	}

	@Override
	public Transform3D getTransform() {
		Transform3D t = new Transform3D();
		super.getTransform(t);
		return t;
	}
}
