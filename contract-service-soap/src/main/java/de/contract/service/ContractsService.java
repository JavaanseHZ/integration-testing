package de.contract.service;

import de.contracts.contracts.CalculateContractRequest;
import de.contracts.contracts.CalculateContractResponse;
import de.contracts.contracts.Contract;
import de.contracts.contracts.ContractsPort;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;

@Service
public class ContractsService implements ContractsPort {

    private static DatatypeFactory datatypeFactory;
    static{
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Init Error!", e);
        }
    }


    @Override
    public CalculateContractResponse calculateContract(CalculateContractRequest calculateContractRequest) {
        Contract contract = calculateContractRequest.getContract();
        contract.setActive(true);
        contract.setSigned(datatypeFactory.newXMLGregorianCalendar(new GregorianCalendar()));
        CalculateContractResponse response = new CalculateContractResponse();
        response.setContract(contract);
        return response;
    }
}
