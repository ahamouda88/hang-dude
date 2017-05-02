package com.hangdude.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hangdude.model.constants.PageConstants;

/**
 * This controller class returns the name of the view file corresponding to every angular url
 */
@Controller
public class SpringAngularController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String appPage() {
		return PageConstants.APP_PAGE;
	}

	@RequestMapping(value = "/welcomePage", method = RequestMethod.GET)
	public String welcomePage() {
		return PageConstants.WELCOME_PAGE;
	}

}
