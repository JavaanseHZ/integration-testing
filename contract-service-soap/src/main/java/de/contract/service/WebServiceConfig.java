package de.contract.service;

import de.contracts.contracts.ContractsPortService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;


@Configuration
public class WebServiceConfig {

    private static final String SERVICE_NAME_URL_PATH = "/contracts";

    @Bean(name=Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }


    @Bean
    public ContractsPortService contractsPortService() {
        return new ContractsPortService();
    }

    @Bean
    public Endpoint endpoint(ContractsService contractsService) {
        EndpointImpl endpoint = new EndpointImpl(springBus(), contractsService);
        endpoint.setServiceName(contractsPortService().getServiceName());
        endpoint.setWsdlLocation(contractsPortService().getWSDLDocumentLocation().toString());
        endpoint.publish(SERVICE_NAME_URL_PATH);
        return endpoint;
    }
}