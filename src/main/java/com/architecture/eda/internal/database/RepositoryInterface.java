package com.architecture.eda.internal.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryInterface<T, ID>  extends JpaRepository<T, ID> {
}
