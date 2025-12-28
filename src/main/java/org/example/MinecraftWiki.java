package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MinecraftWiki {
    private final List<Item> itens;
    private final List<Pocao> pocoes;
    private final List<Encantamento> encantamentos;

    public MinecraftWiki() {
        this.itens = new ArrayList<>();
        this.pocoes = new ArrayList<>();
        this.encantamentos = new ArrayList<>();
        carregarDados();
    }

    private void carregarDados() {
        carregarItens();
        carregarPocoes();
        carregarEncantamentos();
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
}

