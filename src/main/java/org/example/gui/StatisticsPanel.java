package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.example.MinecraftWiki;

public class StatisticsPanel extends JPanel {
    private final MinecraftWiki wiki;

    public StatisticsPanel(MinecraftWiki wiki) {
        this.wiki = wiki;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(40, 40, 40));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createTopPanel(), BorderLayout.NORTH);
        add(createStatsPanel(), BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("📊 ESTATÍSTICAS DO BANCO DE DADOS");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);

        panel.add(titleLabel, BorderLayout.CENTER);

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

    private JPanel createStatsPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.weightx = 1;
        gbc.weighty = 1;

        // Card de Itens
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(createStatCardWithIcon(
            "CHEST",
            " ITENS",
            String.valueOf(wiki.listarTodosItens().size()),
            "Receitas de crafting disponíveis",
            MinecraftWikiGUI.MINECRAFT_BROWN
        ), gbc);

        // Card de Poções
        gbc.gridx = 1;
        mainPanel.add(createStatCardWithIcon(
            "BREWING",
            " POÇÕES",
            String.valueOf(wiki.listarTodasPocoes().size()),
            "Poções e efeitos catalogados",
            MinecraftWikiGUI.MINECRAFT_PURPLE
        ), gbc);

        // Card de Encantamentos
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(createStatCardWithIcon(
            "ENCHANTED_BOOK",
            " ENCANTAMENTOS",
            String.valueOf(wiki.listarTodosEncantamentos().size()),
            "Encantamentos registrados",
            MinecraftWikiGUI.MINECRAFT_BLUE
        ), gbc);

        // Card de Total
        gbc.gridx = 1;
        int total = wiki.listarTodosItens().size() +
                    wiki.listarTodasPocoes().size() +
                    wiki.listarTodosEncantamentos().size();
        mainPanel.add(createStatCardWithIcon(
            "DIAMOND",
            " TOTAL",
            String.valueOf(total),
            "Entradas no banco de dados",
            MinecraftWikiGUI.MINECRAFT_GREEN
        ), gbc);

        // Informações adicionais
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0.5;
        mainPanel.add(createInfoPanel(), gbc);

        return mainPanel;
    }

    private JPanel createStatCardWithIcon(String iconName, String title, String value, String description, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(60, 60, 60));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 3),
            new EmptyBorder(30, 30, 30, 30)
        ));

        JLabel titleLabel = ImageManager.createIconLabel(iconName, title, 24);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(color);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 72));
        valueLabel.setForeground(color.brighter());
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GRAY);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(20));
        card.add(valueLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(descLabel);

        return card;
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(50, 50, 50));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_GOLD, 2),
            new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel titleLabel = new JLabel("ℹ️ INFORMAÇÕES DO SISTEMA");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel info1 = new JLabel("✓ Suporte para Java Edition e Bedrock Edition");
        info1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        info1.setForeground(Color.WHITE);
        info1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel info2 = new JLabel("✓ Base de dados completa e atualizada");
        info2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        info2.setForeground(Color.WHITE);
        info2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel info3 = new JLabel("✓ Interface gráfica moderna e intuitiva");
        info3.setFont(new Font("SansSerif", Font.PLAIN, 14));
        info3.setForeground(Color.WHITE);
        info3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel info4 = new JLabel("✓ Sistema de busca avançado");
        info4.setFont(new Font("SansSerif", Font.PLAIN, 14));
        info4.setForeground(Color.WHITE);
        info4.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel versionLabel = new JLabel("Versão 2.0 - 2025");
        versionLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        versionLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GRAY);
        versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(info1);
        panel.add(Box.createVerticalStrut(5));
        panel.add(info2);
        panel.add(Box.createVerticalStrut(5));
        panel.add(info3);
        panel.add(Box.createVerticalStrut(5));
        panel.add(info4);
        panel.add(Box.createVerticalStrut(15));
        panel.add(versionLabel);

        return panel;
    }
}

