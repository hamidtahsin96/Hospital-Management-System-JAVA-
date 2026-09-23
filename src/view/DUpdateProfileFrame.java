package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class DUpdateProfileFrame extends JFrame implements ActionListener
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

    private JButton updateBtn;
    private JButton backBtn;

    private JPanel panel;

    private User u;

    public DUpdateProfileFrame(User u)
    {
        super("Update Doctor Profile");

        this.u = u;

        this.setSize(550, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.doctorIdLabel = new JLabel("Doctor ID:");
        this.doctorIdLabel.setBounds(70, 40, 130, 30);
        this.panel.add(doctorIdLabel);

        this.doctorIdTF = new JTextField();
        this.doctorIdTF.setBounds(210, 40, 220, 30);
        this.doctorIdTF.setEditable(false);
        this.panel.add(doctorIdTF);

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

        this.specialistLabel = new JLabel("Specialist:");
        this.specialistLabel.setBounds(70, 200, 130, 30);
        this.panel.add(specialistLabel);

        this.specialistTF = new JTextField();
        this.specialistTF.setBounds(210, 200, 220, 30);
        this.panel.add(specialistTF);

        this.educationLabel = new JLabel("Education:");

        this.educationLabel.setBounds(70, 240, 130, 30);
        this.panel.add(educationLabel);

        this.educationTF = new JTextField();
        this.educationTF.setBounds(210, 240, 220, 30);
        this.panel.add(educationTF);

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

        if(command.equals(updateBtn.getText()))
        {
            if(!emailTF.getText().isEmpty() &&
                    !contactTF.getText().isEmpty() &&
                    !specialistTF.getText().isEmpty() &&
                    !educationTF.getText().isEmpty())
            {
                DoctorController dc = new DoctorController();

                UserController uc = new UserController();

                Doctor d = dc.searchDoctor(this.u.getUserId());

                if(d != null)
                {
                    d.setEmail(emailTF.getText());
                    d.setContactNo(contactTF.getText());
                    d.setSpecialist(specialistTF.getText());
                    d.setEducationalInformation(educationTF.getText()
                    );

                    dc.updateDoctor(d);

                    this.u.setEmail(emailTF.getText());
                    this.u.setContactNo(contactTF.getText());

                    uc.updateUser(this.u);

                    JOptionPane.showMessageDialog(this, "Profile Updated Successfully");

                    DoctorHomeFrame dhf = new DoctorHomeFrame(this.u);

                    this.setVisible(false);
                    dhf.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Doctor Profile Not Found");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Fill Up All The Field Properly");
            }
        }

        else if(command.equals(backBtn.getText()))
        {
            DoctorHomeFrame dhf = new DoctorHomeFrame(this.u);

            this.setVisible(false);
            dhf.setVisible(true);
        }
    }
}