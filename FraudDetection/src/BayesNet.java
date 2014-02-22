import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BayesNet {

	public static void main(String[] args) 
	{
		Factor fraudF = new Factor();
		fraudF.setProbabilities(BayesNet.MakeProbabilities(Arrays.asList(0.01, 0.004)));
		fraudF.setVariables(BayesNet.MakeVariables(Arrays.asList("trav")));
		
		Factor foreignF = new Factor();
		foreignF.setProbabilities(BayesNet.MakeProbabilities(Arrays.asList(0.9, 0.1, 0.9, 0.01)));
		foreignF.setVariables(BayesNet.MakeVariables(Arrays.asList("fraud", "trav")));
		
		Factor ownsCompF = new Factor();
		ownsCompF.setProbabilities(BayesNet.MakeProbabilities(Arrays.asList(0.7)));
		
		Factor internetF = new Factor();
		internetF.setProbabilities(BayesNet.MakeProbabilities(Arrays.asList(0.02, 0.011, 0.01, 0.001)));
		internetF.setVariables(BayesNet.MakeVariables(Arrays.asList("fraud", "oc")));
		
		Factor compBuyF = new Factor();
		compBuyF.setProbabilities(BayesNet.MakeProbabilities(Arrays.asList(0.01, 0.001)));
		compBuyF.setVariables(BayesNet.MakeVariables(Arrays.asList("oc")));
		
		Factor travelF = new Factor();
		travelF.setProbabilities(BayesNet.MakeProbabilities(Arrays.asList(0.05)));
		
		// factor list
		List<Factor> factList = new ArrayList<Factor>();
		factList.add(fraudF); factList.add(foreignF); factList.add(ownsCompF);
		factList.add(internetF); factList.add(compBuyF);factList.add(travelF);
		
		// query variables
		List<String> varNames = new ArrayList<String>()
			{{
				add("fp");
				add("ip");
				add("crp");
			}};
		List<Variable> queryVars = BayesNet.MakeVariables(varNames);
		queryVars.get(0).setValue(true);
		queryVars.get(1).setValue(false);
		queryVars.get(2).setValue(true);
		
		// hidden variables
		varNames = new ArrayList<String>()
				{{
					add("trav");
					add("oc");
				}};
		List<Variable> orderedHiddenVars = BayesNet.MakeVariables(varNames);
		
		// evidence
		varNames = new ArrayList<String>()
				{{
					add("fraud");
				}};
		List<Variable> evidenceVars = BayesNet.MakeVariables(varNames);
		evidenceVars.get(0).setValue(true);
		
		Factor.Inference(factList, queryVars, orderedHiddenVars, evidenceVars);
	}
	
	public static List<Probability> MakeProbabilities(List<Double> probs)
	{
		List<Probability> pList = new ArrayList<Probability>();
		for (int i = 0; i < probs.size(); i++)
		{
			Probability p = new Probability(probs.get(i));
			pList.add(p);
		}
		return pList;
	}
	
	public static List<Variable> MakeVariables(List<String> names)
	{
		List<Variable> varList = new ArrayList<Variable>();
		for (int i = 0; i < names.size(); i++)
		{
			Variable v = new Variable();
			v.setDesc(names.get(i));
			v.setIndex(names.size() - 1 - i);
			varList.add(v);
		}
		return varList;
	}
	
	public static void InferenceTest1()
	{
		Factor cloudyFact = new Factor();
		cloudyFact.getProbabilities().add(new Probability(0.5));
		cloudyFact.getProbabilities().add(new Probability(0.5));
		Variable cloudyVar = new Variable();
		cloudyVar.setIndex(0);
		cloudyVar.setDesc("cloudy");
		cloudyFact.getVariables().add(cloudyVar);
		
		Factor sprinklerFact = new Factor();
		sprinklerFact.getProbabilities().add(new Probability(0.1));
		sprinklerFact.getProbabilities().add(new Probability(0.9));
		sprinklerFact.getProbabilities().add(new Probability(0.5));
		sprinklerFact.getProbabilities().add(new Probability(0.5));
		Variable sprinklerVar = new Variable();
		sprinklerVar.setIndex(0);
		sprinklerVar.setDesc("sprinkler");
		Variable c2var = new Variable(cloudyVar);
		c2var.setIndex(1);
		sprinklerFact.getVariables().add(sprinklerVar);
		sprinklerFact.getVariables().add(c2var);
		
		Factor rainFact = new Factor();
		rainFact.getProbabilities().add(new Probability(0.8));
		rainFact.getProbabilities().add(new Probability(0.2));
		rainFact.getProbabilities().add(new Probability(0.2));
		rainFact.getProbabilities().add(new Probability(0.8));
		Variable rainVar = new Variable();
		rainVar.setIndex(0);
		rainVar.setDesc("rain");
		Variable c3var = new Variable(cloudyVar);
		c3var.setIndex(1);
		rainFact.getVariables().add(rainVar);
		rainFact.getVariables().add(c3var);
		
		Factor wetGrassFact = new Factor();
		wetGrassFact.getProbabilities().add(new Probability(0.99));
		wetGrassFact.getProbabilities().add(new Probability(0.01));
		wetGrassFact.getProbabilities().add(new Probability(0.9));
		wetGrassFact.getProbabilities().add(new Probability(0.1));
		wetGrassFact.getProbabilities().add(new Probability(0.9));
		wetGrassFact.getProbabilities().add(new Probability(0.1));
		wetGrassFact.getProbabilities().add(new Probability(0.0));
		wetGrassFact.getProbabilities().add(new Probability(1));
		Variable wetGrassVar = new Variable();
		wetGrassVar.setIndex(0);
		wetGrassVar.setDesc("wetGrass");
		Variable r2 = new Variable(rainVar);
		r2.setIndex(1);
		Variable s2 = new Variable(sprinklerVar);
		s2.setIndex(2);
		wetGrassFact.getVariables().add(wetGrassVar);
		wetGrassFact.getVariables().add(r2);
		wetGrassFact.getVariables().add(s2);
		
		List<Factor> fList = new ArrayList<Factor>();
		fList.add(cloudyFact);
		fList.add(sprinklerFact);
		fList.add(rainFact);
		fList.add(wetGrassFact);
		
		List<Variable> queryVars = new ArrayList<Variable>();
		sprinklerVar.setValue(true);
		queryVars.add(sprinklerVar);
		
		List<Variable> evidVars = new ArrayList<Variable>();
		wetGrassVar.setValue(true);
		evidVars.add(wetGrassVar);
		
		List<Variable> hidVars = new ArrayList<Variable>();
		hidVars.add(rainVar);
		hidVars.add(cloudyVar);
		
		Factor.Inference(fList, queryVars, hidVars, evidVars);
	}
	
	public static void NormalizeTest1()
	{
		Factor f = new Factor();
		Variable A = new Variable();
		A.setDesc("a");
		A.setIndex(0);
		f.getProbabilities().add(new Probability(0.00059224));
		f.getProbabilities().add(new Probability(0.0014919));
		f.getVariables().add(A);
		Factor.Normalize(f);
	}
	
	private static void SumTest2()
	{
		Factor f = new Factor();
		Variable A = new Variable();
		Variable B = new Variable();
		Variable C = new Variable();
		A.setDesc("a"); B.setDesc("b"); C.setDesc("c");
		
		f.getProbabilities().add(new Probability(0.06));
		f.getProbabilities().add(new Probability(0.24));
		f.getProbabilities().add(new Probability(0.42));
		f.getProbabilities().add(new Probability(0.28));
		f.getProbabilities().add(new Probability(0.18));
		f.getProbabilities().add(new Probability(0.72));
		f.getProbabilities().add(new Probability(0.06));
		f.getProbabilities().add(new Probability(0.04));
		f.getVariables().add(A); f.getVariables().add(B); f.getVariables().add(C);
		
		Factor.SumOut(f, A);
	}
	
	private static void SumTest1()
	{
		Factor f = new Factor();
		Variable A = new Variable();
		Variable B = new Variable();
		A.setDesc("a"); B.setDesc("b");
		A.setIndex(1); B.setIndex(0);
		
		f.getProbabilities().add(new Probability(0.9));
		f.getProbabilities().add(new Probability(0.1));
		f.getProbabilities().add(new Probability(0.4));
		f.getProbabilities().add(new Probability(0.6));
		f.getVariables().add(A); f.getVariables().add(B);
		
		Factor.SumOut(f, A);
	}
	
	private static void RestrictTest1()
	{
		Factor f = new Factor();
		Variable A = new Variable();
		Variable B = new Variable();
		Variable C = new Variable();
		A.setDesc("a"); B.setDesc("b"); C.setDesc("c");
		
//		// test restrict
		A.setIndex(2); B.setIndex(1); C.setIndex(0);
		f.getProbabilities().add(new Probability(0.06));
		f.getProbabilities().add(new Probability(0.24));
		f.getProbabilities().add(new Probability(0.42));
		f.getProbabilities().add(new Probability(0.28));
		f.getProbabilities().add(new Probability(0.18));
		f.getProbabilities().add(new Probability(0.72));
		f.getProbabilities().add(new Probability(0.06));
		f.getProbabilities().add(new Probability(0.04));
		f.getVariables().add(A); f.getVariables().add(B); f.getVariables().add(C);
		Factor.Restrict(f, B, true);
	}
	
	private static void ProductTest1()
	{
		// test product
		Factor f1 = new Factor();
		Factor f2 = new Factor();
		Variable B2 = new Variable();
		Variable C2 = new Variable();
		Variable A = new Variable();
		Variable B = new Variable();
		A.setDesc("a"); B.setDesc("b");
		
		f1.getProbabilities().add(new Probability(0.9));
		f1.getProbabilities().add(new Probability(0.1));
		f1.getProbabilities().add(new Probability(0.4));
		f1.getProbabilities().add(new Probability(0.6));
		
		f2.getProbabilities().add(new Probability(0.7));
		f2.getProbabilities().add(new Probability(0.3));
		f2.getProbabilities().add(new Probability(0.8));
		f2.getProbabilities().add(new Probability(0.2));
		
		B2.setDesc("b");
		C2.setDesc("c");
		A.setIndex(1);
		B.setIndex(0);
		B2.setIndex(1);
		C2.setIndex(0);
		
		f1.getVariables().add(A); f1.getVariables().add(B);
		f2.getVariables().add(B2); f2.getVariables().add(C2);
		
		Factor.Multiply(f1, f2);
	}
	
	private static void ProductTest2()
	{
		// test product
		Factor f1 = new Factor();
		Factor f2 = new Factor();
		Variable B2 = new Variable();
		Variable C2 = new Variable();
		Variable D2 = new Variable();
		Variable A = new Variable();
		Variable B = new Variable();
		Variable C = new Variable();
		A.setDesc("a"); B.setDesc("b"); C.setDesc("c");
		B2.setDesc("b"); C2.setDesc("c"); D2.setDesc("d");
		A.setIndex(2); B.setIndex(1); C.setIndex(0); 
		B2.setIndex(2); C2.setIndex(1); D2.setIndex(0);  
		
		f1.getProbabilities().add(new Probability(0.06));
		f1.getProbabilities().add(new Probability(0.24));
		f1.getProbabilities().add(new Probability(0.42));
		f1.getProbabilities().add(new Probability(0.28));
		f1.getProbabilities().add(new Probability(0.18));
		f1.getProbabilities().add(new Probability(0.72));
		f1.getProbabilities().add(new Probability(0.06));
		f1.getProbabilities().add(new Probability(0.04));
		
		f2.getProbabilities().add(new Probability(0.1));
		f2.getProbabilities().add(new Probability(0.2));
		f2.getProbabilities().add(new Probability(0.3));
		f2.getProbabilities().add(new Probability(0.4));
		f2.getProbabilities().add(new Probability(0.5));
		f2.getProbabilities().add(new Probability(0.05));
		f2.getProbabilities().add(new Probability(0.6));
		f2.getProbabilities().add(new Probability(0.01));
		
		f1.getVariables().add(A); f1.getVariables().add(B); f1.getVariables().add(C);
		f2.getVariables().add(B2); f2.getVariables().add(C2); f2.getVariables().add(D2);
		
		Factor.Multiply(f1, f2);
	}

}
