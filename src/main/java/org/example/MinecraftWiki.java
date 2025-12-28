package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório centralizado de dados do Minecraft.
 * Contém todos os modelos de dados como classes internas para maior coesão.
 */
public class MinecraftWiki {
    private final List<Item> itens;
    private final List<Pocao> pocoes;
    private final List<Encantamento> encantamentos;
    private final List<Armadura> armaduras;
    private final List<MoldeFerraria> moldesFerraria;
    
    // ==================== CLASSES DE MODELO (INNER CLASSES) ====================
    
    /**
     * Classe Item - Representa um item do Minecraft
     */
    public static class Item {
        private final String nome;
        private final String descricao;
        private final MinecraftEdition edicao;
        private final List<String> ingredientes;
        private String padraoCrafting;
        private final String categoria;
        private String imagemItem;
        private String imagemCrafting;

        public Item(String nome, String descricao, MinecraftEdition edicao, String categoria) {
            this.nome = nome;
            this.descricao = descricao;
            this.edicao = edicao;
            this.categoria = categoria;
            this.ingredientes = new ArrayList<>();
        }

        public void adicionarIngrediente(String ingrediente) {
            ingredientes.add(ingrediente);
        }

        public void setPadraoCrafting(String padrao) {
            this.padraoCrafting = padrao;
        }

        public String getNome() { return nome; }
        public String getDescricao() { return descricao; }
        public MinecraftEdition getEdicao() { return edicao; }
        public String getCategoria() { return categoria; }
        public List<String> getIngredientes() { return ingredientes; }
        public String getPadraoCrafting() { return padraoCrafting; }
        public String getImagemItem() { return imagemItem; }
        public void setImagemItem(String imagemItem) { this.imagemItem = imagemItem; }
        public String getImagemCrafting() { return imagemCrafting; }
        public void setImagemCrafting(String imagemCrafting) { this.imagemCrafting = imagemCrafting; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n╔════════════════════════════════════════════════════════════╗\n");
            sb.append(String.format("  ITEM: %s\n", nome));
            sb.append("╠════════════════════════════════════════════════════════════╣\n");
            sb.append(String.format("  Categoria: %s\n", categoria));
            sb.append(String.format("  Edição: %s\n", edicao.getDisplayName()));
            sb.append(String.format("  Descrição: %s\n", descricao));

            if (!ingredientes.isEmpty()) {
                sb.append("\n  ► Ingredientes necessários:\n");
                for (String ing : ingredientes) {
                    sb.append(String.format("    • %s\n", ing));
                }
            }

            if (padraoCrafting != null && !padraoCrafting.isEmpty()) {
                sb.append("\n  ► Padrão de Crafting:\n");
                sb.append(padraoCrafting);
            }

            sb.append("╚════════════════════════════════════════════════════════════╝\n");
            return sb.toString();
        }
    }
    
    /**
     * Classe Pocao - Representa uma poção do Minecraft
     */
    public static class Pocao {
        private final String nome;
        private final String efeito;
        private final String duracao;
        private final MinecraftEdition edicao;
        private final List<String> ingredientes;
        private String basePotion;

        public Pocao(String nome, String efeito, String duracao, MinecraftEdition edicao) {
            this.nome = nome;
            this.efeito = efeito;
            this.duracao = duracao;
            this.edicao = edicao;
            this.ingredientes = new ArrayList<>();
        }

        public void adicionarIngrediente(String ingrediente) {
            ingredientes.add(ingrediente);
        }

        public void setBasePotion(String base) {
            this.basePotion = base;
        }

        public String getNome() { return nome; }
        public String getEfeito() { return efeito; }
        public String getDuracao() { return duracao; }
        public MinecraftEdition getEdicao() { return edicao; }
        public List<String> getIngredientes() { return new ArrayList<>(ingredientes); }
        public String getBasePotion() { return basePotion; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n╔════════════════════════════════════════════════════════════╗\n");
            sb.append(String.format("  POÇÃO: %s\n", nome));
            sb.append("╠════════════════════════════════════════════════════════════╣\n");
            sb.append(String.format("  Edição: %s\n", edicao.getDisplayName()));
            sb.append(String.format("  Efeito: %s\n", efeito));
            sb.append(String.format("  Duração: %s\n", duracao));

            if (basePotion != null) {
                sb.append(String.format("\n  ► Base: %s\n", basePotion));
            }

            if (!ingredientes.isEmpty()) {
                sb.append("\n  ► Ingredientes para preparar:\n");
                for (String ing : ingredientes) {
                    sb.append(String.format("    • %s\n", ing));
                }
            }

            sb.append("\n  ► Como preparar:\n");
            sb.append("    1. Coloque garrafas de água no suporte de poções\n");
            sb.append("    2. Adicione Verruga do Nether (para Poção Estranha)\n");
            sb.append("    3. Adicione o ingrediente principal\n");
            sb.append("    4. [Opcional] Pó de Pedra Luminosa (aumenta potência)\n");
            sb.append("    5. [Opcional] Pó de Redstone (aumenta duração)\n");

            sb.append("╚════════════════════════════════════════════════════════════╝\n");
            return sb.toString();
        }
    }
    
    /**
     * Classe Armadura - Representa uma peça de armadura do Minecraft
     */
    public static class Armadura {
        private final String nome;
        private final String tipo;
        private final String material;
        private final String descricao;
        private final int defesa;
        private final double durabilidade;
        private final MinecraftEdition edicao;
        private final List<String> ingredientes;
        private String moldeFerraria;
        private String receitaMolde;
        private String imagemPath;

        public Armadura(String nome, String tipo, String material, String descricao, 
                       int defesa, double durabilidade, MinecraftEdition edicao) {
            this.nome = nome;
            this.tipo = tipo;
            this.material = material;
            this.descricao = descricao;
            this.defesa = defesa;
            this.durabilidade = durabilidade;
            this.edicao = edicao;
            this.ingredientes = new ArrayList<>();
        }

        public void adicionarIngrediente(String ingrediente) {
            ingredientes.add(ingrediente);
        }

        public void setMoldeFerraria(String molde) {
            this.moldeFerraria = molde;
        }

        public void setReceitaMolde(String receita) {
            this.receitaMolde = receita;
        }

        public void setImagemPath(String path) {
            this.imagemPath = path;
        }

        public String getNome() { return nome; }
        public String getTipo() { return tipo; }
        public String getMaterial() { return material; }
        public String getDescricao() { return descricao; }
        public int getDefesa() { return defesa; }
        public double getDurabilidade() { return durabilidade; }
        public MinecraftEdition getEdicao() { return edicao; }
        public List<String> getIngredientes() { return ingredientes; }
        public String getMoldeFerraria() { return moldeFerraria; }
        public String getReceitaMolde() { return receitaMolde; }
        public String getImagemPath() { return imagemPath; }

        public String getDefesaCompleta() {
            return defesa + " pontos de defesa";
        }

        public String getDurabilidadeCompleta() {
            return (int) durabilidade + " de durabilidade";
        }
    }
    
    /**
     * Classe MoldeFerraria - Representa um molde de ferraria (smithing templates)
     */
    public static class MoldeFerraria {
        private final String nome;
        private final String tipo;
        private final String descricao;
        private final String localizacao;
        private final String uso;
        private final MinecraftEdition edicao;

        public MoldeFerraria(String nome, String tipo, String descricao, String localizacao, String uso, MinecraftEdition edicao) {
            this.nome = nome;
            this.tipo = tipo;
            this.descricao = descricao;
            this.localizacao = localizacao;
            this.uso = uso;
            this.edicao = edicao;
        }

        public String getNome() { return nome; }
        public String getTipo() { return tipo; }
        public String getDescricao() { return descricao; }
        public String getLocalizacao() { return localizacao; }
        public String getUso() { return uso; }
        public MinecraftEdition getEdicao() { return edicao; }

        public boolean isUpgrade() {
            return "upgrade".equals(tipo);
        }

        public boolean isTrim() {
            return "trim".equals(tipo);
        }
    }
    
    /**
     * Classe Encantamento - Representa um encantamento do Minecraft
     */
    public static class Encantamento {
        private final String id;
        private final String nome;
        private final String nomeIngles;
        private final String tipo;
        private final String raridade;
        private final int nivelMaximo;
        private final int nivelMinimo;
        private final int custoXpPorNivel;
        private final String itensAplicaveis;
        private final String itensPrimarios;
        private final String itensSecundarios;
        private final String incompatibilidades;
        private final String fontesObtencao;
        private final boolean tesouro;
        private final int peso;
        private final boolean amaldicoado;
        private final String descricaoCurta;
        private final String descricaoDetalhada;
        private final String efeitoPorNivel;
        private final String efeitoNumerico;
        private final String condicoesEspeciais;
        private final int prioridade;
        private final String versaoAdicionada;
        private final String versaoRemovida;
        private final boolean stackavel;
        private final String icone;
        private final String observacoes;
        private final MinecraftEdition edicao;

        public Encantamento(String nome, String descricao, boolean tesouro, String incompativel, 
                           int nivelMaximo, String itensPrimarios, String itensSecundarios, 
                           int peso, MinecraftEdition edicao) {
            this.id = nome.toLowerCase().replace(" ", "_");
            this.nome = nome;
            this.nomeIngles = nome;
            this.tipo = "Comum";
            this.raridade = tesouro ? "Tesouro" : "Comum";
            this.nivelMaximo = nivelMaximo;
            this.nivelMinimo = 1;
            this.custoXpPorNivel = 1;
            this.itensAplicaveis = itensPrimarios;
            this.itensPrimarios = itensPrimarios;
            this.itensSecundarios = itensSecundarios;
            this.incompatibilidades = incompativel;
            this.fontesObtencao = tesouro ? "Tesouro, Bibliotecário" : "Mesa de Encantamento, Bigorna";
            this.tesouro = tesouro;
            this.peso = peso;
            this.amaldicoado = nome.toLowerCase().contains("maldição");
            this.descricaoCurta = descricao;
            this.descricaoDetalhada = descricao;
            this.efeitoPorNivel = "";
            this.efeitoNumerico = "";
            this.condicoesEspeciais = "";
            this.prioridade = 1;
            this.versaoAdicionada = "1.0";
            this.versaoRemovida = null;
            this.stackavel = false;
            this.icone = "enchanted_book";
            this.observacoes = "";
            this.edicao = edicao;
        }

        public Encantamento(String id, String nome, String nomeIngles, String tipo, String raridade,
                           int nivelMaximo, int nivelMinimo, int custoXpPorNivel,
                           String itensAplicaveis, String incompatibilidades, String fontesObtencao,
                           String descricaoCurta, String descricaoDetalhada, String efeitoPorNivel,
                           String efeitoNumerico, String condicoesEspeciais, int prioridade,
                           String versaoAdicionada, String versaoRemovida, boolean stackavel,
                           boolean amaldicoado, String icone, String observacoes,
                           int peso, boolean tesouro, MinecraftEdition edicao) {
            this.id = id;
            this.nome = nome;
            this.nomeIngles = nomeIngles;
            this.tipo = tipo;
            this.raridade = raridade;
            this.nivelMaximo = nivelMaximo;
            this.nivelMinimo = nivelMinimo;
            this.custoXpPorNivel = custoXpPorNivel;
            this.itensAplicaveis = itensAplicaveis;
            this.itensPrimarios = itensAplicaveis;
            this.itensSecundarios = "";
            this.incompatibilidades = incompatibilidades;
            this.fontesObtencao = fontesObtencao;
            this.tesouro = tesouro;
            this.peso = peso;
            this.amaldicoado = amaldicoado;
            this.descricaoCurta = descricaoCurta;
            this.descricaoDetalhada = descricaoDetalhada;
            this.efeitoPorNivel = efeitoPorNivel;
            this.efeitoNumerico = efeitoNumerico;
            this.condicoesEspeciais = condicoesEspeciais;
            this.prioridade = prioridade;
            this.versaoAdicionada = versaoAdicionada;
            this.versaoRemovida = versaoRemovida;
            this.stackavel = stackavel;
            this.icone = icone;
            this.observacoes = observacoes;
            this.edicao = edicao;
        }

        public String getId() { return id; }
        public String getNome() { return nome; }
        public String getNomeIngles() { return nomeIngles; }
        public String getTipo() { return tipo; }
        public String getRaridade() { return raridade; }
        public int getNivelMaximo() { return nivelMaximo; }
        public int getNivelMinimo() { return nivelMinimo; }
        public int getCustoXpPorNivel() { return custoXpPorNivel; }
        public String getItensAplicaveis() { return itensAplicaveis; }
        public String getItensPrimarios() { return itensPrimarios; }
        public String getItensSecundarios() { return itensSecundarios; }
        public String getIncompatibilidades() { return incompatibilidades; }
        public String getFontesObtencao() { return fontesObtencao; }
        public boolean isTesouro() { return tesouro; }
        public int getPeso() { return peso; }
        public boolean isAmaldicoado() { return amaldicoado; }
        public String getDescricaoCurta() { return descricaoCurta; }
        public String getDescricaoDetalhada() { return descricaoDetalhada; }
        public String getEfeitoPorNivel() { return efeitoPorNivel; }
        public String getEfeitoNumerico() { return efeitoNumerico; }
        public String getCondicoesEspeciais() { return condicoesEspeciais; }
        public int getPrioridade() { return prioridade; }
        public String getVersaoAdicionada() { return versaoAdicionada; }
        public String getVersaoRemovida() { return versaoRemovida; }
        public boolean isStackavel() { return stackavel; }
        public String getIcone() { return icone; }
        public String getObservacoes() { return observacoes; }
        public MinecraftEdition getEdicao() { return edicao; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n╔════════════════════════════════════════════════════════════╗\n");
            sb.append(String.format("  ENCANTAMENTO: %s%s\n", nome, amaldicoado ? " 💀" : ""));
            sb.append(String.format("  ID: %s | Nome em Inglês: %s\n", id, nomeIngles));
            sb.append("╠════════════════════════════════════════════════════════════╣\n");
            sb.append(String.format("  [TIPO] Tipo: %s | Raridade: %s\n", tipo, raridade));
            sb.append(String.format("  [ED] Edição: %s\n", edicao.getDisplayName()));
            sb.append(String.format("  [NIVEL] Nível: %d-%d (Máximo: %d)\n", nivelMinimo, nivelMaximo, nivelMaximo));
            sb.append(String.format("  [XP] Custo XP/Nível: %d | Peso: %d | Prioridade: %d\n", custoXpPorNivel, peso, prioridade));
            sb.append(String.format("  [INFO] Tesouro: %s | Amaldiçoado: %s | Stackável: %s\n", 
                tesouro ? "Sim" : "Não", amaldicoado ? "Sim" : "Não", stackavel ? "Sim" : "Não"));
            sb.append(String.format("\n  [DESC] Descrição: %s\n", descricaoCurta));
            if (descricaoDetalhada != null && !descricaoDetalhada.equals(descricaoCurta)) {
                sb.append(String.format("  [DET] Detalhes: %s\n", descricaoDetalhada));
            }
            if (efeitoPorNivel != null && !efeitoPorNivel.isEmpty()) {
                sb.append(String.format("\n  [EFEITO] Efeito por Nível: %s\n", efeitoPorNivel));
            }
            if (efeitoNumerico != null && !efeitoNumerico.isEmpty()) {
                sb.append(String.format("  [NUM] Valores Numéricos: %s\n", efeitoNumerico));
            }
            sb.append(String.format("\n  [ITEM] Itens Aplicáveis: %s\n", itensAplicaveis));
            if (itensPrimarios != null && !itensPrimarios.isEmpty()) {
                sb.append(String.format("  > Item Primário: %s\n", itensPrimarios));
            }
            if (itensSecundarios != null && !itensSecundarios.isEmpty()) {
                sb.append(String.format("  > Item Secundário: %s\n", itensSecundarios));
            }
            if (incompatibilidades != null && !incompatibilidades.isEmpty()) {
                sb.append(String.format("\n  [X] Incompatível com: %s\n", incompatibilidades));
            }
            if (condicoesEspeciais != null && !condicoesEspeciais.isEmpty()) {
                sb.append(String.format("\n  [!] Condições Especiais: %s\n", condicoesEspeciais));
            }
            sb.append(String.format("\n  [FONTE] Fontes de Obtenção: %s\n", fontesObtencao));
            sb.append("  > Como obter:\n");
            if (tesouro) {
                sb.append("    • Baús de estruturas (tesouros)\n");
                sb.append("    • Pescaria (livros encantados raros)\n");
                sb.append("    • Troca com Aldeões Bibliotecários\n");
            } else {
                sb.append("    • Mesa de Encantamento (requer Lápis-lazúli e XP)\n");
                sb.append("    • Bigorna (combinar livros encantados)\n");
                sb.append("    • Pescaria (livros encantados)\n");
                sb.append("    • Baús de estruturas\n");
                sb.append("    • Troca com Aldeões Bibliotecários\n");
            }
            sb.append(String.format("\n  📅 Adicionado em: %s", versaoAdicionada));
            if (versaoRemovida != null && !versaoRemovida.isEmpty()) {
                sb.append(String.format(" | Removido em: %s", versaoRemovida));
            }
            sb.append("\n");
            if (observacoes != null && !observacoes.isEmpty()) {
                sb.append(String.format("\n  💡 Observações: %s\n", observacoes));
            }
            sb.append("╚════════════════════════════════════════════════════════════╝\n");
            return sb.toString();
        }
    }
    
    // ==================== CONSTRUTOR E MÉTODOS PRINCIPAIS ====================

    public MinecraftWiki() {
        this.itens = new ArrayList<>();
        this.pocoes = new ArrayList<>();
        this.encantamentos = new ArrayList<>();
        this.armaduras = new ArrayList<>();
        this.moldesFerraria = new ArrayList<>();
        carregarDados();
    }

    private void carregarDados() {
        carregarItens();
        carregarPocoes();
        carregarEncantamentos();
        carregarArmaduras();
        carregarMoldesFerraria();
    }

    private void carregarItens() {
        // ==================== FERRAMENTAS ====================

        // Picaretas
        Item picaretaMadeira = new Item("Picareta de Madeira",
            "Primeira picareta básica do jogo",
            MinecraftEdition.BOTH, "Ferramenta");
        picaretaMadeira.adicionarIngrediente("3x Tábuas");
        picaretaMadeira.adicionarIngrediente("2x Graveto");
        itens.add(picaretaMadeira);

        Item picaretaPedra = new Item("Picareta de Pedra",
            "Picareta intermediária feita de pedregulho",
            MinecraftEdition.BOTH, "Ferramenta");
        picaretaPedra.adicionarIngrediente("3x Pedregulho");
        picaretaPedra.adicionarIngrediente("2x Graveto");
        itens.add(picaretaPedra);

        Item picaretaFerro = new Item("Picareta de Ferro",
            "Picareta avançada que minera diamante",
            MinecraftEdition.BOTH, "Ferramenta");
        picaretaFerro.adicionarIngrediente("3x Lingote de Ferro");
        picaretaFerro.adicionarIngrediente("2x Graveto");
        itens.add(picaretaFerro);

        Item picareta = new Item("Picareta de Diamante",
            "Ferramenta para minerar blocos de pedra rapidamente",
            MinecraftEdition.BOTH, "Ferramenta");
        picareta.adicionarIngrediente("3x Diamante");
        picareta.adicionarIngrediente("2x Graveto");
        picareta.setPadraoCrafting(
            """
                ┌───┬───┬───┐
                │ D │ D │ D │
                ├───┼───┼───┤
                │   │ G │   │
                ├───┼───┼───┤
                │   │ G │   │
                └───┴───┴───┘
            """
        );
        itens.add(picareta);

        Item picaretaNetherite = new Item("Picareta de Netherite",
            "A melhor picareta do jogo, mais durável que diamante",
            MinecraftEdition.BOTH, "Ferramenta");
        picaretaNetherite.adicionarIngrediente("1x Picareta de Diamante");
        picaretaNetherite.adicionarIngrediente("1x Lingote de Netherite");
        itens.add(picaretaNetherite);

        // Machados
        Item machadoMadeira = new Item("Machado de Madeira",
            "Ferramenta básica para cortar madeira",
            MinecraftEdition.BOTH, "Ferramenta");
        machadoMadeira.adicionarIngrediente("3x Tábuas");
        machadoMadeira.adicionarIngrediente("2x Graveto");
        itens.add(machadoMadeira);

        Item machadoDiamante = new Item("Machado de Diamante",
            "Machado poderoso, também usado como arma",
            MinecraftEdition.BOTH, "Ferramenta");
        machadoDiamante.adicionarIngrediente("3x Diamante");
        machadoDiamante.adicionarIngrediente("2x Graveto");
        itens.add(machadoDiamante);

        Item machadoNetherite = new Item("Machado de Netherite",
            "O melhor machado do jogo",
            MinecraftEdition.BOTH, "Ferramenta");
        machadoNetherite.adicionarIngrediente("1x Machado de Diamante");
        machadoNetherite.adicionarIngrediente("1x Lingote de Netherite");
        itens.add(machadoNetherite);

        // Pás
        Item paDiamante = new Item("Pá de Diamante",
            "Escava areia, terra e cascalho rapidamente",
            MinecraftEdition.BOTH, "Ferramenta");
        paDiamante.adicionarIngrediente("1x Diamante");
        paDiamante.adicionarIngrediente("2x Graveto");
        itens.add(paDiamante);

        // Enxadas
        Item enxadaDiamante = new Item("Enxada de Diamante",
            "Prepara terra para agricultura",
            MinecraftEdition.BOTH, "Ferramenta");
        enxadaDiamante.adicionarIngrediente("2x Diamante");
        enxadaDiamante.adicionarIngrediente("2x Graveto");
        itens.add(enxadaDiamante);

        // Tesoura
        Item tesoura = new Item("Tesoura",
            "Corta lã, folhas e teias rapidamente",
            MinecraftEdition.BOTH, "Ferramenta");
        tesoura.adicionarIngrediente("2x Lingote de Ferro");
        itens.add(tesoura);

        // Pederneira
        Item pederneira = new Item("Pederneira",
            "Cria fogo e ativa portais do Nether",
            MinecraftEdition.BOTH, "Ferramenta");
        pederneira.adicionarIngrediente("1x Lingote de Ferro");
        pederneira.adicionarIngrediente("1x Sílex");
        itens.add(pederneira);

        // Bússola
        Item bussola = new Item("Bússola",
            "Aponta para o ponto de spawn",
            MinecraftEdition.BOTH, "Ferramenta");
        bussola.adicionarIngrediente("4x Lingote de Ferro");
        bussola.adicionarIngrediente("1x Pó de Redstone");
        itens.add(bussola);

        // Relógio
        Item relogio = new Item("Relógio",
            "Mostra a hora do dia",
            MinecraftEdition.BOTH, "Ferramenta");
        relogio.adicionarIngrediente("4x Lingote de Ouro");
        relogio.adicionarIngrediente("1x Pó de Redstone");
        itens.add(relogio);

        // Vara de Pesca
        Item varaPesca = new Item("Vara de Pesca",
            "Pesca peixes e tesouros",
            MinecraftEdition.BOTH, "Ferramenta");
        varaPesca.adicionarIngrediente("3x Graveto");
        varaPesca.adicionarIngrediente("2x Linha");
        itens.add(varaPesca);

        // ==================== ARMAS ====================

        Item espada = new Item("Espada de Diamante",
            "Arma corpo a corpo com alto dano",
            MinecraftEdition.BOTH, "Arma");
        espada.adicionarIngrediente("2x Diamante");
        espada.adicionarIngrediente("1x Graveto");
        espada.setPadraoCrafting(
            """
                ┌───┬───┬───┐
                │   │ D │   │
                ├───┼───┼───┤
                │   │ D │   │
                ├───┼───┼───┤
                │   │ G │   │
                └───┴───┴───┘
            """
        );
        itens.add(espada);

        Item espadaMadeira = new Item("Espada de Madeira",
            "Primeira arma básica",
            MinecraftEdition.BOTH, "Arma");
        espadaMadeira.adicionarIngrediente("2x Tábuas");
        espadaMadeira.adicionarIngrediente("1x Graveto");
        itens.add(espadaMadeira);

        Item espadaFerro = new Item("Espada de Ferro",
            "Espada intermediária confiável",
            MinecraftEdition.BOTH, "Arma");
        espadaFerro.adicionarIngrediente("2x Lingote de Ferro");
        espadaFerro.adicionarIngrediente("1x Graveto");
        itens.add(espadaFerro);

        Item espadaNetherite = new Item("Espada de Netherite",
            "A melhor espada do jogo, extremamente poderosa",
            MinecraftEdition.BOTH, "Arma");
        espadaNetherite.adicionarIngrediente("1x Espada de Diamante");
        espadaNetherite.adicionarIngrediente("1x Lingote de Netherite");
        itens.add(espadaNetherite);

        Item arco = new Item("Arco",
            "Arma de longo alcance que dispara flechas",
            MinecraftEdition.BOTH, "Arma");
        arco.adicionarIngrediente("3x Graveto");
        arco.adicionarIngrediente("3x Linha");
        arco.setPadraoCrafting(
            """
                ┌───┬───┬───┐
                │   │ G │ L │
                ├───┼───┼───┤
                │ G │   │ L │
                ├───┼───┼───┤
                │   │ G │ L │
                └───┴───┴───┘
            """
        );
        itens.add(arco);

        // Besta
        Item besta = new Item("Besta",
            "Arma de longo alcance que dispara flechas mais poderosas",
            MinecraftEdition.BOTH, "Arma");
        besta.adicionarIngrediente("3x Graveto");
        besta.adicionarIngrediente("2x Linha");
        besta.adicionarIngrediente("1x Anel de Ferro");
        besta.adicionarIngrediente("1x Gatilho");
        itens.add(besta);

        // Tridente
        Item tridente = new Item("Tridente",
            "Arma corpo-a-corpo e de longo alcance, obtida de Afogados",
            MinecraftEdition.BOTH, "Arma");
        tridente.adicionarIngrediente("Drop de Afogado (não craftável)");
        itens.add(tridente);

        // Flechas
        Item flecha = new Item("Flecha",
            "Munição para arcos e bestas",
            MinecraftEdition.BOTH, "Arma");
        flecha.adicionarIngrediente("1x Sílex");
        flecha.adicionarIngrediente("1x Graveto");
        flecha.adicionarIngrediente("1x Pena");
        itens.add(flecha);

        Item flechaEspectral = new Item("Flecha Espectral",
            "Flecha que aplica efeito de brilho no alvo",
            MinecraftEdition.BOTH, "Arma");
        flechaEspectral.adicionarIngrediente("1x Flecha");
        flechaEspectral.adicionarIngrediente("4x Pó de Glowstone");
        itens.add(flechaEspectral);

        // ==================== ARMADURAS ====================

        // Conjunto de Couro
        Item capaceteCouro = new Item("Capacete de Couro",
            "Proteção básica para a cabeça",
            MinecraftEdition.BOTH, "Armadura");
        capaceteCouro.adicionarIngrediente("5x Couro");
        itens.add(capaceteCouro);

        Item peitoralCouro = new Item("Peitoral de Couro",
            "Proteção básica para o torso",
            MinecraftEdition.BOTH, "Armadura");
        peitoralCouro.adicionarIngrediente("8x Couro");
        itens.add(peitoralCouro);

        Item calcasCouro = new Item("Calças de Couro",
            "Proteção básica para as pernas",
            MinecraftEdition.BOTH, "Armadura");
        calcasCouro.adicionarIngrediente("7x Couro");
        itens.add(calcasCouro);

        Item botasCouro = new Item("Botas de Couro",
            "Proteção básica para os pés",
            MinecraftEdition.BOTH, "Armadura");
        botasCouro.adicionarIngrediente("4x Couro");
        itens.add(botasCouro);

        // Conjunto de Ferro
        Item capaceteFerro = new Item("Capacete de Ferro",
            "Proteção intermediária para a cabeça",
            MinecraftEdition.BOTH, "Armadura");
        capaceteFerro.adicionarIngrediente("5x Lingote de Ferro");
        itens.add(capaceteFerro);

        Item peitoralFerro = new Item("Peitoral de Ferro",
            "Proteção para o torso",
            MinecraftEdition.BOTH, "Armadura");
        peitoralFerro.adicionarIngrediente("8x Lingote de Ferro");
        itens.add(peitoralFerro);

        Item calcasFerro = new Item("Calças de Ferro",
            "Proteção para as pernas",
            MinecraftEdition.BOTH, "Armadura");
        calcasFerro.adicionarIngrediente("7x Lingote de Ferro");
        itens.add(calcasFerro);

        Item botasFerro = new Item("Botas de Ferro",
            "Proteção para os pés",
            MinecraftEdition.BOTH, "Armadura");
        botasFerro.adicionarIngrediente("4x Lingote de Ferro");
        itens.add(botasFerro);

        // Diamante
        Item capaceteDiamante = new Item("Capacete de Diamante",
            "Proteção avançada para cabeça",
            MinecraftEdition.BOTH, "Armadura");
        capaceteDiamante.adicionarIngrediente("5x Diamante");
        itens.add(capaceteDiamante);

        Item peitoralDiamante = new Item("Peitoral de Diamante",
            "Proteção avançada para torso",
            MinecraftEdition.BOTH, "Armadura");
        peitoralDiamante.adicionarIngrediente("8x Diamante");
        itens.add(peitoralDiamante);

        Item calcasDiamante = new Item("Calças de Diamante",
            "Proteção avançada para pernas",
            MinecraftEdition.BOTH, "Armadura");
        calcasDiamante.adicionarIngrediente("7x Diamante");
        itens.add(calcasDiamante);

        Item botasDiamante = new Item("Botas de Diamante",
            "Proteção avançada para pés",
            MinecraftEdition.BOTH, "Armadura");
        botasDiamante.adicionarIngrediente("4x Diamante");
        itens.add(botasDiamante);

        // BLOCOS
        Item bancadaCrafting = new Item("Bancada de Crafting",
            "Mesa usada para criar itens com receitas 3x3",
            MinecraftEdition.BOTH, "Bloco Utilitário");
        bancadaCrafting.adicionarIngrediente("4x Tábuas de Madeira");
        itens.add(bancadaCrafting);

        Item fornalha = new Item("Fornalha",
            "Bloco usado para fundir minérios e cozinhar alimentos",
            MinecraftEdition.BOTH, "Bloco Utilitário");
        fornalha.adicionarIngrediente("8x Pedregulho");
        fornalha.setPadraoCrafting(
            """
                ┌───┬───┬───┐
                │ P │ P │ P │
                ├───┼───┼───┤
                │ P │   │ P │
                ├───┼───┼───┤
                │ P │ P │ P │
                └───┴───┴───┘
            """
        );
        itens.add(fornalha);

        Item bau = new Item("Baú",
            "Bloco de armazenamento com 27 espaços",
            MinecraftEdition.BOTH, "Bloco de Armazenamento");
        bau.adicionarIngrediente("8x Tábuas de Madeira");
        bau.setPadraoCrafting(
            """
                ┌───┬───┬───┐
                │ T │ T │ T │
                ├───┼───┼───┤
                │ T │   │ T │
                ├───┼───┼───┤
                │ T │ T │ T │
                └───┴───┴───┘
            """
        );
        itens.add(bau);

        // ARMADURAS
        Item elmoDeNetherite = new Item("Elmo de Netherite",
            "Proteção para cabeça, a melhor armadura do jogo",
            MinecraftEdition.BOTH, "Armadura");
        elmoDeNetherite.adicionarIngrediente("1x Elmo de Diamante");
        elmoDeNetherite.adicionarIngrediente("1x Lingote de Netherite");
        elmoDeNetherite.setPadraoCrafting(
            """
                Mesa de Ferraria (Smithing Table)
                ┌──────────────────┐
                │ Elmo de Diamante │
                │        +         │
                │ Lingote Netherite│
                └──────────────────┘
            """
        );
        itens.add(elmoDeNetherite);

        Item peitoralNetherite = new Item("Peitoral de Netherite",
            "A melhor proteção para o torso",
            MinecraftEdition.BOTH, "Armadura");
        peitoralNetherite.adicionarIngrediente("1x Peitoral de Diamante");
        peitoralNetherite.adicionarIngrediente("1x Lingote de Netherite");
        itens.add(peitoralNetherite);

        Item calcasNetherite = new Item("Calças de Netherite",
            "A melhor proteção para as pernas",
            MinecraftEdition.BOTH, "Armadura");
        calcasNetherite.adicionarIngrediente("1x Calças de Diamante");
        calcasNetherite.adicionarIngrediente("1x Lingote de Netherite");
        itens.add(calcasNetherite);

        Item botasNetherite = new Item("Botas de Netherite",
            "A melhor proteção para os pés",
            MinecraftEdition.BOTH, "Armadura");
        botasNetherite.adicionarIngrediente("1x Botas de Diamante");
        botasNetherite.adicionarIngrediente("1x Lingote de Netherite");
        itens.add(botasNetherite);

        // Escudo
        Item escudo = new Item("Escudo",
            "Bloqueia ataques e projéteis",
            MinecraftEdition.BOTH, "Armadura");
        escudo.adicionarIngrediente("6x Tábuas");
        escudo.adicionarIngrediente("1x Lingote de Ferro");
        itens.add(escudo);

        // Netherite já foi adicionado antes...

        // BLOCOS UTILITÁRIOS EXTRAS
        Item mesaEncantamento = new Item("Mesa de Encantamento",
            "Aplica encantamentos em itens",
            MinecraftEdition.BOTH, "Bloco Utilitário");
        mesaEncantamento.adicionarIngrediente("1x Livro");
        mesaEncantamento.adicionarIngrediente("2x Diamante");
        mesaEncantamento.adicionarIngrediente("4x Obsidiana");
        itens.add(mesaEncantamento);

        Item suportePocoes = new Item("Suporte de Poções",
            "Prepara poções com ingredientes",
            MinecraftEdition.BOTH, "Bloco Utilitário");
        suportePocoes.adicionarIngrediente("1x Vara de Blaze");
        suportePocoes.adicionarIngrediente("3x Pedregulho");
        itens.add(suportePocoes);


        // ALIMENTOS
        Item pao = new Item("Pão",
            "Alimento básico feito de trigo",
            MinecraftEdition.BOTH, "Alimento");
        pao.adicionarIngrediente("3x Trigo");
        itens.add(pao);

        Item macaDourada = new Item("Maçã Dourada",
            "Alimento especial que concede regeneração",
            MinecraftEdition.BOTH, "Alimento");
        macaDourada.adicionarIngrediente("1x Maçã");
        macaDourada.adicionarIngrediente("8x Lingote de Ouro");
        itens.add(macaDourada);

        Item paoDeOuro = new Item("Maçã Dourada Encantada",
            "Alimento especial que concede efeitos",
            MinecraftEdition.BOTH, "Alimento");
        paoDeOuro.adicionarIngrediente("8x Maçã");
        paoDeOuro.adicionarIngrediente("1x Bloco de Ouro");
        paoDeOuro.setPadraoCrafting(
            """
                ┌───┬───┬───┐
                │ M │ M │ M │
                ├───┼───┼───┤
                │ M │ O │ M │
                ├───┼───┼───┤
                │ M │ M │ M │
                └───┴───┴───┘
            """
        );
        itens.add(paoDeOuro);

        Item bolo = new Item("Bolo",
            "Alimento decorativo que pode ser colocado",
            MinecraftEdition.BOTH, "Alimento");
        bolo.adicionarIngrediente("3x Balde de Leite");
        bolo.adicionarIngrediente("2x Açúcar");
        bolo.adicionarIngrediente("1x Ovo");
        bolo.adicionarIngrediente("3x Trigo");
        bolo.setPadraoCrafting(
            """
                ┌───┬───┬───┐
                │ L │ L │ L │
                ├───┼───┼───┤
                │ A │ E │ A │
                ├───┼───┼───┤
                │ T │ T │ T │
                └───┴───┴───┘
            """
        );
        itens.add(bolo);

        // REDSTONE
        Item pistao = new Item("Pistão",
            "Bloco de redstone que empurra outros blocos",
            MinecraftEdition.BOTH, "Redstone");
        pistao.adicionarIngrediente("3x Tábuas de Madeira");
        pistao.adicionarIngrediente("4x Pedregulho");
        pistao.adicionarIngrediente("1x Lingote de Ferro");
        pistao.adicionarIngrediente("1x Pó de Redstone");
        pistao.setPadraoCrafting(
            """
                ┌───┬───┬───┐
                │ T │ T │ T │
                ├───┼───┼───┤
                │ P │ F │ P │
                ├───┼───┼───┤
                │ P │ R │ P │
                └───┴───┴───┘
            """
        );
        itens.add(pistao);

        Item repetidor = new Item("Repetidor de Redstone",
            "Atrasa e amplifica sinal de redstone",
            MinecraftEdition.BOTH, "Redstone");
        repetidor.adicionarIngrediente("2x Tocha de Redstone");
        repetidor.adicionarIngrediente("1x Pó de Redstone");
        repetidor.adicionarIngrediente("3x Pedra");
        repetidor.setPadraoCrafting(
            """
                ┌───┬───┬───┐
                │   │ T │   │
                ├───┼───┼───┤
                │ T │ R │ T │
                ├───┼───┼───┤
                │ P │ P │ P │
                └───┴───┴───┘
            """
        );
        itens.add(repetidor);

        // TRANSPORTES
        Item carrinho = new Item("Carrinho de Mina",
            "Veículo que se move em trilhos",
            MinecraftEdition.BOTH, "Transporte");
        carrinho.adicionarIngrediente("5x Lingote de Ferro");
        carrinho.setPadraoCrafting(
            """
                ┌───┬───┬───┐
                │ F │   │ F │
                ├───┼───┼───┤
                │ F │ F │ F │
                ├───┼───┼───┤
                │   │   │   │
                └───┴───┴───┘
            """
        );
        itens.add(carrinho);

        Item barco = new Item("Barco",
            "Veículo aquático",
            MinecraftEdition.BOTH, "Transporte");
        barco.adicionarIngrediente("5x Tábuas de Madeira");
        barco.setPadraoCrafting(
            """
                ┌───┬───┬───┐
                │ T │   │ T │
                ├───┼───┼───┤
                │ T │ T │ T │
                ├───┼───┼───┤
                │   │   │   │
                └───┴───┴───┘
            """
        );
        itens.add(barco);

        // ITENS ESPECIAIS
        Item totemdaimortalidade = new Item("Totem da Imortalidade",
            "Salva o jogador da morte uma vez",
            MinecraftEdition.BOTH, "Item Especial");
        totemdaimortalidade.adicionarIngrediente("Obtido de Evocadores (não craftável)");
        totemdaimortalidade.setPadraoCrafting(
            """
                ⚠ NÃO É CRAFTÁVEL ⚠
                Obtido ao derrotar Evocadores
                encontrados em Mansões e Raids
            """
        );
        itens.add(totemdaimortalidade);

        Item estreladofogo = new Item("Estrela do Fogo",
            "Item raro usado para criar o Beacon",
            MinecraftEdition.BOTH, "Item Especial");
        estreladofogo.adicionarIngrediente("3x Cabeças de Wither Skeleton");
        estreladofogo.adicionarIngrediente("4x Areia das Almas");
        estreladofogo.setPadraoCrafting(
            """
                Invoque o Wither Boss:
                ┌───┬───┬───┐
                │   │ A │   │  A = Areia das Almas
                ├───┼───┼───┤  C = Cabeça Wither
                │ A │ A │ A │
                ├───┼───┼───┤
                │ C │ C │ C │
                └───┴───┴───┘
                Derrote o Wither para obter
            """
        );
        itens.add(estreladofogo);

        // ==================== BLOCOS BÁSICOS ====================
        
        Item terra = new Item("Terra",
            "Bloco básico encontrado em abundância",
            MinecraftEdition.BOTH, "Bloco Natural");
        terra.adicionarIngrediente("Encontrado naturalmente");
        itens.add(terra);

        Item pedra = new Item("Pedra",
            "Bloco sólido obtido minerando pedregulho",
            MinecraftEdition.BOTH, "Bloco Natural");
        pedra.adicionarIngrediente("Fundir Pedregulho");
        itens.add(pedra);

        Item pedregulho = new Item("Pedregulho",
            "Bloco comum obtido minerando pedra",
            MinecraftEdition.BOTH, "Bloco Natural");
        pedregulho.adicionarIngrediente("Minerar Pedra");
        itens.add(pedregulho);

        Item areia = new Item("Areia",
            "Bloco afetado pela gravidade",
            MinecraftEdition.BOTH, "Bloco Natural");
        areia.adicionarIngrediente("Encontrado em praias e desertos");
        itens.add(areia);

        Item cascalho = new Item("Cascalho",
            "Bloco que pode dropar sílex",
            MinecraftEdition.BOTH, "Bloco Natural");
        cascalho.adicionarIngrediente("Encontrado naturalmente");
        itens.add(cascalho);

        Item madeira = new Item("Tronco de Madeira",
            "Bloco obtido de árvores",
            MinecraftEdition.BOTH, "Bloco Natural");
        madeira.adicionarIngrediente("Derrubar árvores");
        itens.add(madeira);

        Item tabuas = new Item("Tábuas de Madeira",
            "Material básico de construção",
            MinecraftEdition.BOTH, "Bloco Construção");
        tabuas.adicionarIngrediente("1x Tronco de Madeira");
        itens.add(tabuas);

        Item vidro = new Item("Vidro",
            "Bloco transparente",
            MinecraftEdition.BOTH, "Bloco Construção");
        vidro.adicionarIngrediente("Fundir Areia");
        itens.add(vidro);

        Item obsidiana = new Item("Obsidiana",
            "Bloco extremamente resistente",
            MinecraftEdition.BOTH, "Bloco Natural");
        obsidiana.adicionarIngrediente("Água + Lava (fonte)");
        itens.add(obsidiana);

        Item la = new Item("Lã",
            "Bloco decorativo colorível",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        la.adicionarIngrediente("4x Linha");
        itens.add(la);

        // ==================== RECURSOS E MATERIAIS ====================

        // MINÉRIOS
        Item minerioCarvao = new Item("Minério de Carvão",
            "Minério encontrado em cavernas e montanhas",
            MinecraftEdition.BOTH, "Minério");
        minerioCarvao.adicionarIngrediente("Encontrado em Y 0-256");
        itens.add(minerioCarvao);

        Item carvao = new Item("Carvão",
            "Combustível comum para fornalha",
            MinecraftEdition.BOTH, "Material");
        carvao.adicionarIngrediente("Minerar Minério de Carvão");
        itens.add(carvao);

        Item minerioFerro = new Item("Minério de Ferro",
            "Minério comum para ferramentas",
            MinecraftEdition.BOTH, "Minério");
        minerioFerro.adicionarIngrediente("Encontrado em Y -64 a 72");
        itens.add(minerioFerro);

        Item ferroLingote = new Item("Lingote de Ferro",
            "Material para ferramentas e armaduras",
            MinecraftEdition.BOTH, "Lingote");
        ferroLingote.adicionarIngrediente("Fundir Minério de Ferro");
        itens.add(ferroLingote);

        Item minerioOuro = new Item("Minério de Ouro",
            "Minério raro para itens especiais",
            MinecraftEdition.BOTH, "Minério");
        minerioOuro.adicionarIngrediente("Encontrado em Y -64 a 32");
        itens.add(minerioOuro);

        Item ouroLingote = new Item("Lingote de Ouro",
            "Material valioso mas frágil",
            MinecraftEdition.BOTH, "Lingote");
        ouroLingote.adicionarIngrediente("Fundir Minério de Ouro");
        itens.add(ouroLingote);

        Item minerioDiamante = new Item("Minério de Diamante",
            "Minério muito raro e valioso",
            MinecraftEdition.BOTH, "Minério");
        minerioDiamante.adicionarIngrediente("Encontrado em Y -64 a 16");
        itens.add(minerioDiamante);

        Item diamante = new Item("Diamante",
            "Gema rara para itens poderosos",
            MinecraftEdition.BOTH, "Gema");
        diamante.adicionarIngrediente("Minerar Minério de Diamante (Y -64 a 16)");
        itens.add(diamante);

        Item minerioEsmeralda = new Item("Minério de Esmeralda",
            "Minério mais raro do jogo",
            MinecraftEdition.BOTH, "Minério");
        minerioEsmeralda.adicionarIngrediente("Encontrado apenas em biomas de montanha");
        itens.add(minerioEsmeralda);

        Item esmeralda = new Item("Esmeralda",
            "Moeda para comércio com aldeões",
            MinecraftEdition.BOTH, "Gema");
        esmeralda.adicionarIngrediente("Minerar Minério de Esmeralda");
        itens.add(esmeralda);

        Item minerioRedstone = new Item("Minério de Redstone",
            "Minério para circuitos e mecanismos",
            MinecraftEdition.BOTH, "Minério");
        minerioRedstone.adicionarIngrediente("Encontrado em Y -64 a 15");
        itens.add(minerioRedstone);

        Item redstone = new Item("Pó de Redstone",
            "Material para circuitos e mecanismos",
            MinecraftEdition.BOTH, "Material");
        redstone.adicionarIngrediente("Minerar Minério de Redstone");
        itens.add(redstone);

        Item minerioLapis = new Item("Minério de Lápis-lazúli",
            "Minério para corante azul",
            MinecraftEdition.BOTH, "Minério");
        minerioLapis.adicionarIngrediente("Encontrado em Y -64 a 64");
        itens.add(minerioLapis);

        Item lapisLazuli = new Item("Lápis-lazúli",
            "Corante azul e material de encantamento",
            MinecraftEdition.BOTH, "Gema");
        lapisLazuli.adicionarIngrediente("Minerar Minério de Lápis-lazúli");
        itens.add(lapisLazuli);

        Item minerioQuartzo = new Item("Minério de Quartzo do Nether",
            "Minério encontrado no Nether",
            MinecraftEdition.BOTH, "Minério");
        minerioQuartzo.adicionarIngrediente("Encontrado no Nether");
        itens.add(minerioQuartzo);

        Item quartzo = new Item("Quartzo do Nether",
            "Material do Nether para construção",
            MinecraftEdition.BOTH, "Gema");
        quartzo.adicionarIngrediente("Minerar Minério de Quartzo (Nether)");
        itens.add(quartzo);

        Item debrisAncestral = new Item("Debris Ancestral",
            "Minério mais raro do Nether",
            MinecraftEdition.BOTH, "Minério");
        debrisAncestral.adicionarIngrediente("Encontrado no Nether em Y 8-22");
        itens.add(debrisAncestral);

        Item sucataNetherite = new Item("Sucata de Netherite",
            "Fragmento raro do Nether",
            MinecraftEdition.BOTH, "Material");
        sucataNetherite.adicionarIngrediente("Fundir Debris Ancestral");
        itens.add(sucataNetherite);

        Item netherite = new Item("Lingote de Netherite",
            "Material mais forte do jogo",
            MinecraftEdition.BOTH, "Lingote");
        netherite.adicionarIngrediente("4x Sucata de Netherite + 4x Lingotes de Ouro");
        itens.add(netherite);

        Item minerioCobre = new Item("Minério de Cobre",
            "Minério para construção e elétrica",
            MinecraftEdition.BOTH, "Minério");
        minerioCobre.adicionarIngrediente("Encontrado em Y -16 a 112");
        itens.add(minerioCobre);

        Item cobreLingote = new Item("Lingote de Cobre",
            "Material para construção e lightning rods",
            MinecraftEdition.BOTH, "Lingote");
        cobreLingote.adicionarIngrediente("Fundir Minério de Cobre");
        itens.add(cobreLingote);

        // MATERIAIS ESPECIAIS
        Item perola = new Item("Pérola do Ender",
            "Item para teleporte",
            MinecraftEdition.BOTH, "Material");
        perola.adicionarIngrediente("Dropar de Enderman");
        itens.add(perola);

        Item olhoEnder = new Item("Olho de Ender",
            "Item para localizar stronghold",
            MinecraftEdition.BOTH, "Material");
        olhoEnder.adicionarIngrediente("Pérola do Ender + Pó de Blaze");
        itens.add(olhoEnder);

        Item poBlaze = new Item("Pó de Blaze",
            "Ingrediente de poções",
            MinecraftEdition.BOTH, "Material");
        poBlaze.adicionarIngrediente("Dropar de Blaze");
        itens.add(poBlaze);

        Item vareteBlaze = new Item("Varete de Blaze",
            "Material para suporte de poções",
            MinecraftEdition.BOTH, "Material");
        vareteBlaze.adicionarIngrediente("Dropar de Blaze");
        itens.add(vareteBlaze);

        Item linha = new Item("Linha",
            "Material para arco e pesca",
            MinecraftEdition.BOTH, "Material");
        linha.adicionarIngrediente("Matar aranhas ou quebrar teias");
        itens.add(linha);

        Item couro = new Item("Couro",
            "Material para armadura leve",
            MinecraftEdition.BOTH, "Material");
        couro.adicionarIngrediente("Matar vacas, cavalos ou lhamas");
        itens.add(couro);

        Item pena = new Item("Pena",
            "Material para flechas",
            MinecraftEdition.BOTH, "Material");
        pena.adicionarIngrediente("Matar galinhas");
        itens.add(pena);

        Item osso = new Item("Osso",
            "Material para farinha de osso",
            MinecraftEdition.BOTH, "Material");
        osso.adicionarIngrediente("Matar esqueletos");
        itens.add(osso);

        Item polvora = new Item("Pólvora",
            "Material para TNT e fogos",
            MinecraftEdition.BOTH, "Material");
        polvora.adicionarIngrediente("Matar Creepers, Ghasts ou Bruxas");
        itens.add(polvora);

        Item silex = new Item("Sílex",
            "Material para flechas e pederneira",
            MinecraftEdition.BOTH, "Material");
        silex.adicionarIngrediente("Minerar cascalho (chance)");
        itens.add(silex);

        // ==================== ALIMENTOS ====================

        Item maca = new Item("Maçã",
            "Alimento básico restaura 4 de fome",
            MinecraftEdition.BOTH, "Alimento");
        maca.adicionarIngrediente("Dropar de árvores de carvalho");
        itens.add(maca);

        Item carneCrua = new Item("Carne Crua",
            "Alimento cru de animais",
            MinecraftEdition.BOTH, "Alimento");
        carneCrua.adicionarIngrediente("Matar vacas ou porcos");
        itens.add(carneCrua);

        Item carneAssada = new Item("Bife",
            "Alimento cozido restaura 8 de fome",
            MinecraftEdition.BOTH, "Alimento");
        carneAssada.adicionarIngrediente("Cozinhar Carne Crua");
        itens.add(carneAssada);

        Item frangoCru = new Item("Frango Cru",
            "Alimento de galinha",
            MinecraftEdition.BOTH, "Alimento");
        frangoCru.adicionarIngrediente("Matar galinhas");
        itens.add(frangoCru);

        Item frangoAssado = new Item("Frango Assado",
            "Alimento cozido restaura 6 de fome",
            MinecraftEdition.BOTH, "Alimento");
        frangoAssado.adicionarIngrediente("Cozinhar Frango Cru");
        itens.add(frangoAssado);

        Item peixeCru = new Item("Peixe Cru",
            "Alimento obtido pescando",
            MinecraftEdition.BOTH, "Alimento");
        peixeCru.adicionarIngrediente("Pescar");
        itens.add(peixeCru);

        Item peixeAssado = new Item("Peixe Assado",
            "Alimento cozido restaura 5 de fome",
            MinecraftEdition.BOTH, "Alimento");
        peixeAssado.adicionarIngrediente("Cozinhar Peixe Cru");
        itens.add(peixeAssado);

        Item cenoura = new Item("Cenoura",
            "Alimento vegetal restaura 3 de fome",
            MinecraftEdition.BOTH, "Alimento");
        cenoura.adicionarIngrediente("Plantar e colher ou dropar de zumbis");
        itens.add(cenoura);

        Item batata = new Item("Batata",
            "Alimento vegetal básico",
            MinecraftEdition.BOTH, "Alimento");
        batata.adicionarIngrediente("Plantar e colher ou dropar de zumbis");
        itens.add(batata);

        Item batataAssada = new Item("Batata Assada",
            "Alimento cozido restaura 5 de fome",
            MinecraftEdition.BOTH, "Alimento");
        batataAssada.adicionarIngrediente("Cozinhar Batata");
        itens.add(batataAssada);

        Item melancia = new Item("Fatia de Melancia",
            "Alimento leve restaura 2 de fome",
            MinecraftEdition.BOTH, "Alimento");
        melancia.adicionarIngrediente("Quebrar melancia");
        itens.add(melancia);

        Item abobora = new Item("Abóbora",
            "Bloco decorativo e alimento",
            MinecraftEdition.BOTH, "Alimento");
        abobora.adicionarIngrediente("Plantar e colher");
        itens.add(abobora);

        Item tortaAbobora = new Item("Torta de Abóbora",
            "Alimento feito com abóbora",
            MinecraftEdition.BOTH, "Alimento");
        tortaAbobora.adicionarIngrediente("Abóbora + Açúcar + Ovo");
        itens.add(tortaAbobora);

        Item trigo = new Item("Trigo",
            "Cultivo básico para pão",
            MinecraftEdition.BOTH, "Material");
        trigo.adicionarIngrediente("Plantar e colher sementes");
        itens.add(trigo);

        Item biscoito = new Item("Biscoito",
            "Alimento leve restaura 2 de fome",
            MinecraftEdition.BOTH, "Alimento");
        biscoito.adicionarIngrediente("2x Trigo + 1x Cacau");
        itens.add(biscoito);

        // ==================== UTILITÁRIOS ====================

        Item tocha = new Item("Tocha",
            "Fonte de luz básica",
            MinecraftEdition.BOTH, "Utilitário");
        tocha.adicionarIngrediente("1x Carvão + 1x Graveto");
        itens.add(tocha);

        Item cama = new Item("Cama",
            "Permite dormir e pular a noite",
            MinecraftEdition.BOTH, "Utilitário");
        cama.adicionarIngrediente("3x Lã + 3x Tábuas");
        itens.add(cama);

        Item escada = new Item("Escada",
            "Bloco para subir",
            MinecraftEdition.BOTH, "Utilitário");
        escada.adicionarIngrediente("7x Tábuas ou Pedregulho");
        itens.add(escada);

        Item porta = new Item("Porta",
            "Entrada que pode ser aberta/fechada",
            MinecraftEdition.BOTH, "Utilitário");
        porta.adicionarIngrediente("6x Tábuas");
        itens.add(porta);

        Item alçapao = new Item("Alçapão",
            "Porta horizontal",
            MinecraftEdition.BOTH, "Utilitário");
        alçapao.adicionarIngrediente("6x Tábuas");
        itens.add(alçapao);

        Item cerca = new Item("Cerca",
            "Barreira decorativa",
            MinecraftEdition.BOTH, "Utilitário");
        cerca.adicionarIngrediente("4x Graveto + 2x Tábuas");
        itens.add(cerca);

        Item portao = new Item("Portão",
            "Entrada em cercas",
            MinecraftEdition.BOTH, "Utilitário");
        portao.adicionarIngrediente("4x Graveto + 2x Tábuas");
        itens.add(portao);

        Item placa = new Item("Placa",
            "Item para escrever mensagens",
            MinecraftEdition.BOTH, "Utilitário");
        placa.adicionarIngrediente("6x Tábuas + 1x Graveto");
        itens.add(placa);

        Item balde = new Item("Balde",
            "Recipiente para líquidos",
            MinecraftEdition.BOTH, "Utilitário");
        balde.adicionarIngrediente("3x Lingote de Ferro");
        itens.add(balde);

        Item baldeAgua = new Item("Balde de Água",
            "Balde cheio de água",
            MinecraftEdition.BOTH, "Utilitário");
        baldeAgua.adicionarIngrediente("Balde + Fonte de Água");
        itens.add(baldeAgua);

        Item baldeLava = new Item("Balde de Lava",
            "Balde cheio de lava",
            MinecraftEdition.BOTH, "Utilitário");
        baldeLava.adicionarIngrediente("Balde + Lava");
        itens.add(baldeLava);

        Item baldeLeite = new Item("Balde de Leite",
            "Remove todos os efeitos",
            MinecraftEdition.BOTH, "Utilitário");
        baldeLeite.adicionarIngrediente("Ordenhar vaca com balde");
        itens.add(baldeLeite);

        Item papel = new Item("Papel",
            "Material para livros e mapas",
            MinecraftEdition.BOTH, "Material");
        papel.adicionarIngrediente("3x Cana-de-açúcar");
        itens.add(papel);

        Item livro = new Item("Livro",
            "Item para encantamentos",
            MinecraftEdition.BOTH, "Material");
        livro.adicionarIngrediente("3x Papel + 1x Couro");
        itens.add(livro);

        Item estante = new Item("Estante",
            "Bloco para mesa de encantamento",
            MinecraftEdition.BOTH, "Utilitário");
        estante.adicionarIngrediente("6x Tábuas + 3x Livros");
        itens.add(estante);

        Item mapa = new Item("Mapa",
            "Item para navegação",
            MinecraftEdition.BOTH, "Utilitário");
        mapa.adicionarIngrediente("8x Papel + 1x Bússola");
        itens.add(mapa);

        Item tnt = new Item("TNT",
            "Explosivo poderoso",
            MinecraftEdition.BOTH, "Utilitário");
        tnt.adicionarIngrediente("5x Pólvora + 4x Areia");
        itens.add(tnt);

        Item bigorna = new Item("Bigorna",
            "Repara e renomeia itens",
            MinecraftEdition.BOTH, "Utilitário");
        bigorna.adicionarIngrediente("3x Blocos de Ferro + 4x Lingotes de Ferro");
        itens.add(bigorna);

        Item beacon = new Item("Beacon",
            "Fonte de efeitos em área",
            MinecraftEdition.BOTH, "Utilitário");
        beacon.adicionarIngrediente("3x Obsidiana + 5x Vidro + 1x Estrela do Nether");
        itens.add(beacon);

        Item elytra = new Item("Élitros",
            "Asas para voar",
            MinecraftEdition.BOTH, "Item Especial");
        elytra.adicionarIngrediente("Encontrado em End Cities");
        itens.add(elytra);

        Item sela = new Item("Sela",
            "Permite montar cavalos",
            MinecraftEdition.BOTH, "Utilitário");
        sela.adicionarIngrediente("Encontrado em baús ou pescando");
        itens.add(sela);

        Item trilho = new Item("Trilho",
            "Caminho para carrinhos",
            MinecraftEdition.BOTH, "Utilitário");
        trilho.adicionarIngrediente("6x Lingote de Ferro + 1x Graveto");
        itens.add(trilho);

        // ==================== BLOCOS DO NETHER ====================
        
        Item netherrack = new Item("Netherrack",
            "Bloco principal do Nether, pega fogo eternamente",
            MinecraftEdition.BOTH, "Bloco Natural");
        netherrack.adicionarIngrediente("Encontrado no Nether");
        itens.add(netherrack);

        Item soulSand = new Item("Areia das Almas",
            "Bloco que reduz velocidade e faz bolhas na água",
            MinecraftEdition.BOTH, "Bloco Natural");
        soulSand.adicionarIngrediente("Encontrado no Vale das Almas (Nether)");
        itens.add(soulSand);

        Item glowstone = new Item("Pedra Luminosa",
            "Bloco luminoso do Nether",
            MinecraftEdition.BOTH, "Bloco Natural");
        glowstone.adicionarIngrediente("4x Pó de Pedra Luminosa");
        itens.add(glowstone);

        Item poGlowstone = new Item("Pó de Pedra Luminosa",
            "Usado para poções e iluminação",
            MinecraftEdition.BOTH, "Material");
        poGlowstone.adicionarIngrediente("Quebrar Pedra Luminosa");
        itens.add(poGlowstone);

        Item magmaBlock = new Item("Bloco de Magma",
            "Bloco que causa dano por fogo",
            MinecraftEdition.BOTH, "Bloco Natural");
        magmaBlock.adicionarIngrediente("4x Creme de Magma");
        itens.add(magmaBlock);

        Item cremeMagma = new Item("Creme de Magma",
            "Ingrediente de poções",
            MinecraftEdition.BOTH, "Material");
        cremeMagma.adicionarIngrediente("Dropar de Cubo de Magma");
        itens.add(cremeMagma);

        Item tijolonether = new Item("Tijolos do Nether",
            "Bloco decorativo do Nether",
            MinecraftEdition.BOTH, "Bloco Construção");
        tijolonether.adicionarIngrediente("4x Tijolos do Nether");
        itens.add(tijolonether);

        Item warteNether = new Item("Verruga do Nether",
            "Ingrediente essencial para poções",
            MinecraftEdition.BOTH, "Material");
        warteNether.adicionarIngrediente("Encontrado em Fortalezas do Nether");
        itens.add(warteNether);

        // ==================== BLOCOS DO END ====================
        
        Item endStone = new Item("Pedra do End",
            "Bloco principal da dimensão End",
            MinecraftEdition.BOTH, "Bloco Natural");
        endStone.adicionarIngrediente("Encontrado no End");
        itens.add(endStone);

        Item purpurBlock = new Item("Bloco de Purpur",
            "Bloco decorativo do End",
            MinecraftEdition.BOTH, "Bloco Construção");
        purpurBlock.adicionarIngrediente("4x Fruta Chorus Estourada");
        itens.add(purpurBlock);

        Item frutaChorus = new Item("Fruta Chorus",
            "Alimento que teleporta ao comer",
            MinecraftEdition.BOTH, "Alimento");
        frutaChorus.adicionarIngrediente("Quebrar plantas Chorus no End");
        itens.add(frutaChorus);

        Item endRod = new Item("Vareta do End",
            "Fonte de luz decorativa",
            MinecraftEdition.BOTH, "Utilitário");
        endRod.adicionarIngrediente("1x Pó de Blaze + 1x Fruta Chorus Estourada");
        itens.add(endRod);

        // ==================== FERRAMENTAS COMPLETAS ====================
        
        Item paMadeira = new Item("Pá de Madeira",
            "Ferramenta básica para cavar",
            MinecraftEdition.BOTH, "Ferramenta");
        paMadeira.adicionarIngrediente("1x Tábuas + 2x Graveto");
        itens.add(paMadeira);

        Item paPedra = new Item("Pá de Pedra",
            "Pá de pedra mais durável",
            MinecraftEdition.BOTH, "Ferramenta");
        paPedra.adicionarIngrediente("1x Pedregulho + 2x Graveto");
        itens.add(paPedra);

        Item paFerro = new Item("Pá de Ferro",
            "Pá de ferro eficiente",
            MinecraftEdition.BOTH, "Ferramenta");
        paFerro.adicionarIngrediente("1x Lingote de Ferro + 2x Graveto");
        itens.add(paFerro);

        Item paOuro = new Item("Pá de Ouro",
            "Pá rápida mas frágil",
            MinecraftEdition.BOTH, "Ferramenta");
        paOuro.adicionarIngrediente("1x Lingote de Ouro + 2x Graveto");
        itens.add(paOuro);

        Item paNetherite = new Item("Pá de Netherite",
            "Pá mais forte e durável",
            MinecraftEdition.BOTH, "Ferramenta");
        paNetherite.adicionarIngrediente("Pá de Diamante + Lingote de Netherite (Mesa de Ferraria)");
        itens.add(paNetherite);

        Item enxadaMadeira = new Item("Enxada de Madeira",
            "Ferramenta para agricultura",
            MinecraftEdition.BOTH, "Ferramenta");
        enxadaMadeira.adicionarIngrediente("2x Tábuas + 2x Graveto");
        itens.add(enxadaMadeira);

        Item enxadaPedra = new Item("Enxada de Pedra",
            "Enxada de pedra",
            MinecraftEdition.BOTH, "Ferramenta");
        enxadaPedra.adicionarIngrediente("2x Pedregulho + 2x Graveto");
        itens.add(enxadaPedra);

        Item enxadaFerro = new Item("Enxada de Ferro",
            "Enxada de ferro",
            MinecraftEdition.BOTH, "Ferramenta");
        enxadaFerro.adicionarIngrediente("2x Lingote de Ferro + 2x Graveto");
        itens.add(enxadaFerro);

        Item enxadaOuro = new Item("Enxada de Ouro",
            "Enxada de ouro",
            MinecraftEdition.BOTH, "Ferramenta");
        enxadaOuro.adicionarIngrediente("2x Lingote de Ouro + 2x Graveto");
        itens.add(enxadaOuro);

        Item enxadaNetherite = new Item("Enxada de Netherite",
            "Enxada mais forte",
            MinecraftEdition.BOTH, "Ferramenta");
        enxadaNetherite.adicionarIngrediente("Enxada de Diamante + Lingote de Netherite (Mesa de Ferraria)");
        itens.add(enxadaNetherite);

        Item machadoPedra = new Item("Machado de Pedra",
            "Machado de pedra",
            MinecraftEdition.BOTH, "Ferramenta");
        machadoPedra.adicionarIngrediente("3x Pedregulho + 2x Graveto");
        itens.add(machadoPedra);

        Item machadoFerro = new Item("Machado de Ferro",
            "Machado de ferro eficiente",
            MinecraftEdition.BOTH, "Ferramenta");
        machadoFerro.adicionarIngrediente("3x Lingote de Ferro + 2x Graveto");
        itens.add(machadoFerro);

        Item machadoOuro = new Item("Machado de Ouro",
            "Machado rápido mas frágil",
            MinecraftEdition.BOTH, "Ferramenta");
        machadoOuro.adicionarIngrediente("3x Lingote de Ouro + 2x Graveto");
        itens.add(machadoOuro);

        // ==================== MAIS ARMAS ====================
        
        Item espadaPedra = new Item("Espada de Pedra",
            "Espada básica de pedra",
            MinecraftEdition.BOTH, "Arma");
        espadaPedra.adicionarIngrediente("2x Pedregulho + 1x Graveto");
        itens.add(espadaPedra);

        Item espadaOuro = new Item("Espada de Ouro",
            "Espada de ouro com encantamentos melhores",
            MinecraftEdition.BOTH, "Arma");
        espadaOuro.adicionarIngrediente("2x Lingote de Ouro + 1x Graveto");
        itens.add(espadaOuro);

        // ==================== ARMADURAS OURO ====================
        
        Item capaceteOuro = new Item("Capacete de Ouro",
            "Proteção básica de ouro",
            MinecraftEdition.BOTH, "Armadura");
        capaceteOuro.adicionarIngrediente("5x Lingote de Ouro");
        itens.add(capaceteOuro);

        Item peitoralOuro = new Item("Peitoral de Ouro",
            "Peitoral de ouro",
            MinecraftEdition.BOTH, "Armadura");
        peitoralOuro.adicionarIngrediente("8x Lingote de Ouro");
        itens.add(peitoralOuro);

        Item calcasOuro = new Item("Calças de Ouro",
            "Calças de ouro",
            MinecraftEdition.BOTH, "Armadura");
        calcasOuro.adicionarIngrediente("7x Lingote de Ouro");
        itens.add(calcasOuro);

        Item botasOuro = new Item("Botas de Ouro",
            "Botas de ouro",
            MinecraftEdition.BOTH, "Armadura");
        botasOuro.adicionarIngrediente("4x Lingote de Ouro");
        itens.add(botasOuro);

        // ==================== MAIS BLOCOS UTILITÁRIOS ====================
        
        Item caldeirão = new Item("Caldeirão",
            "Armazena água, lava ou poções",
            MinecraftEdition.BOTH, "Utilitário");
        caldeirão.adicionarIngrediente("7x Lingote de Ferro");
        itens.add(caldeirão);

        Item compostor = new Item("Compostor",
            "Transforma itens em farinha de osso",
            MinecraftEdition.BOTH, "Utilitário");
        compostor.adicionarIngrediente("7x Tábuas");
        itens.add(compostor);

        Item barril = new Item("Barril",
            "Armazenamento alternativo ao baú",
            MinecraftEdition.BOTH, "Utilitário");
        barril.adicionarIngrediente("6x Tábuas + 2x Lajes de Madeira");
        itens.add(barril);

        Item funil = new Item("Funil",
            "Transfere itens entre containers",
            MinecraftEdition.BOTH, "Redstone");
        funil.adicionarIngrediente("5x Lingote de Ferro + 1x Baú");
        itens.add(funil);

        Item dispensador = new Item("Dispensador",
            "Dispara itens automaticamente",
            MinecraftEdition.BOTH, "Redstone");
        dispensador.adicionarIngrediente("7x Pedregulho + 1x Arco + 1x Pó de Redstone");
        itens.add(dispensador);

        Item ejetor = new Item("Ejetor",
            "Ejeta itens",
            MinecraftEdition.BOTH, "Redstone");
        ejetor.adicionarIngrediente("7x Pedregulho + 1x Pó de Redstone");
        itens.add(ejetor);

        Item observador = new Item("Observador",
            "Detecta mudanças de blocos",
            MinecraftEdition.BOTH, "Redstone");
        observador.adicionarIngrediente("6x Pedregulho + 2x Pó de Redstone + 1x Quartzo");
        itens.add(observador);

        Item comparador = new Item("Comparador de Redstone",
            "Compara sinais de redstone",
            MinecraftEdition.BOTH, "Redstone");
        comparador.adicionarIngrediente("3x Tocha de Redstone + 3x Pedra + 1x Quartzo");
        itens.add(comparador);

        Item torchaRedstone = new Item("Tocha de Redstone",
            "Fonte de sinal de redstone",
            MinecraftEdition.BOTH, "Redstone");
        torchaRedstone.adicionarIngrediente("1x Pó de Redstone + 1x Graveto");
        itens.add(torchaRedstone);

        Item alavanca = new Item("Alavanca",
            "Interruptor de redstone",
            MinecraftEdition.BOTH, "Redstone");
        alavanca.adicionarIngrediente("1x Graveto + 1x Pedregulho");
        itens.add(alavanca);

        Item botao = new Item("Botão",
            "Ativa redstone temporariamente",
            MinecraftEdition.BOTH, "Redstone");
        botao.adicionarIngrediente("1x Pedra ou Tábua");
        itens.add(botao);

        Item placaPressao = new Item("Placa de Pressão",
            "Ativada ao pisar",
            MinecraftEdition.BOTH, "Redstone");
        placaPressao.adicionarIngrediente("2x Tábuas ou Pedra");
        itens.add(placaPressao);

        // ==================== MAIS ALIMENTOS ====================
        
        Item carnePorco = new Item("Costeleta de Porco Crua",
            "Carne crua de porco",
            MinecraftEdition.BOTH, "Alimento");
        carnePorco.adicionarIngrediente("Matar porcos");
        itens.add(carnePorco);

        Item costeletaAssada = new Item("Costeleta de Porco Assada",
            "Restaura 8 de fome",
            MinecraftEdition.BOTH, "Alimento");
        costeletaAssada.adicionarIngrediente("Cozinhar Costeleta Crua");
        itens.add(costeletaAssada);

        Item carneiro = new Item("Carneiro Cru",
            "Carne de ovelha",
            MinecraftEdition.BOTH, "Alimento");
        carneiro.adicionarIngrediente("Matar ovelhas");
        itens.add(carneiro);

        Item carneiroAssado = new Item("Carneiro Assado",
            "Restaura 6 de fome",
            MinecraftEdition.BOTH, "Alimento");
        carneiroAssado.adicionarIngrediente("Cozinhar Carneiro Cru");
        itens.add(carneiroAssado);

        Item coelho = new Item("Coelho Cru",
            "Carne de coelho",
            MinecraftEdition.BOTH, "Alimento");
        coelho.adicionarIngrediente("Matar coelhos");
        itens.add(coelho);

        Item coelhoAssado = new Item("Coelho Assado",
            "Restaura 5 de fome",
            MinecraftEdition.BOTH, "Alimento");
        coelhoAssado.adicionarIngrediente("Cozinhar Coelho Cru");
        itens.add(coelhoAssado);

        Item bacalhau = new Item("Bacalhau Cru",
            "Peixe comum",
            MinecraftEdition.BOTH, "Alimento");
        bacalhau.adicionarIngrediente("Pescar");
        itens.add(bacalhau);

        Item bacalhauAssado = new Item("Bacalhau Cozido",
            "Restaura 5 de fome",
            MinecraftEdition.BOTH, "Alimento");
        bacalhauAssado.adicionarIngrediente("Cozinhar Bacalhau");
        itens.add(bacalhauAssado);

        Item salmao = new Item("Salmão Cru",
            "Peixe nutritivo",
            MinecraftEdition.BOTH, "Alimento");
        salmao.adicionarIngrediente("Pescar");
        itens.add(salmao);

        Item salmaoAssado = new Item("Salmão Cozido",
            "Restaura 6 de fome",
            MinecraftEdition.BOTH, "Alimento");
        salmaoAssado.adicionarIngrediente("Cozinhar Salmão");
        itens.add(salmaoAssado);

        Item batataVenenosa = new Item("Batata Venenosa",
            "Chance de envenenar",
            MinecraftEdition.BOTH, "Alimento");
        batataVenenosa.adicionarIngrediente("Colher batatas (chance rara)");
        itens.add(batataVenenosa);

        Item carnePodre = new Item("Carne Podre",
            "Causa fome ao comer",
            MinecraftEdition.BOTH, "Alimento");
        carnePodre.adicionarIngrediente("Dropar de zumbis");
        itens.add(carnePodre);

        Item olhoAranha = new Item("Olho de Aranha",
            "Ingrediente de poções (venenoso)",
            MinecraftEdition.BOTH, "Material");
        olhoAranha.adicionarIngrediente("Matar aranhas");
        itens.add(olhoAranha);

        Item olhoFermentado = new Item("Olho de Aranha Fermentado",
            "Corrompe poções",
            MinecraftEdition.BOTH, "Material");
        olhoFermentado.adicionarIngrediente("Olho de Aranha + Açúcar + Cogumelo Marrom");
        itens.add(olhoFermentado);

        Item acucar = new Item("Açúcar",
            "Ingrediente de poções e comidas",
            MinecraftEdition.BOTH, "Material");
        acucar.adicionarIngrediente("1x Cana-de-açúcar");
        itens.add(acucar);

        Item ovo = new Item("Ovo",
            "Ingrediente de receitas",
            MinecraftEdition.BOTH, "Material");
        ovo.adicionarIngrediente("Galinhas botam ovos");
        itens.add(ovo);

        Item leite = new Item("Leite",
            "Remove efeitos de status",
            MinecraftEdition.BOTH, "Alimento");
        leite.adicionarIngrediente("Ordenhar vaca com balde");
        itens.add(leite);

        // ==================== ITENS ESPECIAIS ====================
        
        Item dragaoOvo = new Item("Ovo do Dragão",
            "Troféu por derrotar o Ender Dragon",
            MinecraftEdition.BOTH, "Item Especial");
        dragaoOvo.adicionarIngrediente("Derrotar o Ender Dragon");
        itens.add(dragaoOvo);

        Item estrelaFogo = new Item("Estrela do Nether",
            "Item para criar Beacon",
            MinecraftEdition.BOTH, "Material");
        estrelaFogo.adicionarIngrediente("Derrotar o Wither");
        itens.add(estrelaFogo);

        Item concha = new Item("Concha de Nautilus",
            "Ingrediente do Conduit",
            MinecraftEdition.BOTH, "Material");
        concha.adicionarIngrediente("Pescar ou dropar de Afogados");
        itens.add(concha);

        Item coracaoMar = new Item("Coração do Mar",
            "Item raro para Conduit",
            MinecraftEdition.BOTH, "Material");
        coracaoMar.adicionarIngrediente("Encontrado em tesouros enterrados");
        itens.add(coracaoMar);

        Item conduit = new Item("Conduit",
            "Estrutura subaquática que dá efeitos",
            MinecraftEdition.BOTH, "Item Especial");
        conduit.adicionarIngrediente("8x Concha de Nautilus + 1x Coração do Mar");
        itens.add(conduit);

        Item graveto = new Item("Graveto",
            "Material básico para ferramentas",
            MinecraftEdition.BOTH, "Material");
        graveto.adicionarIngrediente("2x Tábuas");
        itens.add(graveto);

        Item corda = new Item("Corda",
            "Amarra animais e cria cercas",
            MinecraftEdition.BOTH, "Utilitário");
        corda.adicionarIngrediente("4x Linha + 1x Slimeball");
        itens.add(corda);

        Item sliméball = new Item("Slimeball",
            "Material pegajoso",
            MinecraftEdition.BOTH, "Material");
        sliméball.adicionarIngrediente("Matar Slimes");
        itens.add(sliméball);

        Item blocoSlime = new Item("Bloco de Slime",
            "Bloco que pula e gruda pistões",
            MinecraftEdition.BOTH, "Bloco Construção");
        blocoSlime.adicionarIngrediente("9x Slimeball");
        itens.add(blocoSlime);

        Item blocoMel = new Item("Bloco de Mel",
            "Bloco pegajoso que reduz velocidade",
            MinecraftEdition.BOTH, "Bloco Construção");
        blocoMel.adicionarIngrediente("4x Garrafa de Mel");
        itens.add(blocoMel);

        Item frascoMel = new Item("Garrafa de Mel",
            "Remove veneno e restaura fome",
            MinecraftEdition.BOTH, "Alimento");
        frascoMel.adicionarIngrediente("Usar garrafa em colmeia cheia");
        itens.add(frascoMel);

        // ==================== BLOCOS DE CONSTRUÇÃO DECORATIVOS ====================
        
        Item concreto = new Item("Concreto",
            "Bloco sólido colorido para construção",
            MinecraftEdition.BOTH, "Bloco Construção");
        concreto.adicionarIngrediente("Pó de Concreto + Água");
        itens.add(concreto);

        Item poConcreto = new Item("Pó de Concreto",
            "Cai como areia, vira concreto com água",
            MinecraftEdition.BOTH, "Bloco Construção");
        poConcreto.adicionarIngrediente("4x Areia + 4x Cascalho + 1x Corante");
        itens.add(poConcreto);

        Item terracota = new Item("Terracota",
            "Bloco de argila cozida",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        terracota.adicionarIngrediente("Cozinhar Argila");
        itens.add(terracota);

        Item argila = new Item("Argila",
            "Bloco encontrado debaixo d'água",
            MinecraftEdition.BOTH, "Bloco Natural");
        argila.adicionarIngrediente("Encontrado em rios e lagos");
        itens.add(argila);

        Item blocoArgila = new Item("Bloco de Argila",
            "4 bolas de argila",
            MinecraftEdition.BOTH, "Material");
        blocoArgila.adicionarIngrediente("Quebrar blocos de argila");
        itens.add(blocoArgila);

        Item tijolo = new Item("Tijolo",
            "Item de construção clássico",
            MinecraftEdition.BOTH, "Material");
        tijolo.adicionarIngrediente("Cozinhar Argila");
        itens.add(tijolo);

        Item blocoTijolo = new Item("Bloco de Tijolos",
            "Bloco decorativo resistente",
            MinecraftEdition.BOTH, "Bloco Construção");
        blocoTijolo.adicionarIngrediente("4x Tijolos");
        itens.add(blocoTijolo);

        Item vidroColorido = new Item("Vidro Colorido",
            "Vidro tingido com corantes",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroColorido.adicionarIngrediente("8x Vidro + 1x Corante");
        itens.add(vidroColorido);

        Item painelVidro = new Item("Painel de Vidro",
            "Versão fina do vidro",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidro.adicionarIngrediente("6x Vidro");
        itens.add(painelVidro);

        Item quartzoLiso = new Item("Bloco de Quartzo",
            "Bloco branco decorativo",
            MinecraftEdition.BOTH, "Bloco Construção");
        quartzoLiso.adicionarIngrediente("4x Quartzo do Nether");
        itens.add(quartzoLiso);

        Item prismarinho = new Item("Prismarinho",
            "Bloco dos monumentos oceânicos",
            MinecraftEdition.BOTH, "Bloco Natural");
        prismarinho.adicionarIngrediente("4x Fragmento de Prismarinho");
        itens.add(prismarinho);

        Item fragmentoPrismarinho = new Item("Fragmento de Prismarinho",
            "Material dos guardiões",
            MinecraftEdition.BOTH, "Material");
        fragmentoPrismarinho.adicionarIngrediente("Matar Guardiões");
        itens.add(fragmentoPrismarinho);

        Item cristalPrismarinho = new Item("Cristais de Prismarinho",
            "Item luminoso dos guardiões",
            MinecraftEdition.BOTH, "Material");
        cristalPrismarinho.adicionarIngrediente("Matar Guardiões");
        itens.add(cristalPrismarinho);

        Item lanternas = new Item("Lanterna",
            "Fonte de luz que pode ser pendurada",
            MinecraftEdition.BOTH, "Utilitário");
        lanternas.adicionarIngrediente("8x Pepitas de Ferro + 1x Tocha");
        itens.add(lanternas);

        Item lanternaMar = new Item("Lanterna do Mar",
            "Fonte de luz subaquática",
            MinecraftEdition.BOTH, "Utilitário");
        lanternaMar.adicionarIngrediente("4x Fragmento Prismarinho + 5x Cristais");
        itens.add(lanternaMar);

        // ==================== BLOCOS DE PEDRA VARIADOS ====================
        
        Item pedraTalhada = new Item("Pedra Talhada",
            "Pedra decorativa lisa",
            MinecraftEdition.BOTH, "Bloco Construção");
        pedraTalhada.adicionarIngrediente("Cozinhar Pedregulho");
        itens.add(pedraTalhada);

        Item tijolosPedra = new Item("Tijolos de Pedra",
            "Bloco decorativo de pedra",
            MinecraftEdition.BOTH, "Bloco Construção");
        tijolosPedra.adicionarIngrediente("4x Pedra");
        itens.add(tijolosPedra);

        Item ardosia = new Item("Ardósia",
            "Pedra escura decorativa",
            MinecraftEdition.BOTH, "Bloco Natural");
        ardosia.adicionarIngrediente("Encontrada em Y -64 a 0");
        itens.add(ardosia);

        Item tufoProfundo = new Item("Tufo Profundo",
            "Pedra resistente das profundezas",
            MinecraftEdition.BOTH, "Bloco Natural");
        tufoProfundo.adicionarIngrediente("Encontrado em Y -64 a -8");
        itens.add(tufoProfundo);

        Item andesito = new Item("Andesito",
            "Pedra cinza natural",
            MinecraftEdition.BOTH, "Bloco Natural");
        andesito.adicionarIngrediente("Encontrado naturalmente");
        itens.add(andesito);

        Item diorito = new Item("Diorito",
            "Pedra branca natural",
            MinecraftEdition.BOTH, "Bloco Natural");
        diorito.adicionarIngrediente("Encontrado naturalmente");
        itens.add(diorito);

        Item granito = new Item("Granito",
            "Pedra vermelha natural",
            MinecraftEdition.BOTH, "Bloco Natural");
        granito.adicionarIngrediente("Encontrado naturalmente");
        itens.add(granito);

        Item calcario = new Item("Calcário",
            "Pedra bege decorativa",
            MinecraftEdition.BOTH, "Bloco Natural");
        calcario.adicionarIngrediente("Encontrado em cavernas exuberantes");
        itens.add(calcario);

        // ==================== MADEIRAS VARIADAS ====================
        
        Item carvalho = new Item("Tronco de Carvalho",
            "Madeira mais comum",
            MinecraftEdition.BOTH, "Bloco Natural");
        carvalho.adicionarIngrediente("Cortar árvores de carvalho");
        itens.add(carvalho);

        Item betula = new Item("Tronco de Bétula",
            "Madeira branca",
            MinecraftEdition.BOTH, "Bloco Natural");
        betula.adicionarIngrediente("Cortar árvores de bétula");
        itens.add(betula);

        Item pinheiro = new Item("Tronco de Pinheiro",
            "Madeira escura",
            MinecraftEdition.BOTH, "Bloco Natural");
        pinheiro.adicionarIngrediente("Cortar árvores de pinheiro");
        itens.add(pinheiro);

        Item selva = new Item("Tronco da Selva",
            "Madeira da selva",
            MinecraftEdition.BOTH, "Bloco Natural");
        selva.adicionarIngrediente("Cortar árvores da selva");
        itens.add(selva);

        Item acacia = new Item("Tronco de Acácia",
            "Madeira laranja",
            MinecraftEdition.BOTH, "Bloco Natural");
        acacia.adicionarIngrediente("Cortar árvores de acácia");
        itens.add(acacia);

        Item carvalhoEscuro = new Item("Tronco de Carvalho Escuro",
            "Madeira muito escura",
            MinecraftEdition.BOTH, "Bloco Natural");
        carvalhoEscuro.adicionarIngrediente("Cortar árvores de carvalho escuro");
        itens.add(carvalhoEscuro);

        Item mangue = new Item("Tronco de Mangue",
            "Madeira de pântano",
            MinecraftEdition.BOTH, "Bloco Natural");
        mangue.adicionarIngrediente("Cortar árvores de mangue");
        itens.add(mangue);

        Item cerejeira = new Item("Tronco de Cerejeira",
            "Madeira rosa",
            MinecraftEdition.BOTH, "Bloco Natural");
        cerejeira.adicionarIngrediente("Cortar árvores de cerejeira");
        itens.add(cerejeira);

        Item carmesim = new Item("Caule Carmesim",
            "Madeira do Nether vermelha",
            MinecraftEdition.BOTH, "Bloco Natural");
        carmesim.adicionarIngrediente("Cortar fungos carmesim (Nether)");
        itens.add(carmesim);

        Item distorcido = new Item("Caule Distorcido",
            "Madeira do Nether azul",
            MinecraftEdition.BOTH, "Bloco Natural");
        distorcido.adicionarIngrediente("Cortar fungos distorcidos (Nether)");
        itens.add(distorcido);

        // ==================== ITENS DE FARM E AGRICULTURA ====================
        
        Item sementesTrigo = new Item("Sementes de Trigo",
            "Planta trigo",
            MinecraftEdition.BOTH, "Material");
        sementesTrigo.adicionarIngrediente("Quebrar grama alta");
        itens.add(sementesTrigo);

        Item sementesBeterraba = new Item("Sementes de Beterraba",
            "Planta beterraba",
            MinecraftEdition.BOTH, "Material");
        sementesBeterraba.adicionarIngrediente("Encontrar em vilas");
        itens.add(sementesBeterraba);

        Item beterraba = new Item("Beterraba",
            "Vegetal vermelho",
            MinecraftEdition.BOTH, "Alimento");
        beterraba.adicionarIngrediente("Cultivar beterraba");
        itens.add(beterraba);

        Item sopaBeterraba = new Item("Sopa de Beterraba",
            "Restaura 6 de fome",
            MinecraftEdition.BOTH, "Alimento");
        sopaBeterraba.adicionarIngrediente("6x Beterraba + 1x Tigela");
        itens.add(sopaBeterraba);

        Item cacau = new Item("Sementes de Cacau",
            "Produz cacau",
            MinecraftEdition.BOTH, "Material");
        cacau.adicionarIngrediente("Encontrado na selva");
        itens.add(cacau);

        Item chocolate = new Item("Grãos de Cacau",
            "Ingrediente de biscoitos",
            MinecraftEdition.BOTH, "Material");
        chocolate.adicionarIngrediente("Colher cacau maduro");
        itens.add(chocolate);

        Item canaAcucar = new Item("Cana-de-açúcar",
            "Produz açúcar e papel",
            MinecraftEdition.BOTH, "Material");
        canaAcucar.adicionarIngrediente("Encontrada perto de água");
        itens.add(canaAcucar);

        Item bambu = new Item("Bambu",
            "Cresce rápido, combustível",
            MinecraftEdition.BOTH, "Material");
        bambu.adicionarIngrediente("Encontrado na selva de bambu");
        itens.add(bambu);

        Item cacto = new Item("Cacto",
            "Planta do deserto que causa dano",
            MinecraftEdition.BOTH, "Bloco Natural");
        cacto.adicionarIngrediente("Encontrado no deserto");
        itens.add(cacto);

        Item algaroba = new Item("Bagas Brilhantes",
            "Alimento e fonte de luz",
            MinecraftEdition.BOTH, "Alimento");
        algaroba.adicionarIngrediente("Encontradas em cavernas exuberantes");
        itens.add(algaroba);

        Item melanciaBloco = new Item("Melancia",
            "Bloco de melancia",
            MinecraftEdition.BOTH, "Bloco Natural");
        melanciaBloco.adicionarIngrediente("Plantar sementes de melancia");
        itens.add(melanciaBloco);

        Item sementesMelancia = new Item("Sementes de Melancia",
            "Planta melancia",
            MinecraftEdition.BOTH, "Material");
        sementesMelancia.adicionarIngrediente("Fatia de Melancia");
        itens.add(sementesMelancia);

        Item sementesAbobora = new Item("Sementes de Abóbora",
            "Planta abóbora",
            MinecraftEdition.BOTH, "Material");
        sementesAbobora.adicionarIngrediente("Abóbora");
        itens.add(sementesAbobora);

        Item jackolantern = new Item("Abóbora de Halloween",
            "Abóbora iluminada",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        jackolantern.adicionarIngrediente("Abóbora Esculpida + Tocha");
        itens.add(jackolantern);

        Item fardoFeno = new Item("Fardo de Feno",
            "Bloco de armazenamento de trigo",
            MinecraftEdition.BOTH, "Bloco Construção");
        fardoFeno.adicionarIngrediente("9x Trigo");
        itens.add(fardoFeno);

        // ==================== CORANTES ====================
        
        Item coranteVermelho = new Item("Corante Vermelho",
            "Tintura vermelha",
            MinecraftEdition.BOTH, "Material");
        coranteVermelho.adicionarIngrediente("Rosa Vermelha ou Beterraba");
        itens.add(coranteVermelho);

        Item coranteAmarelo = new Item("Corante Amarelo",
            "Tintura amarela",
            MinecraftEdition.BOTH, "Material");
        coranteAmarelo.adicionarIngrediente("Dente-de-leão");
        itens.add(coranteAmarelo);

        Item coranteVerde = new Item("Corante Verde",
            "Tintura verde",
            MinecraftEdition.BOTH, "Material");
        coranteVerde.adicionarIngrediente("Cozinhar Cacto");
        itens.add(coranteVerde);

        Item coranteBranco = new Item("Corante Branco",
            "Tintura branca",
            MinecraftEdition.BOTH, "Material");
        coranteBranco.adicionarIngrediente("Farinha de Osso ou Lírio");
        itens.add(coranteBranco);

        Item corantePreto = new Item("Corante Preto",
            "Tintura preta",
            MinecraftEdition.BOTH, "Material");
        corantePreto.adicionarIngrediente("Saco de Tinta");
        itens.add(corantePreto);

        Item sacoTinta = new Item("Saco de Tinta",
            "Tinta de lula",
            MinecraftEdition.BOTH, "Material");
        sacoTinta.adicionarIngrediente("Matar Lulas");
        itens.add(sacoTinta);

        Item sacoTintaBrilhante = new Item("Saco de Tinta Brilhante",
            "Tinta luminosa",
            MinecraftEdition.BOTH, "Material");
        sacoTintaBrilhante.adicionarIngrediente("Matar Lulas Brilhantes");
        itens.add(sacoTintaBrilhante);

        // ==================== ITENS DE EXPLORAÇÃO ====================
        
        Item luneta = new Item("Luneta",
            "Permite dar zoom",
            MinecraftEdition.BOTH, "Utilitário");
        luneta.adicionarIngrediente("2x Lingote de Cobre + 1x Cristal de Ametista");
        itens.add(luneta);

        Item cristalAmetista = new Item("Cristal de Ametista",
            "Gema roxa de geodos",
            MinecraftEdition.BOTH, "Gema");
        cristalAmetista.adicionarIngrediente("Quebrar blocos de ametista");
        itens.add(cristalAmetista);

        Item fragmentoAmetista = new Item("Fragmento de Ametista",
            "Pedaço de cristal",
            MinecraftEdition.BOTH, "Material");
        fragmentoAmetista.adicionarIngrediente("Quebrar cristais de ametista");
        itens.add(fragmentoAmetista);

        Item blocoAmetista = new Item("Bloco de Ametista",
            "Bloco decorativo roxo",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        blocoAmetista.adicionarIngrediente("4x Fragmento de Ametista");
        itens.add(blocoAmetista);

        Item poDeCobre = new Item("Pó de Cobre",
            "Material oxidado",
            MinecraftEdition.BOTH, "Material");
        poDeCobre.adicionarIngrediente("Raspar Cobre Oxidado");
        itens.add(poDeCobre);

        Item paraRaios = new Item("Para-raios",
            "Atrai raios",
            MinecraftEdition.BOTH, "Utilitário");
        paraRaios.adicionarIngrediente("3x Lingote de Cobre");
        itens.add(paraRaios);

        Item sino = new Item("Sino",
            "Item decorativo de vilas",
            MinecraftEdition.BOTH, "Utilitário");
        sino.adicionarIngrediente("3x Lingote de Ouro + 1x Graveto + 2x Tábuas");
        itens.add(sino);

        Item ancora = new Item("Âncora de Respawn",
            "Define ponto de respawn no Nether",
            MinecraftEdition.BOTH, "Utilitário");
        ancora.adicionarIngrediente("6x Choro de Obsidiana + 3x Pedra Luminosa");
        itens.add(ancora);

        Item choroObsidiana = new Item("Choro de Obsidiana",
            "Obsidiana luminosa do Nether",
            MinecraftEdition.BOTH, "Bloco Natural");
        choroObsidiana.adicionarIngrediente("Encontrada em ruínas do Nether");
        itens.add(choroObsidiana);

        Item respawnAnchor = new Item("Lodestone",
            "Ancora bússolas para localização",
            MinecraftEdition.BOTH, "Utilitário");
        respawnAnchor.adicionarIngrediente("8x Pedregulho Talhado + 1x Lingote de Netherite");
        itens.add(respawnAnchor);

        Item bussolaLodestone = new Item("Bússola de Lodestone",
            "Aponta para lodestone",
            MinecraftEdition.BOTH, "Utilitário");
        bussolaLodestone.adicionarIngrediente("Bússola + Lodestone (usar)");
        itens.add(bussolaLodestone);

        Item bussolaRecuperacao = new Item("Bússola de Recuperação",
            "Aponta para último local de morte",
            MinecraftEdition.BOTH, "Utilitário");
        bussolaRecuperacao.adicionarIngrediente("8x Fragmento Eco + 1x Bússola");
        itens.add(bussolaRecuperacao);

        Item fragmentoEco = new Item("Fragmento de Eco",
            "Material das profundezas",
            MinecraftEdition.BOTH, "Material");
        fragmentoEco.adicionarIngrediente("Encontrado em Ancient Cities");
        itens.add(fragmentoEco);

        Item disco = new Item("Disco de Música",
            "Toca música em jukeboxes",
            MinecraftEdition.BOTH, "Item Especial");
        disco.adicionarIngrediente("Creeper morto por Esqueleto ou encontrado em baús");
        itens.add(disco);

        Item jukebox = new Item("Jukebox",
            "Reproduz discos de música",
            MinecraftEdition.BOTH, "Utilitário");
        jukebox.adicionarIngrediente("8x Tábuas + 1x Diamante");
        itens.add(jukebox);

        Item livroPena = new Item("Livro e Pena",
            "Permite escrever",
            MinecraftEdition.BOTH, "Utilitário");
        livroPena.adicionarIngrediente("1x Livro + 1x Pena + 1x Saco de Tinta");
        itens.add(livroPena);

        Item armaduraSupport = new Item("Suporte de Armadura",
            "Exibe armaduras e itens",
            MinecraftEdition.BOTH, "Utilitário");
        armaduraSupport.adicionarIngrediente("6x Graveto + 1x Laje de Pedra");
        itens.add(armaduraSupport);

        Item quadro = new Item("Quadro",
            "Decoração para paredes",
            MinecraftEdition.BOTH, "Utilitário");
        quadro.adicionarIngrediente("8x Graveto + 1x Couro");
        itens.add(quadro);

        Item pintura = new Item("Bandeira",
            "Decoração personalizável",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        pintura.adicionarIngrediente("6x Lã + 1x Graveto");
        itens.add(pintura);

        Item tear = new Item("Tear",
            "Cria padrões em bandeiras",
            MinecraftEdition.BOTH, "Utilitário");
        tear.adicionarIngrediente("2x Linha + 2x Tábuas");
        itens.add(tear);

        Item tingidor = new Item("Caldeirão",
            "Usado para tingir em Bedrock",
            MinecraftEdition.BEDROCK, "Utilitário");
        tingidor.adicionarIngrediente("7x Lingote de Ferro");
        itens.add(tingidor);

        // ==================== PICARETAS NOVAS ====================
        
        Item picaretaOuroNova = new Item("Picareta de Ouro",
            "Picareta rápida mas frágil",
            MinecraftEdition.BOTH, "Picareta");
        picaretaOuroNova.adicionarIngrediente("3x Lingote de Ouro + 2x Graveto");
        itens.add(picaretaOuroNova);

        // ==================== MACHADOS NOVOS ====================
        
        Item machadoPedraNovo = new Item("Machado de Pedra",
            "Machado intermediário",
            MinecraftEdition.BOTH, "Machado");
        machadoPedraNovo.adicionarIngrediente("3x Pedregulho + 2x Graveto");
        itens.add(machadoPedraNovo);

        Item machadoFerroNovo = new Item("Machado de Ferro",
            "Machado avançado",
            MinecraftEdition.BOTH, "Machado");
        machadoFerroNovo.adicionarIngrediente("3x Lingote de Ferro + 2x Graveto");
        itens.add(machadoFerroNovo);

        Item machadoOuroNovo = new Item("Machado de Ouro",
            "Machado rápido mas frágil",
            MinecraftEdition.BOTH, "Machado");
        machadoOuroNovo.adicionarIngrediente("3x Lingote de Ouro + 2x Graveto");
        itens.add(machadoOuroNovo);

        // ==================== PÁS NOVAS ====================
        
        Item paMadeiraNova = new Item("Pá de Madeira",
            "Pá básica para escavar",
            MinecraftEdition.BOTH, "Pá");
        paMadeiraNova.adicionarIngrediente("1x Tábua + 2x Graveto");
        itens.add(paMadeiraNova);

        Item paPedraNova = new Item("Pá de Pedra",
            "Pá intermediária",
            MinecraftEdition.BOTH, "Pá");
        paPedraNova.adicionarIngrediente("1x Pedregulho + 2x Graveto");
        itens.add(paPedraNova);

        Item paFerroNova = new Item("Pá de Ferro",
            "Pá avançada",
            MinecraftEdition.BOTH, "Pá");
        paFerroNova.adicionarIngrediente("1x Lingote de Ferro + 2x Graveto");
        itens.add(paFerroNova);

        Item paOuroNova = new Item("Pá de Ouro",
            "Pá rápida mas frágil",
            MinecraftEdition.BOTH, "Pá");
        paOuroNova.adicionarIngrediente("1x Lingote de Ouro + 2x Graveto");
        itens.add(paOuroNova);

        Item paNetheriteNova = new Item("Pá de Netherite",
            "A melhor pá do jogo",
            MinecraftEdition.BOTH, "Pá");
        paNetheriteNova.adicionarIngrediente("1x Pá de Diamante + 1x Lingote de Netherite");
        itens.add(paNetheriteNova);

        // ==================== ENXADAS NOVAS ====================
        
        Item enxadaMadeiraNova = new Item("Enxada de Madeira",
            "Enxada básica para agricultura",
            MinecraftEdition.BOTH, "Enxada");
        enxadaMadeiraNova.adicionarIngrediente("2x Tábua + 2x Graveto");
        itens.add(enxadaMadeiraNova);

        Item enxadaPedraNova = new Item("Enxada de Pedra",
            "Enxada intermediária",
            MinecraftEdition.BOTH, "Enxada");
        enxadaPedraNova.adicionarIngrediente("2x Pedregulho + 2x Graveto");
        itens.add(enxadaPedraNova);

        Item enxadaFerroNova = new Item("Enxada de Ferro",
            "Enxada avançada",
            MinecraftEdition.BOTH, "Enxada");
        enxadaFerroNova.adicionarIngrediente("2x Lingote de Ferro + 2x Graveto");
        itens.add(enxadaFerroNova);

        Item enxadaOuroNova = new Item("Enxada de Ouro",
            "Enxada rápida mas frágil",
            MinecraftEdition.BOTH, "Enxada");
        enxadaOuroNova.adicionarIngrediente("2x Lingote de Ouro + 2x Graveto");
        itens.add(enxadaOuroNova);

        Item enxadaNetheriteNova = new Item("Enxada de Netherite",
            "A melhor enxada do jogo",
            MinecraftEdition.BOTH, "Enxada");
        enxadaNetheriteNova.adicionarIngrediente("1x Enxada de Diamante + 1x Lingote de Netherite");
        itens.add(enxadaNetheriteNova);

        // ==================== FERRAMENTAS ESPECIAIS NOVAS ====================
        
        Item cenouraVaraNova = new Item("Vara com Cenoura",
            "Controla porcos",
            MinecraftEdition.BOTH, "Ferramenta Especial");
        cenouraVaraNova.adicionarIngrediente("1x Vara de Pesca + 1x Cenoura");
        itens.add(cenouraVaraNova);

        Item fungoVaraNova = new Item("Vara com Fungo Distorcido",
            "Controla striders",
            MinecraftEdition.BOTH, "Ferramenta Especial");
        fungoVaraNova.adicionarIngrediente("1x Vara de Pesca + 1x Fungo Distorcido");
        itens.add(fungoVaraNova);

        Item escovaNova = new Item("Escova",
            "Revela itens em blocos suspeitos",
            MinecraftEdition.BOTH, "Ferramenta Especial");
        escovaNova.adicionarIngrediente("1x Pena + 1x Lingote de Cobre + 1x Graveto");
        itens.add(escovaNova);

        // ==================== ESPADAS COMPLETAS NOVAS ====================
        
        Item espadaMadeiraNova = new Item("Espada de Madeira",
            "Espada básica",
            MinecraftEdition.BOTH, "Espada");
        espadaMadeiraNova.adicionarIngrediente("2x Tábua + 1x Graveto");
        itens.add(espadaMadeiraNova);

        Item espadaPedraNova = new Item("Espada de Pedra",
            "Espada intermediária",
            MinecraftEdition.BOTH, "Espada");
        espadaPedraNova.adicionarIngrediente("2x Pedregulho + 1x Graveto");
        itens.add(espadaPedraNova);

        Item espadaFerroNova = new Item("Espada de Ferro",
            "Espada avançada",
            MinecraftEdition.BOTH, "Espada");
        espadaFerroNova.adicionarIngrediente("2x Lingote de Ferro + 1x Graveto");
        itens.add(espadaFerroNova);

        Item espadaOuroNova = new Item("Espada de Ouro",
            "Espada rápida mas frágil",
            MinecraftEdition.BOTH, "Espada");
        espadaOuroNova.adicionarIngrediente("2x Lingote de Ouro + 1x Graveto");
        itens.add(espadaOuroNova);

        Item espadaDiamanteNova = new Item("Espada de Diamante",
            "Espada poderosa",
            MinecraftEdition.BOTH, "Espada");
        espadaDiamanteNova.adicionarIngrediente("2x Diamante + 1x Graveto");
        itens.add(espadaDiamanteNova);

        Item espadaNetheriteNova = new Item("Espada de Netherite",
            "A melhor espada do jogo",
            MinecraftEdition.BOTH, "Espada");
        espadaNetheriteNova.adicionarIngrediente("1x Espada de Diamante + 1x Lingote de Netherite");
        itens.add(espadaNetheriteNova);

        // ==================== ARCOS E BESTAS NOVOS ====================
        
        Item arcoNovo = new Item("Arco",
            "Arma de longo alcance",
            MinecraftEdition.BOTH, "Arco");
        arcoNovo.adicionarIngrediente("3x Graveto + 3x Linha");
        itens.add(arcoNovo);

        Item bestaNova = new Item("Besta",
            "Arco automático mais poderoso",
            MinecraftEdition.BOTH, "Besta");
        bestaNova.adicionarIngrediente("3x Graveto + 2x Linha + 1x Gancho de Armadilha + 1x Anel de Ferro");
        itens.add(bestaNova);

        Item flechaNova = new Item("Flecha",
            "Munição para arco e besta",
            MinecraftEdition.BOTH, "Arma de Longo Alcance");
        flechaNova.adicionarIngrediente("1x Sílex + 1x Graveto + 1x Pena");
        itens.add(flechaNova);

        Item flechaEspectralNova = new Item("Flecha Espectral",
            "Ilumina o alvo",
            MinecraftEdition.BOTH, "Arma de Longo Alcance");
        flechaEspectralNova.adicionarIngrediente("4x Pó de Pedra Luminosa + 1x Flecha");
        itens.add(flechaEspectralNova);

        Item tridenteNovo = new Item("Tridente",
            "Arma corpo a corpo e longo alcance",
            MinecraftEdition.BOTH, "Arma de Longo Alcance");
        tridenteNovo.adicionarIngrediente("Drop de Afogados");
        itens.add(tridenteNovo);

        // ==================== BLOCOS FUNCIONAIS NOVOS ====================
        
        Item fornalhaNova = new Item("Fornalha",
            "Funde minérios e cozinha alimentos",
            MinecraftEdition.BOTH, "Bloco Funcional");
        fornalhaNova.adicionarIngrediente("8x Pedregulho");
        itens.add(fornalhaNova);

        Item altoFornoNovo = new Item("Alto-forno",
            "Funde minérios 2x mais rápido",
            MinecraftEdition.BOTH, "Bloco Funcional");
        altoFornoNovo.adicionarIngrediente("5x Lingote de Ferro + 1x Fornalha + 3x Pedra Lisa");
        itens.add(altoFornoNovo);

        Item defumadorNovo = new Item("Defumador",
            "Cozinha alimentos 2x mais rápido",
            MinecraftEdition.BOTH, "Bloco Funcional");
        defumadorNovo.adicionarIngrediente("4x Tronco + 1x Fornalha");
        itens.add(defumadorNovo);

        Item bancadaNova = new Item("Bancada de Trabalho",
            "Grade de crafting 3x3",
            MinecraftEdition.BOTH, "Bloco Funcional");
        bancadaNova.adicionarIngrediente("4x Tábuas");
        itens.add(bancadaNova);

        Item mesaEncantamentoNova = new Item("Mesa de Encantamento",
            "Encanta itens com magia",
            MinecraftEdition.BOTH, "Bloco Funcional");
        mesaEncantamentoNova.adicionarIngrediente("4x Obsidiana + 2x Diamante + 1x Livro");
        itens.add(mesaEncantamentoNova);

        Item bigovaNova = new Item("Bigorna",
            "Repara e combina itens",
            MinecraftEdition.BOTH, "Bloco Funcional");
        bigovaNova.adicionarIngrediente("3x Bloco de Ferro + 4x Lingote de Ferro");
        itens.add(bigovaNova);

        Item pedraAmolarNova = new Item("Pedra de Amolar",
            "Repara itens gastando menos XP",
            MinecraftEdition.BOTH, "Bloco Funcional");
        pedraAmolarNova.adicionarIngrediente("2x Graveto + 1x Laje de Pedra + 2x Tábuas");
        itens.add(pedraAmolarNova);

        Item suporteArmaduraNovo = new Item("Suporte de Armadura",
            "Exibe armaduras e equipamentos",
            MinecraftEdition.BOTH, "Bloco Funcional");
        suporteArmaduraNovo.adicionarIngrediente("6x Graveto + 1x Laje de Pedra");
        itens.add(suporteArmaduraNovo);

        Item compostorNovo = new Item("Compostor",
            "Converte itens vegetais em farinha de osso",
            MinecraftEdition.BOTH, "Bloco Funcional");
        compostorNovo.adicionarIngrediente("7x Laje de Madeira");
        itens.add(compostorNovo);

        Item barrilNovo = new Item("Barril",
            "Armazena itens como um baú",
            MinecraftEdition.BOTH, "Bloco Funcional");
        barrilNovo.adicionarIngrediente("6x Tábuas + 2x Laje de Madeira");
        itens.add(barrilNovo);

        // ==================== MAIS ITENS ESSENCIAIS ====================
        
        Item blocoComandos = new Item("Bloco de Comandos",
            "Executa comandos automaticamente (apenas modo criativo/operador)",
            MinecraftEdition.BOTH, "Bloco Especial");
        blocoComandos.adicionarIngrediente("Obter com /give @p command_block");
        itens.add(blocoComandos);

        Item estruturaVazia = new Item("Bloco de Estrutura",
            "Salva e carrega estruturas (modo criativo)",
            MinecraftEdition.BOTH, "Bloco Especial");
        estruturaVazia.adicionarIngrediente("Obter com /give @p structure_block");
        itens.add(estruturaVazia);

        Item barreira = new Item("Barreira",
            "Bloco invisível e indestrutível (modo criativo)",
            MinecraftEdition.BOTH, "Bloco Especial");
        barreira.adicionarIngrediente("Obter com /give @p barrier");
        itens.add(barreira);

        Item luz = new Item("Bloco de Luz",
            "Fonte de luz invisível configurável (modo criativo)",
            MinecraftEdition.BOTH, "Bloco Especial");
        luz.adicionarIngrediente("Obter com /give @p light");
        itens.add(luz);

        Item shulkerBox = new Item("Caixa de Shulker",
            "Baú portátil que mantém itens ao quebrar",
            MinecraftEdition.BOTH, "Bloco de Armazenamento");
        shulkerBox.adicionarIngrediente("2x Casca de Shulker + 1x Baú");
        itens.add(shulkerBox);

        Item enderChest = new Item("Baú do Ender",
            "Armazenamento pessoal acessível de qualquer lugar",
            MinecraftEdition.BOTH, "Bloco de Armazenamento");
        enderChest.adicionarIngrediente("8x Obsidiana + 1x Olho do Ender");
        itens.add(enderChest);

        Item anvilNew = new Item("Bigorna",
            "Repara, renomeia e combina encantamentos de itens",
            MinecraftEdition.BOTH, "Bloco Funcional");
        anvilNew.adicionarIngrediente("3x Bloco de Ferro + 4x Lingote de Ferro");
        itens.add(anvilNew);

        Item mesaFerraria = new Item("Mesa de Ferraria",
            "Melhora diamante para netherite e adiciona enfeites",
            MinecraftEdition.BOTH, "Bloco Funcional");
        mesaFerraria.adicionarIngrediente("2x Lingote de Ferro + 4x Tábuas");
        itens.add(mesaFerraria);

        Item mesaCartucho = new Item("Mesa de Cartografia",
            "Cria, clona e expande mapas",
            MinecraftEdition.BOTH, "Bloco Funcional");
        mesaCartucho.adicionarIngrediente("2x Papel + 4x Tábuas");
        itens.add(mesaCartucho);

        Item pedraAmolar = new Item("Pedra de Amolar",
            "Repara itens sem gastar XP extra",
            MinecraftEdition.BOTH, "Bloco Funcional");
        pedraAmolar.adicionarIngrediente("2x Graveto + 1x Laje de Pedra + 2x Tábuas");
        itens.add(pedraAmolar);

        Item tecelagem = new Item("Tear",
            "Cria padrões complexos em bandeiras",
            MinecraftEdition.BOTH, "Bloco Funcional");
        tecelagem.adicionarIngrediente("2x Linha + 2x Tábuas");
        itens.add(tecelagem);

        Item mesaCortador = new Item("Cortador de Pedra",
            "Converte pedra em variações de forma eficiente",
            MinecraftEdition.BOTH, "Bloco Funcional");
        mesaCortador.adicionarIngrediente("3x Pedra + 1x Lingote de Ferro");
        itens.add(mesaCortador);

        Item escudoNovo = new Item("Escudo",
            "Bloqueia ataques corpo a corpo e à distância",
            MinecraftEdition.BOTH, "Armadura");
        escudoNovo.adicionarIngrediente("6x Tábuas + 1x Lingote de Ferro");
        itens.add(escudoNovo);

        Item livroPenaVelho = new Item("Livro e Pena",
            "Permite escrever livros personalizados",
            MinecraftEdition.BOTH, "Utilitário");
        livroPenaVelho.adicionarIngrediente("1x Livro + 1x Pena + 1x Saco de Tinta");
        itens.add(livroPenaVelho);

        Item livroEncantado = new Item("Livro Encantado",
            "Armazena encantamentos para combinar na bigorna",
            MinecraftEdition.BOTH, "Material");
        livroEncantado.adicionarIngrediente("Encantar um livro na mesa de encantamento");
        itens.add(livroEncantado);

        Item garrafa = new Item("Garrafa de Vidro",
            "Recipiente para poções e água",
            MinecraftEdition.BOTH, "Material");
        garrafa.adicionarIngrediente("3x Vidro");
        itens.add(garrafa);

        Item garrafaAgua = new Item("Garrafa de Água",
            "Base para criar poções",
            MinecraftEdition.BOTH, "Material");
        garrafaAgua.adicionarIngrediente("Garrafa + Fonte de Água");
        itens.add(garrafaAgua);

        Item barraExperiencia = new Item("Garrafa de Experiência",
            "Armazena XP para uso posterior",
            MinecraftEdition.BOTH, "Item Especial");
        barraExperiencia.adicionarIngrediente("Obtida de comércio com clérigos");
        itens.add(barraExperiencia);

        Item tabuleiro = new Item("Placa",
            "Permite escrever texto em blocos",
            MinecraftEdition.BOTH, "Utilitário");
        tabuleiro.adicionarIngrediente("6x Tábuas + 1x Graveto");
        itens.add(tabuleiro);

        Item placaSuspensa = new Item("Placa Suspensa",
            "Placa pendurada decorativa",
            MinecraftEdition.BOTH, "Utilitário");
        placaSuspensa.adicionarIngrediente("2x Corrente + 6x Tábuas Descascadas");
        itens.add(placaSuspensa);

        Item corrente = new Item("Corrente",
            "Decoração e âncora para lanternas",
            MinecraftEdition.BOTH, "Utilitário");
        corrente.adicionarIngrediente("2x Pepita de Ferro + 1x Lingote de Ferro");
        itens.add(corrente);

        Item estanteLivros = new Item("Estante de Livros",
            "Aumenta poder da mesa de encantamento (máx 15)",
            MinecraftEdition.BOTH, "Bloco Funcional");
        estanteLivros.adicionarIngrediente("6x Tábuas + 3x Livros");
        itens.add(estanteLivros);

        Item estanteEncantada = new Item("Estante Encantada",
            "Armazena livros encantados visualmente",
            MinecraftEdition.BOTH, "Bloco Funcional");
        estanteEncantada.adicionarIngrediente("6x Tábuas + 3x Lajes de Madeira");
        itens.add(estanteEncantada);

        Item quadroBrilhante = new Item("Quadro Brilhante",
            "Quadro que emite luz quando tem item",
            MinecraftEdition.BOTH, "Utilitário");
        quadroBrilhante.adicionarIngrediente("1x Quadro + 1x Saco de Tinta Brilhante");
        itens.add(quadroBrilhante);

        Item moldura = new Item("Moldura",
            "Exibe itens em paredes",
            MinecraftEdition.BOTH, "Utilitário");
        moldura.adicionarIngrediente("8x Graveto + 1x Couro");
        itens.add(moldura);

        Item blocoNota = new Item("Bloco Musical",
            "Emite notas musicais quando acionado",
            MinecraftEdition.BOTH, "Redstone");
        blocoNota.adicionarIngrediente("8x Tábuas + 1x Pó de Redstone");
        itens.add(blocoNota);

        Item blocoAlvo = new Item("Bloco Alvo",
            "Emite sinal de redstone ao ser atingido",
            MinecraftEdition.BOTH, "Redstone");
        blocoAlvo.adicionarIngrediente("4x Pó de Redstone + 1x Fardo de Feno");
        itens.add(blocoAlvo);

        Item sensor = new Item("Sensor Sculk",
            "Detecta vibrações e emite sinal de redstone",
            MinecraftEdition.BOTH, "Redstone");
        sensor.adicionarIngrediente("Minerar em Ancient Cities com Toque Suave");
        itens.add(sensor);

        Item catalystSculk = new Item("Catalisador Sculk",
            "Espalha sculk quando mobs morrem perto",
            MinecraftEdition.BOTH, "Bloco Natural");
        catalystSculk.adicionarIngrediente("Minerar em Ancient Cities com Toque Suave");
        itens.add(catalystSculk);

        Item shriekerSculk = new Item("Gritador Sculk",
            "Invoca o Warden após 4 ativações",
            MinecraftEdition.BOTH, "Bloco Natural");
        shriekerSculk.adicionarIngrediente("Minerar em Ancient Cities com Toque Suave");
        itens.add(shriekerSculk);

        Item cobre = new Item("Bloco de Cobre",
            "Bloco decorativo que oxida com o tempo",
            MinecraftEdition.BOTH, "Bloco Construção");
        cobre.adicionarIngrediente("9x Lingote de Cobre");
        itens.add(cobre);

        Item cobreCerado = new Item("Bloco de Cobre Cerado",
            "Bloco de cobre que não oxida",
            MinecraftEdition.BOTH, "Bloco Construção");
        cobreCerado.adicionarIngrediente("Bloco de Cobre + Favo de Mel");
        itens.add(cobreCerado);

        Item ametista = new Item("Bloco de Ametista",
            "Bloco decorativo de geodos",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        ametista.adicionarIngrediente("4x Fragmento de Ametista");
        itens.add(ametista);

        Item vidroAmetista = new Item("Vidro de Ametista",
            "Vidro translúcido roxo",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroAmetista.adicionarIngrediente("Minerar Broto de Ametista");
        itens.add(vidroAmetista);

        Item farolRespawn = new Item("Âncora de Respawn",
            "Define ponto de renascimento no Nether",
            MinecraftEdition.BOTH, "Utilitário");
        farolRespawn.adicionarIngrediente("6x Choro de Obsidiana + 3x Pedra Luminosa");
        itens.add(farolRespawn);

        Item magnetita = new Item("Magnetita (Lodestone)",
            "Ancora bússolas para localização fixa",
            MinecraftEdition.BOTH, "Utilitário");
        magnetita.adicionarIngrediente("8x Pedregulho Talhado + 1x Lingote de Netherite");
        itens.add(magnetita);

        Item campoForca = new Item("Conduit",
            "Dá poder debaixo d'água (visão, respiração, velocidade)",
            MinecraftEdition.BOTH, "Item Especial");
        campoForca.adicionarIngrediente("8x Concha de Nautilus + 1x Coração do Mar");
        itens.add(campoForca);

        Item sinalizador = new Item("Beacon (Sinalizador)",
            "Concede efeitos em área grande",
            MinecraftEdition.BOTH, "Item Especial");
        sinalizador.adicionarIngrediente("3x Obsidiana + 5x Vidro + 1x Estrela do Nether");
        itens.add(sinalizador);

        Item golem = new Item("Bloco de Ferro",
            "Construção para Golem de Ferro ou Beacon",
            MinecraftEdition.BOTH, "Bloco Construção");
        golem.adicionarIngrediente("9x Lingote de Ferro");
        itens.add(golem);

        Item blocoOuro = new Item("Bloco de Ouro",
            "Armazenamento compacto ou Beacon",
            MinecraftEdition.BOTH, "Bloco Construção");
        blocoOuro.adicionarIngrediente("9x Lingote de Ouro");
        itens.add(blocoOuro);

        Item blocoDiamante = new Item("Bloco de Diamante",
            "Armazenamento compacto ou Beacon",
            MinecraftEdition.BOTH, "Bloco Construção");
        blocoDiamante.adicionarIngrediente("9x Diamante");
        itens.add(blocoDiamante);

        Item blocoEsmeralda = new Item("Bloco de Esmeralda",
            "Armazenamento compacto ou Beacon",
            MinecraftEdition.BOTH, "Bloco Construção");
        blocoEsmeralda.adicionarIngrediente("9x Esmeralda");
        itens.add(blocoEsmeralda);

        Item blocoNetherite = new Item("Bloco de Netherite",
            "Armazenamento do material mais raro",
            MinecraftEdition.BOTH, "Bloco Construção");
        blocoNetherite.adicionarIngrediente("9x Lingote de Netherite");
        itens.add(blocoNetherite);

        Item blocoLapis = new Item("Bloco de Lápis-lazúli",
            "Armazenamento compacto de lapis",
            MinecraftEdition.BOTH, "Bloco Construção");
        blocoLapis.adicionarIngrediente("9x Lápis-lazúli");
        itens.add(blocoLapis);

        Item blocoRedstone = new Item("Bloco de Redstone",
            "Fonte permanente de sinal de redstone",
            MinecraftEdition.BOTH, "Redstone");
        blocoRedstone.adicionarIngrediente("9x Pó de Redstone");
        itens.add(blocoRedstone);

        Item blocoQuartzo = new Item("Bloco de Quartzo",
            "Bloco decorativo branco do Nether",
            MinecraftEdition.BOTH, "Bloco Construção");
        blocoQuartzo.adicionarIngrediente("4x Quartzo do Nether");
        itens.add(blocoQuartzo);

        Item blocoCarv = new Item("Bloco de Carvão",
            "Combustível ultra-eficiente",
            MinecraftEdition.BOTH, "Bloco Construção");
        blocoCarv.adicionarIngrediente("9x Carvão");
        itens.add(blocoCarv);

        Item secador = new Item("Esponja",
            "Absorve água em área grande",
            MinecraftEdition.BOTH, "Utilitário");
        secador.adicionarIngrediente("Encontrada em Monumentos Oceânicos");
        itens.add(secador);

        Item esponjaSeca = new Item("Esponja Seca",
            "Esponja pronta para absorver água novamente",
            MinecraftEdition.BOTH, "Utilitário");
        esponjaSeca.adicionarIngrediente("Cozinhar Esponja molhada");
        itens.add(esponjaSeca);

        Item polvora2 = new Item("TNT",
            "Explosivo potente",
            MinecraftEdition.BOTH, "Utilitário");
        polvora2.adicionarIngrediente("5x Pólvora + 4x Areia");
        itens.add(polvora2);

        Item pederneiraNova = new Item("Isqueiro (Pederneira)",
            "Cria fogo e aciona TNT",
            MinecraftEdition.BOTH, "Utilitário");
        pederneiraNova.adicionarIngrediente("1x Lingote de Ferro + 1x Sílex");
        itens.add(pederneiraNova);

        // ==================== ITENS DE COMBATE ADICIONAIS ====================

        Item fogo = new Item("Carga de Fogo",
            "Projétil usado por Blazes e Ghasts",
            MinecraftEdition.BOTH, "Material");
        fogo.adicionarIngrediente("Drop de Blaze ou Ghast");
        itens.add(fogo);

        Item bolaNeve = new Item("Bola de Neve",
            "Projétil que causa dano de knockback",
            MinecraftEdition.BOTH, "Arma");
        bolaNeve.adicionarIngrediente("Quebrar neve");
        itens.add(bolaNeve);

        Item ovoGalinha = new Item("Ovo",
            "Ingrediente ou projétil leve",
            MinecraftEdition.BOTH, "Material");
        ovoGalinha.adicionarIngrediente("Galinhas botam ovos");
        itens.add(ovoGalinha);

        Item perolaBrilho = new Item("Pérola do Ender",
            "Teleporta o jogador ao ser arremessada",
            MinecraftEdition.BOTH, "Item Especial");
        perolaBrilho.adicionarIngrediente("Drop de Enderman");
        itens.add(perolaBrilho);

        Item pocaoArremesso = new Item("Poção Arremessável",
            "Poção que pode ser lançada",
            MinecraftEdition.BOTH, "Poção");
        pocaoArremesso.adicionarIngrediente("Adicionar Pólvora a qualquer poção");
        itens.add(pocaoArremesso);

        Item pocaoLingering = new Item("Poção Persistente",
            "Cria nuvem de efeito ao arremessar",
            MinecraftEdition.BOTH, "Poção");
        pocaoLingering.adicionarIngrediente("Adicionar Bafo de Dragão a poção arremessável");
        itens.add(pocaoLingering);

        Item bafoDragao = new Item("Bafo de Dragão",
            "Captura o bafo do Ender Dragon",
            MinecraftEdition.BOTH, "Material");
        bafoDragao.adicionarIngrediente("Usar garrafa vazia no ataque do dragão");
        itens.add(bafoDragao);

        Item flechaPonta = new Item("Flecha com Ponta",
            "Flecha com efeito de poção",
            MinecraftEdition.BOTH, "Arma");
        flechaPonta.adicionarIngrediente("8x Flechas + 1x Poção Persistente");
        itens.add(flechaPonta);

        // ==================== ITENS DE AGRICULTURA E FAZENDA ====================

        Item ossoFarinha = new Item("Farinha de Osso",
            "Fertilizante instantâneo para plantas",
            MinecraftEdition.BOTH, "Material");
        ossoFarinha.adicionarIngrediente("1x Osso ou composto completo");
        itens.add(ossoFarinha);

        Item mudaTempo = new Item("Relógio",
            "Mostra a hora do dia",
            MinecraftEdition.BOTH, "Utilitário");
        mudaTempo.adicionarIngrediente("4x Lingote de Ouro + 1x Pó de Redstone");
        itens.add(mudaTempo);

        Item nomeTag = new Item("Etiqueta de Nome",
            "Nomeia e protege mobs de despawn",
            MinecraftEdition.BOTH, "Item Especial");
        nomeTag.adicionarIngrediente("Encontrado em baús ou pescando");
        itens.add(nomeTag);

        Item cenouraDourada = new Item("Cenoura Dourada",
            "Ingrediente de poção de visão noturna",
            MinecraftEdition.BOTH, "Material");
        cenouraDourada.adicionarIngrediente("8x Pepita de Ouro + 1x Cenoura");
        itens.add(cenouraDourada);

        Item melanciaDourada = new Item("Melancia Reluzente",
            "Ingrediente de poção de cura",
            MinecraftEdition.BOTH, "Material");
        melanciaDourada.adicionarIngrediente("8x Pepita de Ouro + 1x Fatia de Melancia");
        itens.add(melanciaDourada);

        Item lagrimaGhast = new Item("Lágrima de Ghast",
            "Ingrediente de poção de regeneração",
            MinecraftEdition.BOTH, "Material");
        lagrimaGhast.adicionarIngrediente("Drop de Ghast");
        itens.add(lagrimaGhast);

        Item peDragao = new Item("Cabeça de Dragão",
            "Troféu decorativo do Ender Dragon",
            MinecraftEdition.BOTH, "Item Especial");
        peDragao.adicionarIngrediente("Encontrado em End Ships");
        itens.add(peDragao);

        Item cabecaZumbi = new Item("Cabeça de Zumbi",
            "Troféu decorativo",
            MinecraftEdition.BOTH, "Item Especial");
        cabecaZumbi.adicionarIngrediente("Drop raro de zumbi morto por Creeper carregado");
        itens.add(cabecaZumbi);

        Item cabecaEsqueleto = new Item("Caveira de Esqueleto",
            "Troféu decorativo",
            MinecraftEdition.BOTH, "Item Especial");
        cabecaEsqueleto.adicionarIngrediente("Drop raro de esqueleto morto por Creeper carregado");
        itens.add(cabecaEsqueleto);

        Item cabecaCreeper = new Item("Cabeça de Creeper",
            "Troféu que reduz alcance de detecção",
            MinecraftEdition.BOTH, "Item Especial");
        cabecaCreeper.adicionarIngrediente("Drop raro de Creeper morto por Creeper carregado");
        itens.add(cabecaCreeper);

        Item cabecaWither = new Item("Caveira de Wither Skeleton",
            "Necessária para invocar o Wither",
            MinecraftEdition.BOTH, "Item Especial");
        cabecaWither.adicionarIngrediente("Drop de Wither Skeleton (2,5% com Looting III: 5,5%)");
        itens.add(cabecaWither);

        Item cabecaJogador = new Item("Cabeça de Jogador",
            "Cabeça de player específico",
            MinecraftEdition.BOTH, "Item Especial");
        cabecaJogador.adicionarIngrediente("Obter com comando /give");
        itens.add(cabecaJogador);

        // ==================== ITENS DE MÚSICA E DECORAÇÃO ====================

        Item disco13 = new Item("Disco de Música - 13",
            "Música ambiente e misteriosa",
            MinecraftEdition.BOTH, "Item Especial");
        disco13.adicionarIngrediente("Creeper morto por Esqueleto");
        itens.add(disco13);

        Item discoCat = new Item("Disco de Música - Cat",
            "Música calma e alegre",
            MinecraftEdition.BOTH, "Item Especial");
        discoCat.adicionarIngrediente("Creeper morto por Esqueleto");
        itens.add(discoCat);

        Item discoOtherside = new Item("Disco de Música - Otherside",
            "Música misteriosa das profundezas",
            MinecraftEdition.BOTH, "Item Especial");
        discoOtherside.adicionarIngrediente("Encontrado em baús de Ancient City");
        itens.add(discoOtherside);

        Item disco5 = new Item("Disco de Música - 5",
            "Música enigmática e sombria",
            MinecraftEdition.BOTH, "Item Especial");
        disco5.adicionarIngrediente("Fragmento de Disco encontrado em Ancient City");
        itens.add(disco5);

        Item fragmentoDisco = new Item("Fragmento de Disco",
            "Peça para criar o disco '5'",
            MinecraftEdition.BOTH, "Material");
        fragmentoDisco.adicionarIngrediente("Encontrado em baús de Ancient City");
        itens.add(fragmentoDisco);

        Item chifre = new Item("Chifre de Cabra",
            "Instrumento musical tocável",
            MinecraftEdition.BOTH, "Item Especial");
        chifre.adicionarIngrediente("Drop de cabra ao bater em blocos sólidos");
        itens.add(chifre);

        // ==================== ITENS DE EXPLORAÇÃO ====================

        Item mapaTesoureo = new Item("Mapa do Tesouro",
            "Mapa que leva a tesouros enterrados",
            MinecraftEdition.BOTH, "Utilitário");
        mapaTesoureo.adicionarIngrediente("Encontrado em naufrágios");
        itens.add(mapaTesoureo);

        Item mapaExplorador = new Item("Mapa de Explorador",
            "Mapa que leva a estruturas específicas",
            MinecraftEdition.BOTH, "Utilitário");
        mapaExplorador.adicionarIngrediente("Comércio com Cartógrafo");
        itens.add(mapaExplorador);

        Item peCoelho = new Item("Pé de Coelho",
            "Ingrediente de poção de salto",
            MinecraftEdition.BOTH, "Material");
        peCoelho.adicionarIngrediente("Drop raro de coelho");
        itens.add(peCoelho);

        Item membranaPhantom = new Item("Membrana de Phantom",
            "Ingrediente de poção de queda lenta",
            MinecraftEdition.BOTH, "Material");
        membranaPhantom.adicionarIngrediente("Drop de Phantom");
        itens.add(membranaPhantom);

        Item carapacaTartaruga = new Item("Escama de Tartaruga",
            "Material para capacete de tartaruga",
            MinecraftEdition.BOTH, "Material");
        carapacaTartaruga.adicionarIngrediente("Drop de tartaruga bebê ao crescer");
        itens.add(carapacaTartaruga);

        Item cascaShulker = new Item("Casca de Shulker",
            "Material para caixa de Shulker",
            MinecraftEdition.BOTH, "Material");
        cascaShulker.adicionarIngrediente("Drop de Shulker");
        itens.add(cascaShulker);

        Item aneisIron = new Item("Anel de Armadilha",
            "Componente de besta",
            MinecraftEdition.BOTH, "Material");
        aneisIron.adicionarIngrediente("4x Lingote de Ferro + 1x Tábua + 1x Graveto");
        itens.add(aneisIron);

        Item foguete = new Item("Foguete",
            "Propulsor para élitros",
            MinecraftEdition.BOTH, "Item Especial");
        foguete.adicionarIngrediente("1x Papel + 1-3x Pólvora + Opcional: Estrela de fogo");
        itens.add(foguete);

        Item estrelaFogos = new Item("Estrela de Fogos",
            "Cria efeitos coloridos no céu",
            MinecraftEdition.BOTH, "Item Especial");
        estrelaFogos.adicionarIngrediente("1x Pólvora + Corantes + Opcional: Diamante/Pena/Glowstone");
        itens.add(estrelaFogos);

        Item fungoDistorcido = new Item("Fungo Distorcido",
            "Fungo do Nether para controlar Striders",
            MinecraftEdition.BOTH, "Material");
        fungoDistorcido.adicionarIngrediente("Encontrado na Floresta Distorcida (Nether)");
        itens.add(fungoDistorcido);

        Item fungoCarmesim = new Item("Fungo Carmesim",
            "Fungo do Nether para criar Hoglins",
            MinecraftEdition.BOTH, "Material");
        fungoCarmesim.adicionarIngrediente("Encontrado na Floresta Carmesim (Nether)");
        itens.add(fungoCarmesim);

        Item nylium = new Item("Nylium",
            "Bloco de terra do Nether",
            MinecraftEdition.BOTH, "Bloco Natural");
        nylium.adicionarIngrediente("Minerar com Toque Suave no Nether");
        itens.add(nylium);

        Item raizes = new Item("Raízes",
            "Planta decorativa do Nether",
            MinecraftEdition.BOTH, "Bloco Natural");
        raizes.adicionarIngrediente("Encontrado no Nether");
        itens.add(raizes);

        // ==================== NOVOS ITENS ADICIONADOS ====================
        
        // Comidas
        Item baiaDoce = new Item("Bagas Doces",
            "Alimento que cresce em arbustos, causa dano ao colher",
            MinecraftEdition.BOTH, "Alimento");
        baiaDoce.adicionarIngrediente("Colher de arbustos de bagas doces");
        itens.add(baiaDoce);

        Item baiasBrilhantes = new Item("Bagas Brilhantes",
            "Alimento luminoso encontrado em cavernas exuberantes",
            MinecraftEdition.BOTH, "Alimento");
        baiasBrilhantes.adicionarIngrediente("Colher em cavernas exuberantes");
        itens.add(baiasBrilhantes);

        Item sopaCogumelo = new Item("Sopa de Cogumelo",
            "Restaura 6 pontos de fome",
            MinecraftEdition.BOTH, "Alimento");
        sopaCogumelo.adicionarIngrediente("1x Cogumelo Vermelho + 1x Cogumelo Marrom + 1x Tigela");
        itens.add(sopaCogumelo);

        Item ensopadoSuspeitoso = new Item("Ensopado Suspeito",
            "Dá efeito de poção baseado na flor usada",
            MinecraftEdition.BOTH, "Alimento");
        ensopadoSuspeitoso.adicionarIngrediente("1x Cogumelo Vermelho + 1x Cogumelo Marrom + 1x Flor + 1x Tigela");
        itens.add(ensopadoSuspeitoso);

        Item tigela = new Item("Tigela",
            "Recipiente para sopas e ensopados",
            MinecraftEdition.BOTH, "Item Especial");
        tigela.adicionarIngrediente("3x Tábuas");
        itens.add(tigela);

        Item macaPodre = new Item("Maçã Podre",
            "Alimento que causa fome e veneno",
            MinecraftEdition.BOTH, "Alimento");
        macaPodre.adicionarIngrediente("Drop raro de zumbis");
        itens.add(macaPodre);

        // Lãs Coloridas
        Item laVermelha = new Item("Lã Vermelha",
            "Bloco decorativo vermelho",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        laVermelha.adicionarIngrediente("1x Lã Branca + 1x Corante Vermelho");
        itens.add(laVermelha);

        Item laAzul = new Item("Lã Azul",
            "Bloco decorativo azul",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        laAzul.adicionarIngrediente("1x Lã Branca + 1x Corante Azul");
        itens.add(laAzul);

        Item laVerde = new Item("Lã Verde",
            "Bloco decorativo verde",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        laVerde.adicionarIngrediente("1x Lã Branca + 1x Corante Verde");
        itens.add(laVerde);

        Item laAmarela = new Item("Lã Amarela",
            "Bloco decorativo amarelo",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        laAmarela.adicionarIngrediente("1x Lã Branca + 1x Corante Amarelo");
        itens.add(laAmarela);

        Item laPreta = new Item("Lã Preta",
            "Bloco decorativo preto",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        laPreta.adicionarIngrediente("1x Lã Branca + 1x Corante Preto");
        itens.add(laPreta);

        Item laRoxa = new Item("Lã Roxa",
            "Bloco decorativo roxo",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        laRoxa.adicionarIngrediente("1x Lã Branca + 1x Corante Roxo");
        itens.add(laRoxa);

        Item laRosa = new Item("Lã Rosa",
            "Bloco decorativo rosa",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        laRosa.adicionarIngrediente("1x Lã Branca + 1x Corante Rosa");
        itens.add(laRosa);

        // Minérios Deepslate
        Item minerioCobreDeepslate = new Item("Minério de Cobre Deepslate",
            "Variante deepslate do minério de cobre",
            MinecraftEdition.BOTH, "Minério");
        minerioCobreDeepslate.adicionarIngrediente("Minerar em camadas profundas (Y < 0)");
        itens.add(minerioCobreDeepslate);

        Item minerioFerroDeepslate = new Item("Minério de Ferro Deepslate",
            "Variante deepslate do minério de ferro",
            MinecraftEdition.BOTH, "Minério");
        minerioFerroDeepslate.adicionarIngrediente("Minerar em camadas profundas (Y < 0)");
        itens.add(minerioFerroDeepslate);

        Item minerioOuroDeepslate = new Item("Minério de Ouro Deepslate",
            "Variante deepslate do minério de ouro",
            MinecraftEdition.BOTH, "Minério");
        minerioOuroDeepslate.adicionarIngrediente("Minerar em camadas profundas (Y < 0)");
        itens.add(minerioOuroDeepslate);

        Item minerioDiamanteDeepslate = new Item("Minério de Diamante Deepslate",
            "Variante deepslate do minério de diamante",
            MinecraftEdition.BOTH, "Minério");
        minerioDiamanteDeepslate.adicionarIngrediente("Minerar em camadas profundas (Y < 0)");
        itens.add(minerioDiamanteDeepslate);

        Item minerioLapisDeepslate = new Item("Minério de Lápis-Lazúli Deepslate",
            "Variante deepslate do minério de lápis-lazúli",
            MinecraftEdition.BOTH, "Minério");
        minerioLapisDeepslate.adicionarIngrediente("Minerar em camadas profundas (Y < 0)");
        itens.add(minerioLapisDeepslate);

        Item minerioRedstoneDeepslate = new Item("Minério de Redstone Deepslate",
            "Variante deepslate do minério de redstone",
            MinecraftEdition.BOTH, "Minério");
        minerioRedstoneDeepslate.adicionarIngrediente("Minerar em camadas profundas (Y < 0)");
        itens.add(minerioRedstoneDeepslate);

        Item minerioEsmeraldaDeepslate = new Item("Minério de Esmeralda Deepslate",
            "Variante deepslate do minério de esmeralda",
            MinecraftEdition.BOTH, "Minério");
        minerioEsmeraldaDeepslate.adicionarIngrediente("Minerar em montanhas profundas (Y < 0)");
        itens.add(minerioEsmeraldaDeepslate);

        // Blocos de Pedra Especiais
        Item granitoPolido = new Item("Granito Polido",
            "Versão polida do granito",
            MinecraftEdition.BOTH, "Bloco Construção");
        granitoPolido.adicionarIngrediente("4x Granito");
        itens.add(granitoPolido);

        Item dioritoPolido = new Item("Diorito Polido",
            "Versão polida do diorito",
            MinecraftEdition.BOTH, "Bloco Construção");
        dioritoPolido.adicionarIngrediente("4x Diorito");
        itens.add(dioritoPolido);

        Item andesitoPolido = new Item("Andesito Polido",
            "Versão polida do andesito",
            MinecraftEdition.BOTH, "Bloco Construção");
        andesitoPolido.adicionarIngrediente("4x Andesito");
        itens.add(andesitoPolido);

        Item basalto = new Item("Basalto",
            "Bloco de pedra do Nether",
            MinecraftEdition.BOTH, "Bloco Natural");
        basalto.adicionarIngrediente("Encontrado em deltas de basalto no Nether");
        itens.add(basalto);

        Item basaltoPolido = new Item("Basalto Polido",
            "Versão decorativa do basalto",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        basaltoPolido.adicionarIngrediente("4x Basalto");
        itens.add(basaltoPolido);

        Item calcite = new Item("Calcita",
            "Bloco branco encontrado em geodos",
            MinecraftEdition.BOTH, "Bloco Natural");
        calcite.adicionarIngrediente("Encontrado em geodos de ametista");
        itens.add(calcite);

        Item tufo = new Item("Tufo",
            "Bloco cinza encontrado no subsolo",
            MinecraftEdition.BOTH, "Bloco Natural");
        tufo.adicionarIngrediente("Encontrado em camadas inferiores (Y < 16)");
        itens.add(tufo);

        Item musgo = new Item("Bloco de Musgo",
            "Bloco natural verdejante",
            MinecraftEdition.BOTH, "Bloco Natural");
        musgo.adicionarIngrediente("Encontrado em cavernas exuberantes");
        itens.add(musgo);

        // Itens de Iluminação
        Item torchaAlma = new Item("Tocha de Alma",
            "Tocha azul que emite luz mais fraca",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        torchaAlma.adicionarIngrediente("1x Graveto + 1x Areia de Alma ou Terra de Alma");
        itens.add(torchaAlma);

        Item lanternaAlma = new Item("Lanterna de Alma",
            "Lanterna azul decorativa",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        lanternaAlma.adicionarIngrediente("8x Pepita de Ferro + 1x Tocha de Alma");
        itens.add(lanternaAlma);

        Item campfireAlma = new Item("Fogueira de Alma",
            "Fogueira azul que causa mais dano",
            MinecraftEdition.BOTH, "Bloco Especial");
        campfireAlma.adicionarIngrediente("3x Graveto + 3x Areia de Alma + 3x Madeira");
        itens.add(campfireAlma);

        Item campfire = new Item("Fogueira",
            "Fonte de luz e cozimento",
            MinecraftEdition.BOTH, "Bloco Especial");
        campfire.adicionarIngrediente("3x Graveto + 1x Carvão + 3x Madeira");
        itens.add(campfire);

        Item lanterna = new Item("Lanterna",
            "Fonte de luz pendurável",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        lanterna.adicionarIngrediente("8x Pepita de Ferro + 1x Tocha");
        itens.add(lanterna);

        // Itens de Redstone
        Item sensorLuz = new Item("Sensor de Luz do Dia",
            "Detecta luz solar e emite sinal de redstone",
            MinecraftEdition.BOTH, "Redstone");
        sensorLuz.adicionarIngrediente("3x Vidro + 3x Quartzo do Nether + 3x Laje de Madeira");
        itens.add(sensorLuz);

        Item alvo = new Item("Alvo",
            "Bloco que emite sinal de redstone ao ser atingido",
            MinecraftEdition.BOTH, "Redstone");
        alvo.adicionarIngrediente("1x Fardo de Feno + 4x Pó de Redstone");
        itens.add(alvo);

        Item sensorSculk = new Item("Sensor Sculk",
            "Detecta vibrações e emite sinal de redstone",
            MinecraftEdition.BOTH, "Redstone");
        sensorSculk.adicionarIngrediente("Minerar com Toque Suave em Deep Dark");
        itens.add(sensorSculk);

        // Outros Itens Importantes
        Item spawner = new Item("Gerador de Monstros",
            "Spawna mobs periodicamente",
            MinecraftEdition.BOTH, "Bloco Especial");
        spawner.adicionarIngrediente("Não craftável - encontrado em masmorras");
        itens.add(spawner);

        Item carvaoVegetal = new Item("Carvão Vegetal",
            "Combustível alternativo ao carvão",
            MinecraftEdition.BOTH, "Material");
        carvaoVegetal.adicionarIngrediente("Queimar madeira na fornalha");
        itens.add(carvaoVegetal);

        Item pepitaOuro = new Item("Pepita de Ouro",
            "Fragmento de ouro",
            MinecraftEdition.BOTH, "Material");
        pepitaOuro.adicionarIngrediente("1x Lingote de Ouro = 9x Pepitas");
        itens.add(pepitaOuro);

        Item pepitaFerro = new Item("Pepita de Ferro",
            "Fragmento de ferro",
            MinecraftEdition.BOTH, "Material");
        pepitaFerro.adicionarIngrediente("1x Lingote de Ferro = 9x Pepitas");
        itens.add(pepitaFerro);

        Item farinhaOsso = new Item("Farinha de Osso",
            "Fertilizante para plantas",
            MinecraftEdition.BOTH, "Material");
        farinhaOsso.adicionarIngrediente("1x Osso = 3x Farinha de Osso");
        itens.add(farinhaOsso);

        Item colar = new Item("Coleira",
            "Amarra animais passivos",
            MinecraftEdition.BOTH, "Item Especial");
        colar.adicionarIngrediente("4x Linha + 1x Bola de Slime");
        itens.add(colar);

        Item cenouraVara = new Item("Cenoura no Graveto",
            "Controla porcos montados",
            MinecraftEdition.BOTH, "Item Especial");
        cenouraVara.adicionarIngrediente("1x Vara de Pescar + 1x Cenoura");
        itens.add(cenouraVara);

        Item fungoVara = new Item("Fungo Distorcido no Graveto",
            "Controla Striders montados",
            MinecraftEdition.BOTH, "Item Especial");
        fungoVara.adicionarIngrediente("1x Vara de Pescar + 1x Fungo Distorcido");
        itens.add(fungoVara);

        Item bandeira = new Item("Bandeira",
            "Bloco decorativo personalizável",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        bandeira.adicionarIngrediente("6x Lã + 1x Graveto");
        itens.add(bandeira);

        Item vitral = new Item("Vidro Colorido",
            "Vidro decorativo transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vitral.adicionarIngrediente("8x Vidro + 1x Corante");
        itens.add(vitral);

        Item dripleaf = new Item("Grande Folha-Goteira",
            "Planta que funciona como plataforma temporária",
            MinecraftEdition.BOTH, "Bloco Natural");
        dripleaf.adicionarIngrediente("Encontrado em cavernas exuberantes");
        itens.add(dripleaf);

        // ==================== BLOCOS ADICIONAIS IMPORTANTES ====================
        
        // Blocos de Nether
        Item soulSoil = new Item("Terra de Alma",
            "Bloco decorativo do Nether, base para fogo azul",
            MinecraftEdition.BOTH, "Bloco Natural");
        soulSoil.adicionarIngrediente("Encontrado em vales de areia de alma");
        itens.add(soulSoil);

        Item blackstone = new Item("Blackstone",
            "Pedra escura do Nether",
            MinecraftEdition.BOTH, "Bloco Construção");
        blackstone.adicionarIngrediente("Encontrado em bastion remnants");
        itens.add(blackstone);

        Item blackstonePolido = new Item("Blackstone Polido",
            "Versão polida do blackstone",
            MinecraftEdition.BOTH, "Bloco Construção");
        blackstonePolido.adicionarIngrediente("4x Blackstone");
        itens.add(blackstonePolido);

        Item netherWartBlock = new Item("Bloco de Verruga do Nether",
            "Bloco decorativo vermelho",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        netherWartBlock.adicionarIngrediente("9x Verruga do Nether");
        itens.add(netherWartBlock);

        Item cryingObsidian = new Item("Obsidiana Chorona",
            "Obsidiana que emite luz e serve para âncora de respawn",
            MinecraftEdition.BOTH, "Bloco Especial");
        cryingObsidian.adicionarIngrediente("Encontrar em ruínas do Nether ou negociar com Piglins");
        itens.add(cryingObsidian);

        Item ancientDebris = new Item("Detritos Ancestrais",
            "Minério mais raro do jogo, fonte de Netherite",
            MinecraftEdition.BOTH, "Minério");
        ancientDebris.adicionarIngrediente("Minerar no Nether (Y 8-22)");
        itens.add(ancientDebris);

        Item lodestone = new Item("Lodestone",
            "Ancora bússolas para localização específica",
            MinecraftEdition.BOTH, "Bloco Utilitário");
        lodestone.adicionarIngrediente("8x Pedregulho Talhado + 1x Lingote de Netherite");
        itens.add(lodestone);

        // Madeiras do Nether
        Item warpedPlanks = new Item("Tábuas Distorcidas",
            "Madeira ciano do Nether, não queima",
            MinecraftEdition.BOTH, "Bloco Construção");
        warpedPlanks.adicionarIngrediente("1x Tronco Distorcido");
        itens.add(warpedPlanks);

        Item crimsonPlanks = new Item("Tábuas Carmesim",
            "Madeira vermelha do Nether, não queima",
            MinecraftEdition.BOTH, "Bloco Construção");
        crimsonPlanks.adicionarIngrediente("1x Tronco Carmesim");
        itens.add(crimsonPlanks);

        // Blocos do End
        Item endStoneBricks = new Item("Tijolos de Pedra do End",
            "Versão decorativa da pedra do End",
            MinecraftEdition.BOTH, "Bloco Construção");
        endStoneBricks.adicionarIngrediente("4x Pedra do End");
        itens.add(endStoneBricks);

        Item chorusPlant = new Item("Planta Chorus",
            "Planta roxa do End",
            MinecraftEdition.BOTH, "Bloco Natural");
        chorusPlant.adicionarIngrediente("Quebrar flores chorus no End");
        itens.add(chorusPlant);

        Item chorusFlower = new Item("Flor Chorus",
            "Topo da planta chorus",
            MinecraftEdition.BOTH, "Bloco Natural");
        chorusFlower.adicionarIngrediente("Cresce no topo de plantas chorus");
        itens.add(chorusFlower);

        // Blocos de Redstone e Mecanismos
        Item observer = new Item("Observer",
            "Detecta mudanças de blocos e emite pulso",
            MinecraftEdition.BOTH, "Redstone");
        observer.adicionarIngrediente("6x Pedregulho + 2x Pó de Redstone + 1x Quartzo do Nether");
        itens.add(observer);

        Item dispenser = new Item("Dispenser",
            "Dispara itens automaticamente",
            MinecraftEdition.BOTH, "Redstone");
        dispenser.adicionarIngrediente("7x Pedregulho + 1x Arco + 1x Pó de Redstone");
        itens.add(dispenser);

        Item dropper = new Item("Dropper",
            "Solta itens automaticamente",
            MinecraftEdition.BOTH, "Redstone");
        dropper.adicionarIngrediente("7x Pedregulho + 1x Pó de Redstone");
        itens.add(dropper);

        Item hopper = new Item("Hopper",
            "Transfere itens entre contêineres",
            MinecraftEdition.BOTH, "Redstone");
        hopper.adicionarIngrediente("5x Lingote de Ferro + 1x Baú");
        itens.add(hopper);

        Item piston = new Item("Pistão",
            "Empurra blocos",
            MinecraftEdition.BOTH, "Redstone");
        piston.adicionarIngrediente("3x Tábuas + 4x Pedregulho + 1x Lingote de Ferro + 1x Pó de Redstone");
        itens.add(piston);

        Item stickyPiston = new Item("Pistão Grudento",
            "Pistão que puxa blocos de volta",
            MinecraftEdition.BOTH, "Redstone");
        stickyPiston.adicionarIngrediente("1x Pistão + 1x Bola de Slime");
        itens.add(stickyPiston);

        // Trilhos
        Item rail = new Item("Trilho",
            "Trilho simples para minecarts",
            MinecraftEdition.BOTH, "Transporte");
        rail.adicionarIngrediente("6x Lingote de Ferro + 1x Graveto");
        itens.add(rail);

        Item poweredRail = new Item("Trilho Elétrico",
            "Acelera ou desacelera minecarts",
            MinecraftEdition.BOTH, "Transporte");
        poweredRail.adicionarIngrediente("6x Lingote de Ouro + 1x Graveto + 1x Pó de Redstone");
        itens.add(poweredRail);

        Item detectorRail = new Item("Trilho Detector",
            "Emite sinal de redstone quando minecart passa",
            MinecraftEdition.BOTH, "Transporte");
        detectorRail.adicionarIngrediente("6x Lingote de Ferro + 1x Placa de Pressão + 1x Pó de Redstone");
        itens.add(detectorRail);

        Item activatorRail = new Item("Trilho Ativador",
            "Ativa minecarts especiais",
            MinecraftEdition.BOTH, "Transporte");
        activatorRail.adicionarIngrediente("6x Lingote de Ferro + 2x Graveto + 1x Tocha de Redstone");
        itens.add(activatorRail);

        // Blocos Oceânicos
        Item prismarine = new Item("Prismarine",
            "Bloco ciano encontrado em monumentos oceânicos",
            MinecraftEdition.BOTH, "Bloco Construção");
        prismarine.adicionarIngrediente("4x Fragmento de Prismarine");
        itens.add(prismarine);

        Item prismarineBricks = new Item("Tijolos de Prismarine",
            "Variante decorativa de prismarine",
            MinecraftEdition.BOTH, "Bloco Construção");
        prismarineBricks.adicionarIngrediente("9x Fragmento de Prismarine");
        itens.add(prismarineBricks);

        Item darkPrismarine = new Item("Prismarine Escuro",
            "Variante escura de prismarine",
            MinecraftEdition.BOTH, "Bloco Construção");
        darkPrismarine.adicionarIngrediente("8x Fragmento de Prismarine + 1x Saco de Tinta");
        itens.add(darkPrismarine);

        Item seaLantern = new Item("Lanterna do Mar",
            "Bloco de iluminação oceânico",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        seaLantern.adicionarIngrediente("4x Fragmento de Prismarine + 5x Cristal de Prismarine");
        itens.add(seaLantern);

        Item coral = new Item("Coral",
            "Bloco decorativo subaquático (requer água)",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        coral.adicionarIngrediente("Encontrado em recifes de coral");
        itens.add(coral);

        Item coralBlock = new Item("Bloco de Coral",
            "Versão em bloco do coral",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        coralBlock.adicionarIngrediente("Encontrado em recifes de coral");
        itens.add(coralBlock);

        Item kelp = new Item("Alga Marinha",
            "Planta aquática que cresce rapidamente",
            MinecraftEdition.BOTH, "Bloco Natural");
        kelp.adicionarIngrediente("Encontrada em oceanos");
        itens.add(kelp);

        // Blocos de Gelo
        Item packedIce = new Item("Gelo Compactado",
            "Não derrete e é muito escorregadio",
            MinecraftEdition.BOTH, "Bloco Natural");
        packedIce.adicionarIngrediente("9x Gelo");
        itens.add(packedIce);

        Item blueIce = new Item("Gelo Azul",
            "Gelo mais escorregadio do jogo",
            MinecraftEdition.BOTH, "Bloco Natural");
        blueIce.adicionarIngrediente("9x Gelo Compactado");
        itens.add(blueIce);

        // Blocos Especiais
        Item slimeBlock = new Item("Bloco de Slime",
            "Bloco que faz entidades quicarem",
            MinecraftEdition.BOTH, "Bloco Especial");
        slimeBlock.adicionarIngrediente("9x Bola de Slime");
        itens.add(slimeBlock);

        Item honeyBlock = new Item("Bloco de Mel",
            "Bloco pegajoso que diminui velocidade de queda",
            MinecraftEdition.BOTH, "Bloco Especial");
        honeyBlock.adicionarIngrediente("4x Garrafa de Mel");
        itens.add(honeyBlock);

        Item scaffolding = new Item("Andaime",
            "Bloco temporário para construção",
            MinecraftEdition.BOTH, "Bloco Utilitário");
        scaffolding.adicionarIngrediente("6x Bambu + 1x Linha");
        itens.add(scaffolding);

        Item chain = new Item("Corrente",
            "Bloco decorativo de corrente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        chain.adicionarIngrediente("2x Pepita de Ferro + 1x Lingote de Ferro");
        itens.add(chain);

        Item shroomlight = new Item("Luz de Cogumelo",
            "Bloco de iluminação das florestas do Nether",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        shroomlight.adicionarIngrediente("Encontrado em árvores enormes do Nether");
        itens.add(shroomlight);

        // Variantes de Cobre
        Item copperBlock = new Item("Bloco de Cobre",
            "Bloco de cobre que oxida com o tempo",
            MinecraftEdition.BOTH, "Bloco Construção");
        copperBlock.adicionarIngrediente("9x Lingote de Cobre");
        itens.add(copperBlock);

        Item cutCopper = new Item("Cobre Cortado",
            "Variante decorativa de cobre",
            MinecraftEdition.BOTH, "Bloco Construção");
        cutCopper.adicionarIngrediente("4x Bloco de Cobre");
        itens.add(cutCopper);

        Item lightningRod = new Item("Para-raios",
            "Atrai raios em área de 128 blocos",
            MinecraftEdition.BOTH, "Bloco Utilitário");
        lightningRod.adicionarIngrediente("3x Lingote de Cobre");
        itens.add(lightningRod);

        // Blocos Sculk
        Item sculk = new Item("Sculk",
            "Bloco do Deep Dark que se espalha com XP",
            MinecraftEdition.BOTH, "Bloco Natural");
        sculk.adicionarIngrediente("Encontrado em Deep Dark");
        itens.add(sculk);

        Item sculkVein = new Item("Veia de Sculk",
            "Textura decorativa de sculk",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        sculkVein.adicionarIngrediente("Encontrada em Deep Dark");
        itens.add(sculkVein);

        Item sculkCatalyst = new Item("Catalisador de Sculk",
            "Espalha sculk quando mob morre perto",
            MinecraftEdition.BOTH, "Bloco Especial");
        sculkCatalyst.adicionarIngrediente("Encontrado em Deep Dark");
        itens.add(sculkCatalyst);

        Item sculkShrieker = new Item("Gritador de Sculk",
            "Invoca o Warden após 4 ativações",
            MinecraftEdition.BOTH, "Bloco Especial");
        sculkShrieker.adicionarIngrediente("Encontrado em Deep Dark");
        itens.add(sculkShrieker);

        // Blocos de Lama
        Item mud = new Item("Lama",
            "Bloco de terra molhada",
            MinecraftEdition.BOTH, "Bloco Natural");
        mud.adicionarIngrediente("1x Terra + 1x Garrafa de Água");
        itens.add(mud);

        Item packedMud = new Item("Lama Compactada",
            "Lama seca e compactada",
            MinecraftEdition.BOTH, "Bloco Construção");
        packedMud.adicionarIngrediente("Secar Lama");
        itens.add(packedMud);

        Item mudBricks = new Item("Tijolos de Lama",
            "Tijolos feitos de lama compactada",
            MinecraftEdition.BOTH, "Bloco Construção");
        mudBricks.adicionarIngrediente("4x Lama Compactada");
        itens.add(mudBricks);

        // Madeiras Novas
        Item mangroveWood = new Item("Madeira de Mangue",
            "Madeira encontrada em pântanos de mangue",
            MinecraftEdition.BOTH, "Bloco Natural");
        mangroveWood.adicionarIngrediente("Árvores de mangue");
        itens.add(mangroveWood);

        Item mangrovePlanks = new Item("Tábuas de Mangue",
            "Tábuas vermelhas de mangue",
            MinecraftEdition.BOTH, "Bloco Construção");
        mangrovePlanks.adicionarIngrediente("1x Madeira de Mangue");
        itens.add(mangrovePlanks);

        Item cherryWood = new Item("Madeira de Cerejeira",
            "Madeira rosa encontrada em biomas de cerejeira",
            MinecraftEdition.BOTH, "Bloco Natural");
        cherryWood.adicionarIngrediente("Árvores de cerejeira");
        itens.add(cherryWood);

        Item cherryPlanks = new Item("Tábuas de Cerejeira",
            "Tábuas rosa de cerejeira",
            MinecraftEdition.BOTH, "Bloco Construção");
        cherryPlanks.adicionarIngrediente("1x Madeira de Cerejeira");
        itens.add(cherryPlanks);

        Item bambooBlock = new Item("Bloco de Bambu",
            "Bloco feito de bambu",
            MinecraftEdition.BOTH, "Bloco Construção");
        bambooBlock.adicionarIngrediente("9x Bambu");
        itens.add(bambooBlock);

        Item bambooPlanks = new Item("Tábuas de Bambu",
            "Tábuas amareladas de bambu",
            MinecraftEdition.BOTH, "Bloco Construção");
        bambooPlanks.adicionarIngrediente("1x Bloco de Bambu");
        itens.add(bambooPlanks);

        // Blocos Diversos
        Item sponge = new Item("Esponja",
            "Absorve água em área de 7x7x7",
            MinecraftEdition.BOTH, "Bloco Especial");
        sponge.adicionarIngrediente("Encontrada em monumentos oceânicos");
        itens.add(sponge);

        Item bookshelf = new Item("Estante de Livros",
            "Aumenta nível de encantamentos",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        bookshelf.adicionarIngrediente("6x Tábuas + 3x Livro");
        itens.add(bookshelf);

        Item lectern = new Item("Atril",
            "Segura livros abertos e emite sinal de redstone",
            MinecraftEdition.BOTH, "Bloco Utilitário");
        lectern.adicionarIngrediente("4x Laje de Madeira + 1x Estante de Livros");
        itens.add(lectern);

        // ===== ITENS DE EXPLORAÇÃO E AVENTURA =====
        // (tridente, elytra já existem anteriormente)
        
        Item totem = new Item("Totem da Imortalidade",
            "Previne morte uma vez, restaura vida",
            MinecraftEdition.BOTH, "Item Especial");
        totem.adicionarIngrediente("Drop de Evocador em Mansões");
        itens.add(totem);

        // ===== MATERIAIS RAROS =====
        
        Item estrelaNether = new Item("Estrela do Nether",
            "Material raro usado para criar Beacon",
            MinecraftEdition.BOTH, "Material");
        estrelaNether.adicionarIngrediente("Drop do Wither");
        itens.add(estrelaNether);

        Item conchaNautilus = new Item("Concha de Nautilus",
            "Material usado para criar Conduite",
            MinecraftEdition.BOTH, "Material");
        conchaNautilus.adicionarIngrediente("Pescaria ou drop de Afogado");
        itens.add(conchaNautilus);

        Item membrana = new Item("Membrana Fantasma",
            "Material para reparar Élitros",
            MinecraftEdition.BOTH, "Material");
        membrana.adicionarIngrediente("Drop de Phantom");
        itens.add(membrana);

        Item carapaca = new Item("Carapaça de Tartaruga",
            "Material para criar capacete de tartaruga",
            MinecraftEdition.BOTH, "Material");
        carapaca.adicionarIngrediente("Drop quando filhote de tartaruga cresce");
        itens.add(carapaca);

        // ===== BLOCOS UTILITÁRIOS =====
        // (tear, beacon já existem anteriormente)
        
        Item bancadaCartografia = new Item("Bancada de Cartografia",
            "Usada para criar e clonar mapas",
            MinecraftEdition.BOTH, "Bloco Utilitário");
        bancadaCartografia.adicionarIngrediente("4x Tábuas + 2x Papel");
        itens.add(bancadaCartografia);

        Item caldeirao = new Item("Caldeirão",
            "Armazena água, lava ou neve",
            MinecraftEdition.BOTH, "Bloco Utilitário");
        caldeirao.adicionarIngrediente("7x Lingote de Ferro");
        itens.add(caldeirao);

        Item conduite = new Item("Conduite",
            "Dá Conduit Power debaixo d'água",
            MinecraftEdition.BOTH, "Bloco Especial");
        conduite.adicionarIngrediente("8x Concha de Nautilus + 1x Coração do Mar");
        itens.add(conduite);

        Item rebolo = new Item("Rebolo",
            "Remove encantamentos e repara itens",
            MinecraftEdition.BOTH, "Bloco Utilitário");
        rebolo.adicionarIngrediente("2x Graveto + 1x Laje de Pedra + 2x Tábuas");
        itens.add(rebolo);

        // ===== BALDES E RECIPIENTES =====
        // (baldeAgua, baldeLava, baldeLeite já existem anteriormente)
        
        Item baldePeixe = new Item("Balde de Peixe Tropical",
            "Transporta peixe tropical",
            MinecraftEdition.BOTH, "Utilitário");
        baldePeixe.adicionarIngrediente("1x Balde de Água + Peixe Tropical");
        itens.add(baldePeixe);

        Item baldeAxolote = new Item("Balde de Axolote",
            "Transporta axolote",
            MinecraftEdition.BOTH, "Utilitário");
        baldeAxolote.adicionarIngrediente("1x Balde de Água + Axolote");
        itens.add(baldeAxolote);

        Item baldeBacalhau = new Item("Balde de Bacalhau",
            "Transporta bacalhau",
            MinecraftEdition.BOTH, "Utilitário");
        baldeBacalhau.adicionarIngrediente("1x Balde de Água + Bacalhau");
        itens.add(baldeBacalhau);

        Item baldeSalmao = new Item("Balde de Salmão",
            "Transporta salmão",
            MinecraftEdition.BOTH, "Utilitário");
        baldeSalmao.adicionarIngrediente("1x Balde de Água + Salmão");
        itens.add(baldeSalmao);

        Item baldeTadpole = new Item("Balde de Girino",
            "Transporta girino",
            MinecraftEdition.BOTH, "Utilitário");
        baldeTadpole.adicionarIngrediente("1x Balde de Água + Girino");
        itens.add(baldeTadpole);

        // ===== FLORES E PLANTAS DECORATIVAS =====
        
        Item papoula = new Item("Papoula",
            "Flor vermelha que gera corante",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        papoula.adicionarIngrediente("Encontrada em planícies e florestas");
        itens.add(papoula);

        Item dentedeleao = new Item("Dente-de-leão",
            "Flor amarela que gera corante",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        dentedeleao.adicionarIngrediente("Encontrada em planícies");
        itens.add(dentedeleao);

        Item orquidea = new Item("Orquídea Azul",
            "Flor azul rara que gera corante",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        orquidea.adicionarIngrediente("Encontrada em pântanos");
        itens.add(orquidea);

        Item tulipaVermelha = new Item("Tulipa Vermelha",
            "Flor vermelha decorativa",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        tulipaVermelha.adicionarIngrediente("Encontrada em planícies de flores");
        itens.add(tulipaVermelha);

        Item tulipaLaranja = new Item("Tulipa Laranja",
            "Flor laranja decorativa",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        tulipaLaranja.adicionarIngrediente("Encontrada em planícies de flores");
        itens.add(tulipaLaranja);

        Item tulipaBranca = new Item("Tulipa Branca",
            "Flor branca decorativa",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        tulipaBranca.adicionarIngrediente("Encontrada em planícies de flores");
        itens.add(tulipaBranca);

        Item tulipaRosa = new Item("Tulipa Rosa",
            "Flor rosa decorativa",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        tulipaRosa.adicionarIngrediente("Encontrada em planícies de flores");
        itens.add(tulipaRosa);

        Item margarida = new Item("Margarida",
            "Flor branca decorativa",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        margarida.adicionarIngrediente("Encontrada em planícies");
        itens.add(margarida);

        Item girassol = new Item("Girassol",
            "Planta alta amarela que indica leste",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        girassol.adicionarIngrediente("Encontrado em planícies de girassóis");
        itens.add(girassol);

        Item lilac = new Item("Lilás",
            "Planta alta roxa decorativa",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        lilac.adicionarIngrediente("Encontrada em florestas");
        itens.add(lilac);

        Item rosaBush = new Item("Arbusto de Rosas",
            "Planta alta com rosas vermelhas",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        rosaBush.adicionarIngrediente("Encontrado em florestas");
        itens.add(rosaBush);

        Item peonia = new Item("Peônia",
            "Planta alta rosa decorativa",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        peonia.adicionarIngrediente("Encontrada em florestas");
        itens.add(peonia);

        Item azaleia = new Item("Azaleia",
            "Arbusto decorativo que indica lush caves",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        azaleia.adicionarIngrediente("Encontrada em lush caves");
        itens.add(azaleia);

        Item azaleiaFlorida = new Item("Azaleia Florida",
            "Arbusto decorativo com flores rosas",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        azaleiaFlorida.adicionarIngrediente("Encontrada em lush caves");
        itens.add(azaleiaFlorida);

        Item lianaBrilhante = new Item("Liana Brilhante",
            "Planta que emite luz fraca",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        lianaBrilhante.adicionarIngrediente("Encontrada em lush caves");
        itens.add(lianaBrilhante);

        Item esporos = new Item("Esporos",
            "Planta que cria partículas verdes",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        esporos.adicionarIngrediente("Encontrados em lush caves");
        itens.add(esporos);

        Item rosaWither = new Item("Rosa de Wither",
            "Flor rara que cresce onde Wither mata mobs",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        rosaWither.adicionarIngrediente("Gerada quando Wither mata um mob");
        itens.add(rosaWither);

        // ===== DISCOS DE MÚSICA =====
        // (disco13, discoCat, discoOtherside, disco5 já existem anteriormente)
        
        Item discoBlocks = new Item("Disco de Música - Blocks",
            "Disco de música eletrônico",
            MinecraftEdition.BOTH, "Item Especial");
        discoBlocks.adicionarIngrediente("Drop quando Creeper é morto por Esqueleto");
        itens.add(discoBlocks);

        Item discoChirp = new Item("Disco de Música - Chirp",
            "Disco de música alegre",
            MinecraftEdition.BOTH, "Item Especial");
        discoChirp.adicionarIngrediente("Drop quando Creeper é morto por Esqueleto");
        itens.add(discoChirp);

        Item discoMall = new Item("Disco de Música - Mall",
            "Disco de música calma",
            MinecraftEdition.BOTH, "Item Especial");
        discoMall.adicionarIngrediente("Drop quando Creeper é morto por Esqueleto");
        itens.add(discoMall);

        Item discoPigstep = new Item("Disco de Música - Pigstep",
            "Disco raro encontrado no Nether",
            MinecraftEdition.BOTH, "Item Especial");
        discoPigstep.adicionarIngrediente("Baús em Bastion Remnants");
        itens.add(discoPigstep);

        // ===== ITENS DE MAPA E NAVEGAÇÃO =====
        // (mapa, bussolaLodestone já existem anteriormente)
        
        Item mapaVazio = new Item("Mapa Vazio",
            "Mapa não ativado",
            MinecraftEdition.BOTH, "Utilitário");
        mapaVazio.adicionarIngrediente("9x Papel");
        itens.add(mapaVazio);

        Item bussolaRecovery = new Item("Bússola de Recuperação",
            "Aponta para último local de morte",
            MinecraftEdition.BOTH, "Utilitário");
        bussolaRecovery.adicionarIngrediente("8x Fragmento de Echo + 1x Bússola");
        itens.add(bussolaRecovery);

        // ===== ITENS ESPECIAIS DE COMBATE =====
        // (flecha, flechaEspectral, foguete, ovo já existem anteriormente)
        
        Item bolaNeye = new Item("Bola de Neve",
            "Projétil que empurra mobs",
            MinecraftEdition.BOTH, "Utilitário");
        bolaNeye.adicionarIngrediente("Coletar neve com pá");
        itens.add(bolaNeye);

        // ===== LIVROS ESPECIAIS =====
        // (livroEncantado, livroPena já existem anteriormente)
        
        Item livroConhecimento = new Item("Livro do Conhecimento",
            "Contém receitas de crafting",
            MinecraftEdition.BOTH, "Utilitário");
        livroConhecimento.adicionarIngrediente("1x Livro + 1x Crafting Table");
        itens.add(livroConhecimento);

        // ===== ITENS DECORATIVOS E ESPECIAIS =====
        // (quadro, quadroBrilhante, sino já existem anteriormente)
        
        Item armadura = new Item("Suporte de Armadura",
            "Exibe armaduras e itens",
            MinecraftEdition.BOTH, "Bloco Utilitário");
        armadura.adicionarIngrediente("6x Gravetos + 1x Laje de Pedra");
        itens.add(armadura);

        // ===== VELAS (CANDLES) =====
        
        Item vela = new Item("Vela",
            "Fonte de luz decorativa",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vela.adicionarIngrediente("1x Favo de Mel + 1x Linha");
        itens.add(vela);

        Item velaColorida = new Item("Vela Colorida",
            "Vela tingida com corantes (16 cores)",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        velaColorida.adicionarIngrediente("1x Vela + 1x Corante");
        itens.add(velaColorida);

        Item boloBirthday = new Item("Bolo com Vela",
            "Bolo decorado com vela acesa",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        boloBirthday.adicionarIngrediente("1x Bolo + 1x Vela");
        itens.add(boloBirthday);

        // ===== FRAGMENTOS E SHARDS =====
        
        Item fragmentoEcho = new Item("Fragmento de Echo",
            "Material raro do Deep Dark",
            MinecraftEdition.BOTH, "Material");
        fragmentoEcho.adicionarIngrediente("Baús em Ancient Cities");
        itens.add(fragmentoEcho);

        // ===== ARMADURAS ESPECIAIS =====
        
        Item capaceteTartaruga = new Item("Capacete de Tartaruga",
            "Capacete que dá respiração aquática",
            MinecraftEdition.BOTH, "Armadura");
        capaceteTartaruga.adicionarIngrediente("5x Carapaça de Tartaruga");
        itens.add(capaceteTartaruga);

        // ===== BLOCOS DE AMETISTA =====
        // (armaduras netherite, blocoAmetista já existem anteriormente)
        
        Item geodoAmetista = new Item("Broto de Ametista",
            "Geodo que cresce cristais de ametista",
            MinecraftEdition.BOTH, "Bloco Especial");
        geodoAmetista.adicionarIngrediente("Encontrado em geodos subterrâneos");
        itens.add(geodoAmetista);

        Item vidroMatizado = new Item("Vidro Matizado",
            "Vidro que bloqueia luz mas permite visão",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroMatizado.adicionarIngrediente("2x Cristal de Ametista + 2x Vidro");
        itens.add(vidroMatizado);

        // ===== TAPETES COLORIDOS =====
        
        Item tapeteVermelho = new Item("Tapete Vermelho",
            "Tapete decorativo vermelho",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        tapeteVermelho.adicionarIngrediente("2x Lã Vermelha");
        itens.add(tapeteVermelho);

        Item tapeteAzul = new Item("Tapete Azul",
            "Tapete decorativo azul",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        tapeteAzul.adicionarIngrediente("2x Lã Azul");
        itens.add(tapeteAzul);

        Item tapeteVerde = new Item("Tapete Verde",
            "Tapete decorativo verde",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        tapeteVerde.adicionarIngrediente("2x Lã Verde");
        itens.add(tapeteVerde);

        Item tapeteBranco = new Item("Tapete Branco",
            "Tapete decorativo branco",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        tapeteBranco.adicionarIngrediente("2x Lã Branca");
        itens.add(tapeteBranco);

        // ===== CORANTES ADICIONAIS =====
        // (coranteVerde, coranteBranco, corantePreto já existem anteriormente)
        
        Item coranteAzul = new Item("Corante Azul",
            "Corante para tingir itens",
            MinecraftEdition.BOTH, "Material");
        coranteAzul.adicionarIngrediente("Lápis-lazúli ou Orquídea Azul");
        itens.add(coranteAzul);

        Item coranteCiano = new Item("Corante Ciano",
            "Corante para tingir itens",
            MinecraftEdition.BOTH, "Material");
        coranteCiano.adicionarIngrediente("1x Corante Azul + 1x Corante Verde");
        itens.add(coranteCiano);

        Item coranteMagenta = new Item("Corante Magenta",
            "Corante para tingir itens",
            MinecraftEdition.BOTH, "Material");
        coranteMagenta.adicionarIngrediente("1x Corante Roxo + 1x Corante Rosa");
        itens.add(coranteMagenta);

        Item coranteLima = new Item("Corante Lima",
            "Corante para tingir itens",
            MinecraftEdition.BOTH, "Material");
        coranteLima.adicionarIngrediente("1x Corante Verde + 1x Corante Branco");
        itens.add(coranteLima);

        // ===== BLOCOS DECORATIVOS DO NETHER =====
        
        Item tijoloCarmesim = new Item("Tijolo Carmesim do Nether",
            "Bloco decorativo vermelho",
            MinecraftEdition.BOTH, "Bloco Construção");
        tijoloCarmesim.adicionarIngrediente("4x Verruga do Nether");
        itens.add(tijoloCarmesim);

        Item tijoloVermelho = new Item("Tijolo Vermelho do Nether",
            "Bloco decorativo avermelhado",
            MinecraftEdition.BOTH, "Bloco Construção");
        tijoloVermelho.adicionarIngrediente("4x Tijolo do Nether");
        itens.add(tijoloVermelho);

        Item quartzoPilar = new Item("Pilar de Quartzo",
            "Bloco decorativo com padrão vertical",
            MinecraftEdition.BOTH, "Bloco Construção");
        quartzoPilar.adicionarIngrediente("2x Bloco de Quartzo");
        itens.add(quartzoPilar);

        Item quartzoEntalhado = new Item("Quartzo Entalhado",
            "Bloco decorativo de quartzo entalhado",
            MinecraftEdition.BOTH, "Bloco Construção");
        quartzoEntalhado.adicionarIngrediente("1x Laje de Quartzo + 1x Laje de Quartzo");
        itens.add(quartzoEntalhado);

        // ===== BLOCOS DO END =====
        // (quartzoLiso já existe anteriormente)
        
        Item purpurPilar = new Item("Pilar Purpur",
            "Bloco decorativo roxo com padrão vertical",
            MinecraftEdition.BOTH, "Bloco Construção");
        purpurPilar.adicionarIngrediente("1x Laje Purpur + 1x Laje Purpur");
        itens.add(purpurPilar);

        Item frutaChorusAssada = new Item("Fruta Chorus Assada",
            "Ingrediente para varinha do End",
            MinecraftEdition.BOTH, "Material");
        frutaChorusAssada.adicionarIngrediente("Fundir Fruta Chorus");
        itens.add(frutaChorusAssada);

        Item varaEnd = new Item("Varinha do End",
            "Material para crafting de purpur",
            MinecraftEdition.BOTH, "Material");
        varaEnd.adicionarIngrediente("1x Fruta Chorus Assada + 1x Pérola de Blaze");
        itens.add(varaEnd);

        // ===== ITENS RAROS E ESPECIAIS =====
        // (esmeralda, blocoEsmeralda, dragaoOvo, cabeças já existem anteriormente)

        // ===== BLOCOS TÉCNICOS E ESPECIAIS =====
        // (barreira, luz já existem anteriormente)
        
        Item blocoComando = new Item("Bloco de Comando",
            "Executa comandos automáticos",
            MinecraftEdition.BOTH, "Bloco Especial");
        blocoComando.adicionarIngrediente("Apenas modo criativo");
        itens.add(blocoComando);

        Item blocoEstrutura = new Item("Bloco de Estrutura",
            "Salva e carrega estruturas",
            MinecraftEdition.BOTH, "Bloco Especial");
        blocoEstrutura.adicionarIngrediente("Apenas modo criativo");
        itens.add(blocoEstrutura);

        // ===== ITENS DE ARQUEOLOGIA (1.20+) =====
        
        Item pincel = new Item("Pincel",
            "Ferramenta para arqueologia",
            MinecraftEdition.BOTH, "Ferramenta");
        pincel.adicionarIngrediente("1x Pena + 1x Lingote de Cobre + 1x Graveto");
        itens.add(pincel);

        Item ceramicaDecorada = new Item("Cerâmica Decorada",
            "Bloco decorativo com padrões",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        ceramicaDecorada.adicionarIngrediente("4x Fragmento de Cerâmica");
        itens.add(ceramicaDecorada);

        Item areiaSuspeita = new Item("Areia Suspeita",
            "Areia que contém itens arqueológicos",
            MinecraftEdition.BOTH, "Bloco Especial");
        areiaSuspeita.adicionarIngrediente("Encontrada em estruturas antigas");
        itens.add(areiaSuspeita);

        Item cascalhoSuspeito = new Item("Cascalho Suspeito",
            "Cascalho que contém itens arqueológicos",
            MinecraftEdition.BOTH, "Bloco Especial");
        cascalhoSuspeito.adicionarIngrediente("Encontrado em estruturas antigas");
        itens.add(cascalhoSuspeito);

        Item moldeArmaduraNetherite = new Item("Molde de Armadura Netherite",
            "Template para upgrade de armadura",
            MinecraftEdition.BOTH, "Material");
        moldeArmaduraNetherite.adicionarIngrediente("Baús em Bastion Remnants");
        itens.add(moldeArmaduraNetherite);

        // ===== BLOCOS DE PEDRA VARIANTES =====
        
        Item pedraLisa = new Item("Pedra Lisa",
            "Pedra com textura lisa",
            MinecraftEdition.BOTH, "Bloco Construção");
        pedraLisa.adicionarIngrediente("Fundir Pedregulho");
        itens.add(pedraLisa);

        Item tijolosPedraRachados = new Item("Tijolos de Pedra Rachados",
            "Tijolos decorativos rachados",
            MinecraftEdition.BOTH, "Bloco Construção");
        tijolosPedraRachados.adicionarIngrediente("Fundir Tijolos de Pedra");
        itens.add(tijolosPedraRachados);

        Item tijolosPedraMusgosos = new Item("Tijolos de Pedra Musgosos",
            "Tijolos cobertos de musgo",
            MinecraftEdition.BOTH, "Bloco Construção");
        tijolosPedraMusgosos.adicionarIngrediente("1x Tijolos de Pedra + 1x Trepadeira");
        itens.add(tijolosPedraMusgosos);

        Item pedregulhoMusgoso = new Item("Pedregulho Musgoso",
            "Pedregulho coberto de musgo",
            MinecraftEdition.BOTH, "Bloco Construção");
        pedregulhoMusgoso.adicionarIngrediente("1x Pedregulho + 1x Trepadeira");
        itens.add(pedregulhoMusgoso);

        Item pedraEntalhada = new Item("Pedra Entalhada",
            "Bloco decorativo com padrão",
            MinecraftEdition.BOTH, "Bloco Construção");
        pedraEntalhada.adicionarIngrediente("1x Laje de Pedra + 1x Laje de Pedra");
        itens.add(pedraEntalhada);

        // ===== BLOCOS DE DEEPSLATE VARIANTES =====
        
        Item deepslatePolido = new Item("Deepslate Polido",
            "Deepslate com textura polida",
            MinecraftEdition.BOTH, "Bloco Construção");
        deepslatePolido.adicionarIngrediente("4x Deepslate Pedregulhoso");
        itens.add(deepslatePolido);

        Item tijoloDeepslate = new Item("Tijolo de Deepslate",
            "Tijolos feitos de deepslate",
            MinecraftEdition.BOTH, "Bloco Construção");
        tijoloDeepslate.adicionarIngrediente("4x Deepslate Polido");
        itens.add(tijoloDeepslate);

        Item ladrilhoDeepslate = new Item("Ladrilho de Deepslate",
            "Ladrilhos decorativos de deepslate",
            MinecraftEdition.BOTH, "Bloco Construção");
        ladrilhoDeepslate.adicionarIngrediente("4x Tijolo de Deepslate");
        itens.add(ladrilhoDeepslate);

        Item deepslateRachado = new Item("Tijolo de Deepslate Rachado",
            "Tijolos rachados de deepslate",
            MinecraftEdition.BOTH, "Bloco Construção");
        deepslateRachado.adicionarIngrediente("Fundir Tijolo de Deepslate");
        itens.add(deepslateRachado);

        // ===== BLOCOS DE BLACKSTONE VARIANTES =====
        
        Item blackstoneDourado = new Item("Blackstone Dourado",
            "Blackstone com detalhes dourados",
            MinecraftEdition.BOTH, "Bloco Construção");
        blackstoneDourado.adicionarIngrediente("Encontrado em Bastion Remnants");
        itens.add(blackstoneDourado);

        Item tijoloBlackstone = new Item("Tijolo de Blackstone",
            "Tijolos feitos de blackstone",
            MinecraftEdition.BOTH, "Bloco Construção");
        tijoloBlackstone.adicionarIngrediente("4x Blackstone Polido");
        itens.add(tijoloBlackstone);

        Item blackstoneRachado = new Item("Tijolo de Blackstone Rachado",
            "Tijolos rachados de blackstone",
            MinecraftEdition.BOTH, "Bloco Construção");
        blackstoneRachado.adicionarIngrediente("Fundir Tijolo de Blackstone");
        itens.add(blackstoneRachado);

        Item tijoloNetherRachado = new Item("Tijolo do Nether Rachado",
            "Tijolos rachados do Nether",
            MinecraftEdition.BOTH, "Bloco Construção");
        tijoloNetherRachado.adicionarIngrediente("Fundir Tijolos do Nether");
        itens.add(tijoloNetherRachado);

        // ===== BLOCOS DE MADEIRA ESPECIAIS =====
        
        Item madeiraDescascada = new Item("Madeira Descascada",
            "Tronco sem casca (todas as faces iguais)",
            MinecraftEdition.BOTH, "Bloco Construção");
        madeiraDescascada.adicionarIngrediente("Usar machado em tronco");
        itens.add(madeiraDescascada);

        Item blocoMadeira = new Item("Bloco de Madeira",
            "Bloco com textura de casca em todos os lados",
            MinecraftEdition.BOTH, "Bloco Construção");
        blocoMadeira.adicionarIngrediente("4x Tronco");
        itens.add(blocoMadeira);

        Item tabuasCarvalho = new Item("Tábuas de Carvalho",
            "Tábuas de madeira de carvalho",
            MinecraftEdition.BOTH, "Bloco Construção");
        tabuasCarvalho.adicionarIngrediente("1x Tronco de Carvalho");
        itens.add(tabuasCarvalho);

        Item tabuasBetula = new Item("Tábuas de Bétula",
            "Tábuas de madeira de bétula",
            MinecraftEdition.BOTH, "Bloco Construção");
        tabuasBetula.adicionarIngrediente("1x Tronco de Bétula");
        itens.add(tabuasBetula);

        Item tabuasAbeto = new Item("Tábuas de Abeto",
            "Tábuas de madeira de abeto",
            MinecraftEdition.BOTH, "Bloco Construção");
        tabuasAbeto.adicionarIngrediente("1x Tronco de Abeto");
        itens.add(tabuasAbeto);

        Item tabuasSelva = new Item("Tábuas de Selva",
            "Tábuas de madeira da selva",
            MinecraftEdition.BOTH, "Bloco Construção");
        tabuasSelva.adicionarIngrediente("1x Tronco de Selva");
        itens.add(tabuasSelva);

        Item tabuasAcacia = new Item("Tábuas de Acácia",
            "Tábuas de madeira de acácia",
            MinecraftEdition.BOTH, "Bloco Construção");
        tabuasAcacia.adicionarIngrediente("1x Tronco de Acácia");
        itens.add(tabuasAcacia);

        Item tabuasCarvalhoEscuro = new Item("Tábuas de Carvalho Escuro",
            "Tábuas de madeira de carvalho escuro",
            MinecraftEdition.BOTH, "Bloco Construção");
        tabuasCarvalhoEscuro.adicionarIngrediente("1x Tronco de Carvalho Escuro");
        itens.add(tabuasCarvalhoEscuro);

        // ===== BLOCOS DE TERRA E GRAMA =====
        
        Item blocoGrama = new Item("Bloco de Grama",
            "Bloco de terra com grama por cima",
            MinecraftEdition.BOTH, "Bloco Natural");
        blocoGrama.adicionarIngrediente("Grama se espalha para terra adjacente com luz");
        itens.add(blocoGrama);

        Item caminhoGrama = new Item("Caminho de Grama",
            "Caminho decorativo feito em grama",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        caminhoGrama.adicionarIngrediente("Usar pá em bloco de grama");
        itens.add(caminhoGrama);

        Item terraArada = new Item("Terra Arada",
            "Terra preparada para agricultura",
            MinecraftEdition.BOTH, "Bloco Funcional");
        terraArada.adicionarIngrediente("Usar enxada em terra ou grama");
        itens.add(terraArada);

        Item podzol = new Item("Podzol",
            "Solo especial de florestas escuras",
            MinecraftEdition.BOTH, "Bloco Natural");
        podzol.adicionarIngrediente("Encontrado em florestas de taiga");
        itens.add(podzol);

        Item micelio = new Item("Micélio",
            "Solo especial de ilha de cogumelos",
            MinecraftEdition.BOTH, "Bloco Natural");
        micelio.adicionarIngrediente("Encontrado em ilhas de cogumelos");
        itens.add(micelio);

        Item terraEnraizada = new Item("Terra Enraizada",
            "Terra com raízes de azaleia",
            MinecraftEdition.BOTH, "Bloco Natural");
        terraEnraizada.adicionarIngrediente("Encontrada sob árvores de azaleia");
        itens.add(terraEnraizada);

        // ===== BLOCOS DE AREIA E CASCALHO =====
        
        Item areiaVermelha = new Item("Areia Vermelha",
            "Areia avermelhada encontrada em badlands",
            MinecraftEdition.BOTH, "Bloco Natural");
        areiaVermelha.adicionarIngrediente("Encontrada em biomas de badlands");
        itens.add(areiaVermelha);

        Item blocoAreia = new Item("Arenito",
            "Bloco feito de areia compactada",
            MinecraftEdition.BOTH, "Bloco Construção");
        blocoAreia.adicionarIngrediente("4x Areia");
        itens.add(blocoAreia);

        Item arenitoLiso = new Item("Arenito Liso",
            "Arenito com textura lisa",
            MinecraftEdition.BOTH, "Bloco Construção");
        arenitoLiso.adicionarIngrediente("Fundir Arenito");
        itens.add(arenitoLiso);

        Item arenitoEntalhado = new Item("Arenito Entalhado",
            "Arenito decorativo com padrão",
            MinecraftEdition.BOTH, "Bloco Construção");
        arenitoEntalhado.adicionarIngrediente("1x Laje de Arenito + 1x Laje de Arenito");
        itens.add(arenitoEntalhado);

        Item arenitoVermelho = new Item("Arenito Vermelho",
            "Bloco feito de areia vermelha",
            MinecraftEdition.BOTH, "Bloco Construção");
        arenitoVermelho.adicionarIngrediente("4x Areia Vermelha");
        itens.add(arenitoVermelho);

        Item concretoAmarelo = new Item("Concreto Amarelo",
            "Bloco de concreto amarelo sólido",
            MinecraftEdition.BOTH, "Bloco Construção");
        concretoAmarelo.adicionarIngrediente("Pó de Concreto Amarelo + Água");
        itens.add(concretoAmarelo);

        Item concretoVermelho = new Item("Concreto Vermelho",
            "Bloco de concreto vermelho sólido",
            MinecraftEdition.BOTH, "Bloco Construção");
        concretoVermelho.adicionarIngrediente("Pó de Concreto Vermelho + Água");
        itens.add(concretoVermelho);

        Item concretoAzul = new Item("Concreto Azul",
            "Bloco de concreto azul sólido",
            MinecraftEdition.BOTH, "Bloco Construção");
        concretoAzul.adicionarIngrediente("Pó de Concreto Azul + Água");
        itens.add(concretoAzul);

        // ===== LAJES E ESCADAS =====
        
        Item lajeCarvalho = new Item("Laje de Carvalho",
            "Meia altura de tábuas",
            MinecraftEdition.BOTH, "Bloco Construção");
        lajeCarvalho.adicionarIngrediente("3x Tábuas de Carvalho");
        itens.add(lajeCarvalho);

        Item escadaCarvalho = new Item("Escada de Carvalho",
            "Escada de madeira de carvalho",
            MinecraftEdition.BOTH, "Bloco Construção");
        escadaCarvalho.adicionarIngrediente("6x Tábuas de Carvalho");
        itens.add(escadaCarvalho);

        Item lajePedra = new Item("Laje de Pedra",
            "Meia altura de pedra",
            MinecraftEdition.BOTH, "Bloco Construção");
        lajePedra.adicionarIngrediente("3x Pedra");
        itens.add(lajePedra);

        Item escadaPedra = new Item("Escada de Pedra",
            "Escada de pedra",
            MinecraftEdition.BOTH, "Bloco Construção");
        escadaPedra.adicionarIngrediente("6x Pedregulho");
        itens.add(escadaPedra);

        Item lajeTijolo = new Item("Laje de Tijolo",
            "Meia altura de tijolos",
            MinecraftEdition.BOTH, "Bloco Construção");
        lajeTijolo.adicionarIngrediente("3x Bloco de Tijolos");
        itens.add(lajeTijolo);

        Item escadaTijolo = new Item("Escada de Tijolo",
            "Escada de tijolos",
            MinecraftEdition.BOTH, "Bloco Construção");
        escadaTijolo.adicionarIngrediente("6x Bloco de Tijolos");
        itens.add(escadaTijolo);

        // ===== MUROS E CERCAS ESPECIAIS =====
        
        Item muroPedra = new Item("Muro de Pedregulho",
            "Muro decorativo de pedra",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        muroPedra.adicionarIngrediente("6x Pedregulho");
        itens.add(muroPedra);

        Item muroTijolo = new Item("Muro de Tijolo",
            "Muro decorativo de tijolo",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        muroTijolo.adicionarIngrediente("6x Bloco de Tijolos");
        itens.add(muroTijolo);

        Item muroArenito = new Item("Muro de Arenito",
            "Muro decorativo de arenito",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        muroArenito.adicionarIngrediente("6x Arenito");
        itens.add(muroArenito);

        Item cercaCarvalho = new Item("Cerca de Carvalho",
            "Cerca de madeira de carvalho",
            MinecraftEdition.BOTH, "Bloco Utilitário");
        cercaCarvalho.adicionarIngrediente("4x Gravetos + 2x Tábuas de Carvalho");
        itens.add(cercaCarvalho);

        Item portaoCarvalho = new Item("Portão de Carvalho",
            "Portão de cerca de carvalho",
            MinecraftEdition.BOTH, "Bloco Utilitário");
        portaoCarvalho.adicionarIngrediente("4x Gravetos + 2x Tábuas de Carvalho");
        itens.add(portaoCarvalho);

        // ===== VIDROS COLORIDOS (16 CORES) =====
        
        Item vidroBranco = new Item("Vidro Branco",
            "Vidro branco transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroBranco.adicionarIngrediente("8x Vidro + 1x Corante Branco");
        itens.add(vidroBranco);

        Item vidroLaranja = new Item("Vidro Laranja",
            "Vidro laranja transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroLaranja.adicionarIngrediente("8x Vidro + 1x Corante Laranja");
        itens.add(vidroLaranja);

        Item vidroMagenta = new Item("Vidro Magenta",
            "Vidro magenta transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroMagenta.adicionarIngrediente("8x Vidro + 1x Corante Magenta");
        itens.add(vidroMagenta);

        Item vidroAzulClaro = new Item("Vidro Azul Claro",
            "Vidro azul claro transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroAzulClaro.adicionarIngrediente("8x Vidro + 1x Corante Azul Claro");
        itens.add(vidroAzulClaro);

        Item vidroAmarelo = new Item("Vidro Amarelo",
            "Vidro amarelo transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroAmarelo.adicionarIngrediente("8x Vidro + 1x Corante Amarelo");
        itens.add(vidroAmarelo);

        Item vidroLima = new Item("Vidro Lima",
            "Vidro verde lima transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroLima.adicionarIngrediente("8x Vidro + 1x Corante Lima");
        itens.add(vidroLima);

        Item vidroRosa = new Item("Vidro Rosa",
            "Vidro rosa transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroRosa.adicionarIngrediente("8x Vidro + 1x Corante Rosa");
        itens.add(vidroRosa);

        Item vidroCinza = new Item("Vidro Cinza",
            "Vidro cinza transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroCinza.adicionarIngrediente("8x Vidro + 1x Corante Cinza");
        itens.add(vidroCinza);

        Item vidroCinzaClaro = new Item("Vidro Cinza Claro",
            "Vidro cinza claro transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroCinzaClaro.adicionarIngrediente("8x Vidro + 1x Corante Cinza Claro");
        itens.add(vidroCinzaClaro);

        Item vidroCiano = new Item("Vidro Ciano",
            "Vidro ciano transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroCiano.adicionarIngrediente("8x Vidro + 1x Corante Ciano");
        itens.add(vidroCiano);

        Item vidroRoxo = new Item("Vidro Roxo",
            "Vidro roxo transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroRoxo.adicionarIngrediente("8x Vidro + 1x Corante Roxo");
        itens.add(vidroRoxo);

        Item vidroAzul = new Item("Vidro Azul",
            "Vidro azul transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroAzul.adicionarIngrediente("8x Vidro + 1x Corante Azul");
        itens.add(vidroAzul);

        Item vidroMarrom = new Item("Vidro Marrom",
            "Vidro marrom transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroMarrom.adicionarIngrediente("8x Vidro + 1x Corante Marrom");
        itens.add(vidroMarrom);

        Item vidroVerde = new Item("Vidro Verde",
            "Vidro verde transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroVerde.adicionarIngrediente("8x Vidro + 1x Corante Verde");
        itens.add(vidroVerde);

        Item vidroVermelho = new Item("Vidro Vermelho",
            "Vidro vermelho transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroVermelho.adicionarIngrediente("8x Vidro + 1x Corante Vermelho");
        itens.add(vidroVermelho);

        Item vidroPreto = new Item("Vidro Preto",
            "Vidro preto transparente",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        vidroPreto.adicionarIngrediente("8x Vidro + 1x Corante Preto");
        itens.add(vidroPreto);

        // ===== PAINÉIS DE VIDRO COLORIDOS (16 CORES) =====
        
        Item painelVidroBranco = new Item("Painel de Vidro Branco",
            "Painel fino de vidro branco",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroBranco.adicionarIngrediente("6x Vidro Branco");
        itens.add(painelVidroBranco);

        Item painelVidroLaranja = new Item("Painel de Vidro Laranja",
            "Painel fino de vidro laranja",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroLaranja.adicionarIngrediente("6x Vidro Laranja");
        itens.add(painelVidroLaranja);

        Item painelVidroMagenta = new Item("Painel de Vidro Magenta",
            "Painel fino de vidro magenta",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroMagenta.adicionarIngrediente("6x Vidro Magenta");
        itens.add(painelVidroMagenta);

        Item painelVidroAzulClaro = new Item("Painel de Vidro Azul Claro",
            "Painel fino de vidro azul claro",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroAzulClaro.adicionarIngrediente("6x Vidro Azul Claro");
        itens.add(painelVidroAzulClaro);

        Item painelVidroAmarelo = new Item("Painel de Vidro Amarelo",
            "Painel fino de vidro amarelo",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroAmarelo.adicionarIngrediente("6x Vidro Amarelo");
        itens.add(painelVidroAmarelo);

        Item painelVidroLima = new Item("Painel de Vidro Lima",
            "Painel fino de vidro verde lima",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroLima.adicionarIngrediente("6x Vidro Lima");
        itens.add(painelVidroLima);

        Item painelVidroRosa = new Item("Painel de Vidro Rosa",
            "Painel fino de vidro rosa",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroRosa.adicionarIngrediente("6x Vidro Rosa");
        itens.add(painelVidroRosa);

        Item painelVidroCinza = new Item("Painel de Vidro Cinza",
            "Painel fino de vidro cinza",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroCinza.adicionarIngrediente("6x Vidro Cinza");
        itens.add(painelVidroCinza);

        Item painelVidroCinzaClaro = new Item("Painel de Vidro Cinza Claro",
            "Painel fino de vidro cinza claro",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroCinzaClaro.adicionarIngrediente("6x Vidro Cinza Claro");
        itens.add(painelVidroCinzaClaro);

        Item painelVidroCiano = new Item("Painel de Vidro Ciano",
            "Painel fino de vidro ciano",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroCiano.adicionarIngrediente("6x Vidro Ciano");
        itens.add(painelVidroCiano);

        Item painelVidroRoxo = new Item("Painel de Vidro Roxo",
            "Painel fino de vidro roxo",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroRoxo.adicionarIngrediente("6x Vidro Roxo");
        itens.add(painelVidroRoxo);

        Item painelVidroAzul = new Item("Painel de Vidro Azul",
            "Painel fino de vidro azul",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroAzul.adicionarIngrediente("6x Vidro Azul");
        itens.add(painelVidroAzul);

        Item painelVidroMarrom = new Item("Painel de Vidro Marrom",
            "Painel fino de vidro marrom",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroMarrom.adicionarIngrediente("6x Vidro Marrom");
        itens.add(painelVidroMarrom);

        Item painelVidroVerde = new Item("Painel de Vidro Verde",
            "Painel fino de vidro verde",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroVerde.adicionarIngrediente("6x Vidro Verde");
        itens.add(painelVidroVerde);

        Item painelVidroVermelho = new Item("Painel de Vidro Vermelho",
            "Painel fino de vidro vermelho",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroVermelho.adicionarIngrediente("6x Vidro Vermelho");
        itens.add(painelVidroVermelho);

        Item painelVidroPreto = new Item("Painel de Vidro Preto",
            "Painel fino de vidro preto",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        painelVidroPreto.adicionarIngrediente("6x Vidro Preto");
        itens.add(painelVidroPreto);

        // ===== BLOCOS FUNCIONAIS ADICIONAIS =====
        
        Item defumador = new Item("Defumador",
            "Cozinha comida 2x mais rápido",
            MinecraftEdition.BOTH, "Bloco Funcional");
        defumador.adicionarIngrediente("4x Tronco + 1x Fornalha");
        itens.add(defumador);

        Item composteira = new Item("Composteira",
            "Transforma itens orgânicos em farinha de osso",
            MinecraftEdition.BOTH, "Bloco Funcional");
        composteira.adicionarIngrediente("7x Lajes de Madeira");
        itens.add(composteira);

        Item pedestalEncantamento = new Item("Pedestal de Encantamento",
            "Aumenta nível de encantamentos",
            MinecraftEdition.BOTH, "Bloco Funcional");
        pedestalEncantamento.adicionarIngrediente("Encontrado em End Ships");
        itens.add(pedestalEncantamento);

        Item blocoAbobora = new Item("Abóbora Esculpida",
            "Abóbora decorativa esculpida",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        blocoAbobora.adicionarIngrediente("Usar tesoura em abóbora");
        itens.add(blocoAbobora);

        Item jackLantern = new Item("Abóbora de Halloween",
            "Abóbora iluminada",
            MinecraftEdition.BOTH, "Bloco Decorativo");
        jackLantern.adicionarIngrediente("1x Abóbora Esculpida + 1x Tocha");
        itens.add(jackLantern);
    }

    private void carregarPocoes() {
        Pocao cura = new Pocao("Poção de Cura",
            "Restaura 4 corações instantaneamente",
            "Instantâneo",
            MinecraftEdition.BOTH);
        cura.setBasePotion("Poção Estranha");
        cura.adicionarIngrediente("Garrafa de Água");
        cura.adicionarIngrediente("Verruga do Nether");
        cura.adicionarIngrediente("Melancia Reluzente");
        pocoes.add(cura);

        Pocao forca = new Pocao("Poção de Força",
            "Aumenta o dano corpo a corpo em 3 pontos",
            "3 minutos (8 min com Redstone)",
            MinecraftEdition.BOTH);
        forca.setBasePotion("Poção Estranha");
        forca.adicionarIngrediente("Garrafa de Água");
        forca.adicionarIngrediente("Verruga do Nether");
        forca.adicionarIngrediente("Pó de Blaze");
        pocoes.add(forca);

        Pocao velocidade = new Pocao("Poção de Velocidade",
            "Aumenta velocidade de movimento em 20%",
            "3 minutos (8 min com Redstone)",
            MinecraftEdition.BOTH);
        velocidade.setBasePotion("Poção Estranha");
        velocidade.adicionarIngrediente("Garrafa de Água");
        velocidade.adicionarIngrediente("Verruga do Nether");
        velocidade.adicionarIngrediente("Açúcar");
        pocoes.add(velocidade);

        Pocao visaoNoturna = new Pocao("Poção de Visão Noturna",
            "Permite ver no escuro como se fosse dia",
            "3 minutos (8 min com Redstone)",
            MinecraftEdition.BOTH);
        visaoNoturna.setBasePotion("Poção Estranha");
        visaoNoturna.adicionarIngrediente("Garrafa de Água");
        visaoNoturna.adicionarIngrediente("Verruga do Nether");
        visaoNoturna.adicionarIngrediente("Cenoura Dourada");
        pocoes.add(visaoNoturna);

        Pocao invisibilidade = new Pocao("Poção de Invisibilidade",
            "Torna o jogador invisível (armadura ainda visível)",
            "3 minutos (8 min com Redstone)",
            MinecraftEdition.BOTH);
        invisibilidade.setBasePotion("Poção de Visão Noturna");
        invisibilidade.adicionarIngrediente("Poção de Visão Noturna");
        invisibilidade.adicionarIngrediente("Olho de Aranha Fermentado");
        pocoes.add(invisibilidade);

        Pocao regeneracao = new Pocao("Poção de Regeneração",
            "Regenera vida ao longo do tempo",
            "45 segundos (2 min com Redstone)",
            MinecraftEdition.BOTH);
        regeneracao.setBasePotion("Poção Estranha");
        regeneracao.adicionarIngrediente("Garrafa de Água");
        regeneracao.adicionarIngrediente("Verruga do Nether");
        regeneracao.adicionarIngrediente("Lágrima de Ghast");
        pocoes.add(regeneracao);

        Pocao resistenciaFogo = new Pocao("Poção de Resistência ao Fogo",
            "Imunidade a fogo, lava e dano de blaze",
            "3 minutos (8 min com Redstone)",
            MinecraftEdition.BOTH);
        resistenciaFogo.setBasePotion("Poção Estranha");
        resistenciaFogo.adicionarIngrediente("Garrafa de Água");
        resistenciaFogo.adicionarIngrediente("Verruga do Nether");
        resistenciaFogo.adicionarIngrediente("Creme de Magma");
        pocoes.add(resistenciaFogo);

        Pocao respiracaoAquatica = new Pocao("Poção de Respiração Aquática",
            "Permite respirar debaixo d'água",
            "3 minutos (8 min com Redstone)",
            MinecraftEdition.BOTH);
        respiracaoAquatica.setBasePotion("Poção Estranha");
        respiracaoAquatica.adicionarIngrediente("Garrafa de Água");
        respiracaoAquatica.adicionarIngrediente("Verruga do Nether");
        respiracaoAquatica.adicionarIngrediente("Peixe-balão");
        pocoes.add(respiracaoAquatica);

        Pocao quedaLenta = new Pocao("Poção de Queda Lenta",
            "Reduz velocidade de queda e previne dano",
            "1:30 minutos (4 min com Redstone)",
            MinecraftEdition.BOTH);
        quedaLenta.setBasePotion("Poção Estranha");
        quedaLenta.adicionarIngrediente("Garrafa de Água");
        quedaLenta.adicionarIngrediente("Verruga do Nether");
        quedaLenta.adicionarIngrediente("Membrana de Phantom");
        pocoes.add(quedaLenta);

        Pocao sorte = new Pocao("Poção de Sorte",
            "Aumenta chances de encontrar itens melhores (Java Only)",
            "5 minutos",
            MinecraftEdition.JAVA);
        sorte.setBasePotion("Poção Estranha");
        sorte.adicionarIngrediente("Garrafa de Água");
        sorte.adicionarIngrediente("Verruga do Nether");
        sorte.adicionarIngrediente("Trevo de Quatro Folhas");
        pocoes.add(sorte);

        Pocao veneno = new Pocao("Poção de Veneno",
            "Causa dano ao longo do tempo",
            "45 segundos (2 min com Redstone)",
            MinecraftEdition.BOTH);
        veneno.setBasePotion("Poção Estranha");
        veneno.adicionarIngrediente("Garrafa de Água");
        veneno.adicionarIngrediente("Verruga do Nether");
        veneno.adicionarIngrediente("Olho de Aranha");
        pocoes.add(veneno);

        Pocao fraqueza = new Pocao("Poção de Fraqueza",
            "Reduz dano corpo a corpo em 4 pontos",
            "1:30 minutos (4 min com Redstone)",
            MinecraftEdition.BOTH);
        fraqueza.setBasePotion("Garrafa de Água");
        fraqueza.adicionarIngrediente("Garrafa de Água");
        fraqueza.adicionarIngrediente("Olho de Aranha Fermentado");
        pocoes.add(fraqueza);

        // Mais poções
        Pocao salto = new Pocao("Poção de Salto",
            "Aumenta altura de pulo e reduz dano de queda",
            "3 minutos (8 min com Redstone)",
            MinecraftEdition.BOTH);
        salto.setBasePotion("Poção Estranha");
        salto.adicionarIngrediente("Garrafa de Água");
        salto.adicionarIngrediente("Verruga do Nether");
        salto.adicionarIngrediente("Pé de Coelho");
        pocoes.add(salto);

        Pocao lentidao = new Pocao("Poção de Lentidão",
            "Reduz velocidade de movimento",
            "1:30 minutos (4 min com Redstone)",
            MinecraftEdition.BOTH);
        lentidao.setBasePotion("Poção de Velocidade");
        lentidao.adicionarIngrediente("Poção de Velocidade");
        lentidao.adicionarIngrediente("Olho de Aranha Fermentado");
        pocoes.add(lentidao);

        Pocao dano = new Pocao("Poção de Dano",
            "Causa 6 pontos de dano instantâneo",
            "Instantâneo",
            MinecraftEdition.BOTH);
        dano.setBasePotion("Poção de Cura");
        dano.adicionarIngrediente("Poção de Cura");
        dano.adicionarIngrediente("Olho de Aranha Fermentado");
        pocoes.add(dano);

        Pocao conduite = new Pocao("Poção do Conduit",
            "Respiração aquática, visão noturna e velocidade na água",
            "Varia",
            MinecraftEdition.BOTH);
        conduite.setBasePotion("Obtido do Conduit (não brewável)");
        pocoes.add(conduite);

        Pocao tartaruga = new Pocao("Poção da Tartaruga Mestre",
            "Concede resistência e lentidão",
            "20 segundos (40 seg com Redstone)",
            MinecraftEdition.BOTH);
        tartaruga.setBasePotion("Poção Estranha");
        tartaruga.adicionarIngrediente("Garrafa de Água");
        tartaruga.adicionarIngrediente("Verruga do Nether");
        tartaruga.adicionarIngrediente("Carapaça de Tartaruga");
        pocoes.add(tartaruga);

        // ==================== POÇÕES ADICIONAIS ====================

        Pocao forcaII = new Pocao("Poção de Força II",
            "Aumenta o dano corpo a corpo em 6 pontos",
            "1:30 minutos",
            MinecraftEdition.BOTH);
        forcaII.setBasePotion("Poção de Força");
        forcaII.adicionarIngrediente("Poção de Força");
        forcaII.adicionarIngrediente("Pó de Pedra Luminosa");
        pocoes.add(forcaII);

        Pocao velocidadeII = new Pocao("Poção de Velocidade II",
            "Aumenta velocidade de movimento em 40%",
            "1:30 minutos",
            MinecraftEdition.BOTH);
        velocidadeII.setBasePotion("Poção de Velocidade");
        velocidadeII.adicionarIngrediente("Poção de Velocidade");
        velocidadeII.adicionarIngrediente("Pó de Pedra Luminosa");
        pocoes.add(velocidadeII);

        Pocao regeneracaoII = new Pocao("Poção de Regeneração II",
            "Regenera vida rapidamente",
            "22 segundos",
            MinecraftEdition.BOTH);
        regeneracaoII.setBasePotion("Poção de Regeneração");
        regeneracaoII.adicionarIngrediente("Poção de Regeneração");
        regeneracaoII.adicionarIngrediente("Pó de Pedra Luminosa");
        pocoes.add(regeneracaoII);

        Pocao curaII = new Pocao("Poção de Cura II",
            "Restaura 8 corações instantaneamente",
            "Instantâneo",
            MinecraftEdition.BOTH);
        curaII.setBasePotion("Poção de Cura");
        curaII.adicionarIngrediente("Poção de Cura");
        curaII.adicionarIngrediente("Pó de Pedra Luminosa");
        pocoes.add(curaII);

        Pocao danoII = new Pocao("Poção de Dano II",
            "Causa 12 pontos de dano instantâneo",
            "Instantâneo",
            MinecraftEdition.BOTH);
        danoII.setBasePotion("Poção de Dano");
        danoII.adicionarIngrediente("Poção de Dano");
        danoII.adicionarIngrediente("Pó de Pedra Luminosa");
        pocoes.add(danoII);

        Pocao saltoII = new Pocao("Poção de Salto II",
            "Permite pular muito mais alto",
            "1:30 minutos",
            MinecraftEdition.BOTH);
        saltoII.setBasePotion("Poção de Salto");
        saltoII.adicionarIngrediente("Poção de Salto");
        saltoII.adicionarIngrediente("Pó de Pedra Luminosa");
        pocoes.add(saltoII);

        Pocao venenoII = new Pocao("Poção de Veneno II",
            "Causa dano severo ao longo do tempo",
            "21 segundos",
            MinecraftEdition.BOTH);
        venenoII.setBasePotion("Poção de Veneno");
        venenoII.adicionarIngrediente("Poção de Veneno");
        venenoII.adicionarIngrediente("Pó de Pedra Luminosa");
        pocoes.add(venenoII);

        Pocao lentidaoII = new Pocao("Poção de Lentidão II",
            "Reduz muito a velocidade de movimento",
            "1:30 minutos",
            MinecraftEdition.BOTH);
        lentidaoII.setBasePotion("Poção de Lentidão");
        lentidaoII.adicionarIngrediente("Poção de Lentidão");
        lentidaoII.adicionarIngrediente("Pó de Pedra Luminosa");
        pocoes.add(lentidaoII);

        Pocao lentidaoIV = new Pocao("Poção de Lentidão IV",
            "Reduz drasticamente a velocidade",
            "20 segundos",
            MinecraftEdition.BOTH);
        lentidaoIV.setBasePotion("Poção da Tartaruga Mestre");
        lentidaoIV.adicionarIngrediente("Poção da Tartaruga Mestre");
        lentidaoIV.adicionarIngrediente("Olho de Aranha Fermentado");
        pocoes.add(lentidaoIV);

        // Versões estendidas
        Pocao forcaExt = new Pocao("Poção de Força (Estendida)",
            "Aumenta o dano corpo a corpo em 3 pontos",
            "8 minutos",
            MinecraftEdition.BOTH);
        forcaExt.setBasePotion("Poção de Força");
        forcaExt.adicionarIngrediente("Poção de Força");
        forcaExt.adicionarIngrediente("Pó de Redstone");
        pocoes.add(forcaExt);

        Pocao velocidadeExt = new Pocao("Poção de Velocidade (Estendida)",
            "Aumenta velocidade de movimento em 20%",
            "8 minutos",
            MinecraftEdition.BOTH);
        velocidadeExt.setBasePotion("Poção de Velocidade");
        velocidadeExt.adicionarIngrediente("Poção de Velocidade");
        velocidadeExt.adicionarIngrediente("Pó de Redstone");
        pocoes.add(velocidadeExt);

        Pocao regeneracaoExt = new Pocao("Poção de Regeneração (Estendida)",
            "Regenera vida ao longo do tempo",
            "2 minutos",
            MinecraftEdition.BOTH);
        regeneracaoExt.setBasePotion("Poção de Regeneração");
        regeneracaoExt.adicionarIngrediente("Poção de Regeneração");
        regeneracaoExt.adicionarIngrediente("Pó de Redstone");
        pocoes.add(regeneracaoExt);

        Pocao resistenciaFogoExt = new Pocao("Poção de Resistência ao Fogo (Estendida)",
            "Imunidade a fogo, lava e dano de blaze",
            "8 minutos",
            MinecraftEdition.BOTH);
        resistenciaFogoExt.setBasePotion("Poção de Resistência ao Fogo");
        resistenciaFogoExt.adicionarIngrediente("Poção de Resistência ao Fogo");
        resistenciaFogoExt.adicionarIngrediente("Pó de Redstone");
        pocoes.add(resistenciaFogoExt);

        Pocao respiracaoAquaticaExt = new Pocao("Poção de Respiração Aquática (Estendida)",
            "Permite respirar debaixo d'água",
            "8 minutos",
            MinecraftEdition.BOTH);
        respiracaoAquaticaExt.setBasePotion("Poção de Respiração Aquática");
        respiracaoAquaticaExt.adicionarIngrediente("Poção de Respiração Aquática");
        respiracaoAquaticaExt.adicionarIngrediente("Pó de Redstone");
        pocoes.add(respiracaoAquaticaExt);

        Pocao visaoNoturnaExt = new Pocao("Poção de Visão Noturna (Estendida)",
            "Permite ver no escuro como se fosse dia",
            "8 minutos",
            MinecraftEdition.BOTH);
        visaoNoturnaExt.setBasePotion("Poção de Visão Noturna");
        visaoNoturnaExt.adicionarIngrediente("Poção de Visão Noturna");
        visaoNoturnaExt.adicionarIngrediente("Pó de Redstone");
        pocoes.add(visaoNoturnaExt);

        Pocao invisibilidadeExt = new Pocao("Poção de Invisibilidade (Estendida)",
            "Torna o jogador invisível",
            "8 minutos",
            MinecraftEdition.BOTH);
        invisibilidadeExt.setBasePotion("Poção de Invisibilidade");
        invisibilidadeExt.adicionarIngrediente("Poção de Invisibilidade");
        invisibilidadeExt.adicionarIngrediente("Pó de Redstone");
        pocoes.add(invisibilidadeExt);

        Pocao quedaLentaExt = new Pocao("Poção de Queda Lenta (Estendida)",
            "Reduz velocidade de queda",
            "4 minutos",
            MinecraftEdition.BOTH);
        quedaLentaExt.setBasePotion("Poção de Queda Lenta");
        quedaLentaExt.adicionarIngrediente("Poção de Queda Lenta");
        quedaLentaExt.adicionarIngrediente("Pó de Redstone");
        pocoes.add(quedaLentaExt);

        Pocao saltoExt = new Pocao("Poção de Salto (Estendida)",
            "Aumenta altura de pulo",
            "8 minutos",
            MinecraftEdition.BOTH);
        saltoExt.setBasePotion("Poção de Salto");
        saltoExt.adicionarIngrediente("Poção de Salto");
        saltoExt.adicionarIngrediente("Pó de Redstone");
        pocoes.add(saltoExt);

        Pocao venenoExt = new Pocao("Poção de Veneno (Estendida)",
            "Causa dano ao longo do tempo",
            "2 minutos",
            MinecraftEdition.BOTH);
        venenoExt.setBasePotion("Poção de Veneno");
        venenoExt.adicionarIngrediente("Poção de Veneno");
        venenoExt.adicionarIngrediente("Pó de Redstone");
        pocoes.add(venenoExt);

        Pocao lentidaoExt = new Pocao("Poção de Lentidão (Estendida)",
            "Reduz velocidade de movimento",
            "4 minutos",
            MinecraftEdition.BOTH);
        lentidaoExt.setBasePotion("Poção de Lentidão");
        lentidaoExt.adicionarIngrediente("Poção de Lentidão");
        lentidaoExt.adicionarIngrediente("Pó de Redstone");
        pocoes.add(lentidaoExt);

        Pocao fraquezaExt = new Pocao("Poção de Fraqueza (Estendida)",
            "Reduz dano corpo a corpo",
            "4 minutos",
            MinecraftEdition.BOTH);
        fraquezaExt.setBasePotion("Poção de Fraqueza");
        fraquezaExt.adicionarIngrediente("Poção de Fraqueza");
        fraquezaExt.adicionarIngrediente("Pó de Redstone");
        pocoes.add(fraquezaExt);

        Pocao tartarugaExt = new Pocao("Poção da Tartaruga Mestre (Estendida)",
            "Concede resistência e lentidão",
            "40 segundos",
            MinecraftEdition.BOTH);
        tartarugaExt.setBasePotion("Poção da Tartaruga Mestre");
        tartarugaExt.adicionarIngrediente("Poção da Tartaruga Mestre");
        tartarugaExt.adicionarIngrediente("Pó de Redstone");
        pocoes.add(tartarugaExt);

        // Poções base
        Pocao pocaoEstranha = new Pocao("Poção Estranha",
            "Base para criar outras poções",
            "N/A",
            MinecraftEdition.BOTH);
        pocaoEstranha.setBasePotion("Garrafa de Água");
        pocaoEstranha.adicionarIngrediente("Garrafa de Água");
        pocaoEstranha.adicionarIngrediente("Verruga do Nether");
        pocoes.add(pocaoEstranha);

        Pocao pocaoGrossa = new Pocao("Poção Grossa",
            "Poção sem efeito (experimento)",
            "N/A",
            MinecraftEdition.BOTH);
        pocaoGrossa.setBasePotion("Garrafa de Água");
        pocaoGrossa.adicionarIngrediente("Garrafa de Água");
        pocaoGrossa.adicionarIngrediente("Pó de Pedra Luminosa");
        pocoes.add(pocaoGrossa);

        Pocao pocaoMundana = new Pocao("Poção Mundana",
            "Poção sem efeito (experimento)",
            "N/A",
            MinecraftEdition.BOTH);
        pocaoMundana.setBasePotion("Garrafa de Água");
        pocaoMundana.adicionarIngrediente("Garrafa de Água");
        pocaoMundana.adicionarIngrediente("Pó de Redstone, Açúcar, Olho de Aranha, etc");
        pocoes.add(pocaoMundana);

        // ==================== POÇÕES ADICIONAIS DO MINECRAFT ====================

        Pocao azar = new Pocao("Poção de Azar",
            "Reduz chances de encontrar itens melhores (Java Only)",
            "5 minutos",
            MinecraftEdition.JAVA);
        azar.setBasePotion("Não pode ser fabricada normalmente");
        azar.adicionarIngrediente("Obtida por comandos apenas");
        pocoes.add(azar);

        Pocao levitacao = new Pocao("Poção de Levitação",
            "Faz o jogador flutuar lentamente para cima (Bedrock Only)",
            "Varia",
            MinecraftEdition.BEDROCK);
        levitacao.setBasePotion("Obtida por comandos no Bedrock");
        levitacao.adicionarIngrediente("Não pode ser fabricada");
        pocoes.add(levitacao);

        Pocao decaimento = new Pocao("Poção de Decaimento",
            "Aplica o efeito Wither, causando dano ao longo do tempo (Bedrock Only)",
            "Varia",
            MinecraftEdition.BEDROCK);
        decaimento.setBasePotion("Poção Estranha");
        decaimento.adicionarIngrediente("Rosa de Wither (Bedrock Edition)");
        pocoes.add(decaimento);

        // ==================== POÇÕES ARREMESSÁVEIS (SPLASH POTIONS) ====================

        Pocao splashCura = new Pocao("Poção Arremessável de Cura",
            "Restaura 3 corações em área (75% de eficácia)",
            "Instantâneo",
            MinecraftEdition.BOTH);
        splashCura.setBasePotion("Poção de Cura");
        splashCura.adicionarIngrediente("Poção de Cura");
        splashCura.adicionarIngrediente("Pólvora");
        pocoes.add(splashCura);

        Pocao splashCuraII = new Pocao("Poção Arremessável de Cura II",
            "Restaura 6 corações em área (75% de eficácia)",
            "Instantâneo",
            MinecraftEdition.BOTH);
        splashCuraII.setBasePotion("Poção de Cura II");
        splashCuraII.adicionarIngrediente("Poção de Cura II");
        splashCuraII.adicionarIngrediente("Pólvora");
        pocoes.add(splashCuraII);

        Pocao splashForca = new Pocao("Poção Arremessável de Força",
            "Aumenta dano corpo a corpo em área",
            "2:15 minutos",
            MinecraftEdition.BOTH);
        splashForca.setBasePotion("Poção de Força");
        splashForca.adicionarIngrediente("Poção de Força");
        splashForca.adicionarIngrediente("Pólvora");
        pocoes.add(splashForca);

        Pocao splashForcaII = new Pocao("Poção Arremessável de Força II",
            "Aumenta muito o dano corpo a corpo em área",
            "1:07 minutos",
            MinecraftEdition.BOTH);
        splashForcaII.setBasePotion("Poção de Força II");
        splashForcaII.adicionarIngrediente("Poção de Força II");
        splashForcaII.adicionarIngrediente("Pólvora");
        pocoes.add(splashForcaII);

        Pocao splashVelocidade = new Pocao("Poção Arremessável de Velocidade",
            "Aumenta velocidade de movimento em área",
            "2:15 minutos",
            MinecraftEdition.BOTH);
        splashVelocidade.setBasePotion("Poção de Velocidade");
        splashVelocidade.adicionarIngrediente("Poção de Velocidade");
        splashVelocidade.adicionarIngrediente("Pólvora");
        pocoes.add(splashVelocidade);

        Pocao splashVelocidadeII = new Pocao("Poção Arremessável de Velocidade II",
            "Aumenta muito a velocidade em área",
            "1:07 minutos",
            MinecraftEdition.BOTH);
        splashVelocidadeII.setBasePotion("Poção de Velocidade II");
        splashVelocidadeII.adicionarIngrediente("Poção de Velocidade II");
        splashVelocidadeII.adicionarIngrediente("Pólvora");
        pocoes.add(splashVelocidadeII);

        Pocao splashRegeneracao = new Pocao("Poção Arremessável de Regeneração",
            "Regenera vida ao longo do tempo em área",
            "33 segundos",
            MinecraftEdition.BOTH);
        splashRegeneracao.setBasePotion("Poção de Regeneração");
        splashRegeneracao.adicionarIngrediente("Poção de Regeneração");
        splashRegeneracao.adicionarIngrediente("Pólvora");
        pocoes.add(splashRegeneracao);

        Pocao splashRegeneracaoII = new Pocao("Poção Arremessável de Regeneração II",
            "Regenera vida rapidamente em área",
            "16 segundos",
            MinecraftEdition.BOTH);
        splashRegeneracaoII.setBasePotion("Poção de Regeneração II");
        splashRegeneracaoII.adicionarIngrediente("Poção de Regeneração II");
        splashRegeneracaoII.adicionarIngrediente("Pólvora");
        pocoes.add(splashRegeneracaoII);

        Pocao splashResistenciaFogo = new Pocao("Poção Arremessável de Resistência ao Fogo",
            "Imunidade a fogo em área",
            "2:15 minutos",
            MinecraftEdition.BOTH);
        splashResistenciaFogo.setBasePotion("Poção de Resistência ao Fogo");
        splashResistenciaFogo.adicionarIngrediente("Poção de Resistência ao Fogo");
        splashResistenciaFogo.adicionarIngrediente("Pólvora");
        pocoes.add(splashResistenciaFogo);

        Pocao splashVisaoNoturna = new Pocao("Poção Arremessável de Visão Noturna",
            "Visão noturna em área",
            "2:15 minutos",
            MinecraftEdition.BOTH);
        splashVisaoNoturna.setBasePotion("Poção de Visão Noturna");
        splashVisaoNoturna.adicionarIngrediente("Poção de Visão Noturna");
        splashVisaoNoturna.adicionarIngrediente("Pólvora");
        pocoes.add(splashVisaoNoturna);

        Pocao splashInvisibilidade = new Pocao("Poção Arremessável de Invisibilidade",
            "Invisibilidade em área",
            "2:15 minutos",
            MinecraftEdition.BOTH);
        splashInvisibilidade.setBasePotion("Poção de Invisibilidade");
        splashInvisibilidade.adicionarIngrediente("Poção de Invisibilidade");
        splashInvisibilidade.adicionarIngrediente("Pólvora");
        pocoes.add(splashInvisibilidade);

        Pocao splashSalto = new Pocao("Poção Arremessável de Salto",
            "Aumenta altura de pulo em área",
            "2:15 minutos",
            MinecraftEdition.BOTH);
        splashSalto.setBasePotion("Poção de Salto");
        splashSalto.adicionarIngrediente("Poção de Salto");
        splashSalto.adicionarIngrediente("Pólvora");
        pocoes.add(splashSalto);

        Pocao splashSaltoII = new Pocao("Poção Arremessável de Salto II",
            "Permite pular muito alto em área",
            "1:07 minutos",
            MinecraftEdition.BOTH);
        splashSaltoII.setBasePotion("Poção de Salto II");
        splashSaltoII.adicionarIngrediente("Poção de Salto II");
        splashSaltoII.adicionarIngrediente("Pólvora");
        pocoes.add(splashSaltoII);

        Pocao splashVeneno = new Pocao("Poção Arremessável de Veneno",
            "Envenena criaturas em área",
            "33 segundos",
            MinecraftEdition.BOTH);
        splashVeneno.setBasePotion("Poção de Veneno");
        splashVeneno.adicionarIngrediente("Poção de Veneno");
        splashVeneno.adicionarIngrediente("Pólvora");
        pocoes.add(splashVeneno);

        Pocao splashVenenoII = new Pocao("Poção Arremessável de Veneno II",
            "Envenena severamente em área",
            "16 segundos",
            MinecraftEdition.BOTH);
        splashVenenoII.setBasePotion("Poção de Veneno II");
        splashVenenoII.adicionarIngrediente("Poção de Veneno II");
        splashVenenoII.adicionarIngrediente("Pólvora");
        pocoes.add(splashVenenoII);

        Pocao splashFraqueza = new Pocao("Poção Arremessável de Fraqueza",
            "Reduz dano em área",
            "1:07 minutos",
            MinecraftEdition.BOTH);
        splashFraqueza.setBasePotion("Poção de Fraqueza");
        splashFraqueza.adicionarIngrediente("Poção de Fraqueza");
        splashFraqueza.adicionarIngrediente("Pólvora");
        pocoes.add(splashFraqueza);

        Pocao splashLentidao = new Pocao("Poção Arremessável de Lentidão",
            "Reduz velocidade em área",
            "1:07 minutos",
            MinecraftEdition.BOTH);
        splashLentidao.setBasePotion("Poção de Lentidão");
        splashLentidao.adicionarIngrediente("Poção de Lentidão");
        splashLentidao.adicionarIngrediente("Pólvora");
        pocoes.add(splashLentidao);

        Pocao splashDano = new Pocao("Poção Arremessável de Dano",
            "Causa 4,5 pontos de dano em área",
            "Instantâneo",
            MinecraftEdition.BOTH);
        splashDano.setBasePotion("Poção de Dano");
        splashDano.adicionarIngrediente("Poção de Dano");
        splashDano.adicionarIngrediente("Pólvora");
        pocoes.add(splashDano);

        Pocao splashDanoII = new Pocao("Poção Arremessável de Dano II",
            "Causa 9 pontos de dano em área",
            "Instantâneo",
            MinecraftEdition.BOTH);
        splashDanoII.setBasePotion("Poção de Dano II");
        splashDanoII.adicionarIngrediente("Poção de Dano II");
        splashDanoII.adicionarIngrediente("Pólvora");
        pocoes.add(splashDanoII);

        Pocao splashRespiracaoAquatica = new Pocao("Poção Arremessável de Respiração Aquática",
            "Respiração aquática em área",
            "2:15 minutos",
            MinecraftEdition.BOTH);
        splashRespiracaoAquatica.setBasePotion("Poção de Respiração Aquática");
        splashRespiracaoAquatica.adicionarIngrediente("Poção de Respiração Aquática");
        splashRespiracaoAquatica.adicionarIngrediente("Pólvora");
        pocoes.add(splashRespiracaoAquatica);

        Pocao splashQuedaLenta = new Pocao("Poção Arremessável de Queda Lenta",
            "Queda lenta em área",
            "1:07 minutos",
            MinecraftEdition.BOTH);
        splashQuedaLenta.setBasePotion("Poção de Queda Lenta");
        splashQuedaLenta.adicionarIngrediente("Poção de Queda Lenta");
        splashQuedaLenta.adicionarIngrediente("Pólvora");
        pocoes.add(splashQuedaLenta);

        Pocao splashTartaruga = new Pocao("Poção Arremessável da Tartaruga Mestre",
            "Resistência e lentidão em área",
            "15 segundos",
            MinecraftEdition.BOTH);
        splashTartaruga.setBasePotion("Poção da Tartaruga Mestre");
        splashTartaruga.adicionarIngrediente("Poção da Tartaruga Mestre");
        splashTartaruga.adicionarIngrediente("Pólvora");
        pocoes.add(splashTartaruga);

        // ==================== POÇÕES PERSISTENTES (LINGERING POTIONS) ====================

        Pocao lingeringCura = new Pocao("Poção Persistente de Cura",
            "Cria nuvem de cura que persiste por 10 segundos",
            "Instantâneo",
            MinecraftEdition.BOTH);
        lingeringCura.setBasePotion("Poção Arremessável de Cura");
        lingeringCura.adicionarIngrediente("Poção Arremessável de Cura");
        lingeringCura.adicionarIngrediente("Bafo de Dragão");
        pocoes.add(lingeringCura);

        Pocao lingeringForca = new Pocao("Poção Persistente de Força",
            "Cria nuvem de força que persiste",
            "33 segundos (área)",
            MinecraftEdition.BOTH);
        lingeringForca.setBasePotion("Poção Arremessável de Força");
        lingeringForca.adicionarIngrediente("Poção Arremessável de Força");
        lingeringForca.adicionarIngrediente("Bafo de Dragão");
        pocoes.add(lingeringForca);

        Pocao lingeringVelocidade = new Pocao("Poção Persistente de Velocidade",
            "Cria nuvem de velocidade que persiste",
            "33 segundos (área)",
            MinecraftEdition.BOTH);
        lingeringVelocidade.setBasePotion("Poção Arremessável de Velocidade");
        lingeringVelocidade.adicionarIngrediente("Poção Arremessável de Velocidade");
        lingeringVelocidade.adicionarIngrediente("Bafo de Dragão");
        pocoes.add(lingeringVelocidade);

        Pocao lingeringDano = new Pocao("Poção Persistente de Dano",
            "Cria nuvem venenosa que causa dano",
            "Instantâneo (área)",
            MinecraftEdition.BOTH);
        lingeringDano.setBasePotion("Poção Arremessável de Dano");
        lingeringDano.adicionarIngrediente("Poção Arremessável de Dano");
        lingeringDano.adicionarIngrediente("Bafo de Dragão");
        pocoes.add(lingeringDano);

        Pocao lingeringVeneno = new Pocao("Poção Persistente de Veneno",
            "Cria nuvem de veneno que persiste",
            "11 segundos (área)",
            MinecraftEdition.BOTH);
        lingeringVeneno.setBasePotion("Poção Arremessável de Veneno");
        lingeringVeneno.adicionarIngrediente("Poção Arremessável de Veneno");
        lingeringVeneno.adicionarIngrediente("Bafo de Dragão");
        pocoes.add(lingeringVeneno);

        Pocao lingeringFraqueza = new Pocao("Poção Persistente de Fraqueza",
            "Cria nuvem de fraqueza que persiste",
            "22 segundos (área)",
            MinecraftEdition.BOTH);
        lingeringFraqueza.setBasePotion("Poção Arremessável de Fraqueza");
        lingeringFraqueza.adicionarIngrediente("Poção Arremessável de Fraqueza");
        lingeringFraqueza.adicionarIngrediente("Bafo de Dragão");
        pocoes.add(lingeringFraqueza);

        Pocao lingeringLentidao = new Pocao("Poção Persistente de Lentidão",
            "Cria nuvem de lentidão que persiste",
            "22 segundos (área)",
            MinecraftEdition.BOTH);
        lingeringLentidao.setBasePotion("Poção Arremessável de Lentidão");
        lingeringLentidao.adicionarIngrediente("Poção Arremessável de Lentidão");
        lingeringLentidao.adicionarIngrediente("Bafo de Dragão");
        pocoes.add(lingeringLentidao);

        Pocao lingeringRegeneracao = new Pocao("Poção Persistente de Regeneração",
            "Cria nuvem de regeneração que persiste",
            "11 segundos (área)",
            MinecraftEdition.BOTH);
        lingeringRegeneracao.setBasePotion("Poção Arremessável de Regeneração");
        lingeringRegeneracao.adicionarIngrediente("Poção Arremessável de Regeneração");
        lingeringRegeneracao.adicionarIngrediente("Bafo de Dragão");
        pocoes.add(lingeringRegeneracao);

        Pocao lingeringResistenciaFogo = new Pocao("Poção Persistente de Resistência ao Fogo",
            "Cria nuvem de resistência ao fogo",
            "33 segundos (área)",
            MinecraftEdition.BOTH);
        lingeringResistenciaFogo.setBasePotion("Poção Arremessável de Resistência ao Fogo");
        lingeringResistenciaFogo.adicionarIngrediente("Poção Arremessável de Resistência ao Fogo");
        lingeringResistenciaFogo.adicionarIngrediente("Bafo de Dragão");
        pocoes.add(lingeringResistenciaFogo);

        Pocao lingeringVisaoNoturna = new Pocao("Poção Persistente de Visão Noturna",
            "Cria nuvem de visão noturna",
            "33 segundos (área)",
            MinecraftEdition.BOTH);
        lingeringVisaoNoturna.setBasePotion("Poção Arremessável de Visão Noturna");
        lingeringVisaoNoturna.adicionarIngrediente("Poção Arremessável de Visão Noturna");
        lingeringVisaoNoturna.adicionarIngrediente("Bafo de Dragão");
        pocoes.add(lingeringVisaoNoturna);

        Pocao lingeringInvisibilidade = new Pocao("Poção Persistente de Invisibilidade",
            "Cria nuvem de invisibilidade",
            "33 segundos (área)",
            MinecraftEdition.BOTH);
        lingeringInvisibilidade.setBasePotion("Poção Arremessável de Invisibilidade");
        lingeringInvisibilidade.adicionarIngrediente("Poção Arremessável de Invisibilidade");
        lingeringInvisibilidade.adicionarIngrediente("Bafo de Dragão");
        pocoes.add(lingeringInvisibilidade);

        Pocao lingeringSalto = new Pocao("Poção Persistente de Salto",
            "Cria nuvem de impulso de salto",
            "33 segundos (área)",
            MinecraftEdition.BOTH);
        lingeringSalto.setBasePotion("Poção Arremessável de Salto");
        lingeringSalto.adicionarIngrediente("Poção Arremessável de Salto");
        lingeringSalto.adicionarIngrediente("Bafo de Dragão");
        pocoes.add(lingeringSalto);

        Pocao lingeringRespiracaoAquatica = new Pocao("Poção Persistente de Respiração Aquática",
            "Cria nuvem de respiração aquática",
            "33 segundos (área)",
            MinecraftEdition.BOTH);
        lingeringRespiracaoAquatica.setBasePotion("Poção Arremessável de Respiração Aquática");
        lingeringRespiracaoAquatica.adicionarIngrediente("Poção Arremessável de Respiração Aquática");
        lingeringRespiracaoAquatica.adicionarIngrediente("Bafo de Dragão");
        pocoes.add(lingeringRespiracaoAquatica);

        Pocao lingeringQuedaLenta = new Pocao("Poção Persistente de Queda Lenta",
            "Cria nuvem de queda lenta",
            "22 segundos (área)",
            MinecraftEdition.BOTH);
        lingeringQuedaLenta.setBasePotion("Poção Arremessável de Queda Lenta");
        lingeringQuedaLenta.adicionarIngrediente("Poção Arremessável de Queda Lenta");
        lingeringQuedaLenta.adicionarIngrediente("Bafo de Dragão");
        pocoes.add(lingeringQuedaLenta);

        Pocao lingeringTartaruga = new Pocao("Poção Persistente da Tartaruga Mestre",
            "Cria nuvem de resistência",
            "5 segundos (área)",
            MinecraftEdition.BOTH);
        lingeringTartaruga.setBasePotion("Poção Arremessável da Tartaruga Mestre");
        lingeringTartaruga.adicionarIngrediente("Poção Arremessável da Tartaruga Mestre");
        lingeringTartaruga.adicionarIngrediente("Bafo de Dragão");
        pocoes.add(lingeringTartaruga);
    }

    private void carregarEncantamentos() {
        // Encantamentos baseados na tabela fornecida
        
        // Afinidade Aquática
        encantamentos.add(new Encantamento(
            "Afinidade Aquática (Aqua Affinity)",
            "Acrescenta velocidade de mineração quando submerso.",
            false,
            "",
            1,
            "🪖🐢",
            "",
            2,
            MinecraftEdition.BOTH
        ));
        
        // Ruína dos Artrópodes
        encantamentos.add(new Encantamento(
            "Ruína dos Artrópodes (Bane of Arthropods)",
            "Infringe dano e aplica lentidão IV às criaturas da categoria artrópode (Aranhas, Aranhas da caverna, Traças, Endermite e Abelhas).",
            false,
            "Julgamento, Afiação",
            5,
            "🗡️🪓",
            "🪓 [Ed: apenas]",
            5,
            MinecraftEdition.BOTH
        ));
        
        // Proteção contra Explosões
        encantamentos.add(new Encantamento(
            "Proteção contra Explosões (Blast Protection)",
            "Reduz o dano e o impacto de uma explosão.",
            false,
            "Proteção contra Fogo, Proteção, Proteção contra Projéteis",
            4,
            "🪖🦺🩳🥾",
            "",
            2,
            MinecraftEdition.BOTH
        ));
        
        // Condutividade
        encantamentos.add(new Encantamento(
            "Condutividade (Channeling)",
            "Durante uma tempestade, o tridente invoca um raio no alvo quando o tridente o atinge.",
            false,
            "Correnteza",
            1,
            "🔱",
            "",
            1,
            MinecraftEdition.BOTH
        ));
        
        // Cleaving [em breve: Edição Java Combat Tests]
        encantamentos.add(new Encantamento(
            "Cleaving",
            "Aumenta o dano e o tempo de recuperação do escudo.",
            false,
            "Afiação, Ruína dos Artrópodes, Julgamento",
            3,
            "🪓",
            "",
            1,
            MinecraftEdition.JAVA  // Desconhecido na tabela, assumindo Java
        ));
        
        // Maldição do Ligamento
        encantamentos.add(new Encantamento(
            "Maldição do Ligamento (Curse of Binding)",
            "Itens não podem ser removidos de seus slots.",
            true,
            "",
            1,
            "",
            "🪖🦺🩳🥾🎃💀🐉🧟⚡🤖🧑‍🏫🛀🕵️",
            1,
            MinecraftEdition.BOTH
        ));
        
        // Maldição do Desaparecimento
        encantamentos.add(new Encantamento(
            "Maldição do Desaparecimento (Curse of Vanishing)",
            "O item desaparece quando o jogador morre.",
            true,
            "",
            1,
            "",
            "🪖🦺🩳🥾⚔️🏹🔱🪓🛡️🗡️🥕🦴🪵🪣🧭🪝🔥💎🍎🔔📘⚱️🧪🔮",
            1,
            MinecraftEdition.BOTH
        ));
        
        // Passos Profundos
        encantamentos.add(new Encantamento(
            "Passos Profundos (Depth Strider)",
            "Incrementa velocidade de movimentação quando submerso.",
            false,
            "Passos Gelados",
            3,
            "🥾",
            "",
            2,
            MinecraftEdition.BOTH
        ));
        
        // Eficiência
        encantamentos.add(new Encantamento(
            "Eficiência (Efficiency)",
            "Incrementa velocidade de mineração.",
            false,
            "",
            5,
            "⛏️🪓🪑🔪",
            "🔪",
            10,
            MinecraftEdition.BOTH
        ));
        
        // Peso-pena
        encantamentos.add(new Encantamento(
            "Peso-pena (Feather Falling)",
            "Reduz o dano de queda.",
            false,
            "",
            4,
            "🥾",
            "",
            5,
            MinecraftEdition.BOTH
        ));
        
        // Aspecto Flamejante
        encantamentos.add(new Encantamento(
            "Aspecto Flamejante (Fire Aspect)",
            "Deixa o alvo que foi atingido em chamas.",
            false,
            "",
            2,
            "🗡️",
            "🗡️ [em breve: Edição Java Combat Tests]",
            2,
            MinecraftEdition.BOTH
        ));
        
        // Proteção contra Fogo
        encantamentos.add(new Encantamento(
            "Proteção contra Fogo (Fire Protection)",
            "Reduz o dano e a duração das chamas.",
            false,
            "Proteção contra Explosões, Proteção, Proteção contra Projéteis",
            4,
            "🪖🦺🩳🥾",
            "",
            5,
            MinecraftEdition.BOTH
        ));
        
        // Chama
        encantamentos.add(new Encantamento(
            "Chama (Flame)",
            "As flechas ao acertarem o alvo deixa-o em chamas.",
            false,
            "",
            1,
            "🏹",
            "",
            2,
            MinecraftEdition.BOTH
        ));
        
        // Fortuna
        encantamentos.add(new Encantamento(
            "Fortuna (Fortune)",
            "Aumenta a possibilidade de largar mais itens ao minerar.",
            false,
            "Toque Suave",
            3,
            "⛏️🪓🔪🪑",
            "",
            2,
            MinecraftEdition.BOTH
        ));
        
        // Passos Gelados
        encantamentos.add(new Encantamento(
            "Passos Gelados (Frost Walker)",
            "Permite o jogador andar sobre a água congelando-a abaixo de seus pés.",
            true,
            "Passos Profundos",
            2,
            "🥾",
            "",
            2,
            MinecraftEdition.BOTH
        ));
        
        // Empalamento
        encantamentos.add(new Encantamento(
            "Empalamento (Impaling)",
            "Aumenta o dano causado em criaturas aquáticas na Edição Bedrock, Incrementa o dano na água e na chuva no Edição Java.",
            false,
            "",
            5,
            "🔱",
            "",
            2,
            MinecraftEdition.BOTH
        ));
        
        // Infinidade
        encantamentos.add(new Encantamento(
            "Infinidade (Infinity)",
            "Não consome flechas normais ao disparar (requer pelo menos 1 flecha no inventário).",
            false,
            "Remendo",
            1,
            "🏹",
            "",
            1,
            MinecraftEdition.BOTH
        ));
        
        // Repulsão
        encantamentos.add(new Encantamento(
            "Repulsão (Knockback)",
            "Aumenta o recuo ao atingir um inimigo.",
            false,
            "",
            2,
            "🗡️",
            "",
            5,
            MinecraftEdition.BOTH
        ));
        
        // Lealdade
        encantamentos.add(new Encantamento(
            "Lealdade (Loyalty)",
            "O tridente retorna ao jogador após ser arremessado.",
            false,
            "Correnteza",
            3,
            "🔱",
            "",
            5,
            MinecraftEdition.BOTH
        ));
        
        // Pilhagem
        encantamentos.add(new Encantamento(
            "Pilhagem (Looting)",
            "Aumenta a quantidade de itens dropados por mobs ao serem mortos.",
            false,
            "",
            3,
            "🗡️",
            "",
            2,
            MinecraftEdition.BOTH
        ));
        
        // Remendo
        encantamentos.add(new Encantamento(
            "Remendo (Mending)",
            "Repara o item usando a experiência obtida.",
            true,
            "Infinidade (apenas em arcos)",
            1,
            "",
            "🪖🦺🩳🥾⚔️🏹🔱🪓🛡️🗡️🥕🦴🪵🪣🧭🪝🔥💎🍎🔔📘⚱️🧪",
            2,
            MinecraftEdition.BOTH
        ));
        
        // Rajada
        encantamentos.add(new Encantamento(
            "Rajada (Multishot)",
            "Dispara 3 flechas ao invés de 1 (consome apenas 1 flecha).",
            false,
            "Perfurante",
            1,
            "🏹",
            "",
            2,
            MinecraftEdition.BOTH
        ));
        
        // Perfurante
        encantamentos.add(new Encantamento(
            "Perfurante (Piercing)",
            "As flechas atravessam entidades, permitindo múltiplos alvos.",
            false,
            "Rajada",
            4,
            "🏹",
            "",
            10,
            MinecraftEdition.BOTH
        ));
        
        // Poder
        encantamentos.add(new Encantamento(
            "Poder (Power)",
            "Aumenta o dano das flechas.",
            false,
            "",
            5,
            "🏹",
            "",
            10,
            MinecraftEdition.BOTH
        ));
        
        // Proteção
        encantamentos.add(new Encantamento(
            "Proteção (Protection)",
            "Reduz a maioria dos tipos de dano.",
            false,
            "Proteção contra Explosões, Proteção contra Fogo, Proteção contra Projéteis",
            4,
            "🪖🦺🩳🥾",
            "",
            10,
            MinecraftEdition.BOTH
        ));
        
        // Proteção contra Projéteis
        encantamentos.add(new Encantamento(
            "Proteção contra Projéteis (Projectile Protection)",
            "Reduz o dano de projéteis (flechas, bolas de fogo, cargas de shulker, etc.).",
            false,
            "Proteção contra Explosões, Proteção contra Fogo, Proteção",
            4,
            "🪖🦺🩳🥾",
            "",
            5,
            MinecraftEdition.BOTH
        ));
        
        // Perfuração
        encantamentos.add(new Encantamento(
            "Perfuração (Punch)",
            "Aumenta o recuo das flechas.",
            false,
            "",
            2,
            "🏹",
            "",
            2,
            MinecraftEdition.BOTH
        ));
        
        // Carga Rápida
        encantamentos.add(new Encantamento(
            "Carga Rápida (Quick Charge)",
            "Reduz o tempo de recarga de bestas.",
            false,
            "",
            3,
            "🏹",
            "",
            5,
            MinecraftEdition.BOTH
        ));
        
        // Respiração
        encantamentos.add(new Encantamento(
            "Respiração (Respiration)",
            "Estende a duração da respiração debaixo d'água.",
            false,
            "",
            3,
            "🪖",
            "🐢",
            2,
            MinecraftEdition.BOTH
        ));
        
        // Correnteza
        encantamentos.add(new Encantamento(
            "Correnteza (Riptide)",
            "O jogador é impulsionado ao arremessar o tridente enquanto estiver na água ou chuva.",
            false,
            "Canalização, Lealdade",
            3,
            "🔱",
            "",
            2,
            MinecraftEdition.BOTH
        ));
        
        // Afiação
        encantamentos.add(new Encantamento(
            "Afiação (Sharpness)",
            "Aumenta o dano de ataques corpo a corpo.",
            false,
            "Ruína dos Artrópodes, Julgamento",
            5,
            "🗡️🪓",
            "🪓 [Ed: apenas]",
            10,
            MinecraftEdition.BOTH
        ));
        
        // Toque Suave
        encantamentos.add(new Encantamento(
            "Toque Suave (Silk Touch)",
            "Blocos são minerados em sua forma original sem processamento.",
            false,
            "Fortuna",
            1,
            "⛏️🪓🔪🪑",
            "",
            1,
            MinecraftEdition.BOTH
        ));
        
        // Julgamento
        encantamentos.add(new Encantamento(
            "Julgamento (Smite)",
            "Aumenta o dano contra mortos-vivos (zumbis, esqueletos, wither, etc.).",
            false,
            "Ruína dos Artrópodes, Afiação",
            5,
            "🗡️🪓",
            "🪓 [Ed: apenas]",
            5,
            MinecraftEdition.BOTH
        ));
        
        // Caminhada das Almas
        encantamentos.add(new Encantamento(
            "Caminhada das Almas (Soul Speed)",
            "Aumenta a velocidade de movimento ao andar sobre Areia das Almas e Solo das Almas.",
            true,
            "",
            3,
            "🥾",
            "",
            1,
            MinecraftEdition.BOTH
        ));
        
        // Lâmina Afiada (Sweeping Edge)
        encantamentos.add(new Encantamento(
            "Lâmina Afiada (Sweeping Edge)",
            "Aumenta o dano do ataque em área de espadas (Java Edition apenas).",
            false,
            "",
            3,
            "🗡️",
            "",
            2,
            MinecraftEdition.JAVA
        ));
        
        // Espinhos
        encantamentos.add(new Encantamento(
            "Espinhos (Thorns)",
            "Reflete parte do dano de volta ao atacante.",
            false,
            "",
            3,
            "🪖🦺🩳🥾",
            "",
            1,
            MinecraftEdition.BOTH
        ));
        
        // Inquebrável
        encantamentos.add(new Encantamento(
            "Inquebrável (Unbreaking)",
            "Aumenta a durabilidade do item reduzindo a chance de perder durabilidade.",
            false,
            "",
            3,
            "🪖🦺🩳🥾⚔️🏹🔱🪓🛡️🗡️🥕🦴🪵🪣🧭🪝🔥💎🍎",
            "",
            5,
            MinecraftEdition.BOTH
        ));

        // ==================== ENCANTAMENTOS ADICIONAIS ====================

        // Densidade (1.19+)
        encantamentos.add(new Encantamento(
            "Densidade (Density)",
            "Aumenta o dano da maça quando cai de grande altura.",
            false,
            "Ruptura, Quebra-vento",
            5,
            "🔨",
            "",
            2,
            MinecraftEdition.BOTH
        ));

        // Ruptura (1.19+)
        encantamentos.add(new Encantamento(
            "Ruptura (Breach)",
            "Reduz a eficácia da armadura do alvo.",
            false,
            "Densidade, Quebra-vento",
            4,
            "🔨",
            "",
            2,
            MinecraftEdition.BOTH
        ));

        // Quebra-vento (1.19+)
        encantamentos.add(new Encantamento(
            "Quebra-vento (Wind Burst)",
            "Lança o jogador para o ar ao atingir algo enquanto cai.",
            false,
            "Densidade, Ruptura",
            3,
            "🔨",
            "",
            2,
            MinecraftEdition.BOTH
        ));

        // Pescaria Sortuda (Luck of the Sea)
        encantamentos.add(new Encantamento(
            "Pescaria Sortuda (Luck of the Sea)",
            "Aumenta chances de pescar itens do tesouro e reduz lixo.",
            false,
            "",
            3,
            "🎣",
            "",
            2,
            MinecraftEdition.BOTH
        ));

        // Isca (Lure)
        encantamentos.add(new Encantamento(
            "Isca (Lure)",
            "Reduz o tempo de espera da pescaria.",
            false,
            "",
            3,
            "🎣",
            "",
            2,
            MinecraftEdition.BOTH
        ));

        // Retorno (Riptide) - verificar se já não existe
        // Já existe como "Correnteza"

        // Proteção explosão - verificar duplicatas
        // Já existe

        // Aqua Affinity - verificar
        // Já existe como "Afinidade Aquática"

        // Vanguarda (Knockback II para arcos) - não existe no jogo base
        
        // Maldição do Encolhimento (Bedrock exclusivo - legacy)
        encantamentos.add(new Encantamento(
            "Maldição do Encolhimento (Curse of Binding - legacy name)",
            "Item não pode ser removido exceto ao morrer ou quebrar.",
            true,
            "",
            1,
            "🪖🦺🩳🥾",
            "",
            1,
            MinecraftEdition.BOTH
        ));

        // Thorns - já existe como "Espinhos"

        // Soul Speed - já existe como "Caminhada das Almas"

        // Swift Sneak (Esgueirar Veloz) - 1.19+
        encantamentos.add(new Encantamento(
            "Esgueirar Veloz (Swift Sneak)",
            "Aumenta a velocidade de movimento ao agachar (sneaking).",
            true,
            "",
            3,
            "🩳",
            "",
            1,
            MinecraftEdition.BOTH
        ));

        // Frost Walker - já existe como "Passos Gelados"

        // Depth Strider - já existe como "Passos Profundos"

        // Feather Falling - já existe como "Peso-pena"

        // Verificando outros encantamentos raros ou especiais

        // Binding (Curse of Binding) - já existe como "Maldição do Ligamento"

        // Vanishing (Curse of Vanishing) - já existe como "Maldição do Desaparecimento"
    }

    public List<Item> buscarItens(String termo) {
        return itens.stream()
                .filter(i -> i.getNome().toLowerCase().contains(termo.toLowerCase()) ||
                        i.getCategoria().toLowerCase().contains(termo.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Pocao> buscarPocoes(String termo) {
        return pocoes.stream()
                .filter(p -> p.getNome().toLowerCase().contains(termo.toLowerCase()) ||
                        p.getEfeito().toLowerCase().contains(termo.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Encantamento> buscarEncantamentos(String termo) {
        return encantamentos.stream()
                .filter(e -> e.getNome().toLowerCase().contains(termo.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Item> listarTodosItens() {
        return new ArrayList<>(itens);
    }

    public List<Pocao> listarTodasPocoes() {
        return new ArrayList<>(pocoes);
    }

    public List<Encantamento> listarTodosEncantamentos() {
        return new ArrayList<>(encantamentos);
    }

    public List<Armadura> getArmaduras() {
        return new ArrayList<>(armaduras);
    }

    public List<Armadura> buscarArmaduras(String termo) {
        return armaduras.stream()
                .filter(a -> a.getNome().toLowerCase().contains(termo.toLowerCase()) ||
                            a.getMaterial().toLowerCase().contains(termo.toLowerCase()))
                .collect(Collectors.toList());
    }

    private void carregarArmaduras() {
        // ==================== ARMADURAS DE COURO ====================
        
        Armadura capaceteCouro = new Armadura("Capacete de Couro", "Capacete", "Couro",
            "Armadura básica tingível", 1, 55, MinecraftEdition.BOTH);
        capaceteCouro.adicionarIngrediente("5x Couro");
        armaduras.add(capaceteCouro);

        Armadura peitoralCouro = new Armadura("Peitoral de Couro", "Peitoral", "Couro",
            "Peitoral leve e tingível", 3, 80, MinecraftEdition.BOTH);
        peitoralCouro.adicionarIngrediente("8x Couro");
        armaduras.add(peitoralCouro);

        Armadura calcasCouro = new Armadura("Calças de Couro", "Calças", "Couro",
            "Calças tingíveis", 2, 75, MinecraftEdition.BOTH);
        calcasCouro.adicionarIngrediente("7x Couro");
        armaduras.add(calcasCouro);

        Armadura botasCouro = new Armadura("Botas de Couro", "Botas", "Couro",
            "Botas leves tingíveis", 1, 65, MinecraftEdition.BOTH);
        botasCouro.adicionarIngrediente("4x Couro");
        armaduras.add(botasCouro);

        // ==================== ARMADURAS DE COTA DE MALHA ====================
        
        Armadura capaceteMalha = new Armadura("Capacete de Cota de Malha", "Capacete", "Cota de Malha",
            "Armadura rara, não craftável", 2, 165, MinecraftEdition.BOTH);
        capaceteMalha.adicionarIngrediente("Encontrado em baús ou comércio");
        armaduras.add(capaceteMalha);

        Armadura peitoralMalha = new Armadura("Peitoral de Cota de Malha", "Peitoral", "Cota de Malha",
            "Peitoral raro, não craftável", 5, 240, MinecraftEdition.BOTH);
        peitoralMalha.adicionarIngrediente("Encontrado em baús ou comércio");
        armaduras.add(peitoralMalha);

        Armadura calcasMalha = new Armadura("Calças de Cota de Malha", "Calças", "Cota de Malha",
            "Calças raras, não craftáveis", 4, 225, MinecraftEdition.BOTH);
        calcasMalha.adicionarIngrediente("Encontrado em baús ou comércio");
        armaduras.add(calcasMalha);

        Armadura botasMalha = new Armadura("Botas de Cota de Malha", "Botas", "Cota de Malha",
            "Botas raras, não craftáveis", 1, 195, MinecraftEdition.BOTH);
        botasMalha.adicionarIngrediente("Encontrado em baús ou comércio");
        armaduras.add(botasMalha);

        // ==================== ARMADURAS DE FERRO ====================
        
        Armadura capaceteFerro = new Armadura("Capacete de Ferro", "Capacete", "Ferro",
            "Proteção sólida e confiável", 2, 165, MinecraftEdition.BOTH);
        capaceteFerro.adicionarIngrediente("5x Lingote de Ferro");
        armaduras.add(capaceteFerro);

        Armadura peitoralFerro = new Armadura("Peitoral de Ferro", "Peitoral", "Ferro",
            "Excelente proteção do tronco", 6, 240, MinecraftEdition.BOTH);
        peitoralFerro.adicionarIngrediente("8x Lingote de Ferro");
        armaduras.add(peitoralFerro);

        Armadura calcasFerro = new Armadura("Calças de Ferro", "Calças", "Ferro",
            "Proteção forte para as pernas", 5, 225, MinecraftEdition.BOTH);
        calcasFerro.adicionarIngrediente("7x Lingote de Ferro");
        armaduras.add(calcasFerro);

        Armadura botasFerro = new Armadura("Botas de Ferro", "Botas", "Ferro",
            "Botas duráveis", 2, 195, MinecraftEdition.BOTH);
        botasFerro.adicionarIngrediente("4x Lingote de Ferro");
        armaduras.add(botasFerro);

        // ==================== ARMADURAS DE OURO ====================
        
        Armadura capaceteOuro = new Armadura("Capacete de Ouro", "Capacete", "Ouro",
            "Fraco mas encanta melhor. Piglins gostam!", 2, 77, MinecraftEdition.BOTH);
        capaceteOuro.adicionarIngrediente("5x Lingote de Ouro");
        armaduras.add(capaceteOuro);

        Armadura peitoralOuro = new Armadura("Peitoral de Ouro", "Peitoral", "Ouro",
            "Baixa durabilidade, alto encantamento", 5, 112, MinecraftEdition.BOTH);
        peitoralOuro.adicionarIngrediente("8x Lingote de Ouro");
        armaduras.add(peitoralOuro);

        Armadura calcasOuro = new Armadura("Calças de Ouro", "Calças", "Ouro",
            "Protege dos Piglins no Nether", 3, 105, MinecraftEdition.BOTH);
        calcasOuro.adicionarIngrediente("7x Lingote de Ouro");
        armaduras.add(calcasOuro);

        Armadura botasOuro = new Armadura("Botas de Ouro", "Botas", "Ouro",
            "Botas frágeis mas encantáveis", 1, 91, MinecraftEdition.BOTH);
        botasOuro.adicionarIngrediente("4x Lingote de Ouro");
        armaduras.add(botasOuro);

        // ==================== ARMADURAS DE DIAMANTE ====================
        
        Armadura capaceteDiamante = new Armadura("Capacete de Diamante", "Capacete", "Diamante",
            "Segunda melhor proteção do jogo", 3, 363, MinecraftEdition.BOTH);
        capaceteDiamante.adicionarIngrediente("5x Diamante");
        armaduras.add(capaceteDiamante);

        Armadura peitoralDiamante = new Armadura("Peitoral de Diamante", "Peitoral", "Diamante",
            "Proteção superior para o tronco", 8, 528, MinecraftEdition.BOTH);
        peitoralDiamante.adicionarIngrediente("8x Diamante");
        armaduras.add(peitoralDiamante);

        Armadura calcasDiamante = new Armadura("Calças de Diamante", "Calças", "Diamante",
            "Excelente proteção das pernas", 6, 495, MinecraftEdition.BOTH);
        calcasDiamante.adicionarIngrediente("7x Diamante");
        armaduras.add(calcasDiamante);

        Armadura botasDiamante = new Armadura("Botas de Diamante", "Botas", "Diamante",
            "Botas de alta durabilidade", 3, 429, MinecraftEdition.BOTH);
        botasDiamante.adicionarIngrediente("4x Diamante");
        armaduras.add(botasDiamante);

        // ==================== ARMADURAS DE NETHERITE ====================
        
        Armadura capaceteNetherite = new Armadura("Capacete de Netherite", "Capacete", "Netherite",
            "Melhor capacete do jogo, imune a lava", 3, 407, MinecraftEdition.BOTH);
        capaceteNetherite.adicionarIngrediente("Capacete de Diamante");
        capaceteNetherite.adicionarIngrediente("1x Lingote de Netherite");
        capaceteNetherite.setMoldeFerraria("Molde de Upgrade de Netherite");
        capaceteNetherite.setReceitaMolde("Encontrado em:\n• Baús de Bastião do Nether\n• Baús de Fortaleza do Nether\n• Raramente em ruínas do Nether\n\nPode ser duplicado com:\n• 7x Diamante + 1x Molde + 1x Netherrack");
        armaduras.add(capaceteNetherite);

        Armadura peitoralNetherite = new Armadura("Peitoral de Netherite", "Peitoral", "Netherite",
            "Melhor peitoral, +1 de resistência a knockback", 8, 592, MinecraftEdition.BOTH);
        peitoralNetherite.adicionarIngrediente("Peitoral de Diamante");
        peitoralNetherite.adicionarIngrediente("1x Lingote de Netherite");
        peitoralNetherite.setMoldeFerraria("Molde de Upgrade de Netherite");
        peitoralNetherite.setReceitaMolde("Encontrado em:\n• Baús de Bastião do Nether\n• Baús de Fortaleza do Nether\n• Raramente em ruínas do Nether\n\nPode ser duplicado com:\n• 7x Diamante + 1x Molde + 1x Netherrack");
        armaduras.add(peitoralNetherite);

        Armadura calcasNetherite = new Armadura("Calças de Netherite", "Calças", "Netherite",
            "Melhores calças, resistência extra", 6, 555, MinecraftEdition.BOTH);
        calcasNetherite.adicionarIngrediente("Calças de Diamante");
        calcasNetherite.adicionarIngrediente("1x Lingote de Netherite");
        calcasNetherite.setMoldeFerraria("Molde de Upgrade de Netherite");
        calcasNetherite.setReceitaMolde("Encontrado em:\n• Baús de Bastião do Nether\n• Baús de Fortaleza do Nether\n• Raramente em ruínas do Nether\n\nPode ser duplicado com:\n• 7x Diamante + 1x Molde + 1x Netherrack");
        armaduras.add(calcasNetherite);

        Armadura botasNetherite = new Armadura("Botas de Netherite", "Botas", "Netherite",
            "Melhores botas, flutuam na lava", 3, 481, MinecraftEdition.BOTH);
        botasNetherite.adicionarIngrediente("Botas de Diamante");
        botasNetherite.adicionarIngrediente("1x Lingote de Netherite");
        botasNetherite.setMoldeFerraria("Molde de Upgrade de Netherite");
        botasNetherite.setReceitaMolde("Encontrado em:\n• Baús de Bastião do Nether\n• Baús de Fortaleza do Nether\n• Raramente em ruínas do Nether\n\nPode ser duplicado com:\n• 7x Diamante + 1x Molde + 1x Netherrack");
        armaduras.add(botasNetherite);

        // ==================== ARMADURA DE TARTARUGA ====================
        
        Armadura capaceteTartaruga = new Armadura("Casco de Tartaruga", "Capacete", "Tartaruga",
            "Permite respirar embaixo d'água por mais tempo", 2, 275, MinecraftEdition.BOTH);
        capaceteTartaruga.adicionarIngrediente("5x Escama de Tartaruga");
        armaduras.add(capaceteTartaruga);
    }

    private void carregarMoldesFerraria() {
        // ==================== MOLDE DE UPGRADE ====================
        
        MoldeFerraria upgradeNetherite = new MoldeFerraria(
            "Melhoria de Netherite",
            "upgrade",
            "Usado para melhorar armaduras e ferramentas de diamante para netherite na Mesa de Ferraria",
            "📍 Bastião em Ruínas:\n  • Baú do tesouro: 100% (1 item)\n  • Baú genérico: 10% (1 item)\n  • Baú da ponte: 10% (1 item)\n  • Baú do estábulo de hoglin: 10% (1 item)\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Netherrack",
            "Na Mesa de Ferraria:\n1. Molde no primeiro slot\n2. Armadura/Ferramenta de Diamante no segundo\n3. Lingote de Netherite no terceiro\n\n✨ Preserva todos os encantamentos!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(upgradeNetherite);

        // ==================== MOLDES DE APARAGEM (TRIM) ====================
        
        MoldeFerraria coastTrim = new MoldeFerraria(
            "Enfeite do Litoral",
            "trim",
            "Padrão decorativo inspirado em ruínas oceânicas e naufrágios",
            "📍 Naufrágio:\n  • Baú do mapa: 16,7% (2 itens)\n  • Baú do tesouro: 16,7% (2 itens)\n  • Baú de suprimentos: 16,7% (2 itens)\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Pedregulho",
            "Na Mesa de Ferraria:\n1. Molde no primeiro slot\n2. Armadura no segundo\n3. Material decorativo (minerais) no terceiro\n\n🎨 Cores variam conforme o material usado!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(coastTrim);

        MoldeFerraria duneTrim = new MoldeFerraria(
            "Enfeite de Dunas",
            "trim",
            "Padrão desértico inspirado nas antigas construções dos templos de areia",
            "📍 Templo do Deserto:\n  • Baú: 14,3% (2 itens)\n  • Baús suspeitos (areia): Comum\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Arenito",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use ouro para efeito desértico!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(duneTrim);

        MoldeFerraria eyeTrim = new MoldeFerraria(
            "Enfeite de Olho",
            "trim",
            "Padrão misterioso inspirado nos Olhos do Ender e nas Fortalezas",
            "📍 Fortaleza (Stronghold):\n  • Baú da biblioteca: 100% (1 item)\n  • Baú do altar: 10% (1 item)\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Pedra do End",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use esmeralda ou aquamarine!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(eyeTrim);

        MoldeFerraria hostTrim = new MoldeFerraria(
            "Enfeite de Hospedeiro",
            "trim",
            "Padrão elegante inspirado nos antigos guardiões das Cidades Ancestrais",
            "📍 Cidade Ancestral (Ancient City):\n  • Baú: 8,3% (1 item)\n  • Raro - explore bem as estruturas!\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Terracota",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use diamante ou netherite!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(hostTrim);

        MoldeFerraria raiserTrim = new MoldeFerraria(
            "Enfeite de Levante",
            "trim",
            "Padrão inspirado nas bandeiras e torres dos Saqueadores",
            "📍 Posto de Saqueadores (Pillager Outpost):\n  • Baú: 25% (2 itens)\n  • Boa chance de encontrar!\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Terracota",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use ferro ou cobre!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(raiserTrim);

        MoldeFerraria ribTrim = new MoldeFerraria(
            "Enfeite de Costelas",
            "trim",
            "Padrão ósseo inspirado nos esqueletos Wither das Fortalezas do Nether",
            "📍 Fortaleza do Nether:\n  • Baú: 6,7% (1 item)\n  • Raro - prepare-se para explorar!\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Netherrack",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use quartzo ou osso!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(ribTrim);

        MoldeFerraria sentryTrim = new MoldeFerraria(
            "Enfeite de Sentinela",
            "trim",
            "Padrão de vigilância inspirado nos guardas dos Postos de Saqueadores",
            "📍 Posto de Saqueadores (Pillager Outpost):\n  • Baú: 25% (2 itens)\n  • Mesma estrutura do Raiser!\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Pedregulho",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use ferro escuro ou lapis!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(sentryTrim);

        MoldeFerraria shaperTrim = new MoldeFerraria(
            "Enfeite de Moldador",
            "trim",
            "Padrão artesanal criado pelos antigos construtores das profundezas",
            "📍 Cidade Ancestral (Ancient City):\n  • Baú: 8,3% (1 item)\n  • Estrutura perigosa - cuidado com o Warden!\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Terracota",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use cobre ou ametista!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(shaperTrim);

        MoldeFerraria silenceTrim = new MoldeFerraria(
            "Enfeite de Silêncio",
            "trim",
            "Padrão mais raro do jogo, encontrado nas profundezas escuras",
            "📍 Cidade Ancestral (Ancient City):\n  • Baú: 1,2% (1 item)\n  • ⚠️ EXTREMAMENTE RARO!\n  • Melhor molde do jogo!\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Pedregulho",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use netherite ou obsidiana!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(silenceTrim);

        MoldeFerraria snoutTrim = new MoldeFerraria(
            "Enfeite de Focinho",
            "trim",
            "Padrão Piglin inspirado nas máscaras dos habitantes do Nether",
            "📍 Bastião em Ruínas:\n  • Baú do tesouro: 8,3% (1 item)\n  • Baú genérico: 8,3% (1 item)\n  • Baú da ponte: 8,3% (1 item)\n  • Baú do estábulo de hoglin: 8,3% (1 item)\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Blackstone",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use ouro (Piglins adoram!)\n⚠️ Vista ouro ao explorar o Bastião!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(snoutTrim);

        MoldeFerraria spireTrim = new MoldeFerraria(
            "Enfeite de Espiral",
            "trim",
            "Padrão pontiagudo inspirado nas torres das Cidades do End",
            "📍 Cidade do End (End City):\n  • Baú: 6,7% (1 item)\n  • Encontrado após derrotar o Ender Dragon\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Purpur",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use ametista ou purpur!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(spireTrim);

        MoldeFerraria tideTrim = new MoldeFerraria(
            "Enfeite de Marés",
            "trim",
            "Padrão aquático inspirado nos Guardiões dos Monumentos Oceânicos",
            "📍 Monumento Oceânico (Ocean Monument):\n  • Baú: 6,7% (1 item)\n  • Protegido por Elder Guardians\n  • Traga poções de respiração aquática!\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Prismarinho",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use prismarinho ou diamante!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(tideTrim);

        MoldeFerraria vexTrim = new MoldeFerraria(
            "Enfeite de Vex",
            "trim",
            "Padrão mágico inspirado nas criaturas invocadas pelos Evokers",
            "📍 Mansão da Floresta (Woodland Mansion):\n  • Baú: 50% (1 item)\n  • Estrutura MUITO rara de encontrar!\n  • Use um mapa de explorador\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Pedregulho",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use lapis ou diamante!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(vexTrim);

        MoldeFerraria wardTrim = new MoldeFerraria(
            "Enfeite das Profundezas",
            "trim",
            "Padrão protetor usado pelos antigos guardiões das profundezas",
            "📍 Cidade Ancestral (Ancient City):\n  • Baú: 5% (1 item)\n  • Raro mas vale a pena!\n  • Evite despertar o Warden\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Pedregulho",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use netherite ou sculk!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(wardTrim);

        MoldeFerraria wayfinderTrim = new MoldeFerraria(
            "Enfeite de Navegador",
            "trim",
            "Padrão de exploração inspirado nos antigos viajantes e suas ruínas",
            "📍 Ruínas de Trilha (Trail Ruins):\n  • Baú suspeito: Comum\n  • Escave com pincel!\n  • Estrutura adicionada em 1.20\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Terracota",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use cobre ou tijolo!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(wayfinderTrim);

        MoldeFerraria wildTrim = new MoldeFerraria(
            "Enfeite da Selva",
            "trim",
            "Padrão natural inspirado na vegetação densa e misteriosa das selvas",
            "📍 Templo da Selva (Jungle Temple):\n  • Baú: 33,3% (2 itens)\n  • Boa chance de encontrar!\n  • Cuidado com as armadilhas\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Blocos de Musgo",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use esmeralda ou cobre!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(wildTrim);

        // ==================== MOLDES EXCLUSIVOS (1.21+) ====================
        
        MoldeFerraria boltTrim = new MoldeFerraria(
            "Enfeite de Parafuso",
            "trim",
            "Padrão mecânico inspirado nos artefatos elétricos das estruturas de teste",
            "📍 Trial Chambers (Câmaras de Teste):\n  • Baú de recompensa: Comum\n  • Novo conteúdo 1.21+\n  • Complete os desafios para ganhar!\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Cobre",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use cobre ou ferro oxidado!\n⚡ Padrão de aspecto tecnológico!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(boltTrim);

        MoldeFerraria flowTrim = new MoldeFerraria(
            "Enfeite de Fluxo",
            "trim",
            "Padrão fluído inspirado nas correntes de vento e movimento",
            "📍 Trial Chambers (Câmaras de Teste):\n  • Baú de recompensa: Comum\n  • Novo conteúdo 1.21+\n  • Vaults ominosos têm melhor chance\n\n✨ Duplicação: 7x Diamante + 1x Molde + 1x Brisa (Breeze Rod)",
            "Na Mesa de Ferraria com armadura e material decorativo\n\n🎨 Recomendado: Use ametista ou quartzo!\n💨 Padrão de aspecto aerodinâmico!",
            MinecraftEdition.BOTH
        );
        moldesFerraria.add(flowTrim);
    }

    public List<MoldeFerraria> getMoldesFerraria() {
        return new ArrayList<>(moldesFerraria);
    }

    public List<MoldeFerraria> getMoldesUpgrade() {
        return moldesFerraria.stream()
                .filter(MoldeFerraria::isUpgrade)
                .collect(Collectors.toList());
    }

    public List<MoldeFerraria> getMoldesTrim() {
        return moldesFerraria.stream()
                .filter(MoldeFerraria::isTrim)
                .collect(Collectors.toList());
    }
}

