package com.fwd.affiliate.lead.serviceImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fwd.affiliate.lead.model.Affiliate;
import com.fwd.affiliate.lead.model.Agent;
import com.fwd.affiliate.lead.model.ResultContent;
import com.fwd.affiliate.lead.service.AgentService;


@Service("agentService")
public class AgentServiceImpl implements AgentService {
    
    @Value("${url.saveViewLog}")
    private String uriFromSetting;

    public ResultContent getViewLogFromUrl(String utmRef, String articleUrl, String postId, String media)
	    throws JsonParseException, JsonMappingException, IOException {
	final String uri = uriFromSetting;
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

	Map<String, Object> map = new HashMap<>();

	map.put("ref", utmRef);
	map.put("postId", postId);
	map.put("articleUrl", articleUrl);
	map.put("media", media);
	
	HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

	RestTemplate restTemplate = new RestTemplate();

	String response = restTemplate.postForObject(uri, entity, String.class);
	ObjectMapper objectMapper = new ObjectMapper();
	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	ResultContent resultContent = new ResultContent();
	Agent agent = null;
	Affiliate affiliate = null;

	JsonNode agentNode = objectMapper.readTree(response).findValue("Agent");
	JsonNode affiliateNode = objectMapper.readTree(response).findValue("Affiliate");
	if (agentNode != null) {
	    agent = objectMapper.readValue(agentNode.toString(), Agent.class);
	    resultContent.setAgent(agent);
	}
	if (affiliateNode != null) {
	    affiliate = objectMapper.readValue(affiliateNode.toString(), Affiliate.class);
	    resultContent.setAffiliate(affiliate);
	}

	// Agent agent = response.getBody().getResultContent().getAgent();
	// if (response.getStatusCode() == HttpStatus.OK) {x`
	return resultContent;
	// } else {
	// return null;
	// }
    }
}