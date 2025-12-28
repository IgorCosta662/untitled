package org.example;

import java.util.ArrayList;
import java.util.List;

public class Pocao {
    private final String nome;
    private final String efeito;
    private final String duracao;
    private final MinecraftEdition edicao;
    private final List<String> ingredientes;
    private String basePotion;

    public Pocao(String nome, String efeito, String duracao, MinecraftEdition edicao) {
        this.nome = nome;
        this.efeito = efeito;
        this.duracao = duracao;
        this.edicao = edicao;
        this.ingredientes = new ArrayList<>();
    }

    public void adicionarIngrediente(String ingrediente) {
        ingredientes.add(ingrediente);
    }

    public void setBasePotion(String base) {
        this.basePotion = base;
    }

    public String getNome() {
        return nome;
    }

    public String getEfeito() {
        return efeito;
    }

    public MinecraftEdition getEdicao() {
        return edicao;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n╔════════════════════════════════════════════════════════════╗\n");
        sb.append(String.format("  POÇÃO: %s\n", nome));
        sb.append("╠════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("  Edição: %s\n", edicao.getDisplayName()));
        sb.append(String.format("  Efeito: %s\n", efeito));
        sb.append(String.format("  Duração: %s\n", duracao));

        if (basePotion != null) {
            sb.append(String.format("\n  ► Base: %s\n", basePotion));
        }

        if (!ingredientes.isEmpty()) {
            sb.append("\n  ► Ingredientes para preparar:\n");
            for (String ing : ingredientes) {
                sb.append(String.format("    • %s\n", ing));
            }
        }

        sb.append("\n  ► Como preparar:\n");
        sb.append("    1. Coloque garrafas de água no suporte de poções\n");
        sb.append("    2. Adicione Verruga do Nether (para Poção Estranha)\n");
        sb.append("    3. Adicione o ingrediente principal\n");
        sb.append("    4. [Opcional] Pó de Pedra Luminosa (aumenta potência)\n");
        sb.append("    5. [Opcional] Pó de Redstone (aumenta duração)\n");

        sb.append("╚════════════════════════════════════════════════════════════╝\n");
        return sb.toString();
    }
}

