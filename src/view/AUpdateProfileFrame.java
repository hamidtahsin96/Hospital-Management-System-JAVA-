package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class AUpdateProfileFrame extends JFrame implements ActionListener
{
    private JLabel userIdLabel, nameLabel, emailLabel, contactLabel, adminTypeLabel;

    private JTextField userIdTF, nameTF, emailTF, contactTF, adminTypeTF;

    private JButton updateBtn, backBtn;

    private JPanel panel;

    private User u;
    private Admin a;

    public AUpdateProfileFrame(User u)
    {
        super("Update Profile Frame");

        this.u = u;

        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        AdminController ac = new AdminController();

        this.a = ac.searchAdmin(u.getUserId());

        this.userIdLabel = new JLabel("User ID:");
        this.userIdLabel.setBounds(80, 80, 80, 30);
        this.panel.add(userIdLabel);

        this.userIdTF = new JTextField();
        this.userIdTF.setBounds(170, 80, 180, 30);
        this.userIdTF.setText(a.getUserId());
        this.userIdTF.setEditable(false);
        this.panel.add(userIdTF);

        this.nameLabel = new JLabel("Name:");
        this.nameLabel.setBounds(80, 130, 80, 30);
        this.panel.add(nameLabel);

        this.nameTF = new JTextField();
        this.nameTF.setBounds(170, 130, 180, 30);
        this.nameTF.setText(a.getName());
        this.nameTF.setEditable(false);
        this.panel.add(nameTF);

        this.emailLabel = new JLabel("Email:");
        this.emailLabel.setBounds(80, 180, 80, 30);
        this.panel.add(emailLabel);

        this.emailTF = new JTextField();
        this.emailTF.setBounds(170, 180, 180, 30);
        this.emailTF.setText(a.getEmail());
        this.panel.add(emailTF);

        this.contactLabel = new JLabel("Contact No:");
        this.contactLabel.setBounds(80, 230, 80, 30);
        this.panel.add(contactLabel);

        this.contactTF = new JTextField();
        this.contactTF.setBounds(170, 230, 180, 30);
        this.contactTF.setText(a.getContactNo());
        this.panel.add(contactTF);

        this.adminTypeLabel = new JLabel("Admin Type:");
        this.adminTypeLabel.setBounds(80, 280, 80, 30);
        this.panel.add(adminTypeLabel);

        this.adminTypeTF = new JTextField();
        this.adminTypeTF.setBounds(170, 280, 180, 30);
        this.adminTypeTF.setText(a.getAdminType());
        this.adminTypeTF.setEditable(false);
        this.panel.add(adminTypeTF);

        this.updateBtn = new JButton("Update");
        this.updateBtn.setBounds(80, 350, 120, 30);
        this.updateBtn.addActionListener(this);
        this.panel.add(updateBtn);

        this.backBtn = new JButton("Back");
        this.backBtn.setBounds(230, 350, 120, 30);
        this.backBtn.addActionListener(this);
        this.panel.add(backBtn);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(updateBtn.getText()))
        {
            if(!emailTF.getText().isEmpty() && !contactTF.getText().isEmpty())
            {
                a.setEmail(emailTF.getText());
                a.setContactNo(contactTF.getText());

                AdminController ac = new AdminController();

                UserController uc = new UserController();

                ac.updateAdmin(a);

                u.setEmail(emailTF.getText());
                u.setContactNo(contactTF.getText());

                uc.updateUser(u);

                JOptionPane.showMessageDialog(this, "Admin Updated Successfully");

                AdminHomeFrame ahf = new AdminHomeFrame(u);

                this.setVisible(false);
                ahf.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Fill Up All The Field Properly");
            }
        }

        else if(command.equals(backBtn.getText()))
        {
            AdminHomeFrame ahf = new AdminHomeFrame(this.u);

            this.setVisible(false);
            ahf.setVisible(true);
        }
    }
}