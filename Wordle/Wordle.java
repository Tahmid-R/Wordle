import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;


public class Wordle implements ActionListener {
	
	JFrame frame;
	JPanel panel;
	JLabel label;
	JTextField textfield;
	char[] answer;
	char[] guess;
	JLabel[] labels = new JLabel[25];
	String answers;
	JTextField header;
	JButton submit = new JButton("Submit");
	int position = 0;
	int pointer =0;
	int count =0;
	Random rand = new Random();
	
	Wordle(){
		frame = new JFrame("Wordle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 500);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(5,5));
		panel.setBounds(0, 30, 400, 370);
				
		for(int i=0;i<25;i++) {
			labels[i] = new JLabel();
			labels[i].setFont(new Font("Arial Black",Font.PLAIN,20));
			labels[i].setHorizontalAlignment(JLabel.CENTER);
			labels[i].setOpaque(true);
			labels[i].setBorder(BorderFactory.createLineBorder(Color.gray));
			panel.add(labels[i]);
		}
		
		header = new JTextField();
		header.setBounds(0, 0, 400, 30);
		header.setFont(new Font("Arial Black",Font.BOLD,15));
		header.setEditable(false);
		header.setHorizontalAlignment(JTextField.CENTER);
		header.setText("Wordle");
		
		textfield = new JTextField();
		textfield.setBounds(100, 420, 100, 30);
		textfield.setFont(new Font("Arial Black",Font.BOLD,15));
		textfield.setDocument(new TextLimit(5));
		
		submit.setBounds(210, 420, 100, 30);
		submit.setFont(new Font("Arial Black",Font.BOLD,15));
		submit.setFocusable(false);
		submit.addActionListener(this);
		
		answers = getRandomWord();
		
		
		frame.add(panel);
		frame.add(textfield);
		frame.add(submit);
		frame.add(header);
		frame.setVisible(true);
		
	}
	
	
	public void checkAnswer() {
		
		answer = textfield.getText().toUpperCase().toCharArray();
		
		
		if(answer.length>4 && !textfield.getText().contains(" ")) {
			for(int i=0;i<5;i++) {
				
				if(pointer<25) {
					labels[pointer].setText(String.valueOf(answer[i]));
					
					if(answers.contains(Character.toString(answer[i])) && answer[i] == answers.charAt(i)) {
						labels[pointer].setBackground(Color.green);
						
						
						if(Objects.equals(answers, textfield.getText().toUpperCase())) {
							header.setText("You Win");
							submit.setEnabled(false);
							textfield.setEditable(false);
						}
						
					}
					else 
						if(answers.contains(Character.toString(answer[i]))) {
							labels[pointer].setBackground(Color.yellow);
							
							
					}
						else {
							labels[pointer].setBackground(Color.gray);
							
						}
					
					pointer++;
				}
				
				else {
					header.setText("You Lose!");
					submit.setEnabled(false);
					textfield.setEditable(false);
				}
				}
				
		}
		
		
		
			
			
		
		
	}
	
	public String getRandomWord() {
		
		List<String> words = new ArrayList<String>();
		
		try {
			Scanner scan = new Scanner(new File( "C:/Users/tausi/OneDrive/Documents/Word.txt"));
			
			while(scan.hasNext()) {
				words.add(scan.nextLine());
			}
		}
		catch(FileNotFoundException f){
			f.printStackTrace();
			
		}
		
		position = rand.nextInt(words.size());
		
		return words.get(position).trim().toUpperCase();
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==submit) {
			checkAnswer();
			textfield.setText("");
		}
		
		
	}

}
