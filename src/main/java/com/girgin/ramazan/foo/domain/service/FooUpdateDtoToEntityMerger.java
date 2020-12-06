package com.girgin.ramazan.foo.domain.service;

import com.girgin.ramazan.foo.domain.model.FooEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FooUpdateDtoToEntityMerger {
    public FooEntity merge(FooEntity fooEntity, FooUpdateDto fooUpdateDto) {
        fooUpdateDto.getExternalId()
                .ifPresent(fooEntity::setExternalId);
        Optional.ofNullable(fooUpdateDto.getName())
                .ifPresent(fooEntity::setName);
        return fooEntity;
    }
}
