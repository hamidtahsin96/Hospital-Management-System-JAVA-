package view;

import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class ADoctorDashboardFrame extends JFrame implements ActionListener
{
    private JLabel userIdLabel, nameLabel, passwordLabel, emailLabel;
    private JLabel contactLabel, specialistLabel, educationLable;

    private JTextField userIdTF, nameTF, emailTF, contactTF;
    private JTextField specialistTF, educationTF;

    private JPasswordField passwordPF;

    private JButton addBtn, editBtn, deleteBtn;
    private JButton searchBtn, resetBtn, backBtn;

    private JTable doctorTable;
    private JScrollPane doctorTableSP;

    private JPanel panel;

    private User u;

    public ADoctorDashboardFrame(User u)
    {
        super("Doctor Dashboard Frame");

        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.userIdLabel = new JLabel("User ID:");
        this.userIdLabel.setBounds(80, 80, 80, 30);
        this.panel.add(userIdLabel);

        this.userIdTF = new JTextField();
        this.userIdTF.setBounds(170, 80, 180, 30);
        this.panel.add(userIdTF);

        this.nameLabel = new JLabel("Name:");
        this.nameLabel.setBounds(80, 130, 80, 30);
        this.panel.add(nameLabel);

        this.nameTF = new JTextField();
        this.nameTF.setBounds(170, 130, 180, 30);
        this.panel.add(nameTF);

        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setBounds(80, 180, 80, 30);
        this.panel.add(passwordLabel);

        this.passwordPF = new JPasswordField();
        this.passwordPF.setBounds(170, 180, 180, 30);
        this.panel.add(passwordPF);

        this.emailLabel = new JLabel("Email:");
        this.emailLabel.setBounds(80, 230, 80, 30);
        this.panel.add(emailLabel);

        this.emailTF = new JTextField();
        this.emailTF.setBounds(170, 230, 180, 30);
        this.panel.add(emailTF);

        this.contactLabel = new JLabel("Contact NO:");
        this.contactLabel.setBounds(80, 280, 80, 30);
        this.panel.add(contactLabel);

        this.contactTF = new JTextField();
        this.contactTF.setBounds(170, 280, 180, 30);
        this.panel.add(contactTF);

        this.specialistLabel = new JLabel("Specialist:");
        this.specialistLabel.setBounds(80, 330, 80, 30);
        this.panel.add(specialistLabel);

        this.specialistTF = new JTextField();
        this.specialistTF.setBounds(170, 330, 180, 30);
        this.panel.add(specialistTF);

        this.educationLable = new JLabel("Educational Information:");
        this.educationLable.setBounds(400, 330, 150, 30);
        this.panel.add(educationLable);

        this.educationTF = new JTextField();
        this.educationTF.setBounds(560, 330, 200, 30);
        this.panel.add(educationTF);

        this.addBtn = new JButton("Add Doctor");
        this.addBtn.setBounds(80, 380, 150, 30);
        this.addBtn.addActionListener(this);
        this.panel.add(addBtn);

        this.editBtn = new JButton("Edit Doctor");
        this.editBtn.setBounds(250, 380, 150, 30);
        this.editBtn.addActionListener(this);
        this.panel.add(editBtn);

        this.deleteBtn = new JButton("Delete Doctor");
        this.deleteBtn.setBounds(420, 380, 150, 30);
        this.deleteBtn.addActionListener(this);
        this.panel.add(deleteBtn);

        this.searchBtn = new JButton("Search Doctor");
        this.searchBtn.setBounds(590, 380, 150, 30);
        this.searchBtn.addActionListener(this);
        this.panel.add(searchBtn);

        this.resetBtn = new JButton("Reset");
        this.resetBtn.setBounds(100, 430, 200, 30);
        this.resetBtn.addActionListener(this);
        this.panel.add(resetBtn);

        this.backBtn = new JButton("Back");
        this.backBtn.setBounds(400, 430, 200, 30);
        this.backBtn.addActionListener(this);
        this.panel.add(backBtn);

        DoctorController dc = new DoctorController();

        Doctor doctorList[] = dc.getAllDoctor();

        String doctorInfo[][] = new String[doctorList.length][6];

        for(int i = 0; i < doctorList.length; i++)
        {
            if(doctorList[i] != null)
            {
                doctorInfo[i][0] = doctorList[i].getUserId();
                doctorInfo[i][1] = doctorList[i].getName();
                doctorInfo[i][2] = doctorList[i].getEmail();
                doctorInfo[i][3] = doctorList[i].getContactNo();
                doctorInfo[i][4] = doctorList[i].getSpecialist();
                doctorInfo[i][5] = doctorList[i].getEducationalInformation();
            }
        }

        String head[] =
                {
                        "ID",
                        "Name",
                        "Email",
                        "Contact NO",
                        "Specialist",
                        "Educational Information"
                };

        this.doctorTable = new JTable(doctorInfo, head);

        this.doctorTableSP = new JScrollPane(doctorTable);
        this.doctorTableSP.setBounds(80, 480, 700, 200);

        this.doctorTable.setEnabled(false);

        this.panel.add(doctorTableSP);

        this.add(panel);

        this.u = u;
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(addBtn.getText()))
        {
            if(!userIdTF.getText().isEmpty() &&
                    !nameTF.getText().isEmpty() &&
                    !passwordPF.getText().isEmpty() &&
                    !emailTF.getText().isEmpty() &&
                    !contactTF.getText().isEmpty() &&
                    !specialistTF.getText().isEmpty() &&
                    !educationTF.getText().isEmpty())
            {
                UserController uc = new UserController();
                DoctorController dc = new DoctorController();

                User user = uc.searchUser(userIdTF.getText());

                if(user != null)
                {
                    JOptionPane.showMessageDialog(this,
                            "This User ID is Already Used");
                }
                else
                {
                    String userIdValue = userIdTF.getText();
                    String nameValue = nameTF.getText();
                    String passwordValue = passwordPF.getText();
                    String emailValue = emailTF.getText();
                    String contactValue = contactTF.getText();

                    int role = User.DOCTOR;

                    String specialistValue = specialistTF.getText();
                    String educationValue = educationTF.getText();

                    Doctor d = new Doctor(
                            userIdValue,
                            passwordValue,
                            nameValue,
                            emailValue,
                            contactValue,
                            role,
                            specialistValue,
                            educationValue
                    );

                    uc.insertUser(d);
                    dc.insertDoctor(d);

                    JOptionPane.showMessageDialog(this,
                            "Add Successfully");

                    ADoctorDashboardFrame adf =
                            new ADoctorDashboardFrame(this.u);

                    this.setVisible(false);
                    adf.setVisible(true);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this,
                        "Please Fill Up All The Field Properly");
            }
        }

        if(command.equals(editBtn.getText()))
        {
            if(!userIdTF.getText().isEmpty() &&
                    !nameTF.getText().isEmpty() &&
                    !passwordPF.getText().isEmpty() &&
                    !emailTF.getText().isEmpty() &&
                    !contactTF.getText().isEmpty() &&
                    !specialistTF.getText().isEmpty() &&
                    !educationTF.getText().isEmpty())
            {
                UserController uc = new UserController();
                DoctorController dc = new DoctorController();

                Doctor d = dc.searchDoctor(userIdTF.getText());

                if(d != null)
                {
                    String nameValue = nameTF.getText();
                    String passwordValue = passwordPF.getText();
                    String emailValue = emailTF.getText();
                    String contactValue = contactTF.getText();

                    int role = User.DOCTOR;

                    String specialistValue = specialistTF.getText();
                    String educationValue = educationTF.getText();

                    d.setName(nameValue);
                    d.setPassword(passwordValue);
                    d.setEmail(emailValue);
                    d.setContactNo(contactValue);
                    d.setRole(role);
                    d.setSpecialist(specialistValue);
                    d.setEducationalInformation(educationValue);

                    dc.updateDoctor(d);
                    uc.updateUser(d);

                    JOptionPane.showMessageDialog(this,
                            "Edit Successfully");

                    ADoctorDashboardFrame adf =
                            new ADoctorDashboardFrame(this.u);

                    this.setVisible(false);
                    adf.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this,
                            "Doctor Does Not Exist");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this,
                        "Please Fill Up All The Field Properly");
            }
        }

        if(command.equals(deleteBtn.getText()))
        {
            if(!userIdTF.getText().isEmpty())
            {
                UserController uc = new UserController();
                DoctorController dc = new DoctorController();

                User user = uc.searchUser(userIdTF.getText());

                if(user != null)
                {
                    String userIdValue = userIdTF.getText();

                    uc.deleteUser(userIdValue);
                    dc.deleteDoctor(userIdValue);

                    JOptionPane.showMessageDialog(this,
                            "Delete Successfully");

                    ADoctorDashboardFrame adf =
                            new ADoctorDashboardFrame(this.u);

                    this.setVisible(false);
                    adf.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this,
                            "Provide a Valid ID");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this,
                        "Please Provide a User ID");
            }
        }

        if(command.equals(searchBtn.getText()))
        {
            if(!userIdTF.getText().isEmpty())
            {
                DoctorController dc = new DoctorController();

                Doctor d = dc.searchDoctor(userIdTF.getText());

                if(d != null)
                {
                    userIdTF.setEnabled(false);

                    nameTF.setText(d.getName());
                    emailTF.setText(d.getEmail());
                    contactTF.setText(d.getContactNo());
                    specialistTF.setText(d.getSpecialist());
                    educationTF.setText(d.getEducationalInformation());
                }
                else
                {
                    JOptionPane.showMessageDialog(this,
                            "Doctor Not Found");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this,
                        "Please Provide a User ID");
            }
        }

        if(command.equals(resetBtn.getText()))
        {
            userIdTF.setEnabled(true);

            userIdTF.setText("");
            nameTF.setText("");
            passwordPF.setText("");
            emailTF.setText("");
            contactTF.setText("");
            specialistTF.setText("");
            educationTF.setText("");
        }

        if(command.equals(backBtn.getText()))
        {
            AdminHomeFrame ahf = new AdminHomeFrame(this.u);

            this.setVisible(false);
            ahf.setVisible(true);
        }
    }
}