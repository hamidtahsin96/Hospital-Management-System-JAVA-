package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class RUpdateProfileFrame extends JFrame implements ActionListener
{
    private JLabel receptionistIdLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel contactLabel;
    private JLabel shiftLabel;
    private JLabel deskNumberLabel;

    private JTextField receptionistIdTF;
    private JTextField nameTF;
    private JTextField emailTF;
    private JTextField contactTF;
    private JTextField shiftTF;
    private JTextField deskNumberTF;

    private JButton updateBtn;
    private JButton backBtn;

    private JPanel panel;

    private User u;

    public RUpdateProfileFrame(User u)
    {
        super("Update Receptionist Profile");

        this.u = u;

        this.setSize(550, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.receptionistIdLabel = new JLabel("Receptionist ID:");

        this.receptionistIdLabel.setBounds(60, 40, 130, 30);

        this.panel.add(receptionistIdLabel);

        this.receptionistIdTF = new JTextField();

        this.receptionistIdTF.setBounds(200, 40, 220, 30);

        this.receptionistIdTF.setEditable(false);

        this.panel.add(receptionistIdTF);

        this.nameLabel = new JLabel("Name:");

        this.nameLabel.setBounds(60, 80, 130, 30);

        this.panel.add(nameLabel);

        this.nameTF = new JTextField();

        this.nameTF.setBounds(200, 80, 220, 30);

        this.nameTF.setEditable(false);

        this.panel.add(nameTF);

        this.emailLabel = new JLabel("Email:");

        this.emailLabel.setBounds(60, 120, 130, 30);

        this.panel.add(emailLabel);

        this.emailTF = new JTextField();

        this.emailTF.setBounds(200, 120, 220, 30);

        this.panel.add(emailTF);

        this.contactLabel = new JLabel("Contact No:");

        this.contactLabel.setBounds(60, 160, 130, 30);

        this.panel.add(contactLabel);

        this.contactTF = new JTextField();

        this.contactTF.setBounds(200, 160, 220, 30);

        this.panel.add(contactTF);

        this.shiftLabel = new JLabel("Shift:");

        this.shiftLabel.setBounds(60, 200, 130, 30);

        this.panel.add(shiftLabel);

        this.shiftTF = new JTextField();

        this.shiftTF.setBounds(200, 200, 220, 30);

        this.panel.add(shiftTF);

        this.deskNumberLabel = new JLabel("Desk Number:");

        this.deskNumberLabel.setBounds(60, 240, 130, 30);

        this.panel.add(deskNumberLabel);

        this.deskNumberTF = new JTextField();

        this.deskNumberTF.setBounds(200, 240, 220, 30);

        this.panel.add(deskNumberTF);

        this.updateBtn = new JButton("Update");

        this.updateBtn.setBounds(150, 330, 100, 30);

        this.updateBtn.addActionListener(this);

        this.panel.add(updateBtn);

        this.backBtn = new JButton("Back");

        this.backBtn.setBounds(280, 330, 100, 30);

        this.backBtn.addActionListener(this);

        this.panel.add(backBtn);

        this.showProfile();

        this.add(panel);
    }

    public void showProfile()
    {
        ReceptionistController rc = new ReceptionistController();

        Receptionist r = rc.searchReceptionist(this.u.getUserId());

        if(r != null)
        {
            this.receptionistIdTF.setText(r.getUserId());

            this.nameTF.setText(r.getName());

            this.emailTF.setText(r.getEmail());

            this.contactTF.setText(r.getContactNo());

            this.shiftTF.setText(r.getShift());

            this.deskNumberTF.setText(String.valueOf(r.getDeskNumber()));
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Receptionist Profile Not Found");
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(updateBtn.getText()))
        {
            if(emailTF.getText().isEmpty() ||
                    contactTF.getText().isEmpty() ||
                    shiftTF.getText().isEmpty() ||
                    deskNumberTF.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please Fill All Fields");
            }
            else
            {
                try
                {
                    int deskNumber = Integer.parseInt(deskNumberTF.getText());

                    ReceptionistController rc = new ReceptionistController();

                    Receptionist r = rc.searchReceptionist(this.u.getUserId());

                    if(r != null)
                    {
                        r.setEmail(emailTF.getText());

                        r.setContactNo(contactTF.getText());

                        r.setShift(shiftTF.getText());

                        r.setDeskNumber(deskNumberTF.getText());

                        rc.updateReceptionist(r);

                        UserController uc = new UserController();

                        User user = uc.searchUser(this.u.getUserId());

                        if(user != null)
                        {
                            user.setEmail(emailTF.getText());

                            user.setContactNo(contactTF.getText());

                            uc.updateUser(user);
                        }

                        JOptionPane.showMessageDialog(this, "Profile Updated Successfully");

                        ReceptionistHomeFrame rhf = new ReceptionistHomeFrame(this.u);

                        this.setVisible(false);
                        rhf.setVisible(true);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Receptionist Does Not Exist");
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(this, "Desk Number Must Be a Number");
                }
            }
        }

        else if(command.equals(backBtn.getText()))
        {
            ReceptionistHomeFrame rhf = new ReceptionistHomeFrame(this.u);

            this.setVisible(false);
            rhf.setVisible(true);
        }
    }
}