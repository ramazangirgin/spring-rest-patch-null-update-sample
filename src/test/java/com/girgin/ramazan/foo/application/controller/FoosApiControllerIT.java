package com.girgin.ramazan.foo.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.girgin.ramazan.foo.api.model.Foo;
import com.girgin.ramazan.foo.domain.model.FooEntity;
import com.girgin.ramazan.foo.domain.repository.FooRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FoosApiControllerIT {

    private static final String SLOT_UPDATE_ENDPOINT_URL_TEMPLATE = "/foos/{id}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FooRepository fooRepository;

    @Test
    public void shouldUpdateWhenNameAndExternalIdAreGiven() throws Exception {
        //given
        Long id = 1L;
        String updateRequestJson = "{\"name\":\"Foo1_Changed\",\"externalId\":20001}";

        //when
        ResultActions resultActions = whenUpdateFoo(id, updateRequestJson);

        //then
        resultActions.andDo(print()).andExpect(status().isOk());
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        Foo foo = objectMapper.readValue(responseBody, Foo.class);
        assertThat(foo).isNotNull();
        assertThat(foo.getId()).isEqualTo(1);
        assertThat(foo.getName()).isEqualTo("Foo1_Changed");
        assertThat(foo.getExternalId()).isEqualTo(20001L);

        assetThatEntityIsUpdated(foo);
    }

    @Test
    public void shouldUpdateOnlyNameWhenNameIsGivenAndExternalIdIsNotGiven() throws Exception {
        //given
        Long id = 2L;
        String updateRequestJson = "{\"name\":\"Foo2_Changed\"}";

        //when
        ResultActions resultActions = whenUpdateFoo(id, updateRequestJson);

        //then
        resultActions.andDo(print()).andExpect(status().isOk());
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        Foo foo = objectMapper.readValue(responseBody, Foo.class);
        assertThat(foo).isNotNull();
        assertThat(foo.getId()).isEqualTo(2);
        assertThat(foo.getName()).isEqualTo("Foo2_Changed");
        assertThat(foo.getExternalId()).isEqualTo(10002L);

        assetThatEntityIsUpdated(foo);
    }

    @Test
    public void shouldUpdateOnlyExternalIdWhenNullExternalIdIsGiven() throws Exception {
        //given
        Long id = 3L;
        String updateRequestJson = "{\"externalId\":\"null\"}";

        //when
        ResultActions resultActions = whenUpdateFoo(id, updateRequestJson);

        //then
        resultActions.andDo(print()).andExpect(status().isOk());
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        Foo foo = objectMapper.readValue(responseBody, Foo.class);
        assertThat(foo).isNotNull();
        assertThat(foo.getId()).isEqualTo(3);
        assertThat(foo.getName()).isEqualTo("Foo3");
        assertThat(foo.getExternalId()).isEqualTo(null);

        assetThatEntityIsUpdated(foo);
    }

    @Test
    public void shouldReturnBadRequestWhenNameIsNotValid() throws Exception {
        //given
        Long id = 1L;
        String updateRequestJson = "{\"name\":\"A\"}";

        //when
        ResultActions resultActions = whenUpdateFoo(id, updateRequestJson);

        //then
        resultActions.andDo(print()).andExpect(status().isBadRequest());
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(responseBody).isEqualTo("{\"violations\":[{\"fieldName\":\"name\"," +
                "\"message\":\"size must be between 3 and 2147483647\"}]}");
    }

    @Test
    public void shouldReturnBadRequestWhenExternalIdIsNotValid() throws Exception {
        //given
        Long id = 1L;
        String updateRequestJson = "{\"externalId\":20}";

        //when
        ResultActions resultActions = whenUpdateFoo(id, updateRequestJson);

        //then
        resultActions.andDo(print()).andExpect(status().isBadRequest());
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(responseBody).isEqualTo("{\"violations\":[{\"fieldName\":\"externalId\"," +
                "\"message\":\"must be greater than or equal to 1000\"}]}");
    }

    private ResultActions whenUpdateFoo(Long id, String updateRequestJson) throws Exception {

        return this.mockMvc.perform(
                MockMvcRequestBuilders.patch(SLOT_UPDATE_ENDPOINT_URL_TEMPLATE, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestJson)
        );
    }

    private void assetThatEntityIsUpdated(Foo foo) {
        Optional<FooEntity> fooEntityOptional = fooRepository.findById(foo.getId());
        assertThat(fooEntityOptional).isPresent();
        assertThat(fooEntityOptional.get()).usingRecursiveComparison().isEqualTo(foo);
    }
}