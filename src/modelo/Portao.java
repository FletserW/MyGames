package modelo;

import java.awt.Color;
import java.awt.Graphics;

public class Portao {

    private int x, y;
    private int largura, altura;
    private boolean colidindo;

    public Portao(int x, int y, int largura, int altura) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.colidindo = false;
    }

    public void desenhar(Graphics g) {
        if (colidindo) {
            // Desenha o portão roxo
            g.setColor(Color.MAGENTA);
            g.fillRect(x, y, largura, altura);
        } else {
            // Desenha o portão transparente
            g.setColor(new Color(255, 0, 255, 0));
            g.fillRect(x, y, largura, altura);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setColidindo(boolean colidindo) {
        this.colidindo = colidindo;
    }

    public boolean isColidindo() {
        return colidindo;
    }
}
