import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class MainFrame extends JFrame {
	//Define the basic elements of ConnectFour game
	private boolean flagOfPersonDone;
	private boolean flagOfStartDraw;
	private boolean flagOfMousePressed;
	private boolean flagOfPoint;
	private double count;
	private int flagOfStartGame;
	private int flagOfEndGame;
	private int flagOfCreateChessboard;
	private int flagOfChessSignal;
	private int flagOfDifficulty;
	private int flagOfTurnOrder;
	private int currentRowX;
	private int currentColumnY;
	private int mouseX;
	private Point point;
	private JButton startGame;
	private JLabel participationNameLabel;
	private JLabel printWinner;
	private JLabel gameTitle;
	private JLabel lengthLabel;
	private JLabel widthLabel;
	private JLabel chooseModeSimple;
	private JLabel chooseModeHard;
	private JLabel chooseOrderPlayer;
	private JLabel chooseOrderComputer;
	private JTextField participationName;
	private JTextField chessboardSizeLong;
	private JTextField chessboardSizeWidth;
	private JMenuBar menubar;
	private JMenu modeChoose;
	private JMenu sizeChoose;
	private ButtonGroup modeGroup;
	private ButtonGroup sizeGroup;
	private JRadioButtonMenuItem Person_VS_Person;
	private JRadioButtonMenuItem Person_VS_Computer;
	private JRadioButtonMenuItem size1;
	private JRadioButtonMenuItem size2;
	private JRadioButtonMenuItem userDefined;
	private JRadioButton simpleButton;
	private JRadioButton hardButton;
	private JRadioButton turnPerson;
	private JRadioButton turnComputer;
	private Chessboard chessboard;
	private ChessBoardPanel chessBoardPanel;
	
	public MainFrame() {
		
		flagOfPoint = false;
		
		flagOfMousePressed = false;
		
		//Set count
		count = 0.01;
		
		//Set current X, Y
		currentRowX = 99999;
		currentColumnY = 99999;
		
		//Set the signal of draw
		flagOfStartDraw = true;
		
		//Set person done
		flagOfPersonDone = false;
		
		//Set the end of game signal
		flagOfEndGame = 0;
		
		//Set the turn order
		flagOfTurnOrder = 0;
		
		//Set the level of difficulty
		flagOfDifficulty = 0;
		
		//Set the signal of chess
		flagOfChessSignal = 1;
		
		//Define the symbol of starting the game
		flagOfStartGame = 0;
		
		//Define not create chess board
		flagOfCreateChessboard = 0;
		
		//Define JMenubar which contains modeChoose and sizeChoose 
		menubar = new JMenuBar();
		
		//Define JMenu
		modeChoose = new JMenu("GAME MODE");
		sizeChoose = new JMenu("CHESSBOARD SIZE");
		
		menubar.add(modeChoose);
		menubar.add(sizeChoose);
		
		//Define mode group
		modeGroup = new ButtonGroup();
		Person_VS_Person = new JRadioButtonMenuItem("PERSON VS PERSON", true);
		Person_VS_Person.setActionCommand("pvp");
		Person_VS_Computer = new JRadioButtonMenuItem("PERSON VS COMPUTER");
		Person_VS_Computer.setActionCommand("pvc");
		
		modeGroup.add(Person_VS_Person);
		modeGroup.add(Person_VS_Computer);
		modeChoose.add(Person_VS_Person);
		modeChoose.add(Person_VS_Computer);
		
		//Define size group
		sizeGroup = new ButtonGroup();
		size1 = new JRadioButtonMenuItem("7 x 6 SIZE", true);
		size1.setActionCommand("7 x 6");
		size2 = new JRadioButtonMenuItem("8 x 7 SIZE");
		size2.setActionCommand("8 x 7");
		userDefined = new JRadioButtonMenuItem("USER-DEFINED");
		userDefined.setActionCommand("USER-DEFINED");
		
		sizeGroup.add(size1);
		sizeGroup.add(size2);
		sizeGroup.add(userDefined);
		sizeChoose.add(size1);
		sizeChoose.add(size2);
		sizeChoose.add(userDefined);

		setJMenuBar(menubar);
		
		//Set the font
		Font fontCambriaPlain = new Font("Cambria", Font.PLAIN, 12);
		Font fontCambriaItalic = new Font("Cambria", Font.PLAIN, 16);
		Font font = new Font("Cambria", Font.BOLD, 12);
		
		//Define JButton of starting the game
		startGame = new JButton("START");
		startGame.setFont(font);
		
		//Define JTextFields which contain participationName, chessboardSizeLong and chessboardSizeWidth
		participationName = new JTextField(10);
		chessboardSizeLong = new JTextField(2);
		chessboardSizeWidth = new JTextField(2);
		
		//Start to listen the button of starting the game
		startGame.addActionListener(new ButtonListenerStart());
		
		//Font fontCambriaPlain3 = new Font("Cambria", Font.PLAIN, 12);
		chooseModeSimple = new JLabel("Choose mode simple");
		chooseModeSimple.setFont(fontCambriaPlain);
		
		chooseModeHard = new JLabel("Choose mode hard");
		chooseModeHard.setFont(fontCambriaPlain);
		
		chooseOrderPlayer = new JLabel("Order 1");
		chooseOrderPlayer.setFont(fontCambriaPlain);
		
		chooseOrderComputer = new JLabel("Order 2");
		chooseOrderComputer.setFont(fontCambriaPlain);
				
		//Set the prompt of name
		participationNameLabel = new JLabel("Enter your name");
		participationNameLabel.setFont(fontCambriaPlain);
		//participationNameLabel.setFont(fontCambriaPlain);
		
		//Define the status of competition
		printWinner = new JLabel("Please Press START Button to start Game");
		printWinner.setFont(fontCambriaPlain);
		//Define the Title
		gameTitle = new JLabel("Welcome To Connect Four Game");
		gameTitle.setFont(fontCambriaItalic);
		
		//Set the label length and width
		lengthLabel = new JLabel("Enter Chessboard Length (7~15)");
		lengthLabel.setFont(fontCambriaPlain);
		widthLabel = new JLabel("Enter Chessboard Width   (7~20)");
		widthLabel.setFont(fontCambriaPlain);
		
		//Define the degree of difficulty
		ButtonGroup bgroup1 = new ButtonGroup();
		simpleButton = new JRadioButton("SIMPLE");
		simpleButton.setFont(fontCambriaPlain);
		hardButton = new JRadioButton("HARD");
		hardButton.setFont(fontCambriaPlain);
		simpleButton.setBackground(Color.WHITE);
		hardButton.setBackground(Color.WHITE);
		bgroup1.add(simpleButton);
		bgroup1.add(hardButton);
		
		ChangeDifficulty listener = new ChangeDifficulty();
		simpleButton.addActionListener(listener);
		hardButton.addActionListener(listener);
		
		//Define the turn
		ButtonGroup bgroup2 = new ButtonGroup();
		turnPerson = new JRadioButton("PLAYER FIRST");
		turnPerson.setFont(fontCambriaPlain);
		turnComputer = new JRadioButton("COMPUTER FIRST");
		turnComputer.setFont(fontCambriaPlain);
		bgroup2.add(turnPerson);
		bgroup2.add(turnComputer);
		turnPerson.setBackground(Color.WHITE);
		turnComputer.setBackground(Color.WHITE);
		
		ChangeTurn turn = new ChangeTurn();
		turnPerson.addActionListener(turn);
		turnComputer.addActionListener(turn);
		
		//-----------------------------------------------------------------
		//Add the elements of the panel and make layout		
		chessBoardPanel = new ChessBoardPanel();
		add(chessBoardPanel, BorderLayout.CENTER);
		
		chessBoardPanel.setBackground(Color.WHITE);
		chessBoardPanel.addMouseListener(new MouseChange());
		chessBoardPanel.addMouseMotionListener(new MouseMoved());
		
		JPanel Elements = new JPanel();
		Elements.setBackground(Color.WHITE);
		
		Elements.setPreferredSize(new Dimension(250, 600));
		Elements.add(gameTitle, BorderLayout.NORTH);
		
		JPanel namePanel = new JPanel();
		namePanel.setBackground(Color.WHITE);
		namePanel.add(participationNameLabel);
		namePanel.add(participationName);
		
		JPanel sizePanel1 = new JPanel();
		sizePanel1.setBackground(Color.WHITE);
		sizePanel1.add(lengthLabel);
		sizePanel1.add(chessboardSizeLong);
		
		JPanel sizePanel2 = new JPanel();
		sizePanel2.setBackground(Color.WHITE);
		sizePanel2.add(widthLabel);
		sizePanel2.add(chessboardSizeWidth);
		
		JPanel radioButton1 = new JPanel();
		radioButton1.setBackground(Color.WHITE);
		
		radioButton1.add(chooseModeSimple);
		radioButton1.add(simpleButton);
		
		JPanel radioButton2 = new JPanel();
		radioButton2.setBackground(Color.WHITE);
		
		radioButton2.add(chooseModeHard);
		radioButton2.add(hardButton);
		
		JPanel radioButton3 = new JPanel();
		radioButton3.setBackground(Color.WHITE);
		
		radioButton3.add(chooseOrderPlayer);
		radioButton3.add(turnPerson);
		
		JPanel radioButton4 = new JPanel();
		radioButton4.setBackground(Color.WHITE);
		
		radioButton4.add(chooseOrderComputer);
		radioButton4.add(turnComputer);

		JPanel chooseMode = new JPanel();
		chooseMode.setBackground(Color.WHITE);
		
		Box b = Box.createVerticalBox();
		b.add(Box.createRigidArea(new Dimension(100, 5)));
		b.add(namePanel);
		
		
		Box b0 = Box.createVerticalBox();
		b0.add(Box.createRigidArea(new Dimension(100, 10)));
		b0.add(sizePanel1);
		b0.add(sizePanel2);
		
		Box b1 = Box.createVerticalBox();
		b1.add(Box.createRigidArea(new Dimension(100, 12)));
		b1.add(radioButton1);
		b1.add(radioButton2);
		//b1.add(Box.createVerticalGlue());
		Box b4 = Box.createVerticalBox();
		b4.add(Box.createRigidArea(new Dimension(100, 13)));
		b4.add(radioButton3);
		b4.add(radioButton4);
		
		Box b2 = Box.createVerticalBox();
		b2.add(Box.createRigidArea(new Dimension(100, 5)));
		b2.add(startGame);
		
		b0.add(b1);
		b0.add(b4);
		b.add(b0);
		b.add(b2);
		//b1.add(Box.createVerticalStrut(400));
		
		Elements.add(b);
		
		//Elements.add(startGame);
		//Elements.add(printWinner, BorderLayout.SOUTH);
		
		add(Elements, BorderLayout.EAST);
		repaint();
		
		// Chess board thread
		class ChessBoardThread implements Runnable {
			public void run() {
				while (true) {
					if (1 - count < 0.000000001) {
						count = 0.01;
						flagOfStartDraw = false;
						flagOfMousePressed = false;
						flagOfPoint = false;
					}
					if (flagOfStartDraw && flagOfMousePressed && flagOfPoint) {
						chessBoardPanel.repaint();
					}
						
					count += 0.03;
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		Thread updater = new Thread(new ChessBoardThread());
		updater.start();
		
		//-----------------------------------------------------------------

	}
	
	//Create the chess board after starting game
	public void CreateChessboard() {
		if (flagOfStartGame == 1) {
			boolean temp = false;
			//Create the chess board size
			if (sizeGroup.getSelection().getActionCommand() == "7 x 6") {
				chessboard = new Chessboard(7, 6);
				temp = true;
			}
			else if (sizeGroup.getSelection().getActionCommand() == "8 x 7") {
				chessboard = new Chessboard(8, 7);
				temp = true;
			}
				
			else if (sizeGroup.getSelection().getActionCommand() == "USER-DEFINED") {
				
				//Create chess board long
				String textLong = chessboardSizeLong.getText();
				int length = Integer.parseInt(textLong);
				
				//Create chess board width
				String textWidth = chessboardSizeWidth.getText();
				int width = Integer.parseInt(textWidth);
				if (length >= 7 && length <= 15 && width >= 7 && width <= 20) {
					chessboard = new Chessboard(length, width);
					temp = true;
				}
			}
			if (temp)
				flagOfCreateChessboard = 1;
		}
	}
	
	//Set the window size
	public void SetWindowSize(int length, int width) {
		setSize(width, length);
	}
	
	//Listen the start game button
	private class ButtonListenerStart implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			count = 0.01;
			flagOfPoint = true;
			flagOfStartDraw = true;
			flagOfStartGame = 1;
			flagOfEndGame = 0;
			flagOfChessSignal = 1;
			flagOfPersonDone = false;
			flagOfMousePressed = true;
			
			//Create chess board
			CreateChessboard();
			repaint();
			
			if (modeGroup.getSelection().getActionCommand() == "pvc" && flagOfTurnOrder == 2) {
        		if (flagOfDifficulty == 1)
        			GameComputerSimple();
        		else if (flagOfDifficulty == 2)
        			GameComputerHard();
        	}
			
			startGame.setText("RESTART");
		}
	}
	
	//Listen the radio button of the level of difficulty
	private class ChangeDifficulty implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == simpleButton)
				flagOfDifficulty = 1;
			else if (e.getSource() == hardButton)
				flagOfDifficulty = 2;
			else flagOfDifficulty = 0;
		}
	}
	
	//Listen the radio button of the level of difficulty
	private class ChangeTurn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == turnPerson)
				flagOfTurnOrder = 1;
			else if (e.getSource() == turnComputer)
				flagOfTurnOrder = 2;
			else flagOfTurnOrder = 0;
		}
	}
	
	//Create chess board panel
	public class ChessBoardPanel extends JPanel {
		public int xCoordinate;
		public int yCoordinate;
		
		public ChessBoardPanel() {
			xCoordinate = 0;
			yCoordinate = 0;
		}
		public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        if (flagOfStartGame == 1 && flagOfCreateChessboard == 1) {
	        	
				//Set the parameter of coordinate
				xCoordinate = 0;
		        yCoordinate = 0;
					
				//Set the window size
				SetWindowSize((chessboard.GetLength() + 1) * 50 + 12, chessboard.GetWidth() * 50 + 343);
					
				//Set rectangle color
				g.setColor(new Color(8, 46, 84));
				g.fillRect(xCoordinate, yCoordinate, chessboard.GetWidth() * 50, chessboard.GetLength() * 50);
					
				//Set circle color
				g.setColor(new Color(245,245,245));
				for (int i = xCoordinate; i < chessboard.GetWidth() * 50; i += 50) {
		        	for (int j = yCoordinate; j < chessboard.GetLength() * 50; j += 50) {
		        		g.setColor(new Color(245,245,245));
		        		g.fillOval(i, j, 50, 50);
		        		if (chessboard.chessboardSize[j / 50][i / 50] == 1) {
		    	        	g.setColor(Color.RED);
		    	        	if (currentRowX == j / 50 && currentColumnY == i / 50 && flagOfMousePressed && flagOfPoint) {
		    	        		int temp = (int) (j * count * count + 0.01);
		    	        		System.out.println("temp = " + temp);
		    	        		g.fillOval(i, temp, 50, 50);
		    	        	} else g.fillOval(i, j, 50, 50);
		    	        }
		        		if (chessboard.chessboardSize[j / 50][i / 50] == 2) {
		    	        	g.setColor(Color.YELLOW);
		    	        	if (currentRowX == j / 50 && currentColumnY == i / 50 && flagOfMousePressed && flagOfPoint) {
		    	        		int temp2 = (int) (j * count * count + 0.01);
		    	        		System.out.println("temp2 = " + temp2);
		    	        		g.fillOval(i, temp2, 50, 50);
		    	        	} else g.fillOval(i, j, 50, 50);
		    	        }
		        	}
		        }
				
			} else {
				g.drawLine(5, 10, 100, 10);
				g.drawLine(300, 10, 395, 10);
				g.drawLine(5, 325, 100, 325);
				g.drawLine(300, 325, 395, 325);
				g.drawLine(5, 10, 5, 105);
				g.drawLine(395, 10, 395, 105);
				g.drawLine(5, 325, 5, 230);
				g.drawLine(395, 325, 395, 230);
				g.drawString("Please Press START Button to start Game", 80, 170);
			}
	        
	        if (mouseX > 0 && mouseX < chessboard.GetWidth() * 50) {
				for (int i = 0; i < chessboard.GetWidth() * 50; i += 50) {
					if (mouseX > i && mouseX < i + 50) {
						g.setColor(new Color(70, 130, 180, 100));
						g.fillRect(i, 0, 51, chessboard.GetLength() * 50);
					}
				}
	        }
	        
	        if (flagOfEndGame == 1) {
	        	if (flagOfChessSignal == 1) {
	        		g.setColor(new Color(255, 250, 205, 200));      // YELLOW
	        		g.fillRect(xCoordinate, yCoordinate, chessboard.GetWidth() * 50 + 1, chessboard.GetLength() * 50);
	        		g.setColor(Color.BLACK);
	        		Font fontCambriaItalic2 = new Font("Cambria", Font.ITALIC, 24);
	        		g.setFont(fontCambriaItalic2);
	        		if (modeGroup.getSelection().getActionCommand() == "pvp")
	        			g.drawString("Player Yellow Win!", chessboard.GetWidth() * 50 / 2 - 92, chessboard.GetLength() * 50 / 2 - 20);
	        		else if (modeGroup.getSelection().getActionCommand() == "pvc") {
	        			if (flagOfTurnOrder == 1)
	        				g.drawString("Computer Win!", chessboard.GetWidth() * 50 / 2 - 78, chessboard.GetLength() * 50 / 2 - 20);
	        			else if (flagOfTurnOrder == 2)
	        				g.drawString("Player Win!", chessboard.GetWidth() * 50 / 2 - 60, chessboard.GetLength() * 50 / 2 - 20);
	        		}
	        			
	        	}
	        	else if (flagOfChessSignal == 2) {
	        		g.setColor(new Color(255, 106, 106, 200));      // RED
	        		g.fillRect(xCoordinate, yCoordinate, chessboard.GetWidth() * 50 + 1, chessboard.GetLength() * 50);
	        		g.setColor(Color.WHITE);
	        		Font fontCambriaItalic3 = new Font("Cambria", Font.ITALIC, 24);
	        		g.setFont(fontCambriaItalic3);
	        		if (modeGroup.getSelection().getActionCommand() == "pvp")
	        			g.drawString("Player Red Win!", chessboard.GetWidth() * 50 / 2 - 80, chessboard.GetLength() * 50 / 2 - 20);
	        		else if (modeGroup.getSelection().getActionCommand() == "pvc")
	        			if (flagOfTurnOrder == 1)
	        				g.drawString("Player Win!", chessboard.GetWidth() * 50 / 2 - 60, chessboard.GetLength() * 50 / 2 - 20);
	        			else if (flagOfTurnOrder == 2)
	        				g.drawString("Computer Win!", chessboard.GetWidth() * 50 / 2 - 78, chessboard.GetLength() * 50 / 2 - 20);
	        	}
	        }
	    }
	}
	
	//Define the mouse event method
	private class MouseChange extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
        	if (flagOfEndGame == 0) {
        		flagOfPoint = false;
        		flagOfMousePressed = true;
        		count = 0.01;
        		flagOfStartDraw = true;
        		point = e.getPoint();
        		
            	if (modeGroup.getSelection().getActionCommand() == "pvp")
            		GamePerson();
            	else if (modeGroup.getSelection().getActionCommand() == "pvc") {
            		if (flagOfTurnOrder == 2 || flagOfTurnOrder == 1) {
            			count = 0.01;
                		flagOfStartDraw = true;
            			GamePerson();
            			flagOfStartDraw = true;
            			if (flagOfDifficulty == 1 && flagOfPersonDone && flagOfEndGame == 0) {
            				count = 0.01;
                    		flagOfStartDraw = true;
                    		GameComputerSimple();
            			}
                		else if (flagOfDifficulty == 2 && flagOfPersonDone && flagOfEndGame == 0) {
                			count = 0.01;
                    		flagOfStartDraw = true;
                    		GameComputerHard();
                		}
            			flagOfPersonDone = false;
            		}
            	}
        	}
        }
    }
	
	private class MouseMoved extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent e) {
			if (flagOfStartGame == 1 && flagOfEndGame == 0) {
				mouseX = e.getX();
				repaint();
			}
			
		}
	}
	
	//Set the game people against people
	public void GamePerson() {
		//Game method
		for (int i = 50; i <= chessboard.GetWidth() * 50; i += 50) {
			if (point.x < i && point.x > i - 50 && point.y < 50 && point.y > 0) {
				flagOfPoint = true;
				int currentColumn = i / 50 - 1;
				Chess chess = new Chess(flagOfChessSignal);
				int currentRow = 0;
				for (int j = chessboard.GetLength() - 1; j >= 0; j--) {
					if (chessboard.chessboardSize[j][currentColumn] == 0) {
						currentRow = j;
						chessboard.chessboardSize[j][currentColumn] = chess.signal;
						
						currentRowX = currentRow;
						currentColumnY = currentColumn;
						//repaint();
						
						if (chess.signal == 1)
							flagOfChessSignal = 2;
						else if (chess.signal == 2)
							flagOfChessSignal = 1;
						
						flagOfPersonDone = true;
						break;
					}
				}
				
				//Judge the player who win
				if (chessboard.StatsOfWin(currentRow, currentColumn, chess.signal)) {
					flagOfEndGame = 1;
					break;
					//repaint();
				}
					
			}
		}
	}
	
	//Set the game people against computer
	public void GameComputerSimple() {
		//Game method
		flagOfPoint = true;
		boolean flagOfDone = false;
		for (int i = 50; i <= chessboard.GetWidth() * 50; i += 50) {
			int currentColumn = i / 50 - 1;
			Chess chess = new Chess(flagOfChessSignal);
			int currentRow = 0;
			for (int j = chessboard.GetLength() - 1; j >= 0; j--) {
				if (chessboard.chessboardSize[j][currentColumn] == 0) {
					currentRow = j;
					chessboard.chessboardSize[j][currentColumn] = chess.signal;
					
					currentRowX = currentRow;
					currentColumnY = currentColumn;
					
					//repaint();
					
					flagOfDone = true;
					if (chess.signal == 1)
						flagOfChessSignal = 2;
					else if (chess.signal == 2)
						flagOfChessSignal = 1;
					break;
				}
			}
			if (chessboard.StatsOfWin(currentRow, currentColumn, chess.signal)) {
				flagOfEndGame = 1;
				//repaint();
				break;
			}
			if (flagOfDone)
				break;
		}
	}
	
	public void GameComputerHard() {
		//Check computer next step
		flagOfPoint = true;
		boolean flagOfDone = false;
		for (int i = 50; i <= chessboard.GetWidth() * 50; i += 50) {                
			int currentColumn = i / 50 - 1;
			int tempChessSignal = flagOfChessSignal;
			Chess chess = new Chess(flagOfChessSignal);
			int currentRow = 0;
			for (int j = chessboard.GetLength() - 1; j >= 0; j--) {
				if (chessboard.StatsOfNextWin(j, currentColumn, tempChessSignal)) {
					for (int x = chessboard.GetLength() - 1; x >= 0; x--) {
						if (chessboard.chessboardSize[x][currentColumn] == 0) {
							currentRow = x;
							chessboard.chessboardSize[x][currentColumn] = chess.signal;
							
							currentRowX = currentRow;
							currentColumnY = currentColumn;
							
							
							//repaint();
							
							flagOfDone = true;
							if (chess.signal == 1)
								flagOfChessSignal = 2;
							else if (chess.signal == 2)
								flagOfChessSignal = 1;
							break;
						}
					}
				} 
				if (flagOfDone)
					break;
			}

			if (chessboard.StatsOfWin(currentRow, currentColumn, chess.signal)) {
				flagOfEndGame = 1;
				//repaint();
				
				break;
			}
			if (flagOfDone)
				break;
		}
		
		
		//Check player next step
		if (!flagOfDone) {
			for (int i = 50; i <= chessboard.GetWidth() * 50; i += 50) {                
				int currentColumn = i / 50 - 1;
				int tempChessSignal = 0;
				Chess chess = new Chess(flagOfChessSignal);
				if (flagOfChessSignal == 1)
					tempChessSignal = 2;
				else if (flagOfChessSignal == 2)
					tempChessSignal = 1;
				 
				int currentRow = 0;
				for (int j = chessboard.GetLength() - 1; j >= 0; j--) {
					if (chessboard.StatsOfNextWin(j, currentColumn, tempChessSignal) 
							&& (chessboard.chessboardSize[j - 1][currentColumn] != 0 || j == chessboard.GetLength() - 1)) {
						for (int x = chessboard.GetLength() - 1; x >= 0; x--) {
							if (chessboard.chessboardSize[x][currentColumn] == 0) {
								currentRow = x;
								chessboard.chessboardSize[x][currentColumn] = chess.signal;
								
								currentRowX = currentRow;
								currentColumnY = currentColumn;
								
								//repaint();
								
								flagOfDone = true;
								if (chess.signal == 1)
									flagOfChessSignal = 2;
								else if (chess.signal == 2)
									flagOfChessSignal = 1;
								break;
							}
						}
					} 
					if (flagOfDone)
						break;
				}

				if (chessboard.StatsOfWin(currentRow, currentColumn, chess.signal)) {
					flagOfEndGame = 1;
					//repaint();
					break;
				}
				if (flagOfDone)
					break;
			}
		}
		
		//Make the chess on or left or right the player
		if (!flagOfDone) {
			for (int i = 50; i <= chessboard.GetWidth() * 50; i += 50) {
				
				int currentColumn = i / 50 - 1;
				int tempChessSignal = 0;
				Chess chess = new Chess(flagOfChessSignal);
				if (flagOfChessSignal == 1)
					tempChessSignal = 2;
				else if (flagOfChessSignal == 2)
					tempChessSignal = 1;
				
				int currentRow = 0;
				for (int j = chessboard.GetLength() - 1; j >= 1; j--) {
					if ((chessboard.chessboardSize[j][currentColumn] == tempChessSignal 
							&& chessboard.chessboardSize[j - 1][currentColumn] == 0)) {
						currentRow = j - 1;
						chessboard.chessboardSize[j - 1][currentColumn] = chess.signal;
						
						currentRowX = currentRow;
						currentColumnY = currentColumn;

						
						//repaint();
						
						flagOfDone = true;
						if (chess.signal == 1)
							flagOfChessSignal = 2;
						else if (chess.signal == 2)
							flagOfChessSignal = 1;
						break;
					}
				}
				if (chessboard.StatsOfWin(currentRow, currentColumn, chess.signal)) {
					flagOfEndGame = 1;
					//repaint();
					break;
				}
				if (flagOfDone)
					break;
			}
		}
		if (!flagOfDone) {
			GameComputerSimple();
		}
		
	}
}
