import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


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
	
	public Factor(Factor f)
	{
		_variables = new ArrayList<Variable>(f.getVariables());
		_probabilities = new ArrayList<Probability>(f.getProbabilities());
	}
	
	
	//================================================================================
    // Public Methods
    //================================================================================
	
	public static Factor Restrict(Factor fact, Variable var, boolean val)
	{
		// check if factor contains variable
		if (!fact.getVariables().contains(var))
		{
			System.out.println("Could not restrict: variable not found.");
			return null;
		}
		
		Factor f = new Factor(fact);
		
		// find all probabilities to remove
		for (int i = 0; i < f.getProbabilities().size(); i++)
		{
			if (GetValue(var, i) != val)
			{
				f.getProbabilities().get(i).isValid = false;
			}
		}
		
		// remove variable from factor and update indices
		for (int i = var.getIndex(); i < f.getVariables().size(); i++)
		{
			int idx = f.getVariables().get(i).getIndex() - 1;
			f.getVariables().get(i).setIndex(idx);
		}
		f.getVariables().remove(var);
		
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
		return f;
	}
	
	public static void Multiply(Factor f1, Factor f2)
	{
		List<Variable> intersectVars = new ArrayList<Variable>(f1.getVariables());
		intersectVars.retainAll(f2.getVariables());
		Factor f3 = new Factor();
		
		for (Variable v : intersectVars)
		{
			for (int i = 0; i < f1.getProbabilities().size(); i++)
			{
				Variable v1 = f1.getVariables().get(f1.getVariables().indexOf(v));
				boolean f1v = GetValue(v, i);
				
				for (int j = 0; j < f2.getProbabilities().size(); j++)
				{
					Variable v2 = f2.getVariables().get(f2.getVariables().indexOf(v));
					boolean f2v = GetValue(v2, j);
					if (f1v == f2v)
					{
						double pVal = f1.getProbabilities().get(i).value 
								* f2.getProbabilities().get(j).value;
						
						Probability p = new Probability(pVal);
						f3.getProbabilities().add(p);
					}
				}
			}
		}
		
		Set<Variable> set = new HashSet<Variable>();
		set.addAll(f1.getVariables());
		set.addAll(f2.getVariables());
		System.out.println(set);
		f3.setVariables(new ArrayList<Variable>(set));
		System.out.println(f3.getVariables());
		System.out.println(f3.getProbabilities());
	}
	
	public static boolean GetValue(Variable v, int row)
	{
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
