package negocios;

import ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainFrame(sistema).setVisible(true);
        });
    }
}