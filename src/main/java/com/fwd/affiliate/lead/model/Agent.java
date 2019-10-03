package com.fwd.affiliate.lead.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Agent implements Serializable {

    private long id;
    private String agentCode;
    private String utmCode;
    private Date gmtCreated;
    private Date gmtModified;
    private String agentName;
    private String expertiseFields;
    private String accolade;
    private String status;
    private String briefDescription;
    private String expertiseYear;
    private String imgUrl;


}
