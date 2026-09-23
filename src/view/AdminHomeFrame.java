package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class AdminHomeFrame extends JFrame implements ActionListener
{
    private JLabel welcomeLabel;

    private JButton adminDashboardBtn;
    private JButton doctorDashboardBtn;
    private JButton patientDashboardBtn;
    private JButton nurseDashboardBtn;
    private JButton receptionistDashboardBtn;
    private JButton appointmentDetailsBtn;
    private JButton medicalRecordsBtn;
    private JButton viewProfileBtn;
    private JButton updateProfileBtn;
    private JButton resetPasswordBtn;
    private JButton logOutBtn;

    private JPanel panel;

    private User u;

    public AdminHomeFrame(User u)
    {
        super("Admin Home Frame");

        this.u = u;

        this.setSize(600, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.welcomeLabel = new JLabel("Welcome " + u.getName());
        this.welcomeLabel.setBounds(200, 30, 250, 30);
        this.panel.add(welcomeLabel);

        this.adminDashboardBtn = new JButton("Admin Dashboard");
        this.adminDashboardBtn.setBounds(50, 80, 220, 35);
        this.adminDashboardBtn.addActionListener(this);
        this.panel.add(adminDashboardBtn);

        this.doctorDashboardBtn = new JButton("Doctor Dashboard");
        this.doctorDashboardBtn.setBounds(300, 80, 220, 35);
        this.doctorDashboardBtn.addActionListener(this);
        this.panel.add(doctorDashboardBtn);

        this.patientDashboardBtn = new JButton("Patient Dashboard");
        this.patientDashboardBtn.setBounds(50, 130, 220, 35);
        this.patientDashboardBtn.addActionListener(this);
        this.panel.add(patientDashboardBtn);

        this.nurseDashboardBtn = new JButton("Nurse Dashboard");
        this.nurseDashboardBtn.setBounds(300, 130, 220, 35);
        this.nurseDashboardBtn.addActionListener(this);
        this.panel.add(nurseDashboardBtn);

        this.receptionistDashboardBtn = new JButton("Receptionist Dashboard");
        this.receptionistDashboardBtn.setBounds(50, 180, 220, 35);
        this.receptionistDashboardBtn.addActionListener(this);
        this.panel.add(receptionistDashboardBtn);

        this.appointmentDetailsBtn = new JButton("Appointment Details");
        this.appointmentDetailsBtn.setBounds(300, 180, 220, 35);
        this.appointmentDetailsBtn.addActionListener(this);
        this.panel.add(appointmentDetailsBtn);

        this.medicalRecordsBtn = new JButton("Medical Records");
        this.medicalRecordsBtn.setBounds(50, 230, 220, 35);
        this.medicalRecordsBtn.addActionListener(this);
        this.panel.add(medicalRecordsBtn);

        this.viewProfileBtn = new JButton("View Profile");
        this.viewProfileBtn.setBounds(300, 230, 220, 35);
        this.viewProfileBtn.addActionListener(this);
        this.panel.add(viewProfileBtn);

        this.updateProfileBtn = new JButton("Update Profile");
        this.updateProfileBtn.setBounds(50, 280, 220, 35);
        this.updateProfileBtn.addActionListener(this);
        this.panel.add(updateProfileBtn);

        this.resetPasswordBtn = new JButton("Reset Password");
        this.resetPasswordBtn.setBounds(300, 280, 220, 35);
        this.resetPasswordBtn.addActionListener(this);
        this.panel.add(resetPasswordBtn);

        this.logOutBtn = new JButton("Log Out");
        this.logOutBtn.setBounds(200, 350, 180, 35);
        this.logOutBtn.addActionListener(this);
        this.panel.add(logOutBtn);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(adminDashboardBtn.getText()))
        {
            AAdminDashboardFrame aadf = new AAdminDashboardFrame(this.u);

            this.setVisible(false);
            aadf.setVisible(true);
        }

        else if(command.equals(doctorDashboardBtn.getText()))
        {
            ADoctorDashboardFrame addf = new ADoctorDashboardFrame(this.u);

            this.setVisible(false);
            addf.setVisible(true);
        }

        else if(command.equals(patientDashboardBtn.getText()))
        {
            APatientDashboardFrame apdf = new APatientDashboardFrame(this.u);

            this.setVisible(false);
            apdf.setVisible(true);
        }

        else if(command.equals(nurseDashboardBtn.getText()))
        {
            ANurseDashboardFrame andf = new ANurseDashboardFrame(this.u);

            this.setVisible(false);
            andf.setVisible(true);
        }

        else if(command.equals(receptionistDashboardBtn.getText()))
        {
            AReceptionistDashboardFrame ardf = new AReceptionistDashboardFrame(this.u);

            this.setVisible(false);
            ardf.setVisible(true);
        }

        else if(command.equals(appointmentDetailsBtn.getText()))
        {
            AAppointmentDetailsFrame aadf = new AAppointmentDetailsFrame(this.u);

            this.setVisible(false);
            aadf.setVisible(true);
        }

        else if(command.equals(medicalRecordsBtn.getText()))
        {
            AMedicalRecordsFrame amrf = new AMedicalRecordsFrame(this.u);

            this.setVisible(false);
            amrf.setVisible(true);
        }

        else if(command.equals(viewProfileBtn.getText()))
        {
            AViewProfileFrame avpf = new AViewProfileFrame(this.u);

            this.setVisible(false);
            avpf.setVisible(true);
        }

        else if(command.equals(updateProfileBtn.getText()))
        {
            AUpdateProfileFrame aupf = new AUpdateProfileFrame(this.u);

            this.setVisible(false);
            aupf.setVisible(true);
        }

        else if(command.equals(resetPasswordBtn.getText()))
        {
            AResetPasswordFrame arpf = new AResetPasswordFrame(this.u);

            this.setVisible(false);
            arpf.setVisible(true);
        }

        else if(command.equals(logOutBtn.getText()))
        {
            LoginFrame lf = new LoginFrame();

            this.setVisible(false);
            lf.setVisible(true);
        }
    }
}