package org.example;

public class Encantamento {
    private final String nome;
    private final String descricao;
    private final int nivelMaximo;
    private final MinecraftEdition edicao;
    private final String incompativel;
    private final boolean tesouro;
    private final int peso;
    private final String itensPrimarios;
    private final String itensSecundarios;

    public Encantamento(String nome, String descricao, boolean tesouro, String incompativel, 
                       int nivelMaximo, String itensPrimarios, String itensSecundarios, 
                       int peso, MinecraftEdition edicao) {
        this.nome = nome;
        this.descricao = descricao;
        this.tesouro = tesouro;
        this.incompativel = incompativel;
        this.nivelMaximo = nivelMaximo;
        this.itensPrimarios = itensPrimarios;
        this.itensSecundarios = itensSecundarios;
        this.peso = peso;
        this.edicao = edicao;
    }

    public String getNome() {
        return nome;
    }

    public MinecraftEdition getEdicao() {
        return edicao;
    }

    public boolean isTesouro() {
        return tesouro;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n╔════════════════════════════════════════════════════════════╗\n");
        sb.append(String.format("  ENCANTAMENTO: %s\n", nome));
        sb.append("╠════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("  Edição: %s\n", edicao.getDisplayName()));
        sb.append(String.format("  Nível Máximo: %d\n", nivelMaximo));
        sb.append(String.format("  Tesouro: %s\n", tesouro ? "Sim" : "Não"));
        sb.append(String.format("  Peso: %d\n", peso));
        sb.append(String.format("\n  Descrição: %s\n", descricao));
        
        sb.append(String.format("\n  ► Item Primário: %s\n", itensPrimarios));
        if (itensSecundarios != null && !itensSecundarios.isEmpty()) {
            sb.append(String.format("  ► Item Secundário: %s\n", itensSecundarios));
        }

        if (incompativel != null && !incompativel.isEmpty()) {
            sb.append(String.format("\n  ⚠ Incompatível com: %s\n", incompativel));
        }

        sb.append("\n  ► Como obter:\n");
        if (tesouro) {
            sb.append("    • Baús de estruturas (tesouros)\n");
            sb.append("    • Pescaria (livros encantados raros)\n");
            sb.append("    • Troca com Aldeões Bibliotecários\n");
        } else {
            sb.append("    • Mesa de Encantamento (requer Lápis-lazúli e XP)\n");
            sb.append("    • Bigorna (combinar livros encantados)\n");
            sb.append("    • Pescaria (livros encantados)\n");
            sb.append("    • Baús de estruturas\n");
            sb.append("    • Troca com Aldeões Bibliotecários\n");
        }

        sb.append("╚════════════════════════════════════════════════════════════╝\n");
        return sb.toString();
    }
}

