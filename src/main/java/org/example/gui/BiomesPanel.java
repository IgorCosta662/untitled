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

        JLabel titleLabel = new JLabel("[BIOMES] BIOMAS DO MINECRAFT");
        titleLabel.setIcon(ImageManager.getItemIcon("GRASS_BLOCK", 24));
        titleLabel.setIconTextGap(10);
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
            // Biomas Temperados
            {"🌲", "Floresta", "Temperatura: Normal", "Árvores de carvalho e bétula\nCogumelos, flores variadas\nLobos, coelhos"},
            {"🌳", "Floresta de Bétulas", "Temperatura: Normal", "Árvores de bétula densas\nGrama alta, flores\nCoelhos comuns"},
            {"🌲", "Floresta Escura", "Temperatura: Normal", "Árvores escuras gigantes\nCogumelos grandes, mansão\nPerigosa (mobs spawnam de dia)"},
            {"🌾", "Planícies", "Temperatura: Normal", "Campos abertos de grama\nVilas, cavalos selvagens\nFlores, abelhas"},
            {"🌻", "Planícies de Girassóis", "Temperatura: Normal", "Campo de girassóis\nPerfeito para navegação\nCavalos, abelhas"},
            {"🌸", "Floresta de Flores", "Temperatura: Normal", "Todas as flores do jogo\nÁrvores esparsas\nCoelhos, abelhas"},
            {"🍂", "Pântano", "Temperatura: Normal", "Água rasa, lianas\nCabanas de bruxa, slimes\nSapos (1.19+)"},
            {"🌿", "Pântano de Manguezais", "Temperatura: Quente", "Árvores de mangue, lama\nSapos, rãs\nPropágulos de mangue"},
            
            // Biomas Frios
            {"[MOUNTAIN]", "Montanhas", "Temperatura: Fria", "Picos extremos de pedra\nMinério de esmeralda\nCabras"},
            {"[MOUNTAIN]", "Montanhas Nevadas", "Temperatura: Congelante", "Picos cobertos de neve\nGelo, neve em camadas\nCabras, coelhos brancos"},
            {"[MOUNTAIN]", "Picos Congelados", "Temperatura: Congelante", "Picos pontiagudos de gelo\nGelo compactado\nCabras gritadoras"},
            {"[ICE]", "Tundra de Gelo", "Temperatura: Congelante", "Planície congelada\nIglus, gelo azul\nUrsos polares, raposas brancas"},
            {"[ICE]", "Espigões de Gelo", "Temperatura: Congelante", "Espigões de gelo pontiagudos\nExtremamente perigoso\nUrsos polares"},
            {"[TREE]", "Taiga", "Temperatura: Fria", "Floresta de spruce\nSamambaias, vilas\nLobos, raposas, coelhos"},
            {"[TREE]", "Taiga Nevada", "Temperatura: Congelante", "Taiga com neve\nSpruce cobertos de neve\nLobos, raposas brancas"},
            {"[MOUNTAIN]", "Taiga de Montanha", "Temperatura: Fria", "Taiga em terreno montanhoso\nSpruce gigantes\nLobos, coelhos"},
            {"[TREE]", "Taiga Antiga", "Temperatura: Fria", "Árvores de spruce gigantes\nPodzol, samambaias\nLobos, cogumelos"},
            
            // Biomas Quentes
            {"[DESERT]", "Deserto", "Temperatura: Quente/Seco", "Areia infinita, cactos\nTemplos, vilas, poços\nCoelhos, múmias"},
            {"[DESERT]", "Mesa (Badlands)", "Temperatura: Quente", "Terracota colorida natural\nMinérios de ouro expostos\nMinas abandonadas (superfície)"},
            {"[DESERT]", "Mesa Erodida", "Temperatura: Quente", "Formações de terracota únicas\nPilares e arcos naturais\nOuro abundante"},
            {"[TEMPLE]", "Mesa Arborizada", "Temperatura: Quente", "Mesa com árvores de carvalho\nGrama no topo dos platôs\nRaro e único"},
            {"[SAVANA]", "Savana", "Temperatura: Quente/Seco", "Grama amarelada, acacias\nVilas, cavalos\nÁrvores esparsas"},
            {"[SAVANA]", "Savana Fragmentada", "Temperatura: Quente", "Savana com montanhas\nTerreno acidentado\nAcacias em penhascos"},
            
            // Biomas Tropicais
            {"[JUNGLE]", "Selva", "Temperatura: Tropical/Úmido", "Árvores gigantes da selva\nTemplos, melancias, cacau\nPapagaios, ocelots, pandas"},
            {"[JUNGLE]", "Selva de Bambu", "Temperatura: Tropical", "Bambu denso, pandas\nÁrvores da selva menores\nPandas comuns"},
            {"[JUNGLE]", "Borda da Selva", "Temperatura: Tropical", "Transição floresta-selva\nÁrvores menores\nMais navegável"},
            {"[JUNGLE]", "Selva Rala", "Temperatura: Tropical", "Selva com árvores esparsas\nMais espaço aberto\nPapagaios"},
            
            // Biomas Aquáticos
            {"[OCEAN]", "Oceano", "Temperatura: Normal", "Água profunda, naufrágios\nRecifes de corais\nGolfinhos, lulas"},
            {"[OCEAN]", "Oceano Quente", "Temperatura: Quente", "Corais coloridos abundantes\nPeixes tropicais\nBarcos afundados"},
            {"[ICE]", "Oceano Congelado", "Temperatura: Congelante", "Superfície congelada\nIcebergs, ursos polares\nSalmão"},
            {"[OCEAN]", "Oceano Profundo", "Temperatura: Fria", "Extremamente profundo\nMonumentos oceânicos\nGuardiões"},
            {"[RIVER]", "Rio", "Temperatura: Normal", "Água corrente rasa\nSalmão, argila\nConecta biomas"},
            {"[ICE]", "Rio Congelado", "Temperatura: Congelante", "Rio com superfície de gelo\nSalmão sob o gelo\nConecta biomas frios"},
            {"[BEACH]", "Praia", "Temperatura: Variável", "Areia, transição terra-mar\nTartarugas, cana-de-açúcar\nNaufrágios próximos"},
            {"[BEACH]", "Praia de Pedra", "Temperatura: Normal", "Costa rochosa íngreme\nSem areia, apenas pedra\nDifícil para desembarque"},
            
            // Biomas Raros e Especiais
            {"[MUSHROOM]", "Campos de Cogumelos", "Temperatura: Normal", "Micélio, cogumelos gigantes\nMooshrooms exclusivas\nSEM MOBS HOSTIS"},
            {"[ISLAND]", "Ilha de Cogumelos", "Temperatura: Normal", "Versão insular do campo\nExtremamente raro\nRefúgio seguro"},
            {"[FLOWER]", "Vale de Cerejeiras", "Temperatura: Normal (1.20+)", "Árvores de cerejeira rosa\nPétalas flutuantes\nBioma decorativo novo"},
            {"[VALLEY]", "Vales", "Temperatura: Normal", "Planície entre montanhas\nGrama, flores\nPaisagem única"},
            {"[DARK]", "Deep Dark", "Dimensão: Subterrânea", "Sculk, Warden\nCidades antigas\nMuito perigoso"},
            {"[CAVE]", "Cavernas Exuberantes", "Dimensão: Subterrânea", "Vegetação subterrânea\nAzaleias, água brilhante\nAxolotes"},
            {"[CAVE]", "Cavernas de Estalactites", "Dimensão: Subterrânea", "Estalactites e estalagmites\nGotejamento de água\nCobre, ametista"},
            
            // Dimensões
            {"[NETHER]", "The Nether", "Dimensão: Inferno", "Netherrack, lava infinita\nFortalezas, bastiões\nPiglins, blazes, ghasts"},
            {"[FIRE]", "Nether Wastes", "Dimensão: Nether", "Netherrack aberto, lava\nQuartzo, ouro\nZumbi Piglins, ghasts"},
            {"[BASTION]", "Bastion Remnants", "Dimensão: Nether", "Ruínas de piglin\nTesouro, netherite\nPiglin Brutos"},
            {"[FOREST]", "Warped Forest", "Dimensão: Nether", "Floresta azul-turquesa\nEnderman, sem piglins\nSeguro no Nether"},
            {"[FOREST]", "Crimson Forest", "Dimensão: Nether", "Floresta vermelha\nHoglins, piglins\nVegetação carmesim"},
            {"[BASALT]", "Basalt Deltas", "Dimensão: Nether", "Pilares de basalto negro\nMagma cubes, ghasts\nTerreno acidentado"},
            {"[SOUL]", "Soul Sand Valley", "Dimensão: Nether", "Vale de areia das almas\nFósseis, azul fantasmagórico\nEsqueletos, ghasts"},
            {"[END]", "The End", "Dimensão: Vazio", "End stone amarelo\nIlhas flutuantes\nEnderman, Shulkers"},
            {"[DRAGON]", "Ilha Central do End", "Dimensão: End", "Ilha com pilares de obsidiana\nEnder Dragon\nPortal do End"},
            {"[CITY]", "Ilhas Externas do End", "Dimensão: End", "Ilhas pequenas dispersas\nCidades do End, Elytra\nShulkers, chorus"},
            {"[HIGHLANDS]", "Highlands do End", "Dimensão: End", "Ilhas grandes com árvores chorus\nCidades frequentes\nShulkers"},
            
            // Biomas de Caverna (1.18+)
            {"[CAVE]", "Cavernas Comuns", "Subterrâneo", "Cavernas de pedra naturais\nMinérios variados\nBatatas, zumbis"},
            {"[WATER]", "Aquíferos", "Subterrâneo", "Grandes bolsões de água\nArgila, afogados\nLavas subaquáticas"},
            {"[RAVINE]", "Ravinas", "Subterrâneo", "Fissuras profundas\nMinérios expostos\nPerigoso (quedas)"}
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

        JButton backButton = new JButton("[HOME] Voltar ao Menu");
        backButton.setIcon(ImageManager.getItemIcon("OAK_PLANKS", 16));
        backButton.setIconTextGap(6);
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
