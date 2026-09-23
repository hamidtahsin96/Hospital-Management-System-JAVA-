package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class RPatientDetailsFrame extends JFrame implements ActionListener
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

    private JButton searchBtn;
    private JButton resetBtn;
    private JButton backBtn;

    private JPanel panel;

    private User u;

    public RPatientDetailsFrame(User u)
    {
        super("Patient Details");

        this.u = u;

        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.patientIdLabel = new JLabel("Patient ID:");

        this.patientIdLabel.setBounds(80, 50, 100, 30);

        this.panel.add(patientIdLabel);

        this.patientIdTF = new JTextField();

        this.patientIdTF.setBounds(190, 50, 220, 30);

        this.panel.add(patientIdTF);

        this.searchBtn = new JButton("Search");

        this.searchBtn.setBounds(420, 50, 100, 30);

        this.searchBtn.addActionListener(this);

        this.panel.add(searchBtn);

        this.nameLabel = new JLabel("Name:");

        this.nameLabel.setBounds(80, 110, 100, 30);

        this.panel.add(nameLabel);

        this.nameTF = new JTextField();

        this.nameTF.setBounds(190, 110, 220, 30);

        this.nameTF.setEditable(false);

        this.panel.add(nameTF);

        this.emailLabel = new JLabel("Email:");

        this.emailLabel.setBounds(80, 150, 100, 30);

        this.panel.add(emailLabel);

        this.emailTF = new JTextField();

        this.emailTF.setBounds(190, 150, 220, 30);

        this.emailTF.setEditable(false);

        this.panel.add(emailTF);

        this.contactLabel = new JLabel("Contact No:");

        this.contactLabel.setBounds(80, 190, 100, 30);

        this.panel.add(contactLabel);

        this.contactTF = new JTextField();

        this.contactTF.setBounds(190, 190, 220, 30);

        this.contactTF.setEditable(false);

        this.panel.add(contactTF);

        this.genderLabel = new JLabel("Gender:");

        this.genderLabel.setBounds(80, 230, 100, 30);

        this.panel.add(genderLabel);

        this.genderTF = new JTextField();

        this.genderTF.setBounds(190, 230, 220, 30);

        this.genderTF.setEditable(false);

        this.panel.add(genderTF);

        this.ageLabel = new JLabel("Age:");

        this.ageLabel.setBounds(80, 270, 100, 30);

        this.panel.add(ageLabel);

        this.ageTF = new JTextField();

        this.ageTF.setBounds(190, 270, 220, 30);

        this.ageTF.setEditable(false);

        this.panel.add(ageTF);

        this.addressLabel = new JLabel("Address:");

        this.addressLabel.setBounds(80, 310, 100, 30);

        this.panel.add(addressLabel);

        this.addressTF = new JTextField();

        this.addressTF.setBounds(190, 310, 220, 30);

        this.addressTF.setEditable(false);

        this.panel.add(addressTF);

        this.resetBtn = new JButton("Reset");

        this.resetBtn.setBounds(150, 380, 100, 30);

        this.resetBtn.addActionListener(this);

        this.panel.add(resetBtn);

        this.backBtn = new JButton("Back");

        this.backBtn.setBounds(280, 380, 100, 30);

        this.backBtn.addActionListener(this);

        this.panel.add(backBtn);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(searchBtn.getText()))
        {
            if(!patientIdTF.getText().isEmpty())
            {
                PatientController pc = new PatientController();

                Patient p = pc.searchPatient(patientIdTF.getText());

                if(p != null)
                {
                    this.nameTF.setText(p.getName());

                    this.emailTF.setText(p.getEmail());

                    this.contactTF.setText(p.getContactNo());

                    this.genderTF.setText(p.getGender());

                    this.ageTF.setText(String.valueOf(p.getAge()));

                    this.addressTF.setText(p.getAddress());
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Patient Does Not Exist");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Enter Patient ID");
            }
        }

        else if(command.equals(resetBtn.getText()))
        {
            this.patientIdTF.setText("");
            this.nameTF.setText("");
            this.emailTF.setText("");
            this.contactTF.setText("");
            this.genderTF.setText("");
            this.ageTF.setText("");
            this.addressTF.setText("");
        }

        else if(command.equals(backBtn.getText()))
        {
            ReceptionistHomeFrame rhf = new ReceptionistHomeFrame(this.u);

            this.setVisible(false);
            rhf.setVisible(true);
        }
    }
}