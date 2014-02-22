import java.text.DecimalFormat;
import java.text.NumberFormat;


public class Probability {
	public double value;
	public boolean isValid;
	
	public Probability(double v)
	{
		value = v;
		isValid = true;
	}
	
	@Override
	public String toString()
	{
		NumberFormat formatter = new DecimalFormat("###.##########");
		return formatter.format(value);
//		return String.valueOf(value);
	}
}
