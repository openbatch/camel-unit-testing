package org.openbatch.cameltests.configuration;

import org.openbatch.cameltests.processors.CsvProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean Registry.
 */
@Configuration
public class BeanRegistry {

  @Bean(name = ProcessorConstants.CSV_PROCESSOR)
  public CsvProcessor csvProcessor() {
    return new CsvProcessor();
  }
}
