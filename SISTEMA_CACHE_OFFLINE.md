# 📦 Sistema de Cache Offline - Minecraft Wiki

## 🎯 Objetivo
Este sistema permite que a aplicação funcione completamente offline, salvando automaticamente todas as imagens (itens e crafting) para uso futuro sem conexão com a internet.

## 🚀 Funcionalidades

### 1. **Download Automático de Imagens**
- Ao iniciar o programa, todas as imagens são baixadas automaticamente em background
- Imagens são salvas em `cache/images/` para itens
- Imagens de crafting são salvas em `cache/images/crafting/`

### 2. **Modo Offline**
- Botão "🔌 Modo Offline" na tela inicial
- Quando ativado, não tenta baixar novas imagens (usa apenas cache)
- Útil para economizar dados ou trabalhar sem internet

### 3. **Cache Inteligente**
- Verifica se a imagem já existe antes de baixar
- Armazena em vários tamanhos (16px, 24px, 32px, 48px)
- Sistema de fallback com ícones coloridos caso imagem não esteja disponível

### 4. **Auto-Save ao Adicionar Itens**
Quando você adiciona um novo item no MinecraftWiki.java, o sistema automaticamente:
1. Baixa a imagem do item da wiki
2. Salva em cache local em múltiplos tamanhos
3. Baixa a imagem de crafting (se houver receita)
4. Atualiza os caminhos no objeto Item

## 📋 Como Usar

### Para Desenvolvedores - Adicionar Novo Item

```java
// 1. Criar o item normalmente
Item novaMachadoPedra = new Item("Machado de Pedra",
    "Ferramenta para cortar madeira",
    MinecraftEdition.BOTH, "Ferramenta");
novaMachadoPedra.adicionarIngrediente("3x Pedregulho");
novaMachadoPedra.adicionarIngrediente("2x Graveto");

// 2. Adicionar à lista
itens.add(novaMachadoPedra);

// 3. Salvar imagens automaticamente (OPCIONAL - já feito ao iniciar)
ImageManager.autoSaveItemImages(novaMachadoPedra);
```

### Mapeamento de Imagens no ImageManager

Para que a imagem seja encontrada, adicione o mapeamento:

```java
// No método getWikiImageName() do ImageManager.java
nameMap.put("STONE_AXE", "Invicon_Stone_Axe.png");
```

### Para Usuários

1. **Primeira Execução (Online)**
   - Execute o programa com internet
   - Aguarde o download das imagens (mensagem no console)
   - Todas as imagens serão salvas localmente

2. **Uso Offline**
   - Clique no botão "🔌 Modo Offline" na tela inicial
   - Agora você pode usar o programa sem internet
   - Todas as imagens virão do cache local

## 📊 Estatísticas do Cache

Na tela inicial, você verá:
```
📊 Cache: X imagens de itens, Y imagens de crafting
```

Isso mostra quantas imagens estão salvas localmente.

## 🔧 Estrutura de Diretórios

```
untitled/
├── cache/
│   └── images/
│       ├── Invicon_Diamond.png
│       ├── Invicon_Iron_Ingot.png
│       ├── Invicon_Stone.png
│       └── crafting/
│           ├── Crafting_Diamond_Sword.png
│           └── Crafting_Iron_Pickaxe.png
```

## ⚙️ Configuração Técnica

### ImageManager.java - Novos Métodos

1. **`preloadImages()`**
   - Baixa todas as imagens em background
   - Executa em thread separada para não travar a UI
   - Mostra progresso no console

2. **`downloadCraftingImage(String itemName)`**
   - Baixa imagem de crafting da wiki
   - Retorna caminho local ou null

3. **`getCraftingIcon(String itemName, int width, int height)`**
   - Obtém imagem de crafting do cache
   - Redimensiona conforme necessário

4. **`autoSaveItemImages(Item item)`**
   - Salva automaticamente todas as imagens de um item
   - Atualiza os campos imagemItem e imagemCrafting

5. **`setOfflineMode(boolean offline)`**
   - Ativa/desativa modo offline
   - Impede downloads quando offline

6. **`getCacheStats()`**
   - Retorna estatísticas do cache
   - Mostra quantidade de imagens salvas

## 🎨 Imagens Mapeadas

### Menu (9 itens)
- ITEMS, ARMOR, ENCHANTMENTS, BREWING, CRAFTING, STATISTICS, API_TEST, ABOUT, EXIT

### Ferramentas (4 itens)
- PICKAXE, AXE, SHOVEL, HOE

### Armas (5 itens)
- SWORD, BOW, CROSSBOW, TRIDENT, ATTACK

### Armaduras (6 itens)
- HELMET, CHESTPLATE, LEGGINGS, BOOTS, SHIELD, DEFENSE

### Recursos (7 itens)
- DIAMOND, EMERALD, GOLD_INGOT, IRON_INGOT, NETHERITE, COAL, REDSTONE

### Poções (4 itens)
- POTION_HEALING, POTION_STRENGTH, POTION_SPEED, BREWING_STAND

### Encantamentos (3 itens)
- ENCHANTED_BOOK, ENCHANTING_TABLE, ANVIL

### Crafting (4 itens)
- CRAFTING_TABLE, FURNACE, BLAST_FURNACE, SMITHING_TABLE

### Blocos (13 itens)
- STONE, COBBLESTONE, STONE_BRICKS, OAK_PLANKS, GLASS
- DIRT, GRASS_BLOCK, SAND, GRAVEL
- WHITE_WOOL, TERRACOTTA, WHITE_CONCRETE
- CHEST, BARREL

### Outros (2 itens)
- BOOK, RECIPE

**Total: 57+ itens mapeados em 4 tamanhos = 228+ imagens**

## 🌐 URLs da Wiki

Base URL: `https://minecraft.wiki/images/`

Formato de itens: `Invicon_[ItemName].png`
- Exemplo: `Invicon_Diamond_Sword.png`

Formato de crafting: `Crafting_[ItemName].png`
- Exemplo: `Crafting_Diamond_Sword.png`

## 💡 Dicas

1. **Primeira execução**: Deixe o programa aberto por 30-60 segundos para baixar todas as imagens

2. **Modo offline**: Ative antes de viagens ou quando estiver sem internet

3. **Limpar cache**: Delete a pasta `cache/images/` para forçar novo download

4. **Verificar cache**: Console mostra mensagens como "📥 Imagem baixada: ..."

5. **Fallback**: Se uma imagem não existir na wiki, aparecerá um ícone colorido com emoji

## 🐛 Troubleshooting

**Problema**: Imagens não aparecem
- **Solução**: Verifique se a pasta `cache/images/` foi criada
- Execute com internet na primeira vez
- Verifique console para erros de download

**Problema**: Modo offline não funciona
- **Solução**: Execute primeiro em modo online para criar cache
- Verifique se há imagens na pasta cache

**Problema**: Algumas imagens não baixam
- **Solução**: Algumas URLs podem não existir na wiki
- Sistema usa fallback automático com ícones coloridos
- Normal para itens especiais como "Enchanted Book"

## 📝 Log de Console

Durante execução, você verá:
```
📦 Iniciando pré-carregamento de imagens para modo offline...
📥 Imagem baixada: Invicon_Diamond.png
📥 Imagem de crafting baixada: Crafting_Diamond_Sword.png
✅ Pré-carregamento concluído: 52/57 imagens disponíveis
💾 Cache local criado em: cache/images
🌐 Modo offline disponível!
```

## 🎓 Para Novos Desenvolvedores

Para adicionar suporte a um novo item:

1. Adicione o item em `MinecraftWiki.java`:
```java
Item novoItem = new Item("Nome", "Descrição", MinecraftEdition.BOTH, "Categoria");
itens.add(novoItem);
```

2. Mapeie a imagem em `ImageManager.java`:
```java
nameMap.put("NOME_ITEM", "Invicon_Nome_Item.png");
```

3. Use no código:
```java
ImageIcon icon = ImageManager.getItemIcon("NOME_ITEM", 48);
```

4. (Opcional) Salve manualmente:
```java
ImageManager.autoSaveItemImages(novoItem);
```

Pronto! O sistema cuida do resto automaticamente! 🎉

## 🔒 Licença e Créditos

- Imagens: © Mojang Studios (Minecraft Wiki)
- Sistema de Cache: Desenvolvido para Minecraft Wiki App
- Uso educacional e pessoal

---

**Versão**: 2.0 
**Última atualização**: Dezembro 2024
