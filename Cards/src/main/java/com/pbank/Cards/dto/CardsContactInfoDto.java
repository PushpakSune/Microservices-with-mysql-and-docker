package com.pbank.Cards.dto;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties("cards")

public class CardsContactInfoDto {
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(Map<String, String> contactDetails) {
        this.contactDetails = contactDetails;
    }

    public List<String> getOnCallSupport() {
        return onCallSupport;
    }

    public void setOnCallSupport(List<String> onCallSupport) {
        this.onCallSupport = onCallSupport;
    }


}
