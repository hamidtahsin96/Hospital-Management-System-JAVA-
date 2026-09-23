package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class RDoctorListFrame extends JFrame implements ActionListener
{
    private JLabel titleLabel;

    private JButton backBtn;

    private JTable doctorTable;
    private JScrollPane doctorTableSP;

    private JPanel panel;

    private User u;

    public RDoctorListFrame(User u)
    {
        super("Doctor List Frame");

        this.u = u;

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.titleLabel = new JLabel("Doctor List");

        this.titleLabel.setBounds(350, 40, 100, 30);

        this.panel.add(titleLabel);

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

        this.doctorTableSP.setBounds(50, 100, 700, 300);

        this.doctorTable.setEnabled(false);

        this.panel.add(doctorTableSP);

        this.backBtn = new JButton("Back");

        this.backBtn.setBounds(350, 450, 100, 30);

        this.backBtn.addActionListener(this);

        this.panel.add(backBtn);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(backBtn.getText()))
        {
            ReceptionistHomeFrame rhf = new ReceptionistHomeFrame(this.u);

            this.setVisible(false);
            rhf.setVisible(true);
        }
    }
}