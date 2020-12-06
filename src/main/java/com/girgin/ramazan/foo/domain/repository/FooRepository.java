package com.girgin.ramazan.foo.domain.repository;

import com.girgin.ramazan.foo.domain.model.FooEntity;

import java.util.Optional;

public interface FooRepository {
    Optional<FooEntity> findById(Long id);

    FooEntity save(FooEntity fooEntity);
}
