package view;

import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class RegistrationFrame extends JFrame implements ActionListener
{
    private JLabel userIdLabel, contactNoLabel, nameLabel, emailLabel;
    private JLabel ageLabel, passwordLabel, genderLabel, addressLabel;

    private JTextField userIdTF, contactNoTF, nameTF, emailTF;
    private JTextField ageTF, addressTF;

    private JPasswordField passwordPF;

    private JRadioButton maleRB, femaleRB;
    private ButtonGroup btg;

    private JButton submitBtn, backBtn;

    private JPanel panel;

    public RegistrationFrame()
    {
        super("Patient Registration Frame");

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.userIdLabel = new JLabel("User ID:");
        this.userIdLabel.setBounds(50, 100, 70, 20);
        this.panel.add(userIdLabel);

        this.userIdTF = new JTextField();
        this.userIdTF.setBounds(130, 95, 180, 30);
        this.panel.add(userIdTF);

        this.contactNoLabel = new JLabel("Contact NO:");
        this.contactNoLabel.setBounds(400, 100, 80, 20);
        this.panel.add(contactNoLabel);

        this.contactNoTF = new JTextField();
        this.contactNoTF.setBounds(490, 95, 180, 30);
        this.panel.add(contactNoTF);

        this.nameLabel = new JLabel("Name:");
        this.nameLabel.setBounds(50, 150, 70, 20);
        this.panel.add(nameLabel);

        this.nameTF = new JTextField();
        this.nameTF.setBounds(130, 145, 180, 30);
        this.panel.add(nameTF);

        this.emailLabel = new JLabel("Email:");
        this.emailLabel.setBounds(400, 150, 70, 20);
        this.panel.add(emailLabel);

        this.emailTF = new JTextField();
        this.emailTF.setBounds(490, 145, 180, 30);
        this.panel.add(emailTF);

        this.ageLabel = new JLabel("Age:");
        this.ageLabel.setBounds(50, 200, 70, 20);
        this.panel.add(ageLabel);

        this.ageTF = new JTextField();
        this.ageTF.setBounds(130, 195, 180, 30);
        this.panel.add(ageTF);

        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setBounds(400, 200, 80, 20);
        this.panel.add(passwordLabel);

        this.passwordPF = new JPasswordField();
        this.passwordPF.setBounds(490, 195, 180, 30);
        this.panel.add(passwordPF);

        this.genderLabel = new JLabel("Gender:");
        this.genderLabel.setBounds(50, 250, 70, 20);
        this.panel.add(genderLabel);

        this.maleRB = new JRadioButton("Male");
        this.maleRB.setBounds(130, 247, 60, 30);
        this.panel.add(maleRB);

        this.femaleRB = new JRadioButton("Female");
        this.femaleRB.setBounds(200, 247, 80, 30);
        this.panel.add(femaleRB);

        this.btg = new ButtonGroup();
        this.btg.add(maleRB);
        this.btg.add(femaleRB);

        this.addressLabel = new JLabel("Address:");
        this.addressLabel.setBounds(50, 300, 70, 20);
        this.panel.add(addressLabel);

        this.addressTF = new JTextField();
        this.addressTF.setBounds(130, 295, 300, 30);
        this.panel.add(addressTF);

        this.submitBtn = new JButton("Submit");
        this.submitBtn.setBounds(50, 400, 150, 30);
        this.submitBtn.addActionListener(this);
        this.panel.add(submitBtn);

        this.backBtn = new JButton("Back");
        this.backBtn.setBounds(300, 400, 130, 30);
        this.backBtn.addActionListener(this);
        this.panel.add(backBtn);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(submitBtn.getText()))
        {
            if(!userIdTF.getText().isEmpty() &&
                    !passwordPF.getText().isEmpty() &&
                    !nameTF.getText().isEmpty() &&
                    !emailTF.getText().isEmpty() &&
                    !contactNoTF.getText().isEmpty() &&
                    !ageTF.getText().isEmpty() &&
                    !addressTF.getText().isEmpty() &&
                    (maleRB.isSelected() || femaleRB.isSelected()))
            {
                UserController uc = new UserController();
                PatientController pc = new PatientController();

                User u = uc.searchUser(userIdTF.getText());

                if(u != null)
                {
                    JOptionPane.showMessageDialog(this,
                            "This User ID is Already Used");
                }
                else
                {
                    String userIdValue = userIdTF.getText();
                    String passwordValue = passwordPF.getText();
                    String nameValue = nameTF.getText();
                    String emailValue = emailTF.getText();
                    String contactNoValue = contactNoTF.getText();

                    int role = User.PATIENT;

                    String genderValue = "";

                    if(maleRB.isSelected())
                    {
                        genderValue = "Male";
                    }
                    else
                    {
                        genderValue = "Female";
                    }

                    int ageValue;

                    try
                    {
                        ageValue = Integer.parseInt(ageTF.getText());
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(this,
                                "Age Value should be Integer.");
                        return;
                    }

                    String addressValue = addressTF.getText();

                    Patient p = new Patient(
                            userIdValue,
                            passwordValue,
                            nameValue,
                            emailValue,
                            contactNoValue,
                            role,
                            genderValue,
                            ageValue,
                            addressValue
                    );

                    uc.insertUser(p);
                    pc.insertPatient(p);

                    JOptionPane.showMessageDialog(this,
                            "Registration Successful");

                    LoginFrame lf = new LoginFrame();

                    this.setVisible(false);
                    lf.setVisible(true);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this,
                        "Please Fill Up All The Field Properly");
            }
        }

        if(command.equals(backBtn.getText()))
        {
            LoginFrame lf = new LoginFrame();

            this.setVisible(false);
            lf.setVisible(true);
        }
    }
}