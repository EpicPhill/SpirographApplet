import java.awt.*;

public class SpirographPoint{

	protected double x, y;
	protected Color colour;
	
	public SpirographPoint(double xCoord, double yCoord){
		x = xCoord;
		y = yCoord;
	}
	
	public double getXCoord(){
		return x;
	}
	
	public double getYCoord(){
		return y;
	}
	
	public Color getColor(){
		return colour;
	}
	
}
