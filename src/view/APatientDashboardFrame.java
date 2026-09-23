package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class APatientDashboardFrame extends JFrame implements ActionListener
{
    private JLabel userIdLabel, contactLabel, nameLabel, emailLabel;
    private JLabel ageLabel, passwordLabel, genderLabel, addressLabel;

    private JTextField userIdTF, contactTF, nameTF, emailTF;
    private JTextField ageTF, addressTF;

    private JPasswordField passwordPF;

    private JRadioButton maleRB, femaleRB;
    private ButtonGroup btg;

    private JButton addBtn, editBtn, deleteBtn;
    private JButton searchBtn, resetBtn, backBtn;

    private JTable patientTable;
    private JScrollPane patientTableSP;

    private JPanel panel;

    private User u;

    public APatientDashboardFrame(User u)
    {
        super("Patient Dashboard Frame");

        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.userIdLabel = new JLabel("User ID:");
        this.userIdLabel.setBounds(50, 100, 70, 30);
        this.panel.add(userIdLabel);

        this.userIdTF = new JTextField();
        this.userIdTF.setBounds(130, 100, 180, 30);
        this.panel.add(userIdTF);

        this.contactLabel = new JLabel("Contact NO:");
        this.contactLabel.setBounds(400, 100, 80, 30);
        this.panel.add(contactLabel);

        this.contactTF = new JTextField();
        this.contactTF.setBounds(490, 100, 180, 30);
        this.panel.add(contactTF);

        this.nameLabel = new JLabel("Name:");
        this.nameLabel.setBounds(50, 150, 70, 30);
        this.panel.add(nameLabel);

        this.nameTF = new JTextField();
        this.nameTF.setBounds(130, 150, 180, 30);
        this.panel.add(nameTF);

        this.emailLabel = new JLabel("Email:");
        this.emailLabel.setBounds(400, 150, 70, 30);
        this.panel.add(emailLabel);

        this.emailTF = new JTextField();
        this.emailTF.setBounds(490, 150, 180, 30);
        this.panel.add(emailTF);

        this.ageLabel = new JLabel("Age:");
        this.ageLabel.setBounds(50, 200, 70, 30);
        this.panel.add(ageLabel);

        this.ageTF = new JTextField();
        this.ageTF.setBounds(130, 200, 180, 30);
        this.panel.add(ageTF);

        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setBounds(400, 200, 80, 30);
        this.panel.add(passwordLabel);

        this.passwordPF = new JPasswordField();
        this.passwordPF.setBounds(490, 200, 180, 30);
        this.panel.add(passwordPF);

        this.genderLabel = new JLabel("Gender:");
        this.genderLabel.setBounds(50, 250, 70, 30);
        this.panel.add(genderLabel);

        this.maleRB = new JRadioButton("Male");
        this.maleRB.setBounds(130, 250, 60, 30);
        this.panel.add(maleRB);

        this.femaleRB = new JRadioButton("Female");
        this.femaleRB.setBounds(200, 250, 80, 30);
        this.panel.add(femaleRB);

        this.btg = new ButtonGroup();
        this.btg.add(maleRB);
        this.btg.add(femaleRB);

        this.addressLabel = new JLabel("Address:");
        this.addressLabel.setBounds(50, 300, 70, 30);
        this.panel.add(addressLabel);

        this.addressTF = new JTextField();
        this.addressTF.setBounds(130, 300, 300, 30);
        this.panel.add(addressTF);

        this.addBtn = new JButton("Add Patient");
        this.addBtn.setBounds(50, 350, 150, 30);
        this.addBtn.addActionListener(this);
        this.panel.add(addBtn);

        this.editBtn = new JButton("Edit Patient");
        this.editBtn.setBounds(220, 350, 150, 30);
        this.editBtn.addActionListener(this);
        this.panel.add(editBtn);

        this.deleteBtn = new JButton("Delete Patient");
        this.deleteBtn.setBounds(390, 350, 150, 30);
        this.deleteBtn.addActionListener(this);
        this.panel.add(deleteBtn);

        this.searchBtn = new JButton("Search Patient");
        this.searchBtn.setBounds(560, 350, 150, 30);
        this.searchBtn.addActionListener(this);
        this.panel.add(searchBtn);

        this.resetBtn = new JButton("Reset");
        this.resetBtn.setBounds(50, 400, 250, 30);
        this.resetBtn.addActionListener(this);
        this.panel.add(resetBtn);

        this.backBtn = new JButton("Back");
        this.backBtn.setBounds(400, 400, 250, 30);
        this.backBtn.addActionListener(this);
        this.panel.add(backBtn);

        PatientController pc = new PatientController();

        Patient patientList[] = pc.getAllPatient();

        String patientInfo[][] = new String[patientList.length][7];

        for(int i = 0; i < patientList.length; i++)
        {
            if(patientList[i] != null)
            {
                patientInfo[i][0] = patientList[i].getUserId();
                patientInfo[i][1] = patientList[i].getName();
                patientInfo[i][2] = patientList[i].getEmail();
                patientInfo[i][3] = patientList[i].getContactNo();
                patientInfo[i][4] = patientList[i].getGender();
                patientInfo[i][5] = String.valueOf(patientList[i].getAge());
                patientInfo[i][6] = patientList[i].getAddress();
            }
        }

        String head[] =
                {
                        "ID",
                        "Name",
                        "Email",
                        "Contact NO",
                        "Gender",
                        "Age",
                        "Address"
                };

        this.patientTable = new JTable(patientInfo, head);

        this.patientTableSP = new JScrollPane(patientTable);
        this.patientTableSP.setBounds(50, 450, 650, 200);

        this.patientTable.setEnabled(false);

        this.panel.add(patientTableSP);

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
                    (maleRB.isSelected() || femaleRB.isSelected()) &&
                    !ageTF.getText().isEmpty() &&
                    !addressTF.getText().isEmpty())
            {
                UserController uc = new UserController();
                PatientController pc = new PatientController();

                User user = uc.searchUser(userIdTF.getText());

                if(user != null)
                {
                    JOptionPane.showMessageDialog(this, "This User ID is Already Used");
                }
                else
                {
                    String userIdValue = userIdTF.getText();
                    String nameValue = nameTF.getText();
                    String passwordValue = passwordPF.getText();
                    String emailValue = emailTF.getText();
                    String contactValue = contactTF.getText();

                    int role = User.PATIENT;

                    String genderValue = "";

                    if(maleRB.isSelected())
                    {
                        genderValue = maleRB.getText();
                    }
                    else
                    {
                        genderValue = femaleRB.getText();
                    }

                    int ageValue;

                    try
                    {
                        ageValue = Integer.parseInt(ageTF.getText());
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(this, "Age Value should be Integer.");
                        return;
                    }

                    String addressValue = addressTF.getText();

                    Patient p = new Patient(
                            userIdValue,
                            passwordValue,
                            nameValue,
                            emailValue,
                            contactValue,
                            role,
                            genderValue,
                            ageValue,
                            addressValue
                    );

                    uc.insertUser(p);
                    pc.insertPatient(p);

                    JOptionPane.showMessageDialog(this, "Add Successfully");

                    APatientDashboardFrame apdf = new APatientDashboardFrame(this.u);

                    this.setVisible(false);
                    apdf.setVisible(true);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Fill Up All The Field Properly");
            }
        }

        if(command.equals(editBtn.getText()))
        {
            if(!userIdTF.getText().isEmpty() &&
                    !nameTF.getText().isEmpty() &&
                    !passwordPF.getText().isEmpty() &&
                    !emailTF.getText().isEmpty() &&
                    !contactTF.getText().isEmpty() &&
                    (maleRB.isSelected() || femaleRB.isSelected()) &&
                    !ageTF.getText().isEmpty() &&
                    !addressTF.getText().isEmpty())
            {
                UserController uc = new UserController();
                PatientController pc = new PatientController();

                Patient p = pc.searchPatient(userIdTF.getText());

                if(p != null)
                {
                    String nameValue = nameTF.getText();
                    String passwordValue = passwordPF.getText();
                    String emailValue = emailTF.getText();
                    String contactValue = contactTF.getText();

                    int role = User.PATIENT;

                    String genderValue = "";

                    if(maleRB.isSelected())
                    {
                        genderValue = maleRB.getText();
                    }
                    else
                    {
                        genderValue = femaleRB.getText();
                    }

                    int ageValue;

                    try
                    {
                        ageValue = Integer.parseInt(ageTF.getText());
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(this, "Age Value should be Integer.");
                        return;
                    }

                    String addressValue = addressTF.getText();

                    p.setName(nameValue);
                    p.setPassword(passwordValue);
                    p.setEmail(emailValue);
                    p.setContactNo(contactValue);
                    p.setRole(role);
                    p.setGender(genderValue);
                    p.setAge(ageValue);
                    p.setAddress(addressValue);

                    pc.updatePatient(p);
                    uc.updateUser(p);

                    JOptionPane.showMessageDialog(this, "Edit Successfully");

                    APatientDashboardFrame apdf = new APatientDashboardFrame(this.u);

                    this.setVisible(false);
                    apdf.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Patient Does Not Exist");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Fill Up All The Field Properly");
            }
        }

        if(command.equals(deleteBtn.getText()))
        {
            if(!userIdTF.getText().isEmpty())
            {
                UserController uc = new UserController();
                PatientController pc = new PatientController();

                User user = uc.searchUser(userIdTF.getText());

                if(user != null)
                {
                    String userIdValue = userIdTF.getText();

                    uc.deleteUser(userIdValue);
                    pc.deletePatient(userIdValue);

                    JOptionPane.showMessageDialog(this, "Delete Successfully");

                    APatientDashboardFrame apdf = new APatientDashboardFrame(this.u);

                    this.setVisible(false);
                    apdf.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Provide a Valid ID");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Provide a User ID");
            }
        }

        if(command.equals(searchBtn.getText()))
        {
            if(!userIdTF.getText().isEmpty())
            {
                PatientController pc = new PatientController();

                Patient p = pc.searchPatient(userIdTF.getText());

                if(p != null)
                {
                    userIdTF.setEnabled(false);

                    nameTF.setText(p.getName());
                    emailTF.setText(p.getEmail());
                    contactTF.setText(p.getContactNo());

                    if(p.getGender().equals("Male"))
                    {
                        maleRB.setSelected(true);
                    }
                    else
                    {
                        femaleRB.setSelected(true);
                    }

                    ageTF.setText(String.valueOf(p.getAge()));
                    addressTF.setText(p.getAddress());
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Patient Not Found");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Provide a User ID");
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
            ageTF.setText("");
            addressTF.setText("");

            maleRB.setSelected(false);
            femaleRB.setSelected(false);
        }

        if(command.equals(backBtn.getText()))
        {
            AdminHomeFrame ahf = new AdminHomeFrame(this.u);

            this.setVisible(false);
            ahf.setVisible(true);
        }
    }
}