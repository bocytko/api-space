package org.zalando.apispace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zalando.apispace.twintip.ApplicationInformation;
import org.zalando.apispace.twintip.RestTemplateTwintipOperations;

@Component
public class ApiDefinitionCrawler {

    private static final Logger LOG = LoggerFactory.getLogger(ApiDefinitionCrawler.class);

    private final RestTemplateTwintipOperations twintipOperations;

    @Autowired
    public ApiDefinitionCrawler(RestTemplateTwintipOperations twintipOperations) {
        this.twintipOperations = twintipOperations;
    }

    @Scheduled(fixedDelayString = "${crawling.delay}")
    public void crawlApiDefinitions() {
        LOG.info("Start crawling api definitions");
        twintipOperations.retrieveAllApplications().stream()
                .filter(app -> "success".equalsIgnoreCase(app.getStatus()))
                .map(ApplicationInformation::getApplicationId)
                .forEach(appId -> {
                    // TODO: twintipOperations.retrieveApiDefinition(appId) and add to git repo if changed
                });
        LOG.info("Finished crawling api definitions");
    }
}
