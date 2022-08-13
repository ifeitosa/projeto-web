package br.com.letscode.supernova.batatas.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.letscode.supernova.batatas.dto.FaseDto;
import br.com.letscode.supernova.batatas.dto.InsumoConsumidoFaseDto;
import br.com.letscode.supernova.batatas.dto.InsumoDto;
import br.com.letscode.supernova.batatas.dto.InsumoProduzidoFaseDto;
import br.com.letscode.supernova.batatas.dto.ProcessoDto;

@AutoConfigureMockMvc
@SpringBootTest
@WithMockUser(value = "USER", username = "user", password = "batatas")
public class ProcessoRestControllerTest {

        @Autowired
        MockMvc mvc;

        @Autowired
        ObjectMapper objectMapper;

        private static final InsumoDto[] insumos = {
                        new InsumoDto(null, "Insumo 1", "kg", 10.0D),
                        new InsumoDto(null, "Insumo 2", "L", 1.0D),
                        new InsumoDto(null, "Insumo 3", "g", 100.0D),
                        new InsumoDto(null, "Insumo 4", "m", 5.0D),
                        new InsumoDto(null, "Insumo 5", "m", 18.9D),
                        new InsumoDto(null, "Insumo 6", "L", 91.2D),
                        new InsumoDto(null, "Insumo 7", "KL", 12.2D)
        };
        private static final InsumoConsumidoFaseDto[] consumidos = {
                        new InsumoConsumidoFaseDto(insumos[0], 4.0D, "kg"),
                        new InsumoConsumidoFaseDto(insumos[1], 3.0D, "mL"),
                        new InsumoConsumidoFaseDto(insumos[2], 18.0D, "g"),
                        new InsumoConsumidoFaseDto(insumos[3], 2.1D, "m")
        };
        
        private static final InsumoProduzidoFaseDto[] produzidos = {
                new InsumoProduzidoFaseDto(insumos[4], 18.9D, "L"),
                new InsumoProduzidoFaseDto(insumos[5], 17.3D, "m"),
                new InsumoProduzidoFaseDto(insumos[6], 18.3D, "g")
            };
        
            private static final FaseDto[] fases = {
                    new FaseDto(1, "Fase 1", "Instrução da fase 1", "kg", 100.0D, List.of(consumidos[0]), List.of(produzidos[0])),
                    new FaseDto(2, "Fase 2", "Instrução da fase 2", "g", 3.2D, List.of(consumidos[1], consumidos[2]), List.of(produzidos[1])),
                    new FaseDto(3, "Fase 3", "Instrução da fase 3", "m", 2.1D, List.of(consumidos[3]), List.of(produzidos[2]))
            };
        private static final ProcessoDto processo = new ProcessoDto(null, "Processo de teste",
                        "Este é um processo de teste",
                        LocalDate.now(),
                        "Italo",
                        List.of(fases[0], fases[1], fases[2]));

        @Test

        public void testarConsultarATodos() throws UnsupportedEncodingException, Exception {

                ProcessoDto dto = objectMapper.readValue(this.mvc.perform(
                                post("/processo/")
                                                .content(asJsonString(processo))
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andReturn().getResponse().getContentAsString(), ProcessoDto.class);
                assertNotNull(dto.getId());

                String result = this.mvc
                                .perform(get("/processo").secure(false).accept(MediaType.APPLICATION_JSON_VALUE)
                                                .characterEncoding("UTF-8"))
                                .andExpect(status().isOk())
                                .andExpect(MockMvcResultMatchers.content()
                                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                                .andDo(MockMvcResultHandlers.print())
                                .andReturn().getResponse().getContentAsString();
                List<ProcessoDto> lista = objectMapper.readerForListOf(ProcessoDto.class).readValue(result);

                assertNotNull(lista);
                System.out.println(lista);
                assertTrue(lista.contains(dto));
        }

        @Test
        @WithMockUser(roles = "USER", username = "user", password = "batatas")
        public void testarCriacao() throws Exception {
                this.objectMapper.findAndRegisterModules();
                String s;
                System.out.println("[>>>>>]\t\t" + (s = asJsonString(processo)));
                String result = this.mvc.perform(
                                post("/processo/")
                                                .content(s)
                                                .contentType(MediaType.APPLICATION_JSON))
                                // .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andReturn().getResponse().getContentAsString();
                ProcessoDto rdto = null;
                try {
                        rdto = objectMapper.readValue(result, ProcessoDto.class);
                } catch (JsonMappingException ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("[>>>] Retorno: " + result);
                        fail();
                }
                assertNotNull(rdto.getId());
        }

        @Test
        @WithMockUser(roles = "USER", username = "user", password = "batatas")
        public void testarObjectProcesso() throws Exception {
                String result = this.mvc.perform(
                                post("/processo/")
                                                .content(asJsonString(processo))
                                                .contentType(MediaType.APPLICATION_JSON))
                                // .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andReturn().getResponse().getContentAsString();
                ProcessoDto rdto = null;
                try {
                        rdto = objectMapper.readValue(result, ProcessoDto.class);
                } catch (JsonMappingException ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("[>>>] Retorno: " + result);
                        fail();
                }

                String resultado = "";
                ProcessoDto testeDto = null;
                try {
                        testeDto = objectMapper.readValue(
                                        resultado = this.mvc.perform(get("/processo/{id}", rdto.getId())).andReturn()
                                                        .getResponse()
                                                        .getContentAsString(),
                                        ProcessoDto.class);
                } catch (JsonMappingException ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("[>>>] Retorno: " + resultado);
                        fail();
                }
                assertEquals(rdto.getId().longValue(), testeDto.getId().longValue());
        }

        @Test
        @WithMockUser(roles = "USER", username = "user", password = "batatas")
        public void testarCorrigirProcesso() throws Exception {
                String result = this.mvc.perform(
                                post("/processo/")
                                                .content(asJsonString(processo))
                                                .contentType(MediaType.APPLICATION_JSON))
                                // .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andReturn().getResponse().getContentAsString();
                ProcessoDto rdto = objectMapper.readValue(result, ProcessoDto.class);

                String resultado = "";
                ProcessoDto testeDto = null;
                try {
                        testeDto = objectMapper.readValue(
                                        resultado = this.mvc.perform(
                                                        put("/processo/{id}", rdto.getId())
                                                                        .content(asJsonString(rdto))
                                                                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                                                        .andReturn().getResponse().getContentAsString(),
                                        ProcessoDto.class);
                } catch (JsonMappingException ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("[>>>] Retorno: " + resultado);
                        fail();
                }

                assertEquals(rdto.getId().longValue(), testeDto.getId().longValue());
        }

        @Test
        @WithMockUser(roles = "USER", username = "user", password = "batatas")
        public void testarDeletarProcesso() throws Exception {
                String result = this.mvc.perform(
                                post("/processo/")
                                                .content(asJsonString(processo))
                                                .contentType(MediaType.APPLICATION_JSON))
                                // .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andReturn().getResponse().getContentAsString();
                ProcessoDto rdto = objectMapper.readValue(result, ProcessoDto.class);

                String resultado = "";
                try {
                        resultado = this.mvc.perform(
                                        delete("/processo/{id}", rdto.getId()))
                                        .andExpect(status().isOk())
                                        .andReturn().getResponse().getContentAsString();
                } catch (JsonMappingException ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("[>>>] Retorno: " + resultado);
                        fail();
                }

                this.mvc.perform(get("/processo/{id}", rdto.getId()))
                        .andExpect(status().isNotFound());

        }

        public String asJsonString(final Object obj) {
                try {
                        return this.objectMapper.writeValueAsString(obj);
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }
        }

}
