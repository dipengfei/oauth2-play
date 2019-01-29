package me.danielpf.oauth2play.resclient;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@SpringBootApplication
public class ResClientApplication {
    public static void main(String[] args) {

        new SpringApplicationBuilder(ResClientApplication.class).web(WebApplicationType.NONE)
                                                                .build().run(args);
    }


    @Bean
    OAuth2ProtectedResourceDetails resource() {
        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();

        resource.setClientId("client_1");
        resource.setClientSecret("123456");
        resource.setAccessTokenUri("http://localhost:8080/oauth/token");
        resource.setGrantType("client_credentials");

        return resource;
    }

    @Bean
    public OAuth2RestOperations restTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
    }


    @Bean
    ApplicationRunner runner(OAuth2RestOperations operations) {
        return args -> {
            System.out.println(operations.getAccessToken());
            System.out.println(operations.getForEntity("http://localhost:8080/res", String.class).getBody());
        };
    }


}
