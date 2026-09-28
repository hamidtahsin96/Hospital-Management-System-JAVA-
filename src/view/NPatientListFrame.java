package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class NPatientListFrame extends JFrame implements ActionListener
{
    private JLabel titleLabel;

    private JButton backBtn;

    private JTable patientTable;
    private JScrollPane patientTableSP;

    private JPanel panel;

    private User u;

    public NPatientListFrame(User u)
    {
        super("Patient List Frame");

        this.u = u;

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.titleLabel = new JLabel("Patient List");

        this.titleLabel.setBounds(350, 40, 100, 30);

        this.panel.add(titleLabel);

        PatientController pc = new PatientController();

        Patient patientList[] = pc.getAllPatient();

        String patientInfo[][] = new String[patientList.length][7];

        int row = 0;

        for(int i = 0; i < patientList.length; i++)
        {
            if(patientList[i] != null)
            {
                patientInfo[row][0] = patientList[i].getUserId();
                patientInfo[row][1] = patientList[i].getName();
                patientInfo[row][2] = patientList[i].getEmail();
                patientInfo[row][3] = patientList[i].getContactNo();
                patientInfo[row][4] = patientList[i].getGender();
                patientInfo[row][5] = String.valueOf(patientList[i].getAge());
                patientInfo[row][6] = patientList[i].getAddress();

                row++;
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

        this.patientTableSP.setBounds(30, 100, 740, 300);

        this.patientTable.setEnabled(false);

        this.panel.add(patientTableSP);

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
            NurseHomeFrame nhf = new NurseHomeFrame(this.u);

            this.setVisible(false);
            nhf.setVisible(true);
        }
    }
}