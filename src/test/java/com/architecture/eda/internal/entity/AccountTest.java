package com.architecture.eda.internal.entity;

import com.architecture.eda.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class AccountTest extends UnitTest {

    @Test
    public void newAccount() {
        String name = "name";
        String email = "email@email.com";

        Client clientEntity = new Client();

        final var client = clientEntity.newCLient(name, email);

        final var account = new Account(client);

        Assertions.assertEquals(client.getId(), account.getClient().getId());
        Assertions.assertEquals(name, account.getClient().getName());
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void creditAndDebit(){
        String name = "name";
        String email = "email@email.com";

        Client clientEntity = new Client();

        final var client = clientEntity.newCLient(name, email);

        var account = new Account(client);

        account.credit(10);

        Assertions.assertEquals(10, account.getBalance());

        account.debit(8);
        Assertions.assertEquals(2, account.getBalance());
    }
}
