package modelo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author reido
 */
public class BotaoTransparente extends JButton {

    private Image fundo;
    private boolean hover;
    public Color cor;

    public BotaoTransparente(String texto) {
        super(texto);
        this.cor = new Color(0,0,0, 10);
        setOpaque(false); // Torna o botão transparente
        setContentAreaFilled(false); // Remove a área de preenchimento do botão
        setBorderPainted(false); // Remove a borda do botão
        setForeground(Color.WHITE); // Define a cor do texto
        setFont(new Font("Arial", Font.BOLD, 16)); // Define a fonte do texto
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicione aqui o que deseja fazer ao clicar no botão
            }
        });

        // Adiciona um listener de mouse para lidar com o hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true; // Define que o mouse está sobre o botão
                repaint(); // Repinta o botão para atualizar o hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false; // Define que o mouse saiu do botão
                repaint(); // Repinta o botão para atualizar o hover
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Chama o método paintComponent da superclasse JPanel

        if (isEnabled()) {
            if (hover) {
                // Se o mouse estiver sobre o botão, define a cor de fundo como vermelho
                g.setColor(cor);
                g.fillRect(0, 0, getWidth(), getHeight()); // Preenche todo o botão com a cor de fundo
            }
            // Desenha o texto do botão
            g.setColor(getForeground());
            g.setFont(getFont());
            // Centraliza o texto no botão
            int x = (getWidth() - g.getFontMetrics().stringWidth(getText())) / 2;
            int y = (getHeight() + g.getFontMetrics().getHeight()) / 2 - 2;
            g.drawString(getText(), x, y);
        }
    }
}
