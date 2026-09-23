package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class NResetPasswordFrame extends JFrame implements ActionListener
{
    private JLabel currentPasswordLabel;
    private JLabel newPasswordLabel;
    private JLabel retypePasswordLabel;

    private JPasswordField currentPasswordPF;
    private JPasswordField newPasswordPF;
    private JPasswordField retypePasswordPF;

    private JButton changePasswordBtn;
    private JButton backBtn;

    private JPanel panel;

    private User u1;

    public NResetPasswordFrame(User u)
    {
        super("Reset Password");

        this.u1 = u;

        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.currentPasswordLabel = new JLabel("Current Password:");

        this.currentPasswordLabel.setBounds(50, 50, 150, 30);

        this.panel.add(currentPasswordLabel);

        this.currentPasswordPF = new JPasswordField();

        this.currentPasswordPF.setBounds(210, 50, 200, 30);

        this.panel.add(currentPasswordPF);

        this.newPasswordLabel = new JLabel("New Password:");

        this.newPasswordLabel.setBounds(50, 100, 150, 30);

        this.panel.add(newPasswordLabel);

        this.newPasswordPF = new JPasswordField();

        this.newPasswordPF.setBounds(210, 100, 200, 30);

        this.panel.add(newPasswordPF);

        this.retypePasswordLabel = new JLabel("Retype New Password:");

        this.retypePasswordLabel.setBounds(50, 150, 150, 30);

        this.panel.add(retypePasswordLabel);

        this.retypePasswordPF = new JPasswordField();

        this.retypePasswordPF.setBounds(210, 150, 200, 30);

        this.panel.add(retypePasswordPF);

        this.changePasswordBtn = new JButton("Change Password");

        this.changePasswordBtn.setBounds(100, 230, 150, 30);

        this.changePasswordBtn.addActionListener(this);

        this.panel.add(changePasswordBtn);

        this.backBtn = new JButton("Back");

        this.backBtn.setBounds(270, 230, 100, 30);

        this.backBtn.addActionListener(this);

        this.panel.add(backBtn);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(changePasswordBtn.getText()))
        {
            String currentPassword = new String(currentPasswordPF.getPassword());

            String newPassword = new String(newPasswordPF.getPassword());

            String retypePassword = new String(retypePasswordPF.getPassword());

            if(currentPassword.isEmpty() || newPassword.isEmpty() || retypePassword.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please Fill All Fields");
            }
            else if(!currentPassword.equals(this.u1.getPassword()))
            {
                JOptionPane.showMessageDialog(this, "Current Password is Incorrect");
            }
            else if(!newPassword.equals(retypePassword))
            {
                JOptionPane.showMessageDialog(this, "New Passwords Do Not Match");
            }
            else
            {
                UserController uc = new UserController();

                User user = uc.searchUser(this.u1.getUserId());

                if(user != null)
                {
                    user.setPassword(newPassword);

                    uc.updateUser(user);

                    this.u1.setPassword(newPassword);

                    JOptionPane.showMessageDialog(this, "Password Changed Successfully");

                    NurseHomeFrame nhf = new NurseHomeFrame(this.u1);

                    this.setVisible(false);
                    nhf.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Nurse Does Not Exist");
                }
            }
        }

        else if(command.equals(backBtn.getText()))
        {
            NurseHomeFrame nhf = new NurseHomeFrame(this.u1);

            this.setVisible(false);
            nhf.setVisible(true);
        }
    }
}