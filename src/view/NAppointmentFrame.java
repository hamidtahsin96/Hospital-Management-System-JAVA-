package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class NAppointmentFrame extends JFrame implements ActionListener
{
    private JLabel titleLabel;

    private JButton backBtn;

    private JTable appointmentTable;
    private JScrollPane appointmentTableSP;

    private JPanel panel;

    private User u;

    public NAppointmentFrame(User u)
    {
        super("Appointment List Frame");

        this.u = u;

        this.setSize(900, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.titleLabel = new JLabel("Appointment List");

        this.titleLabel.setBounds(380, 40, 150, 30
        );

        this.panel.add(titleLabel);

        AppointmentController ac = new AppointmentController();

        Appointment appointmentList[] = ac.getAllAppointment();

        String appointmentInfo[][] = new String[appointmentList.length][6];

        int row = 0;

        for(int i = 0; i < appointmentList.length; i++)
        {
            if(appointmentList[i] != null)
            {
                appointmentInfo[row][0] = appointmentList[i].getAppointmentId();
                appointmentInfo[row][1] = appointmentList[i].getDoctor().getUserId();
                appointmentInfo[row][2] = appointmentList[i].getPatient().getUserId();
                appointmentInfo[row][3] = appointmentList[i].getDate();
                appointmentInfo[row][4] = appointmentList[i].getTime();
                appointmentInfo[row][5] = appointmentList[i].getStatus();

                row++;
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

        this.appointmentTableSP.setBounds(50, 100, 800, 300);

        this.appointmentTable.setEnabled(false);

        this.panel.add(appointmentTableSP);

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