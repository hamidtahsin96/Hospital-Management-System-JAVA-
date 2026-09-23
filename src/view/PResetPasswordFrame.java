package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class PResetPasswordFrame extends JFrame implements ActionListener
{
    private JLabel currentPasswordLabel;
    private JLabel newPasswordLabel;
    private JLabel retypeNewPasswordLabel;

    private JPasswordField currentPasswordPF;
    private JPasswordField newPasswordPF;
    private JPasswordField retypeNewPasswordPF;

    private JButton changePasswordBtn;
    private JButton backBtn;

    private JPanel panel;

    private User u1;

    public PResetPasswordFrame(User u)
    {
        super("Reset Password Frame");

        this.u1 = u;

        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.currentPasswordLabel = new JLabel("Current Password:");

        this.currentPasswordLabel.setBounds(100, 50, 150, 20);

        this.panel.add(currentPasswordLabel);

        this.currentPasswordPF = new JPasswordField();

        this.currentPasswordPF.setBounds(100, 75, 200, 30);

        this.panel.add(currentPasswordPF);

        this.newPasswordLabel = new JLabel("New Password:");

        this.newPasswordLabel.setBounds(100, 115, 150, 20);

        this.panel.add(newPasswordLabel);

        this.newPasswordPF = new JPasswordField();

        this.newPasswordPF.setBounds(100, 140, 200, 30);

        this.panel.add(newPasswordPF);

        this.retypeNewPasswordLabel = new JLabel("Retype New Password:");

        this.retypeNewPasswordLabel.setBounds(100, 180, 150, 20);

        this.panel.add(retypeNewPasswordLabel);

        this.retypeNewPasswordPF = new JPasswordField();

        this.retypeNewPasswordPF.setBounds(100, 205, 200, 30);

        this.panel.add(retypeNewPasswordPF);

        this.changePasswordBtn = new JButton("Change Password");

        this.changePasswordBtn.setBounds(80, 300, 180, 30);

        this.changePasswordBtn.addActionListener(this);

        this.panel.add(changePasswordBtn);

        this.backBtn = new JButton("Back");

        this.backBtn.setBounds(280, 300, 100, 30);

        this.backBtn.addActionListener(this);

        this.panel.add(backBtn);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(changePasswordBtn.getText()))
        {
            if(!currentPasswordPF.getText().isEmpty() && !newPasswordPF.getText().isEmpty() && !retypeNewPasswordPF.getText().isEmpty())
            {
                UserController uc = new UserController();

                if(u1 != null)
                {
                    String currentPasswordValue = currentPasswordPF.getText();

                    String newPasswordValue = newPasswordPF.getText();

                    String retypeNewPasswordValue = retypeNewPasswordPF.getText();

                    if(u1.getPassword().equals(currentPasswordValue))
                    {
                        if(retypeNewPasswordValue.equals(newPasswordValue))
                        {
                            u1.setPassword(newPasswordValue);

                            uc.updateUser(u1);

                            JOptionPane.showMessageDialog(this, "Password Changed Successfully");

                            PatientHomeFrame phf = new PatientHomeFrame(u1);

                            this.setVisible(false);
                            phf.setVisible(true);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "Retype New Password Does Not Match With New Password");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Current Password is Wrong");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Patient Does Not Exist");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Fill Up All The Field Properly");
            }
        }

        else if(command.equals(backBtn.getText()))
        {
            PatientHomeFrame phf = new PatientHomeFrame(this.u1);

            this.setVisible(false);
            phf.setVisible(true);
        }
    }
}