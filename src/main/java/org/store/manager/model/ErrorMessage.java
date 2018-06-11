package org.store.manager.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter
@Setter
public class ErrorMessage {

	private String errorMessage;
	private int errorCode;
	private String documentation;
	
	public ErrorMessage(){
	}
	public ErrorMessage(String errorMessage, int errorCode, String documentation){
		super();
		this.errorMessage=errorMessage;
		this.errorCode=errorCode;
		this.documentation=documentation;
	}
}
