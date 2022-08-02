# projeto-web: Projeto do módulo de Programação Web II.

* Grupo:
* Italo Franchesco Magalhães Feitosa.

Projeto do módulo de Programaçao WEB II.

# Situação-problema

  A empresa Batatinha-Frita 123 está com dificuldades de controlar sua produção de tipos de batatas fritas: batata-palha, batata palito e batata-smile. Para isto, ela deve controlar tanto os insumos básicos como batatas, óleos, tempeiros e embalagens; e o fluxo de produção, pelas fases de descascamento, corte, fritura, empacotamento, venda, carregamento e entrega. 

  A linha de produção da Batatinha-Frita 123 consiste basicamente de processos linerares, de forma que um processo é simplesmente uma sequência
definida de fases. O processo de descasque das batatas é o único processo que poderia ser considerado como sendo comum para os demais, mas pode
ser tratado como uma etapa "separada" para cada processo. A equipe de limpeza das batatas está ciente de sua atividade e a realiza de maneira agrupada. As fases de corte, fritura e empacotamento não podem ser realizadas de maneira agrupada para vários produtos. Vendas, carregamento e entrega não têm como característica não possuem problemas para serem listadas separadamente.


# Considerações sobre a situação-problema

- deve haver um meio de registrar a identificação (nome) de cada insumo, sua unidade de medida e estoque minimo;
- cada lote de item estocado deve fazer referência ao tipo de insumo, sua quantidade ainda em estoque, data de aquisição, data de validade e anotações sobre suas características de qualidade;
- cada processo de fabricação atende a produção de algum produto. Um processo é caracterizado por um nome de uso interno, sua descrição, data de
registro no sistema e responsável pelo processo. Um processo é uma sequência de fases identificadas por seu nome e seu número de sequência dentro do processo. Uma fase produz ou um insumo para outra fase ou um item para venda, mas não ambos, caracterizado por sua unidade de produtividade e quantidade produzida pela fase;
- Os itens para venda são produzidos em lotes com data de fabricação e data de validade obrigatoriamente registradas. Faz parte dos itens de venda também o preço de venda, que é baseado em seu custo de produção do produto e outras características do mercado. Devem ser registrados a quantidade de itens de venda em estoque e a sua unidade de medida.

# Funcionalidade requeridas do sistema.

  O fluxo de trabalho de elaboração de produtos da Batatinha-Frita 123 inicia na criação de uma ideia de um produto. Para cada produto, então, é desenvolvido seu processo de fabricação. A elaboração do processo de fabricação termina no registro sequencial das fases nomeadas do processo de fabricação, com a respectiva quantidade produzida (e unidade de medida de produção), juntamente com os insumos consumidos e suas quantidades, ou os insumos produzidos pela fase ou o produto de venda fabricado, mas não ambos.

  A equipe de fábrica realiza as fases dos processos. Devem ser registradas o início e o término das execuções das fases para controle das atividades.
  Na execução de cada fase devem ser registrados os lotes dos itens do estoque de insumos consumidos e suas quantidades. As fases que produzem insumos devem registrar um novo item de estoque de insumo com sua respectiva data de fabricação (a data de término da execução da fase) e sua validade, determinada pelo pessoal da equipe de fabricação. As fases que produzem itens de venda, o lote do item para venda, a quantidade de itens de venda produzidos (e respectiva unidade de venda) e o preço de referência para venda, sua data de fabricação (data de término da execução da fase) e a data de validade, também apurada pelo pessoal da equipe de fabricação.
