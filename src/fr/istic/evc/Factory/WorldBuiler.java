package fr.istic.evc.Factory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.istic.evc.object3D.base.controller.CWorld;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;


public class WorldBuiler {
	
	private static WorldBuiler instance;
	private static final String directoryName = "resources/world/";
	private static final String packageObject = "fr.istic.evc.object3D.base.controller.";
	private static Map<String, Class<?>[]> methodsType;
	
	
	private WorldBuiler(){
		methodsType = new HashMap<String, Class<?>[]>();
		methodsType.put("Geometry", new Class<?>[] { String.class });
		methodsType.put("AmbientColor", new Class<?>[] { Color3f.class });
		methodsType.put("DiffuseColor", new Class<?>[] { Color3f.class });
		methodsType.put("Position", new Class<?>[] { Vector3d.class });
		methodsType.put("Pickable", new Class<?>[] { boolean.class });
	}
	
	public CWorld load(String fileName) {
		CWorld world = new CWorld();
		
		try {
			
			// Factory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			// Read document
			Document document = builder.parse(new File(directoryName + fileName));
			Element rootElement = document.getDocumentElement();
			
			// Read objects
			NodeList objectsNode = rootElement.getElementsByTagName("Objects").item(0).getChildNodes();
			for ( int i = 0; i < objectsNode.getLength(); i++) {
				
				// Get node
				Node node = objectsNode.item(i);
				
				// Avoid text node
				if (node.getNodeType() == Node.TEXT_NODE)
					continue;
				
				// Read and instance 3D object
				String nodeName = node.getNodeName();
				Class<?> objectClass = Class.forName(packageObject + nodeName);
				ICObject obj = (ICObject)objectClass.newInstance();
				
				// Id
				Node attributeObjectId = node.getAttributes().getNamedItem("id");
				if ( attributeObjectId != null) {
					obj.setId(attributeObjectId.getTextContent());
				}
				
				// Attributes
				NodeList attributes = node.getChildNodes();
				for ( int j = 0; j < attributes.getLength(); j++) {
					
					// Get node
					Node attribute = attributes.item(j);
					
					// Avoid text node
					if ( attribute.getNodeType() == Node.TEXT_NODE)
						continue;
					
					// Get method name
					String methodName = "update" + attribute.getNodeName();
					//System.out.println("Method: " + methodName);
					
					// Invoke method
					Class<?>[] methodType = methodsType.get(attribute.getNodeName());
					Method method = objectClass.getMethod(methodName, methodType);
					Object arg = convert(methodType[0], attribute.getTextContent());
					method.invoke(obj, new Object[] { arg });
				}
				
				// Add object to world
				world.add(obj);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();			
		} catch (InvocationTargetException e) {
			e.printStackTrace();		
		} catch (NoSuchMethodException e) {
			e.printStackTrace();			
		} catch (SecurityException e) {
			e.printStackTrace();			
		} catch (IllegalAccessException e) {
			e.printStackTrace();			
		} catch (InstantiationException e) {
			e.printStackTrace();			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {	
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		
		return world;
	}
	
	private static Object convert(Object type, String input) {
		input = input.trim();
		if ( type.equals(String.class)) {
			return input;
		} else if ( type.equals(Color3f.class)) {
			String [] data = input.split(" ");
			if ( data.length == 3) {
				float r = Float.parseFloat(data[0]);
				float g = Float.parseFloat(data[1]);
				float b = Float.parseFloat(data[2]);
				return new Color3f(r, g, b);
			}
		} else if ( type.equals(Vector3d.class)) {
			String [] data = input.split(" ");
			if ( data.length == 3) {
				float x = Float.parseFloat(data[0]);
				float y = Float.parseFloat(data[1]);
				float z = Float.parseFloat(data[2]);
				return new Vector3d(x, y, z);
			}
		} else if ( type.equals(boolean.class)) {
			return Boolean.parseBoolean(input);
		}
		System.err.println("BAD: " + type);
		return null;
	}	
	
	
	public synchronized static WorldBuiler getInstance() {
		if ( instance == null ) {
			instance = new WorldBuiler();
		}
		return instance;
	}
}
