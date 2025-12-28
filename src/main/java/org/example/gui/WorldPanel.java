package org.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Painel consolidado de MUNDO: Criaturas + Biomas + Estruturas
 */
public class WorldPanel extends JPanel {
    private final MinecraftWikiGUI parent;

    public WorldPanel(MinecraftWikiGUI parent) {
        this.parent = parent;
        
        setLayout(new BorderLayout());
        setBackground(new Color(40, 40, 40));

        // Criar abas para organizar o conteúdo
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setBackground(new Color(40, 40, 40));
        tabbedPane.setForeground(Color.WHITE);

        // Adicionar painéis existentes como abas
        tabbedPane.addTab("🐾 Criaturas", new CreaturesPanel(parent));
        tabbedPane.addTab("🌍 Biomas", new BiomesPanel(parent));
        tabbedPane.addTab("🏛️ Estruturas", new StructuresPanel(parent));

        add(tabbedPane, BorderLayout.CENTER);

        // Botão voltar
        JButton backButton = createBackButton();
        add(backButton, BorderLayout.SOUTH);
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("← Voltar ao Menu");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backButton.setBackground(new Color(60, 60, 60));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(80, 80, 80));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(60, 60, 60));
            }
        });
        
        backButton.addActionListener(e -> parent.showPanel("HOME"));
        
        return backButton;
    }
}
