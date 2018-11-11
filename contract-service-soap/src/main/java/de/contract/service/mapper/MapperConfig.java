package de.contract.service.mapper;

import de.contracts.contracts.CalculateContractRequest;
import de.contracts.contracts.CalculateContractResponse;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public MapperFactory mapperFactory() {
        return new DefaultMapperFactory.Builder().build();
    }


    @Bean
    public BoundMapperFacade<CalculateContractRequest, CalculateContractResponse> mapperReqResp(MapperFactory mapperFactory) {
        return mapperFactory.getMapperFacade(CalculateContractRequest.class, CalculateContractResponse.class);
    }
}
