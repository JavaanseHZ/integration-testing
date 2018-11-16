package de.contract.service;

import de.contracts.contracts.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.Random;

@Controller
public class ContractsService implements ContractsPort {

    @Override
    public CalculateContractResponse calculateContract(CalculateContractRequest calculateContractRequest) {
        Contract contract = calculateContractRequest.getContract();
        setConditions(contract);
        calculatePremium(contract);
        CalculateContractResponse response = new CalculateContractResponse();
        response.setContract(contract);
        return response;
    }

    private void setConditions(Contract contract) {
        contract.setId(new Random().nextLong());
        switch(contract.getClients().size()) {
            case 1:
                contract.setRisk(Risk.LOW);
                break;
            case 2:
                contract.setRisk(Risk.MEDIUM);
                break;
            default:
                contract.setRisk(Risk.HIGH);
        }
    }

    private void calculatePremium(Contract contract) {
        contract.setPremium(contract.getClients().size() * 200.0);
        if(contract.getClients().stream()
                .map(c -> c.getDateOfBirth())
                .anyMatch(date -> date.toGregorianCalendar().toZonedDateTime().toLocalDate().isAfter(LocalDate.of(2000, 1, 1)))) {
            contract.setPremium(contract.getPremium() * 1.5);
        }
    }
}
