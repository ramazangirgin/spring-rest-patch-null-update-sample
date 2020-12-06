package com.girgin.ramazan.foo.domain.service;

import com.girgin.ramazan.foo.domain.model.FooEntity;
import com.girgin.ramazan.foo.domain.repository.FooRepository;
import org.springframework.stereotype.Service;

@Service
public class FooService {
    private final FooUpdateDtoToEntityMerger fooUpdateDtoToEntityMerger;
    private final FooRepository fooRepository;

    public FooService(FooUpdateDtoToEntityMerger fooUpdateDtoToEntityMerger,
                      FooRepository fooRepository) {
        this.fooUpdateDtoToEntityMerger = fooUpdateDtoToEntityMerger;
        this.fooRepository = fooRepository;
    }

    public FooEntity updateProject(FooUpdateDto fooUpdateDto) {
        FooEntity fooEntity = fooRepository.findById(fooUpdateDto.getId())
                .orElseThrow(() -> new RuntimeException("Foo not found"));
        FooEntity mergedFooEntity = fooUpdateDtoToEntityMerger.merge(fooEntity, fooUpdateDto);
        return fooRepository.save(mergedFooEntity);
    }
}
