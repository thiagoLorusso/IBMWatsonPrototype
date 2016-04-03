package com.ibm.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.application.entity.ConceptEntity;
import com.ibm.application.service.CloudantDBService;
import com.ibm.watson.developer_cloud.concept_expansion.v1.model.Concept;

@RestController
public class CloudantDBController {
	
	@RequestMapping(value = "save/{inputConception}/{conceptionResult}", method = RequestMethod.POST)
	ResponseEntity<Concept> save(@PathVariable String inputConception, @PathVariable String conceptionResult) {
		
		CloudantDBService cdbs = new CloudantDBService();
		ConceptEntity ce = new ConceptEntity(inputConception, conceptionResult);
		cdbs.save(ce);
		
		return new ResponseEntity(null, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "search/{inputConception}", method = RequestMethod.GET)
	ResponseEntity<ConceptEntity> search(@PathVariable String inputConception) {
		
		CloudantDBService cdbs = new CloudantDBService();
		ConceptEntity ce = cdbs.search(inputConception);
		if(ce==null){
			new ResponseEntity(ce, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity(ce, HttpStatus.OK);
	}
}
