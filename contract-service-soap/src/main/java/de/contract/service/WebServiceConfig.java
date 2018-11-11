package de.contract.service;

import de.contracts.contracts.ContractsPortService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.xml.ws.Endpoint;
import java.io.IOException;


@Configuration
public class WebServiceConfig {

    private static final String SERVICE_NAME_URL_PATH = "/contracts";

    @Value("classpath:wsdl/contract-service-soap.wsdl")
    private Resource wsdlResource;


    @Bean(name=Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }


    @Bean
    public ContractsPortService contractsPortService() throws IOException {
        return new ContractsPortService(wsdlResource.getURL());
    }

    @Bean
    public Endpoint endpoint(ContractsService contractsService) throws IOException {
        EndpointImpl endpoint = new EndpointImpl(springBus(), contractsService);
        endpoint.setServiceName(contractsPortService().getServiceName());
        endpoint.setWsdlLocation(wsdlResource.getURL().toString());
        endpoint.publish(SERVICE_NAME_URL_PATH);
        return endpoint;
    }
}