package org.example.gui.wiki;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.example.MinecraftWiki;

public class WikiMainWindow extends JFrame {
    private static MinecraftWiki wiki;
    private JSplitPane splitPane;
    private WikiSidebar sidebar;
    private WikiContentPanel contentArea;
    private WikiNavigationBar navigationBar;
    private final List<String> history;
    private final List<String> favorites;

    // Cores do tema Minecraft Wiki
    public static final Color WIKI_BG = new Color(248, 249, 250);
    public static final Color WIKI_SIDEBAR_BG = new Color(255, 255, 255);
    public static final Color WIKI_CONTENT_BG = new Color(255, 255, 255);
    public static final Color WIKI_BORDER = new Color(220, 220, 220);
    public static final Color WIKI_HEADER = new Color(51, 102, 204);
    public static final Color WIKI_LINK = new Color(0, 102, 204);
    public static final Color WIKI_LINK_HOVER = new Color(0, 51, 153);
    public static final Color WIKI_TEXT = new Color(32, 33, 34);
    public static final Color MINECRAFT_GREEN = new Color(67, 176, 42);

    public WikiMainWindow() {
        wiki = new MinecraftWiki();
        history = new ArrayList<>();
        favorites = new ArrayList<>();
        setupUI();
    }

    private void setupUI() {
        setTitle("Minecraft Wiki - Edição Completa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 900);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());
        getContentPane().setBackground(WIKI_BG);

        // Barra de navegação superior
        navigationBar = new WikiNavigationBar(this);
        add(navigationBar, BorderLayout.NORTH);

        // Painel central com sidebar e conteúdo
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(250);
        splitPane.setDividerSize(1);
        splitPane.setBorder(null);

        // Sidebar esquerda
        sidebar = new WikiSidebar(this, wiki);
        splitPane.setLeftComponent(sidebar);

        // Área de conteúdo principal
        contentArea = new WikiContentPanel(this, wiki);
        splitPane.setRightComponent(new JScrollPane(contentArea));

        add(splitPane, BorderLayout.CENTER);

        // Footer
        add(createFooter(), BorderLayout.SOUTH);

        // Carregar página inicial
        showHomePage();
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        footer.setBackground(new Color(240, 240, 240));
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, WIKI_BORDER));

        JLabel credit = new JLabel("Minecraft Wiki - Desenvolvido com Java | © 2025");
        credit.setFont(new Font("SansSerif", Font.PLAIN, 11));
        credit.setForeground(Color.GRAY);

        footer.add(credit);
        return footer;
    }

    public void showHomePage() {
        contentArea.showHomePage();
        addToHistory("Página Inicial");
    }

    public void showItemPage(String itemName) {
        contentArea.showItemPage(itemName);
        addToHistory(itemName);
    }

    public void showPotionPage(String potionName) {
        contentArea.showPotionPage(potionName);
        addToHistory(potionName);
    }

    public void showEnchantmentPage(String enchantmentName) {
        contentArea.showEnchantmentPage(enchantmentName);
        addToHistory(enchantmentName);
    }

    public void showCategoryPage(String category) {
        contentArea.showCategoryPage(category);
        addToHistory("Categoria: " + category);
    }

    public void performSearch(String query) {
        contentArea.showSearchResults(query);
        addToHistory("Busca: " + query);
    }

    private void addToHistory(String page) {
        history.add(0, page);
        if (history.size() > 20) {
            history.remove(20);
        }
        sidebar.updateHistory(history);
    }

    public void toggleFavorite(String page) {
        if (favorites.contains(page)) {
            favorites.remove(page);
        } else {
            favorites.add(page);
        }
        sidebar.updateFavorites(favorites);
    }

    public boolean isFavorite(String page) {
        return favorites.contains(page);
    }

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }

    public List<String> getFavorites() {
        return new ArrayList<>(favorites);
    }

    public static MinecraftWiki getWiki() {
        return wiki;
    }

    public static void main(String[] args) {
        // Configurar Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.err.println("Não foi possível configurar o Look and Feel: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            WikiMainWindow window = new WikiMainWindow();
            window.setVisible(true);
        });
    }
}

