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
import java.time.OffsetDateTime;

@RestController
public class ContractRestController implements ContractApi {

    @RequestMapping(
            value = {"/contract"},
            produces = {"application/json"},
            consumes = {"application/json"},
            method = {RequestMethod.POST}
    )
    public ResponseEntity<Contract> calulateContract(@Valid @RequestBody Contract body) {
        body.active(true).signed(OffsetDateTime.now());
        return new ResponseEntity(body, HttpStatus.OK);
    }
}
