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

public class SmithingPanel extends JPanel {
    private final MinecraftWikiGUI parent;
    
    private static final Color MINECRAFT_GREEN = new Color(85, 255, 85);
    private static final Color MINECRAFT_GOLD = new Color(255, 170, 0);
    private static final Color MINECRAFT_PURPLE = new Color(170, 85, 255);
    private static final Color DARK_BG = new Color(40, 40, 40);
    private static final Color DARKER_BG = new Color(30, 30, 30);

    public SmithingPanel(MinecraftWikiGUI parent) {
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

        JLabel titleLabel = ImageManager.createIconLabel("SMITHING_TABLE", " MESA DE FERRARIA", 28);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(MINECRAFT_GOLD);
        panel.add(titleLabel);

        return panel;
    }

    private JPanel createContentPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(DARK_BG);

        // Info principal
        JPanel infoPanel = createMainInfoPanel();
        mainPanel.add(infoPanel, BorderLayout.NORTH);

        // Conteúdo principal
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        centerPanel.setBackground(DARK_BG);

        centerPanel.add(createNetheritePanel());
        centerPanel.add(createTrimsPanel());

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createMainInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARKER_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_GOLD, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel title = new JLabel("⚙️ COMO USAR A MESA DE FERRARIA");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(MINECRAFT_GOLD);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        String infoText = """
                📌 Receita: 2 Barras de Ferro + 4 Tábuas de Madeira
                
                🔧 A mesa de ferraria tem 3 slots:
                   1️⃣ Item base (armadura/ferramenta de diamante)
                   2️⃣ Material de upgrade (Barra de Netherite ou Molde de Armadura)
                   3️⃣ Modelo de upgrade (para armaduras decoradas)
                
                💡 Diferente da Bigorna, a mesa de ferraria NÃO consome XP!
                """;

        JTextArea infoArea = new JTextArea(infoText);
        infoArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        infoArea.setForeground(Color.WHITE);
        infoArea.setBackground(DARKER_BG);
        infoArea.setEditable(false);

        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(infoArea);

        return panel;
    }

    private JPanel createNetheritePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARKER_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_PURPLE, 3),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel title = ImageManager.createIconLabel("NETHERITE", " UPGRADE PARA NETHERITE", 24);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(MINECRAFT_PURPLE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        String upgradeInfo = """
                
                🔹 COMO FAZER:
                • Encontre Ancient Debris no Nether (Y 8-22)
                • Fundir Ancient Debris → Sucata de Netherite
                • 4 Sucatas + 4 Barras de Ouro = Barra de Netherite
                • Use mesa de ferraria: Item Diamante + Barra Netherite
                
                ⭐ ITENS QUE PODEM SER UPGRADEADOS:
                ✓ Espada, Picareta, Machado, Pá, Enxada
                ✓ Capacete, Peitoral, Calças, Botas
                
                💪 VANTAGENS DO NETHERITE:
                • +1 de dano/proteção comparado ao diamante
                • +1 de resistência ao recuo (knockback)
                • Não queima em lava (flutua!)
                • Mantém todos os encantamentos
                • Repara durabilidade ao fazer upgrade
                
                ⚠️ IMPORTANTE:
                Ancient Debris é MUITO raro! Apenas 1-3 por chunk.
                Use TNT ou beds para minerar mais rápido.
                """;

        JTextArea infoArea = new JTextArea(upgradeInfo);
        infoArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        infoArea.setForeground(Color.WHITE);
        infoArea.setBackground(DARKER_BG);
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);

        panel.add(title);
        panel.add(infoArea);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(DARK_BG);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(DARK_BG);
        wrapper.add(scrollPane, BorderLayout.CENTER);

        return wrapper;
    }

    private JPanel createTrimsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARKER_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_GOLD, 3),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel title = new JLabel("✨ DECORAÇÃO DE ARMADURAS");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(MINECRAFT_GOLD);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        String trimsInfo = """
                
                🎨 ARMOR TRIMS (desde 1.20):
                Personalize sua armadura com padrões decorativos!
                
                📋 COMO APLICAR:
                1. Encontre Smithing Template em estruturas
                2. Mesa de ferraria: Armadura + Template + Material
                3. O material define a COR do padrão
                
                🎯 TEMPLATES DISPONÍVEIS (16 tipos):
                • Ward - Fortaleza Antiga (Ancient City)
                • Vex - Mansão da Floresta
                • Tide - Monumento Oceânico
                • Snout - Bastião Remnant
                • Rib - Fortaleza do Nether
                • Eye - Fortaleza do End (Stronghold)
                • Dune - Templo do Deserto
                • Coast - Naufrágio (Shipwreck)
                • Sentry - Posto Avançado (Pillager Outpost)
                • Shaper - Trail Ruins
                • Host - Trail Ruins
                • Raiser - Trail Ruins
                • Wayfinder - Trail Ruins
                • Wild - Selva (Jungle Temple)
                • Spire - End City
                • Silence - Ancient City
                
                🌈 MATERIAIS PARA COR:
                • Diamante = Ciano brilhante
                • Esmeralda = Verde brilhante
                • Lápis-lazúli = Azul forte
                • Ametista = Roxo
                • Quartzo = Branco
                • Netherite = Cinza escuro
                • Redstone = Vermelho
                • Cobre = Laranja/Bronze
                • Ouro = Amarelo dourado
                • Ferro = Cinza claro
                
                💡 DICA: Templates podem ser DUPLICADOS!
                Use: 7 Diamantes + 1 Template + 1 Material do bioma
                """;

        JTextArea infoArea = new JTextArea(trimsInfo);
        infoArea.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        infoArea.setForeground(Color.WHITE);
        infoArea.setBackground(DARKER_BG);
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);

        panel.add(title);
        panel.add(infoArea);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(DARK_BG);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(DARK_BG);
        wrapper.add(scrollPane, BorderLayout.CENTER);

        return wrapper;
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
