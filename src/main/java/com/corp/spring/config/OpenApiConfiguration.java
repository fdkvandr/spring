package com.corp.spring.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
    name = "oauth2",
    type = SecuritySchemeType.OAUTH2,
    flows = @OAuthFlows(
            authorizationCode = @OAuthFlow(
                    authorizationUrl = "/oauth2/authorization/keycloak",
                    tokenUrl = "${spring.security.oauth2.client.provider.keycloak.token-uri}"
            )
    )
)
public class OpenApiConfiguration {

}
