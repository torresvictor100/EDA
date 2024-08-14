package com.architecture.eda.internal.entity;

import com.architecture.eda.UnitTest;
import com.architecture.eda.internal.exceptions.AccountAlready;
import com.architecture.eda.internal.exceptions.InvalidClientEmail;
import com.architecture.eda.internal.exceptions.InvalidClientName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;



@DataJpaTest
@ActiveProfiles("test")
public class ClientTest extends UnitTest {

    @Test
    public void newClient(){
        String name = "name";
        String email = "email@email.com";

        Client clientEntity = new Client();

        final var client =  clientEntity.newCLient(name, email);
        Assertions.assertEquals(name, client.getName());
        Assertions.assertEquals(email, client.getEmail());
        Assertions.assertNotNull(client.getCreateAt());
        Assertions.assertNotNull(client.getUpdateAt());

        Assertions.assertThrows(InvalidClientName.class, () -> {
            clientEntity.newCLient("", email);
        });

        Assertions.assertThrows(InvalidClientName.class, () -> {
            clientEntity.newCLient(null, email);
        });

        Assertions.assertThrows(InvalidClientEmail.class, () -> {
            clientEntity.newCLient(name, "");
        });

        Assertions.assertThrows(InvalidClientEmail.class, () -> {
            clientEntity.newCLient(name, null);
        });
    }
    @Test
    public void updateCLient(){
        String name = "name";
        String email = "email@email.com";

        Client clientEntity = new Client();

        final var client =  clientEntity.newCLient(name, email);

        String nameUpdate = "name";
        String emailUpdate = "email@email.com";

        client.updateCLient(nameUpdate, emailUpdate);

        Assertions.assertEquals(nameUpdate, client.getName());
        Assertions.assertEquals(emailUpdate, client.getEmail());
        Assertions.assertNotEquals(client.getCreateAt(),client.getUpdateAt());

        Assertions.assertThrows(InvalidClientName.class, () -> {
            clientEntity.updateCLient("", emailUpdate);
        });

        Assertions.assertThrows(InvalidClientName.class, () -> {
            clientEntity.updateCLient(null, emailUpdate);
        });

        Assertions.assertThrows(InvalidClientEmail.class, () -> {
            clientEntity.updateCLient(nameUpdate, "");
        });

        Assertions.assertThrows(InvalidClientEmail.class, () -> {
            clientEntity.updateCLient(nameUpdate, null);
        });
    }

    @Test
    public void addAccountCLient(){
        String name = "name";
        String email = "email@email.com";

        Client clientEntity = new Client();

        final var client =  clientEntity.newCLient(name, email);

        var account = new Account(client);

        client.addAccount(account);

        Assertions.assertEquals(1, client.getAccounts().size());

        Assertions.assertThrows(AccountAlready.class, () -> {
            client.addAccount(account);
        });
    }
}
