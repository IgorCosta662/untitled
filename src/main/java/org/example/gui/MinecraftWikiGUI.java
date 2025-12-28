package org.example.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.example.MinecraftWiki;

public class MinecraftWikiGUI extends JFrame {
    private static MinecraftWiki wiki;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Stack<String> navigationHistory;
    private String currentPanel;

    // Cores temáticas do Minecraft
    public static final Color MINECRAFT_GREEN = new Color(85, 255, 85);
    public static final Color MINECRAFT_DARK_GREEN = new Color(0, 170, 0);
    public static final Color MINECRAFT_GRAY = new Color(139, 139, 139);
    public static final Color MINECRAFT_DARK_GRAY = new Color(85, 85, 85);
    public static final Color MINECRAFT_BROWN = new Color(139, 90, 43);
    public static final Color MINECRAFT_GOLD = new Color(255, 170, 0);
    public static final Color MINECRAFT_BLUE = new Color(85, 85, 255);
    public static final Color MINECRAFT_PURPLE = new Color(170, 0, 170);
    public static final Color MINECRAFT_RED = new Color(255, 85, 85);

    public MinecraftWikiGUI() {
        wiki = new MinecraftWiki();
        navigationHistory = new Stack<>();
        currentPanel = "HOME";
        setupUI();
    }

    private void setupUI() {
        setTitle("Minecraft Wiki - Edição Completa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // Layout com CardLayout para trocar entre telas
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Adicionar todas as telas
        mainPanel.add(new HomePanel(this, wiki), "HOME");
        mainPanel.add(new ItemsPanel(wiki), "ITEMS");
        mainPanel.add(new PotionsPanel(wiki), "POTIONS");
        mainPanel.add(new EnchantmentsPanel(wiki), "ENCHANTMENTS");
        mainPanel.add(new ItemsPanel(wiki, true), "ARMOR");  // true = mostrar apenas armaduras
        mainPanel.add(new CraftingSimulatorPanel(), "CRAFTING");
        mainPanel.add(new StatisticsPanel(wiki), "STATISTICS");
        mainPanel.add(new APITestPanel(), "API_TEST");  // Novo painel de teste de APIs
        mainPanel.add(new AboutPanel(this), "ABOUT");

        add(mainPanel);

        // Configurar atalhos de teclado
        setupKeyboardShortcuts();

        // Mostrar tela inicial
        showPanel("HOME");
    }
    
    private void setupKeyboardShortcuts() {
        // Atalhos de teclado globais
        javax.swing.KeyStroke homeKey = javax.swing.KeyStroke.getKeyStroke(
            java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_DOWN_MASK);
        javax.swing.KeyStroke itemsKey = javax.swing.KeyStroke.getKeyStroke(
            java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_DOWN_MASK);
        javax.swing.KeyStroke potionsKey = javax.swing.KeyStroke.getKeyStroke(
            java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK);
        javax.swing.KeyStroke armorKey = javax.swing.KeyStroke.getKeyStroke(
            java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK);
        javax.swing.KeyStroke backKey = javax.swing.KeyStroke.getKeyStroke(
            java.awt.event.KeyEvent.VK_BACK_SPACE, java.awt.event.InputEvent.ALT_DOWN_MASK);
        
        mainPanel.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(homeKey, "goHome");
        mainPanel.getActionMap().put("goHome", new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) { showPanel("HOME"); }
        });
        
        mainPanel.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(itemsKey, "goItems");
        mainPanel.getActionMap().put("goItems", new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) { showPanel("ITEMS"); }
        });
        
        mainPanel.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(potionsKey, "goPotions");
        mainPanel.getActionMap().put("goPotions", new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) { showPanel("POTIONS"); }
        });
        
        mainPanel.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(armorKey, "goArmor");
        mainPanel.getActionMap().put("goArmor", new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) { showPanel("ARMOR"); }
        });
        
        mainPanel.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(backKey, "goBack");
        mainPanel.getActionMap().put("goBack", new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) { goBack(); }
        });
    }

    public void showPanel(String panelName) {
        // Adicionar painel atual ao histórico antes de trocar (se não for o mesmo)
        if (!panelName.equals(currentPanel)) {
            navigationHistory.push(currentPanel);
            currentPanel = panelName;
        }
        cardLayout.show(mainPanel, panelName);
    }

    public void goBack() {
        // Voltar para o painel anterior se existir histórico
        if (!navigationHistory.isEmpty()) {
            String previousPanel = navigationHistory.pop();
            currentPanel = previousPanel;
            cardLayout.show(mainPanel, previousPanel);
        }
    }

    public boolean canGoBack() {
        return !navigationHistory.isEmpty();
    }

    public static MinecraftWiki getWiki() {
        return wiki;
    }

    public static void main(String[] args) {
        // Configurar Look and Feel
        try {
            // Tentar usar Nimbus (moderno e cross-platform)
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            UIManager.put("control", new Color(50, 50, 50));
            UIManager.put("info", new Color(50, 50, 50));
            UIManager.put("nimbusBase", new Color(40, 40, 40));
            UIManager.put("nimbusBlueGrey", new Color(60, 60, 60));
            UIManager.put("nimbusLightBackground", new Color(40, 40, 40));
            UIManager.put("text", Color.WHITE);
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.err.println("Não foi possível configurar o Look and Feel: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            MinecraftWikiGUI gui = new MinecraftWikiGUI();
            gui.setVisible(true);
        });
    }
}

