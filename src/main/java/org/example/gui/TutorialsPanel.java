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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TutorialsPanel extends JPanel {
    private final MinecraftWikiGUI parent;
    private static final Color MINECRAFT_GREEN = new Color(85, 255, 85);
    private static final Color MINECRAFT_GOLD = new Color(255, 170, 0);
    private static final Color DARK_BG = new Color(40, 40, 40);

    public TutorialsPanel(MinecraftWikiGUI parent) {
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(DARK_BG);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("📚 TUTORIAIS E GUIAS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(MINECRAFT_GOLD);
        add(titleLabel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.addTab("🌱 Iniciantes", createBeginnersPanel());
        tabbedPane.addTab("⚔️ Sobrevivência", createSurvivalPanel());
        tabbedPane.addTab("🏗️ Construção", createBuildingPanel());

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

    private JScrollPane createBeginnersPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        String tutorialText = """
                🌟 BEM-VINDO AO MINECRAFT!
                
                🎯 PRIMEIRO DIA - O QUE FAZER
                
                1️⃣ COLETE MADEIRA (5-10 minutos antes do anoitecer!)
                   • Soque árvores com as mãos
                   • Abra inventário (E) e faça tábuas
                   • Use tábuas para fazer mesa de trabalho
                
                2️⃣ FERRAMENTAS BÁSICAS
                   • Picareta de madeira (minerar pedra)
                   • Machado de madeira (cortar madeira mais rápido)
                   • Espada de madeira (defesa contra mobs)
                
                3️⃣ ABRIGO ANTES DA NOITE
                   • Cave uma toca na montanha, OU
                   • Construa parede 3 blocos alto ao redor
                   • Coloque tochas dentro (precisará de carvão)
                
                4️⃣ CARVÃO = PRIORIDADE
                   • Procure pedra com manchas pretas
                   • Use picareta de madeira para minerar
                   • Carvão serve para tochas e fornalha
                
                📋 RECEITAS ESSENCIAIS DO INÍCIO
                
                Mesa de Trabalho:
                □ □    □ = tábua de madeira
                □ □
                
                Picareta:
                □ □ □    □ = tábua/pedra/ferro
                  |      | = graveto
                  |
                
                Tocha:
                 C       C = carvão ou carvão vegetal
                 |       | = graveto
                
                Fornalha:
                □ □ □    □ = pedra (não paralelepípedo!)
                □   □
                □ □ □
                
                🎒 ITENS PARA SEMPRE TER
                
                ✓ Mesa de trabalho portátil
                ✓ Cama (3 lã + 3 tábuas) para pular a noite
                ✓ Espada para defesa
                ✓ Picareta reserva
                ✓ Comida (carne cozida é melhor)
                ✓ Tochas (MUITAS tochas!)
                
                💡 DICAS IMPORTANTES
                
                • NUNCA cave direto para baixo (pode cair em lava)
                • SEMPRE carregue água em balde (contra lava)
                • Marque caminho com tochas (sempre no lado direito)
                • Shift (agachar) impede de cair de bordas
                • F3 mostra coordenadas (anote sua base!)
                • Coma ANTES da barra de fome esvaziar
                • Camas resetam seu spawn point
                
                🏠 EXPANDINDO SUA BASE
                
                Dia 2-3:
                • Melhore ferramentas para pedra
                • Crie armadura de couro
                • Plante trigo para pão
                • Faça cerca ao redor da base
                
                Dia 4-7:
                • Procure ferro em cavernas (Y 0-64)
                • Faça fornalha e fundir ferro
                • Armadura e ferramentas de ferro
                • Explore ao redor (leve bússola!)
                
                Dia 8+:
                • Procure diamantes (Y -64 a -16)
                • Monte fazendas de animais
                • Explore Nether (portal 4x5 de obsidiana)
                • Procure vila de aldeões
                """;

        JTextArea textArea = new JTextArea(tutorialText);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(DARK_BG);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        panel.add(textArea);

        return new JScrollPane(panel);
    }

    private JScrollPane createSurvivalPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        String survivalText = """
                ⚔️ GUIA DE SOBREVIVÊNCIA AVANÇADO
                
                💎 MINERAÇÃO EFICIENTE
                
                🔹 TÉCNICA DE MINERAÇÃO EM GALHOS
                • Cave túnel principal no nível Y -54
                • Faça galhos laterais a cada 3 blocos
                • Cubra máxima área com mínimo esforço
                
                🔹 MELHORES NÍVEIS PARA MINÉRIOS:
                • Diamante: Y -64 a -16 (melhor: -54)
                • Ferro: Y -32 a 256 (melhor: 16)
                • Ouro: Y -64 a 32 (melhor: -16)
                • Cobre: Y -16 a 112 (melhor: 48)
                • Lápis: Y -32 a 64 (melhor: 0)
                • Carvão: Y 0 a 256 (melhor: 96)
                • Redstone: Y -64 a 16 (melhor: -54)
                
                🛡️ COMBATE E DEFESA
                
                ⚔️ EQUIPAMENTO RECOMENDADO:
                • Armadura completa de ferro (mínimo)
                • Espada com Sharpness III+
                • Arco com Power III+ e Infinity
                • Escudo para bloquear
                • Poção de Cura Instantânea II
                • Maçã Dourada (emergência)
                
                👹 COMO LUTAR CONTRA CADA MOB:
                
                Creeper: Ataque e recue, NUNCA deixe explodir perto
                Esqueleto: Use escudo, aproxime em zigzag
                Zumbi: Ataque crítico (pulo + ataque), recue
                Enderman: Use abóbora na cabeça, lute em teto baixo
                Aranha: Cave buraco 2x1 (ela não passa)
                Phantom: Durma regularmente, ou lute com arco
                
                🏰 BOSS FIGHTS
                
                🐉 ENDER DRAGON:
                • Destrua cristais do End com arco/bola de neve
                • Espere dragon pousar no portal
                • Ataque cabeça quando ele pousa
                • Use Slow Falling para evitar knockback fatal
                • Leve Ender Pearls para subir torres
                
                💀 WITHER:
                • Construa arena 3 blocos alto (limita movimento)
                • Use armadura netherite com Protection IV
                • Espada com Smite V (dano extra a undead)
                • Arco com Power V para fase voadora
                • Poções de Força II e Regeneração
                • Golden Apples para emergências
                
                🍖 GESTÃO DE FOME
                
                MELHORES COMIDAS (saciedade):
                1. Bife/Costeleta de Porco = 8 🍖 (12.8 saturação)
                2. Salmão Cozido = 6 🍖 (9.6 saturação)
                3. Pão = 5 🍖 (6.0 saturação)
                4. Maçã Dourada = 4 🍖 + Regeneração + Absorção
                
                🔥 SOBREVIVENDO NO NETHER
                
                CHECKLIST ANTES DE IR:
                ✓ Armadura completa de ferro/diamante
                ✓ Picareta de diamante (obsidiana)
                ✓ Espada afiada
                ✓ Arco com muitas flechas
                ✓ Comida (steak x32+)
                ✓ Blocos de paralelepípedo (bridging)
                ✓ Poções de Resistência ao Fogo
                ✓ Ender Pearls (emergência)
                
                DICAS DO NETHER:
                • NUNCA durma (cama explode!)
                • Sempre faça pontes com blocos
                • Não ataque Piglin sem armadura de ouro
                • Traga água (não funciona) → use lava + água para obsidiana
                • Marque caminho com tochas ou blocos diferentes
                
                🌍 EXPLORÃÇÃO E VIAGEM
                
                📍 NAVEGAÇÃO:
                • F3 mostra coordenadas (anote base!)
                • Bússola sempre aponta para spawn
                • Lodestone + Bússola = waypoint customizado
                • Mapas revelam área ao redor
                
                🧭 DISTÂNCIA NETHER:
                • 1 bloco no Nether = 8 blocos no Overworld
                • Use para viagem rápida entre bases distantes
                
                💪 MAXIMIZANDO EFICIÊNCIA
                
                ⚙️ ENCANTAMENTOS ESSENCIAIS:
                Picareta: Efficiency V, Unbreaking III, Mending, Fortune III
                Espada: Sharpness V, Unbreaking III, Mending, Looting III
                Armadura: Protection IV, Unbreaking III, Mending
                Arco: Power V, Infinity, Unbreaking III, Flame
                
                🏆 CONQUISTAS IMPORTANTES:
                □ Obter diamante
                □ Ir ao Nether
                □ Fazer poções
                □ Encontrar fortaleza
                □ Derrotar Ender Dragon
                □ Obter Elytra
                □ Derrotar Wither
                □ Beacon completo
                □ Armadura Netherite completa
                """;

        JTextArea textArea = new JTextArea(survivalText);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(DARK_BG);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        panel.add(textArea);

        return new JScrollPane(panel);
    }

    private JScrollPane createBuildingPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARK_BG);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        String buildingText = """
                🏗️ GUIA DE CONSTRUÇÃO E ARQUITETURA
                
                🎨 PRINCÍPIOS BÁSICOS
                
                1. VARIEDADE DE MATERIAIS
                   Não use apenas 1 tipo de bloco!
                   Combine: madeira + pedra + tijolos
                   Exemplo: Telhado (escadas), Paredes (pedra), Detalhes (madeira)
                
                2. PROFUNDIDADE
                   Adicione camadas para criar sombras:
                   • Janelas afundadas 1 bloco
                   • Pilares que sobressaem
                   • Varandas e sacadas
                
                3. DETALHES
                   Pequenos toques fazem diferença:
                   • Janelas de tamanhos variados
                   • Vasos de flores
                   • Banners decorativos
                   • Trapdoors como janelas
                
                4. ESCALA E PROPORÇÃO
                   • Tetos altos ficam melhores (6+ blocos)
                   • Paredes grossas (2 blocos) para castelos
                   • Portas duplas para entradas grandes
                
                🏠 ESTILOS ARQUITETÔNICOS
                
                🏡 MEDIEVAL:
                • Madeira escura + pedra/tijolos
                • Telhados inclinados íngremes
                • Janelas pequenas e irregulares
                • Vigas de madeira expostas
                • Detalhes em pedregulho
                
                🏰 CASTELO:
                • Paredes de pedra grossas (3+ blocos)
                • Torres nos cantos
                • Ameias (merlon pattern) no topo
                • Portão com grade de ferro
                • Fossas ao redor
                
                🏠 MODERNO:
                • Concreto branco/cinza + vidro
                • Linhas retas e geométricas
                • Grandes janelas de vidro
                • Iluminação embutida (glowstone + carpet)
                • Piscinas infinitas
                
                🌸 JAPONÊS:
                • Madeira clara (carvalho/bétula)
                • Telhados curvos com escadas
                • Jardins zen com areia e cascalho
                • Lanternas (end rods + trapdoors)
                • Cercas de bambu
                
                🏡 RÚSTICO/FAZENDA:
                • Madeira de carvalho + feno
                • Telhados de feno ou madeira
                • Cercas ao redor
                • Detalhes em pedregulho
                • Jardins e fazendas próximas
                
                📐 TÉCNICAS AVANÇADAS
                
                🔹 TERRAFORMING:
                • Suavize terreno em declives naturais
                • Adicione camadas de grama, terra e pedra
                • Use WorldEdit ou VoxelSniper (mods)
                • Plante árvores e vegetação
                
                🔹 LANDSCAPING:
                • Caminhos de caminho de terra ou cascalho
                • Lagos e lagoas artificiais
                • Jardins com flores variadas
                • Árvores customizadas (não padrão)
                
                🔹 ILUMINAÇÃO:
                • Esconda glowstone sob carpetes/lajes
                • Use lanternas/tochas soul para clima sombrio
                • Sea lanterns para construções aquáticas
                • End rods para iluminação moderna
                • Tochas redstone emitem pouca luz (clima)
                
                🔹 CUSTOM FURNITURA:
                • Sofá: escadas + placas/carpetes
                • Mesa: cerca + tapetes em cima
                • Cadeira: escadas + placas
                • Geladeira: ferro + botão
                • TV: quadro na parede
                
                🎯 PALETA DE CORES
                
                CORES NEUTRAS:
                • Acácia, Concreto Branco, Pedra Lisa
                • Combinam com tudo
                
                CORES QUENTES:
                • Tijolos, Terracota Laranja, Madeira de Selva
                • Ambientes acolhedores
                
                CORES FRIAS:
                • Prismarinho, Concreto Ciano, Pedra Azul
                • Ambientes aquáticos ou futuristas
                
                🏗️ ESTRUTURAS GRANDES
                
                PLANEJAMENTO:
                1. Defina dimensões no chão com lã colorida
                2. Construa estrutura básica (pilares, chão, teto)
                3. Adicione paredes
                4. Detalhes externos (janelas, portas)
                5. Interiores por último
                6. Decoração final
                
                FERRAMENTAS ÚTEIS:
                • Régua (string ou lã) para linhas retas
                • Worldedit (mod) para cópia/rotação
                • Litematica (mod) para projetos complexos
                • Papel e lápis para rascunhos!
                
                💡 INSPIRAÇÃO
                
                • Pesquise arquitetura real no Google
                • Visite servidores de construção (Hypixel)
                • Assista timelapses no YouTube
                • Pinterest para paletas de cores
                • r/Minecraft no Reddit para ideias
                
                🎨 PROJETOS PARA INICIANTES
                
                1. Casa Simples com Telhado
                2. Torre de Vigia
                3. Celeiro de Fazenda
                4. Ponte Sobre Rio
                5. Fonte Central
                6. Caminho de Jardim
                7. Dock/Pier de Pesca
                8. Capela Pequena
                
                🏆 PROJETOS AVANÇADOS
                
                1. Castelo Medieval Completo
                2. Cidade Inteira
                3. Catedral Gótica
                4. Nave Espacial
                5. Reino Submarino
                6. Mansão Vitoriana
                7. Forte Militar
                8. Pixel Art Gigante (estátuas)
                """;

        JTextArea textArea = new JTextArea(buildingText);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(DARK_BG);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        panel.add(textArea);

        return new JScrollPane(panel);
    }
}
