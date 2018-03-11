package com.rpg.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 11-Mar-2018
 */
public class DeepCloneUtil implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Create a new instance of the object using serialization
	 * 
	 * @param source
	 * @return Object
	 */
	public Object deepClone(Object source) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(source);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Object) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
}
