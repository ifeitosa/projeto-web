package br.com.letscode.supernova.batatas.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.letscode.supernova.batatas.dto.ExecucaoFaseProcessamentoDto;
import br.com.letscode.supernova.batatas.dto.FaseDto;
import br.com.letscode.supernova.batatas.dto.IdFaseDto;
import br.com.letscode.supernova.batatas.dto.IdProcesso;
import br.com.letscode.supernova.batatas.dto.InsumoConsumidoFaseDto;
import br.com.letscode.supernova.batatas.dto.InsumoDto;
import br.com.letscode.supernova.batatas.dto.InsumoProduzidoFaseDto;
import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoConsumidoDto;
import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoDto;
import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoProduzidoDto;
import br.com.letscode.supernova.batatas.dto.ItemProduzidoExecucaoDto;
import br.com.letscode.supernova.batatas.dto.LoteProdutoVendaDto;
import br.com.letscode.supernova.batatas.dto.ProcessoDto;
import br.com.letscode.supernova.batatas.dto.ProdutoVendaDto;
import br.com.letscode.supernova.batatas.mapper.ExecucaoFaseProcessamentoMapper;
import br.com.letscode.supernova.batatas.mapper.ProdutoVendaMapper;
import br.com.letscode.supernova.batatas.modelos.Processo;
import br.com.letscode.supernova.batatas.modelos.ProdutoVenda;
import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioItemEstoqueInsumo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioProcesso;
import br.com.letscode.supernova.batatas.repositorios.RepositorioProdutoVenda;

@SpringBootTest
public class ExecucaoFaseProcessamentoServiceTest {
    @Autowired
    ProcessoService processoService;
    @Autowired
    RepositorioProcesso repositorioProcesso;
    @Autowired
    ExecucaoFaseProcessamentoService service;
    @Autowired
    RepositorioItemEstoqueInsumo repositorioItemEstoqueInsumo;
    @Autowired
    RepositorioProdutoVenda repositorioProdutoVenda;
    @Autowired
    RepositorioInsumo repositorioInsumo;
    @Autowired
    ProdutoVendaMapper pvMapper;
    @Autowired
    ExecucaoFaseProcessamentoMapper mapper;

    private static final InsumoDto[] insumos = {
            new InsumoDto(null, "Insumo 1", "kg", 10.0D),
            new InsumoDto(null, "Insumo 2", "L", 1.0D),
            new InsumoDto(null, "Insumo 3", "g", 100.0D),
            new InsumoDto(null, "Insumo 4", "m", 5.0D),
            new InsumoDto(null, "Insumo 5", "m", 18.9D),
            new InsumoDto(null, "Insumo 6", "L", 91.2D),
            new InsumoDto(null, "Insumo 7", "KL", 12.2D)
    };
    private static final InsumoConsumidoFaseDto[] consumidosFase = {
            new InsumoConsumidoFaseDto(insumos[0], 4.0D, "kg"),
            new InsumoConsumidoFaseDto(insumos[1], 3.0D, "mL"),
            new InsumoConsumidoFaseDto(insumos[2], 18.0D, "g"),
            new InsumoConsumidoFaseDto(insumos[3], 2.1D, "m")
    };

    private static final InsumoProduzidoFaseDto[] produzidosFase = {
            new InsumoProduzidoFaseDto(insumos[4], 18.9D, "L"),
            new InsumoProduzidoFaseDto(insumos[5], 17.3D, "m"),
            new InsumoProduzidoFaseDto(insumos[6], 18.3D, "g")
    };

    private static final FaseDto[] fases = {
            new FaseDto(1, "Fase 1", "Instrução da fase 1", "kg", 100.0D,
                    List.of(consumidosFase[0]),
                    List.of(produzidosFase[0])),
            new FaseDto(2, "Fase 2", "Instrução da fase 2", "g", 3.2D,
                    List.of(consumidosFase[1], consumidosFase[2]),
                    List.of(produzidosFase[1])),
            new FaseDto(3, "Fase 3", "Instrução da fase 3", "m", 2.1D,
                    List.of(consumidosFase[3]),
                    List.of(produzidosFase[2]))
    };
    private static final ProcessoDto processo = new ProcessoDto(null, "Processo de teste",
            "Este é um processo de teste",
            LocalDate.now(),
            "Italo",
            List.of(fases[0], fases[1], fases[2]));

    static final ItemEstoqueInsumoDto[] itens = {
            new ItemEstoqueInsumoDto(null, insumos[0], 33.2D, LocalDate.now().minusDays(2),
                    LocalDate.now().plusDays(58), "premium"),
            new ItemEstoqueInsumoDto(null, insumos[1], 33.2D, LocalDate.now().minusDays(2),
                    LocalDate.now().plusDays(58), "premium"),
            new ItemEstoqueInsumoDto(null, insumos[2], 99.2D, LocalDate.now().minusDays(27), LocalDate.now().plusDays(78),
                    "regular"),
            new ItemEstoqueInsumoDto(null, insumos[3], 102D, LocalDate.now().minusDays(18),
                    LocalDate.now().plusDays(28), "premium")
    };

    static final ItemEstoqueInsumoConsumidoDto[] insumosConsumidos = {
            new ItemEstoqueInsumoConsumidoDto(null, itens[0], LocalDate.now().minusDays(1), 19.3D, "kg"),
            new ItemEstoqueInsumoConsumidoDto(null, itens[1], LocalDate.now().minusDays(1), 19.3D, "L")
    };
    static final ItemEstoqueInsumoProduzidoDto[] insumosProduzidos = {
            new ItemEstoqueInsumoProduzidoDto(null, itens[2], LocalDate.now(), 7.23D, "m")
    };

    static final ProdutoVendaDto[] produtoVenda = {
            new ProdutoVendaDto(null, new IdProcesso(1L), "Produto venda 1", "m", 18.8, 29.33)
    };

    static final LoteProdutoVendaDto[] loteProdutoVenda = {
            new LoteProdutoVendaDto(null, produtoVenda[0], LocalDate.now(), LocalDate.now().plusDays(90), 45.12, "m",
                    13.9)
    };

    static final ItemProduzidoExecucaoDto[] itensProduzidos = {
            new ItemProduzidoExecucaoDto(null, loteProdutoVenda[0], LocalDate.now(), 45.12, "m")
    };

    @Test
    public void deveSalvarExecucaoFaseProcessamento() {
        ProcessoDto processoDto = processoService.adicionarProcesso(processo);
        Processo proc = repositorioProcesso.findById(processoDto.getId()).get();
        produtoVenda[0].setProcesso(new IdProcesso(processoDto.getId()));
        ProdutoVenda pv = repositorioProdutoVenda.save(pvMapper.toEntity(produtoVenda[0]));

        ExecucaoFaseProcessamentoDto dto = new ExecucaoFaseProcessamentoDto(null,
                new IdFaseDto(proc.getFases().get(0).getId()),
                LocalDate.now().minusDays(10), LocalDate.now(), List.of(insumosConsumidos[0], insumosConsumidos[1]),
                List.of(insumosProduzidos[0]), List.of(itensProduzidos[0]));

        ExecucaoFaseProcessamentoDto resultado = service.inserirExecucaoFaseProcessamento(dto);
        assertNotNull(resultado);

    }

}
