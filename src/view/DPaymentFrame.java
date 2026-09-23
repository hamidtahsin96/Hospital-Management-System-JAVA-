package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class DPaymentFrame extends JFrame implements ActionListener
{
    private JLabel titleLabel;

    private JButton backBtn;

    private JTable paymentTable;
    private JScrollPane paymentTableSP;

    private JPanel panel;

    private User u;

    public DPaymentFrame(User u)
    {
        super("Payment Status Frame");

        this.u = u;

        this.setSize(700, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.titleLabel = new JLabel("Payment Status");

        this.titleLabel.setBounds(300, 40, 150, 30);
        this.panel.add(titleLabel);

        this.showPayments();

        this.backBtn = new JButton("Back");

        this.backBtn.setBounds(300, 430, 100, 30);
        this.backBtn.addActionListener(this);
        this.panel.add(backBtn);

        this.add(panel);
    }

    public void showPayments()
    {
        PaymentController pc = new PaymentController();

        Payment payments[] = pc.getAllPayment();

        String paymentInfo[][] = new String[payments.length][3];

        int j = 0;

        for(int i = 0; i < payments.length; i++)
        {
            if(payments[i] != null)
            {
                if(payments[i].getDoctor() != null)
                {
                    if(payments[i].getDoctor().getUserId().equals(this.u.getUserId()))
                    {
                        paymentInfo[j][0] = payments[i].getPaymentId();

                        paymentInfo[j][1] = payments[i].getPatient().getUserId();

                        paymentInfo[j][2] = String.valueOf(payments[i].getAmount());

                        j++;
                    }
                }
            }
        }

        String head[] =
                {
                        "Payment ID",
                        "Patient ID",
                        "Amount"
                };

        this.paymentTable = new JTable(paymentInfo, head);

        this.paymentTableSP = new JScrollPane(paymentTable);

        this.paymentTableSP.setBounds(50, 100, 600, 280
        );

        this.paymentTable.setEnabled(false);

        this.panel.add(paymentTableSP);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(backBtn.getText()))
        {
            DoctorHomeFrame dhf = new DoctorHomeFrame(this.u);

            this.setVisible(false);
            dhf.setVisible(true);
        }
    }
}