package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class DMedicalRecordsFrame extends JFrame implements ActionListener
{
    private JLabel titleLabel;

    private JButton addBtn;
    private JButton editBtn;
    private JButton backBtn;

    private JTable medicalRecordTable;
    private JScrollPane medicalRecordTableSP;

    private JPanel panel;

    private User u;

    public DMedicalRecordsFrame(User u)
    {
        super("Medical Records Frame");

        this.u = u;

        this.setSize(800, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.titleLabel = new JLabel("Medical Records");

        this.titleLabel.setBounds(330, 40, 150, 30);
        this.panel.add(titleLabel);

        this.showMedicalRecords();

        this.addBtn = new JButton("Add Medical Record");

        this.addBtn.setBounds(100, 450, 180, 30);
        this.addBtn.addActionListener(this);
        this.panel.add(addBtn);

        this.editBtn = new JButton("Edit Medical Record");

        this.editBtn.setBounds(310, 450, 180, 30);
        this.editBtn.addActionListener(this);
        this.panel.add(editBtn);

        this.backBtn = new JButton("Back");

        this.backBtn.setBounds(520, 450, 100, 30);
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
                if(records[i].getDoctor() != null)
                {
                    if(records[i].getDoctor().getUserId().equals(this.u.getUserId()))
                    {
                        medicalRecordInfo[j][0] = records[i].getMedicalRecordId();

                        medicalRecordInfo[j][1] = records[i].getPatient().getUserId();

                        medicalRecordInfo[j][2] = records[i].getDetails();

                        j++;
                    }
                }
            }
        }

        String head[] =
                {
                        "Medical Record ID",
                        "Patient ID",
                        "Details"
                };

        this.medicalRecordTable = new JTable(medicalRecordInfo, head);

        this.medicalRecordTableSP = new JScrollPane(medicalRecordTable);

        this.medicalRecordTableSP.setBounds(50, 100, 700, 300
        );

        this.medicalRecordTable.setEnabled(false);

        this.panel.add(medicalRecordTableSP);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(addBtn.getText()))
        {
            String medicalIdValue = JOptionPane.showInputDialog(this, "Enter Medical Record ID:");

            if(medicalIdValue != null && !medicalIdValue.isEmpty())
            {
                MedicalRecordController mrc = new MedicalRecordController();

                MedicalRecord existing = mrc.searchMedicalRecord(medicalIdValue);

                if(existing == null)
                {
                    String patientIdValue = JOptionPane.showInputDialog(this, "Enter Patient ID:");

                    if(patientIdValue != null && !patientIdValue.isEmpty())
                    {
                        PatientController pc = new PatientController();

                        Patient patient = pc.searchPatient(patientIdValue);

                        if(patient != null)
                        {
                            String detailsValue = JOptionPane.showInputDialog(this, "Enter Medical Record Details:");

                            if(detailsValue != null && !detailsValue.isEmpty())
                            {
                                DoctorController dc = new DoctorController();

                                Doctor doctor = dc.searchDoctor(this.u.getUserId());

                                if(doctor != null)
                                {
                                    MedicalRecord mr = new MedicalRecord(
                                                    medicalIdValue,
                                                    doctor,
                                                    patient,
                                                    detailsValue
                                            );

                                    mrc.insertMedicalRecord(mr);

                                    JOptionPane.showMessageDialog(this, "Medical Record Added Successfully");

                                    this.removeMedicalRecordTable();

                                    this.showMedicalRecords();

                                    this.panel.revalidate();
                                    this.panel.repaint();
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(this, "Doctor Not Found");
                                }
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "Patient Not Found");
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Medical Record ID Already Exists");
                }
            }
        }

        else if(command.equals(editBtn.getText()))
        {
            String medicalIdValue = JOptionPane.showInputDialog(this, "Enter Medical Record ID:");

            if(medicalIdValue != null && !medicalIdValue.isEmpty())
            {
                MedicalRecordController mrc = new MedicalRecordController();

                MedicalRecord mr = mrc.searchMedicalRecord(medicalIdValue);

                if(mr != null)
                {
                    if(mr.getDoctor() != null && mr.getDoctor().getUserId().equals(this.u.getUserId()))
                    {
                        String detailsValue = JOptionPane.showInputDialog(this, "Enter New Details:");

                        if(detailsValue != null && !detailsValue.isEmpty())
                        {
                            mr.setDetails(detailsValue);

                            mrc.updateMedicalRecord(mr);

                            JOptionPane.showMessageDialog(this, "Medical Record Updated Successfully"
                            );

                            this.removeMedicalRecordTable();

                            this.showMedicalRecords();

                            this.panel.revalidate();
                            this.panel.repaint();
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "This Medical Record Does Not Belong To You");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Medical Record Not Found");
                }
            }
        }

        else if(command.equals(backBtn.getText()))
        {
            DoctorHomeFrame dhf = new DoctorHomeFrame(this.u);

            this.setVisible(false);
            dhf.setVisible(true);
        }
    }

    public void removeMedicalRecordTable()
    {
        this.panel.remove(this.medicalRecordTableSP);
    }
}