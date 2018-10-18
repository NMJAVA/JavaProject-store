import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private JButton discountBtn;
	private JButton sellBtn;
	private JTable itemTable;
	private JPanel tablePanel;
	private JTextField barrcodeText;
	
	private JLabel barrcodeLabel;
	private JLabel quantityLabel;
	private JTextField quantityText;
	
	
	
	Product testing=new Product("1" , "Lee Coper delux", "Jeans", "XL", 100 ,2);
	
	public SalePage() {
		//Setting the frame size
		this.setSize(650,500);
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
		 String[] columns = {"Barrcode", "Name", "Size","Price","Quantity"};
		
		 tablePanel=new JPanel();
		 int numRows = 25 ;
		 DefaultTableModel model = new DefaultTableModel(numRows, columns.length) ;
		 model.setColumnIdentifiers(columns);
		 itemTable = new JTable(model);
		 JScrollPane scroll = new JScrollPane(itemTable);
			Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
			scroll.setBorder(blackBorder);
		tablePanel.add(scroll);
		addComp(mainPanel,tablePanel,0,0,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
			
		buttons =new JPanel();
		buttons.setLayout(new GridLayout(0,1,2,2));

		barrcodeLabel= new JLabel("Barrcode");
		buttons.add(barrcodeLabel);		
		
		barrcodeText=new JTextField(10);
		buttons.add(barrcodeText);
		
		quantityLabel=new JLabel("Quantity");
		buttons.add(quantityLabel);
			
		quantityText=new JTextField(5);
		buttons.add(quantityText);
		ListenForKeys lForQuantity=new ListenForKeys();
		quantityText.addKeyListener(lForQuantity);
		barrcodeText.addKeyListener(lForQuantity);
		
		addBtn=new JButton("Add");
		buttons.add(addBtn);
		removeBtn=new JButton("Remove");
		buttons.add(removeBtn);

		discountBtn=new JButton("Discount");
		buttons.add(discountBtn);
		
		sellBtn=new JButton("Sell");
		buttons.add(sellBtn);
		
		addBtn.setAlignmentX(Component.CENTER_ALIGNMENT); 
		removeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		sellBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		addComp(mainPanel,buttons,1,0,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
		
		
		this.add(mainPanel);
		this.setVisible(true);
	}
	
	static public void main(String[] args) {
		new SalePage();
	}
	
	private class ListenForKeys implements KeyListener{
		{
			
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			char input=e.getKeyChar();
			if(!(Character.isDigit(input)))
			{
				e.consume();
			}
			
		}
	}

}
