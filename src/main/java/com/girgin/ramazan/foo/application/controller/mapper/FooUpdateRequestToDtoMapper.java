package com.girgin.ramazan.foo.application.controller.mapper;

import com.girgin.ramazan.foo.api.model.FooUpdateRequest;
import com.girgin.ramazan.foo.domain.service.FooUpdateDto;
import com.girgin.ramazan.foo.domain.service.NullableOptional;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.stereotype.Component;

@Component
public class FooUpdateRequestToDtoMapper {

    public FooUpdateDto map(Long id, FooUpdateRequest fooUpdateRequest) {
        return new FooUpdateDto(id, fooUpdateRequest.getName(), mapExternalId(fooUpdateRequest.getExternalId()));
    }

    private NullableOptional<Long> mapExternalId(JsonNullable<Long> externalIdJsonNullable) {
        if (externalIdJsonNullable.equals(JsonNullable.undefined())) {
            return NullableOptional.undefined();
        }
        return NullableOptional.of(externalIdJsonNullable.get());
    }
}
