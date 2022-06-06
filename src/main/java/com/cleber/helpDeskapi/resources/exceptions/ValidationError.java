package com.cleber.helpDeskapi.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StanderError{

	private List<FieldMessage> fieldMessage = new ArrayList<>();

	
	public ValidationError() {
		super();
	}
	
	public ValidationError(Long timestemp, Integer status, String error, String message, String path) {
		super(timestemp, status, error, message, path);	
	}

	public List<FieldMessage> getFieldMessage() {
		return fieldMessage;
	}

	public void addError(String fieldName, String message) {
		this.fieldMessage.add(new FieldMessage(fieldName, message));
	}

}
