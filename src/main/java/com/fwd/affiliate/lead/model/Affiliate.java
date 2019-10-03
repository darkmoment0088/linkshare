package com.fwd.affiliate.lead.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Affiliate {
    private long id;
    private String userName;
    private String userCode;
    private String utmCode;
    
    private String ancestorAgentCode;
    private String parentAffiliateId;
    private String parentPath;
    private String gender;
    private String userType;
}
