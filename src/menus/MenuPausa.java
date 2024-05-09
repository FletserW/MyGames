package menus;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuPausa extends JPanel {

    private JButton continuarButton;
    private JButton resetarButton;
    private JButton sairButton;

    public MenuPausa() {
        continuarButton = new JButton("Continuar");
        resetarButton = new JButton("Resetar");
        sairButton = new JButton("Sair");

        continuarButton.addActionListener(e -> continuarJogo());
        resetarButton.addActionListener(e -> resetarJogo());
        sairButton.addActionListener(e -> sairJogo());

        add(continuarButton);
        add(resetarButton);
        add(sairButton);
    }

    private void continuarJogo() {
        // Adicione a lógica para continuar o jogo
    }

    private void resetarJogo() {
        // Adicione a lógica para resetar o jogo
    }

    private void sairJogo() {
        int opcao = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja sair do jogo?", "Sair do Jogo",
                JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
