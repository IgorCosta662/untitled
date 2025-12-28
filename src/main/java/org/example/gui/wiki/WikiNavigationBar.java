package org.example.gui.wiki;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class WikiNavigationBar extends JPanel {
    private WikiMainWindow parent;
    private JTextField searchField;
    private JButton backButton;

    public WikiNavigationBar(WikiMainWindow parent) {
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        setBackground(WikiMainWindow.WIKI_HEADER);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, WikiMainWindow.MINECRAFT_GREEN),
            new EmptyBorder(10, 15, 10, 15)
        ));

        // Logo e título
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        leftPanel.setOpaque(false);

        // Botão de voltar
        backButton = new JButton("← Voltar");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        backButton.setBackground(new Color(100, 100, 100));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setEnabled(false);
        backButton.addActionListener(e -> parent.goBack());

        JLabel logo = new JLabel("🎮");
        logo.setFont(new Font("Dialog", Font.PLAIN, 28));

        JLabel title = new JLabel("MINECRAFT WIKI");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setCursor(new Cursor(Cursor.HAND_CURSOR));
        title.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parent.showHomePage();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                title.setForeground(WikiMainWindow.MINECRAFT_GREEN.brighter());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                title.setForeground(Color.WHITE);
            }
        });

        leftPanel.add(backButton);
        leftPanel.add(logo);
        leftPanel.add(title);

        // Barra de busca central
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        centerPanel.setOpaque(false);

        searchField = new JTextField(35);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 1),
            new EmptyBorder(5, 10, 5, 10)
        ));
        searchField.addActionListener(e -> performSearch());

        JButton searchButton = new JButton("🔍 Buscar");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        searchButton.setBackground(WikiMainWindow.MINECRAFT_GREEN);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.addActionListener(e -> performSearch());

        centerPanel.add(searchField);
        centerPanel.add(searchButton);

        // Menu direito
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setOpaque(false);

        JButton homeBtn = createNavButton("🏠 Início");
        homeBtn.addActionListener(e -> parent.showHomePage());

        JButton randomBtn = createNavButton("🎲 Aleatório");
        randomBtn.addActionListener(e -> showRandomPage());

        JButton helpBtn = createNavButton("❓ Ajuda");
        helpBtn.addActionListener(e -> showHelp());

        rightPanel.add(homeBtn);
        rightPanel.add(randomBtn);
        rightPanel.add(helpBtn);

        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btn.setForeground(Color.WHITE);
        btn.setBackground(WikiMainWindow.WIKI_HEADER);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(WikiMainWindow.WIKI_HEADER.brighter());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(WikiMainWindow.WIKI_HEADER);
            }
        });

        return btn;
    }

    private void performSearch() {
        String query = searchField.getText().trim();
        if (!query.isEmpty()) {
            parent.performSearch(query);
            searchField.selectAll();
        }
    }

    private void showRandomPage() {
        java.util.List<String> pages = new java.util.ArrayList<>();
        WikiMainWindow.getWiki().listarTodosItens().forEach(item -> pages.add(item.getNome()));
        WikiMainWindow.getWiki().listarTodasPocoes().forEach(potion -> pages.add(potion.getNome()));
        WikiMainWindow.getWiki().listarTodosEncantamentos().forEach(enc -> pages.add(enc.getNome()));

        if (!pages.isEmpty()) {
            String randomPage = pages.get(new java.util.Random().nextInt(pages.size()));
            parent.performSearch(randomPage);
        }
    }

    private void showHelp() {
        String helpText = """
            🎮 GUIA DE USO DA MINECRAFT WIKI
            
            📖 NAVEGAÇÃO:
            • Use a barra de busca para encontrar itens, poções ou encantamentos
            • Clique em links na sidebar para navegar por categorias
            • Use o botão "Aleatório" para descobrir conteúdo novo
            
            🔍 BUSCA:
            • Digite o nome completo ou parcial
            • Resultados aparecem instantaneamente
            • Clique em um resultado para ver detalhes completos
            
            ⭐ FAVORITOS:
            • Clique na estrela para adicionar aos favoritos
            • Acesse rapidamente seus itens favoritos na sidebar
            
            📚 HISTÓRICO:
            • Suas últimas 20 páginas visitadas aparecem na sidebar
            • Clique para revisitar rapidamente
            
            💡 DICAS:
            • Explore as categorias na sidebar
            • Veja páginas relacionadas no final de cada artigo
            • Use Ctrl+F para buscar na página atual
            """;

        JTextArea textArea = new JTextArea(helpText);
        textArea.setEditable(false);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(new EmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "Ajuda - Minecraft Wiki",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void setSearchText(String text) {
        searchField.setText(text);
    }

    public void updateBackButton(boolean enabled) {
        backButton.setEnabled(enabled);
        if (enabled) {
            backButton.setBackground(WikiMainWindow.MINECRAFT_GREEN);
        } else {
            backButton.setBackground(new Color(100, 100, 100));
        }
    }
}

