package com.architecture.eda.internal.gateway.impl;

import com.architecture.eda.internal.database.account.db.AccountDB;
import com.architecture.eda.internal.database.account.model.AccountModel;
import com.architecture.eda.internal.entity.Account;
import com.architecture.eda.internal.gateway.AccountGateway;
import com.architecture.eda.internal.gateway.exception.NoFoundAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.architecture.eda.internal.entity.factory.AccountFactory.createAccount;
import static com.architecture.eda.internal.entity.factory.AccountFactory.createAccountModel;

@Service
public class AccountGatewayImpl implements AccountGateway {
    @Autowired
    private AccountDB accountDB;

    @Override
    public Account get(String uuid) {
        AccountModel accountModel = accountDB.findByUuid(uuid).orElseThrow(()
                -> new NoFoundAccountException("Account not found for UUID: " + uuid));
        return createAccount(accountModel);
    }

    @Override
    public Account save(Account account) {
        AccountModel accountModel = accountDB.save(createAccountModel(account));
        return createAccount(accountModel);
    }
    @Override
    public Account update(Account account) {
        AccountModel accountModel = accountDB.save(createAccountModel(account));
        return createAccount(accountModel);
    }
}
