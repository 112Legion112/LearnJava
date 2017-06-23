package ru.fearofcode.learn.io.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Vector;

/**
 * Created by maks on 6/20/2017.
 */
public class TaxGuiFile extends JFrame {
    JLabel lblGrIncome;
    JTextField txtGrossIncome = new JTextField(15);
    JLabel lblDependents = new JLabel("Number if Dependents:");
    JTextField txtDependents = new JTextField(2);
    JLabel lblState = new JLabel("State: ");


    Vector<String> states = new Vector<>(50);

    JComboBox chState = new JComboBox(states);
    JLabel lblTax = new JLabel("State Tax: ");
    JTextField txtStateTax = new JTextField(10);
    JButton bGo = new JButton("Go");
    JButton bReset = new JButton("Reset");


    TaxGuiFile(){
        super("Calculate the tax");
        lblGrIncome = new JLabel("Gross income: ");
        GridLayout gr = new GridLayout(5, 2, 1, 1);
        setLayout(gr);

        add(lblGrIncome);
        add(txtGrossIncome);

        add(lblDependents);
        add(txtDependents);

        add(lblState);
        add(chState);

        add(lblTax);
        add(txtStateTax);

        add(bGo);
        add(bReset);

        // Populate states from a file
        populateStates();

        chState.setSelectedIndex(0);
        txtStateTax.setEditable(false);

        // The Button Go processing using lambda expression
        bGo.addActionListener(evt -> {
            try {
                int grossInc = Integer.parseInt(txtGrossIncome.getText());
                int dependents = Integer.parseInt(txtDependents.getText());
                double tax = grossInc * dependents;

                String states = (String) chState.getSelectedItem();
                switch (states) {
                    case "Florida":
                        tax /= 0.1;
                        break;
                    case "New Yuck":
                        tax /= 0.06;
                        break;
                    case "Kurgan":
                        tax /= 0.15;
                        break;
                    default:
                        JOptionPane.showMessageDialog(this,
                                "The program don't know select city " +
                                "\n Contact your system administrator",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                }
                String sTax = Double.toString(new BigDecimal(tax).setScale(2, RoundingMode.HALF_UP).doubleValue());
                txtStateTax.setText(sTax);

            } catch (NumberFormatException e) {
                txtStateTax.setText("Non-Numeric Date");
            } catch (Exception e) {
                txtStateTax.setText(e.getMessage());
            }
        });

        // The Button Reset processing using lambda expression
        bReset.addActionListener(e -> {
            txtGrossIncome.setText("");
            txtDependents.setText("");
            chState.setSelectedIndex(0);
            txtStateTax.setText("");
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void populateStates() {
        states.add("Select State");
        try (FileInputStream myFile = new FileInputStream("TaxGuiFileStates.data");
             InputStreamReader inputStreamReader = new InputStreamReader(myFile, "UTF8");
             BufferedReader reader = new BufferedReader(inputStreamReader);) {
            String stateName;
            while ((stateName = reader.readLine()) != null) {
                states.add(stateName);
            }
        } catch (IOException e) {
            txtStateTax.setText("Can't read : " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        TaxGuiFile taxFrame = new TaxGuiFile();
        taxFrame.setSize(400, 150);
        taxFrame.setVisible(true);

    }
}
