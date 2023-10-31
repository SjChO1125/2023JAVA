package mid1;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class Person{
	String name;
	String stnum;
	String grd;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public String getNum() {
		return stnum;
	}
	public void setNum(String stnum) {
		this.stnum=stnum;
	}
	
	public String getGrade() {
		return grd;
	}
	public void setGrade(String grd) {
		this.grd=grd;
	}
	
	public Person(String name, String stnum, String grd) {
		super();
		this.name=name;
		this.stnum=stnum;
		this.grd=grd;
	}
}
public class Student extends JFrame {
	ArrayList<Person> list=new ArrayList<>();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame=new Student();
					frame.setVisible(true);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		

	}
	public Student() {
		setTitle("덕성여대 화이팅");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,360,252);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel label=new JLabel("학생 등록하기");
		label.setBounds(12,0,57,15);
		contentPane.add(label);
		
		JLabel lblNewLabel=new JLabel("이름");
		lblNewLabel.setBounds(12,20,57,15);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblNewLabel_1=new JLabel("학번");
		lblNewLabel_1.setBounds(12,42,57,15);
		contentPane.add(lblNewLabel_1);
		
		textField=new JTextField();
		textField.setBounds(81,17,243,21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1=new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(81,39,243,21);
		contentPane.add(textField_1);
		
		
		
		JLabel lblNewLabel_2=new JLabel("성적");
		lblNewLabel_2.setBounds(12,79,57,15);
		contentPane.add(lblNewLabel_2);
	
		
		JTextField textField_2=new JTextField();
		textField_2.setBounds(12,104,312,67);
		contentPane.add(textField_2);
		
		JButton btnNewButton=new JButton("등록하기");
		btnNewButton.setBounds(12,181,97,23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(e->{
			String name=textField.getText();
			String stnum=textField_1.getText();
			String grd=textField_2.getText();

			list.add(new Person(name,stnum,grd));
			System.out.println("이름:"+name+"학번"+stnum+"성적"+grd);
		});
		
		JButton btnNewButton_1_1=new JButton("취소");
		btnNewButton_1_1.setBounds(227,181,97,23);
		contentPane.add(btnNewButton_1_1);
		btnNewButton_1_1.addActionListener(e->{
			System.exit(0);
		});
		
		
		
	}

}
