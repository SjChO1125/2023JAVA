import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BusinessCard extends JFrame {
	private JPanel panel;
	private JLabel label1,label2,label3,label4;
	
	public BusinessCard() {
		setTitle("BusinessCard");
		setSize(400,150);
		
		panel=new JPanel();
		label1=new JLabel();
		label1.setText("조서진");
		label2=new JLabel();
		label2.setText("학생");
		label3=new JLabel();
		label3.setText("덕성여자대학교");
		
		label4=new JLabel("");
		ImageIcon icon=new ImageIcon("Card.PNG");
		label4.setIcon(icon);
		
		panel.add(label4);
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		
		add(panel);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BusinessCard b= new BusinessCard();
		

	}

}
