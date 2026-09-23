package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class ReceptionistHomeFrame extends JFrame implements ActionListener
{
    private JButton doctorBtn;
    private JButton patientBtn;
    private JButton addPatientBtn;
    private JButton patientDetailsBtn;
    private JButton appointmentBtn;
    private JButton viewProfileBtn;
    private JButton updateProfileBtn;
    private JButton resetPasswordBtn;
    private JButton logoutBtn;

    private JPanel panel;

    private User u;

    public ReceptionistHomeFrame(User u)
    {
        super("Receptionist Home Frame");

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

        this.addPatientBtn = new JButton("Add Patient");

        this.addPatientBtn.setBounds(50, 150, 300, 30);

        this.addPatientBtn.addActionListener(this);

        this.panel.add(addPatientBtn);

        this.patientDetailsBtn = new JButton("Patient Details");

        this.patientDetailsBtn.setBounds(400, 150, 300, 30);

        this.patientDetailsBtn.addActionListener(this);

        this.panel.add(patientDetailsBtn);

        this.appointmentBtn = new JButton("Appointment");

        this.appointmentBtn.setBounds(50, 250, 300, 30);

        this.appointmentBtn.addActionListener(this);

        this.panel.add(appointmentBtn);

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
            RDoctorListFrame rdlf = new RDoctorListFrame(this.u);

            this.setVisible(false);
            rdlf.setVisible(true);
        }

        else if(command.equals(patientBtn.getText()))
        {
            RPatientListFrame rplf = new RPatientListFrame(this.u);

            this.setVisible(false);
            rplf.setVisible(true);
        }

        else if(command.equals(addPatientBtn.getText()))
        {
            RAddPatientFrame rapf = new RAddPatientFrame(this.u);

            this.setVisible(false);
            rapf.setVisible(true);
        }

        else if(command.equals(patientDetailsBtn.getText()))
        {
            RPatientDetailsFrame rpdf = new RPatientDetailsFrame(this.u);

            this.setVisible(false);
            rpdf.setVisible(true);
        }

        else if(command.equals(appointmentBtn.getText()))
        {
            RAppointmentFrame raf = new RAppointmentFrame(this.u);

            this.setVisible(false);
            raf.setVisible(true);
        }

        else if(command.equals(viewProfileBtn.getText()))
        {
            RViewProfileFrame rvpf = new RViewProfileFrame(this.u);

            this.setVisible(false);
            rvpf.setVisible(true);
        }

        else if(command.equals(updateProfileBtn.getText()))
        {
            RUpdateProfileFrame rupf = new RUpdateProfileFrame(this.u);

            this.setVisible(false);
            rupf.setVisible(true);
        }

        else if(command.equals(resetPasswordBtn.getText()))
        {
            RResetPasswordFrame rrpf = new RResetPasswordFrame(this.u);

            this.setVisible(false);
            rrpf.setVisible(true);
        }

        else if(command.equals(logoutBtn.getText()))
        {
            LoginFrame lf = new LoginFrame();

            this.setVisible(false);
            lf.setVisible(true);
        }
    }
}