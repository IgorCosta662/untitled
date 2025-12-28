# 🖼️ Guia de Imagens - Minecraft Wiki

## 📁 Estrutura de Pastas

As imagens devem ser organizadas da seguinte forma:

```
src/main/resources/images/
├── items/          # Imagens dos itens (PNG, 64x64 recomendado)
│   ├── diamante.png
│   ├── picareta_ferro.png
│   ├── espada_diamante.png
│   └── ...
└── crafting/       # Imagens das receitas de crafting (PNG, 150x150 recomendado)
    ├── picareta_ferro_craft.png
    ├── espada_diamante_craft.png
    └── ...
```

## 🎨 Formatos Recomendados

### Imagens de Itens
- **Formato**: PNG com transparência
- **Tamanho**: 64x64 pixels (será redimensionado automaticamente)
- **Fundo**: Transparente
- **Localização**: `src/main/resources/images/items/`

### Imagens de Crafting
- **Formato**: PNG
- **Tamanho**: 150x150 pixels ou múltiplos de 50 (será redimensionado)
- **Fundo**: Pode ter fundo da mesa de crafting
- **Localização**: `src/main/resources/images/crafting/`

## 💡 Como Adicionar Imagens aos Itens

### Exemplo de código no MinecraftWiki.java:

```java
// Criar o item
Item picareta = new Item(
    "Picareta de Ferro",
    "Ferramenta para minerar blocos rapidamente",
    MinecraftEdition.AMBAS,
    "Ferramenta"
);

// Adicionar ingredientes
picareta.adicionarIngrediente("3x Lingote de Ferro");
picareta.adicionarIngrediente("2x Graveto");

// Definir imagem do item
picareta.setImagemItem("src/main/resources/images/items/picareta_ferro.png");

// Definir imagem da receita de crafting
picareta.setImagemCrafting("src/main/resources/images/crafting/picareta_ferro_craft.png");

// Adicionar ao banco de dados
adicionarItem(picareta);
```

## 📥 Onde Obter as Imagens

1. **Minecraft Wiki Oficial**: https://minecraft.wiki/
   - Imagens oficiais dos itens e blocos
   - Licença: Fair Use (uso educacional)

2. **Minecraft Resource Packs**:
   - Extrair texturas do jogo (pasta `assets/minecraft/textures`)
   - Usar apenas para uso pessoal

3. **Criar Suas Próprias**:
   - Desenhar sprites pixel art 16x16 ou 32x32
   - Ampliar para 64x64 mantendo estilo pixelado

## 🔧 Testando as Imagens

1. Coloque as imagens nas pastas corretas
2. Configure os caminhos no código usando `setImagemItem()` e `setImagemCrafting()`
3. Execute a aplicação
4. Navegue até a aba "Itens" e busque o item

## ⚠️ Resolução de Problemas

### Imagem não aparece:
- ✅ Verifique se o caminho está correto
- ✅ Confirme que o arquivo existe na pasta
- ✅ Verifique a extensão do arquivo (.png, .jpg)
- ✅ Veja o console para mensagens de erro

### Imagem distorcida:
- Ajuste o tamanho recomendado (64x64 para itens, 150x150 para crafting)
- Use imagens quadradas

### Imagem com qualidade ruim:
- Use PNG ao invés de JPG
- Aumente a resolução da imagem original
- Mantenha proporção quadrada
