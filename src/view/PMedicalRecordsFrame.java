package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class PMedicalRecordsFrame extends JFrame implements ActionListener
{
    private JLabel titleLabel;

    private JButton backBtn;

    private JTable medicalRecordTable;
    private JScrollPane medicalRecordTableSP;

    private JPanel panel;

    private User u;

    public PMedicalRecordsFrame(User u)
    {
        super("Medical Records Frame");

        this.u = u;

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.titleLabel = new JLabel("My Medical Records");

        this.titleLabel.setBounds(320, 40, 170, 30);

        this.panel.add(titleLabel);

        this.showMedicalRecords();

        this.backBtn = new JButton("Back");

        this.backBtn.setBounds(350, 450, 100, 30
        );
        this.backBtn.addActionListener(this);

        this.panel.add(backBtn);

        this.add(panel);
    }

    public void showMedicalRecords()
    {
        MedicalRecordController mrc = new MedicalRecordController();

        MedicalRecord records[] = mrc.getAllMedicalRecord();

        String medicalRecordInfo[][] = new String[records.length][3];

        int j = 0;

        for(int i = 0; i < records.length; i++)
        {
            if(records[i] != null)
            {
                if(records[i].getPatient() != null)
                {
                    if(records[i].getPatient().getUserId().equals(this.u.getUserId()))
                    {
                        medicalRecordInfo[j][0] = records[i].getMedicalRecordId();

                        medicalRecordInfo[j][1] = records[i].getDoctor().getUserId();

                        medicalRecordInfo[j][2] = records[i].getDetails();

                        j++;
                    }
                }
            }
        }

        String head[] =
                {
                        "Medical Record ID",
                        "Doctor ID",
                        "Details"
                };

        this.medicalRecordTable = new JTable(medicalRecordInfo, head);

        this.medicalRecordTableSP = new JScrollPane(medicalRecordTable);

        this.medicalRecordTableSP.setBounds(50, 100, 700, 300);

        this.medicalRecordTable.setEnabled(false);

        this.panel.add(medicalRecordTableSP);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(backBtn.getText()))
        {
            PatientHomeFrame phf = new PatientHomeFrame(this.u);

            this.setVisible(false);
            phf.setVisible(true);
        }
    }
}