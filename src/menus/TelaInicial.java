package menus;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import main.Container;
import modelo.BotaoTransparente;

/**
 *
 * @author reido
 */
public class TelaInicial extends JPanel {

    private Image fundo;
    private BotaoTransparente btnComecar;
    private BotaoTransparente btnOpcoes;
    private BotaoTransparente btnSair;
    private Container container;

    public TelaInicial(Container container) {
        super();
        this.container = container;
        setFocusable(true); // Torna a tela inicial focável
        setDoubleBuffered(true);
        setLayout(null); // Define o layout como null para posicionamento absoluto

        ImageIcon ref = new ImageIcon(getClass().getResource("/res/Tela_Inicial.png"));
        fundo = ref.getImage();

        // Inicializa o botão "Começar"
        btnComecar = new BotaoTransparente("");
        // Define as coordenadas x e y do botão
        btnComecar.setBounds(390, 300, 300, 50); // x, y, largura, altura
        // Adiciona um listener de ação ao botão
        btnComecar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.iniciarFase1();
            }
        });
        
        // Inicializa o botão "Opções"
        btnOpcoes = new BotaoTransparente("");
        // Define as coordenadas x e y do botão
        btnOpcoes.setBounds(390, 375, 300, 50); // x, y, largura, altura
        // Adiciona um listener de ação ao botão
        btnOpcoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Menu Opção");
            }
        });
        
        // Inicializa o botão "fechar"
        btnSair = new BotaoTransparente("");
        // Define as coordenadas x e y do botão
        btnSair.setBounds(390, 450, 300, 50); // x, y, largura, altura
        // Adiciona um listener de ação ao botão
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);            }
        });

        // Adiciona o botão à tela inicial
        add(btnComecar);
        add(btnOpcoes);
        add(btnSair);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
    }
}
