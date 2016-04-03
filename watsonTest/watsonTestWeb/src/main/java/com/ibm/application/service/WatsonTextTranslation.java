package com.ibm.application.service;

import java.util.ArrayList;
import java.util.List;

//import com.ibm.json.java.JSONArray;
//import com.ibm.json.java.JSONObject;
import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.IdentifiableLanguage;
import com.ibm.watson.developer_cloud.language_translation.v2.model.IdentifiedLanguage;
import com.ibm.watson.developer_cloud.language_translation.v2.model.Translation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;

public class WatsonTextTranslation {
	
	private static final String DEFAULT_ERROR_MESSAGE = "No translation was found for this text. Something may be wrong in your text or the text language is not supported by Watson translation service.";
	
    public String findLanguage(LanguageTranslation service, String inputText){
    	List<IdentifiedLanguage> list = service.identify(inputText);
    	if(list == null || list.size() == 0){
    		return null;
    	}
    	
    	IdentifiableLanguage idl = (IdentifiableLanguage) list.get(0);
    	List<IdentifiableLanguage> idfLang = service.getIdentifiableLanguages();

    	if(isValidLanguage(idl.getLanguage(), idfLang)){
    		return idl.getLanguage();
    	}else{
    		return null;
    	}
    }
    
    public TranslationResult translate(String inputText){
    	//initiating service
    	LanguageTranslation service = new LanguageTranslation();
    	
    	//find the input language
    	String inputLanguage = findLanguage(service, inputText);
    	
    	//if language is not supported, return a default text
    	if(inputLanguage == null){
    		Translation t = new Translation().withTranslation(DEFAULT_ERROR_MESSAGE);
    		List<Translation> tl = new ArrayList<Translation>();
    		tl.add(t);
    		TranslationResult tr = new TranslationResult(); 
    		tr.setTranslations(tl);
    		List<TranslationResult> trs = new ArrayList<TranslationResult>();
    		trs.add(tr);
    		return tr;
    		
    	//if input text is in English, do not call Watson service. Return the inputed text	
    	}else if(inputLanguage.equals("en")){
    		Translation t = new Translation().withTranslation(inputText);
    		List<Translation> tl = new ArrayList<Translation>();
    		tl.add(t);
    		TranslationResult tr = new TranslationResult(); 
    		tr.setTranslations(tl);
    		List<TranslationResult> trs = new ArrayList<TranslationResult>();
    		trs.add(tr);
    		return tr;
    	}
    	
    	//Call Watson Translation Service
    	TranslationResult translationResult = service.translate(inputText, inputLanguage , "en");
        
    	return translationResult;
    }

	private boolean isValidLanguage(String inputLanguage, List<IdentifiableLanguage> idfLang) {
		for (IdentifiableLanguage identifiableLanguage : idfLang) {
			if(identifiableLanguage.getLanguage().equals(inputLanguage)){
				return true;
			}
		}
		return false;
	}
    
}
