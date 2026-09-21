package view;

import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class LoginFrame extends JFrame implements ActionListener
{
    private JLabel userIdLabel, passwordLabel;
    private JTextField userIdTF;
    private JPasswordField passwordPF;
    private JButton loginBtn, exitBtn, registrationBtn;
    private JPanel panel;

    public LoginFrame()
    {
        super("Login Frame");

        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.userIdLabel = new JLabel("User Id:");
        this.userIdLabel.setBounds(100, 50, 80, 20);
        this.panel.add(userIdLabel);

        this.userIdTF = new JTextField();
        this.userIdTF.setBounds(100, 75, 150, 30);
        this.panel.add(userIdTF);

        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setBounds(100, 115, 80, 20);
        this.panel.add(passwordLabel);

        this.passwordPF = new JPasswordField();
        this.passwordPF.setBounds(100, 140, 150, 30);
        this.panel.add(passwordPF);

        this.loginBtn = new JButton("Login");
        this.loginBtn.setBounds(100, 190, 80, 30);
        this.loginBtn.addActionListener(this);
        this.panel.add(loginBtn);

        this.exitBtn = new JButton("Exit");
        this.exitBtn.setBounds(190, 190, 60, 30);
        this.exitBtn.addActionListener(this);
        this.panel.add(exitBtn);

        this.registrationBtn = new JButton("Registration");
        this.registrationBtn.setBounds(100, 230, 150, 30);
        this.registrationBtn.addActionListener(this);
        this.panel.add(registrationBtn);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(loginBtn.getText()))
        {
            if(!userIdTF.getText().isEmpty() &&
                    !passwordPF.getText().isEmpty())
            {
                String userIdValue = userIdTF.getText();
                String passwordValue = passwordPF.getText();

                UserController uc = new UserController();
                User u = uc.searchUser(userIdValue);

                if(u != null)
                {
                    if(u.getPassword().equals(passwordValue))
                    {
                        if(u.getRole() == User.ADMIN)
                        {
                            AdminHomeFrame ahf = new AdminHomeFrame(u);
                            this.setVisible(false);
                            ahf.setVisible(true);
                        }

                        else if(u.getRole() == User.DOCTOR)
                        {
                            DoctorHomeFrame dhf = new DoctorHomeFrame(u);
                            this.setVisible(false);
                            dhf.setVisible(true);
                        }

                        else if(u.getRole() == User.PATIENT)
                        {
                            PatientHomeFrame phf = new PatientHomeFrame(u);
                            this.setVisible(false);
                            phf.setVisible(true);
                        }

                        else if(u.getRole() == User.NURSE)
                        {
                            NurseHomeFrame nhf = new NurseHomeFrame(u);
                            this.setVisible(false);
                            nhf.setVisible(true);
                        }

                        else if(u.getRole() == User.RECEPTIONIST)
                        {
                            ReceptionistHomeFrame rhf = new ReceptionistHomeFrame(u);
                            this.setVisible(false);
                            rhf.setVisible(true);
                        }
                    }

                    else
                    {
                        JOptionPane.showMessageDialog(this, "Wrong password");
                    }
                }

                else
                {
                    JOptionPane.showMessageDialog(this, "Wrong User Id");
                }
            }

            else
            {
                JOptionPane.showMessageDialog(this, "Please Fill Up All the Field");
            }
        }

        if(command.equals(registrationBtn.getText()))
        {
            RegistrationFrame rf = new RegistrationFrame();

            this.setVisible(false);
            rf.setVisible(true);
        }

        if(command.equals(exitBtn.getText()))
        {
            System.exit(0);
        }
    }
}