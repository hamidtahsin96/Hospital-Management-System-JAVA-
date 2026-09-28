package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class ANurseDashboardFrame extends JFrame implements ActionListener
{
    private JLabel userIdLabel, nameLabel, passwordLabel, emailLabel;
    private JLabel contactLabel, shiftLabel, departmentLabel;

    private JTextField userIdTF, nameTF, emailTF, contactTF;
    private JTextField shiftTF, departmentTF;

    private JPasswordField passwordPF;

    private JButton addBtn, editBtn, deleteBtn;
    private JButton searchBtn, resetBtn, backBtn;

    private JTable nurseTable;
    private JScrollPane nurseTableSP;

    private JPanel panel;

    private User u;

    public ANurseDashboardFrame(User u)
    {
        super("Nurse Dashboard Frame");

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

        this.shiftLabel = new JLabel("Shift:");
        this.shiftLabel.setBounds(80, 330, 80, 30);
        this.panel.add(shiftLabel);

        this.shiftTF = new JTextField();
        this.shiftTF.setBounds(170, 330, 180, 30);
        this.panel.add(shiftTF);

        this.departmentLabel = new JLabel("Department:");
        this.departmentLabel.setBounds(400, 330, 150, 30);
        this.panel.add(departmentLabel);

        this.departmentTF = new JTextField();
        this.departmentTF.setBounds(560, 330, 200, 30);
        this.panel.add(departmentTF);

        this.addBtn = new JButton("Add Nurse");
        this.addBtn.setBounds(80, 380, 150, 30);
        this.addBtn.addActionListener(this);
        this.panel.add(addBtn);

        this.editBtn = new JButton("Edit Nurse");
        this.editBtn.setBounds(250, 380, 150, 30);
        this.editBtn.addActionListener(this);
        this.panel.add(editBtn);

        this.deleteBtn = new JButton("Delete Nurse");
        this.deleteBtn.setBounds(420, 380, 150, 30);
        this.deleteBtn.addActionListener(this);
        this.panel.add(deleteBtn);

        this.searchBtn = new JButton("Search Nurse");
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

        NurseController nc = new NurseController();

        Nurse nurseList[] = nc.getAllNurse();

        String nurseInfo[][] = new String[nurseList.length][6];

        int row = 0;

        for(int i = 0; i < nurseList.length; i++)
        {
            if(nurseList[i] != null)
            {
                nurseInfo[row][0] = nurseList[i].getUserId();
                nurseInfo[row][1] = nurseList[i].getName();
                nurseInfo[row][2] = nurseList[i].getEmail();
                nurseInfo[row][3] = nurseList[i].getContactNo();
                nurseInfo[row][4] = nurseList[i].getShift();
                nurseInfo[row][5] = nurseList[i].getDepartment();

                row++;
            }
        }

        String head[] =
                {
                        "ID",
                        "Name",
                        "Email",
                        "Contact NO",
                        "Shift",
                        "Department"
                };

        this.nurseTable = new JTable(nurseInfo, head);

        this.nurseTableSP = new JScrollPane(nurseTable);
        this.nurseTableSP.setBounds(80, 480, 700, 200);

        this.nurseTable.setEnabled(false);

        this.panel.add(nurseTableSP);

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
                    !shiftTF.getText().isEmpty() &&
                    !departmentTF.getText().isEmpty())
            {
                UserController uc = new UserController();
                NurseController nc = new NurseController();

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
                    String shiftValue = shiftTF.getText();
                    String departmentValue = departmentTF.getText();

                    int role = User.NURSE;

                    Nurse n = new Nurse(
                            userIdValue,
                            passwordValue,
                            nameValue,
                            emailValue,
                            contactValue,
                            role,
                            shiftValue,
                            departmentValue
                    );

                    uc.insertUser(n);
                    nc.insertNurse(n);

                    JOptionPane.showMessageDialog(this,
                            "Nurse Added Successfully");

                    ANurseDashboardFrame andf =
                            new ANurseDashboardFrame(this.u);

                    this.setVisible(false);
                    andf.setVisible(true);
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
                    !shiftTF.getText().isEmpty() &&
                    !departmentTF.getText().isEmpty())
            {
                UserController uc = new UserController();
                NurseController nc = new NurseController();

                Nurse n = nc.searchNurse(userIdTF.getText());

                if(n != null)
                {
                    String nameValue = nameTF.getText();
                    String passwordValue = passwordPF.getText();
                    String emailValue = emailTF.getText();
                    String contactValue = contactTF.getText();

                    int role = User.NURSE;

                    String shiftValue = shiftTF.getText();
                    String departmentValue = departmentTF.getText();

                    n.setName(nameValue);
                    n.setPassword(passwordValue);
                    n.setEmail(emailValue);
                    n.setContactNo(contactValue);
                    n.setRole(role);
                    n.setShift(shiftValue);
                    n.setDepartment(departmentValue);

                    nc.updateNurse(n);
                    uc.updateUser(n);

                    JOptionPane.showMessageDialog(this,
                            "Edit Successfully");

                    ANurseDashboardFrame andf =
                            new ANurseDashboardFrame(this.u);

                    this.setVisible(false);
                    andf.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this,
                            "Nurse Does Not Exist");
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
                NurseController nc = new NurseController();

                User user = uc.searchUser(userIdTF.getText());

                if(user != null)
                {
                    String userIdValue = userIdTF.getText();

                    uc.deleteUser(userIdValue);
                    nc.deleteNurse(userIdValue);

                    JOptionPane.showMessageDialog(this,
                            "Delete Successfully");

                    ANurseDashboardFrame andf =
                            new ANurseDashboardFrame(this.u);

                    this.setVisible(false);
                    andf.setVisible(true);
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
                NurseController nc = new NurseController();

                Nurse n = nc.searchNurse(userIdTF.getText());

                if(n != null)
                {
                    userIdTF.setEnabled(false);

                    nameTF.setText(n.getName());
                    passwordPF.setText(n.getPassword());
                    emailTF.setText(n.getEmail());
                    contactTF.setText(n.getContactNo());
                    shiftTF.setText(n.getShift());
                    departmentTF.setText(n.getDepartment());
                }
                else
                {
                    JOptionPane.showMessageDialog(this,
                            "Nurse Not Found");
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
            shiftTF.setText("");
            departmentTF.setText("");
        }

        if(command.equals(backBtn.getText()))
        {
            AdminHomeFrame ahf = new AdminHomeFrame(this.u);

            this.setVisible(false);
            ahf.setVisible(true);
        }
    }
}