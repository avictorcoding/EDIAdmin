package com.dg.EDIAdmin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dg.EDIAdmin.dto.SchedulerConfigDto;

@Controller
public class HomeController {

	@GetMapping({ "/", "/index" })
	public String home(Model model) {
		return "index";
	}

	@GetMapping("{tab}")
	public ModelAndView reportingScheduler(@PathVariable String tab) {
		if ("reporting".contains(tab.trim())) {
			SchedulerConfigDto schedulerConfig = getConfigObject(false);
			ModelAndView mav = new ModelAndView("reporting-form");
			mav.addObject("schedulerConfig", schedulerConfig);
			return mav;
		} else {
			SchedulerConfigDto schedulerConfig = getConfigObject(true);
			ModelAndView mav = new ModelAndView("datasource-form");
			mav.addObject("schedulerConfig", schedulerConfig);
			return mav;
		}

	}

	private SchedulerConfigDto getConfigObject(boolean fromDatasource) {
		try {
			return SchedulerConfigDto.builder().frequency(2).schedule("Hourly").enabled(true)
					.scheduleDateOfMonth(new SimpleDateFormat("yyyyy-mm-dd").parse(new SimpleDateFormat("yyyyy-mm-dd").format(new Date()))).fromDatasource(fromDatasource)
					.build();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/saveSchedulerConfig")
	public ModelAndView saveSchedulerConfig(
			@ModelAttribute("schedulerConfigDto") SchedulerConfigDto schedulerConfigDto) {
		System.out.println(schedulerConfigDto);
		SchedulerConfigDto schedulerConfig = schedulerConfigDto;
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("schedulerConfig", getConfigObject(false));
		mav.addObject("activateReporting", false);
		return mav;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), true));
	}

	/*
	 * @GetMapping("{tab}") public ModelAndView datasourceScheduler(@PathVariable
	 * String tab) { if (Arrays.asList("reporting", "datasource").contains(tab) ) {
	 * SchedulerConfigDto schedulerConfig = new SchedulerConfigDto(); ModelAndView
	 * mav = new ModelAndView("reporting-form");
	 * mav.addObject("schedulerConfig",schedulerConfig); return mav; } return new
	 * ModelAndView("index"); }
	 */

}
