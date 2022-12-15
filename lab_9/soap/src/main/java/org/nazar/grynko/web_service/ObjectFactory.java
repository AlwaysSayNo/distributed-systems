package org.nazar.grynko.web_service;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public GetBirthdayRequest createGetBirthdayRequest() {
        return  new GetBirthdayRequest();
    }

    public GetAgeResponse createGetAgeResponse() {
        return new GetAgeResponse();
    }
}
