/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player {

    private int x, y;
    private int dx, dy;
    private int altura, largura;
    private Image[] imagensCorrendo;
    private Image[] imagensSubindoEscada;
    private int frameAtual;
    private final int NUMERO_FRAMES = 5;
    private final int GRAVIDADE = 1; // Adiciona a constante de gravidade
    private final int VELOCIDADE_PULO = -13; // Define a velocidade do pulo
    public final int VELOCIDADE_ESCADA = 3; // Define a velocidade de subida na escada
    private final int DELAY_ANIMACAO = 120; // Controla a velocidade da animação
    private long tempoUltimaAnimacao; // Controla o tempo da última animação
    private boolean noChao; // Indica se o jogador está no chão ou não
    private boolean viradoParaDireita; // Indica se o jogador está virado para a direita
    

    public Player() {
        this.x = 140;
        this.y = 500;
        this.noChao = true; // Inicialmente, o jogador está no chão
        this.viradoParaDireita = true; // Inicialmente, o jogador está virado para a direita
        this.frameAtual = 0; // Inicializa o frame atual da animação
        this.imagensCorrendo = new Image[NUMERO_FRAMES]; // Inicializa o array de imagens
        carregarImagens(); // Carrega as imagens
        this.tempoUltimaAnimacao = System.currentTimeMillis(); // Inicializa o tempo da última animação
    }

    private void carregarImagens() {
        // Carrega as imagens da corrida
        for (int i = 0; i < NUMERO_FRAMES; i++) {
            ImageIcon ref = new ImageIcon(getClass().getResource("/res/Player" + (i + 1) + ".png"));
            imagensCorrendo[i] = ref.getImage();

        }

        altura = imagensCorrendo[0].getHeight(null);
        largura = imagensCorrendo[0].getWidth(null);
    }

    public void upgrade() {
        // Aplica a gravidade ao movimento vertical
        if (!noChao) { // Se o jogador não estiver no chão
            dy += GRAVIDADE; // Aumenta a velocidade vertical (queda)
        }

        // Calcula o tempo desde a última animação
        long tempoAtual = System.currentTimeMillis();
        long tempoDecorrido = tempoAtual - tempoUltimaAnimacao;

        if (tempoDecorrido > DELAY_ANIMACAO) {
            // Atualiza o frame da animação se o tempo decorrido for maior que o atraso
            frameAtual++; // Avança para o próximo frame
            if (frameAtual >= NUMERO_FRAMES) {
                frameAtual = 0; // Reinicia a animação
            }

            // Atualiza o tempo da última animação
            tempoUltimaAnimacao = tempoAtual;
        }

        x += dx;
        y += dy;
    }
    
    private void carregarImagensSubindoEscada() {
        imagensSubindoEscada = new Image[3]; // Criando um array para armazenar as imagens
        for (int i = 0; i < 3; i++) {
            ImageIcon ref = new ImageIcon("res/Player/SubindoEscada" + (i + 1) + ".png");
            imagensSubindoEscada[i] = ref.getImage(); // Carregando as imagens
        }
    }

    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_W && noChao) { // Apenas pode pular se estiver no chão
            dy = VELOCIDADE_PULO; // Aplica a velocidade do pulo
            noChao = false; // Marca que o jogador não está mais no chão
        }
        if (codigo == KeyEvent.VK_A) {
            viradoParaDireita = false; // Define que o jogador está virado para a esquerda
            dx = -3; // Define a velocidade negativa se estiver virado para a esquerda
        }
        if (codigo == KeyEvent.VK_D) {
            viradoParaDireita = true; // Define que o jogador está virado para a direita
            dx = 3; // Define a velocidade positiva se estiver virado para a direita
        }
    }

    public void keyRelease(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_A || codigo == KeyEvent.VK_D) {
            dx = 0; // Reseta a velocidade horizontal ao soltar as teclas de movimento
        }
    }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        if (dx == 0) {
            // Se o jogador não estiver se movendo, retorna a imagem estática
            return imagensCorrendo[0];
        } else {
            // Se o jogador estiver se movendo, retorna o frame atual da animação
            if (!viradoParaDireita) { // Se estiver virado para a esquerda, espelha a imagem
                return inverterHorizontalmente(imagensCorrendo[frameAtual]);
            } else {
                return imagensCorrendo[frameAtual];
            }
        }
    }

    private Image inverterHorizontalmente(Image img) {
        int largura = img.getWidth(null);
        int altura = img.getHeight(null);

        // Cria uma imagem com o formato ARGB para garantir a transparência
        BufferedImage imagemInvertida = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);

        // Obtém o contexto gráfico da imagem
        Graphics2D g2d = imagemInvertida.createGraphics();

        // Desenha a imagem espelhada
        g2d.drawImage(img, largura, 0, 0, altura, 0, 0, largura, altura, null);

        // Descarta o contexto gráfico
        g2d.dispose();

        // Retorna a imagem espelhada
        return imagemInvertida;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setNoChao(boolean noChao) {
        this.noChao = noChao;
    }

    public boolean isNoChao() {
        return noChao;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean isViradoParaDireita() {
        return viradoParaDireita;
    }
}
