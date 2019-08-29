package com.fwd.affiliate.cms.linkshare.linkshare;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
	return "";
    }
    
    @GetMapping("/link")
    public String link(@RequestParam(name="link", required=true)String link, Model model) 
    {
	
	
	System.out.println("link: " + link);
	model.addAttribute("link", link);
	
	String meta = "test";
	
	
	return "index";
    }
}
