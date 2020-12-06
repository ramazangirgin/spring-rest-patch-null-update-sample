package com.girgin.ramazan.foo.application.controller;

import com.girgin.ramazan.foo.domain.model.FooEntity;
import com.girgin.ramazan.foo.domain.repository.FooRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MockFooRepository implements FooRepository {

    private final Map<Long, FooEntity> foos = new HashMap<>();

    @PostConstruct
    public void init() {
        foos.put(1L, new FooEntity(1L, "Foo1", 10001L));
        foos.put(2L, new FooEntity(2L, "Foo2", 10002L));
        foos.put(3L, new FooEntity(3L, "Foo3", 10002L));
        foos.put(4L, new FooEntity(4L, "Foo4", null));
    }

    @Override
    public Optional<FooEntity> findById(Long id) {
        return Optional.ofNullable(foos.getOrDefault(id, null));
    }

    @Override
    public FooEntity save(FooEntity fooEntity) {
        foos.put(fooEntity.getId(), fooEntity);
        return fooEntity;
    }
}
