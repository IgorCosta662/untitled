# Minecraft Wiki - Instruções para Agentes de IA

## Visão Geral da Arquitetura

Esta é uma aplicação Swing desktop que funciona como uma enciclopédia interativa do Minecraft. A arquitetura segue um padrão de **repositório centralizado** com **painéis modulares consolidados**.

### Componentes Principais

- **[MinecraftWiki.java](src/main/java/org/example/MinecraftWiki.java)**: Repositório central com 3500+ linhas contendo todos os dados do jogo (itens, poções, encantamentos, armaduras). Funciona como "banco de dados em código".
- **[MinecraftWikiGUI.java](src/main/java/org/example/gui/MinecraftWikiGUI.java)**: Frame principal usando `CardLayout` para navegação entre painéis. Gerencia histórico de navegação com `Stack<String>`.
- **[AllItemsPanel.java](src/main/java/org/example/gui/AllItemsPanel.java)**: Painel consolidado com 8 abas (Itens Gerais, Encantamentos, Poções, Efeitos, Crafting, Fornalha, Ferraria, Redstone).
- **[Main.java](src/main/java/org/example/Main.java)**: Entry point com lock de instância única usando `ServerSocket(9999)` para prevenir múltiplas execuções.
- **[ImageManager.java](src/main/java/org/example/gui/ImageManager.java)**: Sistema de cache de imagens com fallback automático e download assíncrono da Minecraft Wiki.

### Modelos de Dados

Todos os modelos estão em `org.example`:
- **Item**: Categoria, ingredientes, padrão de crafting (ASCII art), caminhos de imagem
- **Pocao**: Base potion + efeitos + duração + amplificação
- **Encantamento**: Níveis máximos + incompatibilidades + tipo de item aplicável
- **Armadura**: Defesa + durabilidade + material
- **MoldeFerraria**: Templates para smithing table (Netherite upgrades)

## Convenções do Projeto

### Sistema de Imagens

**Dois sistemas coexistem:**
1. **Cache local** em `cache/images/` - funciona offline após primeira execução
2. **Fallback dinâmico** - cria ícones coloridos com emoji quando imagem não está disponível

**Mapeamento de nomes:**
- Imagens da wiki seguem padrão `Invicon_<Nome>.png` (ex: `Invicon_Diamond_Sword.png`)
- Crafting recipes: `Crafting_<Nome>.png` 
- Adicionar novos itens requer mapeamento em `ImageManager.getWikiImageName()`

```java
// Exemplo de mapeamento novo item
nameMap.put("NETHERITE_HOE", "Invicon_Netherite_Hoe.png");
```

### Padrão de Painéis

Todos os painéis em `gui/` seguem estrutura:
```java
public class NomePanelPanel extends JPanel {
    private void setupUI() { /* Layout e componentes */ }
    // Sem lógica de negócio - apenas apresentação
}
```

**Menu Principal Simplificado**: HomePanel contém apenas 6 botões (Itens, Sistemas, Estatísticas, Mundo, Sobre, Sair)

**Painéis consolidados com abas internas**: 
- **AllItemsPanel**: Itens Gerais + Encantamentos + Poções + Efeitos + Crafting + Fornalha + Ferraria + Redstone
- **WorldPanel**: Criaturas + Biomas + Estruturas (com abas)
- **SystemsPanel**: Comandos + Comércio + Tutoriais (com abas)

### Adicionando Novos Itens

Processo em [MinecraftWiki.java](src/main/java/org/example/MinecraftWiki.java):

```java
Item novoItem = new Item("Nome do Item", 
    "Descrição detalhada",
    MinecraftEdition.BOTH,  // ou JAVA_ONLY / BEDROCK_ONLY
    "Categoria");  // Ferramenta, Arma, Bloco, etc.
novoItem.adicionarIngrediente("3x Diamante");
novoItem.adicionarIngrediente("2x Graveto");
novoItem.setPadraoCrafting("pattern ASCII art"); // Opcional
itens.add(novoItem);
```

**IMPORTANTE**: Adicionar mapeamento de imagem no `ImageManager` antes de criar o item.

## Workflows de Desenvolvimento

### Build & Execução

**Recomendado (IntelliJ):**
1. Run `Main.main()` diretamente - configuração automática
2. Aguardar 30-60s na primeira execução (download de imagens)

**Maven (se instalado):**
```bash
mvn clean compile exec:java -Dexec.mainClass="org.example.Main"
```

**Linha de comando (fallback):**
```bash
javac -encoding UTF-8 -d target/classes src/main/java/org/example/*.java src/main/java/org/example/gui/*.java
java -cp target/classes org.example.Main
```

### Sistema de Cache Offline

- **Primeira execução**: Thread de background baixa 228+ imagens (~2 min)
- **Próximas execuções**: Modo offline automático se cache completo
- **Botão "Modo Offline"** em HomePanel controla `ImageManager.offlineMode`
- Ver estatísticas: [SISTEMA_CACHE_OFFLINE.md](SISTEMA_CACHE_OFFLINE.md)

### API Layer (Atualmente Desabilitada)

Arquivos `.java.disabled` em `api/` são protótipos para integração futura:
- **MinecraftWikiAPI**: Cliente OkHttp para MediaWiki API
- **CacheManager**: SQLite para persistência de dados JSON
- **WikiDataService**: Camada inteligente API + Cache

**Não são usados atualmente** - dados são todos hardcoded em MinecraftWiki.java.

## Patterns Específicos do Projeto

### Cores Temáticas

Use constantes em `MinecraftWikiGUI` para consistência visual:
```java
MINECRAFT_GREEN       // #55FF55 - Positivo/Sucesso
MINECRAFT_DARK_GREEN  // #00AA00 - Itens raros
MINECRAFT_GOLD        // #FFAA00 - Legendário
MINECRAFT_BLUE        // #5555FF - Encantamentos
MINECRAFT_PURPLE      // #AA00AA - Poções
```

### Navegação com Histórico

```java
// Adicionar ao histórico antes de mudar de painel
navigationHistory.push(currentPanel);
cardLayout.show(mainPanel, "NOVA_TELA");
currentPanel = "NOVA_TELA";

// Voltar (Alt+Backspace)
if (!navigationHistory.isEmpty()) {
    String previous = navigationHistory.pop();
    cardLayout.show(mainPanel, previous);
    currentPanel = previous;
}
```

### Prevenção de Múltiplas Instâncias

`Main.java` usa `ServerSocket(9999)` - se porta ocupada, mostra JOptionPane e sai. **Não remover** sem substituir por outro lock.

## Dependências Maven

- **gson 2.10.1**: Parsing JSON (não usado ativamente)
- **jackson-databind 2.16.1**: JSON alternativo (futuro)
- **sqlite-jdbc 3.45.0**: Cache database (API desabilitada)
- **okhttp 4.12.0**: Cliente HTTP (API desabilitada)
- **jsoup 1.17.2**: HTML parsing MediaWiki (futuro)
- **guava 33.0.0**: Cache em memória (futuro)

**Atualmente**: Projeto roda apenas com JDK 21 stdlib + Swing. Dependências preparadas para fase 2.

## Documentação Adicional

- [API_DOCUMENTATION.md](API_DOCUMENTATION.md): Integração futura com Minecraft Wiki API
- [SISTEMA_CACHE_OFFLINE.md](SISTEMA_CACHE_OFFLINE.md): Detalhes do sistema de imagens
- [MAVEN_INSTALL.md](MAVEN_INSTALL.md): Guia de instalação Maven (opcional)
- [README.md](README.md): Documentação completa para usuários finais

## Considerações Importantes

1. **Encoding**: Sempre usar UTF-8 (contém caracteres Unicode brasileiros e ASCII art)
2. **Java Version**: Requer Java 21+ (text blocks e pattern matching)
3. **Thread Safety**: `ImageManager` usa `HashMap` sem sync - só escrever na main thread
4. **Performance**: MinecraftWiki carrega tudo em memória (~5MB) - instantâneo após init
5. **Paths**: Usar forward slashes `/` mesmo no Windows - `File` normaliza automaticamente
