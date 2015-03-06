import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.*;
import java.lang.*;
import java.beans.PropertyVetoException;


public class MainMenu extends JFrame implements ActionListener{
JDesktopPane desktop = new JDesktopPane();
String sMSGBOX_TITLE	= "Payroll System V. 1.0";


// Menu Bar Variables

JMenuBar menubar = new JMenuBar();

JMenu menuFile = new JMenu("File");
JMenu menuEmployee = new JMenu("Employee");
JMenu menuTools = new JMenu("Tools");
JMenu menuReports = new JMenu("Reports");


// Menu Item

JMenuItem itemExit = new JMenuItem();//Adding item to file

JMenuItem itemAdd = new JMenuItem();//Adding items to Employee
JMenuItem itemEdit = new JMenuItem();
JMenuItem itemDelete = new JMenuItem();

JMenuItem itemSettings = new JMenuItem();//Adding items to Tools


JMenuItem itemEmprpt = new JMenuItem();//Adding item to Reports



JMenuItem itemCredits=new JMenuItem();//Adding credits item to Credit



// JPanel 

JPanel panel_Bottom = new JPanel();
JPanel panel_Top = new JPanel();

// Label 


// TextField
JTextField username = new JTextField();
JTextField logtime = new JTextField();

// JInternalFrame variables

Addwindow FormAddwindow;
Editwindow FormEditwindow;
Deletewindow FormDeletewindow;


Emprptwindow FormEmprptwindow;

Settingswindow FormSettingswindow;

// Connection Variables

Connection conn;

// Date variables

static Date td  = new Date();

// String Variables

static Statement stmtLogin;


//Class Variables
clsSettings settings 	= new clsSettings();

//// User Details
	static String sUser		= "";
	static String sLogin 	= DateFormat.getDateTimeInstance().format(td);
	 
	
    public MainMenu(String user, Date date) {
     		super("PayRoll Accounting System JIET ");//Check the top Screen
     sUser = user;
     td = date; 
    
    JTextField username = new JTextField();
    username.setEditable(false);
	JTextField logtime = new JTextField();
	logtime.setEditable(false);
	username.setText(sUser);
	logtime.setText(sLogin);
    

     
     panel_Top.setLayout(new BorderLayout());
     panel_Top.setPreferredSize(new Dimension(10,65));
     
          
     desktop.setBackground(Color.WHITE);//The backround configurations 
     desktop.setAutoscrolls(true);
     desktop.setBorder(BorderFactory.createLoweredBevelBorder());
     desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
   
     getContentPane().add(panel_Top,BorderLayout.PAGE_START);
     getContentPane().add(desktop,BorderLayout.CENTER);
     getContentPane().add(panel_Bottom,BorderLayout.PAGE_END);
     
     
     
     addWindowListener(new WindowAdapter(){
         
     public void windowClosing(WindowEvent e)
     {
         UnloadWindow();
     }
     });
     
     setJMenuBar(CreateJMenuBar());
     setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
     setIconImage(new ImageIcon("images/logo-jiet[1].gif").getImage());
     setSize(700,700);
     setLocation(2,2);
     show();
        
    }
    
    protected JMenuBar CreateJMenuBar()
    {
       
        
        // creating Submenu
        // Menu File
        menuFile.add(settings.setJMenuItem(itemExit,"Quit","images/exit.png"));
        
        itemExit.addActionListener(this);
        
        // MEnu Employee
        menuEmployee.add(settings.setJMenuItem(itemAdd,"Add Employee","images/employee.png"));
        menuEmployee.add(settings.setJMenuItem(itemEdit,"Edit Employee","images/edit.png"));
        menuEmployee.addSeparator();
        menuEmployee.add(settings.setJMenuItem(itemDelete,"Delete Employee","images/delete.png"));
        
        
        itemAdd.addActionListener(this);
        itemEdit.addActionListener(this);
        itemDelete.addActionListener(this);
        
        // setting tool bar
        menuTools.add(settings.setJMenuItem(itemSettings,"Settings","images/setting.png"));
        
        
        itemSettings.addActionListener(this);
        
        // setting Reports bar
          
        menuReports.add(settings.setJMenuItem(itemEmprpt,"Employee Report","images/emp_rpt.png"));
        itemEmprpt.addActionListener(this);
        
        // setting Help
       
               
        
        // adding menuitem to menubar
        
        
        menubar.add(settings.setJMenu(menuFile));
        menubar.add(settings.setJMenu(menuEmployee));
        menubar.add(settings.setJMenu(menuTools));
        menubar.add(settings.setJMenu(menuReports));
       return menubar;

       //This would be so unecessary

    }
    
    
    ActionListener JToolBarActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            String source = e.getActionCommand();
            
            if (source == "File_Exit")
            {
                loadJInternalFrame(2);
            }
            else if (source == "Emp_Add")
            {
                loadJInternalFrame(3);                
            }
            else if (source == "Emp_Edit")
            {
                loadJInternalFrame(4);                
            }
            else if (source == "Emp_Delete")
            {
                loadJInternalFrame(5);                
            }
            else if (source == "Settings")
            {
                loadJInternalFrame(6);                
            }
            else if (source == "Tools_Calculator")
            {
                loadJInternalFrame(7);                
            }
            else if (source == "Tools_NotePad")
            {
                loadJInternalFrame(8);
            }
            else if (source == "Reports_Employee")
            {
                loadJInternalFrame(9);
            }
            
            else if (source == "Help_Author")
            {
                loadJInternalFrame(11);
            }
            else if (source == "Help_Help")
            {
                loadJInternalFrame(12);
            }
            else if (source =="Credits_Credits")
            {  loadJInternalFrame(13);
            }
        }
    
    };
    
      
    public void actionPerformed(ActionEvent event)
    {
        Object object = event.getSource();
        
        if (object ==  itemExit)
        {
            loadJInternalFrame(2);
        }
        else if (object == itemAdd)
        {
            loadJInternalFrame(3);
        }
        else if ( object == itemEdit)
        {
            loadJInternalFrame(4);
        }
        else if (object == itemDelete)
        {
            loadJInternalFrame(5);
        }
        else if (object == itemSettings)
        {
            loadJInternalFrame(6);
        }
        else if (object == itemEmprpt)
        {
            loadJInternalFrame(7);
        }
        
        
    }
    private void loadJInternalFrame(int intWhich)//this would run the new frames including the addwindow... etc.
    {
        switch(intWhich)
        {
            
            case 2:
                System.exit(0);
                break;
            
            case 3:
                try {
                	FormAddwindow = new Addwindow(this);
               loadForm("Add Employee", FormAddwindow);
                }
                catch(Exception e)
                {
                	System.out.println("\nError");
                }
                break;
            
            case 4:
                try {
                	FormEditwindow = new Editwindow(this);
               loadForm("Edit Employee", FormEditwindow);
                }
                catch(Exception e)
                {
                	System.out.println("\nError");
                }
                break;
            
            case 5:
                try {
                	FormDeletewindow = new Deletewindow(this);
               loadForm("Delete Employee", FormDeletewindow);
                }
                catch(Exception e)
                {
                	System.out.println("\nError");
                }
                break;
            
            case 6:
                try {
                	FormSettingswindow = new Settingswindow(this);
               loadForm("Settings of Employee", FormSettingswindow);
                }
                catch(Exception e)
                {
                	System.out.println("\nError");
                }
                break;
            
          
            
            case 7:
            	try{
            		FormEmprptwindow = new Emprptwindow(this);
               		loadForm("Employee PaySlip", FormEmprptwindow);
            	
                }
                catch(Exception e)
                {
                	System.out.println("\nError" + e );
                }
                break;
                
               
         
                
                
        }
        
    }
    	protected void runComponents(String sComponents)
	{
		Runtime rt = Runtime.getRuntime();
		try{rt.exec(sComponents);}
		catch(IOException evt){JOptionPane.showMessageDialog(null,evt.getMessage(),"Error Found",JOptionPane.ERROR_MESSAGE);}
	}

protected void loadForm(String Title, JInternalFrame clsForm)
{

boolean xForm = isLoaded(Title);
if (xForm == false)
{
desktop.add(clsForm);
clsForm.setVisible(true);
clsForm.show();
}
else
{
try {
clsForm.setIcon(false);
clsForm.setSelected(true);

}
catch(PropertyVetoException e)
{}
 }
} // Complete Load Form methode


protected boolean isLoaded(String FormTitle)
{
 	JInternalFrame Form[] = desktop.getAllFrames();
	for ( int i = 0; i < Form.length; i++)
	{
	if (Form[i].getTitle().equalsIgnoreCase(FormTitle))
		{
			Form[i].show();
			try
			{
			Form[i].setIcon(false);
			Form[i].setSelected(true);
			
			}
			catch(PropertyVetoException e)
			{
				
				}
			return true;
		}	
	}
	return false;
} // Complete to Verify Form loaded or not

protected void UnloadWindow()//The moment one clicks the cross this popup arises....
{
try
   {
			System.exit(0);
			
   }
	catch(Exception e)
	{}

}// Close the Windows

  
	public static void setlogin(String sUsername, Date sDate)
	{
		sUser  = sUsername;
		td	   = sDate;
		
		
	}//Set Login    
	

    
    
    
}




        

