package org.openbatch.cameltests.processors;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Test Class for Csv Processor.
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CsvProcessorTest {

  @EndpointInject("direct:csv-input")
  ProducerTemplate template;

  @EndpointInject("mock:csv-output")
  MockEndpoint output;

  @Test
  @DirtiesContext
  void testCsvProcessor() {
    Map<String, String> row = new HashMap<>();
    row.put("Id", "1");
    row.put("Name", "Jim Smith");

    var exchange = ExchangeBuilder.anExchange(template.getCamelContext())
        .withBody(row)
        .build();
    template.send(exchange);

    assertThat(output.getExchanges()).as("Output should not be empty").isNotEmpty();

    var msg = output.getExchanges().get(0);

    assertThat(msg.getMessage().getHeader("RowId"))
        .as("Row Id should be in the Header")
        .isEqualTo("1");
    assertThat(msg.getMessage().getBody())
        .as("Body should be the name")
        .hasToString("Jim Smith");
  }
}
