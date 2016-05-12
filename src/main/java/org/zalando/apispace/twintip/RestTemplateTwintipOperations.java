package org.zalando.apispace.twintip;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.List;

import static org.springframework.http.RequestEntity.get;

public class RestTemplateTwintipOperations {

    private static final ParameterizedTypeReference<List<ApplicationInformation>> AS_APPLICATION_LIST =
            new ParameterizedTypeReference<List<ApplicationInformation>>() { };

    private final RestOperations restOperations;
    private final String baseUrl;

    public RestTemplateTwintipOperations(RestOperations restOperations, String baseUrl) {
        this.restOperations = restOperations;
        this.baseUrl = baseUrl;
    }

    public List<ApplicationInformation> retrieveAllApplications() {
        return restOperations.exchange(get(URI.create(baseUrl + "/apps")).build(), AS_APPLICATION_LIST).getBody();
    }

    public JsonNode retrieveApiDefinition(String applicationId) {
        return restOperations.getForObject(URI.create(baseUrl + "/apps/" + applicationId + "/definition"), JsonNode.class);
    }
}
