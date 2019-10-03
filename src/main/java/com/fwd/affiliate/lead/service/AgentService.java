package com.fwd.affiliate.lead.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fwd.affiliate.lead.model.ResultContent;

public interface AgentService {
    public ResultContent getViewLogFromUrl (String utmRef, String articleUrl , String postId, String media) throws JsonParseException, JsonMappingException, IOException;
}
