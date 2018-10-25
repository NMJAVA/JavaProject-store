import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;



public class ChatGUI extends GUIFunctinos{
	
	private JPanel mainPanel;
	private JTextArea chatBox;
	private JLabel textFieldLabel;
	private JTextArea textBox;
	private JButton sendBtn;
	private JButton test;
	private Employee employee;
	private String text=null;
	boolean messageReady=false;
	
	Socket socket;
	DataInputStream fromNetInputStram;
	PrintStream toNetOutputStream;
	
	public ChatGUI(Socket socket,DataInputStream fromNetInputStram,PrintStream toNetOutputStream,Employee loggedInUser){
		
		
		
		this.socket=socket;
		this.fromNetInputStram=fromNetInputStram;
		this.toNetOutputStream=toNetOutputStream;
		
		
		//Setting the frame size
		this.setSize(500,600);
		
		//Setting the frame default location in the middle of the screen
		this.setLocationRelativeTo(null);
		//Disabling the option to change the size of the frame
		this.setResizable(false);
		//Setting the option to close the window using the X button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Setting the title of the frame
		this.setTitle("Chat");
		
		mainPanel=new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setBorder(new EmptyBorder(10,10,10,10));
		employee= loggedInUser;
		chatBox=new JTextArea(20,32);
		chatBox.setEditable(false);
		chatBox.setLineWrap(true);
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		chatBox.setBorder(blackBorder);
		JScrollPane chatBoxScrollBar= new JScrollPane(chatBox,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		addComp(mainPanel,chatBoxScrollBar,0,0,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
	
		eventForClose closeWindow=new eventForClose();
		this.addWindowListener(closeWindow);
		

		
		textFieldLabel=new JLabel("Enter Your Text Here");
		addComp(mainPanel,textFieldLabel,0,1,2,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
		
		
		textBox=new JTextArea(3,32);
		textBox.setBorder(blackBorder);
		textBox.setLineWrap(true);
		textBox.setWrapStyleWord(true);
		
		ListenForKeys lForText=new ListenForKeys();
		textBox.addKeyListener(lForText);
		JScrollPane textBoxScrollBar= new JScrollPane(textBox,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		addComp(mainPanel,textBoxScrollBar,0,2,2,1,GridBagConstraints.WEST,GridBagConstraints.NONE);

		
		sendBtn=new JButton("Send");
		addComp(mainPanel,sendBtn,0,3,2,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		
		ListenForButton lForSendBtn= new ListenForButton();
		sendBtn.addActionListener(lForSendBtn);
		
		this.add(mainPanel);
		this.setVisible(true);

	}

	
	private class ListenForButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==sendBtn) {
				if(!text.equals("")) {
					try {
						toNetOutputStream.println("chat");

					toNetOutputStream.println(new Date()+"|"+employee.getFirstName()+": "+text);
					System.out.println("11111111");
						chatBox.append(fromNetInputStram.readLine()+'\n');
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textBox.setText("");
				}
			}
		
				//send message from string
		
	}
	}
		
	private class ListenForKeys implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			text=textBox.getText();

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
}

	public void printMessage(String line) {
		chatBox.append(line+'\n');
		setMessageReady(false);
	}
	
	public boolean getMessageReady()
	{
		return messageReady;
	}

	public void setMessageReady(boolean val) {
		messageReady=val;
	}

	public String getText() {
		String res=text;
		text=null;
		return res;
	}
	
	 private class eventForClose implements WindowListener {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}
			MainMenu mainMenu=new MainMenu(socket,fromNetInputStram,toNetOutputStream,employee);

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		 
		 }
	
}


