package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class AAppointmentDetailsFrame extends JFrame implements ActionListener
{
    private JLabel appointmentIdLabel, doctorIdLabel, patientIdLabel;
    private JLabel dateLabel, timeLabel, statusLabel;

    private JTextField appointmentIdTF, doctorIdTF, patientIdTF;
    private JTextField dateTF, timeTF, statusTF;

    private JButton rescheduleBtn, cancelBtn, backBtn;

    private JTable appointmentTable;
    private JScrollPane appointmentTableSP;

    private JPanel panel;

    private User u;

    public AAppointmentDetailsFrame(User u)
    {
        super("Appointment Details Frame");

        this.u = u;

        this.setSize(850, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.appointmentIdLabel = new JLabel("Appointment ID:");
        this.appointmentIdLabel.setBounds(50, 50, 110, 30);
        this.panel.add(appointmentIdLabel);

        this.appointmentIdTF = new JTextField();
        this.appointmentIdTF.setBounds(170, 50, 180, 30);
        this.panel.add(appointmentIdTF);

        this.doctorIdLabel = new JLabel("Doctor ID:");
        this.doctorIdLabel.setBounds(450, 50, 100, 30);
        this.panel.add(doctorIdLabel);

        this.doctorIdTF = new JTextField();
        this.doctorIdTF.setBounds(550, 50, 180, 30);
        this.doctorIdTF.setEnabled(false);
        this.panel.add(doctorIdTF);

        this.patientIdLabel = new JLabel("Patient ID:");
        this.patientIdLabel.setBounds(50, 100, 110, 30);
        this.panel.add(patientIdLabel);

        this.patientIdTF = new JTextField();
        this.patientIdTF.setBounds(170, 100, 180, 30);
        this.patientIdTF.setEnabled(false);
        this.panel.add(patientIdTF);

        this.dateLabel = new JLabel("Date:");
        this.dateLabel.setBounds(450, 100, 100, 30);
        this.panel.add(dateLabel);

        this.dateTF = new JTextField();
        this.dateTF.setBounds(550, 100, 180, 30);
        this.panel.add(dateTF);

        this.timeLabel = new JLabel("Time:");
        this.timeLabel.setBounds(50, 150, 110, 30);
        this.panel.add(timeLabel);

        this.timeTF = new JTextField();
        this.timeTF.setBounds(170, 150, 180, 30);
        this.panel.add(timeTF);

        this.statusLabel = new JLabel("Status:");
        this.statusLabel.setBounds(450, 150, 100, 30);
        this.panel.add(statusLabel);

        this.statusTF = new JTextField();
        this.statusTF.setBounds(550, 150, 180, 30);
        this.statusTF.setEnabled(false);
        this.panel.add(statusTF);

        this.rescheduleBtn = new JButton("Reschedule Appointment");
        this.rescheduleBtn.setBounds(100, 210, 250, 30);
        this.rescheduleBtn.addActionListener(this);
        this.panel.add(rescheduleBtn);

        this.cancelBtn = new JButton("Cancel Appointment");
        this.cancelBtn.setBounds(400, 210, 250, 30);
        this.cancelBtn.addActionListener(this);
        this.panel.add(cancelBtn);

        this.backBtn = new JButton("Back");
        this.backBtn.setBounds(300, 255, 200, 30);
        this.backBtn.addActionListener(this);
        this.panel.add(backBtn);

        this.loadAppointments();

        this.add(panel);
    }

    public void loadAppointments()
    {
        AppointmentController apc = new AppointmentController();

        Appointment appointmentList[] = apc.getAllAppointment();

        String appointmentInfo[][] = new String[appointmentList.length][6];

        for(int i = 0; i < appointmentList.length; i++)
        {
            if(appointmentList[i] != null)
            {
                appointmentInfo[i][0] = appointmentList[i].getAppointmentId();

                appointmentInfo[i][1] = appointmentList[i].getDoctor().getUserId();

                appointmentInfo[i][2] = appointmentList[i].getPatient().getUserId();

                appointmentInfo[i][3] = appointmentList[i].getDate();

                appointmentInfo[i][4] = appointmentList[i].getTime();

                appointmentInfo[i][5] = appointmentList[i].getStatus();
            }
        }

        String head[] =
                {
                        "Appointment ID",
                        "Doctor ID",
                        "Patient ID",
                        "Date",
                        "Time",
                        "Status"
                };

        this.appointmentTable = new JTable(appointmentInfo, head);

        this.appointmentTableSP = new JScrollPane(appointmentTable);

        this.appointmentTableSP.setBounds(50, 310, 750, 350);

        this.appointmentTable.setEnabled(false);

        this.panel.add(appointmentTableSP);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(rescheduleBtn.getText()))
        {
            if(!appointmentIdTF.getText().isEmpty())
            {
                AppointmentController apc = new AppointmentController();

                Appointment ap = apc.searchAppointment(appointmentIdTF.getText());

                if(ap != null)
                {
                    if(!dateTF.getText().isEmpty() && !timeTF.getText().isEmpty())
                    {
                        ap.setDate(dateTF.getText());
                        ap.setTime(timeTF.getText());

                        apc.updateAppointment(ap);

                        JOptionPane.showMessageDialog(this, "Appointment Rescheduled Successfully");

                        this.setVisible(false);

                        AAppointmentDetailsFrame aadf = new AAppointmentDetailsFrame(this.u);

                        aadf.setVisible(true);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Please Provide Date and Time");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "No Appointment Found");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Provide Appointment ID");
            }
        }

        else if(command.equals(cancelBtn.getText()))
        {
            if(!appointmentIdTF.getText().isEmpty())
            {
                AppointmentController apc = new AppointmentController();

                Appointment ap = apc.searchAppointment(appointmentIdTF.getText());

                if(ap != null)
                {
                    ap.setStatus("Cancelled");

                    apc.updateAppointment(ap);

                    JOptionPane.showMessageDialog(this, "Appointment Cancelled Successfully");

                    this.setVisible(false);

                    AAppointmentDetailsFrame aadf = new AAppointmentDetailsFrame(this.u);

                    aadf.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "No Appointment Found");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please Provide Appointment ID");
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