package com.fwd.affiliate.cms.linkshare.service;

import com.fwd.affiliate.cms.linkshare.model.Agent;

public interface AgentService {
    public Agent getAgentFromUrl (String utmRef, String articleUrl , String postId, String media);
}
