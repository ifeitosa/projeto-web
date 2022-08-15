package br.com.letscode.supernova.batatas.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.letscode.supernova.batatas.dto.ExecucaoFaseProcessamentoDto;
import br.com.letscode.supernova.batatas.dto.IdFaseDto;
import br.com.letscode.supernova.batatas.dto.IdProcesso;
import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoConsumidoDto;
import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoDto;
import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoProduzidoDto;
import br.com.letscode.supernova.batatas.dto.ItemProduzidoExecucaoDto;
import br.com.letscode.supernova.batatas.dto.LoteProdutoVendaDto;
import br.com.letscode.supernova.batatas.dto.ProdutoVendaDto;
import br.com.letscode.supernova.batatas.modelos.ExecucaoFaseProcessamento;
import br.com.letscode.supernova.batatas.modelos.Fase;
import br.com.letscode.supernova.batatas.modelos.ItemEstoqueInsumoConsumido;
import br.com.letscode.supernova.batatas.modelos.ItemEstoqueInsumoProduzido;
import br.com.letscode.supernova.batatas.modelos.ItemProduzidoExecucao;
import br.com.letscode.supernova.batatas.modelos.LoteProdutoVenda;
import br.com.letscode.supernova.batatas.modelos.ProdutoVenda;
import br.com.letscode.supernova.batatas.repositorios.RepositorioFase;
import br.com.letscode.supernova.batatas.repositorios.RepositorioProcesso;

@Component
public class ExecucaoFaseProcessamentoMapper {

        @Autowired
        private RepositorioFase repositorioFase;
        @Autowired
        private RepositorioProcesso repositorioProcesso;

        private ExecucaoFaseProcessamentoMapper() {
        }

        public ExecucaoFaseProcessamentoDto fromEntity(ExecucaoFaseProcessamento entity) {
                return new ExecucaoFaseProcessamentoDto(entity.getOS(), this.fromEntity(entity.getFase()),
                                entity.getDataInicio(), entity.getDataTermino(),
                                entity.getItemEstoqueInsumoConsumido().stream().map(this::fromEntity)
                                                .collect(Collectors.toList()),
                                entity.getItemEstoqueInsumoProduzido().stream().map(this::fromEntity)
                                                .collect(Collectors.toList()),
                                entity.getItemProduzidoExecucao().stream().map(this::fromEntity)
                                                .collect(Collectors.toList()));
        }

        private IdFaseDto fromEntity(Fase fase) {
                return new IdFaseDto(fase.getId());
        }

        private ItemEstoqueInsumoConsumidoDto fromEntity(ItemEstoqueInsumoConsumido entity) {
                ItemEstoqueInsumoDto estoqueInsumo = ItemEstoqueInsumoMapper.fromEntity(entity.getItemEstoqueInsumo());
                return new ItemEstoqueInsumoConsumidoDto(entity.getId(), estoqueInsumo, entity.getDataRegistro(),
                                entity.getQuantidadeConsumida(), entity.getUnidadeConsumo());
        }

        private ItemEstoqueInsumoProduzidoDto fromEntity(ItemEstoqueInsumoProduzido entity) {
                return new ItemEstoqueInsumoProduzidoDto(entity.getId(),
                                ItemEstoqueInsumoMapper.fromEntity(entity.getItemEstoqueInsumo()),
                                entity.getDataRegistro(),
                                entity.getQuantidadeProduzida(), entity.getUnidadeProducao());
        }

        private ItemProduzidoExecucaoDto fromEntity(ItemProduzidoExecucao entity) {
                return new ItemProduzidoExecucaoDto(entity.getId(),
                                this.fromEntity(entity.getLoteProdutoVenda()),
                                entity.getDataRegistro(), entity.getQuantidadeProduzida(), entity.getUnidadeProducao());
        }

        private LoteProdutoVendaDto fromEntity(LoteProdutoVenda entity) {
                LoteProdutoVendaDto dto = new LoteProdutoVendaDto();
                dto.setLote(entity.getLote());
                dto.setDataFabricacao(entity.getDataFabricacao());
                dto.setDataValidade(entity.getDataValidade());
                dto.setPrecoVenda(entity.getPrecoVenda());
                dto.setProdutoVenda(this.fromEntity(entity.getProdutoVenda()));
                dto.setQuantidadeEstoque(entity.getQuantidadeEstoque());
                dto.setUnidadeEstoque(entity.getUnidadeEstoque());
                return dto;
        }

        private ProdutoVendaDto fromEntity(ProdutoVenda dto) {
                return new ProdutoVendaDto(dto.getId(), new IdProcesso(dto.getProcesso().getId()),
                                dto.getDescricao(), dto.getUnidadeMedida(), dto.getQuantidade(),
                                dto.getCustoProducao());
        }

        public ExecucaoFaseProcessamento toEntity(ExecucaoFaseProcessamentoDto execucao) {
                ExecucaoFaseProcessamento resultado = new ExecucaoFaseProcessamento(execucao.getOS(),
                                this.toEntity(execucao.getFase()),
                                execucao.getDataInicio(), execucao.getDataTermino(),
                                null, null, null);
                resultado.setItemEstoqueInsumoConsumido(execucao.getEstoqueInsumoConsumido().stream()
                                .map(i -> this.toEntity(i, resultado))
                                .collect(Collectors.toList()));
                resultado.setItemEstoqueInsumoProduzido(execucao.getEstoqueInsumoProduzido().stream()
                                .map(i -> this.toEntity(i, resultado))
                                .collect(Collectors.toList()));
                resultado.setItemProduzidoExecucao(execucao.getItemProduzidoExecucao().stream()
                                .map(i -> this.toEntity(i, resultado))
                                .collect(Collectors.toList()));
                return resultado;
        }

        private ItemProduzidoExecucao toEntity(ItemProduzidoExecucaoDto dto, ExecucaoFaseProcessamento execucao) {
                return new ItemProduzidoExecucao(dto.getId(), execucao,
                                this.toEntity(dto.getLoteProdutoVenda()), dto.getDataRegistro(),
                                dto.getQuantidadeProduzida(), dto.getUnidadeProducao());
        }

        private LoteProdutoVenda toEntity(LoteProdutoVendaDto dto) {
                return new LoteProdutoVenda(dto.getLote(), this.toEntity(dto.getProdutoVenda()),
                                dto.getDataFabricacao(), dto.getDataValidade(), dto.getPrecoVenda(),
                                dto.getUnidadeEstoque(),
                                dto.getQuantidadeEstoque());
        }

        private ProdutoVenda toEntity(ProdutoVendaDto dto) {

                return new ProdutoVenda(dto.getId(), repositorioProcesso.findById(dto.getProcesso().getId()).get(),
                                dto.getDescricao(), dto.getUnidadeMedida(), dto.getQuantidade(),
                                dto.getCustoProducao());
        }

        private ItemEstoqueInsumoConsumido toEntity(ItemEstoqueInsumoConsumidoDto dto,
                        ExecucaoFaseProcessamento execucao) {
                return new ItemEstoqueInsumoConsumido(dto.getId(),
                                ItemEstoqueInsumoMapper.toEntity(dto.getItemEstoqueInsumo()),
                                execucao, dto.getDataRegistro(), dto.getQuantidadeProduzida(), dto.getUnidadeConsumo());
        }

        private Fase toEntity(IdFaseDto fase) {
                return this.repositorioFase.findById(fase.getId()).get();
        }

        private ItemEstoqueInsumoProduzido toEntity(ItemEstoqueInsumoProduzidoDto dto,
                        ExecucaoFaseProcessamento execucao) {
                return new ItemEstoqueInsumoProduzido(dto.getId(),
                                ItemEstoqueInsumoMapper.toEntity(dto.getItemEstoqueInsumo()),
                                execucao,
                                dto.getDataRegistro(), dto.getQuantidadeProduzida(), dto.getUnidadeConsumo());

        }
}
