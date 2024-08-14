package com.architecture.eda.internal.usecase.create_cliente.factory;

import com.architecture.eda.internal.entity.Client;
import com.architecture.eda.internal.usecase.create_cliente.dtos.CreateClientOutputDTO;

public class CreateClientDTOs {
    public static CreateClientOutputDTO createClientOutput(Client client){
        return new CreateClientOutputDTO(client.getUuid(), client.getName(), client.getEmail()
                , client.getCreateAt(), client.getUpdateAt());
    }
}
