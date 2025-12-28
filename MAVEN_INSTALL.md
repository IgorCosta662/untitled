# 🚀 Guia de Instalação - Maven e APIs

## ⚡ Instalação Rápida do Maven

### Windows

#### Opção 1: Chocolatey (Recomendado)
```powershell
# Instalar Chocolatey (se não tiver)
Set-ExecutionPolicy Bypass -Scope Process -Force
[System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072
iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))

# Instalar Maven
choco install maven -y

# Verificar instalação
mvn -version
```

#### Opção 2: Download Manual
1. Baixar Maven: https://maven.apache.org/download.cgi
2. Extrair para `C:\Program Files\Apache\maven`
3. Adicionar ao PATH:
   ```
   setx MAVEN_HOME "C:\Program Files\Apache\maven"
   setx PATH "%PATH%;%MAVEN_HOME%\bin"
   ```
4. Reiniciar terminal e testar: `mvn -version`

---

## 📦 Baixar Dependências do Projeto

### Após Instalar Maven

```bash
cd c:\Users\medei\IdeaProjects\untitled

# Limpar e baixar todas as dependências
mvn clean install -DskipTests

# Ou baixar apenas as dependências
mvn dependency:resolve
```

---

## 🔧 Alternativa SEM Maven

Se você não quiser instalar Maven, pode usar as classes simplificadas que já criei:

### Classes que funcionam SEM dependências externas:

1. ✅ **MinecraftWikiAPISimple.java** - API básica usando apenas Java
2. ✅ **CacheManager.java** - Usa SQLite (já incluído no Java)
3. ✅ **Todas as classes GUI** - Funcionam normalmente

### Para compilar SEM Maven:

```bash
# Compilar normalmente
cd c:\Users\medei\IdeaProjects\untitled
javac -encoding UTF-8 -d target/classes -cp target/classes src/main/java/org/example/**/*.java

# Executar
java -cp target/classes org.example.gui.MinecraftWikiGUI
```

---

## 📊 Comparação: Com vs Sem Maven

| Recurso | Sem Maven | Com Maven |
|---------|-----------|-----------|
| Compilação | ✅ Manual | ✅ Automática |
| API Minecraft Wiki | ✅ Básica | ✅ Completa |
| Cache SQLite | ⚠️ Limitado | ✅ Completo |
| JSON Parsing | ⚠️ Manual | ✅ Gson/Jackson |
| HTTP Requests | ✅ HttpURLConnection | ✅ OkHttp |
| Performance | ⚠️ Boa | ✅ Excelente |

---

## 🎯 Próximos Passos

### Se instalou Maven:
1. Execute `mvn clean install`
2. As bibliotecas serão baixadas automaticamente
3. Use a versão completa do `APITestPanel`

### Se NÃO instalou Maven:
1. Use as versões simplificadas das classes
2. Funcionalidade básica estará disponível
3. Considere instalar Maven no futuro para recursos avançados

---

## 💡 Dica

Para um projeto profissional, **Maven é altamente recomendado**! Ele gerencia todas as dependências automaticamente e é o padrão da indústria para projetos Java.

Tempo de instalação: ~5 minutos  
Benefícios: Infinitos ✨
