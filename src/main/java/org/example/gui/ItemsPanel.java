package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;

import org.example.Item;
import org.example.Armadura;
import org.example.MoldeFerraria;
import org.example.MinecraftWiki;

public class ItemsPanel extends JPanel {
    private final MinecraftWiki wiki;
    private JTextField searchField;
    private JPanel resultsPanel;
    private JScrollPane scrollPane;
    private JPanel categoryPanel;
    
    // Mapa de categorias principais com subcategorias
    private final Map<String, String[]> categoryHierarchy = new LinkedHashMap<>() {{
        put("⛏️ Mineração", new String[]{"Minério", "Lingote", "Gema", "Material"});
        put("🏗️ Construção", new String[]{"Bloco Natural", "Bloco Construção", "Bloco Decorativo"});
        put("⚔️ Combate", new String[]{"Ferramenta", "Arma"});
        put("🛡️ Armaduras", new String[]{"Couro", "Cota de Malha", "Ferro", "Ouro", "Diamante", "Netherite", "Tartaruga"});
        put("🍖 Sobrevivência", new String[]{"Alimento", "Utilitário"});
        put("⚡ Tecnologia", new String[]{"Redstone", "Transporte"});
        put("✨ Especiais", new String[]{"Item Especial"});
    }};

    public ItemsPanel(MinecraftWiki wiki) {
        this.wiki = wiki;
        setupUI();
        showCategorySelection();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(40, 40, 40));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Painel superior com título e busca
        add(createTopPanel(), BorderLayout.NORTH);

        // Painel de categorias
        categoryPanel = new JPanel();
        categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
        categoryPanel.setBackground(new Color(40, 40, 40));

        // Painel de resultados
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(new Color(40, 40, 40));

        scrollPane = new JScrollPane();
        scrollPane.setBackground(new Color(40, 40, 40));
        scrollPane.setBorder(BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BROWN, 2));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(40, 40, 40));

        // Título
        JLabel titleLabel = new JLabel("📦 ITENS E RECEITAS DE CRAFTING");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_BROWN);

        // Painel de busca e filtros
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(new Color(40, 40, 40));

        JLabel searchLabel = new JLabel("🔍 Buscar:");
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        searchField = new JTextField(30);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.addActionListener(e -> performSearch());

        JButton searchButton = new JButton("Buscar");
        searchButton.setBackground(MinecraftWikiGUI.MINECRAFT_DARK_GREEN);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(e -> performSearch());

        JButton clearButton = new JButton("🏠 Ver Categorias");
        clearButton.setBackground(MinecraftWikiGUI.MINECRAFT_BROWN);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(e -> {
            searchField.setText("");
            showCategorySelection();
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(Box.createHorizontalStrut(20));
        searchPanel.add(clearButton);

        // Painel com título e busca
        JPanel topContainer = new JPanel(new BorderLayout(10, 10));
        topContainer.setBackground(new Color(40, 40, 40));
        topContainer.add(titleLabel, BorderLayout.NORTH);
        topContainer.add(searchPanel, BorderLayout.CENTER);

        panel.add(topContainer, BorderLayout.CENTER);

        // Botão voltar
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
    
    private void showCategorySelection() {
        categoryPanel.removeAll();
        
        // Título da seção
        JLabel sectionTitle = new JLabel("Escolha uma Categoria", SwingConstants.CENTER);
        sectionTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        sectionTitle.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        sectionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        sectionTitle.setBorder(new EmptyBorder(20, 0, 30, 0));
        categoryPanel.add(sectionTitle);
        
        // Grid de categorias principais
        JPanel gridPanel = new JPanel(new GridBagLayout());
        gridPanel.setBackground(new Color(40, 40, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        int row = 0;
        int col = 0;
        
        for (Map.Entry<String, String[]> entry : categoryHierarchy.entrySet()) {
            gbc.gridx = col;
            gbc.gridy = row;
            gridPanel.add(createMainCategoryCard(entry.getKey(), entry.getValue()), gbc);
            
            col++;
            if (col >= 3) { // Grid 3x3 para 7 categorias
                col = 0;
                row++;
            }
        }
        
        categoryPanel.add(gridPanel);
        categoryPanel.add(Box.createVerticalGlue());
        
        scrollPane.setViewportView(categoryPanel);
        scrollPane.revalidate();
        scrollPane.repaint();
    }
    
    private JPanel createMainCategoryCard(String mainCategory, String[] subcategories) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(60, 60, 60));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BROWN, 3),
            new EmptyBorder(20, 20, 20, 20)
        ));
        card.setPreferredSize(new Dimension(350, 250));
        
        // Título da categoria principal
        JLabel titleLabel = new JLabel(mainCategory, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(titleLabel);
        
        card.add(Box.createVerticalStrut(20));
        
        // Linha separadora
        JPanel separator = new JPanel();
        separator.setBackground(MinecraftWikiGUI.MINECRAFT_BROWN);
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        card.add(separator);
        
        card.add(Box.createVerticalStrut(15));
        
        // Subcategorias
        for (String subcategory : subcategories) {
            JButton subButton = new JButton("📦 " + subcategory);
            subButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
            subButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            subButton.setMaximumSize(new Dimension(350, 40));
            subButton.setBackground(new Color(85, 85, 85));
            subButton.setForeground(Color.WHITE);
            subButton.setFocusPainted(false);
            subButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BROWN, 1),
                new EmptyBorder(8, 15, 8, 15)
            ));
            
            // Lista de materiais de armadura
            String[] materiais = {"Couro", "Cota de Malha", "Ferro", "Ouro", "Diamante", "Netherite", "Tartaruga"};
            boolean isMaterialArmadura = false;
            for (String material : materiais) {
                if (subcategory.equals(material)) {
                    isMaterialArmadura = true;
                    break;
                }
            }
            
            if (isMaterialArmadura) {
                // Contar armaduras deste material
                List<Armadura> armadurasDoMaterial = wiki.getArmaduras().stream()
                    .filter(a -> a.getMaterial().equals(subcategory))
                    .collect(Collectors.toList());
                subButton.setText("📦 " + subcategory + " (" + armadurasDoMaterial.size() + ")");
                subButton.addActionListener(e -> displayArmorsByOneMaterial(subcategory));
            } else {
                // Contador de itens
                List<Item> categoryItems = wiki.listarTodosItens().stream()
                    .filter(item -> item.getCategoria().equals(subcategory))
                    .collect(Collectors.toList());
                subButton.setText("📦 " + subcategory + " (" + categoryItems.size() + ")");
                subButton.addActionListener(e -> displayItems(categoryItems));
            }
            
            // Efeito hover
            subButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    subButton.setBackground(MinecraftWikiGUI.MINECRAFT_BROWN);
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    subButton.setBackground(new Color(85, 85, 85));
                }
            });
            
            card.add(subButton);
            card.add(Box.createVerticalStrut(8));
        }
        
        card.add(Box.createVerticalGlue());
        
        return card;
    }

    private void performSearch() {
        String searchTerm = searchField.getText().trim();
        if (searchTerm.isEmpty()) {
            showCategorySelection();
            return;
        }

        List<Item> results = wiki.buscarItens(searchTerm);
        displayItems(results);
    }

    private void displayItems(List<Item> items) {
        resultsPanel.removeAll();

        if (items.isEmpty()) {
            JLabel noResults = new JLabel("❌ Nenhum item encontrado");
            noResults.setFont(new Font("SansSerif", Font.BOLD, 18));
            noResults.setForeground(MinecraftWikiGUI.MINECRAFT_RED);
            noResults.setAlignmentX(Component.CENTER_ALIGNMENT);
            resultsPanel.add(Box.createVerticalStrut(50));
            resultsPanel.add(noResults);
        } else {
            JLabel countLabel = new JLabel("✅ " + items.size() + " item(ns) encontrado(s)");
            countLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            countLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
            countLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            countLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
            resultsPanel.add(countLabel);

            for (Item item : items) {
                resultsPanel.add(createItemCard(item));
                resultsPanel.add(Box.createVerticalStrut(10));
            }
        }

        scrollPane.setViewportView(resultsPanel);
        resultsPanel.revalidate();
        resultsPanel.repaint();
        scrollPane.getVerticalScrollBar().setValue(0);
    }

    private void displayArmorsByOneMaterial(String material) {
        resultsPanel.removeAll();
        
        JLabel titleLabel = new JLabel(getEmojiForMaterial(material) + " ARMADURA DE " + material.toUpperCase(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(getColorForMaterial(material));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        resultsPanel.add(titleLabel);
        
        List<Armadura> armadurasDoMaterial = wiki.getArmaduras().stream()
            .filter(a -> a.getMaterial().equals(material))
            .collect(Collectors.toList());
        
        if (!armadurasDoMaterial.isEmpty()) {
            resultsPanel.add(createMaterialArmorCard(material, armadurasDoMaterial));
        }
        
        scrollPane.setViewportView(resultsPanel);
        resultsPanel.revalidate();
        resultsPanel.repaint();
        scrollPane.getVerticalScrollBar().setValue(0);
    }
    
    private JPanel createMaterialArmorCard(String material, List<Armadura> armaduras) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(60, 60, 60));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(getColorForMaterial(material), 3),
            new EmptyBorder(20, 20, 20, 20)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 600));
        
        // Título do material
        JLabel materialLabel = new JLabel(getEmojiForMaterial(material) + " ARMADURA DE " + material.toUpperCase());
        materialLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        materialLabel.setForeground(getColorForMaterial(material));
        materialLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(materialLabel);
        
        card.add(Box.createVerticalStrut(15));
        
        // Painel de peças da armadura
        JPanel piecesPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        piecesPanel.setOpaque(false);
        piecesPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        
        String[] tipos = {"Capacete", "Peitoral", "Calças", "Botas"};
        for (String tipo : tipos) {
            Armadura peca = armaduras.stream()
                .filter(a -> a.getTipo().equals(tipo))
                .findFirst()
                .orElse(null);
            
            if (peca != null) {
                piecesPanel.add(createArmorPieceCard(peca));
            }
        }
        
        card.add(piecesPanel);
        
        // Se for Netherite, adicionar informações sobre o molde
        if (material.equals("Netherite")) {
            card.add(Box.createVerticalStrut(15));
            card.add(createMoldeInfo());
        }
        
        // Adicionar botão para ver moldes de ferraria (trims)
        card.add(Box.createVerticalStrut(15));
        JButton trimButton = new JButton("✨ Ver Moldes de Ferraria (Enfeites)");
        trimButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        trimButton.setBackground(MinecraftWikiGUI.MINECRAFT_PURPLE);
        trimButton.setForeground(Color.WHITE);
        trimButton.setFocusPainted(false);
        trimButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        trimButton.addActionListener(e -> showAllTrims());
        card.add(trimButton);
        
        return card;
    }
    
    private JPanel createArmorPieceCard(Armadura armadura) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(50, 50, 50));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BROWN, 2),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        // Imagem da peça (se disponível)
        if (armadura.getImagemPath() != null && !armadura.getImagemPath().isEmpty()) {
            JLabel imageLabel = createImageLabel(armadura.getImagemPath(), 64, 64);
            if (imageLabel != null) {
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(imageLabel);
                card.add(Box.createVerticalStrut(10));
            }
        }
        
        // Nome da peça
        JLabel nameLabel = new JLabel(getTipoEmoji(armadura.getTipo()) + " " + armadura.getTipo());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        nameLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(nameLabel);
        
        card.add(Box.createVerticalStrut(5));
        
        // Defesa
        JLabel defenseLabel = new JLabel("🛡️ " + armadura.getDefesaCompleta());
        defenseLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        defenseLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        defenseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(defenseLabel);
        
        // Durabilidade
        JLabel durabilityLabel = new JLabel("🔧 " + armadura.getDurabilidadeCompleta());
        durabilityLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        durabilityLabel.setForeground(MinecraftWikiGUI.MINECRAFT_BLUE);
        durabilityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(durabilityLabel);
        
        card.add(Box.createVerticalStrut(10));
        
        // Botão de detalhes
        JButton detailsButton = new JButton("📖 Ver Receita");
        detailsButton.setFont(new Font("SansSerif", Font.PLAIN, 11));
        detailsButton.setBackground(MinecraftWikiGUI.MINECRAFT_BROWN);
        detailsButton.setForeground(Color.WHITE);
        detailsButton.setFocusPainted(false);
        detailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsButton.addActionListener(e -> showArmorDetails(armadura));
        card.add(detailsButton);
        
        return card;
    }
    
    private JPanel createMoldeInfo() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        
        JLabel upgradeLabel = new JLabel("⬆️ UPGRADE DE NETHERITE");
        upgradeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        upgradeLabel.setForeground(new Color(88, 36, 36));
        upgradeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(upgradeLabel);
        
        panel.add(Box.createVerticalStrut(5));
        
        JTextArea infoArea = new JTextArea(
            "Para fazer armadura de Netherite, você precisa:\n" +
            "1⃣ Armadura de Diamante\n" +
            "2⃣ Molde de Melhoria de Netherite\n" +
            "3⃣ Lingote de Netherite\n" +
            "4⃣ Mesa de Ferraria\n\n" +
            "✨ O molde é encontrado em Baús do Bastiao em Ruínas no Nether!"
        );
        infoArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        infoArea.setForeground(Color.LIGHT_GRAY);
        infoArea.setBackground(new Color(50, 50, 50));
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        infoArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(infoArea);
        
        return panel;
    }
    
    private void showArmorDetails(Armadura armadura) {
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setBackground(new Color(40, 40, 40));
        dialogPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel(armadura.getNome());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.add(titleLabel);
        
        dialogPanel.add(Box.createVerticalStrut(15));
        
        JTextArea descArea = new JTextArea(armadura.getDescricao());
        descArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descArea.setForeground(Color.WHITE);
        descArea.setBackground(new Color(40, 40, 40));
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.add(descArea);
        
        dialogPanel.add(Box.createVerticalStrut(15));
        
        JLabel ingLabel = new JLabel("📝 Ingredientes:");
        ingLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        ingLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        ingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dialogPanel.add(ingLabel);
        
        for (String ing : armadura.getIngredientes()) {
            JLabel ingItem = new JLabel("  • " + ing);
            ingItem.setFont(new Font("SansSerif", Font.PLAIN, 14));
            ingItem.setForeground(Color.WHITE);
            ingItem.setAlignmentX(Component.LEFT_ALIGNMENT);
            dialogPanel.add(ingItem);
        }
        
        javax.swing.JOptionPane.showMessageDialog(
            this,
            dialogPanel,
            "Detalhes da Armadura",
            javax.swing.JOptionPane.PLAIN_MESSAGE
        );
    }
    
    private void showAllTrims() {
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setBackground(new Color(40, 40, 40));
        dialogPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("✨ MOLDES DE FERRARIA (ENFEITES)");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_PURPLE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.add(titleLabel);
        
        dialogPanel.add(Box.createVerticalStrut(10));
        
        JTextArea infoArea = new JTextArea(
            "Os Moldes de Ferraria permitem adicionar padrões decorativos às armaduras!\n" +
            "Existem " + wiki.getMoldesTrim().size() + " moldes de enfeite disponíveis.\n\n" +
            "Para usar: Mesa de Ferraria + Molde + Armadura + Material decorativo"
        );
        infoArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoArea.setForeground(Color.WHITE);
        infoArea.setBackground(new Color(40, 40, 40));
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.add(infoArea);
        
        dialogPanel.add(Box.createVerticalStrut(20));
        
        JLabel listLabel = new JLabel("📜 Lista de Moldes:");
        listLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        listLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        listLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dialogPanel.add(listLabel);
        
        dialogPanel.add(Box.createVerticalStrut(10));
        
        for (MoldeFerraria molde : wiki.getMoldesTrim()) {
            JButton moldeButton = new JButton("• " + molde.getNome());
            moldeButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
            moldeButton.setBackground(new Color(60, 60, 60));
            moldeButton.setForeground(Color.WHITE);
            moldeButton.setFocusPainted(false);
            moldeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            moldeButton.setMaximumSize(new Dimension(400, 30));
            moldeButton.addActionListener(e -> showTrimDetails(molde));
            dialogPanel.add(moldeButton);
            dialogPanel.add(Box.createVerticalStrut(5));
        }
        
        JScrollPane scrollPane = new JScrollPane(dialogPanel);
        scrollPane.setPreferredSize(new Dimension(500, 600));
        
        javax.swing.JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "Moldes de Ferraria",
            javax.swing.JOptionPane.PLAIN_MESSAGE
        );
    }
    
    private void showTrimDetails(MoldeFerraria molde) {
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setBackground(new Color(40, 40, 40));
        dialogPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel(molde.getNome());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_PURPLE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.add(titleLabel);
        
        dialogPanel.add(Box.createVerticalStrut(15));
        
        JTextArea descArea = new JTextArea(molde.getDescricao());
        descArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descArea.setForeground(Color.WHITE);
        descArea.setBackground(new Color(40, 40, 40));
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        dialogPanel.add(descArea);
        
        dialogPanel.add(Box.createVerticalStrut(15));
        
        JLabel locLabel = new JLabel("📍 Localização:");
        locLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        locLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        locLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dialogPanel.add(locLabel);
        
        dialogPanel.add(Box.createVerticalStrut(5));
        
        JTextArea locArea = new JTextArea(molde.getLocalizacao());
        locArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
        locArea.setForeground(Color.LIGHT_GRAY);
        locArea.setBackground(new Color(50, 50, 50));
        locArea.setEditable(false);
        locArea.setLineWrap(true);
        locArea.setWrapStyleWord(true);
        locArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        dialogPanel.add(locArea);
        
        dialogPanel.add(Box.createVerticalStrut(15));
        
        JLabel usoLabel = new JLabel("🔨 Como Usar:");
        usoLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        usoLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        usoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dialogPanel.add(usoLabel);
        
        dialogPanel.add(Box.createVerticalStrut(5));
        
        JTextArea usoArea = new JTextArea(molde.getUso());
        usoArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
        usoArea.setForeground(Color.LIGHT_GRAY);
        usoArea.setBackground(new Color(50, 50, 50));
        usoArea.setEditable(false);
        usoArea.setLineWrap(true);
        usoArea.setWrapStyleWord(true);
        usoArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        dialogPanel.add(usoArea);
        
        JScrollPane scrollPane = new JScrollPane(dialogPanel);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        
        javax.swing.JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "Detalhes do Molde",
            javax.swing.JOptionPane.PLAIN_MESSAGE
        );
    }
    
    private Color getColorForMaterial(String material) {
        return switch (material) {
            case "Couro" -> new Color(139, 69, 19);
            case "Cota de Malha" -> new Color(192, 192, 192);
            case "Ferro" -> Color.LIGHT_GRAY;
            case "Ouro" -> MinecraftWikiGUI.MINECRAFT_GOLD;
            case "Diamante" -> new Color(0, 191, 255);
            case "Netherite" -> new Color(88, 36, 36);
            case "Tartaruga" -> new Color(34, 139, 34);
            default -> Color.WHITE;
        };
    }
    
    private String getEmojiForMaterial(String material) {
        return switch (material) {
            case "Couro" -> "🐮";
            case "Cota de Malha" -> "⛓️";
            case "Ferro" -> "⚒️";
            case "Ouro" -> "💎";
            case "Diamante" -> "💎";
            case "Netherite" -> "🔥";
            case "Tartaruga" -> "🐢";
            default -> "⚔️";
        };
    }
    
    private String getTipoEmoji(String tipo) {
        return switch (tipo) {
            case "Capacete" -> "🪦";
            case "Peitoral" -> "🦺";
            case "Calças" -> "👖";
            case "Botas" -> "🥾";
            default -> "⚔️";
        };
    }

    private JLabel createImageLabel(String imagePath, int width, int height) {
        try {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                ImageIcon icon = new ImageIcon(imagePath);
                Image img = icon.getImage();
                Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImg);
                
                JLabel label = new JLabel(scaledIcon);
                label.setBorder(BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BROWN, 2));
                return label;
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + imagePath + " - " + e.getMessage());
        }
        return null;
    }

    private JPanel createItemCard(Item item) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(new Color(60, 60, 60));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BROWN, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));

        // Imagem do item (lado esquerdo)
        if (item.getImagemItem() != null && !item.getImagemItem().isEmpty()) {
            JLabel imageLabel = createImageLabel(item.getImagemItem(), 64, 64);
            if (imageLabel != null) {
                JPanel imagePanel = new JPanel();
                imagePanel.setOpaque(false);
                imagePanel.add(imageLabel);
                card.add(imagePanel, BorderLayout.WEST);
            }
        }

        // Painel de informações
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel nameLabel = new JLabel("⛏️ " + item.getNome());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        nameLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);

        JLabel categoryLabel = new JLabel("Categoria: " + item.getCategoria());
        categoryLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        categoryLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GRAY);

        JLabel editionLabel = new JLabel("Edição: " + item.getEdicao().getDisplayName());
        editionLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        editionLabel.setForeground(MinecraftWikiGUI.MINECRAFT_BLUE);

        JTextArea descArea = new JTextArea(item.getDescricao());
        descArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descArea.setForeground(Color.WHITE);
        descArea.setBackground(new Color(60, 60, 60));
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);
        descArea.setBorder(new EmptyBorder(10, 0, 10, 0));

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(categoryLabel);
        infoPanel.add(editionLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(descArea);

        // Ingredientes
        if (!item.getIngredientes().isEmpty()) {
            JLabel ingLabel = new JLabel("📋 Ingredientes:");
            ingLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            ingLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
            infoPanel.add(ingLabel);

            for (String ing : item.getIngredientes()) {
                JLabel ingItem = new JLabel("  • " + ing);
                ingItem.setFont(new Font("SansSerif", Font.PLAIN, 12));
                ingItem.setForeground(Color.WHITE);
                infoPanel.add(ingItem);
            }
        }

        // Padrão de crafting
        if (item.getPadraoCrafting() != null && !item.getPadraoCrafting().isEmpty()) {
            JPanel craftingPanel = new JPanel(new BorderLayout(10, 10));
            craftingPanel.setOpaque(false);

            JLabel craftLabel = new JLabel("🔨 Padrão de Crafting:");
            craftLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            craftLabel.setForeground(MinecraftWikiGUI.MINECRAFT_BROWN);
            craftLabel.setBorder(new EmptyBorder(10, 0, 5, 0));
            craftingPanel.add(craftLabel, BorderLayout.NORTH);

            // Container para texto e imagem
            JPanel craftingContent = new JPanel(new BorderLayout(10, 10));
            craftingContent.setOpaque(false);

            // Texto do padrão
            JTextArea craftingArea = new JTextArea(item.getPadraoCrafting());
            craftingArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            craftingArea.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
            craftingArea.setBackground(new Color(50, 50, 50));
            craftingArea.setEditable(false);
            craftingArea.setBorder(new EmptyBorder(10, 10, 10, 10));
            craftingContent.add(craftingArea, BorderLayout.CENTER);

            // Imagem da receita (se disponível)
            if (item.getImagemCrafting() != null && !item.getImagemCrafting().isEmpty()) {
                JLabel craftingImageLabel = createImageLabel(item.getImagemCrafting(), 150, 150);
                if (craftingImageLabel != null) {
                    JPanel imagePanel = new JPanel();
                    imagePanel.setOpaque(false);
                    imagePanel.add(craftingImageLabel);
                    craftingContent.add(imagePanel, BorderLayout.EAST);
                }
            }

            craftingPanel.add(craftingContent, BorderLayout.CENTER);
            infoPanel.add(craftingPanel);
        }

        card.add(infoPanel, BorderLayout.CENTER);

        return card;
    }
}

