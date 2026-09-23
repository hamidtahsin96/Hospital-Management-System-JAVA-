package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class NurseHomeFrame extends JFrame implements ActionListener
{
    private JButton doctorBtn;
    private JButton patientBtn;
    private JButton patientDetailsBtn;
    private JButton appointmentBtn;
    private JButton medicalRecordsBtn;
    private JButton viewProfileBtn;
    private JButton updateProfileBtn;
    private JButton resetPasswordBtn;
    private JButton logoutBtn;

    private JPanel panel;

    private User u;

    public NurseHomeFrame(User u)
    {
        super("Nurse Home Frame");

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

        this.patientDetailsBtn = new JButton("Patient Details");

        this.patientDetailsBtn.setBounds(50, 150, 300, 30);

        this.patientDetailsBtn.addActionListener(this);
        this.panel.add(patientDetailsBtn);

        this.appointmentBtn = new JButton("Appointment List");

        this.appointmentBtn.setBounds(400, 150, 300, 30);

        this.appointmentBtn.addActionListener(this);
        this.panel.add(appointmentBtn);

        this.medicalRecordsBtn = new JButton("Medical Records");

        this.medicalRecordsBtn.setBounds(50, 250, 300, 30);

        this.medicalRecordsBtn.addActionListener(this);
        this.panel.add(medicalRecordsBtn);

        this.viewProfileBtn = new JButton("View Profile");

        this.viewProfileBtn.setBounds(400, 250, 300, 30);

        this.viewProfileBtn.addActionListener(this);
        this.panel.add(viewProfileBtn);

        this.updateProfileBtn = new JButton("Update Profile");

        this.updateProfileBtn.setBounds(50, 350, 300, 30);

        this.updateProfileBtn.addActionListener(this);
        this.panel.add(updateProfileBtn);

        this.resetPasswordBtn = new JButton("Reset Password");

        this.resetPasswordBtn.setBounds(400, 350, 300, 30);

        this.resetPasswordBtn.addActionListener(this);
        this.panel.add(resetPasswordBtn);

        this.logoutBtn = new JButton("Logout");

        this.logoutBtn.setBounds(250, 450, 300, 30);

        this.logoutBtn.addActionListener(this);
        this.panel.add(logoutBtn);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(doctorBtn.getText()))
        {
            NDoctorListFrame ndlf = new NDoctorListFrame(this.u);

            this.setVisible(false);
            ndlf.setVisible(true);
        }

        else if(command.equals(patientBtn.getText()))
        {
            NPatientListFrame nplf = new NPatientListFrame(this.u);

            this.setVisible(false);
            nplf.setVisible(true);
        }

        else if(command.equals(patientDetailsBtn.getText()))
        {
            NPatientDetailsFrame npdf = new NPatientDetailsFrame(this.u);

            this.setVisible(false);
            npdf.setVisible(true);
        }

        else if(command.equals(appointmentBtn.getText()))
        {
            NAppointmentFrame naf = new NAppointmentFrame(this.u);

            this.setVisible(false);
            naf.setVisible(true);
        }

        else if(command.equals(medicalRecordsBtn.getText()))
        {
            NMedicalRecordsFrame nmrf = new NMedicalRecordsFrame(this.u);

            this.setVisible(false);
            nmrf.setVisible(true);
        }

        else if(command.equals(viewProfileBtn.getText()))
        {
            NViewProfileFrame nvpf = new NViewProfileFrame(this.u);

            this.setVisible(false);
            nvpf.setVisible(true);
        }

        else if(command.equals(updateProfileBtn.getText()))
        {
            NUpdateProfileFrame nupf = new NUpdateProfileFrame(this.u);

            this.setVisible(false);
            nupf.setVisible(true);
        }

        else if(command.equals(resetPasswordBtn.getText()))
        {
            NResetPasswordFrame nrpf = new NResetPasswordFrame(this.u);

            this.setVisible(false);
            nrpf.setVisible(true);
        }

        else if(command.equals(logoutBtn.getText()))
        {
            LoginFrame lf = new LoginFrame();

            this.setVisible(false);
            lf.setVisible(true);
        }
    }
}