package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class NViewProfileFrame extends JFrame implements ActionListener
{
    private JLabel nurseIdLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel contactLabel;
    private JLabel shiftLabel;
    private JLabel departmentLabel;

    private JTextField nurseIdTF;
    private JTextField nameTF;
    private JTextField emailTF;
    private JTextField contactTF;
    private JTextField shiftTF;
    private JTextField departmentTF;

    private JButton backBtn;

    private JPanel panel;

    private User u;

    public NViewProfileFrame(User u)
    {
        super("Nurse Profile");

        this.u = u;

        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.nurseIdLabel = new JLabel("Nurse ID:");

        this.nurseIdLabel.setBounds(80, 40, 120, 30);

        this.panel.add(nurseIdLabel);

        this.nurseIdTF = new JTextField();

        this.nurseIdTF.setBounds(200, 40, 200, 30);

        this.nurseIdTF.setEditable(false);

        this.panel.add(nurseIdTF);

        this.nameLabel = new JLabel("Name:");

        this.nameLabel.setBounds(80, 80, 120, 30);

        this.panel.add(nameLabel);

        this.nameTF = new JTextField();

        this.nameTF.setBounds(200, 80, 200, 30);

        this.nameTF.setEditable(false);

        this.panel.add(nameTF);

        this.emailLabel = new JLabel("Email:");

        this.emailLabel.setBounds(80, 120, 120, 30);

        this.panel.add(emailLabel);

        this.emailTF = new JTextField();

        this.emailTF.setBounds(200, 120, 200, 30);

        this.emailTF.setEditable(false);

        this.panel.add(emailTF);

        this.contactLabel = new JLabel("Contact No:");

        this.contactLabel.setBounds(80, 160, 120, 30);

        this.panel.add(contactLabel);

        this.contactTF = new JTextField();

        this.contactTF.setBounds(200, 160, 200, 30);

        this.contactTF.setEditable(false);

        this.panel.add(contactTF);

        this.shiftLabel = new JLabel("Shift:");

        this.shiftLabel.setBounds(80, 200, 120, 30);

        this.panel.add(shiftLabel);

        this.shiftTF = new JTextField();

        this.shiftTF.setBounds(200, 200, 200, 30);

        this.shiftTF.setEditable(false);

        this.panel.add(shiftTF);

        this.departmentLabel = new JLabel("Department:");

        this.departmentLabel.setBounds(80, 240, 120, 30);

        this.panel.add(departmentLabel);

        this.departmentTF = new JTextField();

        this.departmentTF.setBounds(200, 240, 200, 30);

        this.departmentTF.setEditable(false);

        this.panel.add(departmentTF);

        this.backBtn = new JButton("Back");

        this.backBtn.setBounds(200, 320, 100, 30);

        this.backBtn.addActionListener(this);

        this.panel.add(backBtn);

        this.showProfile();

        this.add(panel);
    }

    public void showProfile()
    {
        NurseController nc = new NurseController();

        Nurse n = nc.searchNurse(this.u.getUserId());

        if(n != null)
        {
            this.nurseIdTF.setText(n.getUserId());

            this.nameTF.setText(n.getName());

            this.emailTF.setText(n.getEmail());

            this.contactTF.setText(n.getContactNo());

            this.shiftTF.setText(n.getShift());

            this.departmentTF.setText(n.getDepartment());
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Nurse Profile Not Found");
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(backBtn.getText()))
        {
            NurseHomeFrame nhf = new NurseHomeFrame(this.u);

            this.setVisible(false);
            nhf.setVisible(true);
        }
    }
}