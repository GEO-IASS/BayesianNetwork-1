import java.util.ArrayList;
import java.util.List;


public class Factor
{
	//================================================================================
    // Properties
    //================================================================================
	
	private List<Variable> _variables;
	private List<Double> _probabilities;
	
	//================================================================================
    // Constructors
    //================================================================================
	
	public Factor()
	{
		_variables = new ArrayList<Variable>();
		_probabilities = new ArrayList<Double>();
	}
	
	//================================================================================
    // Public Methods
    //================================================================================
	public static void Restrict(Factor f, Variable v, boolean val)
	{
		// check if factor contains variable
		if (!f.getVariables().contains(v))
		{
			System.out.println("Could not restrict: variable not found.");
			return;
		}
		
		// find all probabilities to remove
		List<Double> toRemove = new ArrayList<Double>();
		for (int i = 0; i < f.getProbabilities().size(); i++)
		{
			if (GetValue(v, i) == val)
			{
				toRemove.add(f.getProbabilities().get(i));
			}
		}
		
		// remove variable from factor and update indices
		for (int i = v.getIndex(); i < f.getVariables().size(); i++)
		{
			int idx = f.getVariables().get(i).getIndex() - 1;
			f.getVariables().get(i).setIndex(idx);
		}
		f.getVariables().remove(v);
		
		// remove probabilities
		for (Double d : toRemove)
		{
			f.getProbabilities().remove(d);
		}
	}
	
	public static boolean GetValue(Variable v, int row)
	{
		int mod = row/(v.getIndex() + 1) % 2;
		if (mod == 0)
		{
			return true;
		}
		return false;
	}
	
	//================================================================================
    // Accessors
    //================================================================================
	
	public List<Variable> getVariables() {
		return _variables;
	}
	public void setVariables(List<Variable> _variables) {
		this._variables = _variables;
	}
	public List<Double> getProbabilities() {
		return _probabilities;
	}
	public void setProbabilities(List<Double> _probabilities) {
		this._probabilities = _probabilities;
	}
}
