package org.example.api;

/**
 * Versão simplificada do MinecraftWikiAPI para demonstração
 * Funciona sem dependências externas usando apenas Java built-in
 */
public class MinecraftWikiAPISimple {
    
    private static final String WIKI_API_URL = "https://minecraft.wiki/api.php";
    
    /**
     * Busca informações sobre um item (versão simplificada)
     * Esta é uma demonstração - na versão completa com OkHttp funciona melhor
     */
    public String searchItemSimple(String itemName) {
        try {
            java.net.URL url = new java.net.URL(WIKI_API_URL + 
                "?action=query&list=search&srsearch=" + 
                itemName.replace(" ", "%20") + 
                "&format=json&srlimit=5");
            
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "MinecraftWikiApp/2.0");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            
            if (conn.getResponseCode() == 200) {
                java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Teste de conexão básico
     */
    public boolean testConnection() {
        try {
            java.net.URL url = new java.net.URL(WIKI_API_URL);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            conn.setConnectTimeout(3000);
            return conn.getResponseCode() == 200;
        } catch (Exception e) {
            return false;
        }
    }
}
