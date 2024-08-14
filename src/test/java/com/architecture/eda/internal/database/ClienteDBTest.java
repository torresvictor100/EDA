package com.architecture.eda.internal.database;

import com.architecture.eda.internal.database.client.db.ClientDB;
import com.architecture.eda.internal.database.client.model.ClientModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.UUID;

@DataJpaTest
@ActiveProfiles("test")
public class ClienteDBTest {

    @Autowired
    private ClientDB clientDB;

    @Test
    public void clientDBSaveTest (){
        String name = "client";
        String email = "cliente@mail.com";

        ClientModel client = new ClientModel(UUID.randomUUID().toString()
                , name, email, LocalDateTime.now(), LocalDateTime.now());

        client = clientDB.save(client);

        Assertions.assertNotNull(client);
        Assertions.assertEquals(name, client.getName());
        Assertions.assertEquals(email, client.getEmail());
    }

    @Test
    public void clientDBFindTest (){

        String name = "client";
        String email = "cliente@mail.com";

        ClientModel client = new ClientModel(UUID.randomUUID().toString()
                , name, email, LocalDateTime.now(), LocalDateTime.now());

        client = clientDB.save(client);

        ClientModel clientFound = clientDB.findByUuid(client.getUuid()).get();

        Assertions.assertNotNull(clientFound);
        Assertions.assertEquals(name, clientFound.getName());
        Assertions.assertEquals(email, clientFound.getEmail());
    }
}
