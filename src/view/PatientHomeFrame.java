package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class PatientHomeFrame extends JFrame implements ActionListener
{
    private JButton doctorBtn, patientBtn;
    private JButton bookAppointmentBtn, appointmentBtn;
    private JButton medicalRecordsBtn, paymentBtn;
    private JButton updateProfileBtn, viewProfileBtn;
    private JButton resetPasswordBtn, logoutBtn;

    private JPanel panel;

    private User u;

    public PatientHomeFrame(User u)
    {
        super("Patient Home Frame");

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

        this.bookAppointmentBtn = new JButton("Book Appointment");

        this.bookAppointmentBtn.setBounds(50, 150, 300, 30);

        this.bookAppointmentBtn.addActionListener(this);
        this.panel.add(bookAppointmentBtn);

        this.appointmentBtn = new JButton("Appointment List");

        this.appointmentBtn.setBounds(400, 150, 300, 30);

        this.appointmentBtn.addActionListener(this);
        this.panel.add(appointmentBtn);

        this.medicalRecordsBtn = new JButton("Medical Records");

        this.medicalRecordsBtn.setBounds(50, 250, 300, 30);

        this.medicalRecordsBtn.addActionListener(this);
        this.panel.add(medicalRecordsBtn);

        this.paymentBtn = new JButton("Payment");

        this.paymentBtn.setBounds(400, 250, 300, 30);

        this.paymentBtn.addActionListener(this);
        this.panel.add(paymentBtn);

        this.viewProfileBtn = new JButton("View Profile");

        this.viewProfileBtn.setBounds(50, 350, 300, 30);

        this.viewProfileBtn.addActionListener(this);
        this.panel.add(viewProfileBtn);

        this.updateProfileBtn = new JButton("Update Profile");

        this.updateProfileBtn.setBounds(400, 350, 300, 30);

        this.updateProfileBtn.addActionListener(this);
        this.panel.add(updateProfileBtn);

        this.resetPasswordBtn = new JButton("Reset Password");

        this.resetPasswordBtn.setBounds(220, 420, 300, 30);

        this.resetPasswordBtn.addActionListener(this);
        this.panel.add(resetPasswordBtn);

        this.logoutBtn = new JButton("Logout");

        this.logoutBtn.setBounds(220, 480, 300, 30);

        this.logoutBtn.addActionListener(this);
        this.panel.add(logoutBtn);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(doctorBtn.getText()))
        {
            PDoctorListFrame pdlf = new PDoctorListFrame(this.u);

            this.setVisible(false);
            pdlf.setVisible(true);
        }

        else if(command.equals(patientBtn.getText()))
        {
            PPatientListFrame pplf = new PPatientListFrame(this.u);

            this.setVisible(false);
            pplf.setVisible(true);
        }

        else if(command.equals(bookAppointmentBtn.getText()))
        {
            PBookAppointmentFrame pbaf = new PBookAppointmentFrame(this.u);

            this.setVisible(false);
            pbaf.setVisible(true);
        }

        else if(command.equals(appointmentBtn.getText()))
        {
            PAppointmentFrame paf = new PAppointmentFrame(this.u);

            this.setVisible(false);
            paf.setVisible(true);
        }

        else if(command.equals(medicalRecordsBtn.getText()))
        {
            PMedicalRecordsFrame pmrf = new PMedicalRecordsFrame(this.u);

            this.setVisible(false);
            pmrf.setVisible(true);
        }

        else if(command.equals(paymentBtn.getText()))
        {
            PPaymentFrame ppf = new PPaymentFrame(this.u);

            this.setVisible(false);
            ppf.setVisible(true);
        }

        else if(command.equals(viewProfileBtn.getText()))
        {
            PViewProfileFrame pvpf = new PViewProfileFrame(this.u);

            this.setVisible(false);
            pvpf.setVisible(true);
        }

        else if(command.equals(updateProfileBtn.getText()))
        {
            PUpdateProfileFrame pupf = new PUpdateProfileFrame(this.u);

            this.setVisible(false);
            pupf.setVisible(true);
        }

        else if(command.equals(resetPasswordBtn.getText()))
        {
            PResetPasswordFrame prpf = new PResetPasswordFrame(this.u);

            this.setVisible(false);
            prpf.setVisible(true);
        }

        else if(command.equals(logoutBtn.getText()))
        {
            LoginFrame lf = new LoginFrame();

            this.setVisible(false);
            lf.setVisible(true);
        }
    }
}