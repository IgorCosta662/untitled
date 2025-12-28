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
import javax.swing.ImageIcon;
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

        // Pré-carregar imagens em background
        ImageManager.preloadImages();

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
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        // Botão Voltar no canto esquerdo
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        JButton backButton = createBackButton();
        leftPanel.add(backButton);

        // Títulos no centro
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Título principal
        JLabel titleLabel = new JLabel("MINECRAFT WIKI");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 48));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtítulo
        JLabel subtitleLabel = new JLabel("Edição Completa e Interativa");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        subtitleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Versão
        JLabel versionLabel = new JLabel("Java & Bedrock Edition • v2.0");
        versionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        versionLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GRAY);
        versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(subtitleLabel);
        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(versionLabel);

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("⬅ Voltar");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 70, 70));
        backButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BLUE, 2),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Tooltip com atalho
        backButton.setToolTipText("Voltar para página anterior (Alt+Backspace)");

        // Efeitos hover
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (parent.canGoBack()) {
                    backButton.setBackground(new Color(90, 90, 90));
                    backButton.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BLUE.brighter(), 2),
                        BorderFactory.createEmptyBorder(8, 15, 8, 15)
                    ));
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(70, 70, 70));
                backButton.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BLUE, 2),
                    BorderFactory.createEmptyBorder(8, 15, 8, 15)
                ));
            }
        });

        backButton.addActionListener(e -> parent.goBack());
        
        // Atualizar visibilidade do botão baseado no histórico
        backButton.setEnabled(parent.canGoBack());

        return backButton;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 20, 20));
        panel.setBackground(new Color(40, 40, 40));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 60));

        // === LINHA 1: CATEGORIAS PRINCIPAIS ===
        panel.add(createCategoryButton("ITEMS", "📦 Itens", MinecraftWikiGUI.MINECRAFT_BLUE, "ITEMS"));
        panel.add(createCategoryButton("SYSTEMS", "⚙️ Sistemas", new Color(85, 255, 255), "SYSTEMS"));
        panel.add(createCategoryButton("STATISTICS", "📊 Estatísticas", MinecraftWikiGUI.MINECRAFT_GOLD, "STATISTICS"));

        // === LINHA 2: UTILIDADES ===
        panel.add(createCategoryButton("WORLD", "🌍 Mundo", MinecraftWikiGUI.MINECRAFT_GREEN, "WORLD"));
        panel.add(createCategoryButton("ABOUT", "ℹ️ Sobre", new Color(100, 149, 237), "ABOUT"));
        panel.add(createCategoryButton("EXIT", "❌ Sair", MinecraftWikiGUI.MINECRAFT_RED, "EXIT"));

        return panel;
    }

    private JButton createCategoryButton(String itemName, String title, Color color, String panelName) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout(5, 5));
        button.setBackground(new Color(60, 60, 60));
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 3),
            BorderFactory.createEmptyBorder(15, 10, 15, 10)
        ));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Imagem do item no topo
        ImageIcon itemIcon = ImageManager.getItemIcon(itemName, 48);
        JLabel iconLabel = new JLabel(itemIcon, SwingConstants.CENTER);

        // Título embaixo
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        titleLabel.setForeground(Color.WHITE);

        JPanel contentPanel = new JPanel(new BorderLayout(5, 5));
        contentPanel.setOpaque(false);
        contentPanel.add(iconLabel, BorderLayout.CENTER);
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
            // Painéis Consolidados
            case "ITEMS" -> parent.showPanel("ITEMS");
            case "WORLD" -> parent.showPanel("WORLD");
            case "SYSTEMS" -> parent.showPanel("SYSTEMS");
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

        // Botão de modo offline
        JButton offlineButton = new JButton("🔌 Modo Offline");
        offlineButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        offlineButton.setForeground(Color.WHITE);
        offlineButton.setBackground(new Color(70, 70, 70));
        offlineButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        offlineButton.setFocusPainted(false);
        offlineButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        offlineButton.setToolTipText("Clique para alternar modo offline/online");
        
        offlineButton.addActionListener(e -> {
            boolean isOffline = ImageManager.isOfflineMode();
            ImageManager.setOfflineMode(!isOffline);
            
            if (!isOffline) {
                offlineButton.setText("🌐 Modo Online");
                offlineButton.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_GREEN, 2),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
                infoLabel.setText("🔌 Modo Offline ativado - usando cache local");
            } else {
                offlineButton.setText("🔌 Modo Offline");
                offlineButton.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
                infoLabel.setText("🌐 Modo Online - baixando imagens da wiki");
            }
        });

        // Label de estatísticas do cache
        JLabel cacheLabel = new JLabel(ImageManager.getCacheStats());
        cacheLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
        cacheLabel.setForeground(new Color(150, 150, 150));

        panel.add(infoLabel);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(offlineButton);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(cacheLabel);

        return panel;
    }
}

