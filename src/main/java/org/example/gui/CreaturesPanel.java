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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class CreaturesPanel extends JPanel {
    private final MinecraftWikiGUI parent;
    
    private static final Color MINECRAFT_GREEN = new Color(85, 255, 85);
    private static final Color MINECRAFT_RED = new Color(255, 85, 85);
    private static final Color MINECRAFT_GOLD = new Color(255, 170, 0);
    private static final Color DARK_BG = new Color(40, 40, 40);
    private static final Color DARKER_BG = new Color(30, 30, 30);

    public CreaturesPanel(MinecraftWikiGUI parent) {
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

        JLabel titleLabel = new JLabel("🐾 CRIATURAS DO MINECRAFT");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(MINECRAFT_GOLD);
        panel.add(titleLabel);

        return panel;
    }

    private JPanel createContentPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(DARK_BG);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setBackground(DARKER_BG);
        tabbedPane.setForeground(Color.WHITE);

        tabbedPane.addTab("😊 Passivos", createPassiveMobsPanel());
        tabbedPane.addTab("😐 Neutros", createNeutralMobsPanel());
        tabbedPane.addTab("👹 Hostis", createHostileMobsPanel());
        tabbedPane.addTab("🐉 Chefes", createBossesPanel());

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private JScrollPane createPassiveMobsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[][] passiveMobs = {
            {"🐄", "Vaca", "10 ❤️", "Couro, Carne Crua"},
            {"🐷", "Porco", "10 ❤️", "Carne de Porco"},
            {"🐔", "Galinha", "4 ❤️", "Penas, Carne de Frango, Ovos"},
            {"🐑", "Ovelha", "8 ❤️", "Lã, Carne de Carneiro"},
            {"🐴", "Cavalo", "15-30 ❤️", "Couro (montável)"},
            {"🐱", "Gato", "10 ❤️", "Espanta Creepers"},
            {"🐺", "Lobo", "8 ❤️", "Domesticável (osso)"},
            {"🦙", "Lhama", "15-30 ❤️", "Couro, transporte"}
        };

        for (String[] mob : passiveMobs) {
            panel.add(createMobCard(mob[0], mob[1], mob[2], mob[3], MINECRAFT_GREEN));
        }

        return new JScrollPane(panel);
    }

    private JScrollPane createNeutralMobsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[][] neutralMobs = {
            {"🐝", "Abelha", "10 ❤️", "Ataca se provocada"},
            {"🕷️", "Aranha", "16 ❤️", "Hostil à noite"},
            {"🐻", "Urso Polar", "30 ❤️", "Protege filhotes"},
            {"🐼", "Panda", "20 ❤️", "Agressivo se atacado"},
            {"🐺", "Lobo (Selvagem)", "8 ❤️", "Ataca ovelhas"},
            {"🦊", "Raposa", "10 ❤️", "Foge de jogadores"},
            {"🐢", "Tartaruga", "30 ❤️", "Defensiva"},
            {"🐬", "Golfinho", "10 ❤️", "Ajuda na água"}
        };

        for (String[] mob : neutralMobs) {
            panel.add(createMobCard(mob[0], mob[1], mob[2], mob[3], MINECRAFT_GOLD));
        }

        return new JScrollPane(panel);
    }

    private JScrollPane createHostileMobsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[][] hostileMobs = {
            {"🧟", "Zumbi", "20 ❤️", "Carne Podre, Ferro"},
            {"💀", "Esqueleto", "20 ❤️", "Ossos, Flechas"},
            {"🧨", "Creeper", "20 ❤️", "Pólvora, Discos"},
            {"🕸️", "Aranha da Caverna", "12 ❤️", "Linha, Olhos"},
            {"👻", "Ghast", "10 ❤️", "Lágrima de Ghast"},
            {"🔥", "Blaze", "20 ❤️", "Vara de Blaze"},
            {"🗡️", "Enderman", "40 ❤️", "Pérola do End"},
            {"⚡", "Witch", "26 ❤️", "Poções, Pó Luminoso"}
        };

        for (String[] mob : hostileMobs) {
            panel.add(createMobCard(mob[0], mob[1], mob[2], mob[3], MINECRAFT_RED));
        }

        return new JScrollPane(panel);
    }

    private JScrollPane createBossesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[][] bosses = {
            {"🐉", "Ender Dragon", "200 ❤️", "Ovo de Dragão, 12000 XP - Chefe final do End"},
            {"💀", "Wither", "300 ❤️", "Estrela do Nether, 50 XP - Invocado com 4 Soul Sand + 3 Crânios de Wither"},
            {"🛡️", "Elder Guardian", "80 ❤️", "Esponja, Prismarinho - Guardião dos Monumentos Oceânicos"}
        };

        for (String[] boss : bosses) {
            panel.add(createBossCard(boss[0], boss[1], boss[2], boss[3]));
        }

        return new JScrollPane(panel);
    }

    private JPanel createMobCard(String emoji, String name, String health, String drops, Color borderColor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(DARKER_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 2),
            new EmptyBorder(10, 10, 10, 10)
        ));

        JLabel emojiLabel = new JLabel(emoji);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 35));
        emojiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        nameLabel.setForeground(borderColor);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel healthLabel = new JLabel("Vida: " + health);
        healthLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        healthLabel.setForeground(Color.LIGHT_GRAY);
        healthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel dropsLabel = new JLabel("Drop: " + drops);
        dropsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        dropsLabel.setForeground(Color.GRAY);
        dropsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(emojiLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(nameLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(healthLabel);
        card.add(Box.createVerticalStrut(3));
        card.add(dropsLabel);

        return card;
    }

    private JPanel createBossCard(String emoji, String name, String health, String info) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(DARKER_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_RED, 3),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel emojiLabel = new JLabel(emoji);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 50));
        card.add(emojiLabel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(DARKER_BG);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setForeground(MINECRAFT_RED);

        JLabel healthLabel = new JLabel("❤️ Vida: " + health);
        healthLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        healthLabel.setForeground(Color.WHITE);

        JTextArea infoArea = new JTextArea(info);
        infoArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        infoArea.setForeground(Color.LIGHT_GRAY);
        infoArea.setBackground(DARKER_BG);
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(healthLabel);
        infoPanel.add(Box.createVerticalStrut(8));
        infoPanel.add(infoArea);

        card.add(infoPanel, BorderLayout.CENTER);

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
