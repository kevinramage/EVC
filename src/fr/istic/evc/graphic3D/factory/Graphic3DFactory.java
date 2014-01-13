package fr.istic.evc.graphic3D.factory;

import javax.media.j3d.BranchGroup;

import fr.istic.evc.graphic3D.base.TransformGroup;

public interface Graphic3DFactory {
	BranchGroup createBranchGroup();
	TransformGroup createTransformGroup();
}
