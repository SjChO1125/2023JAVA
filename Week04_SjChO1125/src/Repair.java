import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Repair extends JPanel implements ItemListener{
	JCheckBox[] buttons=new JCheckBox[4];
	String[] items= {"엔진 오일 교환","자동변속기오일교환","에어콘필터교환","타이어 교환"};
	int[] prices= {45000,80000,30000,100000};
	int money=0;
	JLabel label;
	
	public Repair() {
		super();
		for (int i=0; i<4; i++) {
			buttons[i]=new JCheckBox(items[i]);
			buttons[i].addItemListener(this);
		
		}
		
		  for (int i=0; i<4; i++) 
		 
			  add(buttons[i]);
		      label=new JLabel("현재까지의 가격은"+money+"입니다."); label.setFont(new
		      Font("Serif",Font.BOLD|Font.ITALIC,30));
		  
		      add(label); 
		  
		 
	}
		
		public void itemStateChanged(ItemEvent e) {
			Object source=e.getItemSelectable();
			for(int i=0;i<4;i++) {
				if(source==buttons[i]) {
					if(e.getStateChange()==ItemEvent.DESELECTED)
						money -=prices[i];
					else
						money+=prices[i];
					label.setText("현재까지의 가격은"+money+"입니다.");
					
					
				}
			}
					

		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame=new JFrame("CheckBoxDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent newContentPane=new Repair();
		newContentPane.setVisible(true);
		frame.setContentPane(newContentPane);
		frame.setSize(500,200);
		frame.setVisible(true);

	}


		
	}


