package com.architecture.eda.internal.web;

import com.architecture.eda.internal.usecase.create_account.CreateAccountUseCase;
import com.architecture.eda.internal.usecase.create_account.dtos.CreateAccountInputDTO;
import com.architecture.eda.internal.usecase.create_account.dtos.CreateAccountOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountHandle {

    private final CreateAccountUseCase createAccountUseCase;

    @Autowired
    public AccountHandle(CreateAccountUseCase createAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<CreateAccountOutputDTO> createClient(@RequestBody CreateAccountInputDTO createAccountInputDTO) {
        try {
            CreateAccountOutputDTO createAccountOutputDTO = createAccountUseCase.executed(createAccountInputDTO);
            return new ResponseEntity<>(createAccountOutputDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
