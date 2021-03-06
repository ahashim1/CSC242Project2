package pl.examples;

import pl.core.*;
import pl.prover.BasicModelChecking;
import pl.prover.WalkSAT;

public class WumpusWorldKB extends KB {
	
	public WumpusWorldKB() {
		super();
		// Initializes symbols
		Symbol p11 = intern("P1,1");
		Symbol p12 = intern("P1,2");
		Symbol p21 = intern("P2,1");
		Symbol p22 = intern("P2,2");
		Symbol p31 = intern("P3,1");
		Symbol b11 = intern("B1,1");
		Symbol b21 = intern("B2,1");

		// Adds sentences to the knowledge base
		add(new Negation(p11));
		add(new Biconditional(b11, new Disjunction(p12, p21)));
		add(new Biconditional(b21, new Disjunction(p12, new Disjunction(p22, p31))));
		add(new Negation(b11));
		add(b21);
	}

	public static void main(String[] args) {
		WumpusWorldKB kb = new WumpusWorldKB();

		// Use pit at 1, 2 as the query in the entailment check
		Symbol p12 = kb.intern("P1,2");
		Sentence s = p12;

		// Do basic model checking using truth-table enumeration.
		BasicModelChecking bmc = new BasicModelChecking();
		System.out.println("Basic Model Checking Result:");
		if (bmc.entails(kb, s)){
			System.out.println("It is proven that there is a pit at location 1, 2");
		}else{
			System.out.println("Cannot prove that there is a pit at location 1, 2");
		}

		// WalkSAT result
		System.out.println();
		System.out.println("WalkSAT Algorithm Result:");
		kb.add(new Symbol("P1,2"));
		WalkSAT wSAT = new WalkSAT();
		Model model = wSAT.solve(kb);
		if (model != null){
			System.out.println("Found a solution where there is a pit at 1, 2: ");

			model.dump();
		}else{

			System.out.println("Greater than max_flips, could not determine find a solution where the pit is at 1, 2");
		}


	}

}
