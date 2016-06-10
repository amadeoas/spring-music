package com.aas.music.web;

import com.aas.music.model.EqInstrument;
import com.aas.music.model.Instrument;
import com.aas.music.model.VocalCompressor;
import com.aas.music.service.InstrumentService;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Controller used to access the instrument's details.
 * 
 * @author Amadeo Asco
 */
@Controller
@RequestMapping("/instruments")
public class InstrumentsController extends BaseController {

    private final InstrumentService instrumentService;


    @Autowired
    public InstrumentsController(final InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

	/**
     * <p>Retrieves a list of all the presets.</p>
     * 
     * <p>Expected HTTP GET and request '/instruments'.</p>
     * 
     * @param lang the language.
     * @param model the model.
     * @return the template.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String initList(@RequestParam(value="lang", required=false) String language, 
    		final Model model) {
    	final Collection<Instrument> instruments = this.instrumentService.getAllInstruments();

    	setLanguage(language, model);
    	model.addAttribute("instruments", instruments);

        return "instruments/instrumentsList";
    }

	/**
     * <p>Retrieves a list of all the products the specified customer is subscribed to.</p>
     * 
     * <p>Expected HTTP GET and request '/instruments/{customerId}'.</p>
     * 
     * @param instrumentId the instrument's ID.
     * @param type the instrument's type.
     * @return the instrument specified.
     */
    @RequestMapping(value="/data/{instrumentId}_{type}", method=RequestMethod.GET)
    public @ResponseBody Instrument get(@PathVariable int instrumentId, 
    		@PathVariable final String type) {
    	return this.instrumentService.findInstrument(instrumentId, type);
    }

	/**
     * <p>Retrieves the view of the instrument's details.</p>
     * 
     * <p>Expected HTTP GET and request '/instruments/view/{instrumentId}'.</p>
     * 
     * @param lang the language.
     * @param instrumentId the instrument's ID.
     * @param type the instrument's type.
     * @param model the model.
     * @return the template.
     */
    @RequestMapping(value="/view/{instrumentId}_{type}", method = RequestMethod.GET)
    public String view(@RequestParam(value="lang", required=false) String language, 
    		@PathVariable final int instrumentId, @PathVariable final String type, 
    		final Model model) {
    	final Instrument instrument = this.instrumentService.findInstrument(instrumentId, type);

    	setLanguage(language, model);
    	model.addAttribute("instrument", instrument);

        return EqInstrument.TYPE.equals(type) ? "instruments/instrumentView" : "instruments/vcView";
    }

	/**
     * <p>Retrieves the add view of the instrument's details.</p>
     * 
     * <p>Expected HTTP GET and request '/instruments/view/{instrumentId}'.</p>
     * 
     * @param lang the language.
     * @param instrumentId the instrument's ID.
     * @param type the instrument's type.
     * @param model the model.
     * @return the template.
     */
    @RequestMapping(value="/addView/{instrumentId}_{type}", method = RequestMethod.GET)
    public String addView(@RequestParam(value="lang", required=false) String language, 
    		@PathVariable final int instrumentId, @PathVariable final String type, 
    		final Model model) {
    	setLanguage(language, model);

        return EqInstrument.TYPE.equals(type) ? "instruments/instrumentAdd" : "instruments/vcAdd";
    }

	/**
     * <p>Retrieves the edit view of the instrument's details.</p>
     * 
     * <p>Expected HTTP GET and request '/instruments/edit/{instrumentId}'.</p>
     * 
     * @param lang the language.
     * @param instrumentId the instrument's ID.
     * @param type the instrument's type.
     * @param model the model.
     * @return the template.
     */
    @RequestMapping(value="/edit/{instrumentId}_{type}", method = RequestMethod.GET)
    public String edit(@RequestParam(value="lang", required=false) String language, 
    		@PathVariable final int instrumentId, @PathVariable final String type, 
    		final Model model) {
    	setLanguage(language, model);
    	model.addAttribute("id", instrumentId);
    	model.addAttribute("type", type);

        return EqInstrument.TYPE.equals(type) ? "instruments/instrumentEdit" : "instruments/vcEdit";
    }

	/**
     * <p>Saves the instrument's details provided.</p>
     * 
     * <p>Expected HTTP GET and request '/instruments/edit/{instrumentId}'.</p>
     * 
     * @param lang the language.
     * @param instrument the instrument's details.
     * @param model the model.
     * @return the template.
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody int add(final EqInstrument instrument) {
    	return this.instrumentService.add(instrument);
    }

    @RequestMapping(value="/addVC", method = RequestMethod.POST)
    public @ResponseBody int add(final VocalCompressor instrument) {
    	return this.instrumentService.add(instrument);
    }

	/**
     * <p>Updates the instrument with the provided details.</p>
     * 
     * <p>Expected HTTP POST and request '/instruments/update'.</p>
     * 
     * @param instrument the instrument's details.
     * @param model the model.
     * @return the success template.
     */
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String update(final EqInstrument instrument, final Model model) {
    	this.instrumentService.update(instrument);

        return "/success";
    }
    @RequestMapping(value="/updateVC", method = RequestMethod.POST)
    public String update(final VocalCompressor instrument, final Model model) {
    	this.instrumentService.update(instrument);

        return "/success";
    }

	/**
     * <p>Deletes the specified instrument.</p>
     * 
     * <p>Expected HTTP GET and request '/instruments/edit/{instrumentId}'.</p>
     * 
     * @param lang the language.
     * @param instrumentId the instrument's ID.
     * @param type the instrument's type.
     * @param model the model.
     * @return the template.
     */
    @RequestMapping(value="/delete/{instrumentId}_{type}", method = RequestMethod.GET)
    public String delete(@RequestParam(value="lang", required=false) String language, 
    		@PathVariable final int instrumentId, @PathVariable final String type, 
    		final Model model) {
    	this.instrumentService.delete(instrumentId, type);

        return initList(language, model);
    }

} // end class InstrumentsController
