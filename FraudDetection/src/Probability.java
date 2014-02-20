
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
		return String.valueOf(value);
	}
}
