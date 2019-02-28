/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg020_mortgage_calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author compsci
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        new Calculator();
        
        
        
    }
    
    static class Calculator implements ActionListener {
        JFrame frame;
        JTextField P, r, n, Deposit, APR, Monthly, CompoundPerYear, PMT;
        JLabel PL, rL, nL, DepositL, APRL, MonthlyL, CompoundPerYearL, PMTL, amortOut, PayoutOut;
        JButton calcAmort, calcPayout;
        
        Calculator() {
            frame = new JFrame("Calculator");
            
            P = new JTextField("");
            r = new JTextField("");
            n = new JTextField("");
            calcAmort = new JButton("Monthly Interest Rate");        
            
            Deposit = new JTextField("");
            APR = new JTextField("");
            Monthly = new JTextField("");
            CompoundPerYear = new JTextField("");
            PMT = new JTextField("");
            calcPayout = new JButton("Payout");
            
            PL = new JLabel("Principle");
            rL = new JLabel("Monthly Interest Rate");
            nL = new JLabel("Number of Monthly Payments");
            amortOut = new JLabel("");
            DepositL = new JLabel("Inital Deposit");
            APRL = new JLabel("Annual Rate (Yearly)");
            MonthlyL = new JLabel("# of Years");
            CompoundPerYearL = new JLabel("# of Compounds per year");
            PMTL = new JLabel("Monthly Payments (optional)");
            PayoutOut = new JLabel("");
            
            //Left
            
            PL.setBounds(30,10,210,30);
            P.setBounds(30,40,210,30);
            
            rL.setBounds(30,70,210,30);
            r.setBounds(30,100,210,30);
            
            nL.setBounds(30,130,210,30);
            n.setBounds(30,160,210,30);
            
            calcAmort.setBounds(30,220,210,30);
            amortOut.setBounds(30,260,210,30);
            
            //Right
            
            DepositL.setBounds(270,10,210,30);
            Deposit.setBounds(270,40,210,30);
            
            APRL.setBounds(270,70,210,30);
            APR.setBounds(270,100,210,30);
            
            MonthlyL.setBounds(270,130,210,30);
            Monthly.setBounds(270,160,210,30);
            
            CompoundPerYearL.setBounds(270,190,210,30);
            CompoundPerYear.setBounds(270,220,210,30);
            
            PMTL.setBounds(270,250,210,30);
            PMT.setBounds(270,280,210,30);
            
            calcPayout.setBounds(270,320,210,30);
            PayoutOut.setBounds(270,360,210,30);
            
            frame.add(PL);
            frame.add(P);
            
            frame.add(rL);
            frame.add(r);
            
            frame.add(nL);
            frame.add(n);
            
            frame.add(calcAmort);
            frame.add(amortOut);
            
            frame.add(DepositL);
            frame.add(Deposit);

            frame.add(APRL);
            frame.add(APR);

            frame.add(MonthlyL);
            frame.add(Monthly);
            
            frame.add(CompoundPerYearL);
            frame.add(CompoundPerYear);
            
            frame.add(PMTL);
            frame.add(PMT);

            frame.add(calcPayout);
            frame.add(PayoutOut);            
            
            calcAmort.addActionListener(this);
            calcPayout.addActionListener(this);
            
            frame.setLayout(null);
            frame.setVisible(true);
            
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setTitle("Advanced Mortgage Calculator");
            frame.setSize(540, 430);
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == calcAmort) {
                
                amortOut.setText(calculateAmort(P.getText(),r.getText(),n.getText()));
            } else if (e.getSource() == calcPayout) {
                if(PMT.getText().equals("") || PMT.getText().equals(null)) {
                    PayoutOut.setText(calculatePayout(Deposit.getText(), APR.getText(), Monthly.getText(), CompoundPerYear.getText()));
                } else {
                    PayoutOut.setText(calculatePayout(Deposit.getText(), APR.getText(), Monthly.getText(), CompoundPerYear.getText(), PMT.getText()));
                }
            }
        }

           
    }
    
    /**
     * AmmortCalculator
     * 
     * Calculates monthly payment
     * 
     * @author compsci
     * 
     * 
     * @param Pstring String for principle
     * @param rstring String interest rate (monthly)
     * @param nstring String for number of payments (monthly)
     * @return 
     */
    private static String calculateAmort(String Pstring, String rstring, String nstring) {
        double P,r,n;
        double result = 0;
        try {
            Double.valueOf(Pstring);
            Double.valueOf(rstring);
            Double.valueOf(nstring);
        } catch (Exception e) {
            return "numbers only, please";
                
        }
            
        P = Double.valueOf(Pstring);
        r = Double.valueOf(rstring);
        n = Double.valueOf(nstring);
        
        result = P * ((r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1));                     //This thing is a bracket off from being FUBAR. Do not touch.
        
        System.out.println(result);
        
        return "$" + result;
    }
    
    private static String calculatePayout(String Pstring, String rstring, String tstring, String nstring) {
        double P,r,t,n;
        double result = 0;
        try {
            Double.valueOf(Pstring);
            Double.valueOf(rstring);
            Double.valueOf(tstring);
            Double.valueOf(nstring);
        } catch (Exception e) {
            return "numbers only, please";
                
        }
            
        P = Double.valueOf(Pstring); //principle (large values)
        r = Double.valueOf(rstring); //rate (0.00x)
        t = Double.valueOf(tstring); //number of years
        n = Double.valueOf(nstring); //compounds per year (1 or 12, usually)
        
        result = P * (Math.pow(1 + (r/n), n*t));
        //System.out.println(result);
        
        return "$" + result;
    }
    
    private static String calculatePayout(String Pstring, String rstring, String tstring, String nstring, String PMTstring) {
        double P,r,t,n, PMT;
        double result = 0;
        try {
            Double.valueOf(Pstring);
            Double.valueOf(rstring);
            Double.valueOf(tstring);
            Double.valueOf(nstring);
            Double.valueOf(PMTstring);
        } catch (Exception e) {
            return "numbers only, please";
                
        }
            
        P = Double.valueOf(Pstring); //principle (large values)
        r = Double.valueOf(rstring); //rate (0.00x)
        t = Double.valueOf(tstring); //number of years
        n = Double.valueOf(nstring); //compounds per year (1 or 12, usually)
        PMT = Double.valueOf(PMTstring);
        
        result = (P * (Math.pow(1 + (r/n), n*t))) + (PMT*((Math.pow(1+(r/n), n*t)-1) / (r/n)));         //Jesus Christ.
        //System.out.println(result);
        
        return "$" + result;
    }
    
}
