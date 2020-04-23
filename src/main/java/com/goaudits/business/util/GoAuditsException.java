package com.goaudits.business.util;

public class GoAuditsException {

	private String errorMessage;
	
	public GoAuditsException(String message) {
		    this.errorMessage = message;
	}
	
	public String getErrorMessage() {
        return errorMessage;
    }
}
