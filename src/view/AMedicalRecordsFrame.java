package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class AMedicalRecordsFrame extends JFrame implements ActionListener
{
    private JLabel medicalIdLabel, doctorIdLabel, patientIdLabel, detailsLabel;

    private JTextField medicalIdTF, doctorIdTF, patientIdTF, detailsTF;

    private JButton addBtn, editBtn, backBtn;

    private JTable medicalTable;
    private JScrollPane medicalTableSP;

    private JPanel panel;

    private User u;

    public AMedicalRecordsFrame(User u)
    {
        super("Medical Records Frame");

        this.u = u;

        this.setSize(900, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.medicalIdLabel = new JLabel("Medical Record ID:");
        this.medicalIdLabel.setBounds(50, 50, 120, 30);
        this.panel.add(medicalIdLabel);

        this.medicalIdTF = new JTextField();
        this.medicalIdTF.setBounds(180, 50, 180, 30);
        this.panel.add(medicalIdTF);

        this.doctorIdLabel = new JLabel("Doctor ID:");
        this.doctorIdLabel.setBounds(450, 50, 100, 30);
        this.panel.add(doctorIdLabel);

        this.doctorIdTF = new JTextField();
        this.doctorIdTF.setBounds(550, 50, 180, 30);
        this.panel.add(doctorIdTF);

        this.patientIdLabel = new JLabel("Patient ID:");
        this.patientIdLabel.setBounds(50, 100, 100, 30);
        this.panel.add(patientIdLabel);

        this.patientIdTF = new JTextField();
        this.patientIdTF.setBounds(180, 100, 180, 30);
        this.panel.add(patientIdTF);

        this.detailsLabel = new JLabel("Details:");
        this.detailsLabel.setBounds(450, 100, 100, 30);
        this.panel.add(detailsLabel);

        this.detailsTF = new JTextField();
        this.detailsTF.setBounds(550, 100, 180, 30);
        this.panel.add(detailsTF);

        this.addBtn = new JButton("Add Medical Record");
        this.addBtn.setBounds(100, 160, 250, 30);
        this.addBtn.addActionListener(this);
        this.panel.add(addBtn);

        this.editBtn = new JButton("Edit Medical Record");
        this.editBtn.setBounds(400, 160, 250, 30);
        this.editBtn.addActionListener(this);
        this.panel.add(editBtn);

        this.backBtn = new JButton("Back");
        this.backBtn.setBounds(275, 210, 250, 30);
        this.backBtn.addActionListener(this);
        this.panel.add(backBtn);

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

        this.medicalTable = new JTable(medicalRecordInfo, head);

        this.medicalTableSP = new JScrollPane(medicalTable);

        this.medicalTableSP.setBounds(50, 270, 800, 400);

        this.medicalTable.setEnabled(false);

        this.panel.add(medicalTableSP);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(addBtn.getText()))
        {
            if(!medicalIdTF.getText().isEmpty() &&
                    !doctorIdTF.getText().isEmpty() &&
                    !patientIdTF.getText().isEmpty() &&
                    !detailsTF.getText().isEmpty())
            {
                MedicalRecordController mrc = new MedicalRecordController();

                MedicalRecord mr = mrc.searchMedicalRecord(medicalIdTF.getText());

                if(mr != null)
                {
                    JOptionPane.showMessageDialog(this, "This Medical Record ID is Already Used");
                }
                else
                {
                    String medicalIdValue = medicalIdTF.getText();

                    String doctorIdValue = doctorIdTF.getText();

                    String patientIdValue = patientIdTF.getText();

                    String detailsValue = detailsTF.getText();

                    DoctorController dc = new DoctorController();

                    PatientController pc = new PatientController();

                    Doctor doctor = dc.searchDoctor(doctorIdValue);

                    Patient patient = pc.searchPatient(patientIdValue);

                    if(doctor == null)
                    {
                        JOptionPane.showMessageDialog(this, "Invalid Doctor ID");
                    }
                    else if(patient == null)
                    {
                        JOptionPane.showMessageDialog(this, "Invalid Patient ID");
                    }
                    else
                    {
                        MedicalRecord mr1 = new MedicalRecord(
                                        medicalIdValue,
                                        doctor,
                                        patient,
                                        detailsValue
                                );

                        mrc.insertMedicalRecord(mr1);

                        JOptionPane.showMessageDialog(this, "Medical Record Added Successfully");

                        AMedicalRecordsFrame amrf = new AMedicalRecordsFrame(this.u);

                        this.setVisible(false);
                        amrf.setVisible(true);
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Fill Up All The Field Properly");
            }
        }

        else if(command.equals(editBtn.getText()))
        {
            if(!medicalIdTF.getText().isEmpty() && !detailsTF.getText().isEmpty())
            {
                MedicalRecordController mrc = new MedicalRecordController();

                MedicalRecord mr = mrc.searchMedicalRecord(medicalIdTF.getText());

                if(mr != null)
                {
                    mr.setDetails(detailsTF.getText());

                    mrc.updateMedicalRecord(mr);

                    JOptionPane.showMessageDialog(this, "Medical Record Updated Successfully");

                    AMedicalRecordsFrame amrf = new AMedicalRecordsFrame(this.u);

                    this.setVisible(false);
                    amrf.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Medical Record Does Not Exist");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Enter Medical Record ID and Details");
            }
        }

        else if(command.equals(backBtn.getText()))
        {
            AdminHomeFrame ahf = new AdminHomeFrame(this.u);

            this.setVisible(false);
            ahf.setVisible(true);
        }
    }
}