package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class BiomesPanel extends JPanel {
    private final MinecraftWikiGUI parent;
    
    private static final Color MINECRAFT_GREEN = new Color(85, 255, 85);
    private static final Color MINECRAFT_BLUE = new Color(85, 170, 255);
    private static final Color MINECRAFT_GOLD = new Color(255, 170, 0);
    private static final Color DARK_BG = new Color(40, 40, 40);
    private static final Color DARKER_BG = new Color(30, 30, 30);

    public BiomesPanel(MinecraftWikiGUI parent) {
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(DARK_BG);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createTitlePanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private JPanel createTitlePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(DARK_BG);

        JLabel titleLabel = new JLabel("🌍 BIOMAS DO MINECRAFT");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(MINECRAFT_GOLD);
        panel.add(titleLabel);

        return panel;
    }

    private JPanel createContentPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        mainPanel.setBackground(DARK_BG);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[][] biomes = {
            {"🌲", "Floresta", "Temperatura: Normal", "Árvores, cogumelos, flores\nAnimais: lobos, coelhos"},
            {"🏔️", "Montanhas", "Temperatura: Fria", "Picos altos, neve, esmeraldas\nCabras, lhamas"},
            {"🏜️", "Deserto", "Temperatura: Quente", "Areia, cactos, templos\nCoelhos, múmias"},
            {"❄️", "Tundra de Gelo", "Temperatura: Congelante", "Neve, gelo, iglus\nUrsos polares, raposas"},
            {"🌴", "Selva", "Temperatura: Tropical", "Árvores gigantes, templos\nPapagaios, pandas, ocelots"},
            {"🍄", "Campos de Cogumelos", "Temperatura: Normal", "Micélio, cogumelos gigantes\nMooshrooms (sem mobs hostis)"},
            {"🌊", "Oceano", "Temperatura: Variável", "Água profunda, corais\nGolfinhos, tartarugas, guardians"},
            {"🌾", "Planícies", "Temperatura: Normal", "Grama, flores, vilas\nCavalos, vacas, ovelhas"},
            {"🌵", "Mesa (Badlands)", "Temperatura: Quente", "Terracota, ouro, minas\nTerracota colorida"},
            {"🌲", "Taiga", "Temperatura: Fria", "Spruce, ferns, vilas\nLobos, raposas, coelhos"},
            {"🌑", "The Nether", "Dimensão: Inferno", "Netherrack, lava, fortalezas\nPiglins, blazes, ghasts"},
            {"🌌", "The End", "Dimensão: Vazio", "End stone, cidades\nEnderman, Ender Dragon"}
        };

        for (String[] biome : biomes) {
            mainPanel.add(createBiomeCard(biome[0], biome[1], biome[2], biome[3]));
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(DARK_BG);
        wrapperPanel.add(scrollPane, BorderLayout.CENTER);

        return wrapperPanel;
    }

    private JPanel createBiomeCard(String emoji, String name, String temperature, String features) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(DARKER_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_BLUE, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel emojiLabel = new JLabel(emoji);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        emojiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setForeground(MINECRAFT_GOLD);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tempLabel = new JLabel(temperature);
        tempLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        tempLabel.setForeground(MINECRAFT_BLUE);
        tempLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea featuresArea = new JTextArea(features);
        featuresArea.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        featuresArea.setForeground(Color.LIGHT_GRAY);
        featuresArea.setBackground(DARKER_BG);
        featuresArea.setEditable(false);
        featuresArea.setLineWrap(true);
        featuresArea.setWrapStyleWord(true);
        featuresArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(emojiLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(nameLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(tempLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(featuresArea);

        return card;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(DARK_BG);

        JButton backButton = new JButton("🏠 Voltar ao Menu");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(MINECRAFT_GREEN);
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> parent.showPanel("HOME"));

        panel.add(backButton);

        return panel;
    }
}
