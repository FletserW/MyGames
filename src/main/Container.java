package main;


import javax.swing.JFrame;
import fases.Fase_1;
import fases.MecanicasFase;
import menus.TelaInicial;

public class Container extends JFrame {

    private TelaInicial telaInicial;
    private Fase_1 fase1;
    private MecanicasFase mecanicas;

    public Container() {
        // Cria uma instância da tela inicial
        telaInicial = new TelaInicial(this);
        // Define as configurações padrão do JFrame
        setTitle("Long Stick");
        setSize(1024, 728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        // Adiciona a tela inicial ao JFrame principal
        add(telaInicial);
        // Torna a tela inicial visível
        setVisible(true);

        // Inicializa as mecânicas da fase
        mecanicas = new MecanicasFase();
    }

    // Método para iniciar a primeira fase
    public void iniciarFase1() {
        // Cria uma nova instância da fase 1
        fase1 = new Fase_1();
        // Adiciona as mecânicas da fase
        fase1.setMecanicas(mecanicas);
        // Adiciona a fase 1 ao JFrame principal
        add(fase1);
        // Define o tamanho da fase
        fase1.setSize(1024, 728);
        // Adiciona o foco do teclado à fase
        fase1.requestFocusInWindow();
        // Adiciona o listener de ação do teclado à fase
        fase1.addKeyListener(fase1.new TecladoAdapter());
        // Remove a tela inicial do JFrame principal
        remove(telaInicial);
        // Atualiza o JFrame principal para refletir as mudanças
        revalidate();
        repaint();
        // Torna a fase 1 visível
        fase1.setVisible(true);
    }

    public static void main(String[] args) {
        new Container();
    }
}
