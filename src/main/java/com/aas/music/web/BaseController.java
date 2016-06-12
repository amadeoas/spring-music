package com.aas.music.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


/**
 * Utility class to encapsulate common functionality for controllers.
 * 
 * @author aasco
 */
public class BaseController {

	protected static final String DEFAULT_LANGUAGE = "en_GB";


	@InitBinder
    public void setAllowedFields(final WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

	/**
	 * Sets the language for the next page.
	 * 
	 * @param language the language.
	 * @param model the model.
	 */
    public void setLanguage(String language, final Model model) {
    	if (language == null || language.length() == 0) {
    		language = DEFAULT_LANGUAGE;
    	}
    	if (model == null) {
    		return;
    	}
    	model.addAttribute("language", language);
    }

}
