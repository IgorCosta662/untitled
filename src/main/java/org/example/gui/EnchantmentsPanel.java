package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.example.Encantamento;
import org.example.MinecraftWiki;

public class EnchantmentsPanel extends JPanel {
    private final MinecraftWiki wiki;
    private JTextField searchField;
    private JPanel resultsPanel;
    private JScrollPane scrollPane;
    private boolean isTableView = false;

    public EnchantmentsPanel(MinecraftWiki wiki) {
        this.wiki = wiki;
        setupUI();
        loadAllEnchantments();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(40, 40, 40));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createTopPanel(), BorderLayout.NORTH);

        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(new Color(40, 40, 40));

        scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setBackground(new Color(40, 40, 40));
        scrollPane.setBorder(BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BLUE, 2));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("✨ ENCANTAMENTOS");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_BLUE);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(new Color(40, 40, 40));

        JLabel searchLabel = new JLabel("🔍 Buscar:");
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        searchField = new JTextField(40);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.addActionListener(e -> performSearch());

        JButton searchButton = new JButton("Buscar");
        searchButton.setBackground(MinecraftWikiGUI.MINECRAFT_BLUE);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(e -> performSearch());

        JButton clearButton = new JButton("Limpar");
        clearButton.setBackground(MinecraftWikiGUI.MINECRAFT_GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(e -> {
            searchField.setText("");
            loadAllEnchantments();
        });

        JButton guideButton = new JButton("📖 Guia de Encantamento");
        guideButton.setBackground(MinecraftWikiGUI.MINECRAFT_PURPLE);
        guideButton.setForeground(Color.WHITE);
        guideButton.setFocusPainted(false);
        guideButton.addActionListener(e -> showEnchantingGuide());

        JButton toggleViewButton = new JButton("📊 Visualizar Tabela");
        toggleViewButton.setBackground(MinecraftWikiGUI.MINECRAFT_GOLD);
        toggleViewButton.setForeground(Color.WHITE);
        toggleViewButton.setFocusPainted(false);
        toggleViewButton.addActionListener(e -> {
            isTableView = !isTableView;
            toggleViewButton.setText(isTableView ? "📋 Visualizar Cards" : "📊 Visualizar Tabela");
            if (searchField.getText().trim().isEmpty()) {
                loadAllEnchantments();
            } else {
                performSearch();
            }
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);
        searchPanel.add(Box.createHorizontalStrut(10));
        searchPanel.add(guideButton);
        searchPanel.add(toggleViewButton);

        JPanel topContainer = new JPanel(new BorderLayout(10, 10));
        topContainer.setBackground(new Color(40, 40, 40));
        topContainer.add(titleLabel, BorderLayout.NORTH);
        topContainer.add(searchPanel, BorderLayout.CENTER);

        panel.add(topContainer, BorderLayout.CENTER);

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
            loadAllEnchantments();
            return;
        }

        List<Encantamento> results = wiki.buscarEncantamentos(searchTerm);
        displayEnchantments(results);
    }

    private void loadAllEnchantments() {
        List<Encantamento> enchantments = wiki.listarTodosEncantamentos();
        displayEnchantments(enchantments);
    }

    private void displayEnchantments(List<Encantamento> enchantments) {
        resultsPanel.removeAll();

        if (enchantments.isEmpty()) {
            JLabel noResults = new JLabel("❌ Nenhum encantamento encontrado");
            noResults.setFont(new Font("SansSerif", Font.BOLD, 18));
            noResults.setForeground(MinecraftWikiGUI.MINECRAFT_RED);
            noResults.setAlignmentX(Component.CENTER_ALIGNMENT);
            resultsPanel.add(Box.createVerticalStrut(50));
            resultsPanel.add(noResults);
        } else {
            if (isTableView) {
                displayEnchantmentsTable(enchantments);
            } else {
                displayEnchantmentsCards(enchantments);
            }
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
        scrollPane.getVerticalScrollBar().setValue(0);
    }

    private void displayEnchantmentsCards(List<Encantamento> enchantments) {
        JLabel countLabel = new JLabel("✅ " + enchantments.size() + " encantamento(s) encontrado(s)");
        countLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        countLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        countLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        countLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        resultsPanel.add(countLabel);

        for (Encantamento enchantment : enchantments) {
            resultsPanel.add(createEnchantmentCard(enchantment));
            resultsPanel.add(Box.createVerticalStrut(10));
        }
    }

    private void displayEnchantmentsTable(List<Encantamento> enchantments) {
        // Criar modelo de tabela
        String[] columnNames = {
            "Nome", "Descrição", "Tesouro", "Incompatível com", 
            "Nível máximo", "Item primário", "Item secundário", "Peso"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Preencher dados
        for (Encantamento enc : enchantments) {
            String nome = enc.getNome();
            String descricao = wrapText(enc.toString().split("Descrição: ")[1].split("\n")[0], 40);
            String tesouro = enc.isTesouro() ? "Sim" : "Não";
            
            // Extrair incompatibilidades do toString
            String incompativel = "";
            String fullText = enc.toString();
            if (fullText.contains("⚠ Incompatível com:")) {
                incompativel = fullText.split("⚠ Incompatível com: ")[1].split("\n")[0].trim();
            }
            
            // Extrair itens primários e secundários
            String itensPrimarios = "";
            String itensSecundarios = "";
            if (fullText.contains("► Item Primário:")) {
                itensPrimarios = fullText.split("► Item Primário: ")[1].split("\n")[0].trim();
            }
            if (fullText.contains("► Item Secundário:")) {
                itensSecundarios = fullText.split("► Item Secundário: ")[1].split("\n")[0].trim();
            }
            
            int nivelMaximo = Integer.parseInt(fullText.split("Nível Máximo: ")[1].split("\n")[0].trim());
            int peso = enc.getPeso();

            model.addRow(new Object[]{
                nome, descricao, tesouro, incompativel,
                nivelMaximo, itensPrimarios, itensSecundarios, peso
            });
        }

        // Criar tabela
        JTable table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 12));
        table.setRowHeight(60);
        table.setBackground(new Color(50, 50, 50));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(100, 100, 100));
        table.setSelectionBackground(MinecraftWikiGUI.MINECRAFT_BLUE);
        table.setSelectionForeground(Color.WHITE);

        // Configurar larguras das colunas
        table.getColumnModel().getColumn(0).setPreferredWidth(180); // Nome
        table.getColumnModel().getColumn(1).setPreferredWidth(300); // Descrição
        table.getColumnModel().getColumn(2).setPreferredWidth(80);  // Tesouro
        table.getColumnModel().getColumn(3).setPreferredWidth(200); // Incompatível
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Nível
        table.getColumnModel().getColumn(5).setPreferredWidth(150); // Item primário
        table.getColumnModel().getColumn(6).setPreferredWidth(150); // Item secundário
        table.getColumnModel().getColumn(7).setPreferredWidth(60);  // Peso

        // Configurar cabeçalho
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(30, 30, 30));
        header.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        header.setFont(new Font("SansSerif", Font.BOLD, 13));

        // Configurar renderizador de células com cores por linha
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (!isSelected) {
                    // Colorir linhas de encantamentos tesouro
                    String tesouro = (String) table.getValueAt(row, 2);
                    if ("Sim".equals(tesouro)) {
                        c.setBackground(new Color(85, 255, 85, 30)); // Verde translúcido
                    } else {
                        c.setBackground(new Color(255, 85, 85, 20)); // Vermelho translúcido
                    }
                }
                
                ((JLabel) c).setVerticalAlignment(SwingConstants.TOP);
                return c;
            }
        };

        // Aplicar renderizador em todas as colunas
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        // Adicionar label de contagem
        JLabel countLabel = new JLabel("✅ " + enchantments.size() + " encantamento(s) encontrado(s)");
        countLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        countLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        countLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        resultsPanel.setLayout(new BorderLayout());
        resultsPanel.add(countLabel, BorderLayout.NORTH);
        resultsPanel.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private String wrapText(String text, int maxLength) {
        if (text.length() <= maxLength) return text;
        
        StringBuilder wrapped = new StringBuilder("<html>");
        String[] words = text.split(" ");
        int lineLength = 0;
        
        for (String word : words) {
            if (lineLength + word.length() > maxLength) {
                wrapped.append("<br>");
                lineLength = 0;
            }
            wrapped.append(word).append(" ");
            lineLength += word.length() + 1;
        }
        
        wrapped.append("</html>");
        return wrapped.toString();
    }

    private JPanel createEnchantmentCard(Encantamento enchantment) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(new Color(60, 60, 60));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BLUE, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));

        // Painel com imagem do livro encantado à esquerda
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        leftPanel.setBorder(new EmptyBorder(0, 0, 0, 15));

        // Ícone do livro encantado
        JLabel bookIcon = new JLabel(ImageManager.getItemIcon("ENCHANTED_BOOK", 48));
        bookIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(bookIcon);
        leftPanel.add(Box.createVerticalStrut(5));

        JLabel typeLabel = new JLabel("Encantamento");
        typeLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        typeLabel.setForeground(Color.LIGHT_GRAY);
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(typeLabel);

        card.add(leftPanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(enchantment.getNome());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        nameLabel.setForeground(MinecraftWikiGUI.MINECRAFT_BLUE.brighter());

        JLabel editionLabel = new JLabel("Edição: " + enchantment.getEdicao().getDisplayName());
        editionLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        editionLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);

        // Extrair descrição do toString
        String fullText = enchantment.toString();
        String descricao = "";
        if (fullText.contains("Descrição: ")) {
            descricao = fullText.split("Descrição: ")[1].split("\\n")[0].trim();
        }

        JLabel descLabel = new JLabel("📜 " + descricao);
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descLabel.setForeground(Color.WHITE);

        // Badge de tesouro
        if (enchantment.isTesouro()) {
            JLabel treasureLabel = new JLabel("💎 TESOURO");
            treasureLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
            treasureLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
            treasureLabel.setOpaque(true);
            treasureLabel.setBackground(new Color(255, 215, 0, 50));
            treasureLabel.setBorder(new EmptyBorder(2, 5, 2, 5));
            infoPanel.add(treasureLabel);
            infoPanel.add(Box.createVerticalStrut(5));
        }

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(editionLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(descLabel);

        card.add(infoPanel, BorderLayout.CENTER);

        // Adicionar botão "Ver Detalhes"
        JButton detailsButton = new JButton("📋 Ver Detalhes");
        detailsButton.setBackground(MinecraftWikiGUI.MINECRAFT_GREEN);
        detailsButton.setForeground(Color.WHITE);
        detailsButton.setFocusPainted(false);
        detailsButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        detailsButton.addActionListener(e -> showEnchantmentDetails(enchantment));

        card.add(detailsButton, BorderLayout.EAST);

        return card;
    }

    private void showEnchantingGuide() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Guia de Encantamento", true);
        dialog.setSize(800, 600);
        dialog.setLocationRelativeTo(this);

        JTextArea guideArea = new JTextArea();
        guideArea.setEditable(false);
        guideArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        guideArea.setBackground(new Color(40, 40, 40));
        guideArea.setForeground(Color.WHITE);
        guideArea.setBorder(new EmptyBorder(20, 20, 20, 20));
        guideArea.setLineWrap(true);
        guideArea.setWrapStyleWord(true);

        String guide = """
            📌 GUIA COMPLETO DE ENCANTAMENTO
            
            ══════════════════════════════════════════════════════════
            
            EQUIPAMENTO NECESSÁRIO:
            • Mesa de Encantamento (Enchanting Table)
            • Estantes de Livros (até 15 ao redor da mesa)
            • Lápis-lazúli (1-3 por encantamento)
            • Níveis de Experiência (XP)
            
            ══════════════════════════════════════════════════════════
            
            COMO MONTAR A MESA DE ENCANTAMENTO:
            
            Receita da Mesa:
            • 4 Obsidiana
            • 2 Diamantes
            • 1 Livro
            
            Para nível máximo de encantamento:
            • Coloque 15 estantes ao redor da mesa
            • Deve haver 1 bloco de distância entre mesa e estantes
            • As estantes devem estar na mesma altura ou 1 bloco acima
            
            ══════════════════════════════════════════════════════════
            
            NÍVEIS DE ENCANTAMENTO:
            
            • Nível 1-8: Encantamentos básicos
            • Nível 9-19: Encantamentos intermediários
            • Nível 20-30: Encantamentos máximos e mais raros
            
            Com 15 estantes, você pode obter encantamentos nível 30!
            
            ══════════════════════════════════════════════════════════
            
            BIGORNA (Anvil):
            
            A bigorna permite:
            • Combinar dois itens encantados
            • Aplicar livros encantados em itens
            • Reparar itens danificados
            • Renomear itens
            
            ⚠️ Cada uso da bigorna custa níveis de XP crescentes
            ⚠️ Após muito uso, o item pode ficar "Muito Caro"
            
            ══════════════════════════════════════════════════════════
            
            ENCANTAMENTOS INCOMPATÍVEIS:
            
            Você NÃO pode combinar:
            • Afiação ↔ Golpe Artesão ↔ Ruína dos Artrópodes
            • Fortuna ↔ Toque Suave
            • Proteção, Prot. Fogo, Prot. Projéteis, Prot. Explosão
            • Infinidade ↔ Remendo (em arcos)
            • Andarilho das Profundezas ↔ Passos Gélidos
            • Lealdade ↔ Maresia (tridente)
            • Perfurante ↔ Rajada (besta)
            
            ══════════════════════════════════════════════════════════
            
            COMO OBTER LIVROS ENCANTADOS:
            
            1. Pescar (com vara encantada de Sorte do Mar)
            2. Trocar com Aldeões Bibliotecários
            3. Encontrar em baús de:
               • Templos do Deserto
               • Fortalezas
               • Cidades do End
               • Mansões da Floresta
               • Masmorras
            4. Encantar livros na mesa de encantamento
            
            ══════════════════════════════════════════════════════════
            
            DICAS PRO:
            
            ✓ Remendo é essencial para itens valiosos
            ✓ Combine itens na bigorna para melhores resultados
            ✓ Faça uma fazenda de XP para encantar mais rápido
            ✓ Proteja sua mesa com estantes removíveis (tocha embaixo)
            ✓ Guarde livros raros para itens especiais
            
            ══════════════════════════════════════════════════════════
            """;

        guideArea.setText(guide);
        guideArea.setCaretPosition(0);

        JScrollPane guideScrollPane = new JScrollPane(guideArea);
        guideScrollPane.setBorder(null);

        dialog.add(guideScrollPane);
        dialog.setVisible(true);
    }

    private void showEnchantmentDetails(Encantamento enchantment) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), 
            "Detalhes: " + enchantment.getNome(), true);
        dialog.setSize(800, 700);
        dialog.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(40, 40, 40));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Painel de informações com imagem do livro
        JPanel infoPanel = new JPanel(new BorderLayout(15, 15));
        infoPanel.setBackground(new Color(50, 50, 50));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BLUE, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));

        // Imagem do livro encantado à esquerda
        JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.Y_AXIS));
        iconPanel.setOpaque(false);
        
        JLabel bookIcon = new JLabel(ImageManager.getItemIcon("ENCHANTED_BOOK", 64));
        bookIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconPanel.add(bookIcon);

        infoPanel.add(iconPanel, BorderLayout.WEST);

        // Informações do encantamento
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setOpaque(false);

        JLabel titleLabel = new JLabel(enchantment.getNome());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_BLUE.brighter());
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel editionLabel = new JLabel("📦 Edição: " + enchantment.getEdicao().getDisplayName());
        editionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        editionLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        editionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Extrair informações do toString
        String fullText = enchantment.toString();
        String descricao = "";
        int nivelMaximo = 1;
        
        if (fullText.contains("Descrição: ")) {
            descricao = fullText.split("Descrição: ")[1].split("\\n")[0].trim();
        }
        if (fullText.contains("Nível Máximo: ")) {
            nivelMaximo = Integer.parseInt(fullText.split("Nível Máximo: ")[1].split("\\n")[0].trim());
        }

        JLabel descLabel = new JLabel("📜 " + descricao);
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descLabel.setForeground(Color.WHITE);
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel levelLabel = new JLabel("⭐ Nível Máximo: " + nivelMaximo);
        levelLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        levelLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        levelLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel weightLabel = new JLabel("⚖️ Peso: " + enchantment.getPeso());
        weightLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        weightLabel.setForeground(Color.LIGHT_GRAY);
        weightLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        if (enchantment.isTesouro()) {
            JLabel treasureLabel = new JLabel("💎 ENCANTAMENTO TESOURO");
            treasureLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            treasureLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
            treasureLabel.setOpaque(true);
            treasureLabel.setBackground(new Color(255, 215, 0, 80));
            treasureLabel.setBorder(new EmptyBorder(5, 10, 5, 10));
            treasureLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            detailsPanel.add(treasureLabel);
            detailsPanel.add(Box.createVerticalStrut(10));
        }

        detailsPanel.add(titleLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(editionLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(descLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(levelLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(weightLabel);

        infoPanel.add(detailsPanel, BorderLayout.CENTER);

        // Painel de itens compatíveis com imagens - REORGANIZADO
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(new Color(35, 35, 35));
        itemsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_GREEN, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel itemsTitle = new JLabel("🛠️ PARA QUE SERVE E ITENS COMPATÍVEIS");
        itemsTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        itemsTitle.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        itemsTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        itemsPanel.add(itemsTitle);
        itemsPanel.add(Box.createVerticalStrut(15));

        // Adicionar seção "FINALIDADE"
        String purpose = getEnchantmentPurpose(enchantment.getNome());
        if (!purpose.isEmpty()) {
            JPanel purposePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
            purposePanel.setOpaque(false);
            
            JLabel purposeIcon = new JLabel("🎯");
            purposeIcon.setFont(new Font("SansSerif", Font.BOLD, 14));
            
            JLabel purposeLabel = new JLabel("<html><b>FINALIDADE:</b> " + purpose + "</html>");
            purposeLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            purposeLabel.setForeground(Color.WHITE);
            
            purposePanel.add(purposeIcon);
            purposePanel.add(purposeLabel);
            
            itemsPanel.add(purposePanel);
            itemsPanel.add(Box.createVerticalStrut(15));
        }

        // Extrair itens primários e secundários com categorização
        if (fullText.contains("► Item Primário:")) {
            String itensPrimarios = fullText.split("► Item Primário: ")[1].split("\\n")[0].trim();
            addCategorizedItems(itemsPanel, itensPrimarios, enchantment.getNome());
        }

        if (fullText.contains("► Item Secundário:")) {
            String itensSecundarios = fullText.split("► Item Secundário: ")[1].split("\\n")[0].trim();
            itemsPanel.add(Box.createVerticalStrut(10));
            JLabel secondaryLabel = new JLabel("► Item Secundário (via Bigorna):");
            secondaryLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
            secondaryLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
            secondaryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            itemsPanel.add(secondaryLabel);
            itemsPanel.add(Box.createVerticalStrut(8));
            addItemCategoryWithIcons(itemsPanel, "", itensSecundarios);
        }

        // Incompatibilidades
        if (fullText.contains("⚠ Incompatível com:")) {
            itemsPanel.add(Box.createVerticalStrut(15));
            JLabel incompatLabel = new JLabel("⚠️ INCOMPATIBILIDADES:");
            incompatLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            incompatLabel.setForeground(MinecraftWikiGUI.MINECRAFT_RED);
            incompatLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            itemsPanel.add(incompatLabel);
            itemsPanel.add(Box.createVerticalStrut(10));

            String incompativeis = fullText.split("⚠ Incompatível com: ")[1].split("\\n")[0].trim();
            JLabel incompatText = new JLabel("  " + incompativeis);
            incompatText.setFont(new Font("SansSerif", Font.PLAIN, 13));
            incompatText.setForeground(Color.YELLOW);
            incompatText.setAlignmentX(Component.LEFT_ALIGNMENT);
            itemsPanel.add(incompatText);
        }

        // Área de informações adicionais
        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        detailsArea.setBackground(new Color(30, 30, 30));
        detailsArea.setForeground(Color.WHITE);
        detailsArea.setBorder(new EmptyBorder(15, 15, 15, 15));
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);

        StringBuilder details = new StringBuilder();
        details.append("\n╔═══════════════════════════════════════════════════════╗\n");
        details.append("║           COMO OBTER ESTE ENCANTAMENTO               ║\n");
        details.append("╠═══════════════════════════════════════════════════════╣\n");
        details.append("║                                                       ║\n");

        if (enchantment.isTesouro()) {
            details.append("║  ⚠️  ENCANTAMENTO TESOURO - Não disponível em mesa   ║\n");
            details.append("║                                                       ║\n");
            details.append("║  FORMAS DE OBTER:                                     ║\n");
            details.append("║                                                       ║\n");
            details.append("║  • Pescaria (Vara com Sorte do Mar)                  ║\n");
            details.append("║  • Baús de estruturas (Templos, Fortalezas, etc.)    ║\n");
            details.append("║  • Troca com Aldeões Bibliotecários                  ║\n");
            details.append("║  • Drops de mobs específicos                          ║\n");
        } else {
            details.append("║  ✓ Disponível em Mesa de Encantamento                ║\n");
            details.append("║                                                       ║\n");
            details.append("║  FORMAS DE OBTER:                                     ║\n");
            details.append("║                                                       ║\n");
            details.append("║  • Mesa de Encantamento (com lápis-lazúli e XP)      ║\n");
            details.append("║  • Pescaria                                           ║\n");
            details.append("║  • Baús de estruturas                                 ║\n");
            details.append("║  • Troca com Aldeões                                  ║\n");
        }

        details.append("║                                                       ║\n");
        details.append("╚═══════════════════════════════════════════════════════╝\n\n");

        // Dicas específicas
        details.append("\n💡 DICAS E INFORMAÇÕES:\n\n");
        details.append("• Peso " + enchantment.getPeso() + " indica a raridade\n");
        details.append("  (peso maior = mais comum)\n\n");
        details.append("• Use Bigorna para combinar encantamentos\n\n");
        details.append("• Remendo pode manter itens para sempre\n\n");
        details.append("• Biblioteca com 15 estantes dá encantamentos nível 30\n\n");
        
        if (nivelMaximo > 1) {
            details.append("• Este encantamento tem " + nivelMaximo + " níveis\n");
            details.append("  Níveis maiores = efeito mais forte\n\n");
        }

        if (fullText.contains("⚠ Incompatível com:")) {
            details.append("⚠️ ATENÇÃO: Este encantamento é incompatível com\n");
            details.append("   outros encantamentos específicos.\n");
            details.append("   Verifique a seção de incompatibilidades acima.\n");
        }

        detailsArea.setText(details.toString());
        detailsArea.setCaretPosition(0);

        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
        detailsScrollPane.setBorder(BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BLUE, 2));

        // Layout principal
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setOpaque(false);
        centerPanel.add(itemsPanel, BorderLayout.NORTH);
        centerPanel.add(detailsScrollPane, BorderLayout.CENTER);

        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Botão fechar
        JButton closeButton = new JButton("✖️ Fechar");
        closeButton.setBackground(MinecraftWikiGUI.MINECRAFT_RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        closeButton.addActionListener(e -> dialog.dispose());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(40, 40, 40));
        buttonPanel.add(closeButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(mainPanel);
        dialog.setVisible(true);
    }

    /**
     * Adiciona uma categoria de itens com ícones.
     */
    private void addItemCategoryWithIcons(JPanel panel, String category, String itemsText) {
        JLabel categoryLabel = new JLabel("► " + category + ":");
        categoryLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        categoryLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(categoryLabel);
        panel.add(Box.createVerticalStrut(8));

        // Criar painel para os itens
        JPanel itemsRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        itemsRow.setOpaque(false);

        // Parse dos itens (separados por vírgula)
        String[] items = itemsText.split(",");
        for (String item : items) {
            item = item.trim();
            if (!item.isEmpty()) {
                String iconName = getIconNameForItem(item);
                
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
                itemPanel.setOpaque(false);
                itemPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

                JLabel icon = new JLabel(ImageManager.getItemIcon(iconName, 32));
                icon.setAlignmentX(Component.CENTER_ALIGNMENT);
                
                JLabel nameLabel = new JLabel(item);
                nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
                nameLabel.setForeground(Color.WHITE);
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                itemPanel.add(icon);
                itemPanel.add(Box.createVerticalStrut(3));
                itemPanel.add(nameLabel);

                itemsRow.add(itemPanel);
            }
        }

        panel.add(itemsRow);
        panel.add(Box.createVerticalStrut(10));
    }

    /**
     * Retorna o nome do ícone baseado no nome do item.
     */
    private String getIconNameForItem(String itemName) {
        String normalized = itemName.toLowerCase().replace(" ", "_");
        
        // Mapeamento de nomes comuns
        if (normalized.contains("espada")) return "DIAMOND_SWORD";
        if (normalized.contains("picareta")) return "DIAMOND_PICKAXE";
        if (normalized.contains("machado")) return "DIAMOND_AXE";
        if (normalized.contains("pá")) return "DIAMOND_SHOVEL";
        if (normalized.contains("enxada")) return "DIAMOND_HOE";
        if (normalized.contains("arco")) return "BOW";
        if (normalized.contains("besta")) return "CROSSBOW";
        if (normalized.contains("tridente")) return "TRIDENT";
        if (normalized.contains("capacete")) return "DIAMOND_HELMET";
        if (normalized.contains("peitoral")) return "DIAMOND_CHESTPLATE";
        if (normalized.contains("calça")) return "DIAMOND_LEGGINGS";
        if (normalized.contains("bota")) return "DIAMOND_BOOTS";
        if (normalized.contains("elmo")) return "TURTLE_HELMET";
        if (normalized.contains("vara")) return "FISHING_ROD";
        if (normalized.contains("tesoura")) return "SHEARS";
        if (normalized.contains("pederneira")) return "FLINT_AND_STEEL";
        if (normalized.contains("cenoura")) return "CARROT_ON_A_STICK";
        if (normalized.contains("escudo")) return "SHIELD";
        if (normalized.contains("livro")) return "BOOK";
        
        // Default: tentar usar o nome normalizado
        return normalized.toUpperCase();
    }
}

