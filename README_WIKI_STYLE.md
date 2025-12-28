# 🌐 MINECRAFT WIKI - VERSÃO SITE OFICIAL

## ✨ NOVA VERSÃO 3.0 - ESTILO MINECRAFT.WIKI!

O programa foi completamente redesenhado para se parecer com o site oficial da Minecraft Wiki (https://pt.minecraft.wiki/)!

---

## 🎨 VISUAL RENOVADO

### Layout Similar ao Site Oficial:

```
╔════════════════════════════════════════════════════════╗
║  🎮 MINECRAFT WIKI        [  🔍 Buscar...  ] 🏠 ❓    ║
╠══════════╦═════════════════════════════════════════════╣
║          ║  PÁGINA INICIAL                            ║
║ 📚 NAV   ║  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━  ║
║  • Início║  Bem-vindo à Minecraft Wiki                ║
║  • Itens ║                                            ║
║  • Poções║  A enciclopédia completa sobre Minecraft   ║
║          ║                                            ║
║ 📂 CAT   ║  📚 Conteúdo:                             ║
║  ⛏️ Ferra║    • 15 Itens com receitas                ║
║  ⚔️ Armas║    • 14 Poções com efeitos                 ║
║  🛡️ Armad║    • 39 Encantamentos                      ║
║          ║                                            ║
║ ⭐ FAV   ║  🔗 Links relacionados...                  ║
║  (vazio) ║                                            ║
║          ║  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━  ║
║ 📖 HIST  ║  © 2025 Minecraft Wiki                     ║
║  Página  ║                                            ║
╚══════════╩═════════════════════════════════════════════╝
```

---

## 🚀 COMO EXECUTAR

### Opção 1 - IntelliJ (Mais Fácil):
```
1. Abra: src/main/java/org/example/gui/wiki/WikiMainWindow.java
2. Pressione: Ctrl + F5
3. Pronto! 🎉
```

### Opção 2 - Terminal:
```powershell
cd C:\Users\medei\IdeaProjects\untitled
javac -d target/classes -sourcepath src/main/java src/main/java/org/example/gui/wiki/*.java
java -cp target/classes org.example.gui.wiki.WikiMainWindow
```

---

## 🌟 FUNCIONALIDADES EXCLUSIVAS

### 1. **Barra de Navegação Superior** 🔝
- Logo e título clicável
- Barra de busca centralizada
- Botões: Início | Aleatório | Ajuda
- Design azul estilo site oficial

### 2. **Sidebar Lateral** 📖
Similar ao site oficial com:
- **Navegação Principal**
  - 🏠 Página Inicial
  - 📦 Todos os Itens
  - ⚗️ Todas as Poções
  - ✨ Todos os Encantamentos
  - 🔨 Simulador
  - 📊 Estatísticas

- **Categorias**
  - ⛏️ Ferramentas
  - ⚔️ Armas
  - 🛡️ Armaduras
  - 🍖 Alimentos
  - 🧱 Blocos
  - ⚡ Redstone
  - 🚂 Transporte
  - 💎 Especiais

- **Favoritos** ⭐
  - Adicione páginas aos favoritos
  - Acesso rápido
  - Clique na estrela na página

- **Histórico** 📖
  - Últimas 10 páginas visitadas
  - Um clique para revisitar

### 3. **Área de Conteúdo Principal** 📄
Páginas formatadas estilo Wiki:
- Títulos grandes e legíveis
- Infoboxes com dados importantes
- Links azuis clicáveis
- Seções organizadas
- Código formatado (receitas)
- Listas com bullets
- Páginas relacionadas no rodapé

### 4. **Sistema de Busca Inteligente** 🔍
- Busca em tempo real
- Resultados categorizados
- Sugestões de páginas
- Busca em itens, poções e encantamentos

### 5. **Botão Página Aleatória** 🎲
- Descubra conteúdo novo
- Um clique para explorar
- Funciona como no site oficial

---

## 📋 CARACTERÍSTICAS DO SITE OFICIAL IMPLEMENTADAS

✅ **Layout com Sidebar** - Igual ao site  
✅ **Barra de Busca no Topo** - Centralizada  
✅ **Navegação por Categorias** - Sidebar esquerda  
✅ **Infoboxes** - Dados organizados  
✅ **Links Clicáveis** - Azuis e sublinhados  
✅ **Histórico de Navegação** - Últimas páginas  
✅ **Sistema de Favoritos** - Estrela nas páginas  
✅ **Página Inicial** - Boas-vindas e overview  
✅ **Páginas de Categoria** - Listagens organizadas  
✅ **Resultados de Busca** - Categorizados  
✅ **Footer com Créditos** - Em todas as páginas  
✅ **Cores Tema Wiki** - Azul, branco, cinza  
✅ **Tipografia Legível** - SansSerif padrão  

---

## 🎨 PALETA DE CORES

Baseada no site oficial:

```
Background Geral:    #F8F9FA (cinza muito claro)
Sidebar:             #FFFFFF (branco)
Conteúdo:            #FFFFFF (branco)
Header:              #336 6CC (azul)
Links:               #0066CC (azul link)
Links Hover:         #003399 (azul escuro)
Texto:               #202122 (cinza escuro)
Minecraft Green:     #43B02A (verde Minecraft)
Bordas:              #DCDCDC (cinza claro)
```

---

## 📁 ARQUIVOS CRIADOS

```
src/main/java/org/example/gui/wiki/
├── WikiMainWindow.java          # Janela principal
├── WikiNavigationBar.java       # Barra superior
├── WikiSidebar.java             # Sidebar esquerda
└── WikiContentPanel.java        # Área de conteúdo
```

**Total:** ~1.200 linhas de código novo!

---

## 🆚 COMPARAÇÃO DE VERSÕES

### Versão 1.0 (Terminal):
- ❌ Interface texto
- ❌ Navegação numérica
- ❌ Sem visual

### Versão 2.0 (GUI Básica):
- ✅ Interface gráfica
- ✅ Botões coloridos
- ❌ Layout próprio

### Versão 3.0 (Estilo Wiki): 🆕
- ✅ Layout estilo site oficial
- ✅ Sidebar com navegação
- ✅ Barra de busca no topo
- ✅ Links clicáveis
- ✅ Histórico e favoritos
- ✅ Páginas formatadas
- ✅ Cores tema Wiki
- ✅ Infoboxes
- ✅ Página aleatória

---

## 💡 COMO USAR

### Navegação:
1. **Buscar:** Digite na barra superior e pressione Enter
2. **Categorias:** Clique nos links da sidebar
3. **Links:** Clique nos textos azuis sublinhados
4. **Favoritos:** Clique na estrela ⭐ na página
5. **Histórico:** Acesse na sidebar
6. **Aleatório:** Botão no topo para descobrir

### Dicas:
- 🏠 Clique no título para voltar ao início
- 🔗 Links azuis levam a páginas relacionadas
- ⭐ Estrela vazia = adicionar favorito
- ⭐ Estrela preenchida = remover favorito
- 📖 Histórico guarda últimas 10 páginas

---

## 🎯 PÁGINAS DISPONÍVEIS

### Tipos de Página:
1. **Página Inicial** - Boas-vindas e overview
2. **Página de Item** - Detalhes completos do item
3. **Página de Poção** - Efeitos e preparação
4. **Página de Encantamento** - Como obter
5. **Página de Categoria** - Lista de itens da categoria
6. **Resultados de Busca** - Busca global
7. **Página Não Encontrada** - Erro 404

### Elementos em Cada Página:
- ✅ Título grande
- ✅ Infobox com dados
- ✅ Seções organizadas
- ✅ Links relacionados
- ✅ Footer padronizado
- ✅ Botão favoritar

---

## 🔧 CUSTOMIZAÇÃO

### Modificar Cores:
Edite as constantes em `WikiMainWindow.java`:
```java
public static final Color WIKI_BG = new Color(248, 249, 250);
public static final Color WIKI_HEADER = new Color(51, 102, 204);
// etc...
```

### Adicionar Categorias:
Edite `WikiSidebar.java` método `createCategoryLinks()`:
```java
panel.add(createLink("🎨 Nova Categoria", 
    () -> parent.showCategoryPage("Nova Categoria")));
```

### Customizar Páginas:
Edite `WikiContentPanel.java` para adicionar novos estilos:
```java
private void addCustomStyle(String text) {
    // Seu código aqui
}
```

---

## 📊 ESTATÍSTICAS

### Código:
- **4 arquivos** principais
- **~1.200 linhas** de código
- **20+ métodos** de formatação
- **8 cores** temáticas

### Funcionalidades:
- **7 tipos** de página
- **8 categorias** navegáveis
- **10 itens** no histórico
- **Favoritos** ilimitados
- **Busca** em 68 entradas

---

## 🎓 TECNOLOGIAS

### Stack:
- **Java 21** - Linguagem
- **Swing** - GUI framework
- **JTextPane** - Conteúdo formatado
- **StyledDocument** - Estilos de texto
- **JSplitPane** - Layout sidebar
- **System L&F** - Look nativo

### Componentes:
- JFrame (janela principal)
- JSplitPane (divisão sidebar/conteúdo)
- JTextPane (área de texto rica)
- JScrollPane (scroll suave)
- MouseListener (interatividade)
- StyledDocument (formatação)

---

## 🐛 SOLUÇÃO DE PROBLEMAS

### Janela não abre?
```
✓ Compile novamente
✓ Use Java 21+
✓ Verifique se não há outro processo rodando
```

### Links não funcionam?
```
✓ Clique diretamente no texto azul
✓ Aguarde o carregamento completo
```

### Sidebar não aparece?
```
✓ Redimensione a janela
✓ Arraste o divisor para a esquerda
```

---

## 🚀 PRÓXIMAS MELHORIAS

- [ ] Imagens dos itens
- [ ] Tabs nas páginas
- [ ] Edições (Java/Bedrock)
- [ ] Modo escuro
- [ ] Imprimir página
- [ ] Exportar para PDF
- [ ] Mais categorias
- [ ] Sistema de notificações

---

## 📸 SCREENSHOTS

### Página Inicial:
```
╔══════════════════════════════════╗
║ Bem-vindo à Minecraft Wiki       ║
║                                  ║
║ 📚 Conteúdo Disponível:         ║
║  • 15 Itens                      ║
║  • 14 Poções                     ║
║  • 39 Encantamentos              ║
╚══════════════════════════════════╝
```

### Página de Item:
```
╔══════════════════════════════════╗
║ Picareta de Diamante ⭐          ║
║ ╔════════════════════╗            ║
║ ║ Tipo: Ferramenta   ║            ║
║ ║ Edição: Ambas      ║            ║
║ ╚════════════════════╝            ║
║                                  ║
║ 📝 Descrição                     ║
║ Ferramenta para minerar...       ║
║                                  ║
║ 📋 Ingredientes                  ║
║  • 3x Diamante                   ║
║  • 2x Graveto                    ║
╚══════════════════════════════════╝
```

---

## 🎊 CONCLUSÃO

**Você agora tem uma Minecraft Wiki COMPLETA que se parece com o site oficial!**

✨ Layout profissional estilo Wiki  
🎨 Cores e design autênticos  
📖 Sidebar com navegação completa  
🔍 Sistema de busca inteligente  
⭐ Favoritos e histórico  
🔗 Links internos funcionais  
📱 Interface responsiva  
💚 100% funcional e pronta para uso!

---

```
    ⛏️  ✨  🌐
   /|\  
  / | \
 🟦🟦🟦

 MINECRAFT WIKI
  VERSION 3.0
   SITE STYLE!
   
 ✅ COMPLETO!
 🎉 IGUAL AO SITE!
 🌐 PROFISSIONAL!
```

💙 **Baseado em https://pt.minecraft.wiki/**  
⛏️ **Execute e explore!**

---

**PRESSIONE Ctrl+F5 NO ARQUIVO WikiMainWindow.java PARA COMEÇAR!** 🚀

