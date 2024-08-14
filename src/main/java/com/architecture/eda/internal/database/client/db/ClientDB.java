package com.architecture.eda.internal.database.client.db;

import com.architecture.eda.internal.database.RepositoryInterface;
import com.architecture.eda.internal.database.client.model.ClientModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientDB extends RepositoryInterface<ClientModel, Long> {

    Optional<ClientModel> findByUuid(String uuid);
}
