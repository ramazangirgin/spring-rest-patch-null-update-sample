package com.girgin.ramazan.foo.application.controller;

import com.girgin.ramazan.foo.api.FoosApi;
import com.girgin.ramazan.foo.api.model.Foo;
import com.girgin.ramazan.foo.api.model.FooUpdateRequest;
import com.girgin.ramazan.foo.application.controller.mapper.FooEntityToDtoMapper;
import com.girgin.ramazan.foo.application.controller.mapper.FooUpdateRequestToDtoMapper;
import com.girgin.ramazan.foo.domain.model.FooEntity;
import com.girgin.ramazan.foo.domain.service.FooService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static org.springframework.http.HttpStatus.OK;

@Controller
public class FoosApiController implements FoosApi {
    private final FooService service;
    private final FooUpdateRequestToDtoMapper fooUpdateRequestToDtoMapper;
    private final FooEntityToDtoMapper fooEntityToDtoMapper;

    public FoosApiController(FooService service,
                             FooUpdateRequestToDtoMapper fooUpdateRequestToDtoMapper,
                             FooEntityToDtoMapper fooEntityToDtoMapper) {
        this.service = service;
        this.fooUpdateRequestToDtoMapper = fooUpdateRequestToDtoMapper;
        this.fooEntityToDtoMapper = fooEntityToDtoMapper;
    }

    public ResponseEntity<Foo> update(Long id, FooUpdateRequest fooUpdateRequest) {
        FooEntity fooEntity = service.updateProject(fooUpdateRequestToDtoMapper.map(id, fooUpdateRequest));
        return ResponseEntity.status(OK)
                .body(fooEntityToDtoMapper.map(fooEntity));
    }
}
