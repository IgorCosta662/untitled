package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.example.MinecraftWiki;
import org.example.Pocao;

public class PotionsPanel extends JPanel {
    private final MinecraftWiki wiki;
    private JTextField searchField;
    private JPanel resultsPanel;
    private JScrollPane scrollPane;

    public PotionsPanel(MinecraftWiki wiki) {
        this.wiki = wiki;
        setupUI();
        loadAllPotions();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(40, 40, 40));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createTopPanel(), BorderLayout.NORTH);

        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(new Color(40, 40, 40));

        scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setBackground(new Color(40, 40, 40));
        scrollPane.setBorder(BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_PURPLE, 2));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(40, 40, 40));

        JLabel titleLabel = ImageManager.createIconLabel("BREWING", " POÇÕES E EFEITOS", 28);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_PURPLE);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(new Color(40, 40, 40));

        JLabel searchLabel = new JLabel("🔍 Buscar:");
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        searchField = new JTextField(40);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.addActionListener(e -> performSearch());

        JButton searchButton = new JButton("Buscar");
        searchButton.setBackground(MinecraftWikiGUI.MINECRAFT_PURPLE);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(e -> performSearch());

        JButton clearButton = new JButton("Limpar");
        clearButton.setBackground(MinecraftWikiGUI.MINECRAFT_GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(e -> {
            searchField.setText("");
            loadAllPotions();
        });

        JButton guideButton = new JButton(" Guia de Preparação");
        guideButton.setIcon(ImageManager.getItemIcon("BOOK", 16));
        guideButton.setBackground(MinecraftWikiGUI.MINECRAFT_BLUE);
        guideButton.setForeground(Color.WHITE);
        guideButton.setFocusPainted(false);
        guideButton.addActionListener(e -> showBrewingGuide());

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);
        searchPanel.add(Box.createHorizontalStrut(10));
        searchPanel.add(guideButton);

        JPanel topContainer = new JPanel(new BorderLayout(10, 10));
        topContainer.setBackground(new Color(40, 40, 40));
        topContainer.add(titleLabel, BorderLayout.NORTH);
        topContainer.add(searchPanel, BorderLayout.CENTER);

        panel.add(topContainer, BorderLayout.CENTER);

        JButton backButton = new JButton("⬅️ Voltar");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(MinecraftWikiGUI.MINECRAFT_GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            MinecraftWikiGUI gui = (MinecraftWikiGUI) SwingUtilities.getWindowAncestor(this);
            gui.showPanel("HOME");
        });
        panel.add(backButton, BorderLayout.WEST);

        return panel;
    }

    private void performSearch() {
        String searchTerm = searchField.getText().trim();
        if (searchTerm.isEmpty()) {
            loadAllPotions();
            return;
        }

        List<Pocao> results = wiki.buscarPocoes(searchTerm);
        displayPotions(results);
    }

    private void loadAllPotions() {
        List<Pocao> potions = wiki.listarTodasPocoes();
        displayPotions(potions);
    }

    private void displayPotions(List<Pocao> potions) {
        resultsPanel.removeAll();

        if (potions.isEmpty()) {
            JLabel noResults = new JLabel("❌ Nenhuma poção encontrada");
            noResults.setFont(new Font("SansSerif", Font.BOLD, 18));
            noResults.setForeground(MinecraftWikiGUI.MINECRAFT_RED);
            noResults.setAlignmentX(Component.CENTER_ALIGNMENT);
            resultsPanel.add(Box.createVerticalStrut(50));
            resultsPanel.add(noResults);
        } else {
            JLabel countLabel = new JLabel("✅ " + potions.size() + " poção(ões) encontrada(s)");
            countLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            countLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
            countLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            countLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
            resultsPanel.add(countLabel);

            for (Pocao potion : potions) {
                resultsPanel.add(createPotionCard(potion));
                resultsPanel.add(Box.createVerticalStrut(10));
            }
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
        scrollPane.getVerticalScrollBar().setValue(0);
    }

    private JPanel createPotionCard(Pocao potion) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(new Color(60, 60, 60));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_PURPLE, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));

        // Painel com imagem da poção à esquerda
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        leftPanel.setBorder(new EmptyBorder(0, 0, 0, 15));

        // Tentar obter ícone da poção
        String potionIconName = getPotionIconName(potion.getNome());
        JLabel potionIcon = new JLabel(ImageManager.getItemIcon(potionIconName, 48));
        potionIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(potionIcon);
        leftPanel.add(Box.createVerticalStrut(5));

        JLabel typeLabel = new JLabel("Poção");
        typeLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        typeLabel.setForeground(Color.LIGHT_GRAY);
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(typeLabel);

        card.add(leftPanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(potion.getNome());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        nameLabel.setForeground(MinecraftWikiGUI.MINECRAFT_PURPLE.brighter());

        JLabel editionLabel = new JLabel("Edição: " + potion.getEdicao().getDisplayName());
        editionLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        editionLabel.setForeground(MinecraftWikiGUI.MINECRAFT_BLUE);

        JLabel effectLabel = new JLabel("⚡ Efeito: " + potion.getEfeito());
        effectLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        effectLabel.setForeground(Color.WHITE);

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(editionLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(effectLabel);

        card.add(infoPanel, BorderLayout.CENTER);

        // Adicionar botão "Ver Receita"
        JButton recipeButton = new JButton("📋 Ver Receita");
        recipeButton.setBackground(MinecraftWikiGUI.MINECRAFT_GREEN);
        recipeButton.setForeground(Color.WHITE);
        recipeButton.setFocusPainted(false);
        recipeButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        recipeButton.addActionListener(e -> showPotionRecipe(potion));

        card.add(recipeButton, BorderLayout.EAST);

        return card;
    }

    private void showPotionRecipe(Pocao potion) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), 
            "Receita: " + potion.getNome(), true);
        dialog.setSize(800, 700);
        dialog.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(40, 40, 40));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Painel de informações com imagem da poção
        JPanel infoPanel = new JPanel(new BorderLayout(15, 15));
        infoPanel.setBackground(new Color(50, 50, 50));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_PURPLE, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));

        // Imagem da poção à esquerda
        JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.Y_AXIS));
        iconPanel.setOpaque(false);
        
        String potionIconName = getPotionIconName(potion.getNome());
        JLabel potionIcon = new JLabel(ImageManager.getItemIcon(potionIconName, 64));
        potionIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconPanel.add(potionIcon);

        infoPanel.add(iconPanel, BorderLayout.WEST);

        // Informações da poção
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setOpaque(false);

        JLabel titleLabel = new JLabel(potion.getNome());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_PURPLE.brighter());
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel editionLabel = new JLabel("📦 Edição: " + potion.getEdicao().getDisplayName());
        editionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        editionLabel.setForeground(MinecraftWikiGUI.MINECRAFT_BLUE);
        editionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel effectLabel = new JLabel("⚡ Efeito: " + potion.getEfeito());
        effectLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        effectLabel.setForeground(Color.WHITE);
        effectLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel durationLabel = new JLabel("⏱️ Duração: " + potion.toString().split("Duração: ")[1].split("\n")[0]);
        durationLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        durationLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        durationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        detailsPanel.add(titleLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(editionLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(effectLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(durationLabel);

        infoPanel.add(detailsPanel, BorderLayout.CENTER);

        // Painel de ingredientes com imagens
        JPanel ingredientsPanel = new JPanel();
        ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.Y_AXIS));
        ingredientsPanel.setBackground(new Color(35, 35, 35));
        ingredientsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_GREEN, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel recipeTitle = new JLabel("🧪 INGREDIENTES NECESSÁRIOS");
        recipeTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        recipeTitle.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        recipeTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        ingredientsPanel.add(recipeTitle);
        ingredientsPanel.add(Box.createVerticalStrut(15));

        // Adicionar ingredientes com ícones
        addIngredientWithIcon(ingredientsPanel, "GLASS_BOTTLE", "Garrafa de Vidro", "Base inicial");
        addIngredientWithIcon(ingredientsPanel, "NETHER_WART", "Verruga do Nether", "Criar Poção Estranha");
        
        // Ingrediente principal baseado no efeito
        String mainIngredient = getMainIngredient(potion.getNome());
        addIngredientWithIcon(ingredientsPanel, mainIngredient, 
            mainIngredient.replace("_", " "), "Efeito principal");

        ingredientsPanel.add(Box.createVerticalStrut(10));

        JLabel modifiersLabel = new JLabel("⚙️ MODIFICADORES OPCIONAIS:");
        modifiersLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        modifiersLabel.setForeground(Color.YELLOW);
        modifiersLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ingredientsPanel.add(modifiersLabel);
        ingredientsPanel.add(Box.createVerticalStrut(10));

        addIngredientWithIcon(ingredientsPanel, "GLOWSTONE_DUST", "Pó de Pedra Luminosa", "↑ Potência");
        addIngredientWithIcon(ingredientsPanel, "REDSTONE", "Pó de Redstone", "↑ Duração");
        addIngredientWithIcon(ingredientsPanel, "GUNPOWDER", "Pólvora", "= Arremessável");

        // Área de receita
        JTextArea recipeArea = new JTextArea();
        recipeArea.setEditable(false);
        recipeArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        recipeArea.setBackground(new Color(30, 30, 30));
        recipeArea.setForeground(Color.WHITE);
        recipeArea.setBorder(new EmptyBorder(15, 15, 15, 15));
        recipeArea.setLineWrap(true);
        recipeArea.setWrapStyleWord(true);

        StringBuilder recipe = new StringBuilder();
        recipe.append("\n╔═══════════════════════════════════════════════════════╗\n");
        recipe.append("║              PROCESSO DE PREPARAÇÃO                  ║\n");
        recipe.append("╠═══════════════════════════════════════════════════════╣\n");
        recipe.append("║                                                       ║\n");

        // Extrair ingredientes do toString() do potion
        String potionString = potion.toString();
        if (potionString.contains("Base:")) {
            String basePotion = potionString.split("Base: ")[1].split("\n")[0].trim();
            recipe.append(String.format("║  PASSO 1: %s%-38s║\n", 
                "Comece com ", basePotion));
            recipe.append("║           Coloque no Suporte de Poções               ║\n");
            recipe.append("║                                                       ║\n");
        }

        if (potionString.contains("Ingredientes:")) {
            String[] lines = potionString.split("\n");
            boolean inIngredients = false;
            int stepNumber = 2;
            
            for (String line : lines) {
                if (line.contains("Ingredientes:")) {
                    inIngredients = true;
                    continue;
                }
                if (inIngredients && line.contains("•")) {
                    String ingredient = line.replace("•", "").trim();
                    if (!ingredient.isEmpty()) {
                        recipe.append(String.format("║  PASSO %d: Adicione %s%-28s║\n", 
                            stepNumber++, "", ingredient));
                        recipe.append("║           Aguarde a fermentação completar            ║\n");
                        recipe.append("║                                                       ║\n");
                    }
                }
                if (line.contains("╚═") || line.contains("---")) {
                    break;
                }
            }
        }

        recipe.append("║  RESULTADO: ").append(potion.getNome());
        for (int i = potion.getNome().length(); i < 39; i++) {
            recipe.append(" ");
        }
        recipe.append("║\n");
        recipe.append("╚═══════════════════════════════════════════════════════╝\n\n");

        // Adicionar dicas
        recipe.append("\n💡 DICAS IMPORTANTES:\n\n");
        recipe.append("• Use Pó de Pedra Luminosa para aumentar POTÊNCIA\n");
        recipe.append("  (reduz duração)\n\n");
        recipe.append("• Use Pó de Redstone para aumentar DURAÇÃO\n");
        recipe.append("  (reduz potência)\n\n");
        recipe.append("• Adicione Pólvora para criar versão ARREMESSÁVEL\n\n");
        recipe.append("• Adicione Sopro do Dragão para versão PERSISTENTE\n\n");
        recipe.append("• Olho de Aranha Fermentado CORROMPE a poção\n");
        recipe.append("  (transforma em efeito negativo)\n\n");
        recipe.append("• Você pode preparar até 3 poções simultaneamente\n\n");
        recipe.append("• Pó de Blaze é usado como combustível no suporte\n\n");
        
        if (potion.getNome().contains("II")) {
            recipe.append("⚠️ Esta é uma poção de NÍVEL 2 (mais forte)\n");
        }
        if (potion.getNome().contains("Estendida")) {
            recipe.append("⏱️ Esta é uma poção ESTENDIDA (maior duração)\n");
        }

        recipeArea.setText(recipe.toString());
        recipeArea.setCaretPosition(0);

        JScrollPane recipeScrollPane = new JScrollPane(recipeArea);
        recipeScrollPane.setBorder(BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_PURPLE, 2));

        // Layout principal
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setOpaque(false);
        centerPanel.add(ingredientsPanel, BorderLayout.NORTH);
        centerPanel.add(recipeScrollPane, BorderLayout.CENTER);

        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Botão fechar
        JButton closeButton = new JButton("✖️ Fechar");
        closeButton.setBackground(MinecraftWikiGUI.MINECRAFT_RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        closeButton.addActionListener(e -> dialog.dispose());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(40, 40, 40));
        buttonPanel.add(closeButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(mainPanel);
        dialog.setVisible(true);
    }

    private void showBrewingGuide() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Guia de Preparação de Poções", true);
        dialog.setSize(800, 600);
        dialog.setLocationRelativeTo(this);

        JTextArea guideArea = new JTextArea();
        guideArea.setEditable(false);
        guideArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        guideArea.setBackground(new Color(40, 40, 40));
        guideArea.setForeground(Color.WHITE);
        guideArea.setBorder(new EmptyBorder(20, 20, 20, 20));
        guideArea.setLineWrap(true);
        guideArea.setWrapStyleWord(true);

        String guide = """
            📌 GUIA COMPLETO DE PREPARAÇÃO DE POÇÕES
            
            ══════════════════════════════════════════════════════════
            
            EQUIPAMENTO NECESSÁRIO:
            • Suporte de Poções (Brewing Stand)
            • Garrafas de Vidro (Glass Bottles)
            • Pó de Blaze como combustível
            • Água (fonte ou caldeirão)
            
            ══════════════════════════════════════════════════════════
            
            PROCESSO BÁSICO:
            
            1. Encha garrafas com água
            2. Coloque as garrafas no suporte de poções
            3. Adicione Verruga do Nether → Poção Estranha
            4. Adicione o ingrediente principal → Efeito desejado
            
            ══════════════════════════════════════════════════════════
            
            MODIFICADORES:
            
            • Pó de Pedra Luminosa: Aumenta POTÊNCIA (reduz duração)
            • Pó de Redstone: Aumenta DURAÇÃO
            • Pólvora: Transforma em POÇÃO ARREMESSÁVEL
            • Sopro do Dragão: Transforma em POÇÃO PERSISTENTE
            • Olho de Aranha Fermentado: CORROMPE a poção
            
            ══════════════════════════════════════════════════════════
            
            INGREDIENTES PRIMÁRIOS:
            
            • Açúcar → Velocidade
            • Olho de Aranha → Veneno
            • Melancia Reluzente → Cura
            • Pó de Blaze → Força
            • Creme de Magma → Resistência ao Fogo
            • Lágrima de Ghast → Regeneração
            • Cenoura Dourada → Visão Noturna
            • Peixe-balão → Respiração Aquática
            • Membrana de Phantom → Queda Lenta
            • Pé de Coelho → Salto
            
            ══════════════════════════════════════════════════════════
            
            DICAS IMPORTANTES:
            
            ✓ Sempre comece com Poção Estranha (água + verruga do nether)
            ✓ Você pode preparar até 3 poções ao mesmo tempo
            ✓ Alguns efeitos podem ser combinados
            ✓ Poções arremessáveis causam menos efeito que bebíveis
            ✓ Use caldeirões para economizar garrafas
            
            ══════════════════════════════════════════════════════════
            """;

        guideArea.setText(guide);
        guideArea.setCaretPosition(0);

        JScrollPane guideScrollPane = new JScrollPane(guideArea);
        guideScrollPane.setBorder(null);

        dialog.add(guideScrollPane);
        dialog.setVisible(true);
    }

    /**
     * Retorna o nome do ícone da poção baseado no nome.
     */
    private String getPotionIconName(String potionName) {
        // Normalizar nome da poção para encontrar o ícone correto
        String normalized = potionName.toLowerCase();
        
        if (normalized.contains("cura")) return "POTION_HEALING";
        if (normalized.contains("força")) return "POTION_STRENGTH";
        if (normalized.contains("velocidade")) return "POTION_SWIFTNESS";
        if (normalized.contains("regeneração")) return "POTION_REGENERATION";
        if (normalized.contains("resistência")) return "POTION_FIRE_RESISTANCE";
        if (normalized.contains("veneno")) return "POTION_POISON";
        if (normalized.contains("fraqueza")) return "POTION_WEAKNESS";
        if (normalized.contains("lentidão")) return "POTION_SLOWNESS";
        if (normalized.contains("dano")) return "POTION_HARMING";
        if (normalized.contains("visão noturna")) return "POTION_NIGHT_VISION";
        if (normalized.contains("invisibilidade")) return "POTION_INVISIBILITY";
        if (normalized.contains("salto")) return "POTION_LEAPING";
        if (normalized.contains("respiração")) return "POTION_WATER_BREATHING";
        if (normalized.contains("queda")) return "POTION_SLOW_FALLING";
        if (normalized.contains("sorte")) return "POTION_LUCK";
        if (normalized.contains("tartaruga")) return "POTION_TURTLE_MASTER";
        
        // Poção genérica se não encontrar específica
        return "POTION";
    }

    /**
     * Adiciona um ingrediente com ícone ao painel.
     */
    private void addIngredientWithIcon(JPanel panel, String iconName, String ingredientName, String description) {
        JPanel ingredientPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        ingredientPanel.setOpaque(false);

        JLabel icon = new JLabel(ImageManager.getItemIcon(iconName, 24));
        JLabel nameLabel = new JLabel(ingredientName);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        nameLabel.setForeground(Color.WHITE);

        JLabel descLabel = new JLabel(" - " + description);
        descLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        descLabel.setForeground(Color.LIGHT_GRAY);

        ingredientPanel.add(icon);
        ingredientPanel.add(nameLabel);
        ingredientPanel.add(descLabel);

        panel.add(ingredientPanel);
    }

    /**
     * Retorna o ingrediente principal baseado no nome da poção.
     */
    private String getMainIngredient(String potionName) {
        String normalized = potionName.toLowerCase();
        
        if (normalized.contains("cura")) return "GLISTERING_MELON_SLICE";
        if (normalized.contains("força")) return "BLAZE_POWDER";
        if (normalized.contains("velocidade")) return "SUGAR";
        if (normalized.contains("regeneração")) return "GHAST_TEAR";
        if (normalized.contains("resistência")) return "MAGMA_CREAM";
        if (normalized.contains("veneno")) return "SPIDER_EYE";
        if (normalized.contains("fraqueza")) return "FERMENTED_SPIDER_EYE";
        if (normalized.contains("lentidão")) return "FERMENTED_SPIDER_EYE";
        if (normalized.contains("dano")) return "FERMENTED_SPIDER_EYE";
        if (normalized.contains("visão noturna")) return "GOLDEN_CARROT";
        if (normalized.contains("invisibilidade")) return "FERMENTED_SPIDER_EYE";
        if (normalized.contains("salto")) return "RABBIT_FOOT";
        if (normalized.contains("respiração")) return "PUFFERFISH";
        if (normalized.contains("queda")) return "PHANTOM_MEMBRANE";
        if (normalized.contains("sorte")) return "RABBIT_FOOT";
        if (normalized.contains("tartaruga")) return "TURTLE_SHELL";
        
        return "NETHER_WART"; // Default
    }
}

