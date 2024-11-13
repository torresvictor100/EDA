package com.architecture.eda.internal.web;

import com.architecture.eda.internal.usecase.create_account.dtos.CreateAccountInputDTO;
import com.architecture.eda.internal.usecase.create_account.dtos.CreateAccountOutputDTO;
import com.architecture.eda.internal.usecase.create_transation.CreateTransationUseCase;
import com.architecture.eda.internal.usecase.create_transation.dtos.CreateTransactionInputDTO;
import com.architecture.eda.internal.usecase.create_transation.dtos.CreateTransationOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transation")
public class TransationHandle {
    private final CreateTransationUseCase createTransationUseCase;

    @Autowired
    public TransationHandle(CreateTransationUseCase createTransationUseCase) {
        this.createTransationUseCase = createTransationUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<CreateTransationOutputDTO> createClient(@RequestBody CreateTransactionInputDTO createTransactionInputDTO) {
        try {
            CreateTransationOutputDTO createTransationOutputDTO = createTransationUseCase.executed(createTransactionInputDTO);
            return new ResponseEntity<>(createTransationOutputDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
