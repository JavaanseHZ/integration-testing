package de.contract.service;

import de.contracts.contracts.CalculateContractRequest;
import de.contracts.contracts.CalculateContractResponse;
import de.contracts.contracts.ContractsPort;
import ma.glasnost.orika.BoundMapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractsService implements ContractsPort {

    @Autowired
    private BoundMapperFacade<CalculateContractRequest, CalculateContractResponse> mapperReqResp;

    @Override
    public CalculateContractResponse calculateContract(CalculateContractRequest calculateContractRequest) {
        return mapperReqResp.map(calculateContractRequest);
    }
}
