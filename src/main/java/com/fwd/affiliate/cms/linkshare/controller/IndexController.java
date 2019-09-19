package com.fwd.affiliate.cms.linkshare.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xml.sax.SAXException;

import com.fwd.affiliate.cms.linkshare.model.Agent;
import com.fwd.affiliate.cms.linkshare.service.AgentService;

@Controller
public class IndexController {

    @Autowired
    AgentService agentService;
    Agent agent;
    // UTMParam:
    // http://localhost:8080/?articleUrl=http://www.bbc.com&utmCode=pJzZMRfF&media=Wc&postId=1
    // https://affiliatelinkshare.herokuapp.com/?articleUrl=http://www.bbc.com&utmCode=pJzZMRfF&media=Wc&postId=1

    @RequestMapping("/")
    public String index(@RequestParam(name = "utmCode", required = false) String utmCode,
	    @RequestParam(name = "articleUrl", required = true) String articleUrl,
	    @RequestParam(name = "media", required = false) String media,
	    @RequestParam(name = "postId", required = false) String postId, Model model)
	    throws ParserConfigurationException, SAXException, IOException {

	// use URL parsing so can omit https://
	String metaList = null;
	try {

	    /*
	     * System.setProperty("http.proxyHost", "10.23.22.205");
	     * System.setProperty("http.proxyPort", "80");
	     * System.setProperty("https.proxyHost", "10.23.22.205");
	     * System.setProperty("https.proxyPort", "80");
	     */

	    URL url = new URL(articleUrl);
	    // Proxy proxy = new Proxy(Proxy.Type.HTTP, new
	    // InetSocketAddress("10.23.22.205", 80)); // or whatever your proxy is
	    HttpURLConnection uc = (HttpURLConnection) url.openConnection();

	    uc.connect();

	    String line = null;
	    StringBuffer tmp = new StringBuffer();
	    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
	    while ((line = in.readLine()) != null) {
		tmp.append(line);
	    }

	    Document doc = Jsoup.parse(String.valueOf(tmp));

	    // Document doc = Jsoup.connect(articleUrl).get();
	    metaList = doc.select("meta").outerHtml();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	System.out.println("metaList" + metaList);

	try {
	    agent = agentService.getAgentFromUrl(utmCode, articleUrl, postId, media);
	} catch (Exception e) {

	}

	model.addAttribute("link", articleUrl);
	model.addAttribute("metaList", metaList);
	model.addAttribute("media", media);
	model.addAttribute("PostId", postId);
	model.addAttribute("Agent", agent);

	return "index";
    }

}
