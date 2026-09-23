package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class DoctorHomeFrame extends JFrame implements ActionListener
{
    private JButton doctorBtn, patientBtn, appointmentBtn;
    private JButton medicalRecordsBtn, paymentBtn;
    private JButton updateProfileBtn, viewProfileBtn;
    private JButton resetPasswordBtn, logoutBtn;

    private JPanel panel;

    private User u;

    public DoctorHomeFrame(User u)
    {
        super("Doctor Home Frame");

        this.u = u;

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.doctorBtn = new JButton("Doctor List");
        this.doctorBtn.setBounds(50, 50, 300, 30);
        this.doctorBtn.addActionListener(this);
        this.panel.add(doctorBtn);

        this.patientBtn = new JButton("Patient List");
        this.patientBtn.setBounds(400, 50, 300, 30);
        this.patientBtn.addActionListener(this);
        this.panel.add(patientBtn);

        this.appointmentBtn = new JButton("Appointment List");
        this.appointmentBtn.setBounds(50, 150, 190, 30);
        this.appointmentBtn.addActionListener(this);
        this.panel.add(appointmentBtn);

        this.medicalRecordsBtn = new JButton("Medical Records");
        this.medicalRecordsBtn.setBounds(290, 150, 180, 30);
        this.medicalRecordsBtn.addActionListener(this);
        this.panel.add(medicalRecordsBtn);

        this.paymentBtn = new JButton("Payment Status");
        this.paymentBtn.setBounds(520, 150, 180, 30);
        this.paymentBtn.addActionListener(this);
        this.panel.add(paymentBtn);

        this.viewProfileBtn = new JButton("View Profile");
        this.viewProfileBtn.setBounds(50, 250, 300, 30);
        this.viewProfileBtn.addActionListener(this);
        this.panel.add(viewProfileBtn);

        this.updateProfileBtn = new JButton("Update Profile");
        this.updateProfileBtn.setBounds(400, 250, 300, 30);
        this.updateProfileBtn.addActionListener(this);
        this.panel.add(updateProfileBtn);

        this.resetPasswordBtn = new JButton("Reset Password");
        this.resetPasswordBtn.setBounds(220, 350, 300, 30);
        this.resetPasswordBtn.addActionListener(this);
        this.panel.add(resetPasswordBtn);

        this.logoutBtn = new JButton("Logout");
        this.logoutBtn.setBounds(220, 450, 300, 30);
        this.logoutBtn.addActionListener(this);
        this.panel.add(logoutBtn);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(doctorBtn.getText()))
        {
            DDoctorListFrame ddlf = new DDoctorListFrame(this.u);

            this.setVisible(false);
            ddlf.setVisible(true);
        }

        else if(command.equals(patientBtn.getText()))
        {
            DPatientListFrame dplf = new DPatientListFrame(this.u);

            this.setVisible(false);
            dplf.setVisible(true);
        }

        else if(command.equals(appointmentBtn.getText()))
        {
            DAppointmentFrame dapf = new DAppointmentFrame(this.u);

            this.setVisible(false);
            dapf.setVisible(true);
        }

        else if(command.equals(medicalRecordsBtn.getText()))
        {
            DMedicalRecordsFrame dmrf = new DMedicalRecordsFrame(this.u);

            this.setVisible(false);
            dmrf.setVisible(true);
        }

        else if(command.equals(paymentBtn.getText()))
        {
            DPaymentFrame dpf = new DPaymentFrame(this.u);

            this.setVisible(false);
            dpf.setVisible(true);
        }

        else if(command.equals(viewProfileBtn.getText()))
        {
            DViewProfileFrame dvpf = new DViewProfileFrame(this.u);

            this.setVisible(false);
            dvpf.setVisible(true);
        }

        else if(command.equals(updateProfileBtn.getText()))
        {
            DUpdateProfileFrame dupf = new DUpdateProfileFrame(this.u);

            this.setVisible(false);
            dupf.setVisible(true);
        }

        else if(command.equals(resetPasswordBtn.getText()))
        {
            DResetPasswordFrame drpf = new DResetPasswordFrame(this.u);

            this.setVisible(false);
            drpf.setVisible(true);
        }

        else if(command.equals(logoutBtn.getText()))
        {
            LoginFrame lf = new LoginFrame();

            this.setVisible(false);
            lf.setVisible(true);
        }
    }
}