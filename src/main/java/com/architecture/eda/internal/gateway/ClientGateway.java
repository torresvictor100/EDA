package com.architecture.eda.internal.gateway;

import com.architecture.eda.internal.entity.Client;

public interface ClientGateway {
    Client get(String uuid);
    Client save(Client client);
}
