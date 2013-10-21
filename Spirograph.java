import java.awt.*;

public class Spirograph{
	
	int numberOfPoints;
	protected SpirographPoint[] points;
	double outer, moving, offset;
	Color colour;
	
	public Spirograph(double outer, double moving, double offset, Color colour, int numberOfPoints) {
		this.outer = outer;
		this.moving = moving;
		this.offset = offset;
		this.colour = colour;
		this.numberOfPoints= numberOfPoints;
		points = new SpirographPoint[numberOfPoints];
		populateArray();
	}
	
	protected void populateArray(){
		for (int i=1; i<numberOfPoints; i++){
			points[i] = new SpirographPoint(calculateX(i), calculateY(i));
		}
	}
	
	protected double calculateX(double t){
		double x;
		x = (outer+moving)*Math.cos(t) - (moving+offset)*Math.cos(((outer+moving)/moving)*t);
		return x;
	}
	
	protected double calculateY(double t){
		double y;
		y = (outer+moving)*Math.sin(t) - (moving+offset)*Math.sin(((outer+moving)/moving)*t);
		return y;
	}
	
	public Color getColour(){
		return colour;
	}
	
	public int getPointX(int point){
		return (int)points[point].getXCoord();
	}
	
	public int getPointY(int point){
		return (int)points[point].getYCoord();
	}
}
