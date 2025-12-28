# Minecraft Wiki - Edição Completa com Interface Gráfica

## ✅ Nova Versão 2.0 - Interface Gráfica Completa!

O projeto foi completamente renovado com uma interface gráfica moderna estilo Minecraft!

## 🎮 NOVIDADES DA VERSÃO 2.0

### Interface Gráfica Completa
- ✨ **Design Moderno**: Interface estilo Minecraft com cores temáticas
- 🎨 **Painéis Interativos**: Navegação intuitiva entre seções
- 🔍 **Busca Avançada**: Sistema de pesquisa em tempo real
- 📊 **Visualização Rica**: Cards coloridos para itens, poções e encantamentos

### Novas Funcionalidades
- 🔨 **Simulador de Crafting**: Mesa de crafting 3x3 interativa
- 📖 **Guias Detalhados**: Pop-ups com guias completos de preparação
- 📊 **Dashboard de Estatísticas**: Visualização gráfica dos dados
- 🎯 **Filtros por Categoria**: Organize e encontre itens rapidamente

## 🚀 Como Executar

### Opção 1: Interface Gráfica (RECOMENDADO)
1. Abra o arquivo `MinecraftWikiGUI.java` em `src/main/java/org/example/gui/`
2. Pressione **Ctrl+F5** ou clique com botão direito → Run
3. A aplicação abrirá em uma janela moderna!

### Opção 2: Modo Terminal (Versão Clássica)
1. Abra o arquivo `Main.java`
2. Pressione **Ctrl+F5**
3. Use o terminal interativo

### Opção 3: Maven (se instalado)
```bash
mvn clean compile exec:java
```

## 🎯 Funcionalidades

### 📦 Itens e Receitas
- Busca por nome ou categoria
- Visualização de receitas de crafting
- Padrões visuais de crafting 3x3
- Filtros por tipo: Ferramenta, Arma, Armadura, etc.

### ⚗️ Poções e Efeitos
- Catálogo completo de poções
- Ingredientes e processo de preparação
- Guia interativo de brewing
- Informações sobre modificadores

### ✨ Encantamentos
- Lista completa de encantamentos
- Níveis máximos e aplicações
- Incompatibilidades destacadas
- Guia de como encantar itens

### 🔨 Simulador de Crafting (NOVO!)
- Mesa de crafting 3x3 interativa
- Clique para adicionar materiais
- Veja o resultado em tempo real
- Teste receitas sem consumir recursos

### 📊 Estatísticas
- Visualização do banco de dados
- Contadores por categoria
- Informações do sistema

## 📋 Requisitos
- Java 21 ou superior
- IntelliJ IDEA (recomendado)
- Maven (opcional)
- 1200x800 pixels de resolução mínima

## 🎨 Interface Gráfica

### Telas Disponíveis:
1. **Home**: Menu principal com acesso rápido
2. **Itens**: Busca e visualização de itens
3. **Poções**: Catálogo de poções com guia
4. **Encantamentos**: Lista de encantamentos
5. **Simulador**: Mesa de crafting interativa
6. **Estatísticas**: Dashboard com métricas
7. **Sobre**: Informações do projeto

### Cores Temáticas:
- 🟢 Verde Minecraft: Sucesso e confirmações
- 🟤 Marrom: Itens e crafting
- 🟣 Roxo: Poções e magia
- 🔵 Azul: Encantamentos
- 🟡 Dourado: Destaques importantes

## 🔧 Estrutura do Projeto

```
src/
├── main/
│   └── java/
│       └── org/example/
│           ├── Main.java                    # Versão terminal
│           ├── MinecraftWiki.java           # Banco de dados
│           ├── Item.java                    # Modelo Item
│           ├── Pocao.java                   # Modelo Poção
│           ├── Encantamento.java            # Modelo Encantamento
│           ├── MinecraftEdition.java        # Enum edições
│           └── gui/                         # 🆕 Interface Gráfica
│               ├── MinecraftWikiGUI.java    # Janela principal
│               ├── HomePanel.java           # Tela inicial
│               ├── ItemsPanel.java          # Painel de itens
│               ├── PotionsPanel.java        # Painel de poções
│               ├── EnchantmentsPanel.java   # Painel de encantamentos
│               ├── CraftingSimulatorPanel.java  # Simulador
│               ├── StatisticsPanel.java     # Estatísticas
│               └── AboutPanel.java          # Sobre
```

## 💡 Dicas de Uso

### Interface Gráfica:
- **Busca Rápida**: Digite e pressione Enter
- **Filtros**: Use os dropdowns para refinar resultados
- **Guias**: Clique nos botões de ajuda para ver guias completos
- **Simulador**: Experimente diferentes combinações de crafting
- **Navegação**: Use os botões "Voltar" ou clique nos cards do menu

### Atalhos:
- **ESC**: Fechar diálogos
- **Enter**: Confirmar buscas
- **Scroll**: Navegue pelas listas

## 🌍 Edições Suportadas

### Java Edition ☕
- Todos os recursos padrão
- Encantamentos exclusivos (Lâmina Afiada)
- Poções especiais (Sorte)

### Bedrock Edition 🪨
- Compatibilidade cross-platform
- Mecânicas equivalentes
- Interface adaptada

## 📝 Notas Importantes

- ✓ Interface responsiva e fluida
- ✓ Suporte a alta resolução
- ✓ Tema escuro para conforto visual
- ✓ Busca instantânea
- ✓ Sem necessidade de conexão com internet

## 🐛 Solução de Problemas

**Interface não abre?**
- Verifique se tem Java 21+ instalado
- Recompile o projeto no IntelliJ
- Tente executar Main.java (versão terminal)

**Cores estranhas?**
- O IntelliJ pode estar aplicando tema próprio
- A aplicação funciona em qualquer Look and Feel

**Lento?**
- Feche outros programas
- Aumente a memória da JVM se necessário

## 🎯 Próximas Atualizações

- [ ] Sistema de favoritos
- [ ] Exportar receitas para PDF
- [ ] Modo claro/escuro
- [ ] Mais receitas no simulador
- [ ] Integração com mods populares
- [ ] Suporte a múltiplos idiomas

## 👨‍💻 Desenvolvimento

**Tecnologias:**
- Java 21
- Swing (GUI)
- Maven
- Nimbus Look and Feel

**Versão:** 2.0  
**Data:** 2025  
**Licença:** Educacional

---

💚 Desenvolvido com ☕ Java e paixão por Minecraft!  
⛏️ Bons crafts e aventuras!



