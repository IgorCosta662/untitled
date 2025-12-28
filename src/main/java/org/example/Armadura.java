package org.example;

import java.util.ArrayList;
import java.util.List;

public class Armadura {
    private String nome;
    private String tipo; // Capacete, Peitoral, Calça, Botas
    private String material; // Couro, Ferro, Ouro, Diamante, Netherite, etc.
    private String descricao;
    private int defesa;
    private double durabilidade;
    private MinecraftEdition edicao;
    private List<String> ingredientes;
    private String moldeFerraria; // Para upgrades (ex: Netherite)
    private String receitaMolde; // Como obter o molde
    private String imagemPath; // Caminho para imagem (futuro)

    public Armadura(String nome, String tipo, String material, String descricao, 
                   int defesa, double durabilidade, MinecraftEdition edicao) {
        this.nome = nome;
        this.tipo = tipo;
        this.material = material;
        this.descricao = descricao;
        this.defesa = defesa;
        this.durabilidade = durabilidade;
        this.edicao = edicao;
        this.ingredientes = new ArrayList<>();
    }

    public void adicionarIngrediente(String ingrediente) {
        ingredientes.add(ingrediente);
    }

    public void setMoldeFerraria(String molde) {
        this.moldeFerraria = molde;
    }

    public void setReceitaMolde(String receita) {
        this.receitaMolde = receita;
    }

    public void setImagemPath(String path) {
        this.imagemPath = path;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMaterial() {
        return material;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDefesa() {
        return defesa;
    }

    public double getDurabilidade() {
        return durabilidade;
    }

    public MinecraftEdition getEdicao() {
        return edicao;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public String getMoldeFerraria() {
        return moldeFerraria;
    }

    public String getReceitaMolde() {
        return receitaMolde;
    }

    public String getImagemPath() {
        return imagemPath;
    }

    public String getDefesaCompleta() {
        return defesa + " pontos de defesa";
    }

    public String getDurabilidadeCompleta() {
        return (int) durabilidade + " de durabilidade";
    }
}
