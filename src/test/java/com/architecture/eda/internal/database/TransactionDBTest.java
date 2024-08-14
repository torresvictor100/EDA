package com.architecture.eda.internal.database;

import com.architecture.eda.internal.database.account.db.AccountDB;
import com.architecture.eda.internal.database.account.model.AccountModel;
import com.architecture.eda.internal.database.client.db.ClientDB;
import com.architecture.eda.internal.database.client.model.ClientModel;
import com.architecture.eda.internal.database.transaction.db.TransctionDB;
import com.architecture.eda.internal.database.transaction.model.TransactionModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.UUID;

@DataJpaTest
@ActiveProfiles("test")
public class TransactionDBTest {
    @Autowired
    private ClientDB clientDB;
    @Autowired
    private AccountDB accountDB;
    @Autowired
    private TransctionDB transctionDB;
    @Test
    public void transactionSaveTest (){
        String nameFrom = "clientFrom";
        String emailFrom = "clienteFrom@mail.com";
        ClientModel clientFrom = new ClientModel(UUID.randomUUID().toString()
                , nameFrom, emailFrom, LocalDateTime.now(), LocalDateTime.now());
        clientFrom = clientDB.save(clientFrom);
        Assertions.assertNotNull(clientFrom);
        AccountModel accountFrom = new AccountModel(UUID.randomUUID().toString(),clientFrom
                , 100, LocalDateTime.now(), LocalDateTime.now());
        accountFrom = accountDB.save(accountFrom);
        Assertions.assertNotNull(accountFrom);

        String nameTo = "clientTo";
        String emailTo = "clienteTo@mail.com";
        ClientModel clientTo = new ClientModel(UUID.randomUUID().toString()
                , nameTo, emailTo, LocalDateTime.now(), LocalDateTime.now());
        clientTo = clientDB.save(clientTo);
        Assertions.assertNotNull(clientTo);
        AccountModel accountTo = new AccountModel(UUID.randomUUID().toString(),clientTo
                , 0, LocalDateTime.now(), LocalDateTime.now());
        accountTo = accountDB.save(accountTo);
        Assertions.assertNotNull(accountTo);

        TransactionModel transaction = new TransactionModel(UUID.randomUUID().toString()
                ,accountFrom, accountTo, 100, LocalDateTime.now());

        transaction = transctionDB.save(transaction);
        Assertions.assertNotNull(transaction);
        Assertions.assertEquals(clientFrom.getName(), transaction.getAccountFrom().getClient().getName());
        Assertions.assertEquals(clientTo.getName(), transaction.getAccountTo().getClient().getName());
        Assertions.assertEquals(100, transaction.getAmount());
    }

    @Test
    public void transactionFoundTest (){
        String nameFrom = "clientFrom";
        String emailFrom = "clienteFrom@mail.com";
        ClientModel clientFrom = new ClientModel(UUID.randomUUID().toString()
                , nameFrom, emailFrom, LocalDateTime.now(), LocalDateTime.now());
        clientFrom = clientDB.save(clientFrom);
        Assertions.assertNotNull(clientFrom);
        AccountModel accountFrom = new AccountModel(UUID.randomUUID().toString(),clientFrom
                , 100, LocalDateTime.now(), LocalDateTime.now());
        accountFrom = accountDB.save(accountFrom);
        Assertions.assertNotNull(accountFrom);

        String nameTo = "clientTo";
        String emailTo = "clienteTo@mail.com";
        ClientModel clientTo = new ClientModel(UUID.randomUUID().toString()
                , nameTo, emailTo, LocalDateTime.now(), LocalDateTime.now());
        clientTo = clientDB.save(clientTo);
        Assertions.assertNotNull(clientTo);
        AccountModel accountTo = new AccountModel(UUID.randomUUID().toString(),clientTo
                , 0, LocalDateTime.now(), LocalDateTime.now());
        accountTo = accountDB.save(accountTo);
        Assertions.assertNotNull(accountTo);

        TransactionModel transaction = new TransactionModel(UUID.randomUUID().toString()
                ,accountFrom, accountTo, 100, LocalDateTime.now());

        transaction = transctionDB.save(transaction);
        TransactionModel transactionFound = transctionDB.findByUuid(transaction.getUuid()).get();

        Assertions.assertNotNull(transactionFound);
        Assertions.assertEquals(clientFrom.getName(), transactionFound.getAccountFrom().getClient().getName());
        Assertions.assertEquals(clientTo.getName(), transactionFound.getAccountTo().getClient().getName());
        Assertions.assertEquals(100, transactionFound.getAmount());
    }
}
