package org.example;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private String nome;
    private String descricao;
    private MinecraftEdition edicao;
    private List<String> ingredientes;
    private String padraoCrafting;
    private String categoria;
    private String imagemItem;  // Caminho para imagem do item
    private String imagemCrafting;  // Caminho para imagem da receita de crafting

    public Item(String nome, String descricao, MinecraftEdition edicao, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.edicao = edicao;
        this.categoria = categoria;
        this.ingredientes = new ArrayList<>();
    }

    public void adicionarIngrediente(String ingrediente) {
        ingredientes.add(ingrediente);
    }

    public void setPadraoCrafting(String padrao) {
        this.padraoCrafting = padrao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public MinecraftEdition getEdicao() {
        return edicao;
    }

    public String getCategoria() {
        return categoria;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public String getPadraoCrafting() {
        return padraoCrafting;
    }

    public String getImagemItem() {
        return imagemItem;
    }

    public void setImagemItem(String imagemItem) {
        this.imagemItem = imagemItem;
    }

    public String getImagemCrafting() {
        return imagemCrafting;
    }

    public void setImagemCrafting(String imagemCrafting) {
        this.imagemCrafting = imagemCrafting;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n╔════════════════════════════════════════════════════════════╗\n");
        sb.append(String.format("  ITEM: %s\n", nome));
        sb.append("╠════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("  Categoria: %s\n", categoria));
        sb.append(String.format("  Edição: %s\n", edicao.getDisplayName()));
        sb.append(String.format("  Descrição: %s\n", descricao));

        if (!ingredientes.isEmpty()) {
            sb.append("\n  ► Ingredientes necessários:\n");
            for (String ing : ingredientes) {
                sb.append(String.format("    • %s\n", ing));
            }
        }

        if (padraoCrafting != null && !padraoCrafting.isEmpty()) {
            sb.append("\n  ► Padrão de Crafting:\n");
            sb.append(padraoCrafting);
        }

        sb.append("╚════════════════════════════════════════════════════════════╝\n");
        return sb.toString();
    }
}

