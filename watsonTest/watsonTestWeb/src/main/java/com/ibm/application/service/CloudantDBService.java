package com.ibm.application.service;

import java.util.HashMap;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.DocumentNotFoundException;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import com.ibm.application.entity.ConceptEntity;

public class CloudantDBService {
	
	HttpClient httpClient = null;
	
	public CloudantDBService(){
		httpClient = new StdHttpClient.Builder().host("cdaa02c1-ab98-4fd5-86de-f8d11a5b327f-bluemix.cloudant.com")
				.port(443)
				.username("cdaa02c1-ab98-4fd5-86de-f8d11a5b327f-bluemix")
				.password("7d278d8de358f6d084c0bccfa6355cbcaf4f407ebed41b9d356b4de956693dc7")
				.enableSSL(true)
				.relaxedSSLSettings(true)
				.build();
	}

	public void save(ConceptEntity entity){
		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
		CouchDbConnector dbc = new StdCouchDbConnector("concept_db", dbInstance);
		
		HashMap<String, Object> data = createDataToPersist(entity);
		dbc.create(data);
	}
	
	private HashMap<String, Object> createDataToPersist(ConceptEntity entity) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("_id", entity.getInputText().toUpperCase());
		data.put("concepts", entity.getConceptExpasion());
		return data;
	}

	public ConceptEntity search(String concept){
		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
		CouchDbConnector dbc = new StdCouchDbConnector("concept_db", dbInstance);
		
		concept = concept.toUpperCase();

		try {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> obj = dbc.get(HashMap.class, concept);
			ConceptEntity ce =  new ConceptEntity((String)obj.get("_id"), (String)obj.get("concepts"));
			return ce;
		} catch (DocumentNotFoundException e) {
			return null;
		}
	}
	
}
