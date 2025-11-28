package com.mycompany.gymnewbody;

import Pantallas.NewLogin;

public class GymNewBody {

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar Nimbus: " + e);
        }

        javax.swing.SwingUtilities.invokeLater(() -> {
            NewLogin login = new NewLogin();
            login.setVisible(true);
        });
    }
}
