import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BayesNet {

	public static void main(String[] args) 
	{
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream("A2.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(out);
		
		Factor fraudF = new Factor();
		fraudF.setProbabilities(BayesNet.MakeProbabilities(Arrays.asList(0.01, 0.004, 0.99, 0.996)));
		fraudF.setVariables(BayesNet.MakeVariables(Arrays.asList("fraud", "trav")));
		
		Factor foreignF = new Factor();
		foreignF.setProbabilities(BayesNet.MakeProbabilities(Arrays.asList(0.9, 0.1, 0.9, 0.01, 0.1, 0.9, 0.1, 0.99)));
		foreignF.setVariables(BayesNet.MakeVariables(Arrays.asList("fp", "fraud", "trav")));
		
		Factor ownsCompF = new Factor();
		ownsCompF.setProbabilities(BayesNet.MakeProbabilities(Arrays.asList(0.7, 0.3)));
		ownsCompF.setVariables(BayesNet.MakeVariables(Arrays.asList("oc")));
		
		Factor internetF = new Factor();
		internetF.setProbabilities(BayesNet.MakeProbabilities(Arrays.asList(0.02, 0.011, 0.01, 0.001, 0.98, 0.989, 0.99, 0.999)));
		internetF.setVariables(BayesNet.MakeVariables(Arrays.asList("ip", "fraud", "oc")));
		
		Factor compBuyF = new Factor();
		compBuyF.setProbabilities(BayesNet.MakeProbabilities(Arrays.asList(0.01, 0.001, 0.9, 0.999)));
		compBuyF.setVariables(BayesNet.MakeVariables(Arrays.asList("crp", "oc")));
		
		Factor travelF = new Factor();
		travelF.setProbabilities(BayesNet.MakeProbabilities(Arrays.asList(0.05, 0.95)));
		travelF.setVariables(BayesNet.MakeVariables(Arrays.asList("trav")));
		
		Factor utilityF = new Factor();
		utilityF.setProbabilities(BayesNet.MakeProbabilities(Arrays.asList(0.0, -1000.0, -10.0, 5.0)));
		utilityF.setVariables(BayesNet.MakeVariables(Arrays.asList("fraud", "block")));
		
		// factor list
		List<Factor> factList = new ArrayList<Factor>();
		factList.add(fraudF); factList.add(foreignF); factList.add(ownsCompF);
		factList.add(internetF); factList.add(compBuyF);factList.add(travelF);
		factList.add(utilityF);
		
		
		// info for P(Fraud)
		// query variables
		List<String> varNames1 = new ArrayList<String>()
			{{
				add("fraud");
			}};
		List<Variable> queryVars1 = BayesNet.MakeVariables(varNames1);
		queryVars1.get(0).setValue(true);

		
		// hidden variables
		varNames1 = new ArrayList<String>()
				{{
					add("trav");
					add("fp");
					add("ip");
					add("oc");
					add("crp");
				}};
		List<Variable> orderedHiddenVars1 = BayesNet.MakeVariables(varNames1);
		
		// evidence
		varNames1 = new ArrayList<String>()
				{{

				}};
		List<Variable> evidenceVars1 = BayesNet.MakeVariables(varNames1);
		
		
		// info for P(Fraud | FP, !IP, CRP)
//		 query variables
		List<String> varNames = new ArrayList<String>()
			{{
				add("fraud");
			}};
		List<Variable> queryVars = BayesNet.MakeVariables(varNames);
		queryVars.get(0).setValue(true);
		
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
					add("fp");
					add("ip");
					add("crp");
				}};
		List<Variable> evidenceVars = BayesNet.MakeVariables(varNames);
		evidenceVars.get(0).setValue(true);
		evidenceVars.get(1).setValue(false);
		evidenceVars.get(2).setValue(true);

		
		// info for P(Fraud | FP, !IP, CRP, trav)
//		 query variables
		List<String> varNamesC = new ArrayList<String>()
			{{
				add("fraud");
			}};
		List<Variable> queryVarsC = BayesNet.MakeVariables(varNamesC);
		queryVarsC.get(0).setValue(true);
		
		// hidden variables
		varNamesC = new ArrayList<String>()
				{{
					add("oc");
				}};
		List<Variable> orderedHiddenVarsC = BayesNet.MakeVariables(varNamesC);
		
		// evidence
		varNamesC = new ArrayList<String>()
				{{
					add("fp");
					add("ip");
					add("crp");
					add("trav");
				}};
		List<Variable> evidenceVarsC = BayesNet.MakeVariables(varNamesC);
		evidenceVarsC.get(0).setValue(true);
		evidenceVarsC.get(1).setValue(false);
		evidenceVarsC.get(2).setValue(true);
		evidenceVarsC.get(3).setValue(true);
		
		///-------------------------------------------------------------------------------
		// info for P(Fraud | IP)
//		 query variables
		List<String> varNamesD1 = new ArrayList<String>()
			{{
				add("fraud");
			}};
		List<Variable> queryVarsD1 = BayesNet.MakeVariables(varNamesD1);
		queryVarsD1.get(0).setValue(true);
		
		// hidden variables
		varNamesD1 = new ArrayList<String>()
				{{
					add("trav");
					add("fp");
					add("oc");
					add("crp");
				}};
		List<Variable> orderedHiddenVarsD1 = BayesNet.MakeVariables(varNamesD1);
		
		// evidence
		varNamesD1 = new ArrayList<String>()
				{{
					add("ip");
				}};
		List<Variable> evidenceVarsD1 = BayesNet.MakeVariables(varNamesD1);
		evidenceVarsD1.get(0).setValue(true);
		
		
		// info for P(Fraud | IP, CRP)
//		 query variables
		List<String> varNamesD1b = new ArrayList<String>()
			{{
				add("fraud");
			}};
		List<Variable> queryVarsD1b = BayesNet.MakeVariables(varNamesD1b);
		queryVarsD1b.get(0).setValue(true);
		
		// hidden variables
		varNamesD1b = new ArrayList<String>()
				{{
					add("trav");
					add("fp");
					add("oc");
				}};
		List<Variable> orderedHiddenVarsD1b = BayesNet.MakeVariables(varNamesD1b);
		
		// evidence
		varNamesD1b = new ArrayList<String>()
				{{
					add("ip");
					add("crp");
				}};
		List<Variable> evidenceVarsD1b = BayesNet.MakeVariables(varNamesD1b);
		evidenceVarsD1b.get(0).setValue(true);
		evidenceVarsD1b.get(1).setValue(true);
		
		// info for P(Fraud | IP, CRP, ~trav)
//		 query variables
		List<String> varNamesD1c = new ArrayList<String>()
			{{
				add("fraud");
			}};
		List<Variable> queryVarsD1c = BayesNet.MakeVariables(varNamesD1c);
		queryVarsD1c.get(0).setValue(true);
		
		// hidden variables
		varNamesD1c = new ArrayList<String>()
				{{
					add("fp");
					add("oc");
				}};
		List<Variable> orderedHiddenVarsD1c = BayesNet.MakeVariables(varNamesD1c);
		
		// evidence
		varNamesD1c = new ArrayList<String>()
				{{
					add("ip");
					add("crp");
					add("trav");
				}};
		List<Variable> evidenceVarsD1c = BayesNet.MakeVariables(varNamesD1c);
		evidenceVarsD1c.get(0).setValue(true);
		evidenceVarsD1c.get(1).setValue(true);
		evidenceVarsD1c.get(2).setValue(false);
		
		// info for EU(Block | FP, ~IP, CRP)
//		 query variables
		List<String> varNames3b = new ArrayList<String>()
			{{
				add("block");
			}};
		List<Variable> queryVars3b = BayesNet.MakeVariables(varNames3b);
		queryVars3b.get(0).setValue(true);
		
		// hidden variables
		varNames3b = new ArrayList<String>()
				{{
					add("trav");
					add("fraud");
					add("oc");
				}};
		List<Variable> orderedHiddenVars3b = BayesNet.MakeVariables(varNames3b);
		
		// evidence
		varNames3b = new ArrayList<String>()
				{{
					add("fp");
					add("ip");
					add("crp");
				}};
		List<Variable> evidenceVars3b = BayesNet.MakeVariables(varNames3b);
		evidenceVars3b.get(0).setValue(true);
		evidenceVars3b.get(1).setValue(false);
		evidenceVars3b.get(2).setValue(true);
		
		// info for EU(Block | FP, ~IP, CRP, TRAV)
//		 query variables
		List<String> varNames3c = new ArrayList<String>()
			{{
				add("block");
			}};
		List<Variable> queryVars3c = BayesNet.MakeVariables(varNames3c);
		queryVars3c.get(0).setValue(true);
		
		// hidden variables
		varNames3c = new ArrayList<String>()
				{{
					add("fraud");
					add("oc");
				}};
		List<Variable> orderedHiddenVars3c = BayesNet.MakeVariables(varNames3c);
		
		// evidence
		varNames3c = new ArrayList<String>()
				{{
					add("fp");
					add("ip");
					add("crp");
					add("trav");
				}};
		List<Variable> evidenceVars3c = BayesNet.MakeVariables(varNames3c);
		evidenceVars3c.get(0).setValue(true);
		evidenceVars3c.get(1).setValue(false);
		evidenceVars3c.get(2).setValue(true);
		evidenceVars3c.get(3).setValue(false);
		

//		System.out.println("=====================================");
//		System.out.println("-------------- P(Fraud) -------------");
//		System.out.println("=====================================");
//		Factor.Inference(factList, queryVars1, orderedHiddenVars1, evidenceVars1);
		
//		System.out.println("=====================================");
//		System.out.println("------ P(Fraud | FP, ~IP, CRP) ------");
//		System.out.println("=====================================");
//		Factor.Inference(factList, queryVars, orderedHiddenVars, evidenceVars);
//		
//		System.out.println("=====================================");
//		System.out.println("--- P(Fraud | FP, ~IP, CRP, TRAV) ---");
//		System.out.println("=====================================");
//		Factor.Inference(factList, queryVarsC, orderedHiddenVarsC, evidenceVarsC);
//		
//		System.out.println("=====================================");
//		System.out.println("------------ P(Fraud | IP) ----------");
//		System.out.println("=====================================");
//		Factor.Inference(factList, queryVarsD1, orderedHiddenVarsD1, evidenceVarsD1);
//		
//		System.out.println("=====================================");
//		System.out.println("--------- P(Fraud | IP, CRP) --------");
//		System.out.println("=====================================");
//		Factor.Inference(factList, queryVarsD1b, orderedHiddenVarsD1b, evidenceVarsD1b);
//		
//		System.out.println("=====================================");
//		System.out.println("----- P(Fraud | IP, CRP, ~TRAV) -----");
//		System.out.println("=====================================");
//		Factor.Inference(factList, queryVarsD1c, orderedHiddenVarsD1c, evidenceVarsD1c);
		
//		System.out.println("=====================================");
//		System.out.println("----- EU(BLOCK | FP, ~IP, CRP) -----");
//		System.out.println("=====================================");
//		Factor.Inference(factList, queryVars3b, orderedHiddenVars3b, evidenceVars3b);
		
		
		System.out.println("=====================================");
		System.out.println("-- EU(BLOCK | FP, ~IP, CRP, ~TRAV) ---");
		System.out.println("=====================================");
		Factor.Inference(factList, queryVars3c, orderedHiddenVars3c, evidenceVars3c);

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

}
