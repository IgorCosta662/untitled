package org.example;

/**
 * EXEMPLO: Como adicionar imagens aos itens
 * 
 * Este é um exemplo de como configurar imagens para itens.
 * Copie este código e adicione no MinecraftWiki.java dentro do método inicializarItens()
 */
public class ExemploImagensItens {
    
    public static void exemploConfigurarImagens(MinecraftWiki wiki) {
        // EXEMPLO 1: Picareta de Ferro com imagens
        Item picareta = new Item(
            "Picareta de Ferro",
            "Ferramenta essencial para minerar pedras e minérios rapidamente. Mais durável que madeira e pedra.",
            MinecraftEdition.AMBAS,
            "Ferramenta"
        );
        picareta.adicionarIngrediente("3x Lingote de Ferro");
        picareta.adicionarIngrediente("2x Graveto");
        picareta.setPadraoCrafting(
            "╔═══════╗\n" +
            "║ F F F ║\n" +
            "║ - G - ║\n" +
            "║ - G - ║\n" +
            "╚═══════╝\n" +
            "F = Lingote de Ferro\n" +
            "G = Graveto"
        );
        // Configurar imagens
        picareta.setImagemItem("src/main/resources/images/items/picareta_ferro.png");
        picareta.setImagemCrafting("src/main/resources/images/crafting/picareta_ferro_craft.png");
        wiki.adicionarItem(picareta);
        
        // EXEMPLO 2: Diamante com imagem
        Item diamante = new Item(
            "Diamante",
            "Gema preciosa encontrada nas camadas mais profundas. Usado para criar os melhores equipamentos.",
            MinecraftEdition.AMBAS,
            "Gema"
        );
        diamante.setImagemItem("src/main/resources/images/items/diamante.png");
        // Diamante não tem crafting, apenas a imagem do item
        wiki.adicionarItem(diamante);
        
        // EXEMPLO 3: Espada de Diamante
        Item espadaDiamante = new Item(
            "Espada de Diamante",
            "Arma corpo a corpo com alto dano. Uma das melhores espadas do jogo.",
            MinecraftEdition.AMBAS,
            "Arma"
        );
        espadaDiamante.adicionarIngrediente("2x Diamante");
        espadaDiamante.adicionarIngrediente("1x Graveto");
        espadaDiamante.setPadraoCrafting(
            "╔═══════╗\n" +
            "║ - D - ║\n" +
            "║ - D - ║\n" +
            "║ - G - ║\n" +
            "╚═══════╝\n" +
            "D = Diamante\n" +
            "G = Graveto"
        );
        espadaDiamante.setImagemItem("src/main/resources/images/items/espada_diamante.png");
        espadaDiamante.setImagemCrafting("src/main/resources/images/crafting/espada_diamante_craft.png");
        wiki.adicionarItem(espadaDiamante);
        
        // EXEMPLO 4: Tocha
        Item tocha = new Item(
            "Tocha",
            "Fonte de luz portátil. Essencial para iluminar cavernas e construções.",
            MinecraftEdition.AMBAS,
            "Utilitário"
        );
        tocha.adicionarIngrediente("1x Carvão ou Carvão Vegetal");
        tocha.adicionarIngrediente("1x Graveto");
        tocha.setPadraoCrafting(
            "╔═══════╗\n" +
            "║ - C - ║\n" +
            "║ - G - ║\n" +
            "║ - - - ║\n" +
            "╚═══════╝\n" +
            "C = Carvão\n" +
            "G = Graveto\n" +
            "Resultado: 4x Tocha"
        );
        tocha.setImagemItem("src/main/resources/images/items/tocha.png");
        tocha.setImagemCrafting("src/main/resources/images/crafting/tocha_craft.png");
        wiki.adicionarItem(tocha);
    }
    
    /*
     * INSTRUÇÕES:
     * 
     * 1. Baixe as imagens dos itens da Minecraft Wiki: https://minecraft.wiki/
     * 2. Salve as imagens nas pastas:
     *    - Itens: src/main/resources/images/items/
     *    - Crafting: src/main/resources/images/crafting/
     * 
     * 3. Nomeie os arquivos de forma descritiva:
     *    - picareta_ferro.png
     *    - espada_diamante.png
     *    - diamante.png
     *    - etc.
     * 
     * 4. Para cada item em MinecraftWiki.java, adicione as linhas:
     *    item.setImagemItem("src/main/resources/images/items/nome_do_item.png");
     *    item.setImagemCrafting("src/main/resources/images/crafting/nome_do_item_craft.png");
     * 
     * 5. Se um item não tem receita de crafting, não precisa chamar setImagemCrafting()
     * 
     * 6. Execute a aplicação e veja as imagens aparecerem na aba Itens!
     * 
     * DICAS:
     * - Use imagens PNG para melhor qualidade
     * - Tamanho recomendado: 64x64 para itens, 150x150 para crafting
     * - As imagens serão redimensionadas automaticamente
     */
}
