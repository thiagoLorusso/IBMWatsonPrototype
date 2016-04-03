package com.ibm.application.service;

import java.util.List;

import com.ibm.watson.developer_cloud.concept_expansion.v1.ConceptExpansion;
import com.ibm.watson.developer_cloud.concept_expansion.v1.model.Concept;
import com.ibm.watson.developer_cloud.concept_expansion.v1.model.Job;

public class WatsonConceptExpansion {

    public List<Concept> getSimilarSentences(String[] strings){
    	ConceptExpansion service = new ConceptExpansion();

    	Job job = service.createJob(strings);
    	while (service.getJobStatus(job) == Job.Status.AWAITING_WORK || service.getJobStatus(job) == Job.Status.IN_FLIGHT) {
    		try {
    			Thread.sleep(10000);
    		} catch (final InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    	return service.getJobResult(job);
    }
}
