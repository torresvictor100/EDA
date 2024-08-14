package com.architecture.eda.internal.gateway.impl;

import com.architecture.eda.internal.database.client.db.ClientDB;
import com.architecture.eda.internal.database.client.model.ClientModel;
import com.architecture.eda.internal.entity.Client;
import com.architecture.eda.internal.gateway.ClientGateway;
import com.architecture.eda.internal.gateway.exception.NoFoundAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.architecture.eda.internal.entity.factory.ClientFactory.createClient;
import static com.architecture.eda.internal.entity.factory.ClientFactory.createClientModel;

@Service
public class ClientGatewayImpl implements ClientGateway {
    @Autowired
    private ClientDB clientDB;
    @Override
    public Client get(String uuid) {
        ClientModel clientModel = clientDB.findByUuid(uuid).orElseThrow(()
                -> new NoFoundAccountException("Account not found for UUID: " + uuid));
        return createClient(clientModel);
    }

    @Override
    public Client save(Client client) {
        ClientModel clientModel = clientDB.save(createClientModel(client));
        return createClient(clientModel);
    }
}
