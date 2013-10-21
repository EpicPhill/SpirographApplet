import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SpiroMain extends Applet implements ActionListener{
	
	protected int numberOfPoints = 1000000;
	int width, height;
	
	protected TextField textOuter, textMoving, textOffset;
	protected Button drawGraph, clear;
	protected Label labelOuter, labelMoving, labelOffset, labelColour;
	protected Choice colourChoice;
	
	protected Graphics graph;
	protected Image backBuffer;
	
	public void init(){
		width = getSize().width;
		//I want the spirographs to account for the bar across the top
		height = getSize().height-40;
		setBackground(Color.lightGray);
		
		backBuffer = createImage(width,height);
		graph = backBuffer.getGraphics();
		
		CreateUI();
		drawBanner();
	}

	//sets up all of the various UI elements and adds them to the applet
	public void CreateUI(){
	
		textOuter = new TextField(8);
		textMoving = new TextField(8);
		textOffset = new TextField(8);
		
		labelOuter= new Label("Outer circle radius");
		labelMoving = new Label("Moving circle radius");
		labelOffset = new Label("Pencil Offset");
		labelColour = new Label("Graph colour");
		
		colourChoice = new Choice();
		colourChoice.addItem("Black");
		colourChoice.addItem("Blue");
		colourChoice.addItem("Red");
		colourChoice.addItem("Green");
		colourChoice.addItem("Orange");
		colourChoice.addItem("Pink");
		colourChoice.addItem("Cyan");
		colourChoice.addItem("Magenta");
		
		add(labelOuter);
		add(textOuter);
		add(labelMoving);
		add(textMoving);
		add(labelOffset);
		add(textOffset);
		add(labelColour);
		add(colourChoice);
		
		labelOuter.setBackground(Color.gray);
		labelMoving.setBackground(Color.gray);
		labelOffset.setBackground(Color.gray);
		labelColour.setBackground(Color.gray);
		
		textOuter.setText("80");
		textMoving.setText("90");
		textOffset.setText("3");
		
		drawGraph = new Button("Draw!");
		clear = new Button("Clear");
		add(drawGraph);
		add(clear);
		drawGraph.addActionListener(this);
		clear.addActionListener(this);
	}
	
	//listens for when one of the two buttons on screen is pushed, and carries out the corresponding action
	public void actionPerformed (ActionEvent e){
		if (e.getSource() == drawGraph)
			getValues();
		if (e.getSource() == clear)
			clearApplet();
	}
	
	public void getValues(){
		double outer, moving, offset;
		Color colour;
		
		try 
		{
			//get the values from the text boxes on the page
			outer= new Double(textOuter.getText());
			moving = new Double(textMoving.getText());
			offset = new Double(textOffset.getText());
			colour = readColor(colourChoice.getSelectedItem());
			Spirograph spirograph = new Spirograph(outer, moving, offset, colour, numberOfPoints);
			drawGraph(spirograph);
		}
		catch (NumberFormatException e)
		{
			//gives a popup message if the numbers are invalid (most likely because a letter)
			JOptionPane.showMessageDialog(null, "There was a problem with your numbers. Please try again", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Color readColor(String choice){
		//takes the strings from the choice box and assigns the right color object
		if (choice == "Blue")
			return Color.blue;
		else if (choice == "Red")
			return Color.red;
		else if (choice == "Green")	
			return Color.green;		
		else if (choice == "Orange")	
			return Color.orange;
		else if (choice == "Pink")	
			return Color.pink;
		else if (choice == "Cyan")	
			return Color.cyan;
		else if (choice == "Magenta")
			return Color.magenta;
		//assigns black to anything that isn't predefined (as well as Black) just in case it bugs out
		else return Color.black;
		
	}
	
	public void drawGraph(Spirograph spirograph){
		graph.setColor(spirograph.getColour());
		for (int i=1; i<numberOfPoints-1; i++){
			int x = width/2-spirograph.getPointX(i);
			int y = height/2-spirograph.getPointY(i);
			graph.drawLine(x,y,x,y);
		}
		repaint();
		drawBanner();
	}
	
	//clears the screen of all drawn objects
	public void clearApplet(){
		graph.clearRect(0,0,width,height);
		drawBanner();
	}
	
	//draws the pretty little bar across the top and round the sides
	public void drawBanner(){
		graph.setColor(Color.darkGray);
		graph.drawLine(0,0+40,width,0+40);
		graph.setColor(Color.gray);
		graph.fillRect(0,0,width,0+40);
		repaint();
	}
	
	public void paint(Graphics g){
		update(g);
	}
	
	//draws the image backBuffer to the screen
	public void update(Graphics g){
		g.drawImage(backBuffer,0,0,this);
	}

}
