package com.aas.music.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Controller used to access the initial page.
 * 
 * This is required to have more control about the language version of the page.
 * 
 * @author Amadeo Asco
 */
@Controller
public class HomeController extends BaseController {

	/**
     * <p>Replies with the initial page.</p>
     * 
     * <p>Expected HTTP GET and request '/'.</p>
     * 
     * @param lang the language.
     * @param model the model.
     * @return the template.
     */
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String success(@RequestParam(value="lang", required=false) String language, 
    		final Model model) {
    	setLanguage(language, model);

    	return "welcome";
    }

}
