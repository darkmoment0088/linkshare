package com.fwd.affiliate.cms.linkshare.controller;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xml.sax.SAXException;

import com.fwd.affiliate.cms.linkshare.model.ResultContent;
import com.fwd.affiliate.cms.linkshare.service.AgentService;

@Controller
public class IndexController {

    @Autowired
    AgentService agentService;

    // UTMParam:
    // https://affiliatelinkshare.herokuapp.com/?article=https://www.bbc.com&UTMreferralCode=zFNY1055&Media=Facebook&PostID=14072
    // http://localhost:8080/?articleUrl=https://www.bbc.com&utmCode=zFNY1055&media=Wc&postId=1
    @RequestMapping("/")
    public String index(@RequestParam(name = "utmCode", required = false) String utmCode,
	    @RequestParam(name = "articleUrl", required = true) String articleUrl,
	    @RequestParam(name = "media", required = false) String media,
	    @RequestParam(name = "postId", required = false) String postId, Model model)
	    throws ParserConfigurationException, SAXException, IOException {

	// use URL parsing so can omit https://
	String metaList = "";
	try {

	    /*
	     * URL url = new URL(articleUrl); // Proxy proxy = new Proxy(Proxy.Type.HTTP,
	     * new // InetSocketAddress("10.23.22.205", 80)); // or whatever your proxy is
	     * HttpURLConnection uc = (HttpURLConnection) url.openConnection();
	     * 
	     * uc.connect();
	     * 
	     * String line = null; StringBuffer tmp = new StringBuffer(); BufferedReader in
	     * = new BufferedReader(new InputStreamReader(uc.getInputStream())); while
	     * ((line = in.readLine()) != null) { tmp.append(line); }
	     * System.out.println("tmp : " + tmp); Document doc =
	     * Jsoup.parse(String.valueOf(tmp));
	     * 
	     */

	    /*
	     * System.setProperty("http.proxyHost", "10.23.22.205");
	     * System.setProperty("http.proxyPort", "80");
	     * System.setProperty("https.proxyHost", "10.23.22.205");
	     * System.setProperty("https.proxyPort", "80");
	     */
	    Document doc = Jsoup.connect(articleUrl).get();
	    metaList = doc.select("meta").outerHtml();
	} catch (Exception e) {
	    System.out.println(e);

	}

	System.out.println("metaList" + metaList);

	try {
	    ResultContent resultContent = agentService.getViewLogFromUrl(utmCode, articleUrl, postId, media);
	    model.addAttribute("Agent", resultContent.getAgent());
	    model.addAttribute("Affiliate", resultContent.getAffiliate());
	} catch (Exception e) {
	    System.out.println(e);

	}

	model.addAttribute("link", articleUrl);
	model.addAttribute("metaList", metaList);
	model.addAttribute("media", media);
	model.addAttribute("PostId", postId);

	return "index";
    }

}
