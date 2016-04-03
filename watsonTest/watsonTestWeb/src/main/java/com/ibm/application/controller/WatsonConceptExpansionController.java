package com.ibm.application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.application.service.WatsonConceptExpansion;
import com.ibm.watson.developer_cloud.concept_expansion.v1.model.Concept;

@RestController
public class WatsonConceptExpansionController {

	@RequestMapping(value = "conceptExpansion/{inputConception}", method = RequestMethod.GET)
	ResponseEntity<List<Concept>> getConceptions(@PathVariable String inputConception) {
		String[] strings = inputConception.split(",");
		WatsonConceptExpansion wce = new WatsonConceptExpansion();
		List<Concept> conceptList = wce.getSimilarSentences(strings);
		return new ResponseEntity(conceptList, HttpStatus.OK);
	}
	
	
}
