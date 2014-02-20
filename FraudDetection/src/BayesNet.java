
public class BayesNet {

	public static void main(String[] args) 
	{
		BayesNet.NormalizeTest1();
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
