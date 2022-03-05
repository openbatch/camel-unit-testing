package org.openbatch.cameltests.processors;

import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Generic CSV Processor.
 * Expects a Map of Strings in the Exchange Body.
 */
public class CsvProcessor implements Processor {

  @SuppressWarnings("unchecked")
  @Override
  public void process(Exchange exchange) {

    Map<String, String> row = exchange.getMessage().getBody(Map.class);
    exchange.getMessage().setHeader("RowId", row.getOrDefault("Id", "0"));
    exchange.getMessage().setBody(row.getOrDefault("Name", "None"));
  }
}
