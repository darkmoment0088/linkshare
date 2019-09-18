package com.fwd.affiliate.cms.linkshare.model;

import java.util.Date;

import lombok.NoArgsConstructor;
@NoArgsConstructor
public class PostDetail {
    
    private Long id;
    private String status;
    private String recommend;
    private String title;
    private String bodyTitle;
    private String bodyContent;
    private String commission;
    private String deal;
    private String articleUrl;
    private String imageUuid;
    private String type;
    private Date publishDate;
    private Date expiryDate;
    private Date createdDate;
    private Date modifyDate;
}

