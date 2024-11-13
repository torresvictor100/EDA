package com.architecture.eda.internal.entity.factory;

import com.architecture.eda.internal.database.account.model.AccountModel;
import com.architecture.eda.internal.entity.Account;

import static com.architecture.eda.internal.entity.factory.ClientFactory.createClient;
import static com.architecture.eda.internal.entity.factory.ClientFactory.createClientModel;

public class AccountFactory {

    public static Account createAccount(AccountModel accountModel){
        Account account = new Account();
        account.setId(accountModel.getId());
        account.setUuid(accountModel.getUuid());
        account.setBalance(accountModel.getBalance());
        account.setClient(createClient(accountModel.getClient()));
        account.setCreateAt(accountModel.getCreateAt());
        account.setUpdateAt(accountModel.getUpdateAt());

        return account;
    }

    public static AccountModel createAccountModel(Account account){
        AccountModel accountModel = new AccountModel();
        accountModel.setId(account.getId());
        accountModel.setUuid(account.getUuid());
        accountModel.setBalance(account.getBalance());
        accountModel.setClient(createClientModel(account.getClient()));
        accountModel.setCreateAt(account.getCreateAt());
        accountModel.setUpdateAt(account.getUpdateAt());
        return accountModel;
    }
}
