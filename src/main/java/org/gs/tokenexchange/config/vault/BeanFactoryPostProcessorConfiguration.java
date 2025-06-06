package org.gs.tokenexchange.config.vault;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

@Configuration
public class BeanFactoryPostProcessorConfiguration {
    @Bean
    public static BeanFactoryPostProcessor dependsOnPostProcessor() {
        return bf -> {
            String[] tws = bf.getBeanNamesForType(ServerProperties.class);
            Stream.of(tws)
                    .map(bf::getBeanDefinition)
                    .forEach(it -> it.setDependsOn("VaultDataImport"));
        };
    }
}
