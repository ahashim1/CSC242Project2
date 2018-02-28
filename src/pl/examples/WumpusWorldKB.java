package pl.examples;

import pl.core.*;
import pl.prover.BasicModelChecking;
import pl.prover.WalkSAT;

public class WumpusWorldKB extends KB {
	
	public WumpusWorldKB() {
		super();
		Symbol p11 = intern("P1,1");
		Symbol p12 = intern("P1,2");
		Symbol p21 = intern("P2,1");
		Symbol p22 = intern("P2,2");
		Symbol p31 = intern("P3,1");
		Symbol b11 = intern("B1,1");
		Symbol b21 = intern("B2,1");

		add(new Negation(p11));
		add(new Biconditional(b11, new Disjunction(p12, p21)));
		add(new Biconditional(b21, new Disjunction(p12, new Disjunction(p22, p31))));
		add(new Negation(b11));
		add(b21);
	}

	public static void main(String[] args) {
		WumpusWorldKB kb = new WumpusWorldKB();

		Symbol p12 = kb.intern("P1,2");

		Sentence s = p12;

		BasicModelChecking bmc = new BasicModelChecking();

		if (bmc.entails(kb, s)){
			System.out.println("There is a pit at location 1, 2");
		}else{
			System.out.println("There is not a pit at location 1, 2");
		}

		WalkSAT wSAT = new WalkSAT();

		wSAT.solve(kb).dump();
	}

}
