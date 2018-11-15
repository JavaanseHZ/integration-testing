package de.contract.service;

import de.contract.service.api.ContractApi;
import de.contract.service.model.Contract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Random;

@RestController
@RequestMapping(value = "contract")
public class ContractRestController implements ContractApi {

    @RequestMapping(
            value = {"/contract"},
            produces = {"application/json"},
            consumes = {"application/json"},
            method = {RequestMethod.POST}
    )
    public ResponseEntity<Contract> calulateContract(@Valid @RequestBody Contract body) {
        calculatePremium(body);
        setConditions(body);
        return new ResponseEntity(body, HttpStatus.OK);
    }

    private void setConditions(Contract contract) {
        contract.setId(new Random().nextLong());
        switch(contract.getClients().size()) {
            case 1:
                contract.setRisk(Contract.RiskEnum.LOW);
                break;
            case 2:
                contract.setRisk(Contract.RiskEnum.MEDIUM);
                break;
            default:
                contract.setRisk(Contract.RiskEnum.HIGH);
        }
    }

    private void calculatePremium(Contract contract) {
        contract.setPremium(contract.getClients().size() * 200.0);
        if(contract.getClients().stream()
                .map(c -> c.getDateOfBirth())
                .anyMatch(date -> date.isAfter(LocalDate.of(2000, 1, 1)))) {
            contract.setPremium(contract.getPremium() * 1.5);
        }
    }
}
