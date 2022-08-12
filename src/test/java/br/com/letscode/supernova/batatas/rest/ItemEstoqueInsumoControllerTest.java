package br.com.letscode.supernova.batatas.rest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.time.ZonedDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.letscode.supernova.batatas.dto.InsumoDto;
import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoDto;

@AutoConfigureMockMvc
@SpringBootTest
@WithMockUser(roles = "USER", username = "user", password = "batatas")
public class ItemEstoqueInsumoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    InsumoDto insumoDto;
    InsumoDto insumoDto2;

    @BeforeEach
    public void setup() {
        insumoDto = new InsumoDto(null, "Insumo teste 1", "kg", 10.0D);
        insumoDto2 = new InsumoDto(null, "Insumo teste 2", "L", 28.0D);

        try {
            insumoDto = objectMapper.readValue(mvc.perform(post("/insumo")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(asJsonString(insumoDto)))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString(), InsumoDto.class);

            insumoDto2 = objectMapper.readValue(
                    mvc.perform(post("/insumo").contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(asJsonString(insumoDto2))).andExpect(status().isOk())
                            .andReturn().getResponse().getContentAsString(),
                    InsumoDto.class);
        } catch (JsonMappingException e) {
            fail("Objeto não pode ser convertido do Json");
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            fail("Objeto não pode processar JSON");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            fail("Codificação de caractere não suportada");
            e.printStackTrace();
        } catch (Exception e) {
            fail("Exceção desconhecida lançada " + e.getClass().getCanonicalName() + " com mensagem " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testarObterItensEstoque() {
        ItemEstoqueInsumoDto dto = new ItemEstoqueInsumoDto(null, insumoDto, 100.0D, ZonedDateTime.now(),
                ZonedDateTime.now().plusMonths(3), "premium");
        ItemEstoqueInsumoDto dto2 = new ItemEstoqueInsumoDto(null, insumoDto2, 90.0D, ZonedDateTime.now().minusDays(15),
                ZonedDateTime.now().plusDays(75), "premium");

        try {
            dto = objectMapper.readValue(mvc
                    .perform(
                            post("/estoqueInsumo").contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .content(asJsonString(dto)))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString(), ItemEstoqueInsumoDto.class);

            dto2 = objectMapper.readValue(mvc
                    .perform(
                            post("/estoqueInsumo").contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .content(asJsonString(dto2)))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString(), ItemEstoqueInsumoDto.class);
            String retorno = mvc.perform(get("/estoqueInsumo"))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();

            assertTrue(retorno.length() > 0);
            
            List<ItemEstoqueInsumoDto> lista = objectMapper.readerForListOf(ItemEstoqueInsumoDto.class).readValue(retorno);
            
            assertTrue(lista.contains(dto));
            assertTrue(lista.contains(dto2));
        } catch (JsonMappingException e) {
            fail("Falha no mapeamento do JSON.");
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            fail("Falha no processamento do JSON.");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            fail("Codificação não suportada.");
            e.printStackTrace();
        } catch (Exception e) {
            fail("Exceção inesperada " + e.getClass().getCanonicalName() + " com mensagem " + e.getMessage());
            e.printStackTrace();
        }

    }

    private String asJsonString(Object obj) {
        try {
            return this.objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            fail("Json não pode ser criado para objeto " + obj.toString());
            return "";
        }
    }

}
