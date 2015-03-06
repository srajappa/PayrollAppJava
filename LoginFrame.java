import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.net.*;
import javax.swing.*;
import java.util.Date;

public class LoginFrame extends JFrame implements ActionListener {
    
    static JFrame frame;
    private String username;
    private String password;
    private static JFrame loginFrame;
    private static JPanel panel1;
    private static JPanel panel2;
    private static JPanel panel3;
    private JButton loginBtn;
    private JButton exitBtn;
    int dialogtype = JOptionPane.PLAIN_MESSAGE;
    String dialogmessage;
    String dialogs;
    private JLabel nameLbl;
    private JLabel userLbl;
    private JLabel passwordLbl;
    private static JTextField userTxt;
    private static JPasswordField passwordTxt;//similar to JTextField
    
    public String loginname;
    public String loginpass;
    
    // class Variables
    clsConnection connect = new clsConnection();
    //Connection variable
    
    Connection conn;
    	Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
    
    static Date td = new Date();	
    
    public LoginFrame()
    {
    
   //Provides the Login screen the layout scheme	  
   panel1 = new JPanel();
   panel1.setLayout(new FlowLayout());
   nameLbl = new JLabel("Payroll Management System");

    
   panel2 = new JPanel();
   panel2.setLayout(new GridLayout(2,2));
   userLbl = new JLabel("Username :");
   userTxt = new JTextField(20);
  
   passwordLbl = new JLabel("Password :");
   
   passwordTxt = new JPasswordField(20);
   
   panel3 = new JPanel();
   panel3.setLayout(new FlowLayout());
   
   loginBtn = new JButton("Login");
   
   loginBtn.addActionListener(this);//Shows that the button will be pushed
   exitBtn = new JButton("Exit");
   // this section completely deals with the input into these the fields
   exitBtn.addActionListener(this);
	panel1.add(nameLbl);
	panel1.setOpaque(true);
    panel2.add(userLbl);
	panel2.add(userTxt);
	panel2.add(passwordLbl);
	panel2.add(passwordTxt);
	panel2.setOpaque(true);
   	panel3.add(loginBtn);
	panel3.add(exitBtn);
	panel3.setOpaque(true);
	frame = new JFrame("PayRoll User Login...");
       frame.setSize(300,200);
        
	Container pane = frame.getContentPane();   
	pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
    
	pane.add(panel1);
	pane.add(panel2);
	pane.add(panel3);
	frame.setLocation((screen.width - 500)/2,((screen.height-350)/2));	
    frame.setVisible(true);
    frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
     


    }
    
       public void actionPerformed(ActionEvent event)
    {
        Object source = event.getSource();
        if(source.equals(loginBtn))
        {
           login();
           
        } 
        else if(source.equals(exitBtn))
        {
            		System.exit(0);
        }
    }
        
        public void login()
        {
        	loginname = userTxt.getText().trim();
           	loginpass = passwordTxt.getText().trim();
               //input takes place into the above variables as you enter into the blank text-area
 
                         
                   
            try {
                conn = connect.setConnection(conn,"","");
            }
            catch(Exception e)
            {
            }
            try{
             	
             
                Statement stmt = conn.createStatement();
                
                String query = "SELECT * FROM Login WHERE USERNAME='" + loginname + 
                        "'AND PASSWORD='"+loginpass+"'";
                ResultSet rs = stmt.executeQuery(query);
                boolean recordfound = rs.next();
                if (recordfound)
                {
                	
                	
                    MainMenu menu = new MainMenu(loginname,td); // Calling the next link that is Main Menu
                  	
                	
                }
                else
                {
                	dialogmessage = "Login Failed!";
                    JOptionPane.showMessageDialog(null, "INVALID ID OR PASSWORD!",
                            "WARNING!!",JOptionPane.WARNING_MESSAGE);
                    
                    userTxt.setText("");
                    passwordTxt.setText("");
                }
                conn.close();
        }
        catch(Exception ex)
          {
          	JOptionPane.showMessageDialog(null,"GENERAL EXCEPTION", "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
          	}		
    }
    
    
    public static void main(String[] args)
    {
        
        LoginFrame frame1 = new LoginFrame();
		frame1.addNotify();
		frame1.pack();
                     
	
    }
   
    
    
}

