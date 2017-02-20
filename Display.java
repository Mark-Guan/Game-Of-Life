/*Note: after making a change to the board, the window sometimes needs to be resized a little
 * Written by Mark Guan
 * Period 6
 * 24 November 2013
 */
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class Display implements MouseListener, ActionListener{

	private Rules rulesForGame = new Rules(200,200);
	public static boolean color = false;
	
	//the frame that holds everything
	JFrame frame=new JFrame("Game of Life");
	
	//where the board is drawn
	JPanel myPanel = new MyPanel(rulesForGame);
	
	//slider
	final JSlider mySlider=new JSlider(1,0,200,100);
	
	//buttons
	JButton nextGen = new JButton("Next Generation");
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	JButton clearBoard = new JButton("Clear Board");
	JButton randomGame = new JButton("Random Game");
	JToggleButton makeItColorful = new JToggleButton("Make it Colorful");
	
	//containers for the buttons
	Container south = new Container();
	
	//Constructs a display with the given Buttons, panels, frames, and slider
	public Display(){
		
		//creating a JFrame with a BorderLayout
		frame.setSize(400,400);
		frame.setLayout(new BorderLayout());
		
		//adding ,myPanel to the frame
		frame.add(myPanel, BorderLayout.CENTER);
		
		//adding mySlider to the frame
		frame.add(mySlider, BorderLayout.EAST);
		
		//adding the south button container to the frame and setting is Grid Layout to 1 by 5
		frame.add(south, BorderLayout.SOUTH);
		south.setLayout(new GridLayout(1,5));
		
		//adding buttons to the south and west button containers
		south.add(nextGen);
		south.add(start);
		south.add(stop);
		south.add(clearBoard);
		south.add(randomGame);
		south.add(makeItColorful);
		
		//adding action listeners to my buttons
		nextGen.addActionListener(this);
		start.addActionListener(this);
		stop.addActionListener(this);
		clearBoard.addActionListener(this);
		randomGame.addActionListener(this);
		makeItColorful.addActionListener(new ToggleButtonListener());
		
		//adding action listener to mySlider
		mySlider.addChangeListener(new SliderListener());
		
		//adding mouse listener to myPanel
		myPanel.addMouseListener(this);
		
		//making frame visible
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	//code that doesn't do anything but is necessary for class to compile
	public void mouseReleased(MouseEvent event){
	}
	public void mouseEntered(MouseEvent event){
	}
	public void mouseExited(MouseEvent event){
	}
	public void mousePressed(MouseEvent event){
	}
	
	
	//tells the panel to change the cell from dead to alive when the mouse is clicked and vice versa
	public void mouseClicked(MouseEvent event){
		
		int col = (event.getY() / (myPanel.getHeight() / rulesForGame.getNumColumns()));
		int row = (event.getX() / (myPanel.getWidth() /rulesForGame.getNumRows()));
		if(rulesForGame.isValidLoc(row, col) && rulesForGame.getCell(row, col) == 1){
			rulesForGame.setCell(row,col,0);
		}
		else if (rulesForGame.isValidLoc(row, col)){
			rulesForGame.setCell(row,col,1);
		}
		frame.repaint();
		
	}
	
	//says what happens after one of the Jbuttons is pressed
	public void actionPerformed(ActionEvent e){
		//if the nextGen button is pressed then the board will update its generation and then repaint
		if(e.getSource().equals(nextGen) == true){
			rulesForGame.nextGen();
			frame.repaint();
		}
		//if the start button is pressed then the timer will start
		if(e.getSource().equals(start) == true){
			timer.start();
		}
		//if the stop button is pressed then the timer will stop
		if(e.getSource().equals(stop) == true){
			timer.stop();
		}
		//if the clearBoard button is pressed then the board will be completely cleared
		if(e.getSource().equals(clearBoard) == true){
			rulesForGame.clear();
			frame.repaint();
		}
		//if the randomGame button is pressed then it will generate a random board where 50% of cells are randomly set to alive
		if(e.getSource().equals(randomGame) == true){
			rulesForGame.randomGame();
			frame.repaint();
		}
	}
	
	//A class that represents an ActionListener for my toggle button
	//when the button is activated the board will be colorful, and when its not the board will be black and white
	 class ToggleButtonListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            if (color == false)
	                color = true;
	            else if (color == true)
	            	color = false;
	        }
	}
	
	//A class that represents a ChangeListener for mySlider
	class SliderListener implements ChangeListener{
		public void stateChanged(ChangeEvent e){
			timer.setDelay(mySlider.getValue());
		}

	}
	
	
	//ActonListener for the timer
	//it will call for the Next Generation and then repaint the board
	ActionListener timerlistener = new ActionListener(){
		 public void actionPerformed(ActionEvent event){
			 	rulesForGame.nextGen();
				frame.repaint();
			  }
	};
	//creates a new timer that correlates with the value that the slider sends it
	Timer timer = new Timer(mySlider.getValue(), timerlistener);
	
	

	public static void main(String[] args) {
		new Display();
	}

}
