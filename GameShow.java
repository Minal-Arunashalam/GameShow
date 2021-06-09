//Minal

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;




public class GameShow implements ActionListener{
	
	//array for questions
	String[] questions = 	{"What is the capital of New Hampshire?",
							"What year was Queen Elizabeth II born?",
							"How many notes are there on a standard grand piano?",
							"What country is not a part of Scandinavia?",
							"Among land animals, what species has the largest eyes?",
							"In 1900, what were the most popular first names given to boy and girl babies born in the US?",
							"How many equal sides do Icosahedrons have?",
							"How many different combinations does a Rubix cube have?",
							"5+8-5+87-5+8-5+5-7+4-47+21-47+4-7+4-7+4-54 = ",
							"123456789 + 987654322 = "
							};
							
	
	//2d array for answers
	String[][] options = 	{
								{"Bedford","Concord","Nashua","Hanover"},
								{"1935","1905","1926","1918"},
								{"88","100","66","99"},
								{"Serbia","Yugoslavia","Finland","Slovenia"},
								{"Elephant","Tarantula","Tarsier","Ostrich"},
								{"John & Mary","William & Helen","James & Margaret","Charles & Elizabeth"},
								{"16","20","32","18"},
								{"45 trillion","43 quintillion","50 quintillion","32 quintillion"},
								{"-16","43","26","-34"},
								{"1132136521","1573822531","1111111111","123456121"},
								
								
							};
	
	//array for answer choices
	//this means that the answer for q1 is answer choice B, which is Concord, and the answer for q2 is choice C, which is 1926
	char[] answers = 		{'B','C','A','C','D','A','B','B','D','C'};
	
	//guess is the answer choice that the user selects
	char guess;
	//answer is the right answer for the question
	char answer;
	//counter for which question user is on
	int index;
	//correct answers chosen by user
	int correct_guesses =0;
	int total_questions = questions.length;
	int winnings;
	int seconds=10;
	
	boolean game_over=false;
	
	JFrame frame = new JFrame();
	//textfield will hold question number
	JTextField textfield = new JTextField();
	//textarea will hold the current question itself
	JTextArea textarea = new JTextArea();
	//buttons for answer choices
	JButton buttonA = new JButton();
	JButton buttonB = new JButton();
	JButton buttonC = new JButton();
	JButton buttonD = new JButton();
	//button labels that display the answers for each question
	JLabel answer_labelA = new JLabel();
	JLabel answer_labelB = new JLabel();
	JLabel answer_labelC = new JLabel();
	JLabel answer_labelD = new JLabel();
	//label to display the word timer
	JLabel time_label = new JLabel();
	//display for count down timer
	JLabel seconds_left = new JLabel();
	//display for the current score of user on the right 
	//(since the user earns "money" by answering questions right, the score will be a dollar value)
	JLabel score_display = new JLabel();
	//text label for score display
	JLabel score_label = new JLabel();

	///will appear at the end to show result text
	JTextField result = new JTextField();
	JTextField result2 = new JTextField();
	
	//textfield for the "Press Enter to Reset" Prompt
	JTextField reset_game = new JTextField();
	//textfield for the "Press Shift to Quit" Prompt
	JTextField quit_game = new JTextField();
	//text field for the text "Answer all the questions correct and you'll earn:"
	JTextField info_text= new JTextField();
	//text field for the text "$1 million!"
	JTextField info_text2= new JTextField();
	//text field for the text "Get one wrong and you go home with nothing."
	JTextField info_text3= new JTextField();
	
	
	//this is the seconds left timer 
	//(it will reset for each question and when the timer runs out and no answer is picked, the quiz will move to the nxt question)
	Timer timer = new Timer(1000, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//subtract 1 from seconds left every second and display
			seconds--;
			seconds_left.setText(String.valueOf(seconds));
			//if timer runs out, display the answer and move to next question 
			//(question user was on will be counted as wrong)
			if(seconds<=0) {
				//displayAnswer();
				
				//if timer runs out, game ends
				correct_guesses=0;
				results();
				timer.stop();
				game_over=true;
				gameOver();
				
			}
			}
		});
	
	public GameShow() {
		
		frame.addKeyListener(new Keyboard());
		frame.setFocusable(true);
		
		//designing frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500,900);
		frame.getContentPane().setBackground(new Color(0,0,0));
		frame.setLayout(null);
		frame.setResizable(false);
		
		//designing text field that will hold the question number user is on
		textfield.setBounds(0,0,1500,50);
		textfield.setBackground(new Color(0,0,0));
		textfield.setForeground(new Color(0,255,51));
		textfield.setFont(new Font("Courier New",Font.BOLD,30));
		textfield.setBorder(BorderFactory.createBevelBorder(1));
		textfield.setHorizontalAlignment(JTextField.CENTER);
		textfield.setEditable(false);
		
		//text area that will hold the question itself
		textarea.setBounds(0,50,1500,50);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setBackground(new Color(0,0,0));
		textarea.setForeground(new Color(0,255,51));
		textarea.setFont(new Font("Courier New",Font.BOLD,25));
		textarea.setBorder(BorderFactory.createBevelBorder(1));
		textarea.setEditable(false);
		
		//designing buttons
		buttonA.setBounds(50,150,100,200);
		buttonA.setFont(new Font("Courier New",Font.BOLD,35));
		buttonA.setFocusable(false);
		buttonA.addActionListener(this);
		buttonA.setBackground(new Color(0,0,0));
	    buttonA.setForeground(new Color(0,255,51));
	    buttonA.setBorderPainted(false);
		buttonA.setText("A:");
		
		buttonB.setBounds(50,500,100,200);
		buttonB.setFont(new Font("Courier New",Font.BOLD,35));
		buttonB.setFocusable(false);
		buttonB.addActionListener(this);
		buttonB.setBackground(new Color(0,0,0));
	    buttonB.setForeground(new Color(0,255,51));
	    buttonB.setBorderPainted(false);
		buttonB.setText("B:");
		
		buttonC.setBounds(550,150,100,200);
		buttonC.setFont(new Font("Courier New",Font.BOLD,35));
		buttonC.setFocusable(false);
		buttonC.addActionListener(this);
		buttonC.setBackground(new Color(0,0,0));
	    buttonC.setForeground(new Color(0,255,51));
	    buttonC.setBorderPainted(false);
		buttonC.setText("C:");
		
		buttonD.setBounds(550,500,100,200);
		buttonD.setFont(new Font("Courier New",Font.BOLD,35));
		buttonD.setFocusable(false);
		buttonD.addActionListener(this);
		buttonD.setBackground(new Color(0,0,0));
	    buttonD.setForeground(new Color(0,255,51));
	    buttonD.setBorderPainted(false);
		buttonD.setText("D:");
		
		//designing the answer labels, which will show the designated answer choice for each button 
		//(the first answer choice for question 1 in the options array will be assigned to button A, etc.)
		answer_labelA.setBounds(275,200,500,100);
		answer_labelA.setBackground(new Color(0,0,0));
		answer_labelA.setForeground(new Color(0,255,51));
		answer_labelA.setFont(new Font("Courier New",Font.PLAIN,35));
		
		answer_labelB.setBounds(275,550,500,100);
		answer_labelB.setBackground(new Color(0,0,0));
		answer_labelB.setForeground(new Color(0,255,51));
		answer_labelB.setFont(new Font("Courier New",Font.PLAIN,35));
		
		answer_labelC.setBounds(750,200,500,100);
		answer_labelC.setBackground(new Color(0,0,0));
		answer_labelC.setForeground(new Color(0,255,51));
		answer_labelC.setFont(new Font("Courier New",Font.PLAIN,35));
		
		answer_labelD.setBounds(750,550,500,100);
		answer_labelD.setBackground(new Color(0,0,0));
		answer_labelD.setForeground(new Color(0,255,51));
		answer_labelD.setFont(new Font("Courier New",Font.PLAIN,35));
		
		//designing the seconds left area, which will be show how many seconds are left
		seconds_left.setBounds(1300,680,100,100);
		seconds_left.setBackground(new Color(0,0,0));
		seconds_left.setForeground(new Color(255,0,0));
		seconds_left.setFont(new Font("Courier New",Font.BOLD,60));
		seconds_left.setBorder(BorderFactory.createBevelBorder(1));
		seconds_left.setOpaque(true);
		seconds_left.setHorizontalAlignment(JTextField.CENTER);
		seconds_left.setText(String.valueOf(seconds));
		//designing the time label, which will just print the words "Timer: above the seconds_left area
		time_label.setBounds(1300,660,100,25);
		time_label.setBackground(new Color(0,0,0));
		time_label.setForeground(new Color(255,0,0));
		time_label.setFont(new Font("Courier New",Font.PLAIN,16));
		time_label.setHorizontalAlignment(JTextField.CENTER);
		time_label.setText("Timer");
		
		//designing display for number of questions right, which will be displayed at the end
		result.setBounds(250,330,1000,100);
		result.setBackground(new Color(0,0,0));
		result.setForeground(new Color(25,255,0));
		result.setFont(new Font("Courier New",Font.BOLD,50));
		result.setBorder(BorderFactory.createBevelBorder(1));
		result.setHorizontalAlignment(JTextField.CENTER);
		result.setEditable(false);
		
		//designing display for number of questions right, which will be displayed at the end
		result2.setBounds(250,415,1000,100);
		result2.setBackground(new Color(0,0,0));
		result2.setForeground(new Color(25,255,0));
		result2.setFont(new Font("Courier New",Font.BOLD,50));
		result2.setBorder(BorderFactory.createBevelBorder(1));
		result2.setHorizontalAlignment(JTextField.CENTER);
		result2.setEditable(false);
		
		
		
		//display for the current score of user 
		//(the score is the amount of money they've earned so far)
		score_display.setBounds(1100,200,300,100);
		score_display.setBackground(new Color(0,0,0));
		//score_display.setForeground(new Color(255,153,0));
		score_display.setForeground(new Color(255,255,0));
		score_display.setFont(new Font("Courier New",Font.BOLD,30));
		score_display.setBorder(BorderFactory.createBevelBorder(1));
		score_display.setOpaque(true);
		score_display.setHorizontalAlignment(JTextField.CENTER);
		score_display.setText(String.valueOf(correct_guesses));
		//text label above score display
		score_label.setBounds(1150,125,200,100);
		score_label.setBackground(new Color(0,0,0));
		//score_label.setForeground(new Color(255,153,0));
		score_label.setForeground(new Color(255,255,0));
		score_label.setFont(new Font("Courier New",Font.PLAIN,30));
		score_label.setHorizontalAlignment(JTextField.CENTER);
		score_label.setText("Winnings($)");
		//text for the prompt that user will receive at the end to try again
		reset_game.setBounds(545,375,450,150);
		reset_game.setBackground(new Color(0,0,0));
		reset_game.setForeground(new Color(255,0,0));
		reset_game.setFont(new Font("Courier New",Font.PLAIN,30));
		reset_game.setHorizontalAlignment(JTextField.CENTER);
		reset_game.setEditable(false);
		reset_game.setText("Press Enter to Try Again");
		
		//text for the prompt that user will receive at the end to quit game
		quit_game.setBounds(530,450,450,200);
		quit_game.setBackground(new Color(0,0,0));
		quit_game.setForeground(new Color(255,0,0));
		quit_game.setFont(new Font("Courier New",Font.PLAIN,30));
		quit_game.setHorizontalAlignment(JTextField.CENTER);
		quit_game.setEditable(false);
		quit_game.setText("Press Shift to Quit");
		
		//these info text fields are where info about the quiz is given
		//they are broken up into three fields because I wanted to make a paragraph since I didn't have much space
		
		info_text.setBounds(950,345,500,25);
		info_text.setBackground(new Color(0,0,0));
		info_text.setForeground(new Color(102,255,102));
		info_text.setFont(new Font("Courier New",Font.BOLD,16));
		info_text.setBorder(BorderFactory.createBevelBorder(1));
		info_text.setEditable(false);
		info_text.setText("Answer all the questions correct and you'll earn:");
		
		info_text2.setBounds(1110,375,500,50);
		info_text2.setBackground(new Color(0,0,0));
		info_text2.setForeground(new Color(102,255,102));
		info_text2.setFont(new Font("Courier New",Font.BOLD,25));
		info_text2.setBorder(BorderFactory.createBevelBorder(1));
		info_text2.setEditable(false);
		info_text2.setText("$1 million");
		
		
		info_text3.setBounds(985,430,500,25);
		info_text3.setBackground(new Color(0,0,0));
		info_text3.setForeground(new Color(102,255,102));
		info_text3.setFont(new Font("Courier New",Font.BOLD,16));
		info_text3.setBorder(BorderFactory.createBevelBorder(1));
		info_text3.setEditable(false);
		info_text3.setText("Get one wrong, and you go home with nothing.");
		
		//adding all GUI components to the frame
		//number_right and percentage will only be added to frame at end
		frame.add(score_display);
		frame.add(time_label);
		frame.add(seconds_left);
		frame.add(answer_labelA);
		frame.add(answer_labelB);
		frame.add(answer_labelC);
		frame.add(answer_labelD);
		frame.add(buttonA);
		frame.add(buttonB);
		frame.add(buttonC);
		frame.add(buttonD);
		frame.add(textarea);
		frame.add(textfield);
		frame.add(score_label);
		frame.add(info_text);
		frame.add(info_text2);
		frame.add(info_text3);
		frame.setVisible(true);
		
		//call nextQuestion method to begin the quiz
		nextQuestion();
	}
	public void nextQuestion() {
		
		
		//when questions are finished, display results
		if(index>=total_questions) {
			results();
		}
		//else if questions are still going
		else {
			//increase question number displayed
			textfield.setText("Question "+(index+1));
			//display question being asked
			textarea.setText(questions[index]);
			//set the answer choices to the answer labels
			answer_labelA.setText(options[index][0]);
			answer_labelB.setText(options[index][1]);
			answer_labelC.setText(options[index][2]);
			answer_labelD.setText(options[index][3]);
			//restart the timer every time new question is displayed
			timer.start();
		}
	}
	@Override
	//will trigger when user clicks an answer
	public void actionPerformed(ActionEvent e) {
		
			//disable buttons temporarily
			buttonA.setEnabled(false);
			buttonB.setEnabled(false);
			buttonC.setEnabled(false);
			buttonD.setEnabled(false);
			
			//if button A is clicked
			if(e.getSource()==buttonA) {
				//assign character value to user answer
				answer= 'A';
				//check if the char value of answer user picked is equal to char value of right answer in the answers array
				if(answer == answers[index]) {
					//correct_guesses++;
					correct_guesses=correct_guesses+100000;
					score_display.setText(String.valueOf(correct_guesses));
					
					//briefly display the right answer after user picks an answer so that user knows which one was right
					displayAnswer();
					
				} else {
					
					//if user selects wrong answer, game ends
					correct_guesses=0;
					results();
					timer.stop();
					game_over=true;
					gameOver();
					
					
					
				}
				
			}
			//if button B is clicked
			if(e.getSource()==buttonB) {
				//assign character value to user answer
				answer= 'B';
				//check if the char value of answer user picked is equal to char value of right answer in the answers array
				if(answer == answers[index]) {
					//correct_guesses++;
					correct_guesses=correct_guesses+100000;
					score_display.setText(String.valueOf(correct_guesses));
					
					//briefly display the right answer after user picks an answer so that user knows which one was right
					displayAnswer();
					
				}else {
					
					//if user selects wrong answer, game ends
					correct_guesses=0;
					results();
					timer.stop();
					game_over=true;
					gameOver();
					
					
				}
			}
			//if button C is clicked
			if(e.getSource()==buttonC) {
				answer= 'C';
				//check if the char value of answer user picked is equal to char value of right answer in the answers array
				if(answer == answers[index]) {
					//correct_guesses++;
					correct_guesses=correct_guesses+100000;
					score_display.setText(String.valueOf(correct_guesses));
					
					//briefly display the right answer after user picks an answer so that user knows which one was right
					displayAnswer();
					
				}else {
					
					//if user selects wrong answer, game ends
					correct_guesses=0;
					results();
					timer.stop();
					game_over=true;
					gameOver();
					
					
				}
			}
			//if button D is clicked
			if(e.getSource()==buttonD) {
				answer= 'D';
				//check if the char value of answer user picked is equal to char value of right answer in the answers array
				if(answer == answers[index]) {
					//correct_guesses++;
					correct_guesses=correct_guesses+100000;
					score_display.setText(String.valueOf(correct_guesses));
					
					//briefly display the right answer after user picks an answer so that user knows which one was right
					displayAnswer();
					
				}else {
					
					//if user selects wrong answer, game ends
					correct_guesses=0;
					results();
					timer.stop();
					game_over=true;
					gameOver();
					
				}
			}
			//briefly display the right answer after user picks an answer so that user knows which one was right
			//displayAnswer();
	}
	
	public void displayAnswer() {
		//stop timer to after user answers
		timer.stop();
		//disable buttons temporarily
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		//display the right answer as green and wrong answers as red after user selects an answer
		
		//if the right answer is not A, turn A red
		if(answers[index] != 'A')
			answer_labelA.setForeground(new Color(255,0,0));
		//if the right answer is not B, turn B red
		if(answers[index] != 'B')
			answer_labelB.setForeground(new Color(255,0,0));
		//if the right answer is not C, turn C red
		if(answers[index] != 'C')
			answer_labelC.setForeground(new Color(255,0,0));
		//if the right answer is not D, turn D red
		if(answers[index] != 'D')
			answer_labelD.setForeground(new Color(255,0,0));
		
		//need to add a pause after displaying correct answer, so timer is implemented
		//2 second pause
		Timer pause = new Timer(2000, new ActionListener() {
			
			
			//after 2 second pause runs out, stop displaying answers and switch answer labels back to all green
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//change labels back to green
				answer_labelA.setForeground(new Color(25,255,0));
				answer_labelB.setForeground(new Color(25,255,0));
				answer_labelC.setForeground(new Color(25,255,0));
				answer_labelD.setForeground(new Color(25,255,0));
				
				//reset answer since we have a new question now
				answer = ' ';
				//reset timer back to ten
				seconds=10;
				//show seconds left
				seconds_left.setText(String.valueOf(seconds));
				//enable buttons to be clickable
				buttonA.setEnabled(true);
				buttonB.setEnabled(true);
				buttonC.setEnabled(true);
				buttonD.setEnabled(true);
				//add 1 to index, since we have moved on to next question
				index++;
				//call nextQuestion method to show next question
				nextQuestion();
			}
		});
		
		//only want pause to repeat once when showing answer, not repeat every two seconds, so setRepeats to false
		pause.setRepeats(false);
		//start pause timer when displaying answer
		pause.start();
	}
	public void results(){
		
		//take away buttons
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		
		//the winnings of user
		winnings = (correct_guesses);
		
		//change text field to display text for results display
		textfield.setText("");
		//clear away everything else to display results
		textarea.setText("");
		answer_labelA.setText("");
		answer_labelB.setText("");
		answer_labelC.setText("");
		answer_labelD.setText("");
		
		//if the user selected a wrong answer, or time ran out on a question and no answer was selected, the player lost
		if(winnings==0) {
			
			//display text saying that player lost
			result.setText("You Lost");
			result.setForeground(new Color(255,0,0));
			
			//clear away other areas of text
			score_display.setText("");
			score_label.setText("");
			seconds_left.setText("");
			time_label.setText("");
			
			info_text.setText("");
			info_text2.setText("");
			info_text3.setText("");
			
			//show result to user
			frame.add(result);
			
			
			
		}
		//if the score is not 0, that has to mean that the player answered every question correctly and won
		else {
			//text congratulating user and displaying the money won
			result.setText("Congrats! Your Winnings:");
			result2.setText("$"+correct_guesses);
			
			
			//clear away other areas of text
			score_display.setText("");
			score_label.setText("");
			seconds_left.setText("");
			time_label.setText("");
			
			info_text.setText("");
			info_text2.setText("");
			info_text3.setText("");
			
			//show result to user
			frame.add(result);
			frame.add(result2);
			
			
			/*score_label.setText("");
			seconds_left.setText("");
			time_label.setText("");
			
			info_text.setText("");
			info_text2.setText("");
			info_text3.setText("");*/
			
			
		}
		
		
		
		
		 
		
		 
		
	}
	
	public void gameOver() {
		
		//setting text for these prompts again here will make sure that
		//after the player resets and loses more than once, the reset and quit
		//prompts will show up again every time (since after we reset we clear these texts)
		reset_game.setText("Press Enter to Try Again");
		quit_game.setText("Press Shift to Quit");
		
		//add the prompts (to quit/restart) when player loses 
		frame.add(reset_game);
		frame.add(quit_game);
		  	  
		  
	}
	
	private class Keyboard implements KeyListener {
		
		public void keyPressed(KeyEvent e) {
			
			if (e.getKeyCode() == KeyEvent.VK_ENTER && game_over) {
				
				game_over=false;
				//reset question number back to 0, so that user will start from beginning
				index=0;
				//reset numbers of seconds on timer
				seconds=10;
				//re-enable buttons
				buttonA.setEnabled(true);
				buttonB.setEnabled(true);
				buttonC.setEnabled(true);
				buttonD.setEnabled(true);
				
				//reset all the text, because we cleared it before in order to create the end game screen
				//reset the text of buttons
				buttonA.setText("A:");
				buttonB.setText("B:");
				buttonC.setText("C:");
				buttonD.setText("D:");
				
				//reset timer and timer text
				seconds_left.setText(String.valueOf(seconds));
				time_label.setText("Timer");
				
				//reset score display and text
				score_display.setText(String.valueOf(correct_guesses));
				score_label.setText("Winnings($)");
				
				//reset info text section
				info_text.setText("Answer all the questions correct and you'll earn:");
				info_text2.setText("$1 million");
				info_text3.setText("Get one wrong, and you go home with nothing.");
				
				//take away the end game text
				result.setText("");
				result2.setText("");
				reset_game.setText("");
				quit_game.setText("");
				
				//start quiz again
				nextQuestion();
			  }
			
			//after player loses, they can quit the game by pressing shift
			if (e.getKeyCode() == KeyEvent.VK_SHIFT && game_over) {
				game_over=false;
				System.exit(0);
			  }
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		}
	
	
}

