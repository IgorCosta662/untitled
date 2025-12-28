package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.example.api.MinecraftWikiAPISimple;

/**
 * Painel para demonstrar integração com APIs (versão simplificada sem dependências externas)
 * Para usar a versão completa com Gson, OkHttp, etc., instale Maven e execute: mvn clean install
 */
public class APITestPanel extends JPanel {
    
    private final MinecraftWikiAPISimple wikiAPI;
    private JTextArea outputArea;
    private JTextField searchField;
    private JLabel statusLabel;
    
    public APITestPanel() {
        this.wikiAPI = new MinecraftWikiAPISimple();
        setupUI();
        updateStatusLabel();
    }
    
    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(40, 40, 40));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Painel superior - Busca
        JPanel topPanel = createSearchPanel();
        add(topPanel, BorderLayout.NORTH);
        
        // Painel central - Output
        this.outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(30, 30, 30));
        outputArea.setForeground(Color.WHITE);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 2));
        add(scrollPane, BorderLayout.CENTER);
        
        // Painel inferior - Status e controles
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(new Color(40, 40, 40));
        
        // Título
        JLabel titleLabel = new JLabel("🔍 API Test Console - Minecraft Wiki");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GREEN);
        
        // Campo de busca
        this.searchField = new JTextField();
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setBackground(new Color(60, 60, 60));
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BLUE, 2),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        
        // Botão de busca
        JButton searchButton = new JButton("Buscar Item");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        searchButton.setBackground(MinecraftWikiGUI.MINECRAFT_GREEN.darker());
        searchButton.setForeground(Color.WHITE);
        searchButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        searchButton.setFocusPainted(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        searchButton.addActionListener(e -> searchItem(searchField.getText()));
        searchField.addActionListener(e -> searchItem(searchField.getText()));
        
        JPanel inputPanel = new JPanel(new BorderLayout(10, 0));
        inputPanel.setOpaque(false);
        inputPanel.add(searchField, BorderLayout.CENTER);
        inputPanel.add(searchButton, BorderLayout.EAST);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(Box.createVerticalStrut(10), BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(40, 40, 40));
        
        // Status label
        this.statusLabel = new JLabel("Status: Aguardando busca...");
        statusLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        statusLabel.setForeground(MinecraftWikiGUI.MINECRAFT_GOLD);
        
        // Botões de ação
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonsPanel.setOpaque(false);
        
        JButton cacheStatsButton = createActionButton("📊 Stats Cache", e -> showCacheStats());
        JButton clearCacheButton = createActionButton("🗑️ Limpar Cache", e -> clearCache());
        JButton reconnectButton = createActionButton("🔄 Reconectar", e -> reconnect());
        JButton testImageButton = createActionButton("🖼️ Testar Imagem", e -> testImage());
        
        buttonsPanel.add(cacheStatsButton);
        buttonsPanel.add(clearCacheButton);
        buttonsPanel.add(reconnectButton);
        buttonsPanel.add(testImageButton);
        
        panel.add(statusLabel, BorderLayout.WEST);
        panel.add(buttonsPanel, BorderLayout.EAST);
        
        return panel;
    }
    
    private JButton createActionButton(String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, 11));
        button.setBackground(new Color(70, 70, 70));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MinecraftWikiGUI.MINECRAFT_BLUE, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(listener);
        return button;
    }
    
    private void searchItem(String itemName) {
        if (itemName == null || itemName.trim().isEmpty()) {
            outputArea.setText("❌ Digite um nome de item para buscar!\n\n💡 Exemplos: Diamond, Sword, Pickaxe, Iron");
            return;
        }
        
        outputArea.setText("🔍 Buscando: " + itemName + "...\n\n");
        statusLabel.setText("Status: Buscando...");
        
        // Buscar em thread separada para não travar UI
        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() {
                return wikiAPI.searchItemSimple(itemName);
            }
            
            @Override
            protected void done() {
                try {
                    String result = get();
                    if (result != null && !result.isEmpty()) {
                        displayResult(result);
                        statusLabel.setText("Status: ✅ Busca concluída");
                    } else {
                        outputArea.append("❌ Nenhum resultado encontrado ou erro na conexão\n\n");
                        outputArea.append("💡 Dica: Verifique sua conexão com a internet\n");
                        outputArea.append("💡 Para usar a versão completa com cache, instale Maven:\n");
                        outputArea.append("   Veja o arquivo MAVEN_INSTALL.md");
                        statusLabel.setText("Status: ⚠️ Sem resultados");
                    }
                } catch (Exception e) {
                    outputArea.append("❌ Erro: " + e.getMessage() + "\n");
                    statusLabel.setText("Status: ❌ Erro na busca");
                }
            }
        };
        
        worker.execute();
    }
    
    private void displayResult(String result) {
        StringBuilder output = new StringBuilder();
        output.append("✅ Resultado da API:\n\n");
        output.append("═══════════════════════════════════════\n\n");
        
        // Exibir JSON raw (na versão com Gson seria formatado)
        output.append(result);
        
        output.append("\n\n═══════════════════════════════════════\n\n");
        output.append("💡 Nota: Este é o JSON bruto retornado pela API.\n");
        output.append("   Com Maven e Gson instalados, seria formatado automaticamente!");
        
        outputArea.setText(output.toString());
    }
    
    private void showCacheStats() {
        String message = 
            "📊 Informações sobre APIs\n\n" +
            "════════════════════════════════════\n\n" +
            "✅ APIS DISPONÍVEIS:\n\n" +
            "1. Minecraft Wiki API (MediaWiki)\n" +
            "   - Busca de itens\n" +
            "   - Conteúdo de páginas\n" +
            "   - URLs de imagens\n\n" +
            "2. Cache SQLite (requer Maven)\n" +
            "   - Armazenamento local\n" +
            "   - Reduz requisições\n\n" +
            "3. Gson/Jackson (requer Maven)\n" +
            "   - Parsing JSON avançado\n\n" +
            "4. OkHttp (requer Maven)\n" +
            "   - HTTP client moderno\n\n" +
            "════════════════════════════════════\n\n" +
            "📦 VERSÃO ATUAL: Simplificada\n" +
            "   Usa apenas Java built-in APIs\n\n" +
            "🚀 PARA USAR VERSÃO COMPLETA:\n" +
            "   1. Instale Maven (veja MAVEN_INSTALL.md)\n" +
            "   2. Execute: mvn clean install\n" +
            "   3. Recompile o projeto\n\n" +
            "════════════════════════════════════\n\n" +
            "Status: " + (wikiAPI.testConnection() ? "✅ API Online" : "⚠️ API Offline");
        
        outputArea.setText(message);
        statusLabel.setText("Status: Informações exibidas");
    }
    
    private void clearCache() {
        String message = 
            "ℹ️ Cache Manager\n\n" +
            "O sistema de cache completo requer Maven.\n\n" +
            "Com Maven instalado, você terá:\n" +
            "  • Cache automático de buscas\n" +
            "  • Expiração inteligente (24h)\n" +
            "  • Modo offline automático\n" +
            "  • Estatísticas detalhadas\n\n" +
            "Para instalar: veja MAVEN_INSTALL.md";
        
        outputArea.setText(message);
    }
    
    private void reconnect() {
        outputArea.setText("🔄 Testando conexão com API...\n");
        statusLabel.setText("Status: Testando...");
        
        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() {
                return wikiAPI.testConnection();
            }
            
            @Override
            protected void done() {
                try {
                    boolean success = get();
                    if (success) {
                        outputArea.append("✅ Conectado com sucesso à Minecraft Wiki API!\n\n");
                        outputArea.append("Você pode buscar itens normalmente.\n");
                        statusLabel.setText("Status: ✅ Online");
                    } else {
                        outputArea.append("❌ Sem conexão com a API\n\n");
                        outputArea.append("Possíveis causas:\n");
                        outputArea.append("  • Sem internet\n");
                        outputArea.append("  • Firewall bloqueando\n");
                        outputArea.append("  • API temporariamente indisponível\n");
                        statusLabel.setText("Status: ⚠️ Offline");
                    }
                    updateStatusLabel();
                } catch (Exception e) {
                    outputArea.append("❌ Erro: " + e.getMessage() + "\n");
                }
            }
        };
        
        worker.execute();
    }
    
    private void testImage() {
        String message = 
            "🖼️ Busca de Imagens\n\n" +
            "Esta funcionalidade completa requer Maven.\n\n" +
            "Com Maven, você pode:\n" +
            "  • Buscar URLs de imagens da wiki\n" +
            "  • Fazer cache de imagens\n" +
            "  • Integrar imagens na GUI\n\n" +
            "Exemplo de uso:\n" +
            "  String url = api.getImageUrl(\"Diamond_Sword.png\");\n\n" +
            "Para instalar: veja MAVEN_INSTALL.md";
        
        outputArea.setText(message);
    }
    
    private void updateStatusLabel() {
        boolean online = wikiAPI.testConnection();
        statusLabel.setText(online ? 
            "Status: ✅ API Online (versão simplificada)" : 
            "Status: ⚠️ API Offline"
        );
    }
}
