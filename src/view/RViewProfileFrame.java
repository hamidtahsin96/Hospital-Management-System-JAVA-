package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class RViewProfileFrame extends JFrame implements ActionListener
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

    private JButton backBtn;

    private JPanel panel;

    private User u;

    public RViewProfileFrame(User u)
    {
        super("Receptionist Profile");

        this.u = u;

        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.receptionistIdLabel = new JLabel("Receptionist ID:");

        this.receptionistIdLabel.setBounds(60, 40, 130, 30);

        this.panel.add(receptionistIdLabel);

        this.receptionistIdTF = new JTextField();

        this.receptionistIdTF.setBounds(200, 40, 200, 30);

        this.receptionistIdTF.setEditable(false);

        this.panel.add(receptionistIdTF);

        this.nameLabel = new JLabel("Name:");

        this.nameLabel.setBounds(60, 80, 130, 30);

        this.panel.add(nameLabel);

        this.nameTF = new JTextField();

        this.nameTF.setBounds(200, 80, 200, 30);

        this.nameTF.setEditable(false);

        this.panel.add(nameTF);

        this.emailLabel = new JLabel("Email:");

        this.emailLabel.setBounds(60, 120, 130, 30);

        this.panel.add(emailLabel);

        this.emailTF = new JTextField();

        this.emailTF.setBounds(200, 120, 200, 30);

        this.emailTF.setEditable(false);

        this.panel.add(emailTF);

        this.contactLabel = new JLabel("Contact No:");

        this.contactLabel.setBounds(60, 160, 130, 30);

        this.panel.add(contactLabel);

        this.contactTF = new JTextField();

        this.contactTF.setBounds(200, 160, 200, 30);

        this.contactTF.setEditable(false);

        this.panel.add(contactTF);

        this.shiftLabel = new JLabel("Shift:");

        this.shiftLabel.setBounds(60, 200, 130, 30);

        this.panel.add(shiftLabel);

        this.shiftTF = new JTextField();

        this.shiftTF.setBounds(200, 200, 200, 30);

        this.shiftTF.setEditable(false);

        this.panel.add(shiftTF);

        this.deskNumberLabel = new JLabel("Desk Number:");

        this.deskNumberLabel.setBounds(60, 240, 130, 30);

        this.panel.add(deskNumberLabel);

        this.deskNumberTF = new JTextField();

        this.deskNumberTF.setBounds(200, 240, 200, 30);

        this.deskNumberTF.setEditable(false);

        this.panel.add(deskNumberTF);

        this.backBtn = new JButton("Back");

        this.backBtn.setBounds(200, 320, 100, 30);

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

        if(command.equals(backBtn.getText()))
        {
            ReceptionistHomeFrame rhf = new ReceptionistHomeFrame(this.u);

            this.setVisible(false);
            rhf.setVisible(true);
        }
    }
}