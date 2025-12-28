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
                URL url = new URL(imageUrl);
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
     * Cria ícone fallback com cor e letra
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
        
        // Desenhar texto/emoji
        String text = getEmojiForItem(itemName);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Segoe UI Emoji", Font.BOLD, size * 6 / 10));
        
        FontMetrics fm = g2d.getFontMetrics();
        int x = (size - fm.stringWidth(text)) / 2;
        int y = ((size - fm.getHeight()) / 2) + fm.getAscent();
        
        g2d.drawString(text, x, y);
        g2d.dispose();
        
        return new ImageIcon(img);
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
     * Retorna emoji para fallback
     */
    private static String getEmojiForItem(String itemName) {
        return switch (itemName) {
            // Menu principal
            case "ITEMS" -> "⛏️";
            case "ARMOR" -> "🛡️";
            case "ENCHANTMENTS" -> "📖";
            case "BREWING" -> "⚗️";
            case "CRAFTING" -> "🔨";
            case "STATISTICS" -> "📊";
            case "API_TEST" -> "🌐";
            case "ABOUT" -> "ℹ️";
            case "EXIT" -> "🚪";
            
            // Ferramentas
            case "PICKAXE" -> "⛏️";
            case "AXE" -> "🪓";
            case "SHOVEL" -> "🏗️";
            case "HOE" -> "🌾";
            
            // Armas
            case "SWORD", "ATTACK" -> "⚔️";
            case "BOW" -> "🏹";
            case "CROSSBOW" -> "🏹";
            case "TRIDENT" -> "🔱";
            
            // Armaduras
            case "HELMET", "CHESTPLATE", "LEGGINGS", "BOOTS", "SHIELD", "DEFENSE" -> "🛡️";
            
            // Minérios
            case "DIAMOND", "EMERALD" -> "💎";
            case "GOLD_INGOT" -> "🥇";
            case "IRON_INGOT" -> "⚙️";
            case "NETHERITE" -> "🔥";
            case "COAL" -> "⚫";
            case "REDSTONE" -> "🔴";
            
            // Poções
            case "POTION_HEALING", "POTION_STRENGTH", "POTION_SPEED" -> "⚗️";
            case "BREWING_STAND" -> "🧪";
            
            // Encantamentos
            case "ENCHANTED_BOOK" -> "📖";
            case "ENCHANTING_TABLE" -> "✨";
            case "ANVIL" -> "🔨";
            
            // Crafting
            case "CRAFTING_TABLE", "FURNACE", "BLAST_FURNACE", "SMITHING_TABLE" -> "🔨";
            
            // Livros
            case "BOOK", "RECIPE" -> "📖";
            
            default -> "❓";
        };
    }
    
    /**
     * Pré-carrega imagens em background para modo offline
     */
    public static void preloadImages() {
        new Thread(() -> {
            System.out.println("📦 Iniciando pré-carregamento de imagens para modo offline...");
            
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
            
            System.out.println("✅ Pré-carregamento concluído: " + downloaded + "/" + total + " imagens disponíveis");
            System.out.println("💾 Cache local criado em: " + CACHE_DIR);
            System.out.println("🌐 Modo offline disponível!");
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
                    URL url = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setRequestProperty("User-Agent", "MinecraftWiki/1.0");
                    
                    if (connection.getResponseCode() == 200) {
                        BufferedImage img = ImageIO.read(connection.getInputStream());
                        if (img != null) {
                            ImageIO.write(img, "PNG", cacheFile);
                            System.out.println("📥 Imagem de crafting baixada: " + craftingFileName);
                            return cacheFile.getAbsolutePath();
                        }
                    }
                } catch (Exception e) {
                    // Tentar próxima URL
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao baixar imagem de crafting: " + craftingFileName);
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
    public static void autoSaveItemImages(org.example.Item item) {
        if (item == null) return;
        
        String itemName = item.getNome();
        System.out.println("💾 Salvando imagens para: " + itemName);
        
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
}
