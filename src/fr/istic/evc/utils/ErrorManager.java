package fr.istic.evc.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Allow to manage errors during the parsing of xml file
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 *
 */
public class ErrorManager {
	protected List<String> errors;
	protected List<String> warnings;
	
	public ErrorManager() {
		errors = new ArrayList<String>();
		warnings = new ArrayList<String>();
	}

	public boolean isInError() {
		return !errors.isEmpty();
	}

	public void showError() {
		for (String error : errors) {
			System.err.println(" - " + error);
		}
	}

	public void addError(String error) {
		errors.add(error);
	}
}
