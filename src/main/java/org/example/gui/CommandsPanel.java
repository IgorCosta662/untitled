package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CommandsPanel extends JPanel {
    private final MinecraftWikiGUI parent;
    private static final Color MINECRAFT_GREEN = new Color(85, 255, 85);
    private static final Color MINECRAFT_BLUE = new Color(85, 170, 255);
    private static final Color MINECRAFT_GOLD = new Color(255, 170, 0);
    private static final Color DARK_BG = new Color(40, 40, 40);
    private static final Color DARKER_BG = new Color(30, 30, 30);

    public CommandsPanel(MinecraftWikiGUI parent) {
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(DARK_BG);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("⌨️ COMANDOS DO MINECRAFT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(MINECRAFT_GOLD);
        add(titleLabel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.addTab("🎮 Jogabilidade", createGameplayCommandsPanel());
        tabbedPane.addTab("🌍 Mundo", createWorldCommandsPanel());
        tabbedPane.addTab("👤 Jogador", createPlayerCommandsPanel());

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

    private JScrollPane createGameplayCommandsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[][] commands = {
            {"/gamemode <modo>", "Muda modo de jogo", "survival, creative, adventure, spectator"},
            {"/difficulty <nível>", "Define dificuldade", "peaceful, easy, normal, hard"},
            {"/gamerule <regra> <valor>", "Altera regras do jogo", "keepInventory true, mobGriefing false"},
            {"/time set <valor>", "Define hora do dia", "day, night, 0-24000"},
            {"/weather <tipo>", "Altera clima", "clear, rain, thunder"},
            {"/seed", "Mostra seed do mundo", "Copie para recriar o mundo"},
            {"/locate <estrutura>", "Localiza estrutura mais próxima", "village, mansion, stronghold"},
            {"/tp <x> <y> <z>", "Teleporta para coordenadas", "Ex: /tp 100 64 -200"},
            {"/kill [@s]", "Mata entidade", "@s = você mesmo"},
            {"/clear [jogador]", "Limpa inventário", "Remove todos os itens"}
        };

        for (String[] cmd : commands) {
            panel.add(createCommandCard(cmd[0], cmd[1], cmd[2]));
            panel.add(Box.createVerticalStrut(8));
        }

        return new JScrollPane(panel);
    }

    private JScrollPane createWorldCommandsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[][] commands = {
            {"/fill <x1> <y1> <z1> <x2> <y2> <z2> <bloco>", "Preenche área com blocos", "Ex: /fill ~ ~ ~ ~10 ~5 ~10 stone"},
            {"/setblock <x> <y> <z> <bloco>", "Coloca bloco específico", "Ex: /setblock ~ ~-1 ~ diamond_block"},
            {"/clone <from> <to>", "Copia área de blocos", "Duplica construções"},
            {"/setworldspawn [x] [y] [z]", "Define spawn do mundo", "Ponto de renascimento padrão"},
            {"/spawnpoint [jogador] [x] [y] [z]", "Define spawn individual", "Onde jogador renasce"},
            {"/worldborder <comando>", "Controla borda do mundo", "set, add, center, get"},
            {"/summon <entidade> [x] [y] [z]", "Invoca mob ou entidade", "Ex: /summon zombie ~ ~ ~"},
            {"/particle <tipo> <x> <y> <z>", "Cria efeito de partícula", "flame, heart, explosion"},
            {"/spreadplayers <x> <z> <distância>", "Espalha jogadores", "Para minigames"},
            {"/forceload <add|remove> <x> <z>", "Mantém chunk carregado", "Útil para farms"}
        };

        for (String[] cmd : commands) {
            panel.add(createCommandCard(cmd[0], cmd[1], cmd[2]));
            panel.add(Box.createVerticalStrut(8));
        }

        return new JScrollPane(panel);
    }

    private JScrollPane createPlayerCommandsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[][] commands = {
            {"/give <jogador> <item> [quantidade]", "Dá itens ao jogador", "Ex: /give @s diamond 64"},
            {"/xp <quantidade> [jogador]", "Dá experiência", "Ex: /xp 1000 @s ou /xp 30L @s (níveis)"},
            {"/effect <jogador> <efeito> [duração] [nível]", "Aplica efeito de status", "Ex: /effect @s speed 60 2"},
            {"/effect clear [jogador] [efeito]", "Remove efeitos", "Sem args = remove todos"},
            {"/enchant <jogador> <encantamento> [nível]", "Encanta item na mão", "Ex: /enchant @s sharpness 5"},
            {"/attribute <jogador> <atributo> <valor>", "Modifica atributos", "health, speed, attack_damage"},
            {"/tag <jogador> <add|remove> <tag>", "Adiciona tag ao jogador", "Para seletores personalizados"},
            {"/title <jogador> <tipo> <texto>", "Exibe título na tela", "title, subtitle, actionbar"},
            {"/playsound <som> <jogador>", "Toca som para jogador", "Ex: /playsound entity.ender_dragon.roar @a"},
            {"/spectate [alvo] [jogador]", "Especta entidade", "Modo espectador avançado"}
        };

        for (String[] cmd : commands) {
            panel.add(createCommandCard(cmd[0], cmd[1], cmd[2]));
            panel.add(Box.createVerticalStrut(8));
        }

        JPanel notePanel = new JPanel();
        notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.Y_AXIS));
        notePanel.setBackground(DARKER_BG);
        notePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_BLUE, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel noteTitle = new JLabel("💡 SELETORES IMPORTANTES");
        noteTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        noteTitle.setForeground(MINECRAFT_GOLD);

        String noteText = """
                
                @s = você mesmo (self)
                @p = jogador mais próximo
                @a = todos os jogadores (all)
                @r = jogador aleatório (random)
                @e = todas as entidades
                
                Coordenadas Relativas:
                ~ = posição atual
                ~5 = 5 blocos da posição atual
                ^ = direção que você está olhando
                """;

        JTextArea noteArea = new JTextArea(noteText);
        noteArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        noteArea.setForeground(Color.WHITE);
        noteArea.setBackground(DARKER_BG);
        noteArea.setEditable(false);

        notePanel.add(noteTitle);
        notePanel.add(noteArea);

        panel.add(Box.createVerticalStrut(15));
        panel.add(notePanel);

        return new JScrollPane(panel);
    }

    private JPanel createCommandCard(String command, String description, String example) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(DARKER_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MINECRAFT_BLUE, 2),
            new EmptyBorder(10, 10, 10, 10)
        ));

        JLabel cmdLabel = new JLabel(command);
        cmdLabel.setFont(new Font("Consolas", Font.BOLD, 13));
        cmdLabel.setForeground(MINECRAFT_GREEN);
        cmdLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descLabel.setForeground(Color.WHITE);
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel exLabel = new JLabel("💡 " + example);
        exLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        exLabel.setForeground(Color.GRAY);
        exLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(cmdLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(descLabel);
        card.add(Box.createVerticalStrut(3));
        card.add(exLabel);

        return card;
    }
}
