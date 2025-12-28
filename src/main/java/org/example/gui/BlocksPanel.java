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
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class BlocksPanel extends JPanel {
    private final MinecraftWikiGUI parent;
    
    // Cores do tema Minecraft
    private static final Color MINECRAFT_GREEN = new Color(85, 255, 85);
    private static final Color MINECRAFT_GOLD = new Color(255, 170, 0);
    private static final Color DARK_BG = new Color(40, 40, 40);
    private static final Color DARKER_BG = new Color(30, 30, 30);

    public BlocksPanel(MinecraftWikiGUI parent) {
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

        JLabel titleLabel = new JLabel("🧱 BLOCOS DO MINECRAFT");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(MINECRAFT_GOLD);
        panel.add(titleLabel);

        return panel;
    }

    private JPanel createContentPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(DARK_BG);

        // Painel de categorias
        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new GridLayout(4, 2, 10, 10));
        categoriesPanel.setBackground(DARK_BG);

        String[][] categories = {
            {"🪨", "Blocos Naturais", "Pedra, Terra, Areia, Cascalho"},
            {"🌳", "Blocos de Madeira", "Tábuas, Troncos, Cercas"},
            {"🏗️", "Blocos de Construção", "Tijolos, Concreto, Terracota"},
            {"💎", "Blocos de Minérios", "Carvão, Ferro, Ouro, Diamante"},
            {"🌿", "Blocos de Plantas", "Grama, Flores, Árvores"},
            {"💡", "Blocos Luminosos", "Tochas, Lanternas, Glowstone"},
            {"⚙️", "Blocos Funcionais", "Fornalha, Baú, Mesa de Trabalho"},
            {"🎨", "Blocos Decorativos", "Vidro, Lã, Tapetes, Banners"}
        };

        for (String[] category : categories) {
            JPanel categoryCard = createCategoryCard(category[0], category[1], category[2]);
            categoriesPanel.add(categoryCard);
        }

        JScrollPane scrollPane = new JScrollPane(categoriesPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Painel de informações
        JPanel infoPanel = createInfoPanel();
        mainPanel.add(infoPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createCategoryCard(String emoji, String title, String description) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(DARKER_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_GREEN, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel emojiLabel = new JLabel(emoji);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        emojiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(MINECRAFT_GOLD);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea descArea = new JTextArea(description);
        descArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descArea.setForeground(Color.LIGHT_GRAY);
        descArea.setBackground(DARKER_BG);
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(emojiLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(descArea);

        return card;
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARKER_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_GOLD, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel infoTitle = new JLabel("📚 Informações sobre Blocos");
        infoTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        infoTitle.setForeground(MINECRAFT_GOLD);
        infoTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        String infoText = """
                • Existem mais de 800 tipos diferentes de blocos no Minecraft
                • Cada bloco tem propriedades únicas: dureza, resistência, transparência
                • Alguns blocos podem ser coletados apenas com ferramentas específicas
                • Blocos decorativos podem ser combinados para criar construções incríveis
                • Alguns blocos emitem luz (nível 0-15), afetando a geração de mobs
                """;

        JTextArea infoArea = new JTextArea(infoText);
        infoArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        infoArea.setForeground(Color.WHITE);
        infoArea.setBackground(DARKER_BG);
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);

        panel.add(infoTitle);
        panel.add(Box.createVerticalStrut(10));
        panel.add(infoArea);

        return panel;
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
