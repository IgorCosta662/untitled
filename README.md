# 🎮 Minecraft Wiki - Edição Completa com Interface Gráfica

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Status](https://img.shields.io/badge/Status-Ativo-success?style=for-the-badge)
![Versão](https://img.shields.io/badge/Versão-3.0-blue?style=for-the-badge)

**Uma enciclopédia interativa completa do Minecraft com interface gráfica moderna!**

[Funcionalidades](#-funcionalidades) • [Instalação](#-instalação) • [Como Usar](#-como-usar) • [Screenshots](#-interface-gráfica) • [Documentação](#-documentação)

</div>

---

## ✨ Destaques da Versão 3.0

### 🎨 Interface Gráfica Completa
- ✨ **Design Moderno**: Interface estilo Minecraft com cores temáticas oficiais
- 🖼️ **Imagens Reais**: 228+ imagens originais baixadas da Minecraft Wiki
- 🎯 **Navegação Intuitiva**: Sistema de histórico com botão voltar (Alt+Backspace)
- 📊 **Visualização Rica**: Cards coloridos e organizados por categorias

### 💾 Sistema de Cache Offline
- 🔌 **Modo Offline**: Funciona sem internet após primeira execução
- 📦 **Download Automático**: Baixa todas as imagens em background
- 💿 **Cache Inteligente**: Armazena imagens em múltiplos tamanhos
- 🌐 **Botão Online/Offline**: Alterne entre modos com um clique

### 🗂️ Catálogo Completo
- **700+ Itens**: Ferramentas, armas, blocos, recursos, alimentos
- **100+ Poções**: Todas as poções com efeitos e receitas
- **40+ Encantamentos**: Lista completa com níveis e incompatibilidades
- **50+ Armaduras**: Todas as peças de todas as materiais
- **20+ Blocos**: Construção, decorativos, funcionais e naturais

## � Instalação

### Pré-requisitos
- ☕ **Java 21+** - [Download Eclipse Adoptium](https://adoptium.net/)
- 💻 **4GB RAM** recomendado
- 🖥️ **Resolução mínima**: 1200x800 pixels
- 🌐 **Internet** (apenas para primeira execução - download de imagens)

### Executando o Projeto

#### Opção 1: IntelliJ IDEA (RECOMENDADO)
1. Clone ou baixe o projeto
2. Abra no IntelliJ IDEA
3. Aguarde indexação
4. Navegue até `src/main/java/org/example/Main.java`
5. Clique com botão direito → **Run 'Main.main()'**
6. ✅ A aplicação abrirá automaticamente!

#### Opção 2: Linha de Comando
```bash
# Compilar
javac -encoding UTF-8 -d target/classes src/main/java/org/example/*.java src/main/java/org/example/gui/*.java

# Executar
java -cp target/classes org.example.Main
```

#### Opção 3: Maven (se instalado)
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="org.example.Main"
```

### Primeira Execução
- 📥 Aguarde 30-60 segundos para download das imagens
- 💾 As imagens serão salvas em `cache/images/`
- 🔌 Próximas execuções funcionarão offline automaticamente

## 🎯 Funcionalidades

### 🏠 Painel Inicial (Home)
- 🎨 **Menu Interativo**: 9 categorias principais com ícones
- 📊 **Estatísticas do Cache**: Mostra imagens salvas localmente
- 🔌 **Botão Modo Offline**: Alterna entre online/offline
- ⬅️ **Navegação com Histórico**: Botão voltar e atalho Alt+Backspace
- 🖼️ **Imagens Reais**: Todos os ícones vindos da Minecraft Wiki

### 📦 Painel de Itens
- **15 Categorias Principais**:
  - 🧱 Blocos (Construção, Decorativo, Funcional, Natural)
  - ⛏️ Ferramentas (Picareta, Machado, Pá, Enxada, Especiais)
  - ⚔️ Armas (Espada, Arco, Besta, Longo Alcance, Mágica)
  - 🛡️ Armaduras (Couro, Ferro, Ouro, Diamante, Netherite)
  - 🍖 Alimentos, 🧪 Poções, 🔨 Crafting, ⚡ Redstone
  - 🌱 Agricultura, 🚀 Exploração, 📦 Armazenamento
  - ✨ Encantamentos, 🌀 Dimensões, 🎨 Diversos, 🔧 Técnicos
- 🔍 **Busca em Tempo Real**: Filtro por nome
- 🎯 **Filtros por Categoria**: Dropdown com todas as categorias
- 📖 **Detalhes do Item**: Nome, descrição, ingredientes, receita
- 🖼️ **Ícones Visuais**: Imagem do item + craft visual
- 📊 **Contadores**: Mostra quantidade de itens por categoria

### ⚗️ Painel de Poções
- 🧪 **Catálogo Completo**: 100+ poções catalogadas
- 📋 **Informações Detalhadas**:
  - Nome e efeitos da poção
  - Duração e nível dos efeitos
  - Ingredientes necessários
  - Modificadores disponíveis
- 🎨 Interface Gráfica

### Painéis Disponíveis

| Painel | Descrição | Ícone |
|--------|-----------|-------|
| 🏠 **Home** | Menu principal com 9 categorias | ⛏️ |
| 📦 **Itens** | Catálogo de 700+ itens em 15 categorias | 📦 |
| 🛡️ **Armaduras** | Todas as armaduras com estatísticas | 🛡️ |
| ✨ **Encantamentos** | 40+ encantamentos detalhados | ✨ |
| ⚗️ **Poções** | 100+ poções com receitas | ⚗️ |
| 🔨 **Fabricação** | Mesa de crafting e ferraria | 🔨 |
| 📊 **Estatísticas** | Dashboard com métricas | 📊 |
| 🔧 **API Test** | Teste de APIs (para desenvolvedores) | 🔧 |
| 📖 **Sobre** | Informações do projeto | 📖 |

### Paleta de Cores Oficial

```java
// Cores Minecraft originais
MINECRAFT_GREEN  = #4CAF50  // Sucesso, confirmações
MINECRAFT_BLUE   = #2196F3  // Encantamentos, links
MINECRAFT_PURPLE = #9C27B0  // Poções, magia
MINECRAFT_GOLD   = #FFC107  // Itens raros, destaque
MINECRAFT_RED    = #F44336  // Alertas, perigo
GRAY_DARK        = #282828  // Fundo principal
GRAY_MEDIUM      = #3C3C3C  // Cards e painéis
```

### Componentes Visuais
- 🎴 **Cards Interativos**: Hover effects e clique
- 🖼️ **Imagens HD**: Ícones oficiais 48x48px
- 🎯 **Badges**: Indicadores de status e tipo
- 📊 **Progress Bars**: Barras de durabilidade
- 🔘 **Botões Modernos**: Bordas arredondadas e sombras
- 📝 **Tooltips**: Dicas ao passar o mouse
- 🎨 **Gradientes**: Transições de cor suav
- 🔨 **Receitas de Craft**: Visual de como criar
- 🆙 **Sistema de Upgrade**: Ferraria para Netherite
- 🖼️ **Visualização por Peça**: Elmo, peitoral, calças, botas
- 🎨 **Moldes de Ferraria**: Todos os 16 moldes catalogados

### 🔨 Painel de Crafting/Ferraria
- 🛠️ **Mesa de Crafting**: Informações sobre crafting 3x3
- ⚒️ **Mesa de Ferraria**: Sistema de upgrade para Netherite
- 📋 **Receitas Visuais**: Exemplos de crafts comuns
- 💎 **Upgrade Diamante → Netherite**: Tutorial completo
- 🔧 **Moldes Necessários**: Lista de todos os moldes

### 📊 Painel de Estatísticas
- 📈 **Dashboard Visual**: Cards com contadores
- 🎯 **Métricas do Banco**:
  - Total de itens cadastrados
  - Quantidade de poções
  - Número de encantamentos
  - Armaduras disponíveis
  - Moldes de ferraria
- 💾 **Informações do Cache**:
  - Imagens salvas
  - Tamanho do cache
  - Status offline/online
- ⚡ **Performance**: Tempo de carregamento

### 🔌 Sistema de Cache Offline
- 📥 **Download Automático**: Ao iniciar, baixa todas as imagens
- 💾 **Armazenamento Local**: `cache/images/` e `cache/images/crafting/`
- 🖼️ **Múltiplos Tamanhos**: 16px, 24px, 32px, 48px
- 🌐 **Modo Online/Offline**: Botão para alternar
- 📊 **Estatísticas em Tempo Real**: Mostra progresso do download
- 🎨 **Sistema de Fallback**: Ícones coloridos se imagem não disponível
- ✅ **228+ Imagens Mapeadas**: Todos os itens principais
- 🚀 **Carregamento Rápido**: Cache em memória + disco

### 🗺️ Sistema de Navegação
- ⬅️ **Botão Voltar**: Em todos os painéis
- ⌨️ **Atalho Alt+Backspace**: Navegação rápida
- 📚 **Histórico de Páginas**: Stack-based navigation
- 🎯 **Breadcrumbs Visuais**: Sabe onde está e de onde veio
- 🔄 **Navegação Circular**: Entre painéis relacionados

### 🎨 Design e Interface
- 🌑 **Tema Escuro**: Conforto visual
- 🎨 **Cores Oficiais do Minecraft**:
  - 🟢 Verde (#4CAF50) - Sucesso
  - 🔵 Azul (#2196F3) - Encantamentos
  - 🟣 Roxo (#9C27B0) - Poções/Magia
  - 🟡 Dourado (#FFC107) - Itens raros
  - 🔴 Vermelho (#F44336) - Alertas
- 🖱️ **Efeitos Hover**: Feedback visual ao passar mouse
- 🎯 **Cursor Hand**: Indica elementos clicáveis
- 📱 **Responsivo**: Adapta-se ao tamanho da janela
- ✨ **Animações Suaves**: Transições fluidas

### 🔍 Sistema de Busca
- ⚡ **Busca em Tempo Real**: Resultado enquanto digita
- 🎯 **Filtros Avançados**: Por categoria, tipo, edição
- 🔤 **Case Insensitive**: Não importa maiúsculas/minúsculas
- 📊 **Contadores de Resultados**: Mostra quantidade encontrada
- 🚀 **Performance Otimizada**: Busca instantânea em 700+ itens

### 🌍 Suporte a Edições
- ☕ **Java Edition**: Todos os recursos
- 🪨 **Bedrock Edition**: Itens equivalentes
- 🔄 **Cross-Platform**: Indica disponibilidade
- ⚠️ **Diferenças Destacadas**: Avisos sobre exclusividades

## 📋 Requisitos
- Java 21 ou superior
- IntelliJ IDEA (recomendado)
- Maven (opcional)
- 1200x800 pixels de resolução mínima

## 🎨 Interface Gráfica

### Telas Disponíveis:
1. **Home**: Menu principal com acesso rápido
2. 🏗️ Estrutura do Projeto

```
minecraft-wiki/
├── 📁 src/main/java/org/example/
│   ├── 📄 Main.java                    # Ponto de entrada
│   ├── 📄 MinecraftWiki.java           # Banco de dados (3500+ linhas)
│   ├── 📄 Item.java                    # Modelo de Item
│   ├── 📄 Pocao.java                   # Modelo de Poção
│   ├── 📄 Encantamento.java            # Modelo de Encantamento
│   ├── 📄 Armadura.java                # Modelo de Armadura
│   ├── 📄 MinecraftEdition.java        # Enum de Edições
│   │
│   ├── 📁 gui/                         # Interface Gráfica
│   │   ├── 📄 MinecraftWikiGUI.java    # Janela principal + navegação
│   │   ├── 📄 HomePanel.java           # Painel inicial
│   │   ├── 📄 ItemsPanel.java          # Catálogo de itens
│   │   ├── 📄 PotionsPanel.java        # Painel de poções
│   │   ├── 📄 EnchantmentsPanel.java   # Painel de encantamentos
│   │   ├── 📄 CraftingSimulatorPanel.java  # Simulador de crafting
│   │   ├── 📄 StatisticsPanel.java     # Dashboard de estatísticas
│   │   ├── 📄 AboutPanel.java          # Informações do projeto
│   │   ├── 📄 SmithingPanel.java       # Painel de ferraria
│   │   ├── 📄 ImageManager.java        # Gerenciador de imagens (600+ linhas)
│   │   └── 📄 APITestPanel.java        # Teste de APIs
│   │
│   └── 📁 api/ (desabilitado)          # APIs avançadas
│       ├── 📄 MinecraftWikiAPI.java    # Cliente API completo
│       ├── 📄 WikiDataService.java     # Serviço de dados
│       └── 📄 CacheManager.java        # Gerenciador de cache SQL
│
├── 📁 cache/                           # Cache local
│   └── 📁 images/                      # Imagens dos itens
│       ├── 🖼️ Invicon_Diamond.png
│       ├── 🖼️ Invicon_Iron_Ingot.png
│       └── 📁 crafting/                # Imagens de receitas
│
├── 📁 target/classes/                  # Arquivos compilados
│Como Usar

### 🚀 Guia Rápido

1. **Primeira Execução**:
   ```
   ✅ Execute Main.java
   ⏳ Aguarde 30-60s para download das imagens
   📦 Veja progresso no console: "📥 Imagem baixada: ..."
   ✅ Pronto! Agora funciona offline
   ```

2. **Navegando pelos Painéis**:
   ```
   📚 Documentação

### Arquivos de Documentação Disponíveis

| Arquivo | Descrição |
|---------|-----------|
| [SISTEMA_CACHE_OFFLINE.md](SISTEMA_CACHE_OFFLINE.md) | Documentação completa do sistema de cache |
| [GUIA_RAPIDO.md](GUIA_RAPIDO.md) | Guia rápido de uso para iniciantes |
| [CHANGELOG.md](CHANGELOG.md) | Histórico de versões e mudanças |
| [README_WIKI_STYLE.md](README_WIKI_STYLE.md) | README estilo wiki (legado) |

### 🎓 Para Desenvolvedores

#### Adicionar Novo Item
```java
// Em MinecraftWiki.java - método carregarItens()
Item novoItem = new Item("Machado de Netherite",
    "O melhor machado do jogo",
    MinecraftEdition.BOTH, "Ferramenta");
novoItem.adicionarIngrediente("1x Machado de Diamante");
novoItem.adicionarIngrediente("1x Lingote de Netherite");
itens.add(novoItem);

// Mapear imagem no ImageManager.java
nameMap.put("NETHERITE_AXE", "Invicon_Netherite_Axe.png");
```

#### Adicionar Nova Categoria
```java
// Em ItemsPanel.java - método setupUI()
categoryHierarchy.put("🔧 Ferramentas Especiais", 
    Arrays.asList("Isqueiro", "Bússola", "Relógio"));
```

#### Criar Novo Painel
```java
// Criar classe MyPanel extends JPanel
public class MyPanel extends JPanel {
    public MyPanel(MinecraftWikiGUI parent, MinecraftWiki wiki) {
        setupUI();
    }
}

// Registrar em MinecraftWikiGUI.java
panels.put("MY_PANEL", new MyPanel(this, wiki));
```

## 🌍 Catálogo Completo

### 📦 Itens (700+)
<details>
### ❌ Problema: Interface não abre
**Sintomas**: Nada acontece ao executar
```
✅ Solução:
1. Verifique Java: java -version (deve ser 21+)
2. Recompile: javac src/main/java/org/example/*.java
3. Execute: java -cp target/classes org.example.Main
4. Veja erros no console
```

### ❌ Problema: Imagens não aparecem (quadrados vazios)
**Sintomas**: Ícones não carregam, aparecem ícones fallback
```
✅ Solução:
1. Execute com internet na primeira vez
2. Aguarde 60 segundos para download
3. Veja console: "📥 Imagem baixada: ..."
4. Verifique pasta cache/images/
5. Se persistir: delete cache e reinicie
```

### ❌ Problema: Modo offline não funciona
**Sintomas**: Sem internet, imagens não aparecem
```
✅ Solução:
1. Execute primeiro em modo ONLINE
2. Aguarde download completo das imagens
3. Veja estatísticas: "📊 Cache: X imagens"
4. Então ative modo offline
```

### ❌ Problema: Aplicação lenta
**Sintomas**: Interface trava, navegação lenta
```
✅ Solução:
1. Feche outros programas
2. Aumente RAM da JVM:
   java -Xmx512m -cp target/classes org.example.Main
3. Limpe cache: delete pasta cache/
4. Verifique CPU: Aplicação usa 1 core
```

### ❌ Problema: Erro de compilação
**Sintomas**: javac falha com erros
```
✅ Solução:
1. Verifique Java 21+: java -version
2. Use encoding UTF-8:
   javac -encoding UTF-8 -d target/classes src/...
3. Limpe e recompile:
   rm -rf target/classes
   mkdir target/classes
   javac -encoding UTF-8 -d target/classes src/main/java/org/example/*.java src/main/java/org/example/gui/*.java
```

### ❌ Problema: Console mostra erros de imagem
**Sintomas**: "Erro ao carregar imagem: Invicon_..."
```
✅ Normal! Algumas imagens não existem na wiki
Sistema usa fallback automático (ícone colorido)
Não afeta funcionamento da aplicação
```

### 🆘 Ainda com problemas?
1. Veja console para mensagens de erro
2. Verifique estrutura de pastas
3. Reinstale Java 21+ do Eclipse Adoptium
4. Clone repositório novamente

## 🔮 Roadmap / Próximas Versões

### Versão 3.1 (Em Breve)
- [ ] 🌐 Tradução para English
- [ ] ⭐ Sistema de favoritos
- [ ] 🔖 Histórico de visualizações
- [ ] 📱 Interface responsiva melhorada
- [ ] 🎨 Modo claro/escuro alternável

### Versão 3.5 (Futuro)
- [ ] 📄 Exportar para PDF
- [ ] 🖨️ Imprimir receitas
- [ ] 💾 Salvar builds personalizados
- [ ] 🔍 Busca avançada com regex
- [ ] 📊 Gráficos de comparação

### Versão 4.0 (Longo Prazo)
- [ ] 🎮 Integração com mods populares
- [ ] 🌍 API pública para desenvolvedores
- [ ] 🤖 Chatbot de ajuda
- [ ] 🎬 Tutoriais em vídeo integrados
- [ ] 🏆 Sistema de conquistas

### Contribuições
Aceita-se contribuições! Veja [CONTRIBUTING.md] para detalhes.

## 📊 Estatísticas do Projeto

```
📈 Status: Ativo e Mantido
👨‍💻 Desenvolvedores: 1 principal
📅 Início: 2024
📅 Última atualização: Dezembro 2025
⭐ Versão: 3.0
📝 Linhas de código: ~15.000+
🐛 Issues conhecidas: 0 críticas
🚀 Próximo release: v3.1 (Janeiro 2026)
```

## 👥 Créditos e Licença

### 🎮 Minecraft
- © 2009-2025 Mojang Studios
- Minecraft é marca registrada da Mojang
- Imagens e ícones © Minecraft Wiki (minecraft.wiki)

### 💻 Desenvolvimento
- **Autor Principal**: Desenvolvedor Java
- **Framework**: Java Swing + Custom UI
- **Inspiração**: Minecraft Official Wiki
- **Comunidade**: Jogadores de Minecraft

### 📜 Licença
```
MIT License

Este projeto é de código aberto para fins educacionais.
Não possui afiliação oficial com Mojang Studios ou Microsoft.
Imagens e ícones são propriedade da Mojang/Minecraft Wiki.
Uso pessoal e educacional permitido.
Uso comercial requer autorização da Mojang.
```

### 🙏 Agradecimentos
- Mojang Studios pelo Minecraft
- Minecraft Wiki pela documentação
- Comunidade Java pelo suporte
- Jogadores que testaram a aplicação

## 📞 Contato e Suporte

### 🐛 Reportar Bugs
- Abra uma issue no repositório
- Descreva o problema detalhadamente
- Inclua screenshots se possível
- Informe versão do Java e SO

### 💡 Sugestões
- Issues com tag "enhancement"
- Pull requests são bem-vindos
- Discussões na seção Discussions

### 📧 Contato
- Email: [seu-email]
- Discord: [seu-discord]
- Twitter: [seu-twitter]

---

<div align="center">

## ⛏️ Bons Crafts e Aventuras! ⚔️

💚 **Desenvolvido com ☕ Java e paixão por Minecraft!**

[![Java](https://img.shields.io/badge/Feito%20com-Java%2021-orange?style=for-the-badge&logo=java)](https://adoptium.net/)
[![Minecraft](https://img.shields.io/badge/Para-Minecraft-green?style=for-the-badge&logo=minecraft)](https://minecraft.net/)
[![Status](https://img.shields.io/badge/Status-Ativo-success?style=for-the-badge)]()

**[⬆ Voltar ao topo](#-minecraft-wiki---edição-completa-com-interface-gráfica)**

</div>uras</summary>

| Material | Defesa Total | Durabilidade | Especial |
|----------|--------------|--------------|----------|
| Couro | 7 | 80 | Tingível |
| Cota de Malha | 12 | 240 | Rara |
| Ouro | 11 | 112 | Encantabilidade +++ |
| Ferro | 15 | 240 | Balanceada |
| Diamante | 20 | 528 | Alto tier |
| Netherite | 20 | 592 | Resistência a fogo |
| Tartaruga | 2 (elmo) | 275 | Respiração aquática |

**16 Moldes de Ferraria**: Todos catalogados com receitas

</details>

### ⚗️ Poções (100+)
<details>
<summary>Ver categorias de poções</summary>

#### Poções Positivas
- Cura: Cura Instantânea I-II
- Força: Força I-II (3:00/8:00)
- Velocidade: Rapidez I-II (3:00/8:00)
- Regeneração: Regeneração I-II (0:45/2:00)
- Resistência ao Fogo (3:00/8:00)
- Visão Noturna (3:00/8:00)
- Invisibilidade (3:00/8:00)
- Salto: Levitação (3:00/8:00)

#### Poções Negativas
- Dano: Dano Instantâneo I-II
- Lentidão I-II
- Fraqueza
- Veneno I-II

#### Modificadores
- 🔴 Pó de Redstone: +5:00 duração
- ✨ Pó de Glowstone: +1 nível
- 🧨 Pólvora: Versão arremessável

</details>

### ✨ Encantamentos (40+)
<details>
<summary>Ver todos os encantamentos</summary>

#### Para Armas
- Afiação I-V: +1.25 dano por nível
- Smite I-V: +2.5 vs mortos-vivos
- Arthropods I-V: +2.5 vs artrópodes
- Aspecto Flamejante I-II: Fogo ao atingir
- Repulsão II: Knockback aumentado

#### Para Armaduras
- Proteção I-IV: -4% dano por nível
- Proteção contra Fogo I-IV
- Proteção contra Projéteis I-IV
- Proteção contra Explosões I-IV
- Espinhos I-III: Reflete dano

#### Para Ferramentas
- Eficiência I-V: Minera mais rápido
- Fortuna I-III: Mais drops
- Toque Suave: Bloco original
- Irrompível I-III: Mais durabilidade

#### Especiais
- Remendo: Repara com XP
- Desaparecimento: Some ao morrer
- ⚡ Channeling: Invoca raio
- 🔱 Lealdade I-III: Tridente retorna
- 🌊 Riptide I-III: Impulso na água

</details>

## 🌍 Suporte a Edições

| Recurso | Java ☕ | Bedrock 🪨 |
|---------|---------|-----------|
| Itens Básicos | ✅ | ✅ |
| Netherite | ✅ | ✅ |
| Moldes Ferraria | ✅ | ✅ |
| Encant. Lâmina Afiada | ✅ | ❌ |
| Poção de Sorte | ✅ | ❌ |
| Interface Gráfica | ✅ | ✅ |
| Cache Offline | ✅ | ✅ |

## ✨ Recursos Técnicos

### 🎨 Frontend
- **Framework**: Java Swing
- **Look & Feel**: Nimbus (nativo do Java)
- **Resolução**: 1200x800 (mínimo) até 4K
- **Taxa de Atualização**: 60 FPS
- **Tema**: Escuro customizado

### 🔧 Backend
- **Linguagem**: Java 21 (LTS)
- **Arquitetura**: MVC Pattern
- **Banco de Dados**: In-Memory (ArrayList)
- **Cache**: Disco + Memória híbrido
- **Imagens**: PNG 48x48px (HD Ready)

### 📦 Dependências
- **JDK 21+**: Runtime principal
- **Swing**: GUI nativa
- **ImageIO**: Manipulação de imagens
- **HttpURLConnection**: Download de imagens
- **Collections Framework**: Estruturas de dados

### 🚀 Performance
- **Startup**: ~2-3 segundos
- **Download Inicial**: ~30-60 segundos (228 imagens)
- **Busca**: <10ms para 700+ itens
- **Navegação**: Instantânea (cache em memória)
- **Uso de RAM**: ~200MB
- **Tamanho do Cache**: ~5-10MBterior |
| `Ctrl + F` | Focar na busca (se disponível) |
| `Enter` | Confirmar busca |
| `ESC` | Fechar diálogos/popups |
| `Alt + F4` | Fechar aplicação |

### 🎯 Dicas Avançadas

1. **Busca Eficiente**:
   - Digite parte do nome: "dia" encontra "Diamante"
   - Use filtros de categoria para resultados precisos
   - Busca funciona em tempo real enquanto digita

2. **Cache de Imagens**:
   - Primeira execução baixa ~5MB de imagens
   - Próximas execuções são instantâneas
   - Para limpar cache: delete pasta `cache/images/`
   - Para forçar re-download: ative modo online

3. **Performance**:
   - Aplicação usa ~200MB de RAM
   - Cache em memória acelera navegação
   - Imagens carregadas em background

4. **Personalização**:
   - Cores podem ser alteradas em cada Panel
   - Tamanhos de fonte ajustáveis no código
   - Sistema modular permite adicionar novos painéi
- **Imagens Mapeadas**: 228+         ├── MinecraftWiki.java           # Banco de dados
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



