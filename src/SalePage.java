import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;


public class SalePage extends GUIFunctinos{

	private JPanel mainPanel;
	private JPanel buttons;
	private JButton addBtn;
	private JButton removeBtn;
	private JButton sellBtn;
	private JTable itemTable;
	private JPanel tablePanel;
	private DefaultTableModel model;
	private JTextField barcodeText;
	private JLabel barcodeLabel;
	private JLabel quantityLabel;
	private JLabel customerLabel;
	private JTextField customerText;
	private JTextField quantityText;
	

	
	private String barcode;
	private String quantity;
	Employee employee;
	private String customerEmail="";
	private int lastRow=-1;
	private int selectedRow=-1;
	private String tempProductString; 
	private int amount;
	private int discountValue;
	Socket socket;
	DataInputStream fromNetInputStram;
	PrintStream toNetOutputStream;
	
	Vector<Product> product= new Vector<Product>();
	
	
	public SalePage(Socket socket,DataInputStream fromNetInputStram,PrintStream toNetOutputStream) {
		
		this.socket=socket;
		this.fromNetInputStram=fromNetInputStram;
		this.toNetOutputStream=toNetOutputStream;
		
		//Setting the frame size
		this.setSize(700,500);
		//Setting the frame default location in the middle of the screen
		this.setLocationRelativeTo(null);
		//Disabling the option to change the size of the frame
		this.setResizable(false);
		//Setting the option to close the window using the X button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Setting the title of the frame
		this.setTitle("Sale");
		
		mainPanel=new JPanel();
	//	mainPanel.setLayout(new GridBagLayout());
		 String[] columns = {"barcode", "Name", "Size","Price","Quantity"};
		
		 tablePanel=new JPanel();
		// int numRows = 0;
		 model = new DefaultTableModel()  {
		      public boolean isCellEditable(int rowIndex, int mColIndex) {
		          return false;
		        }
		      };
		 model.setColumnIdentifiers(columns);
		 itemTable = new JTable(model);
		 JScrollPane scroll = new JScrollPane(itemTable);
			Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
			scroll.setBorder(blackBorder);
			
		tablePanel.add(scroll);
		addComp(mainPanel,tablePanel,0,0,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
			
		buttons =new JPanel();
		buttons.setLayout(new GridLayout(0,1,2,2));

		barcodeLabel= new JLabel("barcode");
		buttons.add(barcodeLabel);		
		
		barcodeText=new JTextField(10);
		buttons.add(barcodeText);
		
		quantityLabel=new JLabel("Quantity");
		buttons.add(quantityLabel);
			
		quantityText=new JTextField(5);
		buttons.add(quantityText);
		ListenForKeys lForQuantity=new ListenForKeys();
		quantityText.addKeyListener(lForQuantity);
		barcodeText.addKeyListener(lForQuantity);
	
		 customerLabel=new JLabel("Customer email");
		 buttons.add(customerLabel);
		 
		  customerText=new JTextField(15);
		  buttons.add(customerText);
			customerText.addKeyListener(lForQuantity);
		addBtn=new JButton("Add");
		buttons.add(addBtn);
		removeBtn=new JButton("Remove");
		buttons.add(removeBtn);

		
		sellBtn=new JButton("Sell");
		buttons.add(sellBtn);
		
		addBtn.setAlignmentX(Component.CENTER_ALIGNMENT); 
		removeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		sellBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		addComp(mainPanel,buttons,1,0,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		
		ListenForButtons lForSendBtn= new ListenForButtons();
		addBtn.addActionListener(lForSendBtn);
		removeBtn.addActionListener(lForSendBtn);
		sellBtn.addActionListener(lForSendBtn);
		
		EmployeeHelper EmployeeHelper = new EmployeeHelper();
		employee = EmployeeHelper.login(new Email("niv@gmail.com"), "111111111");
		
		this.add(mainPanel);
		this.setVisible(true);
	}
	
	
	private class ListenForKeys implements KeyListener{
	

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			barcode=barcodeText.getText();
			quantity=quantityText.getText();
			customerEmail=customerText.getText();
		}

		@Override
		public void keyTyped(KeyEvent e) {
			char input=e.getKeyChar();
			if(e.getComponent()==quantityText||e.getComponent()==barcodeText)
			{
			if(!(Character.isDigit(input)))
			{
				e.consume();
			}
			}
		}
	}
	
	private class ListenForButtons implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource()==addBtn) {
				toNetOutputStream.println("add");
				try {
					String apply=fromNetInputStram.readLine();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				int index=serchBySku(product,barcode);
				if(!barcode.equals("")) {
					if(quantity.equals("")) {
						amount=1;
					}
					else {
						amount=Integer.parseInt(quantity);
					}
					if(index!=-1) {
					
							int extraAmount=(int) model.getValueAt(index, 4)+amount;
							product.elementAt(index).setAmount(extraAmount);
							itemTable.setValueAt(extraAmount, index, 4);
							barcodeText.setText("");
							barcode="";
							quantityText.setText("");
							quantity="";
					
				}
					else {
				System.out.println(barcode);
				toNetOutputStream.println(barcode);
				try {
					tempProductString=fromNetInputStram.readLine();
				
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			
			
				if(!(tempProductString.equals("false")))
				{
					
				    lastRow++;
				product.addElement(stringToProduct(tempProductString,amount));
				Object[] row=new Object[5];
				row[0] = product.elementAt(lastRow).getSKU();
                row[1] = product.elementAt(lastRow).getName();
                row[2] = product.elementAt(lastRow).getSize();
                row[3] = product.elementAt(lastRow).getPrice();
                row[4] = product.elementAt(lastRow).getAmount();
            
                model.addRow(row);
					barcodeText.setText("");
					barcode="";
					quantityText.setText("");
					quantity="";
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Barcode");
				}
				}}
				else {
					JOptionPane.showMessageDialog(null, "Barcode is empty");
				}
			}
			
			if(e.getSource()==removeBtn) {
				selectedRow=itemTable.getSelectedRow();
				if(lastRow>-1)
				{
				model.removeRow(selectedRow);
				 product.remove(selectedRow);
				selectedRow=-1;
				lastRow--;
				}
			}
			if(e.getSource()==sellBtn) {
				if(customerEmail.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Customer Email is empty");
				}
				else {
				toNetOutputStream.println("sell");
				try {
					String apply=fromNetInputStram.readLine();
					Address tempAdress=employee.getObjectAddress();
					String temp=employee.getFirstName()+"|"+employee.getLastName()+"|"+tempAdress.getCity()+"|"+tempAdress.getStreet()+"|"+tempAdress.getHouseNumber()+"|"+employee.getPhone()+"|"+employee.getEmail();
					toNetOutputStream.println(temp);
					   System.out.println(product.size());
					   
					toNetOutputStream.println(customerEmail);
				apply=fromNetInputStram.readLine();
				System.out.println(apply);
				if(apply.equals("true")) {
				for(int i=0;i<product.size();i++) {
					
					toNetOutputStream.println(product.elementAt(i).getSKU());
					toNetOutputStream.println(product.elementAt(i).getAmount());
				}
				toNetOutputStream.println("end");
				}
				else if(apply=="false") {
					JOptionPane.showMessageDialog(null, "Invalid Client");
				
				}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
			}
		}
	}

	
	
	
	static Product stringToProduct(String customerDetails,int amount){
		
		String sku;
		String name;
		String type;
		String size;
		String tempString;
		int price;

		
		
		 sku=customerDetails.substring(0, customerDetails.indexOf('|'));	
		 customerDetails=customerDetails.substring(customerDetails.indexOf('|')+1, customerDetails.length());
		 
		 name=customerDetails.substring(0, customerDetails.indexOf('|'));
		 customerDetails=customerDetails.substring(customerDetails.indexOf('|')+1, customerDetails.length());
		 
		 type=customerDetails.substring(0, customerDetails.indexOf('|'));
		 customerDetails=customerDetails.substring(customerDetails.indexOf('|')+1, customerDetails.length());
		 
		 size=customerDetails.substring(0, customerDetails.indexOf('|'));
		 customerDetails=customerDetails.substring(customerDetails.indexOf('|')+1, customerDetails.length());
		 
		 
		 tempString =customerDetails;

		 
		 price = Integer.parseInt(tempString);
		 

		
		 Product newProduct   = new Product(sku,name, type, size, price , amount);
		return newProduct;
	}
	
	int serchBySku(Vector<Product> product,String sku) {
		for(int i=0;i<product.size();i++)
		{
			if(product.get(i).getSKU().equals(sku))
			return i;

		}
		return -1;
		
	}
	


}
