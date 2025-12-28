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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class StructuresPanel extends JPanel {
    private final MinecraftWikiGUI parent;
    private static final Color MINECRAFT_GREEN = new Color(85, 255, 85);
    private static final Color MINECRAFT_GOLD = new Color(255, 170, 0);
    private static final Color DARK_BG = new Color(40, 40, 40);
    private static final Color DARKER_BG = new Color(30, 30, 30);

    public StructuresPanel(MinecraftWikiGUI parent) {
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(DARK_BG);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("🏛️ ESTRUTURAS GERADAS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(MINECRAFT_GOLD);
        add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        contentPanel.setBackground(DARK_BG);
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[][] structures = {
            {"🏰", "Vila", "Planícies, Deserto, Savana, Taiga", "Aldeões, baús, ferreiro"},
            {"🗼", "Fortaleza (Stronghold)", "Subterrâneo aleatório", "Portal do End, biblioteca"},
            {"[TEMPLE]", "Templo do Deserto", "Deserto", "TNT, 4 baús com tesouros"},
            {"[TEMPLE]", "Templo da Selva", "Selva", "Alavancas, dispensers, baús"},
            {"[MANSION]", "Mansão da Floresta", "Floresta Escura", "Vindicators, Evokers, Totens"},
            {"[MONUMENT]", "Monumento Oceânico", "Oceano Profundo", "Guardiões, esponjas, prismarinho"},
            {"[FORTRESS]", "Fortaleza do Nether", "Nether", "Blazes, Wither Skeletons"},
            {"[BASTION]", "Bastião Remnant", "Nether", "Piglins, ouro, Ancient Debris"},
            {"[CITY]", "Cidade do End", "End", "Shulkers, elytra, End Ships"},
            {"[OUTPOST]", "Posto Avançado", "Próximo a vilas", "Pillagers, Bad Omen"},
            {"[RUINS]", "Ruínas Antigas", "Deep Dark (Y -50)", "Warden, Skulk, loot épico"},
            {"[RUINS]", "Ruínas Oceânicas", "Oceanos", "Baús, tesouros submersos"},
            {"[MINE]", "Mina Abandonada", "Subterrâneo", "Trilhos, minecarts, spawners"},
            {"[DUNGEON]", "Dungeon", "Subterrâneo aleatório", "Spawner de mobs, baús"},
            {"[WELL]", "Poço do Deserto", "Deserto", "Entrada para mina, baús"},
            {"[IGLOO]", "Iglu", "Tundra Gelada", "Cama, fornalha, subsolo secreto"}
        };

        for (String[] struct : structures) {
            JPanel card = createStructureCard(struct[0], struct[1], struct[2], struct[3]);
            contentPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Voltar ao Menu", ImageManager.getItemIcon("BARRIER", 16));
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(MINECRAFT_GREEN);
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> parent.showPanel("HOME"));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(DARK_BG);
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createStructureCard(String emoji, String name, String location, String loot) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(DARKER_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_GOLD, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel emojiLabel = new JLabel(emoji, SwingConstants.CENTER);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        emojiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setForeground(MINECRAFT_GOLD);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel locationLabel = new JLabel(location, SwingConstants.CENTER);
        locationLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        locationLabel.setForeground(Color.LIGHT_GRAY);
        locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lootLabel = new JLabel(loot, SwingConstants.CENTER);
        lootLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lootLabel.setForeground(Color.GRAY);
        lootLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(emojiLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(nameLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(locationLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(lootLabel);

        return card;
    }
}
