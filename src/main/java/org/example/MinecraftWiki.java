package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MinecraftWiki {
    private final List<Item> itens;
    private final List<Pocao> pocoes;
    private final List<Encantamento> encantamentos;
    private final List<Armadura> armaduras;
    private final List<MoldeFerraria> moldesFerraria;

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

