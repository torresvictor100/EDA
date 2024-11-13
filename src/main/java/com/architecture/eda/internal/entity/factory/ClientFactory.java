package com.architecture.eda.internal.entity.factory;

import com.architecture.eda.internal.database.client.model.ClientModel;
import com.architecture.eda.internal.entity.Client;

public class ClientFactory {
    public static Client createClient(ClientModel clientModel){
        Client client = new Client();
        client.setId(clientModel.getId());
        client.setUuid(clientModel.getUuid());
        client.setName(clientModel.getName());
        client.setEmail(clientModel.getEmail());
        client.setCreateAt(clientModel.getCreateAt());
        client.setUpdateAt(clientModel.getUpdateAt());
        return client;
    }

    public static ClientModel createClientModel(Client client){
        ClientModel clientModel = new ClientModel();
        clientModel.setId(client.getId());
        clientModel.setUuid(client.getUuid());
        clientModel.setName(client.getName());
        clientModel.setEmail(client.getEmail());
        clientModel.setCreateAt(client.getCreateAt());
        clientModel.setUpdateAt(client.getUpdateAt());
        return clientModel;
    }
}
