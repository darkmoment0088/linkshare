package com.fwd.affiliate.cms.linkshare.model;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultContent {

    Agent agent;
    Affiliate affiliate;
}
