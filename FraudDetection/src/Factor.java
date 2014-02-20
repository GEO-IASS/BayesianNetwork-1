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
	
	public static Factor Multiply(Factor f1, Factor f2)
	{
		List<Variable> intersectVars = new ArrayList<Variable>(f1.getVariables());
		intersectVars.retainAll(f2.getVariables());
		Factor f3 = new Factor();
		
		// for each probability in f1, find corresponding match in f2 and multiply
		for (int i = 0; i < f1.getProbabilities().size(); i++)
		{
			List<Boolean> varVals1 = new ArrayList<Boolean>();
			for (Variable v : intersectVars)
			{
				Variable v1 = f1.getVariables().get(f1.getVariables().indexOf(v));
				varVals1.add(GetValue(v1, i));
			}

			for (int j = 0; j < f2.getProbabilities().size(); j++)
			{
				List<Boolean> varVals2 = new ArrayList<Boolean>();
				for (Variable v : intersectVars)
				{
					Variable v2 = f2.getVariables().get(f2.getVariables().indexOf(v));
					varVals2.add(GetValue(v2, j));
				}
				
				if (varVals1.equals(varVals2))
				{
					double pVal = f1.getProbabilities().get(i).value 
							* f2.getProbabilities().get(j).value;
					
					System.out.format("%d,  %d, [%b], %f \n", i, j, varVals1, pVal);
					Probability p = new Probability(pVal);
					f3.getProbabilities().add(p);
				}
			}
		}
		
		// add variables from first factor
		for (Variable v : f1.getVariables())
		{
			if (!f3.getVariables().contains(v))
			{
				f3.getVariables().add(v);
			}
		}
		
		// add variables from second factor
		for (Variable v : f2.getVariables())
		{
			if (!f3.getVariables().contains(v))
			{
				f3.getVariables().add(v);
			}
		}
		
		// update indices
		Iterator<Variable> it = f3.getVariables().iterator();
		int idx = f3.getVariables().size()-1;
		while(it.hasNext())
		{
			Variable v = it.next();
			v.setIndex(idx);
			idx--;
		}
		System.out.println(f3.getVariables());
		return f3;
	}
	
	public static Factor SumOut(Factor fact, Variable var)
	{
		Factor f = new Factor();
		
		// create new list of remaining vars 
		List<Variable> varList = new ArrayList<Variable>(fact.getVariables());
		for (int i = 0; i < fact.getVariables().size(); i++)
		{	
			int indx = fact.getVariables().size() - 1 - i;
			varList.get(i).setIndex(indx);
		}
		varList.remove(var);
		
		// create list of distinct variable combinations from remaining vars
		List<List<Boolean>> allVarVals = new ArrayList<List<Boolean>>();
		for (int i = 0; i < fact.getProbabilities().size(); i++)
		{
			List<Boolean> varVals = new ArrayList<Boolean>();
			for (Variable v : varList)
			{
				varVals.add(GetValue(v, i));
			}
			if (!allVarVals.contains(varVals))
			{
				allVarVals.add(varVals);
			}
		}
		
		// for each combination, go through prob list and sum
		for (List<Boolean> valList : allVarVals)
		{
			double summedProb = 0;
			for (int i = 0; i < fact.getProbabilities().size(); i++)
			{
				List<Boolean> varVals = new ArrayList<Boolean>();
				for (Variable v : varList)
				{
					varVals.add(GetValue(v, i));
				}
				
				if (valList.equals(varVals))
				{
					summedProb += fact.getProbabilities().get(i).value;
				}
			}
			f.getProbabilities().add(new Probability(summedProb));
		}
		
		f.setVariables(varList);
		System.out.println(f.getVariables());
		System.out.println(f.getProbabilities());
		return f;
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
