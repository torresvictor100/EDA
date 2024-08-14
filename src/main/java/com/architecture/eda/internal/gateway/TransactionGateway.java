package com.architecture.eda.internal.gateway;

import com.architecture.eda.internal.entity.Transaction;

public interface TransactionGateway {
    Transaction create(Transaction transaction);
}
