package org.example.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Gerenciador de imagens do Minecraft
 * Baixa e cacheia imagens de itens da Minecraft Wiki
 */
public class ImageManager {
    private static final String CACHE_DIR = "cache/images";
    private static final String CACHE_CRAFTING_DIR = "cache/images/crafting";
    private static final String WIKI_IMAGE_BASE = "https://minecraft.wiki/images/";
    private static final Map<String, ImageIcon> imageCache = new HashMap<>();
    private static final Map<String, ImageIcon> craftingCache = new HashMap<>();
    private static boolean offlineMode = false;
    
    static {
        // Criar diretórios de cache
        new File(CACHE_DIR).mkdirs();
        new File(CACHE_CRAFTING_DIR).mkdirs();
    }
    
    /**
     * Obtém um ícone de item do Minecraft
     * @param itemName Nome do item
     * @param size Tamanho desejado do ícone
     * @return ImageIcon ou null se não encontrado
     */
    public static ImageIcon getItemIcon(String itemName, int size) {
        String cacheKey = itemName + "_" + size;
        
        // Verificar cache em memória
        if (imageCache.containsKey(cacheKey)) {
            return imageCache.get(cacheKey);
        }
        
        // Tentar carregar da Minecraft Wiki
        ImageIcon icon = loadFromWiki(itemName, size);
        
        if (icon == null) {
            // Fallback: criar ícone colorido com texto
            icon = createFallbackIcon(itemName, size);
        }
        
        imageCache.put(cacheKey, icon);
        return icon;
    }
    
    /**
     * Mapeamento de nomes para URLs de imagens da wiki
     */
    private static String getWikiImageName(String itemName) {
        Map<String, String> nameMap = new HashMap<>();
        
        // Menu principal
        nameMap.put("ITEMS", "Invicon_Diamond_Pickaxe.png");
        nameMap.put("ARMOR", "Invicon_Diamond_Chestplate.png");
        nameMap.put("ENCHANTMENTS", "Invicon_Enchanted_Book.png");
        nameMap.put("BREWING", "Invicon_Potion_of_Healing.png");
        nameMap.put("CRAFTING", "Invicon_Crafting_Table.png");
        nameMap.put("STATISTICS", "Invicon_Knowledge_Book.png");
        nameMap.put("API_TEST", "Invicon_Command_Block.png");
        nameMap.put("ABOUT", "Invicon_Book.png");
        nameMap.put("EXIT", "Invicon_Barrier.png");
        
        // Ferramentas
        nameMap.put("PICKAXE", "Invicon_Diamond_Pickaxe.png");
        nameMap.put("AXE", "Invicon_Diamond_Axe.png");
        nameMap.put("SHOVEL", "Invicon_Diamond_Shovel.png");
        nameMap.put("HOE", "Invicon_Diamond_Hoe.png");
        
        // Armas
        nameMap.put("SWORD", "Invicon_Diamond_Sword.png");
        nameMap.put("BOW", "Invicon_Bow.png");
        nameMap.put("CROSSBOW", "Invicon_Crossbow.png");
        nameMap.put("TRIDENT", "Invicon_Trident.png");
        
        // Armaduras
        nameMap.put("HELMET", "Invicon_Diamond_Helmet.png");
        nameMap.put("CHESTPLATE", "Invicon_Diamond_Chestplate.png");
        nameMap.put("LEGGINGS", "Invicon_Diamond_Leggings.png");
        nameMap.put("BOOTS", "Invicon_Diamond_Boots.png");
        nameMap.put("SHIELD", "Invicon_Shield.png");
        
        // Minérios e recursos
        nameMap.put("DIAMOND", "Invicon_Diamond.png");
        nameMap.put("EMERALD", "Invicon_Emerald.png");
        nameMap.put("GOLD_INGOT", "Invicon_Gold_Ingot.png");
        nameMap.put("IRON_INGOT", "Invicon_Iron_Ingot.png");
        nameMap.put("NETHERITE", "Invicon_Netherite_Ingot.png");
        nameMap.put("COAL", "Invicon_Coal.png");
        nameMap.put("REDSTONE", "Invicon_Redstone_Dust.png");
        
        // Poções
        nameMap.put("POTION_HEALING", "Invicon_Potion_of_Healing.png");
        nameMap.put("POTION_STRENGTH", "Invicon_Potion_of_Strength.png");
        nameMap.put("POTION_SPEED", "Invicon_Potion_of_Swiftness.png");
        nameMap.put("BREWING_STAND", "Invicon_Brewing_Stand.png");
        
        // Encantamentos
        nameMap.put("ENCHANTED_BOOK", "Invicon_Enchanted_Book.png");
        nameMap.put("ENCHANTING_TABLE", "Invicon_Enchanting_Table.png");
        nameMap.put("ANVIL", "Invicon_Anvil.png");
        
        // Crafting
        nameMap.put("CRAFTING_TABLE", "Invicon_Crafting_Table.png");
        nameMap.put("FURNACE", "Invicon_Furnace.png");
        nameMap.put("BLAST_FURNACE", "Invicon_Blast_Furnace.png");
        nameMap.put("SMITHING_TABLE", "Invicon_Smithing_Table.png");
        
        // Blocos de Construção
        nameMap.put("STONE", "Invicon_Stone.png");
        nameMap.put("COBBLESTONE", "Invicon_Cobblestone.png");
        nameMap.put("STONE_BRICKS", "Invicon_Stone_Bricks.png");
        nameMap.put("OAK_PLANKS", "Invicon_Oak_Planks.png");
        nameMap.put("GLASS", "Invicon_Glass.png");
        
        // Blocos Naturais
        nameMap.put("DIRT", "Invicon_Dirt.png");
        nameMap.put("GRASS_BLOCK", "Invicon_Grass_Block.png");
        nameMap.put("SAND", "Invicon_Sand.png");
        nameMap.put("GRAVEL", "Invicon_Gravel.png");
        
        // Blocos Decorativos
        nameMap.put("WHITE_WOOL", "Invicon_White_Wool.png");
        nameMap.put("TERRACOTTA", "Invicon_Terracotta.png");
        nameMap.put("WHITE_CONCRETE", "Invicon_White_Concrete.png");
        
        // Blocos Funcionais
        nameMap.put("CHEST", "Invicon_Chest.png");
        nameMap.put("BARREL", "Invicon_Barrel.png");
        
        // Outros
        nameMap.put("BOOK", "Invicon_Book.png");
        nameMap.put("RECIPE", "Invicon_Knowledge_Book.png");
        nameMap.put("DEFENSE", "Invicon_Shield.png");
        nameMap.put("ATTACK", "Invicon_Diamond_Sword.png");
        
        return nameMap.getOrDefault(itemName, null);
    }
    
    /**
     * Carrega imagem da Minecraft Wiki
     */
    private static ImageIcon loadFromWiki(String itemName, int size) {
        String wikiImageName = getWikiImageName(itemName);
        if (wikiImageName == null) return null;
        
        try {
            // Verificar se existe em cache local
            File cacheFile = new File(CACHE_DIR, wikiImageName);
            BufferedImage img;
            
            if (cacheFile.exists()) {
                // Carregar do cache
                img = ImageIO.read(cacheFile);
            } else if (offlineMode) {
                // Modo offline - não tentar baixar
                return null;
            } else {
                // Baixar da wiki
                String imageUrl = WIKI_IMAGE_BASE + wikiImageName;
                URL url = URI.create(imageUrl).toURL();
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("User-Agent", "MinecraftWikiApp/2.0");
                conn.setConnectTimeout(3000);
                conn.setReadTimeout(5000);
                
                try (InputStream in = conn.getInputStream()) {
                    img = ImageIO.read(in);
                    
                    // Salvar em cache
                    if (img != null) {
                        ImageIO.write(img, "png", cacheFile);
                    }
                }
            }
            
            if (img != null) {
                // Redimensionar mantendo proporção
                Image scaledImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImg);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem " + wikiImageName + ": " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Cria ícone fallback com cor e iniciais
     */
    private static ImageIcon createFallbackIcon(String itemName, int size) {
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        
        // Ativar antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        // Cores por categoria
        Color bgColor = getColorForItem(itemName);
        
        // Desenhar fundo
        g2d.setColor(bgColor);
        g2d.fillRoundRect(0, 0, size, size, size/4, size/4);
        
        // Desenhar borda
        g2d.setColor(bgColor.brighter());
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(1, 1, size-2, size-2, size/4, size/4);
        
        // Desenhar iniciais do item
        String text = getInitialsForItem(itemName);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, size / 3));
        
        FontMetrics fm = g2d.getFontMetrics();
        int x = (size - fm.stringWidth(text)) / 2;
        int y = ((size - fm.getHeight()) / 2) + fm.getAscent();
        
        g2d.drawString(text, x, y);
        g2d.dispose();
        
        return new ImageIcon(img);
    }
    
    /**
     * Retorna iniciais para o item (usado no fallback)
     */
    private static String getInitialsForItem(String itemName) {
        return switch (itemName) {
            // Menu principal
            case "ITEMS" -> "IT";
            case "ARMOR" -> "AR";
            case "ENCHANTMENTS" -> "EN";
            case "BREWING" -> "BR";
            case "CRAFTING" -> "CR";
            case "STATISTICS" -> "ST";
            case "API_TEST" -> "API";
            case "ABOUT" -> "AB";
            case "EXIT" -> "EX";
            
            // Ferramentas
            case "PICKAXE" -> "PK";
            case "AXE" -> "AX";
            case "SHOVEL" -> "SH";
            case "HOE" -> "HO";
            
            // Armas
            case "SWORD" -> "SW";
            case "BOW" -> "BO";
            case "CROSSBOW" -> "CB";
            case "TRIDENT" -> "TR";
            case "ATTACK" -> "AT";
            
            // Armaduras
            case "HELMET" -> "HE";
            case "CHESTPLATE" -> "CP";
            case "LEGGINGS" -> "LG";
            case "BOOTS" -> "BT";
            case "SHIELD" -> "SH";
            case "DEFENSE" -> "DF";
            
            // Minérios
            case "DIAMOND" -> "DI";
            case "EMERALD" -> "EM";
            case "GOLD_INGOT" -> "AU";
            case "IRON_INGOT" -> "FE";
            case "NETHERITE" -> "NT";
            case "COAL" -> "CO";
            case "REDSTONE" -> "RS";
            
            // Poções
            case "POTION_HEALING" -> "HP";
            case "POTION_STRENGTH" -> "ST";
            case "POTION_SPEED" -> "SP";
            case "BREWING_STAND" -> "BS";
            
            // Encantamentos
            case "ENCHANTED_BOOK" -> "EB";
            case "ENCHANTING_TABLE" -> "ET";
            case "ANVIL" -> "AN";
            
            // Crafting
            case "CRAFTING_TABLE" -> "CT";
            case "FURNACE" -> "FU";
            case "BLAST_FURNACE" -> "BF";
            case "SMITHING_TABLE" -> "SM";
            
            // Livros
            case "BOOK" -> "BK";
            case "RECIPE" -> "RC";
            
            default -> {
                // Para outros itens, pegar as primeiras 2 letras
                String name = itemName.replace("_", "");
                yield name.length() >= 2 ? name.substring(0, 2).toUpperCase() : name.toUpperCase();
            }
        };
    }
    
    /**
     * Retorna cor baseada no tipo de item
     */
    private static Color getColorForItem(String itemName) {
        return switch (itemName) {
            // Menu principal
            case "ITEMS" -> new Color(85, 170, 255);
            case "ARMOR" -> new Color(192, 192, 192);
            case "ENCHANTMENTS" -> new Color(170, 85, 255);
            case "BREWING" -> new Color(170, 85, 255);
            case "CRAFTING" -> new Color(139, 90, 43);
            case "STATISTICS" -> new Color(255, 170, 0);
            case "API_TEST" -> new Color(0, 150, 200);
            case "ABOUT" -> new Color(85, 170, 255);
            case "EXIT" -> new Color(255, 85, 85);
            
            // Ferramentas - Cinza/Azul
            case "PICKAXE", "AXE", "SHOVEL", "HOE" -> new Color(192, 192, 192);
            
            // Armas - Vermelho
            case "SWORD", "BOW", "CROSSBOW", "TRIDENT", "ATTACK" -> new Color(255, 85, 85);
            
            // Armaduras - Azul
            case "HELMET", "CHESTPLATE", "LEGGINGS", "BOOTS", "SHIELD", "DEFENSE" -> new Color(100, 149, 237);
            
            // Minérios valiosos - Ciano/Azul
            case "DIAMOND" -> new Color(0, 255, 255);
            case "EMERALD" -> new Color(0, 255, 127);
            case "NETHERITE" -> new Color(68, 58, 59);
            
            // Metais - Dourado/Cinza
            case "GOLD_INGOT" -> new Color(255, 215, 0);
            case "IRON_INGOT" -> new Color(192, 192, 192);
            case "COAL" -> new Color(50, 50, 50);
            case "REDSTONE" -> new Color(255, 0, 0);
            
            // Poções - Roxo/Rosa
            case "POTION_HEALING", "POTION_STRENGTH", "POTION_SPEED", "BREWING_STAND" -> new Color(170, 85, 255);
            
            // Encantamentos - Roxo
            case "ENCHANTED_BOOK", "ENCHANTING_TABLE", "ANVIL" -> new Color(170, 85, 255);
            
            // Crafting - Marrom
            case "CRAFTING_TABLE", "FURNACE", "BLAST_FURNACE", "SMITHING_TABLE" -> new Color(139, 90, 43);
            
            // Livros - Marrom
            case "BOOK", "RECIPE" -> new Color(139, 90, 43);
            
            default -> new Color(100, 100, 100);
        };
    }
    
    /**
     * Pré-carrega imagens em background para modo offline
     */
    public static void preloadImages() {
        new Thread(() -> {
            System.out.println("Iniciando pré-carregamento de imagens para modo offline...");
            
            // Imagens do menu principal
            String[] menuItems = {"ITEMS", "ARMOR", "ENCHANTMENTS", "BREWING", 
                                "CRAFTING", "STATISTICS", "API_TEST", "ABOUT", "EXIT"};
            
            // Ferramentas
            String[] tools = {"PICKAXE", "AXE", "SHOVEL", "HOE"};
            
            // Armas
            String[] weapons = {"SWORD", "BOW", "CROSSBOW", "TRIDENT", "ATTACK"};
            
            // Armaduras
            String[] armor = {"HELMET", "CHESTPLATE", "LEGGINGS", "BOOTS", "SHIELD", "DEFENSE"};
            
            // Recursos
            String[] resources = {"DIAMOND", "EMERALD", "GOLD_INGOT", "IRON_INGOT", 
                                "NETHERITE", "COAL", "REDSTONE"};
            
            // Poções
            String[] potions = {"POTION_HEALING", "POTION_STRENGTH", "POTION_SPEED", "BREWING_STAND"};
            
            // Encantamentos
            String[] enchanting = {"ENCHANTED_BOOK", "ENCHANTING_TABLE", "ANVIL"};
            
            // Crafting
            String[] crafting = {"CRAFTING_TABLE", "FURNACE", "BLAST_FURNACE", "SMITHING_TABLE"};
            
            // Blocos
            String[] blocks = {"STONE", "COBBLESTONE", "STONE_BRICKS", "OAK_PLANKS", "GLASS",
                             "DIRT", "GRASS_BLOCK", "SAND", "GRAVEL", 
                             "WHITE_WOOL", "TERRACOTTA", "WHITE_CONCRETE",
                             "CHEST", "BARREL"};
            
            // Outros
            String[] others = {"BOOK", "RECIPE"};
            
            int total = 0;
            int downloaded = 0;
            
            // Carregar todas as imagens
            String[][] allCategories = {menuItems, tools, weapons, armor, resources, 
                                       potions, enchanting, crafting, blocks, others};
            
            for (String[] category : allCategories) {
                for (String item : category) {
                    total++;
                    ImageIcon icon = getItemIcon(item, 48);
                    if (icon != null && icon.getIconWidth() > 0) {
                        downloaded++;
                    }
                    
                    // Também carregar em tamanhos diferentes
                    getItemIcon(item, 16);
                    getItemIcon(item, 24);
                    getItemIcon(item, 32);
                }
            }
            
            System.out.println("Pré-carregamento concluído: " + downloaded + "/" + total + " imagens disponíveis");
            System.out.println("Cache local criado em: " + CACHE_DIR);
            System.out.println("Modo offline disponível!");
        }).start();
    }
    
    /**
     * Baixa uma imagem de crafting da wiki
     * @param itemName Nome do item
     * @return Caminho local da imagem ou null
     */
    public static String downloadCraftingImage(String itemName) {
        String craftingFileName = "Crafting_" + itemName.replace(" ", "_") + ".png";
        File cacheFile = new File(CACHE_CRAFTING_DIR, craftingFileName);
        
        // Se já existe em cache, retornar o caminho
        if (cacheFile.exists()) {
            return cacheFile.getAbsolutePath();
        }
        
        // Tentar baixar da wiki
        try {
            String[] possibleUrls = {
                WIKI_IMAGE_BASE + craftingFileName,
                WIKI_IMAGE_BASE + "Grid_layout_Arrow_(small).png" // Seta de crafting
            };
            
            for (String urlString : possibleUrls) {
                try {
                    URL url = URI.create(urlString).toURL();
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setRequestProperty("User-Agent", "MinecraftWiki/1.0");
                    
                    if (connection.getResponseCode() == 200) {
                        BufferedImage img = ImageIO.read(connection.getInputStream());
                        if (img != null) {
                            ImageIO.write(img, "PNG", cacheFile);
                            System.out.println("Imagem de crafting baixada: " + craftingFileName);
                            return cacheFile.getAbsolutePath();
                        }
                    }
                } catch (Exception e) {
                    // Tentar próxima URL
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao baixar imagem de crafting: " + craftingFileName);
        }
        
        return null;
    }
    
    /**
     * Obtém uma imagem de crafting do cache
     * @param itemName Nome do item
     * @param width Largura desejada
     * @param height Altura desejada
     * @return ImageIcon ou null
     */
    public static ImageIcon getCraftingIcon(String itemName, int width, int height) {
        String cacheKey = "crafting_" + itemName + "_" + width + "x" + height;
        
        if (craftingCache.containsKey(cacheKey)) {
            return craftingCache.get(cacheKey);
        }
        
        String craftingFileName = "Crafting_" + itemName.replace(" ", "_") + ".png";
        File cacheFile = new File(CACHE_CRAFTING_DIR, craftingFileName);
        
        if (cacheFile.exists()) {
            try {
                BufferedImage img = ImageIO.read(cacheFile);
                Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaled);
                craftingCache.put(cacheKey, icon);
                return icon;
            } catch (Exception e) {
                System.err.println("Erro ao carregar imagem de crafting: " + craftingFileName);
            }
        }
        
        return null;
    }
    
    /**
     * Define o modo offline (não tenta baixar novas imagens)
     */
    public static void setOfflineMode(boolean offline) {
        offlineMode = offline;
        if (offline) {
            System.out.println("🔌 Modo offline ativado - usando apenas cache local");
        }
    }
    
    /**
     * Verifica se está em modo offline
     */
    public static boolean isOfflineMode() {
        return offlineMode;
    }
    
    /**
     * Salva automaticamente as imagens de um item ao adicioná-lo
     * @param item Item do Minecraft
     */
    public static void autoSaveItemImages(org.example.MinecraftWiki.Item item) {
        if (item == null) return;
        
        String itemName = item.getNome();
        System.out.println("Salvando imagens para: " + itemName);
        
        // Baixar imagem do item em vários tamanhos
        getItemIcon(itemName.toUpperCase().replace(" ", "_"), 16);
        getItemIcon(itemName.toUpperCase().replace(" ", "_"), 24);
        getItemIcon(itemName.toUpperCase().replace(" ", "_"), 32);
        getItemIcon(itemName.toUpperCase().replace(" ", "_"), 48);
        
        // Baixar imagem de crafting se houver receita
        if (item.getPadraoCrafting() != null && !item.getPadraoCrafting().isEmpty()) {
            String craftingPath = downloadCraftingImage(itemName);
            if (craftingPath != null) {
                item.setImagemCrafting(craftingPath);
            }
        }
        
        // Salvar caminho da imagem do item
        String itemIconName = itemName.toUpperCase().replace(" ", "_");
        String imagePath = CACHE_DIR + "/Invicon_" + itemIconName + ".png";
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            item.setImagemItem(imageFile.getAbsolutePath());
        }
    }
    
    /**
     * Obtém estatísticas do cache
     */
    public static String getCacheStats() {
        File cacheDir = new File(CACHE_DIR);
        File craftingDir = new File(CACHE_CRAFTING_DIR);
        
        int itemImages = cacheDir.listFiles() != null ? cacheDir.listFiles().length : 0;
        int craftingImages = craftingDir.listFiles() != null ? craftingDir.listFiles().length : 0;
        
        return String.format("📊 Cache: %d imagens de itens, %d imagens de crafting", 
                           itemImages, craftingImages);
    }
    
    /**
     * Cria um JLabel com ícone e texto
     * @param itemName Nome do item para buscar ícone
     * @param text Texto do label
     * @param iconSize Tamanho do ícone
     * @return JLabel configurado com ícone e texto
     */
    public static javax.swing.JLabel createIconLabel(String itemName, String text, int iconSize) {
        ImageIcon icon = getItemIcon(itemName, iconSize);
        javax.swing.JLabel label = new javax.swing.JLabel(text, icon, javax.swing.SwingConstants.LEFT);
        label.setIconTextGap(8);
        return label;
    }
    
    /**
     * Atualiza um JLabel existente com ícone
     * @param label Label a ser atualizado
     * @param itemName Nome do item para buscar ícone
     * @param iconSize Tamanho do ícone
     */
    public static void setLabelIcon(javax.swing.JLabel label, String itemName, int iconSize) {
        ImageIcon icon = getItemIcon(itemName, iconSize);
        label.setIcon(icon);
        label.setIconTextGap(8);
    }
    
    /**
     * Limpa o cache de imagens
     */
    public static void clearCache() {
        imageCache.clear();
        File cacheDir = new File(CACHE_DIR);
        File[] files = cacheDir.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }
    
    /**
     * Pré-carrega todas as imagens disponíveis em uma thread separada
     */
    public static void preloadAllImages() {
        new Thread(() -> {
            System.out.println("🔄 Iniciando pré-carregamento de imagens...");
            long startTime = System.currentTimeMillis();
            int loaded = 0;
            int craftingLoaded = 0;
            
            // Lista de todas as imagens disponíveis
            String[] allImages = {
                // Menu principal
                "ITEMS", "ARMOR", "ENCHANTMENTS", "BREWING", "CRAFTING", 
                "STATISTICS", "API_TEST", "ABOUT", "EXIT",
                
                // Ferramentas
                "PICKAXE", "AXE", "SHOVEL", "HOE", "DIAMOND_PICKAXE", "DIAMOND_AXE",
                "DIAMOND_SHOVEL", "DIAMOND_HOE", "IRON_PICKAXE", "IRON_AXE",
                "IRON_SHOVEL", "IRON_HOE", "STONE_PICKAXE", "STONE_AXE",
                "STONE_SHOVEL", "STONE_HOE", "WOODEN_PICKAXE", "WOODEN_AXE",
                "WOODEN_SHOVEL", "WOODEN_HOE", "GOLDEN_PICKAXE", "GOLDEN_AXE",
                "GOLDEN_SHOVEL", "GOLDEN_HOE", "NETHERITE_PICKAXE", "NETHERITE_AXE",
                "NETHERITE_SHOVEL", "NETHERITE_HOE",
                
                // Armas
                "SWORD", "BOW", "CROSSBOW", "TRIDENT", "DIAMOND_SWORD", "IRON_SWORD",
                "STONE_SWORD", "WOODEN_SWORD", "GOLDEN_SWORD", "NETHERITE_SWORD",
                
                // Armaduras
                "HELMET", "CHESTPLATE", "LEGGINGS", "BOOTS", "SHIELD",
                "DIAMOND_HELMET", "DIAMOND_CHESTPLATE", "DIAMOND_LEGGINGS", "DIAMOND_BOOTS",
                "IRON_HELMET", "IRON_CHESTPLATE", "IRON_LEGGINGS", "IRON_BOOTS",
                "GOLDEN_HELMET", "GOLDEN_CHESTPLATE", "GOLDEN_LEGGINGS", "GOLDEN_BOOTS",
                "LEATHER_HELMET", "LEATHER_CHESTPLATE", "LEATHER_LEGGINGS", "LEATHER_BOOTS",
                "CHAINMAIL_HELMET", "CHAINMAIL_CHESTPLATE", "CHAINMAIL_LEGGINGS", "CHAINMAIL_BOOTS",
                "NETHERITE_HELMET", "NETHERITE_CHESTPLATE", "NETHERITE_LEGGINGS", "NETHERITE_BOOTS",
                "TURTLE_HELMET", "ELYTRA",
                
                // Encantamentos e poções
                "ENCHANTED_BOOK", "BOOK", "BOTTLE", "GLASS_BOTTLE", "EXPERIENCE_BOTTLE",
                "POTION", "SPLASH_POTION", "LINGERING_POTION",
                "POTION_HEALING", "POTION_STRENGTH", "POTION_SWIFTNESS", "POTION_REGENERATION",
                "POTION_FIRE_RESISTANCE", "POTION_POISON", "POTION_WEAKNESS", "POTION_SLOWNESS",
                "POTION_HARMING", "POTION_NIGHT_VISION", "POTION_INVISIBILITY", "POTION_LEAPING",
                "POTION_WATER_BREATHING", "POTION_SLOW_FALLING", "POTION_LUCK", "POTION_TURTLE_MASTER",
                
                // Ingredientes de poção
                "NETHER_WART", "GLOWSTONE_DUST", "REDSTONE", "GUNPOWDER", "DRAGON_BREATH",
                "FERMENTED_SPIDER_EYE", "GLISTERING_MELON_SLICE", "BLAZE_POWDER", "SUGAR",
                "GHAST_TEAR", "MAGMA_CREAM", "SPIDER_EYE", "GOLDEN_CARROT", "PUFFERFISH",
                "PHANTOM_MEMBRANE", "RABBIT_FOOT", "TURTLE_SHELL",
                
                // Blocos comuns
                "GRASS_BLOCK", "DIRT", "STONE", "COBBLESTONE", "BEDROCK", "SAND", "GRAVEL",
                "OAK_LOG", "OAK_PLANKS", "GLASS", "OBSIDIAN", "NETHERRACK", "END_STONE",
                "CRAFTING_TABLE", "FURNACE", "CHEST", "ANVIL", "ENCHANTING_TABLE",
                "BREWING_STAND", "BEACON", "ENDER_CHEST", "SHULKER_BOX",
                
                // Minérios
                "COAL_ORE", "IRON_ORE", "GOLD_ORE", "DIAMOND_ORE", "EMERALD_ORE",
                "LAPIS_ORE", "REDSTONE_ORE", "COPPER_ORE", "ANCIENT_DEBRIS",
                "COAL", "IRON_INGOT", "GOLD_INGOT", "DIAMOND", "EMERALD",
                "LAPIS_LAZULI", "COPPER_INGOT", "NETHERITE_INGOT",
                
                // Alimentos
                "APPLE", "GOLDEN_APPLE", "BREAD", "COOKED_BEEF", "COOKED_PORKCHOP",
                "COOKED_CHICKEN", "COOKED_MUTTON", "COOKED_SALMON", "COOKED_COD",
                "COOKIE", "CAKE", "PUMPKIN_PIE", "GOLDEN_CARROT",
                
                // Itens especiais
                "ENDER_PEARL", "ENDER_EYE", "TOTEM_OF_UNDYING", "ELYTRA",
                "FIREWORK_ROCKET", "TNT", "FLINT_AND_STEEL", "BUCKET",
                "WATER_BUCKET", "LAVA_BUCKET", "MILK_BUCKET", "SADDLE",
                "NAME_TAG", "LEAD", "COMPASS", "CLOCK", "FISHING_ROD",
                "CARROT_ON_A_STICK", "SHEARS", "COMMAND_BLOCK", "BARRIER",
                "STRUCTURE_BLOCK", "STRUCTURE_VOID", "KNOWLEDGE_BOOK",
                
                // Redstone
                "REDSTONE_TORCH", "LEVER", "BUTTON", "PRESSURE_PLATE",
                "TRIPWIRE_HOOK", "PISTON", "STICKY_PISTON", "OBSERVER",
                "HOPPER", "DROPPER", "DISPENSER", "COMPARATOR", "REPEATER"
            };
            
            // Pré-carregar ícones de itens em diferentes tamanhos comuns
            int[] sizes = {16, 24, 32, 48, 64};
            
            for (String imageName : allImages) {
                for (int size : sizes) {
                    try {
                        getItemIcon(imageName, size);
                        loaded++;
                    } catch (RuntimeException e) {
                        // Ignorar erros individuais
                    }
                }
            }
            
            System.out.println("Pré-carregando imagens de crafting da API...");
            
            // Baixar imagens de crafting de itens comuns
            String[] craftingItems = {
                "Diamond Sword", "Diamond Pickaxe", "Diamond Axe", "Diamond Shovel", "Diamond Hoe",
                "Iron Sword", "Iron Pickaxe", "Iron Axe", "Iron Shovel", "Iron Hoe",
                "Stone Sword", "Stone Pickaxe", "Stone Axe", "Stone Shovel", "Stone Hoe",
                "Wooden Sword", "Wooden Pickaxe", "Wooden Axe", "Wooden Shovel", "Wooden Hoe",
                "Golden Sword", "Golden Pickaxe", "Golden Axe", "Golden Shovel", "Golden Hoe",
                "Netherite Sword", "Netherite Pickaxe", "Netherite Axe", "Netherite Shovel", "Netherite Hoe",
                "Diamond Helmet", "Diamond Chestplate", "Diamond Leggings", "Diamond Boots",
                "Iron Helmet", "Iron Chestplate", "Iron Leggings", "Iron Boots",
                "Golden Helmet", "Golden Chestplate", "Golden Leggings", "Golden Boots",
                "Leather Helmet", "Leather Chestplate", "Leather Leggings", "Leather Boots",
                "Chainmail Helmet", "Chainmail Chestplate", "Chainmail Leggings", "Chainmail Boots",
                "Netherite Helmet", "Netherite Chestplate", "Netherite Leggings", "Netherite Boots",
                "Bow", "Crossbow", "Shield", "Trident",
                "Crafting Table", "Furnace", "Chest", "Anvil", "Enchanting Table",
                "Brewing Stand", "Beacon", "Ender Chest", "Shulker Box",
                "Torch", "Ladder", "Bed", "Door", "Trapdoor", "Fence", "Gate",
                "TNT", "Piston", "Sticky Piston", "Hopper", "Dropper", "Dispenser",
                "Redstone Torch", "Lever", "Button", "Pressure Plate", "Comparator", "Repeater",
                "Bread", "Cookie", "Cake", "Golden Apple", "Bucket", "Clock", "Compass",
                "Flint and Steel", "Fishing Rod", "Shears", "Lead", "Name Tag"
            };
            
            for (String itemName : craftingItems) {
                try {
                    String craftingPath = downloadCraftingImage(itemName);
                    if (craftingPath != null) {
                        craftingLoaded++;
                    }
                    // Pequena pausa para não sobrecarregar a rede
                    Thread.sleep(50);
                } catch (Exception e) {
                    // Ignorar erros individuais
                }
            }
            
            long endTime = System.currentTimeMillis();
            long duration = (endTime - startTime) / 1000;
            System.out.println("✅ Pré-carregamento concluído!");
            System.out.println("   📦 " + loaded + " ícones de itens carregados");
            System.out.println("   🔨 " + craftingLoaded + " imagens de crafting baixadas");
            System.out.println("   ⏱️ Tempo: " + duration + " segundos");
            System.out.println("   💾 " + getCacheStats());
        }, "ImagePreloader").start();
    }
}
