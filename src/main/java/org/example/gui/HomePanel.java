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
import javax.swing.SwingConstants;

import org.example.MinecraftWiki;

public class HomePanel extends JPanel {
    private final MinecraftWikiGUI parent;

    public HomePanel(MinecraftWikiGUI parent, MinecraftWiki wiki) {
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 40, 40));

        // Painel do título
        JPanel titlePanel = createTitlePanel();
        add(titlePanel, BorderLayout.NORTH);

        // Painel central com botões
        JPanel centerPanel = createCenterPanel();
        add(centerPanel, BorderLayout.CENTER);

        // Painel inferior com informações
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createTitlePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        // Título principal
        JLabel titleLabel = new JLabel("MINECRAFT WIKI");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 48));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtítulo
        JLabel subtitleLabel = new JLabel("🎮 Edição Completa e Interativa 🎮");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        subtitleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Versão
        JLabel versionLabel = new JLabel("Java & Bedrock Edition • v2.0");
        versionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        versionLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GRAY);
        versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(subtitleLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(versionLabel);

        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 8, 15, 15));
        panel.setBackground(new Color(40, 40, 40));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Linha 1
        panel.add(createCategoryButton("💎", "Comércio", MinecraftWikiGUI.MINECRAFT_GREEN, "COMMERCE"));
        panel.add(createCategoryButton("⚗️", "Fermentação", new Color(139, 90, 43), "BREWING"));
        panel.add(createCategoryButton("📖", "Encantamento", MinecraftWikiGUI.MINECRAFT_PURPLE, "ENCHANTMENTS"));
        panel.add(createCategoryButton("🐷", "Criaturas", new Color(0, 200, 0), "CREATURES"));
        panel.add(createCategoryButton("🧱", "Blocos", new Color(139, 69, 19), "BLOCKS"));
        panel.add(createCategoryButton("⛏️", "Itens", MinecraftWikiGUI.MINECRAFT_BLUE, "ITEMS"));
        panel.add(createCategoryButton("🌸", "Biomas", new Color(255, 105, 180), "BIOMES"));
        panel.add(createCategoryButton("✨", "Efeitos", MinecraftWikiGUI.MINECRAFT_GREEN.brighter(), "EFFECTS"));

        // Linha 2
        panel.add(createCategoryButton("🔨", "Fabricação", new Color(139, 90, 43), "CRAFTING"));
        panel.add(createCategoryButton("🔥", "Fundição", new Color(128, 128, 128), "SMELTING"));
        panel.add(createCategoryButton("⚒️", "Ferraria", new Color(64, 64, 64), "SMITHING"));
        panel.add(createCategoryButton("🏛️", "Estruturas", new Color(222, 184, 135), "STRUCTURES"));
        panel.add(createCategoryButton("🔴", "Redstone", MinecraftWikiGUI.MINECRAFT_RED, "REDSTONE"));
        panel.add(createCategoryButton("💻", "Comandos", new Color(255, 165, 0), "COMMANDS"));
        panel.add(createCategoryButton("📜", "Histórico", MinecraftWikiGUI.MINECRAFT_DARK_GRAY, "HISTORY"));
        panel.add(createCategoryButton("📚", "Tutoriais", MinecraftWikiGUI.MINECRAFT_GOLD, "TUTORIALS"));

        return panel;
    }

    private JButton createCategoryButton(String emoji, String title, Color color, String panelName) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout(5, 5));
        button.setBackground(new Color(60, 60, 60));
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 3),
            BorderFactory.createEmptyBorder(15, 10, 15, 10)
        ));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Emoji no topo
        JLabel emojiLabel = new JLabel(emoji, SwingConstants.CENTER);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        emojiLabel.setForeground(color);

        // Título embaixo
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        titleLabel.setForeground(Color.WHITE);

        JPanel contentPanel = new JPanel(new BorderLayout(5, 5));
        contentPanel.setOpaque(false);
        contentPanel.add(emojiLabel, BorderLayout.CENTER);
        contentPanel.add(titleLabel, BorderLayout.SOUTH);

        button.add(contentPanel);

        // Efeitos hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(80, 80, 80));
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(color.brighter(), 3),
                    BorderFactory.createEmptyBorder(15, 10, 15, 10)
                ));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(60, 60, 60));
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(color, 3),
                    BorderFactory.createEmptyBorder(15, 10, 15, 10)
                ));
            }
        });

        button.addActionListener(e -> handleCategoryClick(panelName));

        return button;
    }

    private void handleCategoryClick(String category) {
        switch (category) {
            case "ITEMS" -> parent.showPanel("ITEMS");
            case "ENCHANTMENTS" -> parent.showPanel("ENCHANTMENTS");
            case "BREWING" -> parent.showPanel("POTIONS");
            case "CRAFTING" -> parent.showPanel("CRAFTING");
            case "BLOCKS" -> parent.showPanel("BLOCKS");
            case "CREATURES" -> parent.showPanel("CREATURES");
            case "BIOMES" -> parent.showPanel("BIOMES");
            case "EFFECTS" -> parent.showPanel("EFFECTS");
            case "SMELTING" -> parent.showPanel("SMELTING");
            case "SMITHING" -> parent.showPanel("SMITHING");
            case "STRUCTURES" -> parent.showPanel("STRUCTURES");
            case "REDSTONE" -> parent.showPanel("REDSTONE");
            case "COMMANDS" -> parent.showPanel("COMMANDS");
            case "COMMERCE" -> parent.showPanel("COMMERCE");
            case "HISTORY" -> parent.showPanel("HISTORY");
            case "TUTORIALS" -> parent.showPanel("TUTORIALS");
            default -> parent.showPanel("HOME");
        }
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel infoLabel = new JLabel("💡 Explore as 16 categorias da Minecraft Wiki!");
        infoLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        infoLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);

        JButton statsButton = new JButton("📊 Estatísticas");
        statsButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        statsButton.setBackground(MinecraftWikiGUI.MINECRAFT_GOLD.darker());
        statsButton.setForeground(Color.WHITE);
        statsButton.setFocusPainted(false);
        statsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        statsButton.addActionListener(e -> parent.showPanel("STATISTICS"));

        JButton aboutButton = new JButton("ℹ️ Sobre");
        aboutButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        aboutButton.setBackground(MinecraftWikiGUI.MINECRAFT_BLUE.darker());
        aboutButton.setForeground(Color.WHITE);
        aboutButton.setFocusPainted(false);
        aboutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aboutButton.addActionListener(e -> parent.showPanel("ABOUT"));

        JButton exitButton = new JButton("🚪 Sair");
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        exitButton.setBackground(MinecraftWikiGUI.MINECRAFT_RED.darker());
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(infoLabel);
        panel.add(statsButton);
        panel.add(aboutButton);
        panel.add(exitButton);

        return panel;
    }
}

