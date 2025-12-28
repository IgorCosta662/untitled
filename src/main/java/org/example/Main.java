package org.example;

import org.example.gui.MinecraftWikiGUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    private static ServerSocket serverSocket;
    
    public static void main(String[] args) {
        // Verificar se já existe uma instância rodando
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                "Minecraft Wiki já está aberto!", 
                "Aviso", 
                JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        
        // Configurar Look and Feel
        try {
            // Tentar usar Nimbus (moderno e cross-platform)
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            UIManager.put("control", new Color(50, 50, 50));
            UIManager.put("info", new Color(50, 50, 50));
            UIManager.put("nimbusBase", new Color(40, 40, 40));
            UIManager.put("nimbusBlueGrey", new Color(60, 60, 60));
            UIManager.put("nimbusLightBackground", new Color(40, 40, 40));
            UIManager.put("text", Color.WHITE);
        } catch (Exception e) {
            System.err.println("Não foi possível configurar o Look and Feel");
        }

        // Iniciar interface gráfica
        SwingUtilities.invokeLater(() -> {
            MinecraftWikiGUI gui = new MinecraftWikiGUI();
            gui.setVisible(true);
            
            // Liberar porta ao fechar
            gui.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    try {
                        if (serverSocket != null) {
                            serverSocket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }
}
