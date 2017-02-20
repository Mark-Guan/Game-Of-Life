/*MyPanel.java : represents a JPanel but has a set of Rules as its private data
 * 
 * Written by Mark Guan
 * Period 6
 * 24 November 2013
 */

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class MyPanel extends JPanel{

	private Rules rulesForGame;
	
	//Constructs a MyPanel based of the Rules that are given
	public MyPanel(Rules rulesForGame){
		this.rulesForGame = rulesForGame;

	}
	
	//first this draws the grid based of the number of rows and columns that the board has
	// then it paints the MyPanel making cells that are alive, colored, and making cells that are dead, white.
	public void paintComponent(Graphics g) {
		Random random = new Random();		
		super.paintComponent(g);
		double boxWidth = this.getWidth() / rulesForGame.getNumRows();
		double boxHeight = this.getHeight() / rulesForGame.getNumRows();
		
		for(int i = 0; i <rulesForGame.getNumRows(); i++){
			for(int j = 0; j <rulesForGame.getNumRows(); j++){
				if(rulesForGame.getCell(i,j) == 1 && Display.color == true){
					g.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
					g.fillRect((int)(i*boxWidth), (int)(j*boxHeight), (int)boxWidth, (int)boxHeight);
				}
				else if(rulesForGame.getCell(i,j) == 1){
					g.setColor(Color.BLACK);
					g.fillRect((int)(i*boxWidth), (int)(j*boxHeight), (int)boxWidth, (int)boxHeight);
				}
			}	

		}
		
		
		//Draws the Grid
		for(int i = 0; i<rulesForGame.getNumRows()+ 1; i ++){
				g.setColor(new Color(0,0,0));
				g.drawLine((int)(i*boxWidth), 0, (int)(i*boxWidth), rulesForGame.getNumRows()*(int)boxHeight);
				g.drawLine(0, (int)(i*boxHeight), rulesForGame.getNumRows()*(int)boxWidth, (int)(i*boxHeight));
			
		}
	}
}
