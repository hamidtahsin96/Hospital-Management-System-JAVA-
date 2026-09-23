package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class PViewProfileFrame extends JFrame implements ActionListener
{
    private JLabel patientIdLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel contactLabel;
    private JLabel genderLabel;
    private JLabel ageLabel;
    private JLabel addressLabel;

    private JTextField patientIdTF;
    private JTextField nameTF;
    private JTextField emailTF;
    private JTextField contactTF;
    private JTextField genderTF;
    private JTextField ageTF;
    private JTextField addressTF;

    private JButton backBtn;

    private JPanel panel;

    private User u;

    public PViewProfileFrame(User u)
    {
        super("Patient Profile");

        this.u = u;

        this.setSize(500, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.patientIdLabel = new JLabel("Patient ID:");
        this.patientIdLabel.setBounds(80, 40, 120, 30);
        this.panel.add(patientIdLabel);

        this.patientIdTF = new JTextField();
        this.patientIdTF.setBounds(200, 40, 200, 30);
        this.patientIdTF.setEditable(false);
        this.panel.add(patientIdTF);

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

        this.genderLabel = new JLabel("Gender:");
        this.genderLabel.setBounds(80, 200, 120, 30);
        this.panel.add(genderLabel);

        this.genderTF = new JTextField();
        this.genderTF.setBounds(200, 200, 200, 30);
        this.genderTF.setEditable(false);
        this.panel.add(genderTF);

        this.ageLabel = new JLabel("Age:");
        this.ageLabel.setBounds(80, 240, 120, 30);
        this.panel.add(ageLabel);

        this.ageTF = new JTextField();
        this.ageTF.setBounds(200, 240, 200, 30);
        this.ageTF.setEditable(false);
        this.panel.add(ageTF);

        this.addressLabel = new JLabel("Address:");
        this.addressLabel.setBounds(80, 280, 120, 30);
        this.panel.add(addressLabel);

        this.addressTF = new JTextField();
        this.addressTF.setBounds(200, 280, 200, 30);
        this.addressTF.setEditable(false);
        this.panel.add(addressTF);

        this.backBtn = new JButton("Back");
        this.backBtn.setBounds(200, 360, 100, 30);
        this.backBtn.addActionListener(this);
        this.panel.add(backBtn);

        this.showProfile();

        this.add(panel);
    }

    public void showProfile()
    {
        PatientController pc = new PatientController();

        Patient p = pc.searchPatient(this.u.getUserId());

        if(p != null)
        {
            this.patientIdTF.setText(p.getUserId());
            this.nameTF.setText(p.getName());
            this.emailTF.setText(p.getEmail());
            this.contactTF.setText(p.getContactNo());
            this.genderTF.setText(p.getGender());
            this.ageTF.setText(String.valueOf(p.getAge()));
            this.addressTF.setText(p.getAddress());
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Patient Profile Not Found");
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(backBtn.getText()))
        {
            PatientHomeFrame phf = new PatientHomeFrame(this.u);

            this.setVisible(false);
            phf.setVisible(true);
        }
    }
}