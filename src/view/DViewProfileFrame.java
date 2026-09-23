package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class DViewProfileFrame extends JFrame implements ActionListener
{
    private JLabel doctorIdLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel contactLabel;
    private JLabel specialistLabel;
    private JLabel educationLabel;

    private JTextField doctorIdTF;
    private JTextField nameTF;
    private JTextField emailTF;
    private JTextField contactTF;
    private JTextField specialistTF;
    private JTextField educationTF;

    private JButton backBtn;

    private JPanel panel;

    private User u;

    public DViewProfileFrame(User u)
    {
        super("Doctor Profile");

        this.u = u;

        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.doctorIdLabel = new JLabel("Doctor ID:");
        this.doctorIdLabel.setBounds(80, 40, 120, 30);
        this.panel.add(doctorIdLabel);

        this.doctorIdTF = new JTextField();
        this.doctorIdTF.setBounds(200, 40, 200, 30);
        this.doctorIdTF.setEditable(false);
        this.panel.add(doctorIdTF);

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

        this.specialistLabel = new JLabel("Specialist:");
        this.specialistLabel.setBounds(80, 200, 120, 30);
        this.panel.add(specialistLabel);

        this.specialistTF = new JTextField();
        this.specialistTF.setBounds(200, 200, 200, 30);
        this.specialistTF.setEditable(false);
        this.panel.add(specialistTF);

        this.educationLabel = new JLabel("Education:");

        this.educationLabel.setBounds(80, 240, 120, 30);
        this.panel.add(educationLabel);

        this.educationTF = new JTextField();
        this.educationTF.setBounds(200, 240, 200, 30);
        this.educationTF.setEditable(false);
        this.panel.add(educationTF);

        this.backBtn = new JButton("Back");
        this.backBtn.setBounds(200, 320, 100, 30);
        this.backBtn.addActionListener(this);
        this.panel.add(backBtn);

        this.showProfile();

        this.add(panel);
    }

    public void showProfile()
    {
        DoctorController dc = new DoctorController();

        Doctor d = dc.searchDoctor(this.u.getUserId());

        if(d != null)
        {
            this.doctorIdTF.setText(d.getUserId());
            this.nameTF.setText(d.getName());
            this.emailTF.setText(d.getEmail());
            this.contactTF.setText(d.getContactNo());
            this.specialistTF.setText(d.getSpecialist());
            this.educationTF.setText(d.getEducationalInformation());
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Doctor Profile Not Found");
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(backBtn.getText()))
        {
            DoctorHomeFrame dhf = new DoctorHomeFrame(this.u);

            this.setVisible(false);
            dhf.setVisible(true);
        }
    }
}