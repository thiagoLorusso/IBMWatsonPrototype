package com.ibm.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.application.service.WatsonTextTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;

@RestController
public class WatsonTextTranslationController {

	@RequestMapping(value = "textTranslation/{inputText}", method = RequestMethod.GET)
	ResponseEntity<TranslationResult> translate(@PathVariable String inputText) {
		WatsonTextTranslation wtt = new WatsonTextTranslation();
		TranslationResult tr = wtt.translate(inputText);
		return new ResponseEntity(tr, HttpStatus.OK);
	}

}