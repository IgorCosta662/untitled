package org.example.gui;

import java.awt.CardLayout;
import java.awt.Color;

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
        mainPanel.add(new CraftingSimulatorPanel(), "CRAFTING");
        mainPanel.add(new StatisticsPanel(wiki), "STATISTICS");
        mainPanel.add(new AboutPanel(this), "ABOUT");
        
        // Novos painéis de categorias
        mainPanel.add(new BlocksPanel(this), "BLOCKS");
        mainPanel.add(new CreaturesPanel(this), "CREATURES");
        mainPanel.add(new BiomesPanel(this), "BIOMES");
        mainPanel.add(new EffectsPanel(this), "EFFECTS");
        mainPanel.add(new SmeltingPanel(this), "SMELTING");
        mainPanel.add(new SmithingPanel(this), "SMITHING");
        mainPanel.add(new StructuresPanel(this), "STRUCTURES");
        mainPanel.add(new RedstonePanel(this), "REDSTONE");
        mainPanel.add(new CommandsPanel(this), "COMMANDS");
        mainPanel.add(new CommercePanel(this), "COMMERCE");
        mainPanel.add(new HistoryPanel(this), "HISTORY");
        mainPanel.add(new TutorialsPanel(this), "TUTORIALS");

        add(mainPanel);

        // Mostrar tela inicial
        showPanel("HOME");
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
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

