/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

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

public class Fase_1 extends JPanel implements ActionListener{

    private Image fundo;
    private Player player;
    private Timer timer;
    private ArrayList<Plataforma> plataformas;
    private ArrayList<Escada> escadas; 
    private Trampolim trampolim;
    private Portao objetoRoxo;
    private boolean mostrarMensagem;


    public Fase_1() {
        setFocusable(true);
        setDoubleBuffered(true);
        
        ImageIcon ref = new ImageIcon(getClass().getResource("/res/Background.png"));

        fundo = ref.getImage();
        
        player = new Player();
        
        
        plataformas = new ArrayList<>();
        escadas = new ArrayList<>();
        
        // Adicione algumas plataformas de exemplo
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
        
        escadas.add(new Escada(920, 390, 40, 180));
        
        trampolim = new Trampolim(210, 410, 50, 20); 
        
        objetoRoxo = new Portao(950, 60, 50, 50);
        
        addKeyListener(new TecladoAdapter());
        
        timer = new Timer(5,this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g; // Correção aqui
        graficos.drawImage(fundo, 0, 0, null);
        graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
        
        // Desenhe as plataformas
        for (Plataforma plataforma : plataformas) {
            plataforma.desenhar(graficos);
        }
        
        // Desenhe as escadas
        for (Escada escada : escadas) {
            escada.desenhar(graficos);
        }
        
        trampolim.desenhar(graficos);
        
        objetoRoxo.desenhar(graficos);
        
        if (mostrarMensagem) {
            // Desenhe a mensagem de parabéns
            graficos.setFont(new Font("Arial", Font.BOLD, 24));
            graficos.setColor(Color.BLACK);
            graficos.drawString("Parabéns! Você concluiu a fase!", 300, 100);
            
        }

        
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.upgrade();
        verificarColisao();
        repaint();
    }
    
    private void reiniciarFase() {
        // Reinicializa a posição do jogador para a posição inicial
        player.setX(140);
        player.setY(500);
        player.setNoChao(true); // Garante que o jogador inicie no chão
        player.setDy(0); // Reseta a velocidade vertical
    }

    
    private class TecladoAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent tecla) {
            int codigo = tecla.getKeyCode();
            if (codigo == KeyEvent.VK_R) {
                reiniciarFase(); // Se a tecla "R" for pressionada, reinicia a fase
            } else {
                player.keyPressed(tecla);
            }
        }
        @Override
        public void keyReleased(KeyEvent tecla){
            player.keyRelease(tecla);
        }
    }
    
    private void verificarColisao() {
        // Coordenadas do jogador
        int jogadorXEsquerda = player.getX();
        int jogadorXCentro = jogadorXEsquerda + player.getLargura() / 2;
        int jogadorXDireita = jogadorXEsquerda + player.getLargura();
        int jogadorYBaixo = player.getY() + player.getAltura();
        int jogadorYSuperior = player.getY(); // Coordenada Y do topo do jogador

        // Verifica colisão com plataformas
        for (Plataforma plataforma : plataformas) {
            int plataformaXEsquerda = plataforma.getX();
            int plataformaXDireita = plataformaXEsquerda + plataforma.getLargura();
            int plataformaYTopo = plataforma.getY();
            int plataformaYBaixo = plataformaYTopo + plataforma.getAltura();

            // Verifica se a parte inferior do jogador está se aproximando da parte superior da plataforma
            if (jogadorYBaixo >= plataformaYTopo && jogadorYSuperior <= plataformaYBaixo + 2
                    && // Ajuste aqui
                    jogadorXCentro >= plataformaXEsquerda && jogadorXCentro <= plataformaXDireita) {
                // Ajusta a posição do jogador para que fique na parte de cima da plataforma
                player.setY(plataformaYTopo - player.getAltura());
                player.setNoChao(true);
                return;
            }
        }

        // Verifica colisão com escadas
        for (Escada escada : escadas) {
            int escadaTopo = escada.getY(); // Coordenada Y do topo da escada
            int escadaBaixo = escadaTopo + escada.getAltura(); // Coordenada Y da parte de baixo da escada
            int escadaEsquerda = escada.getX(); // Coordenada X da esquerda da escada
            int escadaDireita = escadaEsquerda + escada.getLargura(); // Coordenada X da direita da escada

            // Verifica se o jogador está dentro da escada
            if (jogadorXCentro >= escadaEsquerda && jogadorXCentro <= escadaDireita
                    && jogadorYSuperior <= escadaBaixo && jogadorYBaixo >= escadaTopo) {
                // Se estiver, permite que o jogador suba
                player.setNoChao(true);
                player.setDy(-player.VELOCIDADE_ESCADA); // Define uma velocidade constante de subida
                return;
            }
        }
        
        if (jogadorYBaixo >= trampolim.getY() && jogadorYSuperior <= trampolim.getY() + trampolim.getAltura()
                && jogadorXCentro >= trampolim.getX() && jogadorXCentro <= trampolim.getX() + trampolim.getLargura()) {
            // Se houver colisão com o trampolim, joga o jogador para cima
            player.setNoChao(true);
            player.setDy(-25); // Ajuste a altura conforme necessário
            return;
        }
        
        if (jogadorXEsquerda < objetoRoxo.getX() + objetoRoxo.getLargura()
                && jogadorXEsquerda + player.getLargura() > objetoRoxo.getX()
                && jogadorYBaixo > objetoRoxo.getY() && jogadorYSuperior < objetoRoxo.getY() + objetoRoxo.getAltura()) {
            // Se houver colisão com o objeto roxo, mostra a mensagem
            mostrarMensagem = true;
        } else {
            mostrarMensagem = false;
        }


        // Se não houver colisão com nenhuma plataforma ou escada, define que o jogador não está no chão
        player.setNoChao(false);
    }


}