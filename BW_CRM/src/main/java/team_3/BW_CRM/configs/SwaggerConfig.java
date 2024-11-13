package team_3.BW_CRM.configs;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }

    // @Tag(name = "Indirizzo", description = "Gestione degli indirizzi")
    // @Operation(summary = "Ottieni tutti gli indirizzi", description = "Ritorna una lista di tutti gli indirizzi presenti nel sistema")
}
