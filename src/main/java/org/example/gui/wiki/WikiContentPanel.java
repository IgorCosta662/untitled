package org.example.gui.wiki;

import org.example.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;
import java.util.List;

public class WikiContentPanel extends JPanel {
    private WikiMainWindow parent;
    private MinecraftWiki wiki;
    private JTextPane textPane;
    private StyledDocument doc;

    public WikiContentPanel(WikiMainWindow parent, MinecraftWiki wiki) {
        this.parent = parent;
        this.wiki = wiki;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        setBackground(WikiMainWindow.WIKI_CONTENT_BG);
        setBorder(new EmptyBorder(20, 30, 20, 30));

        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBackground(WikiMainWindow.WIKI_CONTENT_BG);
        textPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        doc = textPane.getStyledDocument();

        add(textPane, BorderLayout.CENTER);
    }

    public void showHomePage() {
        clearContent();

        addTitle("Bem-vindo à Minecraft Wiki", 32);
        addParagraph("\n");
        addSubtitle("A enciclopédia completa sobre Minecraft", 16);
        addParagraph("\n\n");

        addSectionTitle("🎮 Sobre esta Wiki");
        addParagraph(
            "Esta é uma enciclopédia completa e interativa sobre Minecraft, contendo " +
            "informações detalhadas sobre itens, poções, encantamentos e muito mais! " +
            "Explore o conteúdo usando a barra de busca ou navegue pelas categorias na sidebar."
        );
        addParagraph("\n\n");

        addSectionTitle("📚 Conteúdo Disponível");
        addBulletList(new String[]{
            String.format("📦 %d Itens com receitas de crafting detalhadas", wiki.listarTodosItens().size()),
            String.format("⚗️ %d Poções com ingredientes e efeitos", wiki.listarTodasPocoes().size()),
            String.format("✨ %d Encantamentos com níveis e aplicações", wiki.listarTodosEncantamentos().size()),
            "🔨 Simulador de crafting interativo",
            "📊 Estatísticas completas do banco de dados"
        });
        addParagraph("\n\n");

        addSectionTitle("🚀 Como Usar");
        addParagraph(
            "1. Use a barra de busca no topo para encontrar qualquer item\n" +
            "2. Navegue pelas categorias na sidebar à esquerda\n" +
            "3. Clique nos links azuis para acessar páginas relacionadas\n" +
            "4. Adicione páginas aos favoritos clicando na estrela ⭐\n" +
            "5. Seu histórico de navegação fica salvo na sidebar"
        );
        addParagraph("\n\n");

        addSectionTitle("✨ Páginas em Destaque");
        addLinkList(new String[]{
            "Picareta de Diamante",
            "Espada de Netherite",
            "Poção de Força",
            "Encantamento de Afiação"
        });

        addParagraph("\n\n");
        addFooter();

        textPane.setCaretPosition(0);
    }

    public void showItemPage(String itemName) {
        clearContent();

        List<Item> results = wiki.buscarItens(itemName);
        if (results.isEmpty()) {
            showNotFound(itemName);
            return;
        }

        Item item = results.get(0);

        // Título com favorito
        addTitleWithFavorite(item.getNome(), item.getNome());
        addParagraph("\n");

        // Infobox
        addInfoBox(new String[]{
            "Tipo: " + item.getCategoria(),
            "Edição: " + item.getEdicao().getDisplayName()
        });
        addParagraph("\n");

        addSectionTitle("📝 Descrição");
        addParagraph(item.getDescricao());
        addParagraph("\n\n");

        if (!item.getIngredientes().isEmpty()) {
            addSectionTitle("📋 Ingredientes");
            addBulletList(item.getIngredientes().toArray(new String[0]));
            addParagraph("\n");
        }

        if (item.getPadraoCrafting() != null && !item.getPadraoCrafting().isEmpty()) {
            addSectionTitle("🔨 Receita de Crafting");
            addCodeBlock(item.getPadraoCrafting());
            addParagraph("\n");
        }

        addSectionTitle("📚 Páginas Relacionadas");
        addRelatedPages(item.getCategoria());

        addParagraph("\n\n");
        addFooter();

        textPane.setCaretPosition(0);
    }

    public void showPotionPage(String potionName) {
        clearContent();

        List<Pocao> results = wiki.buscarPocoes(potionName);
        if (results.isEmpty()) {
            showNotFound(potionName);
            return;
        }

        Pocao potion = results.get(0);

        addTitleWithFavorite(potion.getNome(), potion.getNome());
        addParagraph("\n");

        addInfoBox(new String[]{
            "Tipo: Poção",
            "Edição: " + potion.getEdicao().getDisplayName()
        });
        addParagraph("\n");

        addSectionTitle("⚡ Efeito");
        addParagraph(potion.getEfeito());
        addParagraph("\n\n");

        addSectionTitle("📋 Preparação");
        addParagraph("Use um Suporte de Poções (Brewing Stand) com os seguintes ingredientes:");
        addParagraph("\n");

        addCodeBlock(
            "1. Garrafa de Água\n" +
            "2. Verruga do Nether (→ Poção Estranha)\n" +
            "3. Ingrediente Principal\n" +
            "4. [Opcional] Modificadores"
        );
        addParagraph("\n");

        addSectionTitle("📚 Páginas Relacionadas");
        addLinkList(new String[]{
            "Suporte de Poções",
            "Verruga do Nether",
            "Todas as Poções"
        });

        addParagraph("\n\n");
        addFooter();

        textPane.setCaretPosition(0);
    }

    public void showEnchantmentPage(String enchantmentName) {
        clearContent();

        List<Encantamento> results = wiki.buscarEncantamentos(enchantmentName);
        if (results.isEmpty()) {
            showNotFound(enchantmentName);
            return;
        }

        Encantamento enc = results.get(0);

        addTitleWithFavorite(enc.getNome(), enc.getNome());
        addParagraph("\n");

        addInfoBox(new String[]{
            "Tipo: Encantamento",
            "Edição: " + enc.getEdicao().getDisplayName()
        });
        addParagraph("\n");

        addSectionTitle("✨ Descrição");
        addParagraph("Este encantamento possui propriedades especiais que melhoram o desempenho do item.");
        addParagraph("\n\n");

        addSectionTitle("📖 Como Obter");
        addBulletList(new String[]{
            "Mesa de Encantamento (requer Lápis-lazúli e XP)",
            "Bigorna (combinar livros encantados)",
            "Pescaria (livros encantados)",
            "Troca com Aldeões Bibliotecários",
            "Baús em estruturas geradas"
        });
        addParagraph("\n");

        addSectionTitle("📚 Páginas Relacionadas");
        addLinkList(new String[]{
            "Mesa de Encantamento",
            "Bigorna",
            "Todos os Encantamentos"
        });

        addParagraph("\n\n");
        addFooter();

        textPane.setCaretPosition(0);
    }

    public void showCategoryPage(String category) {
        clearContent();

        addTitle("Categoria: " + category, 28);
        addParagraph("\n\n");

        if (category.contains("Itens")) {
            List<Item> items = wiki.listarTodosItens();
            addParagraph(String.format("Esta categoria contém %d itens.\n\n", items.size()));

            for (Item item : items) {
                addLink("📦 " + item.getNome(), () -> parent.showItemPage(item.getNome()));
                addParagraph("\n");
            }
        } else if (category.contains("Poções")) {
            List<Pocao> potions = wiki.listarTodasPocoes();
            addParagraph(String.format("Esta categoria contém %d poções.\n\n", potions.size()));

            for (Pocao potion : potions) {
                addLink("⚗️ " + potion.getNome(), () -> parent.showPotionPage(potion.getNome()));
                addParagraph("\n");
            }
        } else if (category.contains("Encantamentos")) {
            List<Encantamento> enchantments = wiki.listarTodosEncantamentos();
            addParagraph(String.format("Esta categoria contém %d encantamentos.\n\n", enchantments.size()));

            for (Encantamento enc : enchantments) {
                addLink("✨ " + enc.getNome(), () -> parent.showEnchantmentPage(enc.getNome()));
                addParagraph("\n");
            }
        } else {
            // Buscar por categoria específica
            List<Item> items = wiki.buscarItens(category);
            addParagraph(String.format("Encontrados %d itens nesta categoria.\n\n", items.size()));

            for (Item item : items) {
                addLink("📦 " + item.getNome(), () -> parent.showItemPage(item.getNome()));
                addParagraph("\n");
            }
        }

        addParagraph("\n");
        addFooter();

        textPane.setCaretPosition(0);
    }

    public void showSearchResults(String query) {
        clearContent();

        addTitle("Resultados da busca: \"" + query + "\"", 24);
        addParagraph("\n\n");

        List<Item> items = wiki.buscarItens(query);
        List<Pocao> potions = wiki.buscarPocoes(query);
        List<Encantamento> enchantments = wiki.buscarEncantamentos(query);

        int total = items.size() + potions.size() + enchantments.size();

        if (total == 0) {
            showNotFound(query);
            return;
        }

        addParagraph(String.format("Encontrados %d resultado(s)\n\n", total));

        if (!items.isEmpty()) {
            addSectionTitle("📦 Itens (" + items.size() + ")");
            for (Item item : items) {
                addLink("  • " + item.getNome() + " - " + item.getDescricao(),
                       () -> parent.showItemPage(item.getNome()));
                addParagraph("\n");
            }
            addParagraph("\n");
        }

        if (!potions.isEmpty()) {
            addSectionTitle("⚗️ Poções (" + potions.size() + ")");
            for (Pocao potion : potions) {
                addLink("  • " + potion.getNome() + " - " + potion.getEfeito(),
                       () -> parent.showPotionPage(potion.getNome()));
                addParagraph("\n");
            }
            addParagraph("\n");
        }

        if (!enchantments.isEmpty()) {
            addSectionTitle("✨ Encantamentos (" + enchantments.size() + ")");
            for (Encantamento enc : enchantments) {
                addLink("  • " + enc.getNome(),
                       () -> parent.showEnchantmentPage(enc.getNome()));
                addParagraph("\n");
            }
        }

        addParagraph("\n\n");
        addFooter();

        textPane.setCaretPosition(0);
    }

    private void showNotFound(String query) {
        addTitle("Página não encontrada", 28);
        addParagraph("\n\n");
        addParagraph("A página \"" + query + "\" não foi encontrada na Minecraft Wiki.");
        addParagraph("\n\n");
        addParagraph("Sugestões:");
        addBulletList(new String[]{
            "Verifique a ortografia",
            "Use a barra de busca para encontrar conteúdo relacionado",
            "Navegue pelas categorias na sidebar",
            "Volte para a página inicial"
        });
        textPane.setCaretPosition(0);
    }

    // Métodos de formatação
    private void clearContent() {
        textPane.setText("");
    }

    private void addTitle(String text, int size) {
        Style style = textPane.addStyle("Title", null);
        StyleConstants.setFontSize(style, size);
        StyleConstants.setBold(style, true);
        StyleConstants.setForeground(style, WikiMainWindow.WIKI_TEXT);
        StyleConstants.setSpaceAbove(style, 10);
        StyleConstants.setSpaceBelow(style, 10);

        try {
            doc.insertString(doc.getLength(), text, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void addTitleWithFavorite(String text, String pageName) {
        addTitle(text + " ", 28);

        String starIcon = parent.isFavorite(pageName) ? "⭐" : "☆";
        Style starStyle = textPane.addStyle("Star", null);
        StyleConstants.setFontSize(starStyle, 24);
        StyleConstants.setForeground(starStyle, WikiMainWindow.MINECRAFT_GREEN);

        try {
            int start = doc.getLength();
            doc.insertString(doc.getLength(), starIcon, starStyle);
            int end = doc.getLength();

            // Tornar a estrela clicável
            textPane.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    int pos = textPane.viewToModel2D(e.getPoint());
                    if (pos >= start && pos < end) {
                        parent.toggleFavorite(pageName);
                        showItemPage(pageName); // Recarregar para atualizar estrela
                    }
                }
            });
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void addSubtitle(String text, int size) {
        Style style = textPane.addStyle("Subtitle", null);
        StyleConstants.setFontSize(style, size);
        StyleConstants.setItalic(style, true);
        StyleConstants.setForeground(style, Color.GRAY);

        try {
            doc.insertString(doc.getLength(), text, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void addSectionTitle(String text) {
        Style style = textPane.addStyle("SectionTitle", null);
        StyleConstants.setFontSize(style, 18);
        StyleConstants.setBold(style, true);
        StyleConstants.setForeground(style, WikiMainWindow.WIKI_HEADER);
        StyleConstants.setSpaceAbove(style, 15);
        StyleConstants.setSpaceBelow(style, 10);

        try {
            doc.insertString(doc.getLength(), text + "\n", style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void addParagraph(String text) {
        Style style = textPane.addStyle("Paragraph", null);
        StyleConstants.setFontSize(style, 14);
        StyleConstants.setForeground(style, WikiMainWindow.WIKI_TEXT);
        StyleConstants.setLineSpacing(style, 0.2f);

        try {
            doc.insertString(doc.getLength(), text, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void addBulletList(String[] items) {
        for (String item : items) {
            addParagraph("  • " + item + "\n");
        }
    }

    private void addLinkList(String[] items) {
        for (String item : items) {
            addLink("  🔗 " + item, () -> parent.performSearch(item));
            addParagraph("\n");
        }
    }

    private void addLink(String text, Runnable action) {
        Style style = textPane.addStyle("Link", null);
        StyleConstants.setFontSize(style, 14);
        StyleConstants.setForeground(style, WikiMainWindow.WIKI_LINK);
        StyleConstants.setUnderline(style, true);

        try {
            int start = doc.getLength();
            doc.insertString(doc.getLength(), text, style);
            int end = doc.getLength();

            textPane.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    int pos = textPane.viewToModel2D(e.getPoint());
                    if (pos >= start && pos < end) {
                        action.run();
                    }
                }
            });
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void addCodeBlock(String code) {
        Style style = textPane.addStyle("Code", null);
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
        StyleConstants.setForeground(style, new Color(0, 100, 0));
        StyleConstants.setBackground(style, new Color(245, 245, 245));

        try {
            doc.insertString(doc.getLength(), code + "\n", style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void addInfoBox(String[] info) {
        Style style = textPane.addStyle("InfoBox", null);
        StyleConstants.setFontSize(style, 13);
        StyleConstants.setForeground(style, WikiMainWindow.WIKI_TEXT);
        StyleConstants.setBackground(style, new Color(240, 248, 255));
        StyleConstants.setLeftIndent(style, 20);
        StyleConstants.setRightIndent(style, 20);

        try {
            doc.insertString(doc.getLength(), "╔══════════════════════════╗\n", style);
            for (String line : info) {
                doc.insertString(doc.getLength(), "║ " + line + "\n", style);
            }
            doc.insertString(doc.getLength(), "╚══════════════════════════╝\n", style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void addRelatedPages(String category) {
        List<Item> related = wiki.buscarItens(category);
        int count = 0;
        for (Item item : related) {
            if (count++ >= 5) break;
            addLink("  • " + item.getNome(), () -> parent.showItemPage(item.getNome()));
            addParagraph("\n");
        }
    }

    private void addFooter() {
        Style style = textPane.addStyle("Footer", null);
        StyleConstants.setFontSize(style, 11);
        StyleConstants.setForeground(style, Color.GRAY);
        StyleConstants.setItalic(style, true);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);

        try {
            doc.insertString(doc.getLength(), "\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n", style);
            doc.insertString(doc.getLength(), "Minecraft Wiki • © 2025 • Desenvolvido com Java\n", style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}

