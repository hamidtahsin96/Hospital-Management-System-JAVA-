package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class NMedicalRecordsFrame extends JFrame implements ActionListener
{
    private JLabel titleLabel;

    private JButton backBtn;

    private JTable medicalRecordTable;
    private JScrollPane medicalRecordTableSP;

    private JPanel panel;

    private User u;

    public NMedicalRecordsFrame(User u)
    {
        super("Medical Records Frame");

        this.u = u;

        this.setSize(900, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.titleLabel = new JLabel("Medical Records");

        this.titleLabel.setBounds(380, 40, 150, 30);

        this.panel.add(titleLabel);

        MedicalRecordController mrc = new MedicalRecordController();

        MedicalRecord medicalRecordList[] = mrc.getAllMedicalRecord();

        String medicalRecordInfo[][] = new String[medicalRecordList.length][4];

        for(int i = 0; i < medicalRecordList.length; i++)
        {
            if(medicalRecordList[i] != null)
            {
                medicalRecordInfo[i][0] = medicalRecordList[i].getMedicalRecordId();

                medicalRecordInfo[i][1] = medicalRecordList[i].getDoctor().getUserId();

                medicalRecordInfo[i][2] = medicalRecordList[i].getPatient().getUserId();

                medicalRecordInfo[i][3] = medicalRecordList[i].getDetails();
            }
        }

        String head[] =
                {
                        "Medical Record ID",
                        "Doctor ID",
                        "Patient ID",
                        "Details"
                };

        this.medicalRecordTable = new JTable(medicalRecordInfo, head);

        this.medicalRecordTableSP = new JScrollPane(medicalRecordTable);

        this.medicalRecordTableSP.setBounds(50, 100, 800, 300);

        this.medicalRecordTable.setEnabled(false);

        this.panel.add(medicalRecordTableSP);

        this.backBtn = new JButton("Back");

        this.backBtn.setBounds(400, 450, 100, 30);

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