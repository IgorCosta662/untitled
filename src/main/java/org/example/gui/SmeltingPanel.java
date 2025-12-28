package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class SmeltingPanel extends JPanel {
    private final MinecraftWikiGUI parent;
    
    private static final Color MINECRAFT_GREEN = new Color(85, 255, 85);
    private static final Color MINECRAFT_GOLD = new Color(255, 170, 0);
    private static final Color MINECRAFT_RED = new Color(255, 85, 85);
    private static final Color DARK_BG = new Color(40, 40, 40);
    private static final Color DARKER_BG = new Color(30, 30, 30);

    public SmeltingPanel(MinecraftWikiGUI parent) {
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(DARK_BG);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createTitlePanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private JPanel createTitlePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(DARK_BG);

        JLabel titleLabel = new JLabel("🔥 FUNDIÇÃO E FORNALHAS");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(MINECRAFT_GOLD);
        panel.add(titleLabel);

        return panel;
    }

    private JPanel createContentPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(DARK_BG);

        // Info panel no topo
        JPanel infoPanel = createInfoPanel();
        mainPanel.add(infoPanel, BorderLayout.NORTH);

        // Tabs com categorias
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setBackground(DARKER_BG);
        tabbedPane.setForeground(Color.WHITE);

        tabbedPane.addTab("⛏️ Minérios", createOresPanel());
        tabbedPane.addTab("🍖 Alimentos", createFoodPanel());
        tabbedPane.addTab("🧱 Blocos", createBlocksPanel());
        tabbedPane.addTab("💎 Outros", createOthersPanel());

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARKER_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_GOLD, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel title = new JLabel("⚙️ TIPOS DE FORNALHAS");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(MINECRAFT_GOLD);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        String infoText = """
                🔥 Fornalha Normal - Velocidade padrão (10s por item)
                ⚡ Fornalha-relâmpago - 2x mais rápida, consome o dobro de combustível
                💨 Defumador - 2x mais rápido para ALIMENTOS apenas
                
                💡 Combustíveis: Carvão (8 itens), Bloco de Carvão (80 itens), Vara de Blaze (12 itens), Lava (100 itens)
                """;

        JTextArea infoArea = new JTextArea(infoText);
        infoArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        infoArea.setForeground(Color.WHITE);
        infoArea.setBackground(DARKER_BG);
        infoArea.setEditable(false);

        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(infoArea);

        return panel;
    }

    private JScrollPane createOresPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[][] ores = {
            {"⚫", "Carvão Bruto → Carvão", "10s", "Minerar ou combustível"},
            {"⚪", "Ferro Bruto → Barra de Ferro", "10s", "Ferramentas, armaduras, trilhos"},
            {"🟡", "Ouro Bruto → Barra de Ouro", "10s", "Equipamentos, powered rails"},
            {"💎", "Minério de Diamante → Diamante", "10s", "Já sai diamante, não precisa fundir!"},
            {"💚", "Minério de Esmeralda → Esmeralda", "10s", "Já sai esmeralda, não precisa fundir!"},
            {"🔷", "Lápis-lazúli (Bruto) → Lápis", "10s", "Corante azul, encantamentos"},
            {"🟠", "Cobre Bruto → Barra de Cobre", "10s", "Lightning rod, spyglass, blocos"},
            {"🟢", "Ancient Debris → Sucata Netherite", "10s", "Material para netherite (4 sucatas = 1 barra)"}
        };

        for (String[] ore : ores) {
            panel.add(createRecipeCard(ore[0], ore[1], ore[2], ore[3]));
        }

        return new JScrollPane(panel);
    }

    private JScrollPane createFoodPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[][] food = {
            {"🥩", "Carne Crua → Bife", "10s", "Recupera 8 de fome"},
            {"🥓", "Carne de Porco → Costeleta", "10s", "Recupera 8 de fome"},
            {"🍗", "Frango Cru → Frango Assado", "10s", "Recupera 6 de fome"},
            {"🐟", "Peixe Cru → Peixe Cozido", "10s", "Recupera 5 de fome"},
            {"🍖", "Coelho Cru → Coelho Cozido", "10s", "Recupera 5 de fome"},
            {"🐑", "Carneiro Cru → Carneiro Assado", "10s", "Recupera 6 de fome"},
            {"🥔", "Batata → Batata Assada", "10s", "Recupera 5 de fome"},
            {"🥬", "Alga Marinha → Alga Seca", "10s", "Usado em receitas"}
        };

        for (String[] item : food) {
            panel.add(createRecipeCard(item[0], item[1], item[2], item[3]));
        }

        return new JScrollPane(panel);
    }

    private JScrollPane createBlocksPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[][] blocks = {
            {"🧱", "Tijolo de Argila → Tijolo", "10s", "Construção decorativa"},
            {"🪨", "Pedra → Pedra Lisa", "10s", "Construção, decoração"},
            {"🏺", "Terracota → Terracota Vidrada", "10s", "Com corante, decoração"},
            {"🪣", "Areia → Vidro", "10s", "Janelas, garrafas"},
            {"🟢", "Bloco de Lodo → Tijolo de Lodo", "10s", "Construção verde"},
            {"🪵", "Tronco → Carvão Vegetal", "10s", "Substituto do carvão"},
            {"🧽", "Esponja Molhada → Esponja", "10s", "Remove água"},
            {"⬛", "Pedra do Nether → Tijolo Nether", "10s", "Construção no Nether"}
        };

        for (String[] block : blocks) {
            panel.add(createRecipeCard(block[0], block[1], block[2], block[3]));
        }

        return new JScrollPane(panel);
    }

    private JScrollPane createOthersPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[][] others = {
            {"🟢", "Cacto → Corante Verde", "10s", "Tingir lã, couro"},
            {"🪨", "Pedra + Corante → Terracota Colorida", "10s", "16 cores disponíveis"},
            {"⚙️", "Areia + Pó Corante → Vidro Colorido", "10s", "Vitrais decorativos"},
            {"🌵", "Blocos de Madeira → Carvão Vegetal", "10s", "Alternativa ao carvão"},
            {"🪙", "Ferramentas/Armaduras de Ferro → Pepita", "10s", "Reciclagem"},
            {"💰", "Ferramentas/Armaduras de Ouro → Pepita Ouro", "10s", "Reciclagem"},
            {"🎣", "Armadura de Malha → Pepita de Ferro", "10s", "Rara, só de pesca/loot"},
            {"🔱", "Ancient Debris (Raro!) → Sucata Netherite", "10s", "Mais valioso do jogo"}
        };

        for (String[] other : others) {
            panel.add(createRecipeCard(other[0], other[1], other[2], other[3]));
        }

        return new JScrollPane(panel);
    }

    private JPanel createRecipeCard(String emoji, String recipe, String time, String use) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(DARKER_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_RED, 2),
            new EmptyBorder(10, 10, 10, 10)
        ));

        JLabel emojiLabel = new JLabel(emoji);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 35));
        emojiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel recipeLabel = new JLabel(recipe);
        recipeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        recipeLabel.setForeground(MINECRAFT_GOLD);
        recipeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel timeLabel = new JLabel("⏱️ " + time);
        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        timeLabel.setForeground(Color.LIGHT_GRAY);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea useArea = new JTextArea(use);
        useArea.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        useArea.setForeground(Color.GRAY);
        useArea.setBackground(DARKER_BG);
        useArea.setEditable(false);
        useArea.setLineWrap(true);
        useArea.setWrapStyleWord(true);
        useArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(emojiLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(recipeLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(timeLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(useArea);

        return card;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(DARK_BG);

        JButton backButton = new JButton("🏠 Voltar ao Menu");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(MINECRAFT_GREEN);
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> parent.showPanel("HOME"));

        panel.add(backButton);

        return panel;
    }
}
