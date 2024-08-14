package com.architecture.eda.internal.entity.factory;

import com.architecture.eda.internal.database.client.model.ClientModel;
import com.architecture.eda.internal.entity.Client;

public class ClientFactory {
    public static Client createClient(ClientModel clientModel){
        Client client = new Client();
        client.setId(client.getId());
        client.setUuid(clientModel.getUuid());
        client.setName(clientModel.getName());
        client.setEmail(clientModel.getEmail());
        client.setCreateAt(clientModel.getCreateAt());
        client.setUpdateAt(clientModel.getUpdateAt());
        return client;
    }

    public static ClientModel createClientModel(Client client){
        ClientModel clientModel = new ClientModel();
        clientModel.setUuid(clientModel.getUuid());
        clientModel.setName(clientModel.getName());
        clientModel.setEmail(clientModel.getEmail());
        clientModel.setCreateAt(clientModel.getCreateAt());
        clientModel.setUpdateAt(clientModel.getUpdateAt());
        return clientModel;
    }
}
