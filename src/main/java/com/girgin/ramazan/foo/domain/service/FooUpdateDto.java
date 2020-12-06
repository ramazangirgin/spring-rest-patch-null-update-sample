package com.girgin.ramazan.foo.domain.service;

public class FooUpdateDto {
    private final Long id;
    private final String name;
    private final NullableOptional<Long> externalId;

    public FooUpdateDto(Long id,
                        String name,
                        NullableOptional externalId) {
        this.id = id;
        this.name = name;
        this.externalId = externalId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public NullableOptional<Long> getExternalId() {
        return externalId;
    }
}
