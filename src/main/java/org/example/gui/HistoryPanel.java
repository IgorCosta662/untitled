package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class HistoryPanel extends JPanel {
    private final MinecraftWikiGUI parent;
    private static final Color MINECRAFT_GREEN = new Color(85, 255, 85);
    private static final Color MINECRAFT_GOLD = new Color(255, 170, 0);
    private static final Color DARK_BG = new Color(40, 40, 40);

    public HistoryPanel(MinecraftWikiGUI parent) {
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(DARK_BG);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("📜 HISTÓRIA DO MINECRAFT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(MINECRAFT_GOLD);
        add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(DARK_BG);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        String historyText = """
                🎮 A ORIGEM (2009-2011)
                
                • Maio 2009: Markus "Notch" Persson começa desenvolvimento
                • Maio 2009: Versão "Cave Game" - primeiro protótipo
                • 17 Maio 2009: Minecraft Classic 0.0.11a lançado publicamente
                • Novembro 2010: Beta lançado oficialmente
                • 18 Novembro 2011: Minecraft 1.0 - Lançamento Oficial
                
                🏢 MOJANG E MICROSOFT (2010-2014)
                
                • 2010: Mojang Studios fundada por Notch
                • 2011: Jens "Jeb" Bergensten assume como lead developer
                • 2012: Pocket Edition lançada para mobile
                • 2014: Microsoft compra Mojang por $2.5 bilhões
                • Notch deixa a equipe após aquisição
                
                📱 EXPANSÃO (2013-2017)
                
                • 2013: Console Edition para Xbox 360, PS3
                • 2016: Bedrock Edition unifica todas as plataformas
                • 2017: Better Together Update une jogadores cross-platform
                • 2017: Marketplace permite criadores venderem conteúdo
                
                🎓 MINECRAFT EDUCATION (2016+)
                
                • 2016: Minecraft: Education Edition lançada
                • Usado em 115+ países para ensinar programação,
                  matemática, história e ciências
                • Chemistry Update adiciona elementos da tabela periódica
                
                🌟 MARCOS IMPORTANTES
                
                2011 - The End e Ender Dragon adicionados
                2012 - Nether atualizado, Wither Boss
                2013 - Cavalos domesticáveis
                2016 - Shulkers e Elytras (cidades do End)
                2017 - Sistema de avanços (achievements renovado)
                2018 - Update Aquatic (oceanos, golfinhos, naufrágios)
                2019 - Village & Pillage (vilas renovadas, raids)
                2019 - Abelhas e mel adicionados
                2020 - Nether Update (biomas, netherite)
                2021 - Caves & Cliffs (montanhas, cavernas profundas)
                2022 - The Wild Update (Deep Dark, Warden, mangue)
                2023 - Trails & Tales (arqueologia, armor trims)
                2024 - Tricky Trials (estruturas de teste, novos itens)
                
                📊 ESTATÍSTICAS IMPRESSIONANTES
                
                • 300+ MILHÕES de cópias vendidas
                • Jogo mais vendido da história
                • 140+ MILHÕES de jogadores ativos mensais
                • Disponível em 20+ plataformas
                • Traduzido para 100+ idiomas
                • 1+ TRILHÃO de visualizações no YouTube
                
                🎬 MÍDIA E CULTURA POP
                
                • 2015: Minecraft: Story Mode (Telltale Games)
                • 2019: Livro "Minecraft: The Island" best-seller
                • 2022: Minecraft Legends anunciado (jogo de estratégia)
                • 2025: Filme live-action em produção (Warner Bros)
                • LEGO Minecraft - linha de sets desde 2012
                • Merchandising: roupas, brinquedos, livros
                
                🏆 PRÊMIOS E RECONHECIMENTO
                
                • Game of the Year (múltiplas vezes)
                • Kids' Choice Awards - Jogo Favorito
                • Guinness World Records:
                  - Jogo mais vendido de todos os tempos
                  - Jogo indie de maior sucesso
                  - Vídeo de jogo mais assistido no YouTube
                
                💡 CURIOSIDADES
                
                • Nome original: "Cave Game"
                • Creeper foi um bug de porco (código errado)
                • Som do Ghast = gato do compositor C418
                • "Minecraft" = Mine (minerar) + Craft (criar)
                • Notch programou a primeira versão em 6 dias
                • Trilha sonora de C418 vendeu milhões
                
                🌍 IMPACTO CULTURAL
                
                • Usado em educação formal por milhões de estudantes
                • Ferramenta de terapia para autismo
                • Meio de expressão artística (pixel art, construções)
                • Comunidade global de modders e criadores
                • Inspirou geração inteira de game developers
                
                🎯 VERSÕES NOTÁVEIS
                
                1.0 - The End (2011)
                1.7 - The Update that Changed the World (2013)
                1.9 - Combat Update (2016)
                1.13 - Update Aquatic (2018)
                1.14 - Village & Pillage (2019)
                1.16 - Nether Update (2020)
                1.17-1.18 - Caves & Cliffs I & II (2021)
                1.19 - The Wild Update (2022)
                1.20 - Trails & Tales (2023)
                1.21 - Tricky Trials (2024)
                """;

        JTextArea textArea = new JTextArea(historyText);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(DARK_BG);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        contentPanel.add(textArea);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

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
}
