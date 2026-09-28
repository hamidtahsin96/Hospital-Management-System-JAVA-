package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class AReceptionistDashboardFrame extends JFrame implements ActionListener
{
    private JLabel userIdLabel, nameLabel, passwordLabel, emailLabel;
    private JLabel contactLabel, shiftLabel, deskNumberLabel;

    private JTextField userIdTF, nameTF, emailTF, contactTF;
    private JTextField shiftTF, deskNumberTF;

    private JPasswordField passwordPF;

    private JButton addBtn, editBtn, deleteBtn;
    private JButton searchBtn, resetBtn, backBtn;

    private JTable receptionistTable;
    private JScrollPane receptionistTableSP;

    private JPanel panel;

    private User u;

    public AReceptionistDashboardFrame(User u)
    {
        super("Receptionist Dashboard Frame");

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

        this.deskNumberLabel = new JLabel("Desk Number:");
        this.deskNumberLabel.setBounds(400, 330, 150, 30);
        this.panel.add(deskNumberLabel);

        this.deskNumberTF = new JTextField();
        this.deskNumberTF.setBounds(560, 330, 200, 30);
        this.panel.add(deskNumberTF);

        this.addBtn = new JButton("Add Receptionist");
        this.addBtn.setBounds(80, 380, 150, 30);
        this.addBtn.addActionListener(this);
        this.panel.add(addBtn);

        this.editBtn = new JButton("Edit Receptionist");
        this.editBtn.setBounds(250, 380, 150, 30);
        this.editBtn.addActionListener(this);
        this.panel.add(editBtn);

        this.deleteBtn = new JButton("Delete Receptionist");
        this.deleteBtn.setBounds(420, 380, 150, 30);
        this.deleteBtn.addActionListener(this);
        this.panel.add(deleteBtn);

        this.searchBtn = new JButton("Search Receptionist");
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

        ReceptionistController rc = new ReceptionistController();

        Receptionist receptionistList[] = rc.getAllReceptionist();

        String receptionistInfo[][] = new String[receptionistList.length][6];

        int row = 0;

        for(int i = 0; i < receptionistList.length; i++)
        {
            if(receptionistList[i] != null)
            {
                receptionistInfo[row][0] = receptionistList[i].getUserId();
                receptionistInfo[row][1] = receptionistList[i].getName();
                receptionistInfo[row][2] = receptionistList[i].getEmail();
                receptionistInfo[row][3] = receptionistList[i].getContactNo();
                receptionistInfo[row][4] = receptionistList[i].getShift();
                receptionistInfo[row][5] = receptionistList[i].getDeskNumber();

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
                        "Desk Number"
                };

        this.receptionistTable =
                new JTable(receptionistInfo, head);

        this.receptionistTableSP =
                new JScrollPane(receptionistTable);

        this.receptionistTableSP.setBounds(80, 480, 700, 200);

        this.receptionistTable.setEnabled(false);

        this.panel.add(receptionistTableSP);

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
                    !deskNumberTF.getText().isEmpty())
            {
                UserController uc = new UserController();
                ReceptionistController rc =
                        new ReceptionistController();

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
                    String deskNumberValue = deskNumberTF.getText();

                    int role = User.RECEPTIONIST;

                    Receptionist r = new Receptionist(
                            userIdValue,
                            passwordValue,
                            nameValue,
                            emailValue,
                            contactValue,
                            role,
                            shiftValue,
                            deskNumberValue
                    );

                    uc.insertUser(r);
                    rc.insertReceptionist(r);

                    JOptionPane.showMessageDialog(this,
                            "Receptionist Added Successfully");

                    AReceptionistDashboardFrame ardf =
                            new AReceptionistDashboardFrame(this.u);

                    this.setVisible(false);
                    ardf.setVisible(true);
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
                    !deskNumberTF.getText().isEmpty())
            {
                UserController uc = new UserController();
                ReceptionistController rc =
                        new ReceptionistController();

                Receptionist r =
                        rc.searchReceptionist(userIdTF.getText());

                if(r != null)
                {
                    String nameValue = nameTF.getText();
                    String passwordValue = passwordPF.getText();
                    String emailValue = emailTF.getText();
                    String contactValue = contactTF.getText();
                    String shiftValue = shiftTF.getText();
                    String deskNumberValue = deskNumberTF.getText();

                    int role = User.RECEPTIONIST;

                    r.setName(nameValue);
                    r.setPassword(passwordValue);
                    r.setEmail(emailValue);
                    r.setContactNo(contactValue);
                    r.setRole(role);
                    r.setShift(shiftValue);
                    r.setDeskNumber(deskNumberValue);

                    rc.updateReceptionist(r);
                    uc.updateUser(r);

                    JOptionPane.showMessageDialog(this,
                            "Edit Successfully");

                    AReceptionistDashboardFrame ardf =
                            new AReceptionistDashboardFrame(this.u);

                    this.setVisible(false);
                    ardf.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this,
                            "Receptionist Does Not Exist");
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
                ReceptionistController rc =
                        new ReceptionistController();

                User user =
                        uc.searchUser(userIdTF.getText());

                if(user != null)
                {
                    String userIdValue =
                            userIdTF.getText();

                    uc.deleteUser(userIdValue);
                    rc.deleteReceptionist(userIdValue);

                    JOptionPane.showMessageDialog(this,
                            "Delete Successfully");

                    AReceptionistDashboardFrame ardf =
                            new AReceptionistDashboardFrame(this.u);

                    this.setVisible(false);
                    ardf.setVisible(true);
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
                ReceptionistController rc =
                        new ReceptionistController();

                Receptionist r =
                        rc.searchReceptionist(userIdTF.getText());

                if(r != null)
                {
                    userIdTF.setEnabled(false);

                    nameTF.setText(r.getName());
                    passwordPF.setText(r.getPassword());
                    emailTF.setText(r.getEmail());
                    contactTF.setText(r.getContactNo());
                    shiftTF.setText(r.getShift());
                    deskNumberTF.setText(r.getDeskNumber());
                }
                else
                {
                    JOptionPane.showMessageDialog(this,
                            "Receptionist Not Found");
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
            deskNumberTF.setText("");
        }

        if(command.equals(backBtn.getText()))
        {
            AdminHomeFrame ahf =
                    new AdminHomeFrame(this.u);

            this.setVisible(false);
            ahf.setVisible(true);
        }
    }
}