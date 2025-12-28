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
        JPanel panel = new JPanel(new GridLayout(2, 4, 15, 15));
        panel.setBackground(new Color(40, 40, 40));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Linha 1
        panel.add(createCategoryButton("⛏️", "Itens", MinecraftWikiGUI.MINECRAFT_BLUE, "ITEMS"));
        panel.add(createCategoryButton("⚔️", "Armaduras", new Color(192, 192, 192), "ARMOR"));
        panel.add(createCategoryButton("📖", "Encantamento", MinecraftWikiGUI.MINECRAFT_PURPLE, "ENCHANTMENTS"));
        panel.add(createCategoryButton("⚗️", "Poções", MinecraftWikiGUI.MINECRAFT_PURPLE, "BREWING"));

        // Linha 2
        panel.add(createCategoryButton("🔨", "Fabricação", new Color(139, 90, 43), "CRAFTING"));
        panel.add(createCategoryButton("📊", "Estatísticas", MinecraftWikiGUI.MINECRAFT_GOLD, "STATISTICS"));
        panel.add(createCategoryButton("ℹ️", "Sobre", MinecraftWikiGUI.MINECRAFT_BLUE, "ABOUT"));
        panel.add(createCategoryButton("🚪", "Sair", MinecraftWikiGUI.MINECRAFT_RED, "EXIT"));

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
            case "ARMOR" -> parent.showPanel("ARMOR");
            case "ENCHANTMENTS" -> parent.showPanel("ENCHANTMENTS");
            case "BREWING" -> parent.showPanel("POTIONS");
            case "CRAFTING" -> parent.showPanel("CRAFTING");
            case "STATISTICS" -> parent.showPanel("STATISTICS");
            case "ABOUT" -> parent.showPanel("ABOUT");
            case "EXIT" -> System.exit(0);
            default -> parent.showPanel("HOME");
        }
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel infoLabel = new JLabel("💡 Bem-vindo à Minecraft Wiki! Explore as categorias acima.");
        infoLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        infoLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);

        panel.add(infoLabel);

        return panel;
    }
}

