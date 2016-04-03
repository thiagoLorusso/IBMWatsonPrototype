package com.ibm.application.entity;

public class ConceptEntity {
	
	public ConceptEntity(String inputText, String conceptionExpansion){
		this.inputText = inputText;
		this.conceptExpasion = conceptionExpansion;
	}

	public String inputText;
	public String conceptExpasion;
	
	public String getInputText() {
		return inputText;
	}
	public void setInputText(String inputText) {
		this.inputText = inputText;
	}
	public String getConceptExpasion() {
		return conceptExpasion;
	}
	public void setConceptExpasion(String conceptExpasion) {
		this.conceptExpasion = conceptExpasion;
	}
	
}
