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
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel nameLabel = new JLabel("✨ " + enchantment.getNome());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        nameLabel.setForeground(MinecraftWikiGUI.MINECRAFT_BLUE.brighter());

        JLabel editionLabel = new JLabel("Edição: " + enchantment.getEdicao().getDisplayName());
        editionLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        editionLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(editionLabel);

        card.add(infoPanel, BorderLayout.CENTER);

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
}

