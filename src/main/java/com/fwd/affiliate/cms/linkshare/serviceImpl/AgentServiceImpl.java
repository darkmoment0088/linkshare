package com.fwd.affiliate.cms.linkshare.serviceImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fwd.affiliate.cms.linkshare.model.Affiliate;
import com.fwd.affiliate.cms.linkshare.model.Agent;
import com.fwd.affiliate.cms.linkshare.model.ResultContent;
import com.fwd.affiliate.cms.linkshare.service.AgentService;

@Service("agentService")
public class AgentServiceImpl implements AgentService {

    public ResultContent getViewLogFromUrl(String utmRef, String articleUrl, String postId, String media)
	    throws JsonParseException, JsonMappingException, IOException {
	final String uri = "http://10.110.1.130:8080/affiliate_gateway/affiliate_share/external/productViewLog/saveViewLog";

	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

	Map<String, Object> map = new HashMap<>();

	map.put("utmCode", utmRef);
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