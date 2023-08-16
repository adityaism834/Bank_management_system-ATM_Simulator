package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Login extends JFrame implements ActionListener{
    JButton clear,login,signup;
    JTextField cardtextField;
    JPasswordField pintextField;
    Login(){
        
        setTitle("Automated Teller Machine - ATM");
        
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 =i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70,10,100,100);
        add(label);
        
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("osward",Font.BOLD,36));
        text.setBounds(200,40,450,40);
        add(text);
        
        JLabel cardno = new JLabel("CARD NO:");
        cardno.setFont(new Font("osward",Font.BOLD,26));
        cardno.setBounds(125,150,375,30);
        add(cardno);
        
         cardtextField = new JTextField();
        cardtextField.setBounds(320,150,250,40);
        cardtextField.setFont(new Font("osward",Font.BOLD,30));
        add(cardtextField);
        
        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("osward",Font.BOLD,26));
        pin.setBounds(180,200,400,40);
        add(pin);
        
         pintextField = new JPasswordField();
        pintextField.setBounds(320,200,250,40);
        add(pintextField);
        
        login = new JButton("SIGN IN");
        login.setBounds(300,300,100,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        
        
        clear = new JButton("CLEAR");
        clear.setBounds(430,300,100,30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);
        
        signup = new JButton("SIGN UP");
        signup.setBounds(370,350,100,30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,500);
        setVisible(true);
        setLocation(350,200);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==clear){
            cardtextField.setText("");
            pintextField.setText("");
        }else if(ae.getSource()==login){
            Conn conn = new Conn();
            String cardnumber = cardtextField.getText();
            String pinnumber = pintextField.getText();
            String query = "select * from login where cardnumber ='"+cardnumber+"'and pin ='"+pinnumber+"' ";
            try{
              ResultSet rs =  conn.s.executeQuery(query);
              if(rs.next()){
                  setVisible(false);
                  new Transactions(pinnumber).setVisible(true);
              }else{
                  JOptionPane.showMessageDialog(null, "Incorrect Card ");
              }
            }catch(Exception e){
                System.out.println(e);
            }
        }else if(ae.getSource()==signup){
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }
    public static void main(String args[]){
     new Login();   
    }
}
