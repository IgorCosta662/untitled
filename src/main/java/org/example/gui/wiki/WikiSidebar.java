package org.example.gui.wiki;

import org.example.MinecraftWiki;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class WikiSidebar extends JPanel {
    private WikiMainWindow parent;
    private MinecraftWiki wiki;
    private JPanel historyPanel;
    private JPanel favoritesPanel;

    public WikiSidebar(WikiMainWindow parent, MinecraftWiki wiki) {
        this.parent = parent;
        this.wiki = wiki;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        setBackground(WikiMainWindow.WIKI_SIDEBAR_BG);
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, WikiMainWindow.WIKI_BORDER));
        setPreferredSize(new Dimension(250, 0));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(WikiMainWindow.WIKI_SIDEBAR_BG);
        contentPanel.setBorder(new EmptyBorder(15, 10, 15, 10));

        // Navegação principal
        contentPanel.add(createSection("📚 NAVEGAÇÃO", createNavigationLinks()));
        contentPanel.add(Box.createVerticalStrut(20));

        // Categorias
        contentPanel.add(createSection("📂 CATEGORIAS", createCategoryLinks()));
        contentPanel.add(Box.createVerticalStrut(20));

        // Favoritos
        favoritesPanel = new JPanel();
        favoritesPanel.setLayout(new BoxLayout(favoritesPanel, BoxLayout.Y_AXIS));
        favoritesPanel.setBackground(WikiMainWindow.WIKI_SIDEBAR_BG);
        contentPanel.add(createSection("⭐ FAVORITOS", favoritesPanel));
        contentPanel.add(Box.createVerticalStrut(20));

        // Histórico
        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        historyPanel.setBackground(WikiMainWindow.WIKI_SIDEBAR_BG);
        contentPanel.add(createSection("📖 HISTÓRICO", historyPanel));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createSection(String title, JPanel content) {
        JPanel section = new JPanel(new BorderLayout(0, 10));
        section.setBackground(WikiMainWindow.WIKI_SIDEBAR_BG);
        section.setAlignmentX(Component.LEFT_ALIGNMENT);
        section.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        titleLabel.setForeground(WikiMainWindow.WIKI_TEXT);
        titleLabel.setBorder(new EmptyBorder(0, 5, 5, 0));

        JSeparator separator = new JSeparator();
        separator.setForeground(WikiMainWindow.WIKI_BORDER);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(WikiMainWindow.WIKI_SIDEBAR_BG);
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(separator, BorderLayout.SOUTH);

        section.add(titlePanel, BorderLayout.NORTH);
        section.add(content, BorderLayout.CENTER);

        return section;
    }

    private JPanel createNavigationLinks() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(WikiMainWindow.WIKI_SIDEBAR_BG);

        panel.add(createLink("🏠 Página Inicial", () -> parent.showHomePage()));
        panel.add(createLink("📦 Todos os Itens", () -> parent.showCategoryPage("Todos os Itens")));
        panel.add(createLink("⚗️ Todas as Poções", () -> parent.showCategoryPage("Todas as Poções")));
        panel.add(createLink("✨ Todos os Encantamentos", () -> parent.showCategoryPage("Todos os Encantamentos")));
        panel.add(createLink("🔨 Simulador de Crafting", () -> showCraftingSimulator()));
        panel.add(createLink("📊 Estatísticas", () -> showStatistics()));

        return panel;
    }

    private JPanel createCategoryLinks() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(WikiMainWindow.WIKI_SIDEBAR_BG);

        // Categorias de itens
        panel.add(createLink("⛏️ Ferramentas", () -> parent.showCategoryPage("Ferramenta")));
        panel.add(createLink("⚔️ Armas", () -> parent.showCategoryPage("Arma")));
        panel.add(createLink("🛡️ Armaduras", () -> parent.showCategoryPage("Armadura")));
        panel.add(createLink("🍖 Alimentos", () -> parent.showCategoryPage("Alimento")));
        panel.add(createLink("🧱 Blocos", () -> parent.showCategoryPage("Bloco Utilitário")));
        panel.add(createLink("⚡ Redstone", () -> parent.showCategoryPage("Redstone")));
        panel.add(createLink("🚂 Transporte", () -> parent.showCategoryPage("Transporte")));
        panel.add(createLink("💎 Especiais", () -> parent.showCategoryPage("Item Especial")));

        return panel;
    }

    private JLabel createLink(String text, Runnable action) {
        JLabel link = new JLabel(text);
        link.setFont(new Font("SansSerif", Font.PLAIN, 12));
        link.setForeground(WikiMainWindow.WIKI_LINK);
        link.setCursor(new Cursor(Cursor.HAND_CURSOR));
        link.setBorder(new EmptyBorder(3, 10, 3, 10));
        link.setAlignmentX(Component.LEFT_ALIGNMENT);
        link.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));

        link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                action.run();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                link.setForeground(WikiMainWindow.WIKI_LINK_HOVER);
                link.setBackground(new Color(240, 240, 240));
                link.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                link.setForeground(WikiMainWindow.WIKI_LINK);
                link.setOpaque(false);
            }
        });

        return link;
    }

    public void updateHistory(List<String> history) {
        historyPanel.removeAll();

        if (history.isEmpty()) {
            JLabel empty = new JLabel("  Nenhuma página visitada");
            empty.setFont(new Font("SansSerif", Font.ITALIC, 11));
            empty.setForeground(Color.GRAY);
            historyPanel.add(empty);
        } else {
            int count = 0;
            for (String page : history) {
                if (count++ >= 10) break; // Limitar a 10 itens
                historyPanel.add(createLink("  📄 " + page, () -> parent.performSearch(page)));
            }
        }

        historyPanel.revalidate();
        historyPanel.repaint();
    }

    public void updateFavorites(List<String> favorites) {
        favoritesPanel.removeAll();

        if (favorites.isEmpty()) {
            JLabel empty = new JLabel("  Nenhum favorito ainda");
            empty.setFont(new Font("SansSerif", Font.ITALIC, 11));
            empty.setForeground(Color.GRAY);
            favoritesPanel.add(empty);
        } else {
            for (String page : favorites) {
                favoritesPanel.add(createLink("  ⭐ " + page, () -> parent.performSearch(page)));
            }
        }

        favoritesPanel.revalidate();
        favoritesPanel.repaint();
    }

    private void showCraftingSimulator() {
        JOptionPane.showMessageDialog(
            this,
            "Simulador de Crafting está disponível no menu principal!\nClique em 'Simulador' na navegação.",
            "Simulador de Crafting",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showStatistics() {
        String stats = String.format("""
            📊 ESTATÍSTICAS DA WIKI
            
            📦 Itens: %d
            ⚗️ Poções: %d
            ✨ Encantamentos: %d
            ━━━━━━━━━━━━━━━━━━━━━━
            🎯 Total: %d entradas
            
            ✅ Base de dados completa
            🔄 Atualizado em 2025
            💚 Java & Bedrock Edition
            """,
            wiki.listarTodosItens().size(),
            wiki.listarTodasPocoes().size(),
            wiki.listarTodosEncantamentos().size(),
            wiki.listarTodosItens().size() +
            wiki.listarTodasPocoes().size() +
            wiki.listarTodosEncantamentos().size()
        );

        JTextArea textArea = new JTextArea(stats);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        textArea.setBorder(new EmptyBorder(15, 15, 15, 15));

        JOptionPane.showMessageDialog(
            this,
            textArea,
            "Estatísticas",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}

