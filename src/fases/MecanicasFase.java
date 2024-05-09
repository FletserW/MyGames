/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fases;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import menus.MenuPausa;
import modelo.Escada;
import modelo.Plataforma;
import modelo.Player;
import modelo.Portao;
import modelo.Trampolim;

public class MecanicasFase {

    private boolean mostrarMensagem;
    private boolean mostrarPause;
    private Image mensagemImg;

    public MecanicasFase() {
        // Carrega a imagem da mensagem
        ImageIcon ref = new ImageIcon(getClass().getResource("/res/menu_pause.png"));
        mensagemImg = ref.getImage();

    }

    public void verificarColisao(Player player, ArrayList<Plataforma> plataformas, ArrayList<Escada> escadas,
            Trampolim trampolim, Portao objetoRoxo) {
        // Implemente aqui a lógica de verificação de colisão
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
                    && jogadorXCentro >= plataformaXEsquerda && jogadorXCentro <= plataformaXDireita) {
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

    public void reiniciarFase(Player player) {
        // Reinicializa a posição do jogador para a posição inicial
        player.setX(140);
        player.setY(500);
        player.setNoChao(true); // Garante que o jogador inicie no chão
        player.setDy(0); // Reseta a velocidade vertical
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            mostrarPause = true; // Ativa a flag para mostrar a mensagem
            System.out.println("Tecla ESC");
        }
    }

    public void keyReleased(KeyEvent e) {
        // Implemente aqui o tratamento de eventos quando uma tecla é solta
    }

    public void desenharMensagem(Graphics g, int larguraTela, int alturaTela) {
        if (mostrarPause) {
            // Calcula a posição para centralizar a mensagem
            int x = (larguraTela - mensagemImg.getWidth(null)) / 2;
            int y = (alturaTela - mensagemImg.getHeight(null)) / 2;
            // Desenha a mensagem no centro da tela
            g.drawImage(mensagemImg, x, y, null);
        }
    }

    public void setMostrarPause(boolean mostrarPause) {
        this.mostrarPause = mostrarPause;
    }
    
    
}

