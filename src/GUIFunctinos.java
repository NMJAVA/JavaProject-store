import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
//Function fot the gui classs
public abstract class GUIFunctinos extends JFrame{
	
	//adding new component to the gridbad layout
	protected void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch){		
        GridBagConstraints gridConstraints = new GridBagConstraints();

         

        gridConstraints.gridx = xPos;		
        gridConstraints.gridy = yPos;	
        gridConstraints.gridwidth = compWidth;		
        gridConstraints.gridheight = compHeight;		
        gridConstraints.weightx = 100;		
        gridConstraints.weighty = 100;		
        gridConstraints.insets = new Insets(5,5,5,5);		
        gridConstraints.anchor = place;		
        gridConstraints.fill = stretch;

        thePanel.add(comp, gridConstraints);
    }
	//check for boolean lettes
	public boolean lttersOnly(String str) {
		for (char c : str.toCharArray()) {
		    if ((!Character.isLetter(c))&&!Character.isSpace(c)) {
		    	System.out.print(c);
		    	return false;
		    }
		}
		 return true;
	}
	//Check for digit only
	public boolean digitOnly(String str) {
		for (char c : str.toCharArray()) {
		    if (!Character.isDigit(c)) {
		    	System.out.print(c);
		    	return false;
		    }
		}
		 return true;
	}
	
	
}
