package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.LocationStates;
import com.example.demo.services.CoronaVirusDataServices;

@Controller
public class HomeController 
{
	@Autowired
	CoronaVirusDataServices crnService;
	
	
	public void setCrnService(CoronaVirusDataServices crnService) {
		this.crnService = crnService;
	}


	@GetMapping("/")
	public String home(Model model)
	{
		System.out.println("==============controler reach========================");
		List<LocationStates> allstates=crnService.getAllstates();
		
		System.out.println("========================AllState+++++++++++++++++++");
		System.out.println(allstates);
		int totalDeathsReported=allstates.stream().mapToInt(stat->stat.getLatestTotalDeaths()).sum();
		model.addAttribute("LocationStates",allstates);
		model.addAttribute("totalDeathsReported",totalDeathsReported);
		return "home";
	}
	
	@RequestMapping(path="/chartfile",produces  = {"application/json"} )
	@ResponseBody
	public List<LocationStates> chartfile() {
		List<LocationStates> allstates=crnService.getAllstates();
		return  allstates;
	}
	
	@RequestMapping("/charts")
	public String charts() {
		
		return "chart";
	}
	
	@RequestMapping("/map")
	public String map() {
		
		return "map";
	}

}
