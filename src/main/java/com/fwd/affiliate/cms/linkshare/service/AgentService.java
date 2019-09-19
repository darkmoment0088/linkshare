package com.fwd.affiliate.cms.linkshare.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fwd.affiliate.cms.linkshare.model.Agent;

public interface AgentService {
    public Agent getAgentFromUrl (String utmRef, String articleUrl , String postId, String media) throws JsonParseException, JsonMappingException, IOException;
}
