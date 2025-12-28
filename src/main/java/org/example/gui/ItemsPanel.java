package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.example.MinecraftWiki;
import org.example.MinecraftWiki.Armadura;
import org.example.MinecraftWiki.Item;
import org.example.MinecraftWiki.MoldeFerraria;
import org.example.MinecraftWiki.Pocao;

/**
 * Painel consolidado de ITENS: Itens Gerais + Encantamentos + Poções + Efeitos + Crafting + Fornalha + Ferraria + Redstone
 */
public class ItemsPanel extends JPanel {
    private final MinecraftWiki wiki;
    private final MinecraftWikiGUI parent;
    private JTextField searchField;
    private JPanel resultsPanel;
    private JScrollPane scrollPane;
    private JPanel categoryPanel;
    private JPanel itemsGeneralPanel;
    
    // Mapa de categorias principais com subcategorias - Sistema completo e atualizado
    private final Map<String, String[]> categoryHierarchy = new LinkedHashMap<>() {{
        // Categoria 1: Blocos (7 subcategorias)
        put("🧱 Blocos", new String[]{"Bloco Construção", "Bloco Decorativo", "Bloco Funcional", "Bloco Natural", "Bloco Especial", "Bloco de Armazenamento", "Bloco Utilitário"});
        
        // Categoria 2: Ferramentas (7 materiais + especiais)
        put("⛏️ Ferramentas", new String[]{"Madeira", "Pedra", "Ferro", "Ouro", "Diamante", "Netherite", "Ferramentas Especiais"});
        
        // Categoria 3: Armas (5 subcategorias)
        put("⚔️ Armas", new String[]{"Espada", "Arco", "Besta", "Arma", "Arma de Longo Alcance"});
        
        // Categoria 4: Armaduras (7 tipos de materiais + moldes)
        put("🛡️ Armaduras", new String[]{"Couro", "Cota de Malha", "Ferro", "Ouro", "Diamante", "Netherite", "Tartaruga", "Moldes de Ferraria"});
        
        // Categoria 5: Alimentos (1 subcategoria)
        put("🍖 Alimentos", new String[]{"Alimento"});
        
        // Categoria 6: Poções e Alquimia (1 subcategoria)
        put("🧪 Poções e Alquimia", new String[]{"Poção"});
        
        // Categoria 7: Materiais de Crafting (4 subcategorias)
        put("🔨 Materiais de Crafting", new String[]{"Material", "Minério", "Lingote", "Gema"});
        
        // Categoria 8: Redstone e Mecanismos (1 subcategoria)
        put("⚡ Redstone e Mecanismos", new String[]{"Redstone"});
        
        // Categoria 9: Exploração e Transporte (1 subcategoria)
        put("🚂 Exploração e Transporte", new String[]{"Transporte"});
        
        // Categoria 10: Itens Especiais e Utilitários (2 subcategorias)
        put("✨ Itens Especiais", new String[]{"Item Especial", "Utilitário"});
    }};

    public ItemsPanel(MinecraftWiki wiki, MinecraftWikiGUI parent) {
        this.wiki = wiki;
        this.parent = parent;
        setupUI();
    }
    
    private void setupUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 40, 40));

        // Criar abas para organizar todo o conteúdo
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setBackground(new Color(40, 40, 40));
        tabbedPane.setForeground(Color.WHITE);

        // Criar painel de Itens Gerais (o conteúdo original do ItemsPanel)
        itemsGeneralPanel = createItemsGeneralPanel();
        
        // Adicionar apenas a aba de Itens Gerais (outras abas removidas temporariamente)
        tabbedPane.addTab("[ITEMS] Itens Gerais", itemsGeneralPanel);

        add(tabbedPane, BorderLayout.CENTER);

        // Botão voltar
        JButton backButton = createBackButton();
        add(backButton, BorderLayout.SOUTH);
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("← Voltar ao Menu");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backButton.setBackground(new Color(60, 60, 60));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(80, 80, 80));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(60, 60, 60));
            }
        });
        
        backButton.addActionListener(e -> parent.showPanel("HOME"));
        
        return backButton;
    }

    private JPanel createItemsGeneralPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(40, 40, 40));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Painel superior com título e busca
        panel.add(createTopPanel(), BorderLayout.NORTH);

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

        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Inicializar com seleção de categorias
        showCategorySelection();
        
        return panel;
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(new Color(30, 30, 35));
        panel.setBorder(new EmptyBorder(15, 20, 15, 20));

        // Painel de cabeçalho com gradiente visual
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(45, 45, 50), 0, getHeight(), new Color(30, 30, 35));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        headerPanel.setLayout(new BorderLayout(10, 10));
        headerPanel.setOpaque(false);
        headerPanel.setBorder(new EmptyBorder(20, 25, 20, 25));

        // Título moderno com efeito
        JLabel titleLabel = new JLabel("[BIBLIOTECA] BIBLIOTECA DE ITENS");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(255, 200, 100));
        
        JLabel subtitleLabel = new JLabel("Explore todos os itens e receitas do Minecraft");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(180, 180, 190));
        
        JPanel titleContainer = new JPanel(new GridLayout(2, 1, 0, 5));
        titleContainer.setOpaque(false);
        titleContainer.add(titleLabel);
        titleContainer.add(subtitleLabel);
        
        headerPanel.add(titleContainer, BorderLayout.CENTER);

        // Painel de busca modernizado
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 5));
        searchPanel.setOpaque(false);

        JLabel searchLabel = new JLabel("🔍");
        searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        searchLabel.setForeground(new Color(200, 200, 210));

        searchField = new JTextField(35);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        searchField.setBackground(new Color(50, 50, 55));
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 90), 2, true),
            new EmptyBorder(8, 12, 8, 12)
        ));
        searchField.addActionListener(e -> performSearch());
        
        // Efeito de foco no campo de busca
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                searchField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_GOLD, 2, true),
                    new EmptyBorder(8, 12, 8, 12)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                searchField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(80, 80, 90), 2, true),
                    new EmptyBorder(8, 12, 8, 12)
                ));
            }
        });
        
        // Adicionar sugestões em tempo real
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { showSuggestions(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { showSuggestions(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { showSuggestions(); }
            
            private void showSuggestions() {
                String text = searchField.getText().trim();
                if (text.length() >= 2) {
                    // Buscar correspondências
                    List<String> suggestions = wiki.listarTodosItens().stream()
                        .map(Item::getNome)
                        .filter(nome -> nome.toLowerCase().contains(text.toLowerCase()))
                        .limit(5)
                        .collect(Collectors.toList());
                    
                    if (!suggestions.isEmpty() && !suggestions.get(0).equalsIgnoreCase(text)) {
                        searchField.setToolTipText("Sugestões: " + String.join(", ", suggestions));
                    } else {
                        searchField.setToolTipText(null);
                    }
                }
            }
        });

        JButton searchButton = createModernButton("🔍 Buscar", new Color(34, 139, 34), new Color(46, 184, 46));
        searchButton.setPreferredSize(new Dimension(120, 36));
        searchButton.addActionListener(e -> performSearch());

        JButton clearButton = createModernButton("🏠 Categorias", new Color(139, 69, 19), new Color(184, 92, 25));
        clearButton.setPreferredSize(new Dimension(140, 36));
        clearButton.addActionListener(e -> {
            searchField.setText("");
            showCategorySelection();
        });

        JButton allItemsBtn = createModernButton("📋 Todos", new Color(41, 128, 185), new Color(52, 152, 219));
        allItemsBtn.setPreferredSize(new Dimension(110, 36));
        allItemsBtn.addActionListener(e -> showAllItems());

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);
        searchPanel.add(allItemsBtn);

        headerPanel.add(searchPanel, BorderLayout.SOUTH);
        panel.add(headerPanel, BorderLayout.CENTER);

        // Botão voltar estilizado
        JButton backButton = createModernButton("← Voltar ao Menu", new Color(60, 60, 70), new Color(80, 80, 90));
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setPreferredSize(new Dimension(160, 40));
        backButton.addActionListener(e -> {
            MinecraftWikiGUI gui = (MinecraftWikiGUI) SwingUtilities.getWindowAncestor(this);
            gui.showPanel("HOME");
        });
        panel.add(backButton, BorderLayout.WEST);

        return panel;
    }
    
    // Método para criar botões modernos com hover effect
    private JButton createModernButton(String text, Color bgColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new EmptyBorder(8, 16, 8, 16));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }

    private void showCategorySelection() {
        categoryPanel.removeAll();
        
        // Título da seção
        JLabel sectionTitle = new JLabel("[CATEGORIAS] SISTEMA DE CATEGORIAS DE ITENS", SwingConstants.CENTER);
        sectionTitle.setFont(new Font("SansSerif", Font.BOLD, 28));
        sectionTitle.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        sectionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        sectionTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
        categoryPanel.add(sectionTitle);
        
        // Subtítulo
        JLabel subTitle = new JLabel("Selecione uma categoria para explorar", SwingConstants.CENTER);
        subTitle.setFont(new Font("SansSerif", Font.ITALIC, 14));
        subTitle.setForeground(Color.LIGHT_GRAY);
        subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subTitle.setBorder(new EmptyBorder(0, 0, 20, 0));
        categoryPanel.add(subTitle);
        
        // Botões de ações especiais
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        actionsPanel.setBackground(new Color(40, 40, 40));
        actionsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        // Botão VER TODOS OS ITENS
        JButton allItemsButton = new JButton("📋 VER TODOS OS ITENS", ImageManager.getItemIcon("DIAMOND", 16));
        allItemsButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        allItemsButton.setBackground(MinecraftWikiGUI.MINECRAFT_BLUE);
        allItemsButton.setForeground(Color.WHITE);
        allItemsButton.setFocusPainted(false);
        allItemsButton.setBorderPainted(false);
        allItemsButton.setPreferredSize(new Dimension(250, 40));
        allItemsButton.addActionListener(e -> showAllItems());
        allItemsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                allItemsButton.setBackground(new Color(100, 150, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                allItemsButton.setBackground(MinecraftWikiGUI.MINECRAFT_BLUE);
            }
        });
        actionsPanel.add(allItemsButton);
        
        // Botão de Comparar Armaduras
        JButton compareButton = new JButton("Comparar Armaduras", ImageManager.getItemIcon("CHESTPLATE", 16));
        compareButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        compareButton.setBackground(MinecraftWikiGUI.MINECRAFT_BROWN);
        compareButton.setForeground(Color.WHITE);
        compareButton.setFocusPainted(false);
        compareButton.setBorderPainted(false);
        compareButton.setPreferredSize(new Dimension(250, 40));
        compareButton.addActionListener(e -> showArmorComparison());
        compareButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                compareButton.setBackground(MinecraftWikiGUI.MINECRAFT_GOLD);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                compareButton.setBackground(MinecraftWikiGUI.MINECRAFT_BROWN);
            }
        });
        actionsPanel.add(compareButton);
        
        // Botão de Armaduras com Moldes
        JButton trimsButton = new JButton("Armaduras com Moldes", ImageManager.getItemIcon("GOLD_INGOT", 16));
        trimsButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        trimsButton.setBackground(MinecraftWikiGUI.MINECRAFT_PURPLE);
        trimsButton.setForeground(Color.WHITE);
        trimsButton.setFocusPainted(false);
        trimsButton.setBorderPainted(false);
        trimsButton.setPreferredSize(new Dimension(250, 40));
        trimsButton.addActionListener(e -> showArmorTrims());
        trimsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                trimsButton.setBackground(new Color(170, 100, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                trimsButton.setBackground(MinecraftWikiGUI.MINECRAFT_PURPLE);
            }
        });
        actionsPanel.add(trimsButton);
        
        categoryPanel.add(actionsPanel);
        categoryPanel.add(Box.createVerticalStrut(25));
        
        // Grid de categorias principais com layout moderno (2 colunas)
        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 25, 25));
        gridPanel.setBackground(new Color(40, 40, 40));
        gridPanel.setBorder(new EmptyBorder(10, 30, 30, 30));
        
        for (Map.Entry<String, String[]> entry : categoryHierarchy.entrySet()) {
            gridPanel.add(createModernCategoryCard(entry.getKey(), entry.getValue()));
        }
        
        categoryPanel.add(gridPanel);
        categoryPanel.add(Box.createVerticalGlue());
        
        scrollPane.setViewportView(categoryPanel);
        scrollPane.revalidate();
        scrollPane.repaint();
    }
    
    private JPanel createModernCategoryCard(String mainCategory, String[] subcategories) {
        JPanel card = new JPanel() {
            private boolean hover = false;
            
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fundo com gradiente
                Color topColor = hover ? new Color(60, 60, 70) : new Color(50, 50, 60);
                Color bottomColor = new Color(40, 40, 48);
                GradientPaint gp = new GradientPaint(0, 0, topColor, 0, getHeight(), bottomColor);
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                // Borda com cor da categoria
                g2d.setColor(getCategoryColor(mainCategory));
                g2d.setStroke(new java.awt.BasicStroke(3));
                g2d.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 20, 20);
                
                // Barra superior colorida
                g2d.fillRoundRect(0, 0, getWidth(), 8, 20, 20);
            }
            
            {
                addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        hover = true;
                        repaint();
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        hover = false;
                        repaint();
                    }
                });
            }
        };
        
        card.setLayout(new BorderLayout(10, 10));
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));
        card.setPreferredSize(new Dimension(400, Math.min(350, 180 + (subcategories.length * 38))));
        
        // Painel de cabeçalho
        JPanel headerPanel = new JPanel(new BorderLayout(10, 5));
        headerPanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel(mainCategory);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(getCategoryColor(mainCategory));
        
        JLabel countLabel = new JLabel(subcategories.length + " tipos");
        countLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        countLabel.setForeground(new Color(150, 150, 160));
        
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(countLabel, BorderLayout.SOUTH);
        
        card.add(headerPanel, BorderLayout.NORTH);
        
        // Painel de subcategorias com scroll
        JPanel subcategoriesPanel = new JPanel();
        subcategoriesPanel.setLayout(new BoxLayout(subcategoriesPanel, BoxLayout.Y_AXIS));
        subcategoriesPanel.setOpaque(false);
        
        for (String subcategory : subcategories) {
            subcategoriesPanel.add(createSubcategoryButton(subcategory, mainCategory));
            subcategoriesPanel.add(Box.createVerticalStrut(8));
        }
        
        JScrollPane subScroll = new JScrollPane(subcategoriesPanel);
        subScroll.setOpaque(false);
        subScroll.getViewport().setOpaque(false);
        subScroll.setBorder(null);
        subScroll.getVerticalScrollBar().setUnitIncrement(12);
        
        card.add(subScroll, BorderLayout.CENTER);
        
        return card;
    }
    
    private JButton createSubcategoryButton(String subcategory, String mainCategory) {
        JButton button = new JButton(getSubcategoryIcon(subcategory) + " " + subcategory) {
            private boolean hover = false;
            
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (hover) {
                    g2d.setColor(getCategoryColor(mainCategory));
                } else {
                    g2d.setColor(new Color(60, 60, 70));
                }
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                
                super.paintComponent(g);
            }
            
            {
                addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        hover = true;
                        repaint();
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        hover = false;
                        repaint();
                    }
                });
            }
        };
        
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(new EmptyBorder(8, 15, 8, 15));
        
        configureSubcategoryAction(button, subcategory, mainCategory);
        
        return button;
    }
    
    private void configureSubcategoryAction(JButton button, String subcategory, String mainCategory) {
        // Verificar contexto baseado na categoria principal
        boolean isArmorCategory = mainCategory.contains("Armaduras");
        boolean isToolCategory = mainCategory.contains("Ferramentas");
        
        // Verificar se é armadura por material (apenas se estiver na categoria Armaduras)
        String[] materiais = {"Couro", "Cota de Malha", "Ferro", "Ouro", "Diamante", "Netherite", "Tartaruga"};
        boolean isMaterialArmadura = false;
        if (isArmorCategory) {
            for (String material : materiais) {
                if (subcategory.equals(material)) {
                    isMaterialArmadura = true;
                    break;
                }
            }
        }
        
        // Verificar se é ferramenta por material (apenas se estiver na categoria Ferramentas)
        String[] materiaisFerramentas = {"Madeira", "Pedra", "Ferro", "Ouro", "Diamante", "Netherite"};
        boolean isMaterialFerramenta = false;
        if (isToolCategory) {
            for (String material : materiaisFerramentas) {
                if (subcategory.equals(material)) {
                    isMaterialFerramenta = true;
                    break;
                }
            }
        }
        
        if (isMaterialArmadura && isArmorCategory) {
            List<Armadura> armadurasDoMaterial = wiki.getArmaduras().stream()
                .filter(a -> a.getMaterial().equals(subcategory))
                .collect(Collectors.toList());
            button.setText(getSubcategoryIcon(subcategory) + " " + subcategory + " (" + armadurasDoMaterial.size() + ")");
            button.addActionListener(e -> displayArmorsByOneMaterial(subcategory));
        } else if (isMaterialFerramenta && isToolCategory) {
            // Buscar ferramentas por material (picareta, machado, pá, enxada)
            List<Item> ferramentasDoMaterial = wiki.listarTodosItens().stream()
                .filter(item -> {
                    String nome = item.getNome().toLowerCase();
                    String materialLower = subcategory.toLowerCase();
                    return item.getCategoria().equals("Ferramenta") && 
                           (nome.contains(materialLower) || 
                            (materialLower.equals("madeira") && nome.contains("madeira")) ||
                            (materialLower.equals("pedra") && nome.contains("pedra")) ||
                            (materialLower.equals("ferro") && nome.contains("ferro")) ||
                            (materialLower.equals("ouro") && nome.contains("ouro")) ||
                            (materialLower.equals("diamante") && nome.contains("diamante")) ||
                            (materialLower.equals("netherite") && nome.contains("netherite")));
                })
                .collect(Collectors.toList());
            button.setText(getSubcategoryIcon(subcategory) + " " + subcategory + " (" + ferramentasDoMaterial.size() + ")");
            button.addActionListener(e -> displayToolsByMaterial(subcategory, ferramentasDoMaterial));
        } else if (subcategory.equals("Ferramentas Especiais")) {
            List<Item> ferramentasEspeciais = wiki.listarTodosItens().stream()
                .filter(item -> item.getCategoria().equals("Ferramenta Especial"))
                .collect(Collectors.toList());
            button.setText(getSubcategoryIcon(subcategory) + " " + subcategory + " (" + ferramentasEspeciais.size() + ")");
            button.addActionListener(e -> displayItems(ferramentasEspeciais));
        } else if (subcategory.equals("Moldes de Ferraria")) {
            List<MoldeFerraria> moldes = wiki.getMoldesFerraria();
            button.setText(getSubcategoryIcon(subcategory) + " " + subcategory + " (" + moldes.size() + ")");
            button.addActionListener(e -> displayMoldesFerraria());
        } else if (subcategory.equals("Minério")) {
            List<Item> minerios = wiki.listarTodosItens().stream()
                .filter(item -> item.getCategoria().equals("Minério"))
                .collect(Collectors.toList());
            button.setText(getSubcategoryIcon(subcategory) + " " + subcategory + " (" + minerios.size() + ")");
            button.addActionListener(e -> displayItems(minerios));
        } else if (subcategory.equals("Poção")) {
            // Poções são uma classe separada, não Item
            List<Pocao> todasPocoes = wiki.listarTodasPocoes();
            button.setText(getSubcategoryIcon(subcategory) + " " + subcategory + " (" + todasPocoes.size() + ")");
            button.addActionListener(e -> displayPotions(todasPocoes));
        } else {
            // Buscar itens por categoria
            List<Item> categoryItems = wiki.listarTodosItens().stream()
                .filter(item -> item.getCategoria().equals(subcategory))
                .collect(Collectors.toList());
            button.setText(getSubcategoryIcon(subcategory) + " " + subcategory + " (" + categoryItems.size() + ")");
            button.addActionListener(e -> displayItems(categoryItems));
        }
    }
    
    private Color getCategoryColor(String category) {
        return switch (category) {
            case "🧱 Blocos" -> new Color(183, 110, 58);  // Marrom terroso vibrante
            case "⛏️ Ferramentas" -> new Color(169, 169, 169);  // Prata brilhante
            case "⚔️ Armas" -> new Color(220, 50, 47);  // Vermelho intenso
            case "🛡️ Armaduras" -> new Color(65, 140, 230);  // Azul royal
            case "🍖 Alimentos" -> new Color(255, 159, 64);  // Laranja apetitoso
            case "🧪 Poções e Alquimia" -> new Color(155, 89, 182);  // Roxo místico
            case "🔨 Materiais de Crafting" -> new Color(241, 196, 15);  // Dourado brilhante
            case "⚡ Redstone e Mecanismos" -> new Color(231, 76, 60);  // Vermelho redstone
            case "🚂 Exploração e Transporte" -> new Color(52, 152, 219);  // Azul celeste
            case "✨ Itens Especiais" -> new Color(142, 68, 173);  // Roxo épico
            // Fallback para categorias antigas
            case "Blocos" -> new Color(183, 110, 58);
            case "Ferramentas" -> new Color(169, 169, 169);
            case "Armas" -> new Color(220, 50, 47);
            case "Armaduras" -> new Color(65, 140, 230);
            case "Alimentos" -> new Color(255, 159, 64);
            case "Poções e Alquimia" -> new Color(155, 89, 182);
            case "Itens de Crafting" -> new Color(241, 196, 15);
            case "Redstone e Mecanismos" -> new Color(231, 76, 60);
            case "Agricultura e Natureza" -> new Color(46, 204, 113);
            case "Exploração e Transporte" -> new Color(52, 152, 219);
            case "Armazenamento" -> new Color(211, 84, 0);
            case "Encantamentos e Magia" -> new Color(155, 89, 182);
            case "Dimensões Especiais" -> new Color(142, 68, 173);
            case "Itens Diversos" -> new Color(52, 152, 219);
            case "Itens Técnicos" -> new Color(149, 165, 166);
            default -> new Color(241, 196, 15);  // Dourado padrão
        };
    }
    
    private String getSubcategoryIcon(String subcategory) {
        return switch (subcategory) {
            // Ferramentas por material
            case "Madeira" -> "🪵";
            case "Pedra" -> "🪨";
            case "Ferramentas Especiais" -> "🔧";
            // Tipos de ferramentas individuais
            case "Picareta" -> "⛏️";
            case "Machado" -> "🪓";
            case "Pá" -> "🥄";
            case "Enxada" -> "🔱";
            case "Espada" -> "⚔️";
            case "Arco" -> "🏹";
            case "Besta" -> "🎯";
            // Armaduras por material
            case "Couro" -> "🟤";
            case "Cota de Malha" -> "⛓️";
            case "Ferro" -> "⚙️";
            case "Ouro" -> "💛";
            case "Diamante" -> "💎";
            case "Netherite" -> "🔥";
            case "Tartaruga" -> "🐢";
            case "Moldes de Ferraria" -> "✨";
            // Outros
            case "Semente" -> "🌱";
            case "Planta" -> "🌿";
            case "Árvore" -> "🌳";
            case "Barco" -> "⛵";
            case "Carrinho" -> "🚂";
            case "Mapa" -> "🗺️";
            case "Baú" -> "[BAU]";
            case "Livro Encantado" -> "📖";
            case "Poção Normal", "Poção Arremessável", "Poção Persistente" -> "🧪";
            case "Minério" -> "💎";
            case "Lingote" -> "🔩";
            case "Gema" -> "💠";
            case "Comida Crua", "Comida Cozida" -> "🍖";
            case "Alimento Raro" -> "🍯";
            default -> "[ITEM]";
        };
    }
    
    /**
     * Cria um JLabel com ícone do item
     */
    private JLabel createItemLabel(String itemName, int iconSize) {
        JLabel label = new JLabel(itemName);
        
        // Tentar obter ícone do ImageManager
        String iconKey = getIconKeyForItem(itemName);
        if (iconKey != null) {
            ImageIcon icon = ImageManager.getItemIcon(iconKey, iconSize);
            if (icon != null) {
                label.setIcon(icon);
                label.setIconTextGap(8);
            }
        }
        
        return label;
    }
    
    /**
     * Mapeia nome do item para chave de ícone do ImageManager
     */
    private String getIconKeyForItem(String itemName) {
        String nameLower = itemName.toLowerCase();
        
        // Ferramentas
        if (nameLower.contains("picareta") || nameLower.contains("pickaxe")) return "PICKAXE";
        if (nameLower.contains("machado") || nameLower.contains("axe") || nameLower.contains("enxó")) return "AXE";
        if (nameLower.contains("pá") || nameLower.contains("shovel")) return "SHOVEL";
        if (nameLower.contains("enxada") || nameLower.contains("hoe")) return "HOE";
        
        // Armas
        if (nameLower.contains("espada") || nameLower.contains("sword")) return "SWORD";
        if (nameLower.contains("arco") || nameLower.contains("bow")) return "BOW";
        if (nameLower.contains("besta") || nameLower.contains("crossbow")) return "CROSSBOW";
        if (nameLower.contains("tridente") || nameLower.contains("trident")) return "TRIDENT";
        
        // Armaduras
        if (nameLower.contains("capacete") || nameLower.contains("helmet")) return "HELMET";
        if (nameLower.contains("peitoral") || nameLower.contains("chestplate")) return "CHESTPLATE";
        if (nameLower.contains("calça") || nameLower.contains("leggings")) return "LEGGINGS";
        if (nameLower.contains("bota") || nameLower.contains("boots")) return "BOOTS";
        if (nameLower.contains("escudo") || nameLower.contains("shield")) return "SHIELD";
        
        // Materiais
        if (nameLower.contains("diamante") || nameLower.contains("diamond")) return "DIAMOND";
        if (nameLower.contains("esmeralda") || nameLower.contains("emerald")) return "EMERALD";
        if (nameLower.contains("ouro") || nameLower.contains("gold")) return "GOLD_INGOT";
        if (nameLower.contains("ferro") || nameLower.contains("iron")) return "IRON_INGOT";
        if (nameLower.contains("netherite")) return "NETHERITE";
        if (nameLower.contains("carvão") || nameLower.contains("coal")) return "COAL";
        if (nameLower.contains("redstone")) return "REDSTONE";
        
        // Poções
        if (nameLower.contains("poção") || nameLower.contains("potion")) return "POTION_HEALING";
        if (nameLower.contains("suporte") || nameLower.contains("brewing")) return "BREWING_STAND";
        
        // Encantamentos
        if (nameLower.contains("livro encantado") || nameLower.contains("enchanted book")) return "ENCHANTED_BOOK";
        if (nameLower.contains("mesa de encantamento") || nameLower.contains("enchanting table")) return "ENCHANTING_TABLE";
        if (nameLower.contains("bigorna") || nameLower.contains("anvil")) return "ANVIL";
        
        // Crafting
        if (nameLower.contains("bancada") || nameLower.contains("crafting table")) return "CRAFTING_TABLE";
        if (nameLower.contains("fornalha") || nameLower.contains("furnace")) return "FURNACE";
        if (nameLower.contains("alto-forno") || nameLower.contains("blast furnace")) return "BLAST_FURNACE";
        if (nameLower.contains("mesa de ferraria") || nameLower.contains("smithing")) return "SMITHING_TABLE";
        
        return null;
    }
    
    /**
     * Mapeia categorias para nomes de ícones
     */
    private String getCategoryIconName(String categoryKey) {
        if (categoryKey.contains("Ferramentas")) return "PICKAXE";
        if (categoryKey.contains("Armas")) return "SWORD";
        if (categoryKey.contains("Armaduras")) return "ARMOR";
        if (categoryKey.contains("Crafting")) return "CRAFTING_TABLE";
        if (categoryKey.contains("Encantamentos")) return "ENCHANTED_BOOK";
        if (categoryKey.contains("Poções")) return "BREWING";
        return null;
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

    private void showAllItems() {
        resultsPanel.removeAll();
        
        // Botão de voltar
        JButton backButton = new JButton("← Voltar para Categorias");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(new Color(80, 80, 80));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(250, 35));
        backButton.addActionListener(e -> showCategorySelection());
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(MinecraftWikiGUI.MINECRAFT_BROWN);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(80, 80, 80));
            }
        });
        
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(new Color(40, 40, 40));
        backPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        backPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        backPanel.add(backButton);
        resultsPanel.add(backPanel);
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(40, 40, 40));
        headerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        
        JLabel titleLabel = new JLabel("📋 TODOS OS ITENS DO MINECRAFT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(titleLabel);
        
        headerPanel.add(Box.createVerticalStrut(10));
        
        List<Item> allItems = wiki.listarTodosItens();
        JLabel countLabel = new JLabel("[OK] " + allItems.size() + " itens cadastrados no total", SwingConstants.CENTER);
        countLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        countLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(countLabel);
        
        JLabel tipLabel = new JLabel("💡 Dica: Use Ctrl+F para buscar um item específico", SwingConstants.CENTER);
        tipLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        tipLabel.setForeground(Color.LIGHT_GRAY);
        tipLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(tipLabel);
        
        resultsPanel.add(headerPanel);
        resultsPanel.add(Box.createVerticalStrut(10));
        
        // Adicionar todos os itens
        for (Item item : allItems) {
            resultsPanel.add(createItemCard(item));
            resultsPanel.add(Box.createVerticalStrut(10));
        }
        
        scrollPane.setViewportView(resultsPanel);
        resultsPanel.revalidate();
        resultsPanel.repaint();
        scrollPane.getVerticalScrollBar().setValue(0);
    }

    private void displayItems(List<Item> items) {
        resultsPanel.removeAll();

        if (items.isEmpty()) {
            JLabel noResults = new JLabel("[!] Nenhum item encontrado");
            noResults.setFont(new Font("SansSerif", Font.BOLD, 18));
            noResults.setForeground(MinecraftWikiGUI.MINECRAFT_RED);
            noResults.setAlignmentX(Component.CENTER_ALIGNMENT);
            resultsPanel.add(Box.createVerticalStrut(50));
            resultsPanel.add(noResults);
        } else {
            JLabel countLabel = new JLabel("[OK] " + items.size() + " item(ns) encontrado(s)");
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

    private void displayPotions(List<Pocao> potions) {
        resultsPanel.removeAll();

        if (potions.isEmpty()) {
            JLabel noResults = new JLabel("[!] Nenhuma poção encontrada");
            noResults.setFont(new Font("SansSerif", Font.BOLD, 18));
            noResults.setForeground(MinecraftWikiGUI.MINECRAFT_RED);
            noResults.setAlignmentX(Component.CENTER_ALIGNMENT);
            resultsPanel.add(Box.createVerticalStrut(50));
            resultsPanel.add(noResults);
        } else {
            JLabel countLabel = new JLabel("🧪 " + potions.size() + " poção(ões) encontrada(s)");
            countLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            countLabel.setForeground(new Color(155, 89, 182)); // Roxo místico
            countLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            countLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
            resultsPanel.add(countLabel);

            for (Pocao potion : potions) {
                resultsPanel.add(createPotionCard(potion));
                resultsPanel.add(Box.createVerticalStrut(10));
            }
        }

        scrollPane.setViewportView(resultsPanel);
        resultsPanel.revalidate();
        resultsPanel.repaint();
        scrollPane.getVerticalScrollBar().setValue(0);
    }

    private JPanel createPotionCard(Pocao potion) {
        JPanel outerCard = new JPanel();
        outerCard.setLayout(new BoxLayout(outerCard, BoxLayout.Y_AXIS));
        outerCard.setOpaque(false);
        outerCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 500));
        
        // Card principal compacto
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fundo com gradiente roxo
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(155, 89, 182, 30),
                    0, getHeight(), new Color(142, 68, 173, 50)
                );
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                // Borda roxa
                g2d.setColor(new Color(155, 89, 182));
                g2d.setStroke(new java.awt.BasicStroke(2));
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                
                g2d.dispose();
            }
        };
        
        card.setLayout(new BorderLayout(15, 10));
        card.setBorder(new EmptyBorder(15, 20, 15, 20));
        card.setOpaque(false);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));

        // Painel esquerdo com ícone
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(80, 120));
        
        // Tentar carregar ícone da poção do ImageManager
        ImageIcon potionIcon = getPotionIcon(potion.getNome());
        if (potionIcon != null) {
            JLabel iconLabel = new JLabel(potionIcon);
            iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
            leftPanel.add(iconLabel, BorderLayout.CENTER);
        } else {
            // Usar emoji como fallback
            JLabel iconLabel = new JLabel(getPotionEmoji(potion.getNome()));
            iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
            iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
            leftPanel.add(iconLabel, BorderLayout.CENTER);
        }

        // Painel central com informações principais
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        // Nome da poção
        JLabel nameLabel = new JLabel(potion.getNome());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setForeground(new Color(155, 89, 182));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Efeito
        JLabel effectLabel = new JLabel("⚡ " + potion.getEfeito());
        effectLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        effectLabel.setForeground(new Color(80, 80, 80));
        effectLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Duração
        JLabel durationLabel = new JLabel("⏱️ " + potion.getDuracao());
        durationLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        durationLabel.setForeground(new Color(0, 150, 136));
        durationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Edição
        JLabel editionLabel = new JLabel("🎮 " + potion.getEdicao().getDisplayName());
        editionLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        editionLabel.setForeground(new Color(150, 150, 150));
        editionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        centerPanel.add(nameLabel);
        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(effectLabel);
        centerPanel.add(Box.createVerticalStrut(3));
        centerPanel.add(durationLabel);
        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(editionLabel);

        // Painel direito com ingredientes
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        rightPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
        rightPanel.setPreferredSize(new Dimension(250, 120));

        if (!potion.getIngredientes().isEmpty()) {
            JLabel ingLabel = new JLabel("[ING] Ingredientes:");
            ingLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
            ingLabel.setForeground(new Color(100, 100, 100));
            ingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            rightPanel.add(ingLabel);
            rightPanel.add(Box.createVerticalStrut(5));

            for (String ingrediente : potion.getIngredientes()) {
                JLabel ingItem = new JLabel("• " + ingrediente);
                ingItem.setFont(new Font("Segoe UI", Font.PLAIN, 11));
                ingItem.setForeground(new Color(120, 120, 120));
                ingItem.setAlignmentX(Component.LEFT_ALIGNMENT);
                rightPanel.add(ingItem);
            }
        }

        card.add(leftPanel, BorderLayout.WEST);
        card.add(centerPanel, BorderLayout.CENTER);
        if (!potion.getIngredientes().isEmpty()) {
            card.add(rightPanel, BorderLayout.EAST);
        }

        // Botão "Ver Como Fazer"
        JButton detailsButton = new JButton("🔍 Ver Como Fazer");
        detailsButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
        detailsButton.setBackground(new Color(155, 89, 182));
        detailsButton.setForeground(Color.WHITE);
        detailsButton.setFocusPainted(false);
        detailsButton.setBorderPainted(false);
        detailsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        detailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsButton.setMaximumSize(new Dimension(150, 30));
        
        // Painel expansível com instruções
        JPanel detailsPanel = createPotionDetailsPanel(potion);
        detailsPanel.setVisible(false);
        
        detailsButton.addActionListener(e -> {
            boolean isVisible = detailsPanel.isVisible();
            detailsPanel.setVisible(!isVisible);
            detailsButton.setText(isVisible ? "🔍 Ver Como Fazer" : "▲ Ocultar Detalhes");
            outerCard.revalidate();
            outerCard.repaint();
        });
        
        detailsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                detailsButton.setBackground(new Color(142, 68, 173));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                detailsButton.setBackground(new Color(155, 89, 182));
            }
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        buttonPanel.add(detailsButton);
        
        outerCard.add(card);
        outerCard.add(buttonPanel);
        outerCard.add(detailsPanel);
        
        return outerCard;
    }

    private JPanel createPotionDetailsPanel(Pocao potion) {
        JPanel detailsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fundo com gradiente mais escuro
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(50, 50, 60),
                    0, getHeight(), new Color(40, 40, 50)
                );
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                
                // Borda
                g2d.setColor(new Color(155, 89, 182, 100));
                g2d.setStroke(new java.awt.BasicStroke(1));
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
                
                g2d.dispose();
            }
        };
        
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        detailsPanel.setOpaque(false);
        detailsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        
        // Título da seção
        JLabel howToTitle = new JLabel("⚗️ COMO PREPARAR NO SUPORTE DE POÇÕES");
        howToTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        howToTitle.setForeground(new Color(155, 89, 182));
        howToTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(howToTitle);
        detailsPanel.add(Box.createVerticalStrut(10));
        
        // Poção base
        if (potion.getBasePotion() != null && !potion.getBasePotion().isEmpty()) {
            JLabel baseLabel = new JLabel("1️⃣ Base: " + potion.getBasePotion());
            baseLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            baseLabel.setForeground(new Color(200, 200, 200));
            baseLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            detailsPanel.add(baseLabel);
            detailsPanel.add(Box.createVerticalStrut(5));
        }
        
        // Passos de preparo baseados no tipo de poção
        String[] steps = getBrewingSteps(potion);
        int stepNumber = potion.getBasePotion() != null ? 2 : 1;
        
        for (String step : steps) {
            JLabel stepLabel = new JLabel(getNumberEmoji(stepNumber) + " " + step);
            stepLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            stepLabel.setForeground(new Color(200, 200, 200));
            stepLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            detailsPanel.add(stepLabel);
            detailsPanel.add(Box.createVerticalStrut(5));
            stepNumber++;
        }
        
        // Dicas especiais
        detailsPanel.add(Box.createVerticalStrut(10));
        JLabel tipsTitle = new JLabel("💡 DICAS:");
        tipsTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        tipsTitle.setForeground(new Color(241, 196, 15));
        tipsTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(tipsTitle);
        detailsPanel.add(Box.createVerticalStrut(5));
        
        JLabel tip1 = new JLabel("• Use Pó de Pedra Luminosa para aumentar a potência (Nível II)");
        tip1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        tip1.setForeground(new Color(180, 180, 180));
        tip1.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(tip1);
        
        JLabel tip2 = new JLabel("• Use Pó de Redstone para aumentar a duração");
        tip2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        tip2.setForeground(new Color(180, 180, 180));
        tip2.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(tip2);
        
        JLabel tip3 = new JLabel("• Adicione Pólvora para criar versão arremessável (Splash)");
        tip3.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        tip3.setForeground(new Color(180, 180, 180));
        tip3.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(tip3);
        
        JLabel tip4 = new JLabel("• Adicione Bafo de Dragão à splash para criar versão persistente (Lingering)");
        tip4.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        tip4.setForeground(new Color(180, 180, 180));
        tip4.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(tip4);
        
        return detailsPanel;
    }

    private String[] getBrewingSteps(Pocao potion) {
        String nome = potion.getNome().toLowerCase();
        
        // Poções arremessáveis
        if (nome.contains("arremessável") || nome.contains("splash")) {
            return new String[]{
                "Prepare a poção normal primeiro",
                "Adicione Pólvora ao suporte de poções",
                "Aguarde a conversão para versão arremessável"
            };
        }
        
        // Poções persistentes
        if (nome.contains("persistente") || nome.contains("lingering")) {
            return new String[]{
                "Prepare a poção arremessável primeiro",
                "Adicione Bafo de Dragão ao suporte",
                "Aguarde a conversão para versão persistente"
            };
        }
        
        // Poções estendidas
        if (nome.contains("estendida")) {
            return new String[]{
                "Prepare a poção básica primeiro",
                "Adicione Pó de Redstone ao suporte",
                "Aguarde o aumento da duração"
            };
        }
        
        // Poções nível II
        if (nome.contains(" II")) {
            return new String[]{
                "Prepare a poção nível I primeiro",
                "Adicione Pó de Pedra Luminosa ao suporte",
                "Aguarde o aumento da potência"
            };
        }
        
        // Poções especiais
        if (nome.contains("Invisibilidade")) {
            return new String[]{
                "Prepare Poção de Visão Noturna",
                "Adicione Olho de Aranha Fermentado",
                "Aguarde a transformação"
            };
        }
        
        if (nome.contains("Fraqueza")) {
            return new String[]{
                "Coloque Garrafas de Água no suporte",
                "Adicione Olho de Aranha Fermentado direto (sem Verruga)",
                "Aguarde a preparação"
            };
        }
        
        if (nome.contains("Lentidão")) {
            return new String[]{
                "Prepare Poção de Velocidade ou Salto",
                "Adicione Olho de Aranha Fermentado",
                "Aguarde a transformação negativa"
            };
        }
        
        // Poções padrão
        return new String[]{
            "Coloque Garrafas de Água no suporte de poções",
            "Adicione Verruga do Nether para criar Poção Estranha",
            "Adicione o ingrediente principal conforme a lista acima",
            "Aguarde o processo de fermentação (20 segundos)"
        };
    }

    private String getNumberEmoji(int number) {
        return switch (number) {
            case 1 -> "1️⃣";
            case 2 -> "2️⃣";
            case 3 -> "3️⃣";
            case 4 -> "4️⃣";
            case 5 -> "5️⃣";
            case 6 -> "6️⃣";
            default -> "▪️";
        };
    }

    private String getPotionEmoji(String potionName) {
        String nome = potionName.toLowerCase();
        if (nome.contains("cura") || nome.contains("healing")) return "❤️";
        if (nome.contains("força") || nome.contains("strength")) return "💪";
        if (nome.contains("velocidade") || nome.contains("speed") || nome.contains("swiftness")) return "⚡";
        if (nome.contains("regeneração") || nome.contains("regeneration")) return "💚";
        if (nome.contains("fogo") || nome.contains("fire")) return "🔥";
        if (nome.contains("visão") || nome.contains("vision") || nome.contains("night")) return "👁️";
        if (nome.contains("invisibilidade") || nome.contains("invisibility")) return "👻";
        if (nome.contains("salto") || nome.contains("jump") || nome.contains("leaping")) return "🦘";
        if (nome.contains("veneno") || nome.contains("poison")) return "☠️";
        if (nome.contains("fraqueza") || nome.contains("weakness")) return "😰";
        if (nome.contains("lentidão") || nome.contains("slowness")) return "🐌";
        if (nome.contains("dano") || nome.contains("harming") || nome.contains("damage")) return "💀";
        if (nome.contains("respiração") || nome.contains("water") || nome.contains("breathing")) return "🌊";
        if (nome.contains("queda") || nome.contains("falling") || nome.contains("slow fall")) return "🪂";
        if (nome.contains("tartaruga") || nome.contains("turtle")) return "🐢";
        if (nome.contains("arremessável") || nome.contains("splash")) return "💣";
        if (nome.contains("persistente") || nome.contains("lingering")) return "☁️";
        return "🧪";
    }

    private ImageIcon getPotionIcon(String potionName) {
        // Mapear nome da poção para identificador do ImageManager
        String imageName = null;
        String nome = potionName.toLowerCase();
        
        if (nome.contains("cura") || nome.contains("healing")) {
            imageName = "POTION_HEALING";
        } else if (nome.contains("força") || nome.contains("strength")) {
            imageName = "POTION_STRENGTH";
        } else if (nome.contains("velocidade") || nome.contains("speed")) {
            imageName = "POTION_SPEED";
        }
        
        if (imageName != null) {
            try {
                ImageIcon icon = ImageManager.getItemIcon(imageName, 64);
                if (icon != null && icon.getIconWidth() > 0) {
                    return icon;
                }
            } catch (Exception e) {
                // Ignora erro e usa fallback de emoji
            }
        }
        
        return null; // Retorna null para usar emoji como fallback
    }

    private void displayArmorsByOneMaterial(String material) {
        resultsPanel.removeAll();
        
        // Botão de voltar
        JButton backButton = new JButton("← Voltar para Armaduras");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(new Color(80, 80, 80));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(250, 35));
        backButton.addActionListener(e -> showCategorySelection());
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(MinecraftWikiGUI.MINECRAFT_BROWN);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(80, 80, 80));
            }
        });
        
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(new Color(40, 40, 40));
        backPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        backPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        backPanel.add(backButton);
        resultsPanel.add(backPanel);
        
        // Header melhorado
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(40, 40, 40));
        headerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        
        JLabel titleLabel = new JLabel(getEmojiForMaterial(material) + " ARMADURA DE " + material.toUpperCase(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(getColorForMaterial(material));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(titleLabel);
        
        headerPanel.add(Box.createVerticalStrut(10));
        
        // Descrição do conjunto
        String descricao = getArmorSetDescription(material);
        JLabel descLabel = new JLabel("<html><center>" + descricao + "</center></html>", SwingConstants.CENTER);
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descLabel.setForeground(Color.LIGHT_GRAY);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(descLabel);
        
        resultsPanel.add(headerPanel);
        
        List<Armadura> armadurasDoMaterial = wiki.getArmaduras().stream()
            .filter(a -> a.getMaterial().equals(material))
            .sorted((a1, a2) -> {
                String[] ordem = {"Capacete", "Peitoral", "Calças", "Botas"};
                return Integer.compare(
                    java.util.Arrays.asList(ordem).indexOf(a1.getTipo()),
                    java.util.Arrays.asList(ordem).indexOf(a2.getTipo())
                );
            })
            .collect(Collectors.toList());
        
        if (!armadurasDoMaterial.isEmpty()) {
            // Painel de estatísticas do conjunto
            resultsPanel.add(createArmorSetStats(armadurasDoMaterial, material));
            
            resultsPanel.add(Box.createVerticalStrut(20));
            
            // Cards das peças individuais
            resultsPanel.add(createImprovedArmorPiecesPanel(armadurasDoMaterial, material));
            
            resultsPanel.add(Box.createVerticalStrut(20));
            
            // Informações adicionais
            resultsPanel.add(createArmorExtraInfo(material));
        }
        
        scrollPane.setViewportView(resultsPanel);
        resultsPanel.revalidate();
        resultsPanel.repaint();
        scrollPane.getVerticalScrollBar().setValue(0);
    }
    
    private void displayToolsByMaterial(String material, List<Item> ferramentas) {
        resultsPanel.removeAll();
        
        // Botão de voltar
        JButton backButton = new JButton("← Voltar para Ferramentas");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(new Color(80, 80, 80));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(250, 35));
        backButton.addActionListener(e -> showCategorySelection());
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(169, 169, 169));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(80, 80, 80));
            }
        });
        
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(new Color(40, 40, 40));
        backPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        backPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        backPanel.add(backButton);
        resultsPanel.add(backPanel);
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(40, 40, 40));
        headerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel(getSubcategoryIcon(material) + " FERRAMENTAS DE " + material.toUpperCase(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        titleLabel.setForeground(new Color(169, 169, 169));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel descLabel = new JLabel(getToolSetDescription(material), SwingConstants.CENTER);
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descLabel.setForeground(new Color(180, 180, 180));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(descLabel);
        
        resultsPanel.add(headerPanel);
        resultsPanel.add(Box.createVerticalStrut(15));
        
        // Cards das ferramentas
        if (!ferramentas.isEmpty()) {
            for (Item ferramenta : ferramentas) {
                resultsPanel.add(createItemCard(ferramenta));
                resultsPanel.add(Box.createVerticalStrut(10));
            }
        } else {
            JLabel noItemsLabel = new JLabel("Nenhuma ferramenta encontrada para " + material);
            noItemsLabel.setForeground(Color.LIGHT_GRAY);
            noItemsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            resultsPanel.add(noItemsLabel);
        }
        
        scrollPane.setViewportView(resultsPanel);
        resultsPanel.revalidate();
        resultsPanel.repaint();
        scrollPane.getVerticalScrollBar().setValue(0);
    }
    
    private String getToolSetDescription(String material) {
        return switch (material) {
            case "Madeira" -> "Ferramentas básicas para começar sua aventura • Durabilidade: 59 usos";
            case "Pedra" -> "Melhoria significativa sobre madeira • Durabilidade: 131 usos";
            case "Ferro" -> "Ferramentas confiáveis para o meio do jogo • Durabilidade: 250 usos";
            case "Ouro" -> "Rápidas mas frágeis! Alta encantabilidade • Durabilidade: 32 usos";
            case "Diamante" -> "Ferramentas de alta qualidade • Durabilidade: 1561 usos";
            case "Netherite" -> "As melhores ferramentas! Resistentes a lava • Durabilidade: 2031 usos";
            default -> "Conjunto completo de ferramentas para todas as suas necessidades";
        };
    }
    
    private String getArmorSetDescription(String material) {
        return switch (material) {
            case "Couro" -> "Armadura básica, tingível e ideal para iniciantes";
            case "Cota de Malha" -> "Armadura rara que só pode ser obtida através de comércio ou baús";
            case "Ferro" -> "Proteção sólida e acessível no meio do jogo";
            case "Ouro" -> "Baixa durabilidade, mas alta encantabilidade";
            case "Diamante" -> "Proteção excelente antes do Nether";
            case "Netherite" -> "A melhor armadura do jogo! Resistente a fogo e lava";
            case "Tartaruga" -> "Capacete especial que concede respiração aquática";
            default -> "Conjunto completo de armadura";
        };
    }
    
    private JPanel createArmorSetStats(List<Armadura> armaduras, String material) {
        JPanel statsPanel = new JPanel(new GridBagLayout());
        statsPanel.setBackground(new Color(50, 50, 50));
        statsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(getColorForMaterial(material), 3),
            new EmptyBorder(20, 20, 20, 20)
        ));
        statsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Defesa total
        int defesaTotal = armaduras.stream().mapToInt(Armadura::getDefesa).sum();
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel defesaTotalLabel = ImageManager.createIconLabel("DEFENSE", " DEFESA TOTAL", 16);
        defesaTotalLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        defesaTotalLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        statsPanel.add(defesaTotalLabel, gbc);
        
        gbc.gridy = 1;
        JLabel defesaValue = new JLabel(defesaTotal + " pontos");
        defesaValue.setFont(new Font("SansSerif", Font.BOLD, 20));
        defesaValue.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        statsPanel.add(defesaValue, gbc);
        
        // Durabilidade média
        double durabMedia = armaduras.stream().mapToDouble(Armadura::getDurabilidade).average().orElse(0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel durabLabel = new JLabel("🔧 DURABILIDADE MÉDIA");
        durabLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        durabLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        statsPanel.add(durabLabel, gbc);
        
        gbc.gridy = 1;
        JLabel durabValue = new JLabel(String.format("%.0f usos", durabMedia));
        durabValue.setFont(new Font("SansSerif", Font.BOLD, 20));
        durabValue.setForeground(MinecraftWikiGUI.MINECRAFT_BLUE);
        statsPanel.add(durabValue, gbc);
        
        // Peças no conjunto
        gbc.gridx = 2;
        gbc.gridy = 0;
        JLabel pecasLabel = new JLabel("[PECAS] PEÇAS");
        pecasLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        pecasLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        statsPanel.add(pecasLabel, gbc);
        
        gbc.gridy = 1;
        JLabel pecasValue = new JLabel(armaduras.size() + " peças");
        pecasValue.setFont(new Font("SansSerif", Font.BOLD, 20));
        pecasValue.setForeground(MinecraftWikiGUI.MINECRAFT_BROWN);
        statsPanel.add(pecasValue, gbc);
        
        return statsPanel;
    }
    
    private JPanel createImprovedArmorPiecesPanel(List<Armadura> armaduras, String material) {
        JPanel piecesPanel = new JPanel(new GridLayout(1, armaduras.size(), 15, 0));
        piecesPanel.setOpaque(false);
        piecesPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
        
        for (Armadura armadura : armaduras) {
            piecesPanel.add(createEnhancedArmorCard(armadura, material));
        }
        
        return piecesPanel;
    }
    
    private JPanel createEnhancedArmorCard(Armadura armadura, String material) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(45, 45, 45));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(getColorForMaterial(material), 3),
            new EmptyBorder(15, 15, 15, 15)
        ));
        card.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        // Tornar o card clicável para mostrar todos os moldes
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showArmorWithAllTrims(armadura, material);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(55, 55, 55));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(45, 45, 45));
            }
        });
        
        // Imagem da peça
        if (armadura.getImagemPath() != null && !armadura.getImagemPath().isEmpty()) {
            JLabel imageLabel = createImageLabel(armadura.getImagemPath(), 80, 80);
            if (imageLabel != null) {
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(imageLabel);
                card.add(Box.createVerticalStrut(10));
            }
        }
        
        // Nome da peça
        JLabel nameLabel = new JLabel(getTipoEmoji(armadura.getTipo()) + " " + armadura.getTipo());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        nameLabel.setForeground(getColorForMaterial(material));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(nameLabel);
        
        card.add(Box.createVerticalStrut(10));
        
        // Dica de clique
        JLabel clickHint = new JLabel("👆 Clique para ver com moldes");
        clickHint.setFont(new Font("SansSerif", Font.ITALIC, 10));
        clickHint.setForeground(MinecraftWikiGUI.MINECRAFT_PURPLE);
        clickHint.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(clickHint);
        
        card.add(Box.createVerticalStrut(10));
        
        // Barra de defesa visual
        JPanel defenseBar = createStatBar(" Defesa", armadura.getDefesa(), 8, MinecraftWikiGUI.MINECRAFT_GREEN);
        defenseBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(defenseBar);
        
        card.add(Box.createVerticalStrut(8));
        
        // Durabilidade com barra
        int maxDurab = 500; // Aproximadamente o máximo
        JPanel durabBar = createStatBar("🔧 Durabilidade", (int)(armadura.getDurabilidade() / maxDurab * 10), 10, MinecraftWikiGUI.MINECRAFT_BLUE);
        durabBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(durabBar);
        
        card.add(Box.createVerticalStrut(8));
        
        // Valor numérico
        JLabel durabValue = new JLabel(String.format("%.0f usos", armadura.getDurabilidade()));
        durabValue.setFont(new Font("SansSerif", Font.PLAIN, 11));
        durabValue.setForeground(Color.LIGHT_GRAY);
        durabValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(durabValue);
        
        card.add(Box.createVerticalStrut(15));
        
        // Botões de ação
        JButton recipeBtn = new JButton(" Receita");
        recipeBtn.setIcon(ImageManager.getItemIcon("RECIPE", 16));
        recipeBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        recipeBtn.setBackground(getColorForMaterial(material));
        recipeBtn.setForeground(Color.WHITE);
        recipeBtn.setFocusPainted(false);
        recipeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipeBtn.addActionListener(e -> showArmorDetails(armadura));
        card.add(recipeBtn);
        
        return card;
    }
    
    private JPanel createStatBar(String label, int value, int max, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        
        JLabel labelText = new JLabel(label + ": " + value);
        labelText.setFont(new Font("SansSerif", Font.BOLD, 12));
        labelText.setForeground(Color.WHITE);
        labelText.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(labelText);
        
        panel.add(Box.createVerticalStrut(3));
        
        // Barra visual
        JPanel barContainer = new JPanel();
        barContainer.setLayout(new BoxLayout(barContainer, BoxLayout.X_AXIS));
        barContainer.setOpaque(false);
        barContainer.setMaximumSize(new Dimension(150, 12));
        
        for (int i = 0; i < max; i++) {
            JPanel segment = new JPanel();
            segment.setPreferredSize(new Dimension(12, 12));
            segment.setMaximumSize(new Dimension(12, 12));
            segment.setBackground(i < value ? color : new Color(80, 80, 80));
            segment.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 1));
            barContainer.add(segment);
            if (i < max - 1) {
                barContainer.add(Box.createHorizontalStrut(2));
            }
        }
        
        panel.add(barContainer);
        return panel;
    }
    
    private JPanel createArmorExtraInfo(String material) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(50, 50, 50));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BROWN, 2),
            new EmptyBorder(20, 20, 20, 20)
        ));
        infoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        
        JLabel infoTitle = new JLabel("ℹ️ INFORMAÇÕES ADICIONAIS");
        infoTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        infoTitle.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        infoTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(infoTitle);
        
        infoPanel.add(Box.createVerticalStrut(15));
        
        String[] infos = getExtraInfoForMaterial(material);
        for (String info : infos) {
            JLabel infoLabel = new JLabel("• " + info);
            infoLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            infoLabel.setForeground(Color.WHITE);
            infoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(infoLabel);
            infoPanel.add(Box.createVerticalStrut(8));
        }
        
        return infoPanel;
    }
    
    private String[] getExtraInfoForMaterial(String material) {
        return switch (material) {
            case "Couro" -> new String[]{
                "Pode ser tingida com corantes em 16 cores diferentes",
                "Ideal para iniciantes e fácil de obter",
                "Crafting: necessita de couro obtido de vacas",
                "Menor proteção, mas alta disponibilidade"
            };
            case "Cota de Malha" -> new String[]{
                "Não pode ser craftada, apenas obtida",
                "Encontrada em baús de estruturas ou drops de mobs",
                "Comerciantes de vilas podem vender peças",
                "Mesma proteção do ferro, visual único"
            };
            case "Ferro" -> new String[]{
                "Ótimo custo-benefício para meio do jogo",
                "Crafting: necessita de lingotes de ferro",
                "Ferro é abundante em cavernas",
                "Proteção sólida e durabilidade razoável"
            };
            case "Ouro" -> new String[]{
                "Alta encantabilidade - melhores encantamentos",
                "Baixa durabilidade - quebra rápido",
                "Piglins não atacam se você usar ouro",
                "Ideal apenas para encantamentos específicos"
            };
            case "Diamante" -> new String[]{
                "Segunda melhor armadura do jogo",
                "Crafting: necessita de diamantes raros",
                "Pode ser melhorada para Netherite",
                "Essencial antes de explorar o Nether"
            };
            case "Netherite" -> new String[]{
                "Melhor armadura do jogo!",
                "Resistente a fogo e lava - não queima!",
                "Crafting: Mesa de Ferraria com armadura de Diamante",
                "Necessita Molde de Netherite + Lingote de Netherite",
                "Mantém encantamentos ao fazer upgrade"
            };
            case "Tartaruga" -> new String[]{
                "Apenas capacete disponível",
                "Concede efeito Respiração Aquática",
                "Crafting: necessita de escamas de tartaruga",
                "Escamas obtidas de tartarugas bebês crescendo"
            };
            default -> new String[]{"Conjunto completo de armadura para proteção"};
        };
    }
    
    @SuppressWarnings("unused")
    private JPanel createArmorTableRow(Armadura armadura) {
        JPanel row = new JPanel(new GridBagLayout());
        row.setBackground(new Color(50, 50, 50));
        row.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(getColorForMaterial(armadura.getMaterial()), 2),
            new EmptyBorder(15, 15, 15, 15)
        ));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.BOTH;
        
        // Coluna 1: Nome do Material
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.15;
        gbc.gridheight = 2;
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setOpaque(false);
        
        JLabel materialLabel = new JLabel(getEmojiForMaterial(armadura.getMaterial()) + " " + armadura.getMaterial());
        materialLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        materialLabel.setForeground(getColorForMaterial(armadura.getMaterial()));
        namePanel.add(materialLabel);
        
        namePanel.add(Box.createVerticalStrut(5));
        
        JLabel tipoLabel = new JLabel(getTipoEmoji(armadura.getTipo()) + " " + armadura.getTipo());
        tipoLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tipoLabel.setForeground(Color.LIGHT_GRAY);
        namePanel.add(tipoLabel);
        
        row.add(namePanel, gbc);
        
        // Coluna 2: Ingredientes
        gbc.gridx = 1;
        gbc.weightx = 0.25;
        JPanel ingredientesPanel = new JPanel();
        ingredientesPanel.setLayout(new BoxLayout(ingredientesPanel, BoxLayout.Y_AXIS));
        ingredientesPanel.setOpaque(false);
        
        JLabel ingLabel = new JLabel("[ING] Ingredientes:");
        ingLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        ingLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        ingredientesPanel.add(ingLabel);
        
        ingredientesPanel.add(Box.createVerticalStrut(5));
        
        for (String ing : armadura.getIngredientes()) {
            JLabel ingrediente = new JLabel("• " + ing);
            ingrediente.setFont(new Font("SansSerif", Font.PLAIN, 11));
            ingrediente.setForeground(Color.WHITE);
            ingredientesPanel.add(ingrediente);
        }
        
        row.add(ingredientesPanel, gbc);
        
        // Coluna 3: Stats (Defesa e Durabilidade)
        gbc.gridx = 2;
        gbc.weightx = 0.20;
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setOpaque(false);
        
        JLabel statsTitle = new JLabel("📊 Estatísticas:");
        statsTitle.setFont(new Font("SansSerif", Font.BOLD, 12));
        statsTitle.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        statsPanel.add(statsTitle);
        
        statsPanel.add(Box.createVerticalStrut(5));
        
        JLabel defenseLabel = ImageManager.createIconLabel("DEFENSE", " Defesa: " + armadura.getDefesaCompleta(), 16);
        defenseLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        defenseLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        statsPanel.add(defenseLabel);
        
        JLabel durabilityLabel = new JLabel("🔧 Durabilidade: " + armadura.getDurabilidadeCompleta());
        durabilityLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        durabilityLabel.setForeground(MinecraftWikiGUI.MINECRAFT_BLUE);
        statsPanel.add(durabilityLabel);
        
        row.add(statsPanel, gbc);
        
        // Coluna 4: Imagem da receita de crafting
        gbc.gridx = 3;
        gbc.weightx = 0.25;
        JPanel recipePanel = new JPanel();
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
        recipePanel.setOpaque(false);
        
        JLabel recipeTitle = new JLabel("⚒️ Receita:");
        recipeTitle.setFont(new Font("SansSerif", Font.BOLD, 12));
        recipeTitle.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        recipeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipePanel.add(recipeTitle);
        
        recipePanel.add(Box.createVerticalStrut(5));
        
        // Placeholder para imagem de crafting (você pode adicionar a imagem real aqui)
        JLabel craftingPlaceholder = ImageManager.createIconLabel("CRAFTING_TABLE", " Ver Receita", 16);
        craftingPlaceholder.setFont(new Font("SansSerif", Font.PLAIN, 10));
        craftingPlaceholder.setForeground(Color.GRAY);
        craftingPlaceholder.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipePanel.add(craftingPlaceholder);
        
        row.add(recipePanel, gbc);
        
        // Coluna 5: Botão de ação
        gbc.gridx = 4;
        gbc.weightx = 0.15;
        JButton detailsButton = new JButton(" Detalhes");
        detailsButton.setIcon(ImageManager.getItemIcon("BOOK", 16));
        detailsButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        detailsButton.setBackground(MinecraftWikiGUI.MINECRAFT_BROWN);
        detailsButton.setForeground(Color.WHITE);
        detailsButton.setFocusPainted(false);
        detailsButton.addActionListener(e -> showArmorDetails(armadura));
        row.add(detailsButton, gbc);
        
        return row;
    }
    
    @SuppressWarnings("unused")
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
        JLabel defenseLabel = ImageManager.createIconLabel("DEFENSE", " " + armadura.getDefesaCompleta(), 16);
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
        JButton detailsButton = new JButton(" Ver Receita");
        detailsButton.setIcon(ImageManager.getItemIcon("RECIPE", 16));
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
    
    private void displayMoldesFerraria() {
        resultsPanel.removeAll();
        
        // Botão de voltar
        JButton backButton = new JButton("← Voltar para Armaduras");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(new Color(80, 80, 80));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(250, 35));
        backButton.addActionListener(e -> showCategorySelection());
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(MinecraftWikiGUI.MINECRAFT_BROWN);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(80, 80, 80));
            }
        });
        
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(new Color(40, 40, 40));
        backPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        backPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        backPanel.add(backButton);
        resultsPanel.add(backPanel);
        
        // Título
        JLabel titleLabel = new JLabel("✨ MOLDES DE FERRARIA (ENFEITES)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_PURPLE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(20, 0, 10, 0));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(40, 40, 40));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        titlePanel.add(titleLabel);
        resultsPanel.add(titlePanel);
        
        // Informações
        JTextArea infoArea = new JTextArea(
            "Os Moldes de Ferraria permitem adicionar padrões decorativos às armaduras!\n" +
            "Existem " + wiki.getMoldesFerraria().size() + " moldes de enfeite disponíveis.\n\n" +
            "Para usar: Mesa de Ferraria + Molde + Armadura + Material decorativo"
        );
        infoArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoArea.setForeground(Color.WHITE);
        infoArea.setBackground(new Color(50, 50, 50));
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setBorder(new EmptyBorder(15, 15, 15, 15));
        infoArea.setMaximumSize(new Dimension(900, 120));
        
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        infoPanel.setBackground(new Color(40, 40, 40));
        infoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
        infoPanel.add(infoArea);
        resultsPanel.add(infoPanel);
        
        resultsPanel.add(Box.createVerticalStrut(20));
        
        // Grid de moldes
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 15, 15));
        gridPanel.setBackground(new Color(40, 40, 40));
        gridPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        for (MoldeFerraria molde : wiki.getMoldesFerraria()) {
            gridPanel.add(createMoldeCard(molde));
        }
        
        JPanel gridContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gridContainer.setBackground(new Color(40, 40, 40));
        gridContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        gridContainer.add(gridPanel);
        resultsPanel.add(gridContainer);
        
        resultsPanel.add(Box.createVerticalGlue());
        
        scrollPane.setViewportView(resultsPanel);
        scrollPane.revalidate();
        scrollPane.repaint();
    }
    
    private JPanel createMoldeCard(MoldeFerraria molde) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(60, 60, 60));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_PURPLE, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));
        card.setPreferredSize(new Dimension(300, 250));
        card.setMaximumSize(new Dimension(300, 250));
        
        JLabel nameLabel = new JLabel(molde.getNome(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        nameLabel.setForeground(MinecraftWikiGUI.MINECRAFT_PURPLE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(nameLabel);
        
        card.add(Box.createVerticalStrut(10));
        
        JTextArea descArea = new JTextArea(molde.getDescricao());
        descArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        descArea.setForeground(Color.WHITE);
        descArea.setBackground(new Color(60, 60, 60));
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setMaximumSize(new Dimension(270, 80));
        card.add(descArea);
        
        card.add(Box.createVerticalStrut(10));
        
        JLabel locLabel = new JLabel("📍 " + molde.getLocalizacao());
        locLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        locLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        locLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(locLabel);
        
        card.add(Box.createVerticalGlue());
        
        JButton detailsButton = new JButton("Ver Detalhes");
        detailsButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        detailsButton.setBackground(MinecraftWikiGUI.MINECRAFT_PURPLE);
        detailsButton.setForeground(Color.WHITE);
        detailsButton.setFocusPainted(false);
        detailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsButton.addActionListener(e -> showTrimDetails(molde));
        detailsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                detailsButton.setBackground(new Color(170, 100, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                detailsButton.setBackground(MinecraftWikiGUI.MINECRAFT_PURPLE);
            }
        });
        card.add(detailsButton);
        
        return card;
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
        
        JLabel usoLabel = ImageManager.createIconLabel("CRAFTING_TABLE", " Como Usar:", 16);
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
    
    // Método para mostrar comparação de armaduras
    private void showArmorComparison() {
        resultsPanel.removeAll();
        
        // Botão de voltar
        JButton backButton = new JButton("← Voltar para Categorias");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(new Color(80, 80, 80));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(250, 35));
        backButton.addActionListener(e -> showCategorySelection());
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(MinecraftWikiGUI.MINECRAFT_BROWN);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(80, 80, 80));
            }
        });
        
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(new Color(40, 40, 40));
        backPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        backPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        backPanel.add(backButton);
        resultsPanel.add(backPanel);
        
        // Título
        JLabel titleLabel = new JLabel("⚖ COMPARAÇÃO DE ARMADURAS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(40, 40, 40));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        titlePanel.add(titleLabel);
        resultsPanel.add(titlePanel);
        
        // Tabela de comparação
        resultsPanel.add(createComparisonTable());
        
        resultsPanel.add(Box.createVerticalStrut(20));
        
        // Gráfico visual de defesa
        resultsPanel.add(createDefenseChart());
        
        resultsPanel.add(Box.createVerticalStrut(20));
        
        // Informações extras
        resultsPanel.add(createComparisonNotes());
        
        resultsPanel.add(Box.createVerticalGlue());
        
        scrollPane.setViewportView(resultsPanel);
        scrollPane.revalidate();
        scrollPane.repaint();
    }
    
    private JPanel createComparisonTable() {
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        tablePanel.setBackground(new Color(50, 50, 50));
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BROWN, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));
        tablePanel.setMaximumSize(new Dimension(1200, Integer.MAX_VALUE));
        tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Header da tabela
        JPanel headerPanel = new JPanel(new GridLayout(1, 6, 5, 5));
        headerPanel.setBackground(new Color(40, 40, 40));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        
        String[] headers = {"Material", "Defesa Total", "Durabilidade Média", "Dureza", "Encantabilidade", "Vantagens"};
        for (String header : headers) {
            JLabel label = new JLabel(header, SwingConstants.CENTER);
            label.setFont(new Font("SansSerif", Font.BOLD, 14));
            label.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
            label.setOpaque(true);
            label.setBackground(new Color(60, 60, 60));
            label.setBorder(BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BROWN, 1));
            headerPanel.add(label);
        }
        tablePanel.add(headerPanel);
        tablePanel.add(Box.createVerticalStrut(5));
        
        // Dados das armaduras
        String[] materials = {"Couro", "Cota de Malha", "Ferro", "Ouro", "Diamante", "Netherite", "Tartaruga"};
        int[][] stats = {
            {7, 112, 1, 15, 1},    // Couro: defesa, durabilidade, dureza, encantabilidade, bonus
            {12, 240, 1, 12, 0},   // Cota de Malha
            {15, 280, 2, 9, 0},    // Ferro
            {11, 128, 2, 25, 0},   // Ouro
            {20, 528, 3, 10, 2},   // Diamante
            {20, 592, 3, 15, 3},   // Netherite
            {2, 275, 2, 9, 0}      // Tartaruga (capacete apenas)
        };
        String[] advantages = {
            "Barato, silencioso",
            "Raro, estilo único",
            "Bom custo-benefício",
            "Piglins neutros",
            "Excelente proteção",
            "Melhor do jogo",
            "Respiração aquática"
        };
        
        for (int i = 0; i < materials.length; i++) {
            JPanel rowPanel = new JPanel(new GridLayout(1, 6, 5, 5));
            rowPanel.setBackground(new Color(50, 50, 50));
            rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            
            Color materialColor = getColorForMaterial(materials[i]);
            
            // Material
            JLabel materialLabel = new JLabel(getEmojiForMaterial(materials[i]) + " " + materials[i], SwingConstants.CENTER);
            materialLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
            materialLabel.setForeground(materialColor);
            materialLabel.setOpaque(true);
            materialLabel.setBackground(new Color(65, 65, 65));
            materialLabel.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));
            rowPanel.add(materialLabel);
            
            // Defesa Total
            JLabel defenseLabel = new JLabel("🛡️ " + stats[i][0], SwingConstants.CENTER);
            defenseLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            defenseLabel.setForeground(Color.WHITE);
            defenseLabel.setOpaque(true);
            defenseLabel.setBackground(new Color(65, 65, 65));
            defenseLabel.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));
            rowPanel.add(defenseLabel);
            
            // Durabilidade Média
            JLabel durabilityLabel = new JLabel("⚙️ " + stats[i][1], SwingConstants.CENTER);
            durabilityLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            durabilityLabel.setForeground(Color.WHITE);
            durabilityLabel.setOpaque(true);
            durabilityLabel.setBackground(new Color(65, 65, 65));
            durabilityLabel.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));
            rowPanel.add(durabilityLabel);
            
            // Dureza
            String toughnessStars = "★".repeat(stats[i][2]) + "☆".repeat(3 - stats[i][2]);
            JLabel toughnessLabel = new JLabel(toughnessStars, SwingConstants.CENTER);
            toughnessLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            toughnessLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
            toughnessLabel.setOpaque(true);
            toughnessLabel.setBackground(new Color(65, 65, 65));
            toughnessLabel.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));
            rowPanel.add(toughnessLabel);
            
            // Encantabilidade
            JLabel enchantLabel = new JLabel("✨ " + stats[i][3], SwingConstants.CENTER);
            enchantLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            enchantLabel.setForeground(new Color(170, 100, 255));
            enchantLabel.setOpaque(true);
            enchantLabel.setBackground(new Color(65, 65, 65));
            enchantLabel.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));
            rowPanel.add(enchantLabel);
            
            // Vantagens
            JLabel advantagesLabel = new JLabel("<html><center>" + advantages[i] + "</center></html>", SwingConstants.CENTER);
            advantagesLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
            advantagesLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
            advantagesLabel.setOpaque(true);
            advantagesLabel.setBackground(new Color(65, 65, 65));
            advantagesLabel.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));
            rowPanel.add(advantagesLabel);
            
            tablePanel.add(rowPanel);
            tablePanel.add(Box.createVerticalStrut(3));
        }
        
        JPanel containerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        containerPanel.setBackground(new Color(40, 40, 40));
        containerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        containerPanel.add(tablePanel);
        
        return containerPanel;
    }
    
    private JPanel createDefenseChart() {
        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new BoxLayout(chartPanel, BoxLayout.Y_AXIS));
        chartPanel.setBackground(new Color(50, 50, 50));
        chartPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BROWN, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));
        chartPanel.setMaximumSize(new Dimension(900, 400));
        chartPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel chartTitle = new JLabel("📊 Comparação Visual de Defesa", SwingConstants.CENTER);
        chartTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        chartTitle.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        chartTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        chartTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        chartPanel.add(chartTitle);
        
        String[] materials = {"Couro", "Cota de Malha", "Ferro", "Ouro", "Diamante", "Netherite", "Tartaruga"};
        int[] defenseValues = {7, 12, 15, 11, 20, 20, 2};
        int maxDefense = 20;
        
        for (int i = 0; i < materials.length; i++) {
            JPanel barPanel = new JPanel();
            barPanel.setLayout(new BoxLayout(barPanel, BoxLayout.X_AXIS));
            barPanel.setBackground(new Color(50, 50, 50));
            barPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            
            // Label do material
            JLabel materialLabel = new JLabel(getEmojiForMaterial(materials[i]) + " " + materials[i]);
            materialLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
            materialLabel.setForeground(getColorForMaterial(materials[i]));
            materialLabel.setPreferredSize(new Dimension(180, 30));
            barPanel.add(materialLabel);
            
            barPanel.add(Box.createHorizontalStrut(10));
            
            // Barra de defesa
            int barWidth = (int) ((defenseValues[i] / (double) maxDefense) * 500);
            JPanel bar = new JPanel();
            bar.setBackground(getColorForMaterial(materials[i]));
            bar.setPreferredSize(new Dimension(barWidth, 25));
            bar.setMaximumSize(new Dimension(barWidth, 25));
            barPanel.add(bar);
            
            barPanel.add(Box.createHorizontalStrut(10));
            
            // Valor da defesa
            JLabel valueLabel = new JLabel("🛡️ " + defenseValues[i]);
            valueLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            valueLabel.setForeground(Color.WHITE);
            barPanel.add(valueLabel);
            
            barPanel.add(Box.createHorizontalGlue());
            
            chartPanel.add(barPanel);
            chartPanel.add(Box.createVerticalStrut(5));
        }
        
        JPanel containerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        containerPanel.setBackground(new Color(40, 40, 40));
        containerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 450));
        containerPanel.add(chartPanel);
        
        return containerPanel;
    }
    
    private JPanel createComparisonNotes() {
        JPanel notesPanel = new JPanel();
        notesPanel.setLayout(new BoxLayout(notesPanel, BoxLayout.Y_AXIS));
        notesPanel.setBackground(new Color(50, 50, 50));
        notesPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BROWN, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));
        notesPanel.setMaximumSize(new Dimension(900, 300));
        notesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel notesTitle = new JLabel("📝 Notas Importantes", SwingConstants.CENTER);
        notesTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        notesTitle.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        notesTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        notesTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        notesPanel.add(notesTitle);
        
        String[] notes = {
            "🔥 Netherite: A única armadura indestrutível por lava e fogo. Não queima ao ser descartada.",
            "⚡ Diamante vs Netherite: Mesma defesa, mas Netherite tem +1 de resistência a knockback e durabilidade 12% maior.",
            "🐷 Ouro: Apesar da defesa baixa, tem a maior encantabilidade. Piglins não atacam se você usar pelo menos uma peça.",
            "🐢 Tartaruga: O capacete fornece respiração aquática por 10 segundos fora d'água.",
            "💎 Dureza (Toughness): Reduz dano de ataques fortes. Diamante tem +2, Netherite tem +3.",
            "⚔️ Para PvP: Netherite é superior. Para PvE inicial: Ferro tem melhor custo-benefício.",
            "✨ Encantamentos recomendados: Proteção IV, Inquebrável III, Remendo."
        };
        
        for (String note : notes) {
            JLabel noteLabel = new JLabel("<html><div style='width: 800px; padding: 5px;'>• " + note + "</div></html>");
            noteLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            noteLabel.setForeground(Color.WHITE);
            noteLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            noteLabel.setBorder(new EmptyBorder(3, 10, 3, 10));
            notesPanel.add(noteLabel);
        }
        
        JPanel containerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        containerPanel.setBackground(new Color(40, 40, 40));
        containerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));
        containerPanel.add(notesPanel);
        
        return containerPanel;
    }
    
    private void showArmorTrims() {
        resultsPanel.removeAll();
        
        // Botão de voltar
        JButton backButton = new JButton("← Voltar para Categorias");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(new Color(80, 80, 80));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(250, 35));
        backButton.addActionListener(e -> showCategorySelection());
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(MinecraftWikiGUI.MINECRAFT_BROWN);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(80, 80, 80));
            }
        });
        
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(new Color(40, 40, 40));
        backPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        backPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        backPanel.add(backButton);
        resultsPanel.add(backPanel);
        
        // Título
        JLabel titleLabel = new JLabel("✨ ARMADURAS COM MOLDES DE FERRARIA", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_PURPLE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(20, 0, 10, 0));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(40, 40, 40));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        titlePanel.add(titleLabel);
        resultsPanel.add(titlePanel);
        
        // Descrição
        JTextArea descArea = new JTextArea(
            "Veja como cada molde de ferraria fica aplicado nas diferentes armaduras!\n" +
            "Moldes decorativos podem ser aplicados em qualquer peça de armadura usando uma Mesa de Ferraria."
        );
        descArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descArea.setForeground(Color.WHITE);
        descArea.setBackground(new Color(50, 50, 50));
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setBorder(new EmptyBorder(15, 15, 15, 15));
        descArea.setMaximumSize(new Dimension(900, 100));
        
        JPanel descPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        descPanel.setBackground(new Color(40, 40, 40));
        descPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        descPanel.add(descArea);
        resultsPanel.add(descPanel);
        
        resultsPanel.add(Box.createVerticalStrut(20));
        
        // Lista de moldes com armaduras
        List<MoldeFerraria> trimMoldes = wiki.getMoldesTrim();
        
        for (MoldeFerraria molde : trimMoldes) {
            resultsPanel.add(createTrimSection(molde));
            resultsPanel.add(Box.createVerticalStrut(15));
        }
        
        resultsPanel.add(Box.createVerticalGlue());
        
        scrollPane.setViewportView(resultsPanel);
        scrollPane.revalidate();
        scrollPane.repaint();
    }
    
    private JPanel createTrimSection(MoldeFerraria molde) {
        JPanel section = new JPanel();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBackground(new Color(45, 45, 45));
        section.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_PURPLE, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));
        section.setMaximumSize(new Dimension(1100, Integer.MAX_VALUE));
        section.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Cabeçalho do molde
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.setBackground(new Color(45, 45, 45));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        JLabel moldeNameLabel = new JLabel("✨ " + molde.getNome().toUpperCase());
        moldeNameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        moldeNameLabel.setForeground(MinecraftWikiGUI.MINECRAFT_PURPLE);
        headerPanel.add(moldeNameLabel, BorderLayout.WEST);
        
        JLabel locLabel = new JLabel("📍 " + molde.getLocalizacao());
        locLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        locLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        headerPanel.add(locLabel, BorderLayout.EAST);
        
        section.add(headerPanel);
        section.add(Box.createVerticalStrut(10));
        
        // Descrição do molde
        JTextArea moldeDesc = new JTextArea(molde.getDescricao());
        moldeDesc.setFont(new Font("SansSerif", Font.PLAIN, 13));
        moldeDesc.setForeground(Color.LIGHT_GRAY);
        moldeDesc.setBackground(new Color(45, 45, 45));
        moldeDesc.setEditable(false);
        moldeDesc.setLineWrap(true);
        moldeDesc.setWrapStyleWord(true);
        moldeDesc.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        section.add(moldeDesc);
        
        section.add(Box.createVerticalStrut(15));
        
        // Grid de armaduras com este molde
        JPanel armorGrid = new JPanel(new GridLayout(1, 7, 10, 0));
        armorGrid.setBackground(new Color(45, 45, 45));
        armorGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        
        String[] materials = {"Couro", "Cota de Malha", "Ferro", "Ouro", "Diamante", "Netherite", "Tartaruga"};
        
        for (String material : materials) {
            armorGrid.add(createTrimArmorCard(material, molde));
        }
        
        section.add(armorGrid);
        
        JPanel containerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        containerPanel.setBackground(new Color(40, 40, 40));
        containerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        containerPanel.add(section);
        
        return containerPanel;
    }
    
    private JPanel createTrimArmorCard(String material, MoldeFerraria molde) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(55, 55, 55));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(getColorForMaterial(material), 2),
            new EmptyBorder(10, 10, 10, 10)
        ));
        card.setPreferredSize(new Dimension(140, 170));
        
        // Ícone/Emoji do material
        JLabel iconLabel = new JLabel(getEmojiForMaterial(material), SwingConstants.CENTER);
        iconLabel.setFont(new Font("SansSerif", Font.PLAIN, 40));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(iconLabel);
        
        card.add(Box.createVerticalStrut(10));
        
        // Nome do material
        JLabel materialLabel = new JLabel(material, SwingConstants.CENTER);
        materialLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        materialLabel.setForeground(getColorForMaterial(material));
        materialLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(materialLabel);
        
        card.add(Box.createVerticalStrut(5));
        
        // Descrição da combinação
        String combo = getTrimDescription(material, molde.getNome());
        JTextArea comboDesc = new JTextArea(combo);
        comboDesc.setFont(new Font("SansSerif", Font.PLAIN, 10));
        comboDesc.setForeground(Color.LIGHT_GRAY);
        comboDesc.setBackground(new Color(55, 55, 55));
        comboDesc.setEditable(false);
        comboDesc.setLineWrap(true);
        comboDesc.setWrapStyleWord(true);
        comboDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(comboDesc);
        
        card.add(Box.createVerticalGlue());
        
        return card;
    }
    
    private String getTrimDescription(String material, String moldeName) {
        // Descrições específicas para combinações interessantes
        if (material.equals("Netherite") && moldeName.contains("Silence")) {
            return "Combinação premium - visual escuro e imponente";
        } else if (material.equals("Ouro") && moldeName.contains("Vex")) {
            return "Ideal para Nether - impressiona Piglins";
        } else if (material.equals("Diamante") && moldeName.contains("Ward")) {
            return "Estilo clássico e elegante";
        } else if (material.equals("Ferro") && moldeName.contains("Sentry")) {
            return "Visual militar e robusto";
        } else if (material.equals("Couro") && moldeName.contains("Coast")) {
            return "Estilo aventureiro casual";
        } else if (material.equals("Tartaruga") && moldeName.contains("Tide")) {
            return "Perfeito para explorações aquáticas";
        } else {
            return "Padrão " + moldeName + " aplicado";
        }
    }
    
    private void showArmorWithAllTrims(Armadura armadura, String material) {
        resultsPanel.removeAll();
        
        // Botão de voltar
        JButton backButton = new JButton("← Voltar para " + material);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(new Color(80, 80, 80));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(250, 35));
        backButton.addActionListener(e -> displayArmorsByOneMaterial(material));
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(MinecraftWikiGUI.MINECRAFT_BROWN);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(80, 80, 80));
            }
        });
        
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(new Color(40, 40, 40));
        backPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        backPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        backPanel.add(backButton);
        resultsPanel.add(backPanel);
        
        // Título
        String titulo = getTipoEmoji(armadura.getTipo()) + " " + armadura.getTipo().toUpperCase() + " DE " + material.toUpperCase() + " - TODOS OS MOLDES";
        JLabel titleLabel = new JLabel(titulo, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(getColorForMaterial(material));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(20, 0, 10, 0));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(40, 40, 40));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        titlePanel.add(titleLabel);
        resultsPanel.add(titlePanel);
        
        // Info da peça original
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        statsPanel.setBackground(new Color(50, 50, 50));
        statsPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        statsPanel.setMaximumSize(new Dimension(800, 80));
        
        JLabel defLabel = new JLabel("🛡️ Defesa: " + armadura.getDefesa());
        defLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        defLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        statsPanel.add(defLabel);
        
        JLabel durabLabel = new JLabel("🔧 Durabilidade: " + String.format("%.0f", armadura.getDurabilidade()));
        durabLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        durabLabel.setForeground(MinecraftWikiGUI.MINECRAFT_BLUE);
        statsPanel.add(durabLabel);
        
        JPanel statsPanelContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statsPanelContainer.setBackground(new Color(40, 40, 40));
        statsPanelContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        statsPanelContainer.add(statsPanel);
        resultsPanel.add(statsPanelContainer);
        
        resultsPanel.add(Box.createVerticalStrut(20));
        
        // Peça original sem molde
        JPanel originalSection = createSingleTrimCard("SEM MOLDE", armadura, material, null);
        resultsPanel.add(originalSection);
        
        resultsPanel.add(Box.createVerticalStrut(20));
        
        // Separador
        JLabel trimsLabel = new JLabel("✨ COM MOLDES DE FERRARIA", SwingConstants.CENTER);
        trimsLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        trimsLabel.setForeground(MinecraftWikiGUI.MINECRAFT_PURPLE);
        trimsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        trimsLabel.setBorder(new EmptyBorder(10, 0, 15, 0));
        
        JPanel trimsLabelPanel = new JPanel();
        trimsLabelPanel.setLayout(new BoxLayout(trimsLabelPanel, BoxLayout.Y_AXIS));
        trimsLabelPanel.setBackground(new Color(40, 40, 40));
        trimsLabelPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        trimsLabelPanel.add(trimsLabel);
        resultsPanel.add(trimsLabelPanel);
        
        // Grid com todos os moldes
        List<MoldeFerraria> trimMoldes = wiki.getMoldesTrim();
        
        for (MoldeFerraria molde : trimMoldes) {
            resultsPanel.add(createSingleTrimCard(molde.getNome(), armadura, material, molde));
            resultsPanel.add(Box.createVerticalStrut(15));
        }
        
        resultsPanel.add(Box.createVerticalGlue());
        
        scrollPane.setViewportView(resultsPanel);
        scrollPane.revalidate();
        scrollPane.repaint();
    }
    
    private JPanel createSingleTrimCard(String trimName, Armadura armadura, String material, MoldeFerraria molde) {
        JPanel card = new JPanel(new BorderLayout(15, 0));
        card.setBackground(new Color(50, 50, 50));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(molde == null ? getColorForMaterial(material) : MinecraftWikiGUI.MINECRAFT_PURPLE, 2),
            new EmptyBorder(15, 20, 15, 20)
        ));
        card.setMaximumSize(new Dimension(1000, 150));
        
        // Lado esquerdo - Imagem e nome
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(50, 50, 50));
        
        if (armadura.getImagemPath() != null && !armadura.getImagemPath().isEmpty()) {
            JLabel imageLabel = createImageLabel(armadura.getImagemPath(), 80, 80);
            if (imageLabel != null) {
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                leftPanel.add(imageLabel);
                leftPanel.add(Box.createVerticalStrut(5));
            }
        }
        
        JLabel nameLabel = new JLabel(trimName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        nameLabel.setForeground(molde == null ? getColorForMaterial(material) : MinecraftWikiGUI.MINECRAFT_PURPLE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(nameLabel);
        
        card.add(leftPanel, BorderLayout.WEST);
        
        // Centro - Descrição
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(50, 50, 50));
        
        if (molde != null) {
            JLabel descLabel = new JLabel("📝 " + molde.getDescricao());
            descLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            descLabel.setForeground(Color.WHITE);
            centerPanel.add(descLabel);
            
            centerPanel.add(Box.createVerticalStrut(8));
            
            JLabel locLabel = new JLabel("📍 Localização: " + molde.getLocalizacao());
            locLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            locLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
            centerPanel.add(locLabel);
            
            centerPanel.add(Box.createVerticalStrut(8));
            
            String combo = getTrimDescription(material, molde.getNome());
            JLabel comboLabel = new JLabel("💡 " + combo);
            comboLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
            comboLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
            centerPanel.add(comboLabel);
        } else {
            JLabel originalLabel = new JLabel("Peça de armadura original sem nenhum molde aplicado");
            originalLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            originalLabel.setForeground(Color.LIGHT_GRAY);
            centerPanel.add(originalLabel);
            
            centerPanel.add(Box.createVerticalStrut(8));
            
            JLabel tipLabel = new JLabel("💡 Use uma Mesa de Ferraria para aplicar moldes decorativos");
            tipLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
            tipLabel.setForeground(MinecraftWikiGUI.MINECRAFT_PURPLE);
            centerPanel.add(tipLabel);
        }
        
        card.add(centerPanel, BorderLayout.CENTER);
        
        // Direita - Ícone grande do tipo
        JLabel typeIcon = new JLabel(getTipoEmoji(armadura.getTipo()), SwingConstants.CENTER);
        typeIcon.setFont(new Font("SansSerif", Font.PLAIN, 60));
        typeIcon.setPreferredSize(new Dimension(100, 100));
        card.add(typeIcon, BorderLayout.EAST);
        
        JPanel containerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        containerPanel.setBackground(new Color(40, 40, 40));
        containerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        containerPanel.add(card);
        
        return containerPanel;
    }
}
