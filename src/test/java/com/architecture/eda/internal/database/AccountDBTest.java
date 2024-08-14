package com.architecture.eda.internal.database;

import com.architecture.eda.internal.database.account.db.AccountDB;
import com.architecture.eda.internal.database.account.model.AccountModel;
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
public class AccountDBTest {
    @Autowired
    private ClientDB clientDB;
    @Autowired
    private AccountDB accountDB;
    @Test
    public void accountDBSaveTest (){
        String name = "client";
        String email = "cliente@mail.com";

        ClientModel client = new ClientModel(UUID.randomUUID().toString()
                , name, email, LocalDateTime.now(), LocalDateTime.now());

        client = clientDB.save(client);

        Assertions.assertNotNull(client);

        AccountModel account = new AccountModel(UUID.randomUUID().toString(),client
                , 100, LocalDateTime.now(), LocalDateTime.now());

        account = accountDB.save(account);

        Assertions.assertNotNull(account);
        Assertions.assertEquals(account.getBalance(), 100);
        Assertions.assertEquals(account.getClient().getUuid(), client.getUuid());
    }
    @Test
    public void accountDBFoundTest (){
        String name = "client";
        String email = "cliente@mail.com";

        ClientModel client = new ClientModel(UUID.randomUUID().toString()
                , name, email, LocalDateTime.now(), LocalDateTime.now());

        client = clientDB.save(client);

        Assertions.assertNotNull(client);

        AccountModel account = new AccountModel(UUID.randomUUID().toString(),client
                , 100, LocalDateTime.now(), LocalDateTime.now());

        account = accountDB.save(account);
        AccountModel accountFound = accountDB.findByUuid(account.getUuid()).get();

        Assertions.assertNotNull(accountFound);
        Assertions.assertEquals(accountFound.getBalance(), 100);
        Assertions.assertEquals(accountFound.getClient().getUuid(), client.getUuid());
    }
}
