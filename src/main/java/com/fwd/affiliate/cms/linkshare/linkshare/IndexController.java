package com.fwd.affiliate.cms.linkshare.linkshare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xml.sax.SAXException;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
	return "";
    }

    @GetMapping("/link")
    public String link(@RequestParam(name = "link", required = true) String link, Model model)
	    throws ParserConfigurationException, SAXException, IOException {
	Document doc = Jsoup.connect(link).get();
	
	String metaList = doc.select("meta").outerHtml();

	System.out.println("metaList"+metaList);
	
	model.addAttribute("link", link);
	model.addAttribute("metaList", metaList);

	return "index";
    }
}
