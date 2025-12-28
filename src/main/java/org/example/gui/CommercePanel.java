package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CommercePanel extends JPanel {
    private final MinecraftWikiGUI parent;
    private static final Color MINECRAFT_GREEN = new Color(85, 255, 85);
    private static final Color MINECRAFT_GOLD = new Color(255, 170, 0);
    private static final Color DARK_BG = new Color(40, 40, 40);
    private static final Color DARKER_BG = new Color(30, 30, 30);

    public CommercePanel(MinecraftWikiGUI parent) {
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(DARK_BG);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("💰 COMÉRCIO COM ALDEÕES", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(MINECRAFT_GOLD);
        add(titleLabel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.addTab("👨‍🌾 Fazendeiro", createTradesPanel("Fazendeiro", new String[][]{
            {"Trigo x20", "1 💎", "Pão x6", "1 💎"},
            {"Cenoura x22", "1 💎", "Torta de Abóbora", "1 💎"},
            {"Batata x26", "1 💎", "Maçã x4", "1 💎"}
        }));
        tabbedPane.addTab("📚 Bibliotecário", createTradesPanel("Bibliotecário", new String[][]{
            {"Papel x24", "1 💎", "Estante", "9 💎"},
            {"Livro x4", "1 💎", "Livro Encantado", "5-64 💎"},
            {"Tinta x5", "1 💎", "Bússola", "4 💎"}
        }));
        tabbedPane.addTab("⛏️ Ferreiro", createTradesPanel("Ferreiro de Ferramentas", new String[][]{
            {"Carvão x15", "1 💎", "Picareta de Ferro", "7-22 💎"},
            {"Barra de Ferro x4", "1 💎", "Machado de Diamante", "17-31 💎"},
            {"Diamante x1", "1 💎", "Picareta Encantada", "13-27 💎"}
        }));
        tabbedPane.addTab("🛡️ Armeiro", createTradesPanel("Armeiro", new String[][]{
            {"Carvão x15", "1 💎", "Peitoral de Ferro", "14-33 💎"},
            {"Barra de Ferro x5", "1 💎", "Botas de Diamante", "11-27 💎"},
            {"Diamante x1", "1 💎", "Armadura Encantada", "19-33 💎"}
        }));

        add(tabbedPane, BorderLayout.CENTER);

        JButton backButton = new JButton("🏠 Voltar ao Menu");
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

    private JScrollPane createTradesPanel(String profession, String[][] trades) {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(DARK_BG);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(DARKER_BG);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_GOLD, 2),
            new EmptyBorder(10, 10, 10, 10)
        ));

        JLabel profLabel = new JLabel("👤 Profissão: " + profession);
        profLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        profLabel.setForeground(MINECRAFT_GOLD);

        String infoText = """
                💎 Esmeraldas são a moeda oficial
                📈 Preços variam com oferta/demanda
                ⭐ Troque bastante para desbloquear novos itens
                🎯 Aldeões Master têm as melhores ofertas
                """;

        JTextArea infoArea = new JTextArea(infoText);
        infoArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        infoArea.setForeground(Color.WHITE);
        infoArea.setBackground(DARKER_BG);
        infoArea.setEditable(false);

        infoPanel.add(profLabel);
        infoPanel.add(Box.createVerticalStrut(8));
        infoPanel.add(infoArea);

        mainPanel.add(infoPanel, BorderLayout.NORTH);

        // Trades grid
        JPanel tradesGrid = new JPanel(new GridLayout(0, 1, 10, 10));
        tradesGrid.setBackground(DARK_BG);

        for (String[] trade : trades) {
            tradesGrid.add(createTradeCard(trade[0], trade[1], trade[2], trade[3]));
        }

        mainPanel.add(tradesGrid, BorderLayout.CENTER);

        return new JScrollPane(mainPanel);
    }

    private JPanel createTradeCard(String input1, String cost1, String input2, String cost2) {
        JPanel card = new JPanel(new GridLayout(1, 2, 10, 0));
        card.setBackground(DARKER_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_GREEN, 2),
            new EmptyBorder(12, 12, 12, 12)
        ));

        JPanel trade1 = createSingleTrade(input1, cost1);
        JPanel trade2 = createSingleTrade(input2, cost2);

        card.add(trade1);
        card.add(trade2);

        return card;
    }

    private JPanel createSingleTrade(String input, String cost) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(DARKER_BG);

        JLabel inputLabel = new JLabel(input);
        inputLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        inputLabel.setForeground(Color.WHITE);

        JLabel arrowLabel = new JLabel(" → ");
        arrowLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        arrowLabel.setForeground(MINECRAFT_GOLD);

        JLabel costLabel = new JLabel(cost);
        costLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        costLabel.setForeground(MINECRAFT_GREEN);

        panel.add(inputLabel);
        panel.add(arrowLabel);
        panel.add(costLabel);

        return panel;
    }
}
