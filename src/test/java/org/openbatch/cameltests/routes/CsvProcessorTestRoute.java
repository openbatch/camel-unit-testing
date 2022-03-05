package org.openbatch.cameltests.routes;

import org.apache.camel.builder.RouteBuilder;
import org.openbatch.cameltests.configuration.ProcessorConstants;
import org.springframework.stereotype.Component;

/**
 * Test route for the CSV Processor.
 */
@Component
public class CsvProcessorTestRoute extends RouteBuilder {
  @Override
  public void configure() {

    from("direct:csv-input")
        .routeId("csv-processor-test-route")
        .bean(ProcessorConstants.CSV_PROCESSOR)
        .to("mock:csv-output")
        .end();

  }
}
