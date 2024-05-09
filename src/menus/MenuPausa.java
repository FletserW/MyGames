package menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPausa extends JPanel {

    private JButton continuarButton;
    private JButton resetarButton;
    private JButton sairButton;
    private ActionListener listener;

    public MenuPausa(ActionListener listener) {
        this.listener = listener;

        setLayout(null); // Define layout como nulo para posicionar os botões manualmente

        continuarButton = new JButton("Continuar");
        resetarButton = new JButton("Resetar");
        sairButton = new JButton("Sair");

        // Define o tamanho e posição dos botões
        continuarButton.setBounds(200, 200, 100, 50);
        resetarButton.setBounds(200, 270, 100, 50);
        sairButton.setBounds(200, 340, 100, 50);

        // Adiciona o listener aos botões
        continuarButton.addActionListener(listener);
        resetarButton.addActionListener(listener);
        sairButton.addActionListener(listener);

        // Adiciona os botões ao painel
        add(continuarButton);
        add(resetarButton);
        add(sairButton);

        // Define o tamanho e a cor de fundo do painel
        setSize(500, 500);
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha um título
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.setColor(Color.BLACK);
        g.drawString("Menu de Pausa", 180, 150);
    }
}
