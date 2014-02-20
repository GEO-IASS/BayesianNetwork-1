
public class BayesNet {

	public static void main(String[] args) {
//		Factor f = new Factor();
		Variable A = new Variable();
		Variable B = new Variable();
		Variable C = new Variable();
		A.setDesc("a"); B.setDesc("b"); C.setDesc("C");
		
//		// test restrict
//		A.setIndex(2); B.setIndex(1); C.setIndex(0);
//		f.getProbabilities().add(new Probability(0.06));
//		f.getProbabilities().add(new Probability(0.24));
//		f.getProbabilities().add(new Probability(0.42));
//		f.getProbabilities().add(new Probability(0.28));
//		f.getProbabilities().add(new Probability(0.18));
//		f.getProbabilities().add(new Probability(0.72));
//		f.getProbabilities().add(new Probability(0.06));
//		f.getProbabilities().add(new Probability(0.04));
//		f.getVariables().add(A); f.getVariables().add(B); f.getVariables().add(C);
		
		// test product
		Factor f1 = new Factor();
		Factor f2 = new Factor();
		Variable B2 = new Variable();
		Variable C2 = new Variable();
		
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

}
