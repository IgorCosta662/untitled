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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class RedstonePanel extends JPanel {
    private final MinecraftWikiGUI parent;
    private static final Color MINECRAFT_GREEN = new Color(85, 255, 85);
    private static final Color MINECRAFT_RED = new Color(255, 85, 85);
    private static final Color MINECRAFT_GOLD = new Color(255, 170, 0);
    private static final Color DARK_BG = new Color(40, 40, 40);
    private static final Color DARKER_BG = new Color(30, 30, 30);

    public RedstonePanel(MinecraftWikiGUI parent) {
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(DARK_BG);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("⚡ REDSTONE E CIRCUITOS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(MINECRAFT_RED);
        add(titleLabel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.addTab("🔌 Componentes", createComponentsPanel());
        tabbedPane.addTab("🚪 Mecanismos", createMechanismsPanel());
        tabbedPane.addTab("📚 Guia Básico", createGuidePanel());

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

    private JScrollPane createComponentsPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[][] components = {
            {"🔴", "Pó de Redstone", "Conduz sinal por 15 blocos"},
            {"🔦", "Tocha de Redstone", "Fonte de energia permanente"},
            {"⏰", "Repetidor", "Atrasa sinal e reforça alcance"},
            {"🔄", "Comparador", "Compara sinais ou lê containers"},
            {"🔘", "Botão", "Sinal temporário (madeira: 1.5s, pedra: 1s)"},
            {"🎚️", "Alavanca", "Liga/Desliga permanente"},
            {"⚡", "Bloco de Redstone", "Conduz em todas as direções"},
            {"📊", "Placa de Pressão", "Ativa quando pisada"},
            {"🎯", "Alvo", "Emite sinal ao ser acertado"},
            {"🔔", "Observador", "Detecta mudanças de bloco"},
            {"🚂", "Trilho Detector", "Ativa com minecart"},
            {"💡", "Lâmpada de Redstone", "Luz controlável"},
            {"🚪", "Porta de Ferro", "Só abre com redstone"},
            {"📦", "Dispenser", "Dispara itens automaticamente"},
            {"🎰", "Dropper", "Solta itens"},
            {"⏱️", "Funil (Hopper)", "Move itens automaticamente"}
        };

        for (String[] comp : components) {
            panel.add(createComponentCard(comp[0], comp[1], comp[2]));
        }

        return new JScrollPane(panel);
    }

    private JScrollPane createMechanismsPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[][] mechanisms = {
            {"🚪 Porta Automática", "Use placa de pressão ou botão → porta de ferro"},
            {"🌾 Fazenda Automática", "Funis coletam itens → baú"},
            {"🔥 Forno Automático", "Funis alimentam combustível e itens"},
            {"🏹 Torre de Flechas", "Dispensers + redstone clock → defesa"},
            {"🚂 Sistema de Trilhos", "Trilhos powered + botões → transporte"},
            {"💡 Iluminação Automática", "Sensor de luz + lâmpadas → luz noturna"},
            {"🎰 Sorteador Aleatório", "Droppers + comparadores → RNG"},
            {"🚪 Porta Secreta", "Pistões + redstone oculto → entrada escondida"},
            {"⏰ Clock de Redstone", "Repetidores em loop → pulso contínuo"},
            {"🔐 Fechadura de Combinação", "Comparadores + tochas → senha"}
        };

        for (String[] mech : mechanisms) {
            panel.add(createMechanismCard(mech[0], mech[1]));
        }

        return new JScrollPane(panel);
    }

    private JScrollPane createGuidePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        String guideText = """
                📚 GUIA BÁSICO DE REDSTONE
                
                🔴 O QUE É REDSTONE?
                Redstone é o sistema de "eletricidade" do Minecraft, permitindo criar
                circuitos, mecanismos automáticos e máquinas complexas.
                
                ⚡ CONCEITOS FUNDAMENTAIS:
                
                1. ENERGIA (Power Level)
                   • Fontes produzem energia de 0-15
                   • Pó de redstone perde 1 de força por bloco
                   • Use repetidores para reforçar o sinal
                
                2. SINAL FORTE vs SINAL FRACO
                   • Sinal Forte: Atravessa blocos sólidos
                   • Sinal Fraco: Só viaja pelo pó
                
                3. BLOCOS SÓLIDOS vs TRANSPARENTES
                   • Sólidos: Conduzem energia (pedra, madeira)
                   • Transparentes: NÃO conduzem (vidro, lajes)
                
                4. ATRASO (Delay)
                   • Cada componente tem delay (ticks de redstone)
                   • Repetidores: 1-4 ticks ajustáveis
                   • Comparadores: 1 tick fixo
                
                🎯 DICAS PARA INICIANTES:
                
                ✓ Comece Simples
                  Faça uma porta automática antes de clocks complexos
                
                ✓ Use Tochas para Inverter
                  Tocha de redstone inverte o sinal (ON→OFF, OFF→ON)
                
                ✓ Esconda os Fios
                  Use blocos decorativos para camuflar circuitos
                
                ✓ Aprenda Monostable Circuits
                  Pulsos únicos são essenciais para muitos mecanismos
                
                ✓ Teste no Creative
                  Experimente sem gastar recursos
                
                🔧 CIRCUITOS BÁSICOS:
                
                1. NOT Gate (Inversor)
                   Entrada → Tocha de Redstone → Saída invertida
                
                2. AND Gate (E Lógico)
                   Duas entradas devem estar ON para saída ON
                
                3. OR Gate (OU Lógico)
                   Qualquer entrada ON = saída ON
                
                4. Clock (Relógio)
                   Repetidores em loop = pulso contínuo
                
                5. Monostable (Pulso Único)
                   Entrada momentânea → pulso fixo de saída
                
                📖 RECURSOS AVANÇADOS:
                
                • Comparadores podem ler níveis de baús/fornos
                • Observadores detectam mudanças de estado
                • Pistões sticky podem criar elevadores
                • Slime blocks conduzem energia e movem estruturas
                
                💡 COMANDOS ÚTEIS (Creative):
                /gamerule randomTickSpeed 0 - Para o tempo
                /gamerule doDaylightCycle false - Congela hora do dia
                """;

        JTextArea textArea = new JTextArea(guideText);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(DARK_BG);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        panel.add(textArea);

        return new JScrollPane(panel);
    }

    private JPanel createComponentCard(String emoji, String name, String desc) {
        JPanel card = new JPanel(new BorderLayout(10, 5));
        card.setBackground(DARKER_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_RED, 2),
            new EmptyBorder(10, 10, 10, 10)
        ));

        JLabel emojiLabel = new JLabel(emoji);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
        card.add(emojiLabel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(DARKER_BG);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setForeground(MINECRAFT_RED);

        JLabel descLabel = new JLabel(desc);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descLabel.setForeground(Color.LIGHT_GRAY);

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(3));
        infoPanel.add(descLabel);

        card.add(infoPanel, BorderLayout.CENTER);

        return card;
    }

    private JPanel createMechanismCard(String title, String desc) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(DARKER_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_GOLD, 2),
            new EmptyBorder(12, 12, 12, 12)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        titleLabel.setForeground(MINECRAFT_GOLD);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel descLabel = new JLabel(desc);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descLabel.setForeground(Color.WHITE);
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(descLabel);

        return card;
    }
}
