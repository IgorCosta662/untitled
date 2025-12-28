package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.example.Armadura;
import org.example.MinecraftWiki;
import org.example.MoldeFerraria;

public class ArmorPanel extends JPanel {
    private final MinecraftWiki wiki;
    private JPanel contentPanel;

    public ArmorPanel(MinecraftWiki wiki) {
        this.wiki = wiki;
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(40, 40, 40));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Painel superior com título
        add(createTopPanel(), BorderLayout.NORTH);

        // Painel central com conteúdo scrollable
        JScrollPane scrollPane = createContentPanel();
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBackground(new Color(40, 40, 40));

        // Título
        JLabel titleLabel = new JLabel("⚔️ Armaduras por Minérios e Moldes de Ferraria", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Subtítulo
        JLabel subtitleLabel = new JLabel("Organize suas armaduras por material e descubra todos os moldes!", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.LIGHT_GRAY);
        topPanel.add(subtitleLabel, BorderLayout.SOUTH);

        return topPanel;
    }

    private JScrollPane createContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(40, 40, 40));

        // Agrupar armaduras por material
        Map<String, List<Armadura>> armadurasporMaterial = wiki.getArmaduras().stream()
                .collect(Collectors.groupingBy(Armadura::getMaterial));

        // Ordem específica de materiais
        String[] ordemMateriais = {"Couro", "Cota de Malha", "Ferro", "Ouro", "Diamante", "Netherite", "Tartaruga"};

        // Seção 1: Armaduras por Minério/Material
        contentPanel.add(createSectionTitle("🛡️ ARMADURAS POR MINÉRIO/MATERIAL"));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        for (String material : ordemMateriais) {
            if (armadurasporMaterial.containsKey(material)) {
                contentPanel.add(createMaterialSection(material, armadurasporMaterial.get(material)));
                contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
            }
        }

        // Seção 2: Moldes de Ferraria
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(createSectionTitle("🔨 MOLDES DE FERRARIA"));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Moldes de Upgrade
        contentPanel.add(createSubsectionTitle("⚡ Moldes de Upgrade"));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        List<MoldeFerraria> moldesUpgrade = wiki.getMoldesUpgrade();
        for (MoldeFerraria molde : moldesUpgrade) {
            contentPanel.add(createMoldeCard(molde));
            contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Moldes de Aparagem (Trim)
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        contentPanel.add(createSubsectionTitle("✨ Moldes de Aparagem Decorativa (16 Tipos)"));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JPanel trimGrid = new JPanel(new GridLayout(0, 2, 15, 15));
        trimGrid.setBackground(new Color(40, 40, 40));
        trimGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        
        List<MoldeFerraria> moldesTrim = wiki.getMoldesTrim();
        for (MoldeFerraria molde : moldesTrim) {
            trimGrid.add(createMoldeTrimCard(molde));
        }
        contentPanel.add(trimGrid);
        
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        return scrollPane;
    }

    private JLabel createSectionTitle(String title) {
        JLabel label = new JLabel(title);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        label.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JLabel createSubsectionTitle(String title) {
        JLabel label = new JLabel(title);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JPanel createMaterialSection(String material, List<Armadura> armaduras) {
        JPanel section = new JPanel();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBackground(new Color(50, 50, 50));
        section.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(getColorForMaterial(material), 3),
            new EmptyBorder(15, 15, 15, 15)
        ));
        section.setAlignmentX(Component.LEFT_ALIGNMENT);
        section.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2000));

        // Título do material
        JLabel materialLabel = new JLabel("━━━ " + material.toUpperCase() + " ━━━");
        materialLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        materialLabel.setForeground(getColorForMaterial(material));
        materialLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        section.add(materialLabel);

        section.add(Box.createRigidArea(new Dimension(0, 10)));

        // Informações gerais do conjunto
        if (!armaduras.isEmpty()) {
            int defesaTotal = armaduras.stream().mapToInt(Armadura::getDefesa).sum();
            JLabel statsLabel = new JLabel(String.format("🛡️ Defesa Total do Conjunto: %d pontos | 📦 Peças: %d", 
                defesaTotal, armaduras.size()));
            statsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            statsLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
            statsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            section.add(statsLabel);
            section.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        // Grid com as peças
        JPanel armorGrid = new JPanel(new GridLayout(0, 4, 10, 10));
        armorGrid.setBackground(new Color(50, 50, 50));
        armorGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));

        // Ordenar: Capacete, Peitoral, Calças, Botas
        armaduras.sort((a1, a2) -> {
            String[] ordem = {"Capacete", "Peitoral", "Calças", "Botas"};
            int i1 = java.util.Arrays.asList(ordem).indexOf(a1.getTipo());
            int i2 = java.util.Arrays.asList(ordem).indexOf(a2.getTipo());
            return Integer.compare(i1, i2);
        });

        for (Armadura armadura : armaduras) {
            armorGrid.add(createCompactArmorCard(armadura));
        }

        section.add(armorGrid);

        return section;
    }

    private JPanel createCompactArmorCard(Armadura armadura) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(60, 60, 60));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(getColorForMaterial(armadura.getMaterial()), 2),
            new EmptyBorder(10, 10, 10, 10)
        ));

        // Ícone do tipo
        JLabel iconLabel = new JLabel(getIconForType(armadura.getTipo()), SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(iconLabel);

        card.add(Box.createRigidArea(new Dimension(0, 5)));

        // Nome
        JLabel nameLabel = new JLabel(armadura.getTipo());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(nameLabel);

        card.add(Box.createRigidArea(new Dimension(0, 8)));

        // Stats
        JLabel defesaLabel = new JLabel("🛡️ " + armadura.getDefesa());
        defesaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        defesaLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        defesaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(defesaLabel);

        JLabel durabilidadeLabel = new JLabel("💎 " + (int)armadura.getDurabilidade());
        durabilidadeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        durabilidadeLabel.setForeground(MinecraftWikiGUI.MINECRAFT_BLUE);
        durabilidadeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(durabilidadeLabel);

        card.add(Box.createRigidArea(new Dimension(0, 8)));

        // Botão ver detalhes
        JButton detailsButton = new JButton("📋 Detalhes");
        detailsButton.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        detailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsButton.setBackground(new Color(85, 85, 85));
        detailsButton.setForeground(Color.WHITE);
        detailsButton.setFocusPainted(false);
        detailsButton.addActionListener(e -> showArmorRecipe(armadura));
        card.add(detailsButton);

        return card;
    }

    private String getIconForType(String tipo) {
        return switch (tipo) {
            case "Capacete" -> "⛑️";
            case "Peitoral" -> "🦺";
            case "Calças" -> "👖";
            case "Botas" -> "🥾";
            default -> "🛡️";
        };
    }

    private JPanel createMoldeCard(MoldeFerraria molde) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(60, 60, 60));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_PURPLE, 3),
            new EmptyBorder(15, 15, 15, 15)
        ));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));

        // Nome do molde
        JLabel nameLabel = new JLabel("🔨 " + molde.getNome());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setForeground(MinecraftWikiGUI.MINECRAFT_PURPLE);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(nameLabel);

        card.add(Box.createRigidArea(new Dimension(0, 10)));

        // Descrição
        JTextArea descArea = new JTextArea(molde.getDescricao());
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);
        descArea.setBackground(new Color(60, 60, 60));
        descArea.setForeground(Color.LIGHT_GRAY);
        descArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        card.add(descArea);

        card.add(Box.createRigidArea(new Dimension(0, 10)));

        // Botão ver detalhes
        JButton detailsButton = new JButton("📍 Ver Localização e Como Usar");
        detailsButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsButton.setBackground(MinecraftWikiGUI.MINECRAFT_PURPLE.darker());
        detailsButton.setForeground(Color.WHITE);
        detailsButton.setFocusPainted(false);
        detailsButton.addActionListener(e -> showMoldeDetails(molde));
        card.add(detailsButton);

        return card;
    }

    private JPanel createMoldeTrimCard(MoldeFerraria molde) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(60, 60, 60));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 215, 0), 2),
            new EmptyBorder(12, 12, 12, 12)
        ));

        // Nome do molde
        JLabel nameLabel = new JLabel("✨ " + molde.getNome());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setForeground(new Color(255, 215, 0));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(nameLabel);

        card.add(Box.createRigidArea(new Dimension(0, 8)));

        // Descrição
        JTextArea descArea = new JTextArea(molde.getDescricao());
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);
        descArea.setBackground(new Color(60, 60, 60));
        descArea.setForeground(Color.LIGHT_GRAY);
        descArea.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descArea.setRows(2);
        card.add(descArea);

        card.add(Box.createRigidArea(new Dimension(0, 8)));

        // Botão ver detalhes
        JButton detailsButton = new JButton("📍 Localização");
        detailsButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        detailsButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsButton.setBackground(new Color(85, 85, 85));
        detailsButton.setForeground(Color.WHITE);
        detailsButton.setFocusPainted(false);
        detailsButton.addActionListener(e -> showMoldeDetails(molde));
        card.add(detailsButton);

        return card;
    }

    private void showMoldeDetails(MoldeFerraria molde) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), 
                                    molde.getNome(), true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(550, 450);
        dialog.setLocationRelativeTo(this);

        JPanel dialogContent = new JPanel();
        dialogContent.setLayout(new BoxLayout(dialogContent, BoxLayout.Y_AXIS));
        dialogContent.setBackground(new Color(40, 40, 40));
        dialogContent.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titleLabel = new JLabel(molde.getNome(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(molde.isUpgrade() ? MinecraftWikiGUI.MINECRAFT_PURPLE : new Color(255, 215, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogContent.add(titleLabel);

        dialogContent.add(Box.createRigidArea(new Dimension(0, 15)));

        // Descrição
        JLabel descTitle = new JLabel("📝 Descrição:");
        descTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        descTitle.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        descTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        dialogContent.add(descTitle);

        dialogContent.add(Box.createRigidArea(new Dimension(0, 5)));

        JTextArea descArea = new JTextArea(molde.getDescricao());
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);
        descArea.setBackground(new Color(30, 30, 30));
        descArea.setForeground(Color.WHITE);
        descArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        dialogContent.add(descArea);

        dialogContent.add(Box.createRigidArea(new Dimension(0, 15)));

        // Localização
        JLabel locTitle = new JLabel("📍 Onde Encontrar:");
        locTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        locTitle.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        locTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        dialogContent.add(locTitle);

        dialogContent.add(Box.createRigidArea(new Dimension(0, 5)));

        JTextArea locArea = new JTextArea(molde.getLocalizacao());
        locArea.setLineWrap(true);
        locArea.setWrapStyleWord(true);
        locArea.setEditable(false);
        locArea.setBackground(new Color(30, 30, 30));
        locArea.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        locArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        locArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        dialogContent.add(locArea);

        dialogContent.add(Box.createRigidArea(new Dimension(0, 15)));

        // Como usar
        JLabel usoTitle = new JLabel("🔨 Como Usar:");
        usoTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        usoTitle.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        usoTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        dialogContent.add(usoTitle);

        dialogContent.add(Box.createRigidArea(new Dimension(0, 5)));

        JTextArea usoArea = new JTextArea(molde.getUso());
        usoArea.setLineWrap(true);
        usoArea.setWrapStyleWord(true);
        usoArea.setEditable(false);
        usoArea.setBackground(new Color(30, 30, 30));
        usoArea.setForeground(Color.LIGHT_GRAY);
        usoArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        usoArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        dialogContent.add(usoArea);

        JScrollPane scrollPane = new JScrollPane(dialogContent);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Botão fechar
        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(40, 40, 40));
        buttonPanel.add(closeButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private Color getColorForMaterial(String material) {
        return switch (material) {
            case "Couro" -> new Color(139, 90, 43);
            case "Cota de Malha" -> new Color(169, 169, 169);
            case "Ferro" -> new Color(211, 211, 211);
            case "Ouro" -> MinecraftWikiGUI.MINECRAFT_GOLD;
            case "Diamante" -> new Color(0, 255, 255);
            case "Netherite" -> new Color(68, 37, 37);
            case "Tartaruga" -> MinecraftWikiGUI.MINECRAFT_GREEN;
            default -> Color.GRAY;
        };
    }

    private void showArmorRecipe(Armadura armadura) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), 
                                    "Receita: " + armadura.getNome(), true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);

        JPanel dialogContent = new JPanel();
        dialogContent.setLayout(new BoxLayout(dialogContent, BoxLayout.Y_AXIS));
        dialogContent.setBackground(new Color(40, 40, 40));
        dialogContent.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titleLabel = new JLabel("Receita de Crafting", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogContent.add(titleLabel);

        dialogContent.add(Box.createRigidArea(new Dimension(0, 20)));

        // Ingredientes
        JLabel ingredientesLabel = new JLabel("Ingredientes Necessários:");
        ingredientesLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        ingredientesLabel.setForeground(Color.WHITE);
        ingredientesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dialogContent.add(ingredientesLabel);

        dialogContent.add(Box.createRigidArea(new Dimension(0, 10)));

        for (String ingrediente : armadura.getIngredientes()) {
            JLabel ingLabel = new JLabel("  • " + ingrediente);
            ingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            ingLabel.setForeground(Color.LIGHT_GRAY);
            ingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            dialogContent.add(ingLabel);
        }

        dialogContent.add(Box.createRigidArea(new Dimension(0, 20)));

        // Padrão de crafting visual
        JLabel patternLabel = new JLabel("Padrão de Crafting:");
        patternLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        patternLabel.setForeground(Color.WHITE);
        patternLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dialogContent.add(patternLabel);

        dialogContent.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextArea patternArea = new JTextArea(getCraftingPattern(armadura.getTipo()));
        patternArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        patternArea.setBackground(new Color(30, 30, 30));
        patternArea.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        patternArea.setEditable(false);
        patternArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        dialogContent.add(patternArea);

        JScrollPane scrollPane = new JScrollPane(dialogContent);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Botão fechar
        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(40, 40, 40));
        buttonPanel.add(closeButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private String getCraftingPattern(String tipo) {
        return switch (tipo) {
            case "Capacete" -> """
                ┌─────────┐
                │ X   X   X │
                │ X       X │
                │           │
                └─────────┘
                X = Material da armadura
                """;
            case "Peitoral" -> """
                ┌─────────┐
                │ X       X │
                │ X   X   X │
                │ X   X   X │
                └─────────┘
                X = Material da armadura
                """;
            case "Calças" -> """
                ┌─────────┐
                │ X   X   X │
                │ X       X │
                │ X       X │
                └─────────┘
                X = Material da armadura
                """;
            case "Botas" -> """
                ┌─────────┐
                │           │
                │ X       X │
                │ X       X │
                └─────────┘
                X = Material da armadura
                """;
            default -> "Padrão não disponível";
        };
    }
}
