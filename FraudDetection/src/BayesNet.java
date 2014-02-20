
public class BayesNet {

	public static void main(String[] args) {
		Factor f = new Factor();
		Variable A = new Variable();
		Variable B = new Variable();
		Variable C = new Variable();
		
		A.setDesc("a"); B.setDesc("b"); C.setDesc("C");
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

}
