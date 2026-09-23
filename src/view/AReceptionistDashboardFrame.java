package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class AReceptionistDashboardFrame extends JFrame implements ActionListener
{
    private JLabel userIdLabel, nameLabel, passwordLabel, emailLabel;
    private JLabel contactNoLabel, shiftLabel, deskNumberLabel;

    private JTextField userIdTF, nameTF, emailTF, contactNoTF;
    private JTextField shiftTF, deskNumberTF;

    private JPasswordField passwordPF;

    private JButton addBtn, editBtn, deleteBtn, searchBtn;
    private JButton resetBtn, viewAllBtn, backBtn;

    private JTable receptionistTable;
    private JScrollPane receptionistTableSP;

    private JPanel panel;

    private User u;

    public AReceptionistDashboardFrame(User u)
    {
        super("Receptionist Dashboard");

        this.u = u;

        this.setSize(900, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.userIdLabel = new JLabel("User ID:");
        this.userIdLabel.setBounds(50, 30, 100, 20);
        this.panel.add(userIdLabel);

        this.userIdTF = new JTextField();
        this.userIdTF.setBounds(150, 25, 180, 30);
        this.panel.add(userIdTF);

        this.nameLabel = new JLabel("Name:");
        this.nameLabel.setBounds(450, 30, 100, 20);
        this.panel.add(nameLabel);

        this.nameTF = new JTextField();
        this.nameTF.setBounds(550, 25, 180, 30);
        this.panel.add(nameTF);

        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setBounds(50, 75, 100, 20);
        this.panel.add(passwordLabel);

        this.passwordPF = new JPasswordField();
        this.passwordPF.setBounds(150, 70, 180, 30);
        this.panel.add(passwordPF);

        this.emailLabel = new JLabel("Email:");
        this.emailLabel.setBounds(450, 75, 100, 20);
        this.panel.add(emailLabel);

        this.emailTF = new JTextField();
        this.emailTF.setBounds(550, 70, 180, 30);
        this.panel.add(emailTF);

        this.contactNoLabel = new JLabel("Contact NO:");
        this.contactNoLabel.setBounds(50, 120, 100, 20);
        this.panel.add(contactNoLabel);

        this.contactNoTF = new JTextField();
        this.contactNoTF.setBounds(150, 115, 180, 30);
        this.panel.add(contactNoTF);

        this.shiftLabel = new JLabel("Shift:");
        this.shiftLabel.setBounds(450, 120, 100, 20);
        this.panel.add(shiftLabel);

        this.shiftTF = new JTextField();
        this.shiftTF.setBounds(550, 115, 180, 30);
        this.panel.add(shiftTF);

        this.deskNumberLabel = new JLabel("Desk Number:");
        this.deskNumberLabel.setBounds(50, 165, 100, 20);
        this.panel.add(deskNumberLabel);

        this.deskNumberTF = new JTextField();
        this.deskNumberTF.setBounds(150, 160, 180, 30);
        this.panel.add(deskNumberTF);

        this.addBtn = new JButton("Add");
        this.addBtn.setBounds(50, 210, 100, 30);
        this.addBtn.addActionListener(this);
        this.panel.add(addBtn);

        this.editBtn = new JButton("Edit");
        this.editBtn.setBounds(160, 210, 100, 30);
        this.editBtn.addActionListener(this);
        this.panel.add(editBtn);

        this.deleteBtn = new JButton("Delete");
        this.deleteBtn.setBounds(270, 210, 100, 30);
        this.deleteBtn.addActionListener(this);
        this.panel.add(deleteBtn);

        this.searchBtn = new JButton("Search");
        this.searchBtn.setBounds(380, 210, 100, 30);
        this.searchBtn.addActionListener(this);
        this.panel.add(searchBtn);

        this.resetBtn = new JButton("Reset");
        this.resetBtn.setBounds(490, 210, 100, 30);
        this.resetBtn.addActionListener(this);
        this.panel.add(resetBtn);

        this.viewAllBtn = new JButton("View All");
        this.viewAllBtn.setBounds(600, 210, 100, 30);
        this.viewAllBtn.addActionListener(this);
        this.panel.add(viewAllBtn);

        this.backBtn = new JButton("Back");
        this.backBtn.setBounds(710, 210, 100, 30);
        this.backBtn.addActionListener(this);
        this.panel.add(backBtn);

        String data[][] = new String[100][6];

        String column[] =
                {
                        "User ID",
                        "Name",
                        "Email",
                        "Contact NO",
                        "Shift",
                        "Desk Number"
                };

        this.receptionistTable = new JTable(data, column);
        this.receptionistTableSP = new JScrollPane(receptionistTable);
        this.receptionistTableSP.setBounds(50, 270, 760, 300);
        this.panel.add(receptionistTableSP);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(addBtn.getText()))
        {
            if(!userIdTF.getText().isEmpty() &&
                    !passwordPF.getText().isEmpty() &&
                    !nameTF.getText().isEmpty() &&
                    !emailTF.getText().isEmpty() &&
                    !contactNoTF.getText().isEmpty() &&
                    !shiftTF.getText().isEmpty() &&
                    !deskNumberTF.getText().isEmpty())
            {
                UserController uc = new UserController();
                ReceptionistController rc = new ReceptionistController();

                User user = uc.searchUser(userIdTF.getText());

                if(user != null)
                {
                    JOptionPane.showMessageDialog(this, "This User ID is Already Used");
                }
                else
                {
                    String userIdValue = userIdTF.getText();
                    String passwordValue = passwordPF.getText();
                    String nameValue = nameTF.getText();
                    String emailValue = emailTF.getText();
                    String contactNoValue = contactNoTF.getText();
                    String shiftValue = shiftTF.getText();
                    String deskNumberValue = deskNumberTF.getText();

                    int role = User.RECEPTIONIST;

                    Receptionist r = new Receptionist(
                            userIdValue,
                            passwordValue,
                            nameValue,
                            emailValue,
                            contactNoValue,
                            role,
                            shiftValue,
                            deskNumberValue
                    );

                    uc.insertUser(r);
                    rc.insertReceptionist(r);

                    JOptionPane.showMessageDialog(this, "Receptionist Added Successfully");

                    AReceptionistDashboardFrame ardf = new AReceptionistDashboardFrame(this.u);

                    this.setVisible(false);
                    ardf.setVisible(true);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Fill Up All The Field Properly");
            }
        }

        else if(command.equals(editBtn.getText()))
        {
            if(!userIdTF.getText().isEmpty())
            {
                ReceptionistController rc = new ReceptionistController();
                UserController uc = new UserController();

                Receptionist r = rc.searchReceptionist(userIdTF.getText());

                if(r == null)
                {
                    JOptionPane.showMessageDialog(this, "Receptionist Not Found");
                }
                else
                {
                    if(!passwordPF.getText().isEmpty())
                    {
                        r.setPassword(passwordPF.getText());
                    }

                    if(!nameTF.getText().isEmpty())
                    {
                        r.setName(nameTF.getText());
                    }

                    if(!emailTF.getText().isEmpty())
                    {
                        r.setEmail(emailTF.getText());
                    }

                    if(!contactNoTF.getText().isEmpty())
                    {
                        r.setContactNo(contactNoTF.getText());
                    }

                    if(!shiftTF.getText().isEmpty())
                    {
                        r.setShift(shiftTF.getText());
                    }

                    if(!deskNumberTF.getText().isEmpty())
                    {
                        r.setDeskNumber(deskNumberTF.getText());
                    }

                    r.setRole(User.RECEPTIONIST);

                    rc.updateReceptionist(r);
                    uc.updateUser(r);

                    JOptionPane.showMessageDialog(this, "Receptionist Updated Successfully");

                    AReceptionistDashboardFrame ardf = new AReceptionistDashboardFrame(this.u);

                    this.setVisible(false);
                    ardf.setVisible(true);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Enter Receptionist User ID");
            }
        }

        else if(command.equals(deleteBtn.getText()))
        {
            if(!userIdTF.getText().isEmpty())
            {
                ReceptionistController rc = new ReceptionistController();
                UserController uc = new UserController();

                Receptionist r = rc.searchReceptionist(userIdTF.getText());

                if(r == null)
                {
                    JOptionPane.showMessageDialog(this, "Receptionist Not Found");
                }
                else
                {
                    rc.deleteReceptionist(userIdTF.getText());
                    uc.deleteUser(userIdTF.getText());

                    JOptionPane.showMessageDialog(this, "Receptionist Deleted Successfully");

                    AReceptionistDashboardFrame ardf = new AReceptionistDashboardFrame(this.u);

                    this.setVisible(false);
                    ardf.setVisible(true);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Enter Receptionist User ID");
            }
        }

        else if(command.equals(searchBtn.getText()))
        {
            if(!userIdTF.getText().isEmpty())
            {
                ReceptionistController rc = new ReceptionistController();

                Receptionist r = rc.searchReceptionist(userIdTF.getText());

                if(r != null)
                {
                    nameTF.setText(r.getName());
                    passwordPF.setText(r.getPassword());
                    emailTF.setText(r.getEmail());
                    contactNoTF.setText(r.getContactNo());
                    shiftTF.setText(r.getShift());
                    deskNumberTF.setText(r.getDeskNumber());

                    userIdTF.setEnabled(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Receptionist Not Found");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Enter Receptionist User ID");
            }
        }

        else if(command.equals(resetBtn.getText()))
        {
            userIdTF.setText("");
            passwordPF.setText("");
            nameTF.setText("");
            emailTF.setText("");
            contactNoTF.setText("");
            shiftTF.setText("");
            deskNumberTF.setText("");

            userIdTF.setEnabled(true);
        }

        else if(command.equals(viewAllBtn.getText()))
        {
            ReceptionistController rc = new ReceptionistController();

            Receptionist receptionists[] = rc.getAllReceptionist();

            String data[][] = new String[100][6];

            for(int i = 0; i < receptionists.length; i++)
            {
                if(receptionists[i] != null)
                {
                    data[i][0] = receptionists[i].getUserId();
                    data[i][1] = receptionists[i].getName();
                    data[i][2] = receptionists[i].getEmail();
                    data[i][3] = receptionists[i].getContactNo();
                    data[i][4] = receptionists[i].getShift();
                    data[i][5] = receptionists[i].getDeskNumber();
                }
            }

            String column[] =
                    {
                            "User ID",
                            "Name",
                            "Email",
                            "Contact NO",
                            "Shift",
                            "Desk Number"
                    };

            this.panel.remove(receptionistTableSP);

            this.receptionistTable = new JTable(data, column);

            this.receptionistTableSP = new JScrollPane(receptionistTable);

            this.receptionistTableSP.setBounds(50, 270, 760, 300);

            this.panel.add(receptionistTableSP);

            this.panel.revalidate();
            this.panel.repaint();
        }

        else if(command.equals(backBtn.getText()))
        {
            AdminHomeFrame ahf = new AdminHomeFrame(this.u);

            this.setVisible(false);
            ahf.setVisible(true);
        }
    }
}