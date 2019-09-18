package com.fwd.affiliate.cms.linkshare.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class ViewLogResponse {

   boolean resultFlag;
   String resultMsg;
   ResultContent resultContent;
}
