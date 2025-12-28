# 🛡️ Guia de Imagens para Armaduras

## 📁 Estrutura de Pastas para Armaduras

```
src/main/resources/images/
├── armaduras/      # Imagens das peças de armadura
│   ├── capacete_couro.png
│   ├── peitoral_couro.png
│   ├── calcas_couro.png
│   ├── botas_couro.png
│   ├── capacete_ferro.png
│   ├── peitoral_ferro.png
│   └── ...
└── moldes/         # Imagens dos moldes de ferraria
    ├── molde_netherite.png
    ├── molde_coast.png
    ├── molde_dune.png
    └── ...
```

## 💡 Como Adicionar Imagens às Armaduras

### Exemplo de código no MinecraftWiki.java:

```java
// Dentro do método carregarArmaduras():

// Couro
Armadura capaceteCouro = new Armadura("Capacete de Couro", "Capacete", "Couro",
    "Armadura básica tingível", 1, 55, MinecraftEdition.BOTH);
capaceteCouro.adicionarIngrediente("5x Couro");
capaceteCouro.setImagemPath("src/main/resources/images/armaduras/capacete_couro.png");
armaduras.add(capaceteCouro);

// Ferro
Armadura peitoralFerro = new Armadura("Peitoral de Ferro", "Peitoral", "Ferro",
    "Excelente proteção do tronco", 6, 240, MinecraftEdition.BOTH);
peitoralFerro.adicionarIngrediente("8x Lingote de Ferro");
peitoralFerro.setImagemPath("src/main/resources/images/armaduras/peitoral_ferro.png");
armaduras.add(peitoralFerro);

// Diamante
Armadura capaceteDiamante = new Armadura("Capacete de Diamante", "Capacete", "Diamante",
    "Proteção máxima para a cabeça", 3, 363, MinecraftEdition.BOTH);
capaceteDiamante.adicionarIngrediente("5x Diamante");
capaceteDiamante.setImagemPath("src/main/resources/images/armaduras/capacete_diamante.png");
armaduras.add(capaceteDiamante);
```

## 🎨 Formatos Recomendados

### Imagens de Armaduras
- **Formato**: PNG com transparência
- **Tamanho**: 64x64 pixels
- **Fundo**: Transparente
- **Nomenclatura**: `[tipo]_[material].png`
  - Exemplos: `capacete_ferro.png`, `peitoral_diamante.png`, `botas_ouro.png`

### Imagens de Moldes
- **Formato**: PNG
- **Tamanho**: 32x32 ou 64x64 pixels
- **Nomenclatura**: `molde_[nome].png`
  - Exemplos: `molde_netherite.png`, `molde_coast.png`

## 📝 Lista de Imagens Necessárias

### Armaduras de Couro (4 peças):
- ✅ capacete_couro.png
- ✅ peitoral_couro.png
- ✅ calcas_couro.png
- ✅ botas_couro.png

### Armaduras de Cota de Malha (4 peças):
- ✅ capacete_malha.png
- ✅ peitoral_malha.png
- ✅ calcas_malha.png
- ✅ botas_malha.png

### Armaduras de Ferro (4 peças):
- ✅ capacete_ferro.png
- ✅ peitoral_ferro.png
- ✅ calcas_ferro.png
- ✅ botas_ferro.png

### Armaduras de Ouro (4 peças):
- ✅ capacete_ouro.png
- ✅ peitoral_ouro.png
- ✅ calcas_ouro.png
- ✅ botas_ouro.png

### Armaduras de Diamante (4 peças):
- ✅ capacete_diamante.png
- ✅ peitoral_diamante.png
- ✅ calcas_diamante.png
- ✅ botas_diamante.png

### Armaduras de Netherite (4 peças):
- ✅ capacete_netherite.png
- ✅ peitoral_netherite.png
- ✅ calcas_netherite.png
- ✅ botas_netherite.png

### Armadura de Tartaruga (1 peça):
- ✅ capacete_tartaruga.png

## 🔧 Exemplo Completo de Configuração

```java
private void carregarArmaduras() {
    // ==================== ARMADURAS DE FERRO ====================
    
    Armadura capaceteFerro = new Armadura("Capacete de Ferro", "Capacete", "Ferro",
        "Proteção sólida e confiável", 2, 165, MinecraftEdition.BOTH);
    capaceteFerro.adicionarIngrediente("5x Lingote de Ferro");
    capaceteFerro.setImagemPath("src/main/resources/images/armaduras/capacete_ferro.png");
    armaduras.add(capaceteFerro);

    Armadura peitoralFerro = new Armadura("Peitoral de Ferro", "Peitoral", "Ferro",
        "Excelente proteção do tronco", 6, 240, MinecraftEdition.BOTH);
    peitoralFerro.adicionarIngrediente("8x Lingote de Ferro");
    peitoralFerro.setImagemPath("src/main/resources/images/armaduras/peitoral_ferro.png");
    armaduras.add(peitoralFerro);

    Armadura calcasFerro = new Armadura("Calças de Ferro", "Calças", "Ferro",
        "Proteção forte para as pernas", 5, 225, MinecraftEdition.BOTH);
    calcasFerro.adicionarIngrediente("7x Lingote de Ferro");
    calcasFerro.setImagemPath("src/main/resources/images/armaduras/calcas_ferro.png");
    armaduras.add(calcasFerro);

    Armadura botasFerro = new Armadura("Botas de Ferro", "Botas", "Ferro",
        "Botas duráveis", 2, 195, MinecraftEdition.BOTH);
    botasFerro.adicionarIngrediente("4x Lingote de Ferro");
    botasFerro.setImagemPath("src/main/resources/images/armaduras/botas_ferro.png");
    armaduras.add(botasFerro);
}
```

## 📥 Onde Obter as Imagens

1. **Minecraft Wiki Oficial**: https://minecraft.wiki/
   - Busque por "Armor" ou "Armadura"
   - Baixe as sprites de cada peça
   
2. **Resource Packs do Minecraft**:
   - Pasta: `assets/minecraft/textures/items/`
   - Arquivos: `leather_helmet.png`, `iron_chestplate.png`, etc.

3. **Moldes de Ferraria**:
   - Busque por "Smithing Template" na wiki
   - Baixe as texturas dos moldes

## ✨ Nova Funcionalidade

Quando você clicar em "Armadura" na categoria Combate, agora verá:

- 📋 Armaduras organizadas por material (Couro, Malha, Ferro, Ouro, Diamante, Netherite, Tartaruga)
- 🖼️ Cada peça com sua imagem (se configurada)
- 🛡️ Defesa e durabilidade de cada peça
- 📖 Botão "Ver Receita" para ver ingredientes
- ⬆️ Seção especial de upgrade para Netherite
- ✨ Botão "Ver Moldes de Ferraria" mostrando todos os 19 moldes decorativos
- 📍 Informações completas de localização e uso de cada molde

## ⚙️ Testando

1. Coloque as imagens nas pastas corretas
2. Configure os caminhos usando `setImagemPath()` no MinecraftWiki.java
3. Execute a aplicação
4. Navegue: Itens → Combate → Armadura
5. Veja as armaduras organizadas por material!
