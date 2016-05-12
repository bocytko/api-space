package org.zalando.apispace.twintip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.zalando.stups.oauth2.spring.client.StupsOAuth2RestTemplate;
import org.zalando.stups.oauth2.spring.client.StupsTokensAccessTokenProvider;
import org.zalando.stups.spring.http.client.ClientHttpRequestFactorySelector;
import org.zalando.stups.spring.http.client.TimeoutConfig;
import org.zalando.stups.tokens.AccessTokens;

@Configuration
public class TwintipClientConfiguration {

    @Autowired
    private AccessTokens accessTokens;

    @Bean
    public RestTemplateTwintipOperations twintipOperations(@Value("${twintip.url}") String twintipBaseUrl) {
        return new RestTemplateTwintipOperations(buildOAuth2RestTemplate("twintip"), twintipBaseUrl);
    }

    private RestOperations buildOAuth2RestTemplate(final String tokenName) {
        return new StupsOAuth2RestTemplate(new StupsTokensAccessTokenProvider(tokenName, accessTokens),
                ClientHttpRequestFactorySelector.getRequestFactory(new TimeoutConfig.Builder()
                        .withReadTimeout(5000)
                        .withConnectTimeout(2000)
                        .withConnectionRequestTimeout(1000)
                        .build()));
    }
}
