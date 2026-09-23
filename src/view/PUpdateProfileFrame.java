package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class PUpdateProfileFrame extends JFrame implements ActionListener
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

    private JButton updateBtn;
    private JButton backBtn;

    private JPanel panel;

    private User u;

    public PUpdateProfileFrame(User u)
    {
        super("Update Patient Profile");

        this.u = u;

        this.setSize(550, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.patientIdLabel = new JLabel("Patient ID:");
        this.patientIdLabel.setBounds(70, 40, 130, 30);
        this.panel.add(patientIdLabel);

        this.patientIdTF = new JTextField();
        this.patientIdTF.setBounds(210, 40, 220, 30);
        this.patientIdTF.setEditable(false);
        this.panel.add(patientIdTF);

        this.nameLabel = new JLabel("Name:");
        this.nameLabel.setBounds(70, 80, 130, 30);
        this.panel.add(nameLabel);

        this.nameTF = new JTextField();
        this.nameTF.setBounds(210, 80, 220, 30);
        this.nameTF.setEditable(false);
        this.panel.add(nameTF);

        this.emailLabel = new JLabel("Email:");
        this.emailLabel.setBounds(70, 120, 130, 30);
        this.panel.add(emailLabel);

        this.emailTF = new JTextField();
        this.emailTF.setBounds(210, 120, 220, 30);
        this.panel.add(emailTF);

        this.contactLabel = new JLabel("Contact No:");
        this.contactLabel.setBounds(70, 160, 130, 30);
        this.panel.add(contactLabel);

        this.contactTF = new JTextField();
        this.contactTF.setBounds(210, 160, 220, 30);
        this.panel.add(contactTF);

        this.genderLabel = new JLabel("Gender:");
        this.genderLabel.setBounds(70, 200, 130, 30);
        this.panel.add(genderLabel);

        this.genderTF = new JTextField();
        this.genderTF.setBounds(210, 200, 220, 30);
        this.panel.add(genderTF);

        this.ageLabel = new JLabel("Age:");
        this.ageLabel.setBounds(70, 240, 130, 30);
        this.panel.add(ageLabel);

        this.ageTF = new JTextField();
        this.ageTF.setBounds(210, 240, 220, 30);
        this.panel.add(ageTF);

        this.addressLabel = new JLabel("Address:");
        this.addressLabel.setBounds(70, 280, 130, 30);
        this.panel.add(addressLabel);

        this.addressTF = new JTextField();
        this.addressTF.setBounds(210, 280, 220, 30);
        this.panel.add(addressTF);

        this.updateBtn = new JButton("Update");
        this.updateBtn.setBounds(150, 370, 100, 30);
        this.updateBtn.addActionListener(this);
        this.panel.add(updateBtn);

        this.backBtn = new JButton("Back");
        this.backBtn.setBounds(280, 370, 100, 30);
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

        if(command.equals(updateBtn.getText()))
        {
            if(!emailTF.getText().isEmpty() &&
                    !contactTF.getText().isEmpty() &&
                    !genderTF.getText().isEmpty() &&
                    !ageTF.getText().isEmpty() &&
                    !addressTF.getText().isEmpty())
            {
                PatientController pc = new PatientController();

                UserController uc = new UserController();

                Patient p = pc.searchPatient(this.u.getUserId());

                if(p != null)
                {
                    p.setEmail(emailTF.getText());
                    p.setContactNo(contactTF.getText());
                    p.setGender(genderTF.getText());
                    p.setAge(Integer.parseInt(ageTF.getText()));
                    p.setAddress(addressTF.getText());

                    pc.updatePatient(p);

                    this.u.setEmail(emailTF.getText());
                    this.u.setContactNo(contactTF.getText());

                    uc.updateUser(this.u);

                    JOptionPane.showMessageDialog(this, "Profile Updated Successfully");

                    PatientHomeFrame phf = new PatientHomeFrame(this.u);

                    this.setVisible(false);
                    phf.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Patient Profile Not Found");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Fill Up All The Field Properly");
            }
        }

        else if(command.equals(backBtn.getText()))
        {
            PatientHomeFrame phf = new PatientHomeFrame(this.u);

            this.setVisible(false);
            phf.setVisible(true);
        }
    }
}