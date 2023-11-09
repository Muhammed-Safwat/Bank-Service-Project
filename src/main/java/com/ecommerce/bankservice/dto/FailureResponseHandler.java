package com.ecommerce.bankservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FailureResponseHandler {

    private String error;
    private boolean ok;
    private int status;

}
