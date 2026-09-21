package view;

import javax.swing.*;
import model.*;

public class ReceptionistHomeFrame extends JFrame
{
    private JLabel welcomeLabel;

    public ReceptionistHomeFrame(User u)
    {
        super("Receptionist Home");

        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.welcomeLabel = new JLabel("Welcome " + u.getName());

        this.add(welcomeLabel);
    }
}