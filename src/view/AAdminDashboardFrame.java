package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class AAdminDashboardFrame extends JFrame implements ActionListener
{
    private JLabel userIdLabel, nameLabel, passwordLabel, emailLabel;
    private JLabel contactLabel, adminTypeLabel;

    private JTextField userIdTF, nameTF, emailTF, contactTF;

    private JPasswordField passwordPF;

    private JComboBox adminTypeCMB;

    private JButton addBtn, editBtn, deleteBtn, searchBtn;
    private JButton resetBtn, backBtn;

    private JTable adminTable;
    private JScrollPane adminTableSP;

    private JPanel panel;

    private User u;

    public AAdminDashboardFrame(User u)
    {
        super("Admin Dashboard Frame");

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

        this.adminTypeLabel = new JLabel("Admin Type:");
        this.adminTypeLabel.setBounds(80, 330, 80, 30);
        this.panel.add(adminTypeLabel);

        String types[] =
                {
                        "Hospital Manager",
                        "Medical Supervisor"
                };

        this.adminTypeCMB = new JComboBox(types);
        this.adminTypeCMB.setBounds(170, 330, 180, 30);
        this.panel.add(adminTypeCMB);

        this.addBtn = new JButton("Add Admin");
        this.addBtn.setBounds(80, 380, 150, 30);
        this.addBtn.addActionListener(this);
        this.panel.add(addBtn);

        this.editBtn = new JButton("Edit Admin");
        this.editBtn.setBounds(250, 380, 150, 30);
        this.editBtn.addActionListener(this);
        this.panel.add(editBtn);

        this.deleteBtn = new JButton("Delete Admin");
        this.deleteBtn.setBounds(420, 380, 150, 30);
        this.deleteBtn.addActionListener(this);
        this.panel.add(deleteBtn);

        this.searchBtn = new JButton("Search Admin");
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

        AdminController ac = new AdminController();

        Admin adminList[] = ac.getAllAdmin();

        String adminInfo[][] = new String[adminList.length][5];

        for(int i = 0; i < adminList.length; i++)
        {
            if(adminList[i] != null)
            {
                adminInfo[i][0] = adminList[i].getUserId();
                adminInfo[i][1] = adminList[i].getName();
                adminInfo[i][2] = adminList[i].getEmail();
                adminInfo[i][3] = adminList[i].getContactNo();
                adminInfo[i][4] = adminList[i].getAdminType();
            }
        }

        String head[] =
                {
                        "ID",
                        "Name",
                        "Email",
                        "Contact NO",
                        "Admin Type"
                };

        this.adminTable = new JTable(adminInfo, head);

        this.adminTableSP = new JScrollPane(adminTable);
        this.adminTableSP.setBounds(80, 480, 650, 200);

        this.adminTable.setEnabled(false);

        this.panel.add(adminTableSP);

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
                    !contactTF.getText().isEmpty())
            {
                UserController uc = new UserController();
                AdminController ac = new AdminController();

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

                    int role = User.ADMIN;

                    String adminTypeValue = adminTypeCMB.getSelectedItem().toString();

                    Admin a = new Admin(userIdValue, passwordValue, nameValue, emailValue, contactValue, role, adminTypeValue);

                    uc.insertUser(a);
                    ac.insertAdmin(a);

                    JOptionPane.showMessageDialog(this,
                            "Add Successfully");

                    AAdminDashboardFrame aadf =
                            new AAdminDashboardFrame(this.u);

                    this.setVisible(false);
                    aadf.setVisible(true);
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
                    !contactTF.getText().isEmpty())
            {
                UserController uc = new UserController();
                AdminController ac = new AdminController();

                Admin a = ac.searchAdmin(userIdTF.getText());

                if(a != null)
                {
                    String nameValue = nameTF.getText();
                    String passwordValue = passwordPF.getText();
                    String emailValue = emailTF.getText();
                    String contactValue = contactTF.getText();

                    int role = User.ADMIN;

                    String adminTypeValue = adminTypeCMB.getSelectedItem().toString();

                    a.setName(nameValue);
                    a.setPassword(passwordValue);
                    a.setEmail(emailValue);
                    a.setContactNo(contactValue);
                    a.setRole(role);
                    a.setAdminType(adminTypeValue);

                    ac.updateAdmin(a);
                    uc.updateUser(a);

                    JOptionPane.showMessageDialog(this, "Edit Successfully");

                    AAdminDashboardFrame aadf = new AAdminDashboardFrame(this.u);

                    this.setVisible(false);
                    aadf.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Admin Does Not Exist");
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
                AdminController ac = new AdminController();

                User user = uc.searchUser(userIdTF.getText());

                if(user != null)
                {
                    String userIdValue = userIdTF.getText();

                    uc.deleteUser(userIdValue);
                    ac.deleteAdmin(userIdValue);

                    JOptionPane.showMessageDialog(this, "Delete Successfully");

                    AAdminDashboardFrame aadf = new AAdminDashboardFrame(this.u);

                    this.setVisible(false);
                    aadf.setVisible(true);
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
                AdminController ac = new AdminController();

                Admin a = ac.searchAdmin(userIdTF.getText());

                if(a != null)
                {
                    userIdTF.setEnabled(false);

                    nameTF.setText(a.getName());
                    emailTF.setText(a.getEmail());
                    contactTF.setText(a.getContactNo());

                    adminTypeCMB.setSelectedItem(
                            a.getAdminType()
                    );
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Admin Not Found");
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

            adminTypeCMB.setSelectedIndex(0);
        }

        if(command.equals(backBtn.getText()))
        {
            AdminHomeFrame ahf = new AdminHomeFrame(this.u);

            this.setVisible(false);
            ahf.setVisible(true);
        }
    }
}