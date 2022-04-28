/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Login;

import java.awt.*;

/**
 *
 * @author NghiepCuong
 */

public class LoginView extends javax.swing.JFrame {

    /**
     * Creates new form LoginView
     */
    public LoginView() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldUsername = new java.awt.TextField();
        jLabelUsername = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jPasswordFieldLoginPass = new javax.swing.JPasswordField();
        jLabelRegisterAccount = new javax.swing.JLabel();
        jLabelForgotPassword = new javax.swing.JLabel();
        jButtonLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(340, 460));
        setResizable(false);

        textFieldUsername.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textFieldUsername.setFont(new java.awt.Font("SVN-Arial", 0, 16));
        textFieldUsername.setForeground(new java.awt.Color(204, 204, 204));
        textFieldUsername.setMaximumSize(new java.awt.Dimension(220, 30));
        textFieldUsername.setMinimumSize(new java.awt.Dimension(220, 30));
        textFieldUsername.setPreferredSize(new java.awt.Dimension(220, 30));
        textFieldUsername.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                textFieldUsernameHierarchyChanged(evt);
            }
        });
        textFieldUsername.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                textFieldUsernameInputMethodTextChanged(evt);
            }
        });

        jLabelUsername.setFont(new java.awt.Font("SVN-Arial", 3, 20)); // NOI18N
        jLabelUsername.setLabelFor(textFieldUsername);
        jLabelUsername.setText("SĐT/Tên tài khoản");
        jLabelUsername.setAutoscrolls(false);
        jLabelUsername.setPreferredSize(new java.awt.Dimension(240, 30));

        jLabelPassword.setFont(new java.awt.Font("SVN-Arial", 3, 20)); // NOI18N
        jLabelPassword.setLabelFor(jPasswordFieldLoginPass);
        jLabelPassword.setText("Mật khẩu");
        jLabelPassword.setAutoscrolls(false);
        jLabelPassword.setPreferredSize(new java.awt.Dimension(240, 30));

        jPasswordFieldLoginPass.setFont(new java.awt.Font("SVN-Arial", 0, 16));
        jPasswordFieldLoginPass.setForeground(new java.awt.Color(204, 204, 204));
        jPasswordFieldLoginPass.setMaximumSize(new java.awt.Dimension(220, 30));
        jPasswordFieldLoginPass.setMinimumSize(new java.awt.Dimension(220, 30));
        jPasswordFieldLoginPass.setPreferredSize(new java.awt.Dimension(220, 30));

        jLabelRegisterAccount.setFont(new java.awt.Font("SVN-Arial", 2, 12)); // NOI18N
        jLabelRegisterAccount.setText("Đăng ký");
        jLabelRegisterAccount.setFont(new Font("SVN-Arial", Font.ITALIC, 13));

        jLabelForgotPassword.setFont(new java.awt.Font("SVN-Arial", 2, 12)); // NOI18N
        jLabelForgotPassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelForgotPassword.setText("Quên mật khẩu");

        jButtonLogin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/Login Button.png"))); // NOI18N
        jButtonLogin.setText(null);
        jButtonLogin.setBorder(null);
        jButtonLogin.setBorderPainted(false);
        jButtonLogin.setContentAreaFilled(false);
        jButtonLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonLogin.setDefaultCapable(false);
        jButtonLogin.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/Login Button.png"))); // NOI18N
        jButtonLogin.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/Login Button.png"))); // NOI18N
        jButtonLogin.setFocusPainted(false);
        jButtonLogin.setFocusable(false);
        jButtonLogin.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonLogin.setPreferredSize(new java.awt.Dimension(150, 50));
        jButtonLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonLoginMouseClicked(evt);
            }
        });
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabelRegisterAccount)
                .addGap(98, 98, 98)
                .addComponent(jLabelForgotPassword)
                .addGap(31, 31, 31))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPasswordFieldLoginPass, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabelUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(textFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPasswordFieldLoginPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRegisterAccount)
                    .addComponent(jLabelForgotPassword))
                .addGap(18, 18, 18)
                .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabelUsername.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void jButtonLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonLoginMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLoginMouseClicked

    private void textFieldUsernameHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_textFieldUsernameHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUsernameHierarchyChanged

    private void textFieldUsernameInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_textFieldUsernameInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUsernameInputMethodTextChanged

    /**
     * @param args the command line arguments
     */
    
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new LoginView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JLabel jLabelForgotPassword;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelRegisterAccount;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPasswordField jPasswordFieldLoginPass;
    private java.awt.TextField textFieldUsername;
    // End of variables declaration//GEN-END:variables
}
