package project1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class program {

	public static void main(String[] args) {

		DBMS db = new DBMS();

		String JDBC_URL = "jdbc:postgresql://localhost:5432/mydatabase";
		String JDBC_USER = "postgres";
		String JDBC_PASSWORD = "123";
		String tableName = "Student";

		db.dbConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

		db.CreateTable(tableName);

		JFrame frame = new JFrame("Student List");
		frame.setLayout(new GridLayout(1, 2));

		JPanel panel1 = new JPanel();
		frame.add(panel1);

		JPanel panel2 = new JPanel();
		frame.add(panel2);

		JLabel label1 = new JLabel("Rollno");
		panel1.add(label1);
		label1.setBounds(10, 10, 70, 20);

		JTextField tf1 = new JTextField();
		panel1.add(tf1);
		tf1.setBounds(10, 30, 70, 20);
		tf1.setEditable(false);

		JLabel label2 = new JLabel("Name:");
		panel1.add(label2);
		label2.setBounds(10, 50, 70, 20);

		JTextField tf2 = new JTextField();
		panel1.add(tf2);
		tf2.setBounds(10, 70, 70, 20);

		JLabel label3 = new JLabel("Favorite foods:");
		panel1.add(label3);
		label3.setBounds(10, 100, 100, 20);

		JCheckBox Pizza = new JCheckBox("Pizza");
		panel1.add(Pizza);
		Pizza.setBounds(10, 120, 70, 20);

		JCheckBox Burger = new JCheckBox("Burger");
		panel1.add(Burger);
		Burger.setBounds(80, 120, 70, 20);

		JCheckBox Pasta = new JCheckBox("Pasta");
		panel1.add(Pasta);
		Pasta.setBounds(160, 120, 70, 20);

		JCheckBox Sushi = new JCheckBox("Sushi");
		panel1.add(Sushi);
		Sushi.setBounds(10, 140, 70, 20);

		JCheckBox Salad = new JCheckBox("Salad");
		panel1.add(Salad);
		Salad.setBounds(80, 140, 70, 20);

		// Adding radio buttons for gender
		JLabel label4 = new JLabel("Gender");
		panel1.add(label4);
		label4.setBounds(10, 180, 70, 20);
		JRadioButton male = new JRadioButton("Male");
		JRadioButton female = new JRadioButton("Female");
		JRadioButton other = new JRadioButton("Other");

		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(male);
		genderGroup.add(female);
		genderGroup.add(other);

		male.setBounds(10, 200, 70, 20);
		female.setBounds(80, 200, 70, 20);
		other.setBounds(160, 200, 70, 20);

		panel1.add(male);
		panel1.add(female);
		panel1.add(other);

		JButton button = new JButton("Submit");
		panel1.add(button);
		button.setBounds(10, 240, 100, 20);

		JLabel label5 = new JLabel("Students lists:");
		panel2.add(label5);
		label5.setBounds(0, 0, 100, 20);
		JTextArea ta = new JTextArea();
		JScrollPane sp = new JScrollPane(ta);
		panel2.add(sp);
		sp.setBounds(0, 20, 300, 400);
//		

		JLabel updateLabel = new JLabel("Enter id:");
		panel1.add(updateLabel);
		updateLabel.setBounds(10, 280, 100, 20);

		JTextField tfUpdate = new JTextField();
		panel1.add(tfUpdate);
		tfUpdate.setBounds(10, 300, 100, 21);
		
		JButton updateButton= new JButton("Update");
		panel1.add(updateButton);
		updateButton.setBounds(115, 300, 75, 20);
		
		
		JButton deleteButton= new JButton("Delete");
		panel1.add(deleteButton);
		deleteButton.setBounds(200, 300, 75, 20);
		
		

		tf1.setText(Integer.valueOf(db.returnRoll(tableName)).toString());
		db.selectTable(tableName, ta);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String name = tf2.getText();

				String gen = male.isSelected() ? "Male" : "";
				String gen1 = female.isSelected() ? "female" : gen;
				String gender = other.isSelected() ? "Others" : gen1;
				String fav_foods = getFavFoods(Pizza.isSelected(), Pasta.isSelected(), Sushi.isSelected(),
						Salad.isSelected(), Burger.isSelected());

				db.insertTable(db.returnRoll(tableName), name, gender, fav_foods, tableName);
				ta.setText(null);
				db.selectTable(tableName, ta);
				int currentRoll = db.returnRoll(tableName);
				tf1.setText(Integer.valueOf(currentRoll).toString());

				tf2.setText("");
				genderGroup.clearSelection();
				Pizza.setSelected(false);
				Pasta.setSelected(false);
				Sushi.setSelected(false);
				Salad.setSelected(false);
				Burger.setSelected(false);

			}
		});
		
		
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf2.getText();

				String gen = male.isSelected() ? "Male" : "";
				String gen1 = female.isSelected() ? "female" : gen;
				String gender = other.isSelected() ? "Others" : gen1;
				String fav_foods = getFavFoods(Pizza.isSelected(), Pasta.isSelected(), Sushi.isSelected(),
						Salad.isSelected(), Burger.isSelected());
				
				String rollno= tfUpdate.getText();
				int roll= Integer.parseInt(rollno);
				db.updateTableData(roll,name, gender, fav_foods, tableName);
				ta.setText(null);
				db.selectTable(tableName, ta);
				
				tf2.setText("");
				tfUpdate.setText(null);
				genderGroup.clearSelection();
				Pizza.setSelected(false);
				Pasta.setSelected(false);
				Sushi.setSelected(false);
				Salad.setSelected(false);
				Burger.setSelected(false);
			}
		});
		
		
		
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String rollno= tfUpdate.getText();
				int roll= Integer.parseInt(rollno);
				db.deleteTableData(tableName, roll);
				ta.setText(null);
				db.selectTable(tableName, ta);
				
				tf1.setText(Integer.valueOf(db.returnRoll(tableName)).toString());
				tf2.setText("");
				tfUpdate.setText(null);
				genderGroup.clearSelection();
				Pizza.setSelected(false);
				Pasta.setSelected(false);
				Sushi.setSelected(false);
				Salad.setSelected(false);
				Burger.setSelected(false);
			}
		});

		panel1.setLayout(null);
		panel2.setLayout(null);
		frame.setVisible(true);
		frame.setSize(700, 500);

	}

	static String getFavFoods(boolean Pizza, boolean Pasta, boolean Sushi, boolean Salad, boolean Burger) {
		StringBuilder sb = new StringBuilder();
		if (Pizza) {
			sb.append("Pizza, ");
		}
		if (Pasta) {
			sb.append("Pasta, ");
		}
		if (Sushi) {
			sb.append("Sushi, ");
		}
		if (Salad) {
			sb.append("Salad, ");
		}
		if (Burger) {
			sb.append("Burger, ");
		}
		return sb.toString();
	}
}
