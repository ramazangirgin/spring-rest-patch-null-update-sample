package com.girgin.ramazan.foo.application.controller.mapper;

import com.girgin.ramazan.foo.api.model.Foo;
import com.girgin.ramazan.foo.domain.model.FooEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FooEntityToDtoMapper {

    Foo map(FooEntity fooEntity);
}
