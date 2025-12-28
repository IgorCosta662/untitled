package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class AboutPanel extends JPanel {
    private final MinecraftWikiGUI parent;

    public AboutPanel(MinecraftWikiGUI parent) {
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(40, 40, 40));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createTopPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(40, 40, 40));

        JLabel titleLabel = ImageManager.createIconLabel("BOOK", "SOBRE A MINECRAFT WIKI", 28);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);

        panel.add(titleLabel, BorderLayout.CENTER);

        JButton backButton = new JButton("Voltar", ImageManager.getItemIcon("BARRIER", 16));
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(MinecraftWikiGUI.MINECRAFT_GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> parent.showPanel("HOME"));
        panel.add(backButton, BorderLayout.WEST);

        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(40, 40, 40));

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.setBackground(new Color(40, 40, 40));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Logo/Banner
        JPanel bannerPanel = createBannerPanel();
        mainPanel.add(bannerPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Informações principais
        mainPanel.add(createInfoCard(
            "O QUE É ESTA WIKI?",
            "A Minecraft Wiki Completa é uma aplicação desktop desenvolvida em Java " +
            "que oferece um guia completo e interativo sobre Minecraft. " +
            "Contém informações detalhadas sobre itens, receitas de crafting, " +
            "poções, encantamentos e muito mais!",
            MinecraftWikiGUI.MINECRAFT_BLUE
        ));
        mainPanel.add(Box.createVerticalStrut(15));

        mainPanel.add(createInfoCard(
            "FUNCIONALIDADES",
            """
            • Sistema de busca avançado para encontrar rapidamente o que você precisa
            • Receitas de crafting detalhadas com padrões visuais
            • Guias completos de preparação de poções
            • Informações sobre encantamentos e incompatibilidades
            • Simulador de crafting interativo
            • Interface gráfica moderna estilo Minecraft
            • Suporte para Java Edition e Bedrock Edition""",
            MinecraftWikiGUI.MINECRAFT_GREEN
        ));
        mainPanel.add(Box.createVerticalStrut(15));

        mainPanel.add(createInfoCard(
            "TECNOLOGIAS UTILIZADAS",
            """
            • Java 21 - Linguagem de programação principal
            • Maven - Gerenciamento de dependências e build
            • Swing - Framework para interface gráfica
            • FlatLaf - Look and Feel moderno para Swing
            • IntelliJ IDEA - IDE de desenvolvimento""",
            MinecraftWikiGUI.MINECRAFT_BROWN
        ));
        mainPanel.add(Box.createVerticalStrut(15));

        mainPanel.add(createInfoCard(
            "EDIÇÕES SUPORTADAS",
            "JAVA EDITION:\n" +
            "A versão original do Minecraft para PC, com recursos exclusivos " +
            "como mods extensivos e comandos avançados.\n\n" +
            "BEDROCK EDITION:\n" +
            "A versão multiplataforma do Minecraft, disponível para Windows 10/11, " +
            "Xbox, PlayStation, Nintendo Switch, iOS e Android.",
            MinecraftWikiGUI.MINECRAFT_PURPLE
        ));
        mainPanel.add(Box.createVerticalStrut(15));

        mainPanel.add(createInfoCard(
            "COMO USAR",
            "1. Navegue pelo menu principal para escolher uma seção\n" +
            "2. Use a barra de busca para encontrar itens específicos\n" +
            "3. Clique nos cards para ver informações detalhadas\n" +
            "4. Use os botões de guia para aprender passo a passo\n" +
            "5. Experimente o simulador de crafting para testar receitas\n" +
            "6. Consulte as estatísticas para ver todo o conteúdo disponível",
            MinecraftWikiGUI.MINECRAFT_GOLD
        ));
        mainPanel.add(Box.createVerticalStrut(15));

        mainPanel.add(createInfoCard(
            "NOTAS IMPORTANTES",
            "• Esta wiki é baseada nas mecânicas atuais do Minecraft\n" +
            "• Algumas funcionalidades podem variar entre Java e Bedrock\n" +
            "• O conteúdo é atualizado regularmente\n" +
            "• Esta é uma ferramenta educacional e de consulta\n" +
            "• Não é afiliada oficialmente com Mojang ou Microsoft",
            MinecraftWikiGUI.MINECRAFT_RED.darker()
        ));
        mainPanel.add(Box.createVerticalStrut(15));

        // Footer
        mainPanel.add(createFooterPanel());

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(new Color(40, 40, 40));
        container.add(scrollPane, BorderLayout.CENTER);

        return container;
    }

    private JPanel createBannerPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(new EmptyBorder(30, 20, 30, 20));

        JLabel titleLabel = new JLabel("MINECRAFT WIKI");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 42));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("[COMPLETO] Edição Completa e Interativa [COMPLETO]");
        subtitleLabel.setIcon(ImageManager.getItemIcon("DIAMOND", 20));
        subtitleLabel.setIconTextGap(8);
        subtitleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        subtitleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel versionLabel = new JLabel("Versão 2.0 • 2025");
        versionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        versionLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GRAY);
        versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(subtitleLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(versionLabel);

        return panel;
    }

    private JPanel createInfoCard(String title, String content, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(50, 50, 50));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2),
            new EmptyBorder(20, 20, 20, 20)
        ));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(color);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea contentArea = new JTextArea(content);
        contentArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        contentArea.setForeground(Color.WHITE);
        contentArea.setBackground(new Color(50, 50, 50));
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setEditable(false);
        contentArea.setBorder(new EmptyBorder(10, 0, 0, 0));
        contentArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(titleLabel);
        card.add(contentArea);

        return card;
    }

    private JPanel createFooterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(new EmptyBorder(30, 20, 30, 20));

        JLabel devLabel = new JLabel("[DEV] Desenvolvido com Java para a comunidade Minecraft");
        devLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        devLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        devLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel thanksLabel = new JLabel("Obrigado por usar a Minecraft Wiki! Bons crafts e aventuras! ⛏️");
        thanksLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        thanksLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        thanksLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(devLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(thanksLabel);

        return panel;
    }
}

