package d1107;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CountDownTest extends JFrame{
	private JLabel label;
	Thread t;
	
	class Counter extends Thread{
		public void run() {
			for(int i=0;i<=10;i++) {
				try {
					Thread.sleep(1000);
					
				}catch(InterruptedException e) {
					/*e.printStackTrace();*/
					return;
				}
				label.setText(i+" ");
			}
		}
	}
	public CountDownTest() {
		setTitle("카운트다운");
		setSize(400,150);
		JPanel panel=new JPanel();
		label=new JLabel("0");
		label.setBounds(0,0,384,111);
		label.setFont(new Font("Serif",Font.BOLD,100));
		getContentPane().add(label);
		
		JButton btnNewButton1=new JButton("카운터 중지");
		//btnNewButton.setBounds(20,10,10,10);
		btnNewButton1.addActionListener(e->t.interrupt());
		//getContentPane().add(btnNewButton);
		panel.add(btnNewButton1);
		getContentPane().add(panel);
		
		JButton btnNewButton2=new JButton("카운터 다시 시작");
		
		btnNewButton2.addActionListener(e->{
		
		if (t!=null) {
			t.interrupt();
		}
		label.setText("0");
		t=new Counter();
		t.start();});
		
		
		panel.add(btnNewButton2);
		getContentPane().add(panel);
		
		
		setVisible(true);
		t=new Counter();
		t.start();
		//add(label);
		
		//(new MyThread()).start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountDownTest t= new CountDownTest();

	}

}
