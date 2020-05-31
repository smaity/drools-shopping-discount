package com.smaity.prj.drools.config;
/**
 * Configuration for Drools rule engine File
 * <p>
 * Rule engine definition. Rule file will be gathered from a specific rules directory
 * and then loaded in memory. New Rules can be added just by adding another DRL rule
 * file in configuration directory. This will help in correcting rules just by updating file
 * and restarting the application.
 *
 * @Author: Sajal Maity
 * @Date: 05/31/2020
 */

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.stream.Stream;

@Configuration
public class DiscountRuleConfiguration {

    private static Logger logger = LoggerFactory.getLogger(DiscountRuleConfiguration.class);
    public static final String RULE_LOCATION = "service.rule.location";

    @Autowired
    Environment env;

    @Bean
    public KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        String ruleLocation = env.getProperty(RULE_LOCATION);
        if (!StringUtils.isEmpty(ruleLocation)) {
            File ruleDir = new File(ruleLocation);
            File[] files = ruleDir.listFiles((dir, name) -> name.toLowerCase().endsWith("drl"));
            Stream.of(files).forEach(drlFile -> {
                logger.info("Found DRL file: {}", drlFile.getAbsolutePath());
                kieFileSystem.write(ResourceFactory.newFileResource(drlFile));
            });
        }

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        for (Message message : kieBuilder.getResults().getMessages()) {
            logger.error("Error Message: ({}) {}", message.getPath(), message.getText());
        }
        KieModule kieModule = kieBuilder.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }
}
