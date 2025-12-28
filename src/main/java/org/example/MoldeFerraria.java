package org.example;

public class MoldeFerraria {
    private String nome;
    private String tipo; // "upgrade" ou "trim"
    private String descricao;
    private String localizacao;
    private String uso;
    private MinecraftEdition edicao;

    public MoldeFerraria(String nome, String tipo, String descricao, String localizacao, String uso, MinecraftEdition edicao) {
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.uso = uso;
        this.edicao = edicao;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getUso() {
        return uso;
    }

    public MinecraftEdition getEdicao() {
        return edicao;
    }

    public boolean isUpgrade() {
        return "upgrade".equals(tipo);
    }

    public boolean isTrim() {
        return "trim".equals(tipo);
    }
}
