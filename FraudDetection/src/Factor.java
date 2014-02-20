import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Factor
{
	//================================================================================
    // Properties
    //================================================================================
	
	public List<Variable> _variables;
	public List<Probability> _probabilities;
	
	
	//================================================================================
    // Constructors
    //================================================================================
	
	public Factor()
	{
		_variables = new ArrayList<Variable>();
		_probabilities = new ArrayList<Probability>();
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
			if (GetValue(v, i) != val)
			{
//				toRemove.add(f.getProbabilities().get(i));
				f.getProbabilities().get(i).isValid = false;
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
		Iterator<Probability> it = f.getProbabilities().iterator();
		while(it.hasNext())
		{
			Probability p = it.next();
			if (p.isValid == false)
			{
				it.remove();
			}
		}
		System.out.println(f.getProbabilities());
	}
	
	public static boolean GetValue(Variable v, int row)
	{
//		int mod = row/(v.getIndex() + 1) % 2;
		int mod = (row / (int)Math.pow(2, v.getIndex())) % 2;
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
	public List<Probability> getProbabilities() {
		return _probabilities;
	}
	public void setProbabilities(List<Probability> _probabilities) {
		this._probabilities = _probabilities;
	}
	
}
