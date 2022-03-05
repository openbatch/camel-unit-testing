package org.openbatch.cameltests.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.openbatch.cameltests.configuration.ProcessorConstants;
import org.springframework.stereotype.Component;

/**
 * Csv File Route.
 */
@Component
public class CsvRoute extends RouteBuilder {
  @Override
  public void configure() {

    var dataFormat = new CsvDataFormat();
    dataFormat.setDelimiter('|');
    dataFormat.setUseOrderedMaps(true);
    dataFormat.setHeaderDisabled(false);

    from("file:data?move=completed")
        .routeId("csv-route")
        .autoStartup(false)
        .marshal(dataFormat)
        .split(body())
        .log("Processing Row!")
        .bean(ProcessorConstants.CSV_PROCESSOR)
        .end();



  }
}
