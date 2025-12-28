package org.example.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CraftingSimulatorPanel extends JPanel {
    private JButton[][] craftingGrid;
    private JLabel resultLabel;
    private Map<String, String> recipes;

    public CraftingSimulatorPanel() {
        setupUI();
        loadRecipes();
    }

    private void setupUI() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(40, 40, 40));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createTopPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("🔨 SIMULADOR DE CRAFTING INTERATIVO");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_BROWN);

        JLabel instructionLabel = new JLabel("Clique nos slots para selecionar materiais e ver o resultado!");
        instructionLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        instructionLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GRAY);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setBackground(new Color(40, 40, 40));
        labelPanel.add(titleLabel);
        labelPanel.add(Box.createVerticalStrut(10));
        labelPanel.add(instructionLabel);

        panel.add(labelPanel, BorderLayout.CENTER);

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

    private JPanel createCenterPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // Painel da mesa de crafting
        JPanel craftingPanel = createCraftingTable();
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(craftingPanel, gbc);

        // Seta
        JLabel arrowLabel = new JLabel("→");
        arrowLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        arrowLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        gbc.gridx = 1;
        mainPanel.add(arrowLabel, gbc);

        // Painel do resultado
        JPanel resultPanel = createResultPanel();
        gbc.gridx = 2;
        mainPanel.add(resultPanel, gbc);

        // Painel de materiais disponíveis
        JPanel materialsPanel = createMaterialsPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(materialsPanel, gbc);

        return mainPanel;
    }

    private JPanel createCraftingTable() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(50, 50, 50));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BROWN, 3),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel label = new JLabel("Mesa de Crafting 3x3");
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        gridPanel.setBackground(new Color(50, 50, 50));
        craftingGrid = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton slot = new JButton("");
                slot.setPreferredSize(new Dimension(80, 80));
                slot.setBackground(new Color(100, 100, 100));
                slot.setFont(new Font("Dialog", Font.PLAIN, 32));
                slot.setFocusPainted(false);
                slot.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

                int row = i;
                int col = j;
                slot.addActionListener(e -> showMaterialSelector(row, col));

                craftingGrid[i][j] = slot;
                gridPanel.add(slot);
            }
        }

        JButton clearButton = new JButton("🗑️ Limpar Tudo");
        clearButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        clearButton.setBackground(MinecraftWikiGUI.MINECRAFT_RED);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(e -> clearGrid());

        panel.add(label, BorderLayout.NORTH);
        panel.add(gridPanel, BorderLayout.CENTER);
        panel.add(clearButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(50, 50, 50));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_GREEN, 3),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel label = new JLabel("Resultado");
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        resultLabel = new JLabel("?");
        resultLabel.setPreferredSize(new Dimension(80, 80));
        resultLabel.setFont(new Font("Dialog", Font.PLAIN, 48));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.CENTER);
        resultLabel.setOpaque(true);
        resultLabel.setBackground(new Color(100, 100, 100));
        resultLabel.setBorder(BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_GREEN, 2));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(resultLabel);

        panel.add(label, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createMaterialsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(50, 50, 50));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BLUE, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel label = new JLabel("📦 Materiais Disponíveis (Clique para selecionar)");
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(MinecraftWikiGUI.MINECRAFT_BLUE);

        JPanel materialsGrid = new JPanel(new GridLayout(2, 8, 10, 10));
        materialsGrid.setBackground(new Color(50, 50, 50));

        String[] materials = {
            "🪵", "⛏️", "💎", "🪙", "🪨", "⚙️", "🔥", "💧",
            "🌾", "🧱", "🪜", "🧪", "📖", "🔱", "⚔️", "🛡️"
        };

        String[] materialNames = {
            "Madeira", "Picareta", "Diamante", "Ouro", "Pedra", "Ferro", "Fogo", "Água",
            "Trigo", "Tijolo", "Escada", "Poção", "Livro", "Tridente", "Espada", "Escudo"
        };

        for (int i = 0; i < materials.length; i++) {
            JButton materialBtn = new JButton(materials[i]);
            materialBtn.setFont(new Font("Dialog", Font.PLAIN, 24));
            materialBtn.setToolTipText(materialNames[i]);
            materialBtn.setBackground(new Color(80, 80, 80));
            materialBtn.setForeground(Color.WHITE);
            materialBtn.setFocusPainted(false);
            materialsGrid.add(materialBtn);
        }

        panel.add(label, BorderLayout.NORTH);
        panel.add(materialsGrid, BorderLayout.CENTER);

        return panel;
    }

    private void showMaterialSelector(int row, int col) {
        String[] materials = {
            "Vazio", "🪵 Madeira", "⛏️ Picareta", "💎 Diamante", "🪙 Ouro",
            "🪨 Pedra", "⚙️ Ferro", "🔥 Fogo", "💧 Água", "🌾 Trigo",
            "🧱 Tijolo", "🪜 Escada", "🧪 Poção", "📖 Livro",
            "🔱 Tridente", "⚔️ Espada", "🛡️ Escudo"
        };

        String selected = (String) JOptionPane.showInputDialog(
            this,
            "Selecione um material:",
            "Material",
            JOptionPane.PLAIN_MESSAGE,
            null,
            materials,
            materials[0]
        );

        if (selected != null) {
            if (selected.equals("Vazio")) {
                craftingGrid[row][col].setText("");
            } else {
                craftingGrid[row][col].setText(selected.substring(0, 2));
            }
            checkRecipe();
        }
    }

    private void clearGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                craftingGrid[i][j].setText("");
            }
        }
        resultLabel.setText("?");
    }

    private void checkRecipe() {
        StringBuilder pattern = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String text = craftingGrid[i][j].getText();
                pattern.append(text.isEmpty() ? "_" : text);
            }
        }

        String result = recipes.getOrDefault(pattern.toString(), "?");
        resultLabel.setText(result);

        if (!"?".equals(result)) {
            resultLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        } else {
            resultLabel.setForeground(Color.WHITE);
        }
    }

    private void loadRecipes() {
        recipes = new HashMap<>();

        // Mesa de trabalho: 4 madeiras
        recipes.put("_🪵🪵_🪵🪵___", "🔨");
        recipes.put("🪵🪵__🪵🪵___", "🔨");

        // Picareta de madeira
        recipes.put("🪵🪵🪵_🪵__🪵_", "⛏️");

        // Espada de diamante
        recipes.put("_💎__💎__🪵_", "⚔️");

        // Pão: 3 trigos
        recipes.put("___🌾🌾🌾___", "🍞");
        recipes.put("🌾🌾🌾______", "🍞");

        // Adicione mais receitas conforme necessário
    }
}

