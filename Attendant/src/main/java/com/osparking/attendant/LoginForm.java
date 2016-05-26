/* 
 * Copyright (C) 2015 Open Source Parking Inc.(www.osparking.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.osparking.attendant;

import static com.osparking.global.names.ControlEnums.ButtonTypes.*;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.UIManager;
import javax.swing.event.EventListenerList;
import static com.osparking.global.names.DB_Access.readSettings;
import com.osparking.global.Globals;
import static com.osparking.global.Globals.checkOptions;
import static com.osparking.global.Globals.font_Size;
import static com.osparking.global.Globals.font_Style;
import static com.osparking.global.Globals.font_Type;
import static com.osparking.global.Globals.OSPiconList;
import static com.osparking.global.Globals.initializeLoggers;
import static com.osparking.global.Globals.language;
import static com.osparking.global.Globals.logParkingException;
import static com.osparking.global.names.ControlEnums.DialogMSGTypes.INPUT_ID_DIALOG;
import static com.osparking.global.names.ControlEnums.DialogMSGTypes.INPUT_PW_DIALOG;
import static com.osparking.global.names.ControlEnums.DialogMSGTypes.LOGIN_WRONG_DIALOG;
import static com.osparking.global.names.ControlEnums.DialogTitleTypes.WARING_DIALOGTITLE;
import static com.osparking.global.names.ControlEnums.LabelContent.LOGIN_ID_LABEL;
import static com.osparking.global.names.ControlEnums.LabelContent.PW_LABEL;
import static com.osparking.global.names.ControlEnums.TitleTypes.LOGIN_FRAME_TITLE;
import com.osparking.global.names.JDBCMySQL;

/**
 *
 * @author Open Source Parking Inc.
 */
public class LoginForm extends JFrame {
    EventListenerList loginListeners = new EventListenerList();
    private static Logger logException = null;
    private static Logger logOperation = null;
    
    /**
     * Creates new form ManagerForm
     */
    public LoginForm() {
        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
        initComponents();
        setIconImages(OSPiconList);
        
        // delete this line and following 2 more lines
        this.getRootPane().setDefaultButton(loginButton);
        loginButton.requestFocus();
    }
    
    public void addLoginEventListener(LoginEventListener listener) {
        loginListeners.add(LoginEventListener.class, listener);
    }

    public void removeMyEventListener(LoginEventListener listener) {
        loginListeners.remove(LoginEventListener.class, listener);
    }
    
    void fireLoginEvent(LoginWindowEvent evt) {
        Object[] listeners = loginListeners.getListenerList();
        for (int i = 0; i < listeners.length; i = i+2) {
            if (listeners[i] == LoginEventListener.class) {
                ((LoginEventListener) listeners[i+1]).loginEventOccurred(evt);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        loginDialog = new javax.swing.JDialog();
        userIDText = new javax.swing.JTextField();
        ID_Label = new javax.swing.JLabel();
        PW_Label = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();

        javax.swing.GroupLayout loginDialogLayout = new javax.swing.GroupLayout(loginDialog.getContentPane());
        loginDialog.getContentPane().setLayout(loginDialogLayout);
        loginDialogLayout.setHorizontalGroup(
            loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        loginDialogLayout.setVerticalGroup(
            loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(LOGIN_FRAME_TITLE.getContent());
        setFocusTraversalPolicyProvider(true);
        setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        userIDText.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        userIDText.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(userIDText, gridBagConstraints);

        ID_Label.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        ID_Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ID_Label.setText(LOGIN_ID_LABEL.getContent());
        ID_Label.setMaximumSize(new java.awt.Dimension(70, 16));
        ID_Label.setPreferredSize(new java.awt.Dimension(80, 30));
        ID_Label.setRequestFocusEnabled(false);
        ID_Label.setVerifyInputWhenFocusTarget(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(ID_Label, gridBagConstraints);

        PW_Label.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        PW_Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PW_Label.setText(PW_LABEL.getContent());
        PW_Label.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(PW_Label, gridBagConstraints);

        loginButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        loginButton.setMnemonic('L');
        loginButton.setText(LOGIN_BTN.getContent());
        loginButton.setPreferredSize(new java.awt.Dimension(100, 35));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        loginButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loginButtonKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(loginButton, gridBagConstraints);

        closeButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        closeButton.setMnemonic('C');
        closeButton.setText(CLOSE_BTN.getContent());
        closeButton.setPreferredSize(new java.awt.Dimension(90, 35));
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        closeButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                closeButtonKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(closeButton, gridBagConstraints);

        password.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        password.setPreferredSize(new java.awt.Dimension(80, 30));
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(password, gridBagConstraints);

        setSize(new java.awt.Dimension(352, 215));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        try {
            handleLoginAttempt();
        } catch (Exception ex) {
            logParkingException(Level.SEVERE, ex, "(attendant : login");
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    public void handleLoginAttempt()
    {
        //<editor-fold defaultstate="collapsed" desc="-- Request ID and PW textboxes be filled">
        // Check if both user ID and password were entered.
        if (getUserIDText().getText().length() == 0)            
        {
            showMessageDialog(null, INPUT_ID_DIALOG.getContent());
            return;
        } else if (getPassword().getPassword().length== 0) 
        {
            showMessageDialog(null, INPUT_PW_DIALOG.getContent());
            return;            
        }        
        //</editor-fold>
        
        boolean checkGood = loginCheckGood(getUserIDText().getText(), new String(getPassword().getPassword()));

        if (checkGood) {
            dispose();
        } else {
            showMessageDialog(null, LOGIN_WRONG_DIALOG.ordinal(),
                    WARING_DIALOGTITLE.getContent(), 
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public boolean loginCheckGood(String userID, String passwd) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;    
        ResultSet rs = null;
        try 
        {
            // Check if ID exists and password matches
            conn = JDBCMySQL.getConnection();
            pstmt = conn.prepareStatement("Select md5(?) as hashedPW, " + 
                    "password as pwInDB, isManager " + 
                    "from users_osp where id = ?");
            pstmt.setString(1, passwd);
            pstmt.setString(2, userID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                //<editor-fold defaultstate="collapsed" desc="-- For the existing ID, check if password matches.">
                String hashedPW = rs.getString("hashedPW");                               
                String pwInDB = rs.getString("pwInDB");                               
                int isManager = rs.getInt("isManager");  
                if (pwInDB.equals(hashedPW)) {
                    LoginWindowEvent loginEvent = new LoginWindowEvent
                        (this, 0, userID, hashedPW, (isManager == 1 ? true : false));
                    fireLoginEvent(loginEvent);
                    result = true;
                }
                //</editor-fold>                
            }
        } catch(Exception e) {
            logParkingException(Level.SEVERE, e, "(userID: " + userID + ")");
        } finally {
            Globals.closeDBstuff(conn, pstmt, rs, "(finally-userID: " + userID + ")");
        }
        return result;
    }    

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed
    
    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            handleLoginAttempt();
        }
    }//GEN-LAST:event_passwordKeyPressed

    private void loginButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginButtonKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            handleLoginAttempt();
        }
    }//GEN-LAST:event_loginButtonKeyPressed

    private void closeButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_closeButtonKeyPressed
        this.dispose();
    }//GEN-LAST:event_closeButtonKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }      
        } catch (ClassNotFoundException ex) {
            logParkingException(Level.SEVERE, ex, "(main-ClassNotFoundException)");
        } catch (InstantiationException ex) {
            logParkingException(Level.SEVERE, ex, "(main-InstantiationException)");
        } catch (IllegalAccessException ex) {
            logParkingException(Level.SEVERE, ex, "(main-IllegalAccessException)");
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            logParkingException(Level.SEVERE, ex, "(main-UnsupportedLookAndFeelException)");
        }        
        //</editor-fold>

        initializeLoggers();
        checkOptions(args);
        readSettings();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Following line makes "Enter" key raise focused button performed event 
                UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
                new LoginForm().setVisible(true);  
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ID_Label;
    private javax.swing.JLabel PW_Label;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton loginButton;
    private javax.swing.JDialog loginDialog;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField userIDText;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the password
     */
    public javax.swing.JPasswordField getPassword() {
        return password;
    }

    /**
     * @return the userIDText
     */
    public javax.swing.JTextField getUserIDText() {
        return userIDText;
    }
}
