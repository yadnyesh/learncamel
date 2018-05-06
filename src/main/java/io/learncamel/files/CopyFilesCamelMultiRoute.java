package io.learncamel.files;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CopyFilesCamelMultiRoute {
	
	public static void main(String[] args) {
		CamelContext camelContext = new DefaultCamelContext();
		try {
			camelContext.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					from("file:data/input?noop=true")
					.to("log:?level=info&showBody=true&showHeaders=true")
					.to("file:data/output");
					
					from("file:data/input1?noop=true")
							.to("log:?level=info&showBody=true&showHeaders=true")
							.to("file:data/output1");
				}
			});
			camelContext.start();
			Thread.sleep(5000);
			camelContext.stop();
		} catch(Exception e) {
			System.out.println("Exception is : " + e);
			e.printStackTrace();
		}
	}
}
