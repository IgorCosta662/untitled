package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class EffectsPanel extends JPanel {
    private final MinecraftWikiGUI parent;
    
    private static final Color MINECRAFT_GREEN = new Color(85, 255, 85);
    private static final Color MINECRAFT_RED = new Color(255, 85, 85);
    private static final Color MINECRAFT_GOLD = new Color(255, 170, 0);
    private static final Color DARK_BG = new Color(40, 40, 40);
    private static final Color DARKER_BG = new Color(30, 30, 30);

    public EffectsPanel(MinecraftWikiGUI parent) {
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

        JLabel titleLabel = new JLabel("✨ EFEITOS DE STATUS");
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

        tabbedPane.addTab("✅ Positivos", createPositiveEffectsPanel());
        tabbedPane.addTab("❌ Negativos", createNegativeEffectsPanel());
        tabbedPane.addTab("ℹ️ Info", createInfoPanel());

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private JScrollPane createPositiveEffectsPanel() {
        String[] columns = {"Efeito", "Nível Máximo", "Duração Máx.", "Fontes"};
        Object[][] data = {
            {"⚡ Velocidade", "II", "8:00", "Poção, Farol"},
            {"🛡️ Resistência", "IV", "8:00", "Poção, Maçã Dourada"},
            {"🦘 Salto", "II", "8:00", "Poção"},
            {"💪 Força", "II", "8:00", "Poção, Farol"},
            {"❤️ Regeneração", "II", "2:00", "Poção, Maçã Dourada"},
            {"🔥 Resistência ao Fogo", "I", "8:00", "Poção"},
            {"🌊 Respiração Aquática", "I", "8:00", "Poção, Capacete de Tartaruga"},
            {"👁️ Visão Noturna", "I", "8:00", "Poção"},
            {"👻 Invisibilidade", "I", "8:00", "Poção"},
            {"🦋 Queda Lenta", "I", "4:00", "Poção"},
            {"🍀 Sorte", "I", "5:00", "Poção (só Java)"},
            {"💎 Pressa", "II", "5:00", "Farol, Conduto"},
            {"🌟 Absorção", "IV", "2:00", "Maçã Dourada Encantada"},
            {"💖 Vida Extra", "V", "∞", "Totem da Imortalidade"}
        };

        return createEffectTable(columns, data, MINECRAFT_GREEN);
    }

    private JScrollPane createNegativeEffectsPanel() {
        String[] columns = {"Efeito", "Nível Máximo", "Duração Máx.", "Fontes"};
        Object[][] data = {
            {"🐌 Lentidão", "IV", "4:00", "Poção, Mob"},
            {"⛏️ Fadiga", "III", "∞", "Elder Guardian"},
            {"🤢 Náusea", "I", "0:30", "Peixe-balão"},
            {"🤮 Fome", "I", "0:30", "Poção, Frango Cru"},
            {"🧪 Veneno", "II", "1:30", "Poção, Aranha, Witch"},
            {"☠️ Dano Instantâneo", "II", "Instantâneo", "Poção"},
            {"😵 Fraqueza", "I", "4:00", "Poção, Witch"},
            {"💀 Definhar", "II", "0:40", "Wither, Rosa do Wither"},
            {"🌑 Cegueira", "I", "0:15", "Illager"},
            {"💥 Bad Omen", "V", "1:40", "Matar Pillager Capitão"},
            {"😱 Darkness", "I", "0:12", "Warden, Sensor Sculk"}
        };

        return createEffectTable(columns, data, MINECRAFT_RED);
    }

    private JScrollPane createEffectTable(String[] columns, Object[][] data, Color borderColor) {
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(30);
        table.setBackground(DARKER_BG);
        table.setForeground(Color.WHITE);
        table.setGridColor(borderColor);
        table.setSelectionBackground(borderColor.darker());
        table.setSelectionForeground(Color.WHITE);

        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(borderColor);
        table.getTableHeader().setForeground(Color.BLACK);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        scrollPane.getViewport().setBackground(DARKER_BG);

        return scrollPane;
    }

    private JScrollPane createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        String[] sections = {
            "📌 O QUE SÃO EFEITOS DE STATUS?",
            "Efeitos de status são condições temporárias que afetam o jogador ou mobs, " +
            "alterando suas habilidades, velocidade, visão ou vida. Podem ser benéficos ou prejudiciais.",
            "",
            "🧪 COMO OBTER EFEITOS?",
            """
            • Poções - A forma mais comum de aplicar efeitos
            • Alimentos - Alguns alimentos dão efeitos (ex: maçã dourada)
            • Beacons - Fornecem efeitos contínuos em área
            • Mobs - Ataques de certos mobs aplicam efeitos negativos
            • Encantamentos - Alguns itens encantados dão efeitos
            """,
            "",
            "⏱️ DURAÇÃO E NÍVEIS",
            """
            • A maioria dos efeitos tem níveis (I a IV ou mais)
            • Níveis mais altos = efeito mais forte
            • Duração pode ser estendida com Pó de Redstone
            • Potência pode ser aumentada com Pó Luminoso
            • Beber leite remove TODOS os efeitos
            """,
            "",
            "🎯 EFEITOS ESPECIAIS",
            """
            • Absorption - Dá corações amarelos temporários
            • Bad Omen - Inicia raid ao entrar em vila
            • Hero of the Village - Descontos com aldeões
            • Conduit Power - Efeitos subaquáticos do Conduto
            • Dolphin's Grace - Aumenta velocidade na água
            """,
            "",
            "💡 DICAS",
            """
            ✓ Combine efeitos para vantagens táticas
            ✓ Tenha sempre leite para emergências
            ✓ Use beacon para efeitos permanentes na base
            ✓ Maçãs douradas são úteis em combate
            ✓ Poções podem ser arremessadas em mobs
            """
        };

        for (String section : sections) {
            if (section.isEmpty()) {
                panel.add(Box.createVerticalStrut(15));
                continue;
            }

            if (section.startsWith("📌") || section.startsWith("🧪") || 
                section.startsWith("⏱️") || section.startsWith("🎯") || 
                section.startsWith("💡")) {
                JLabel titleLabel = new JLabel(section);
                titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
                titleLabel.setForeground(MINECRAFT_GOLD);
                panel.add(titleLabel);
                panel.add(Box.createVerticalStrut(8));
            } else {
                JTextArea textArea = new JTextArea(section);
                textArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                textArea.setForeground(Color.WHITE);
                textArea.setBackground(DARK_BG);
                textArea.setEditable(false);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                panel.add(textArea);
                panel.add(Box.createVerticalStrut(10));
            }
        }

        return new JScrollPane(panel);
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
