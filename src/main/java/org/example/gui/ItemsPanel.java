package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.example.Item;
import org.example.MinecraftWiki;

public class ItemsPanel extends JPanel {
    private final MinecraftWiki wiki;
    private JTextField searchField;
    private JComboBox<String> categoryFilter;
    private JPanel resultsPanel;
    private JScrollPane scrollPane;

    public ItemsPanel(MinecraftWiki wiki) {
        this.wiki = wiki;
        setupUI();
        loadAllItems();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(40, 40, 40));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Painel superior com título e busca
        add(createTopPanel(), BorderLayout.NORTH);

        // Painel central com resultados
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(new Color(40, 40, 40));

        scrollPane = new JScrollPane(resultsPanel);
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

        JLabel categoryLabel = new JLabel("Categoria:");
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        categoryFilter = new JComboBox<>(new String[]{
            "Todas", "Minério", "Lingote", "Gema", "Material",
            "Bloco Natural", "Bloco Construção", "Bloco Decorativo",
            "Ferramenta", "Arma", "Armadura", "Alimento", "Utilitário",
            "Redstone", "Transporte", "Item Especial"
        });
        categoryFilter.setFont(new Font("SansSerif", Font.PLAIN, 14));
        categoryFilter.addActionListener(e -> filterByCategory());

        JButton clearButton = new JButton("Limpar");
        clearButton.setBackground(MinecraftWikiGUI.MINECRAFT_GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(e -> {
            searchField.setText("");
            categoryFilter.setSelectedIndex(0);
            loadAllItems();
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(Box.createHorizontalStrut(20));
        searchPanel.add(categoryLabel);
        searchPanel.add(categoryFilter);
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

    private void performSearch() {
        String searchTerm = searchField.getText().trim();
        if (searchTerm.isEmpty()) {
            loadAllItems();
            return;
        }

        List<Item> results = wiki.buscarItens(searchTerm);
        displayItems(results);
    }

    private void filterByCategory() {
        String category = (String) categoryFilter.getSelectedItem();
        if ("Todas".equals(category)) {
            loadAllItems();
        } else {
            List<Item> results = wiki.buscarItens(category);
            displayItems(results);
        }
    }

    private void loadAllItems() {
        List<Item> items = wiki.listarTodosItens();
        displayItems(items);
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

        resultsPanel.revalidate();
        resultsPanel.repaint();
        scrollPane.getVerticalScrollBar().setValue(0);
    }

    private JPanel createItemCard(Item item) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(new Color(60, 60, 60));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BROWN, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));

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
            JTextArea craftingArea = new JTextArea(item.getPadraoCrafting());
            craftingArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            craftingArea.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
            craftingArea.setBackground(new Color(50, 50, 50));
            craftingArea.setEditable(false);
            craftingArea.setBorder(new EmptyBorder(10, 10, 10, 10));

            JPanel craftingPanel = new JPanel(new BorderLayout());
            craftingPanel.setOpaque(false);

            JLabel craftLabel = new JLabel("🔨 Padrão de Crafting:");
            craftLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            craftLabel.setForeground(MinecraftWikiGUI.MINECRAFT_BROWN);
            craftLabel.setBorder(new EmptyBorder(10, 0, 5, 0));

            craftingPanel.add(craftLabel, BorderLayout.NORTH);
            craftingPanel.add(craftingArea, BorderLayout.CENTER);

            infoPanel.add(craftingPanel);
        }

        card.add(infoPanel, BorderLayout.CENTER);

        return card;
    }
}

