package com.architecture.eda.internal.web;

import com.architecture.eda.internal.usecase.create_cliente.CreateClientUseCase;
import com.architecture.eda.internal.usecase.create_cliente.dtos.CreateClientInputDTO;
import com.architecture.eda.internal.usecase.create_cliente.dtos.CreateClientOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientHandler {

    private final CreateClientUseCase createClientUseCase;

    @Autowired
    public ClientHandler(CreateClientUseCase createClientUseCase) {
        this.createClientUseCase = createClientUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<CreateClientOutputDTO> createClient(@RequestBody CreateClientInputDTO createClientInputDTO) {
        try {
            CreateClientOutputDTO createClientOutputDTO = createClientUseCase.executed(createClientInputDTO);
            return new ResponseEntity<>(createClientOutputDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
