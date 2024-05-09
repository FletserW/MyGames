package fases;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import menus.MenuPausa;
import modelo.Escada;
import modelo.Plataforma;
import modelo.Player;
import modelo.Portao;
import modelo.Trampolim;

public class Fase_1 extends JPanel implements ActionListener {

    private Image fundo;
    private Player player;
    private Timer timer;
    private ArrayList<Plataforma> plataformas;
    private ArrayList<Escada> escadas;
    private Trampolim trampolim;
    private Portao objetoRoxo;
    private boolean mostrarMensagem;
    private MecanicasFase mecanicas;

    private MenuPausa menuPausa;

    public Fase_1() {
        setFocusable(true);
        setDoubleBuffered(true);

        // Carrega a imagem de fundo da fase
        ImageIcon ref = new ImageIcon(getClass().getResource("/res/Background.png"));
        fundo = ref.getImage();

        // Inicializa as mecânicas da fase
        mecanicas = new MecanicasFase();

        // Configura o jogador
        player = new Player();

        // Configura as plataformas
        plataformas = new ArrayList<>();
        plataformas.add(new Plataforma(130, 610, 330, 20));
        plataformas.add(new Plataforma(490, 565, 200, 20));
        plataformas.add(new Plataforma(740, 565, 90, 20));
        plataformas.add(new Plataforma(870, 565, 150, 20));
        plataformas.add(new Plataforma(720, 400, 190, 20));
        plataformas.add(new Plataforma(420, 370, 250, 20));
        plataformas.add(new Plataforma(175, 430, 190, 20));
        plataformas.add(new Plataforma(320, 190, 180, 20));
        plataformas.add(new Plataforma(570, 180, 220, 20));
        plataformas.add(new Plataforma(820, 110, 190, 20));

        // Configura as escadas
        escadas = new ArrayList<>();
        escadas.add(new Escada(920, 390, 40, 180));

        // Configura o trampolim
        trampolim = new Trampolim(210, 410, 50, 20);

        // Configura o objeto roxo
        objetoRoxo = new Portao(950, 60, 50, 50);

        // Adiciona um KeyListener para as teclas do jogador
        addKeyListener(new TecladoAdapter());

        // Inicia o timer
        timer = new Timer(5, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

        // Desenha as plataformas
        for (Plataforma plataforma : plataformas) {
            plataforma.desenhar(graficos);
        }

        // Desenha as escadas
        for (Escada escada : escadas) {
            escada.desenhar(graficos);
        }

        trampolim.desenhar(graficos);
        objetoRoxo.desenhar(graficos);

        if (mostrarMensagem) {
            // Desenha a mensagem de parabéns
            graficos.setFont(new Font("Arial", Font.BOLD, 24));
            graficos.setColor(Color.BLACK);
            graficos.drawString("Parabéns! Você concluiu a fase!", 300, 100);
        }
        
        // Desenha a mensagem se necessário
        mecanicas.desenharMensagem(g, getWidth(), getHeight());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.upgrade();
        // Verifica as colisões e atualiza o estado da fase
        mecanicas.verificarColisao(player, plataformas, escadas, trampolim, objetoRoxo);
        // Redesenha a tela
        repaint();
    }

    public class TecladoAdapter extends KeyAdapter {

        @Override  
        public void keyPressed(KeyEvent tecla) {
            if (tecla.getKeyCode() == KeyEvent.VK_R) {
                mecanicas.reiniciarFase(player);
            } else if (tecla.getKeyCode() == KeyEvent.VK_ESCAPE) { // Adiciona a detecção da tecla "ESC"
                mecanicas.keyPressed(tecla);
            } else {
                player.keyPressed(tecla);
            }
        }

        @Override
        public void keyReleased(KeyEvent tecla) {
            // Trata o evento de soltar a tecla
            player.keyRelease(tecla);
        }
    }

    // Método para carregar uma imagem
    private Image loadImage(String nomeArquivo) {
        ImageIcon imagemIcon = new ImageIcon(nomeArquivo);
        return imagemIcon.getImage();
    }

    public void setMecanicas(MecanicasFase mecanicas) {
        this.mecanicas = mecanicas;
    }

}
