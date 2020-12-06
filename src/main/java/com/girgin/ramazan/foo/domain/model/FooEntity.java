package com.girgin.ramazan.foo.domain.model;

import java.util.Objects;

public class FooEntity {
    private Long id;
    private String name;
    private Long externalId;

    public Long getId() {
        return id;
    }

    public FooEntity(Long id, String name, Long externalId) {
        this.id = id;
        this.name = name;
        this.externalId = externalId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FooEntity fooEntity = (FooEntity) o;
        return id.equals(fooEntity.id) &&
                name.equals(fooEntity.name) &&
                externalId.equals(fooEntity.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, externalId);
    }

    @Override
    public String toString() {
        return "FooEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", externalId=" + externalId +
                '}';
    }
}
