package com.architecture.eda.internal.gateway;

import com.architecture.eda.internal.entity.Account;

public interface AccountGateway {
    Account get(String uuid);
    Account save(Account account);
}
