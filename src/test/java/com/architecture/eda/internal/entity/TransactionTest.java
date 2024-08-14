package com.architecture.eda.internal.entity;

import com.architecture.eda.internal.exceptions.InsufficienteFoundsError;
import com.architecture.eda.internal.exceptions.InvalidAmount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class TransactionTest {

    @Test
    public void newTransaction() {
        Client clientEntity = new Client();

        String nameFrom = "nameFrom";
        String emailFrom = "emailFrom@email.com";
        final var clientFrom = clientEntity.newCLient(nameFrom, emailFrom);
        final var accountFrom = new Account(clientFrom);
        accountFrom.credit(101);
        Assertions.assertNotNull(accountFrom);

        String nameTo = "nameTo";
        String emailTo = "emailTo@email.com";
        final var clientTo = clientEntity.newCLient(nameTo, emailTo);
        final var accountTo = new Account(clientTo);
        Assertions.assertNotNull(accountTo);


        Transaction transaction = new Transaction()
                .newTransaction(accountFrom, accountTo, 50);

        Assertions.assertNotNull(transaction);
        Assertions.assertEquals(transaction.getAccountFrom().getBalance(), 51);
        Assertions.assertEquals(transaction.getAccountTo().getBalance(), 50);
    }

    @Test
    public void newTransactionError() {
        Client clientEntity = new Client();

        String nameFrom = "nameFrom";
        String emailFrom = "emailFrom@email.com";
        final var clientFrom = clientEntity.newCLient(nameFrom, emailFrom);
        final var accountFrom = new Account(clientFrom);
        Assertions.assertNotNull(accountFrom);

        String nameTo = "nameTo";
        String emailTo = "emailTo@email.com";
        final var clientTo = clientEntity.newCLient(nameTo, emailTo);
        final var accountTo = new Account(clientTo);
        Assertions.assertNotNull(accountTo);

        Assertions.assertThrows(InsufficienteFoundsError.class, () -> {
            new Transaction().newTransaction(accountFrom, accountTo, 50);
        });

        Assertions.assertThrows(InvalidAmount.class, () -> {
            new Transaction().newTransaction(accountFrom, accountTo, -1);
        });

    }
}
