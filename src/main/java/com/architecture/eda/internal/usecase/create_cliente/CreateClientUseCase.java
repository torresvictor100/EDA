package com.architecture.eda.internal.usecase.create_cliente;

import com.architecture.eda.internal.gateway.ClientGateway;
import com.architecture.eda.internal.entity.Client;
import com.architecture.eda.internal.usecase.create_cliente.dtos.CreateClientOutputDTO;
import com.architecture.eda.internal.usecase.create_cliente.dtos.CreateClientInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.architecture.eda.internal.usecase.create_cliente.factory.CreateClientDTOs.createClientOutput;

@Service
public class CreateClientUseCase {

    @Autowired
    private ClientGateway clientGateway;

    public CreateClientUseCase(ClientGateway clientGateway) {
        this.clientGateway = clientGateway;
    }

    public CreateClientOutputDTO executed(CreateClientInputDTO client) {
        Client clientEntity = new Client().newCLient(client.name(), client.email());
        clientGateway.save(clientEntity);
        return createClientOutput(clientEntity);
    }


}
