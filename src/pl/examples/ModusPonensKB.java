package pl.examples;

import pl.core.*;
import pl.prover.BasicModelChecking;

public class ModusPonensKB extends KB {

	static Symbol test;
	public ModusPonensKB() {
		super();

		Symbol p = intern("P");
		Symbol q = intern("Q");
		add(p);
		add(new Implication(p, q));
		test = p;
	}
	
	public static void main(String[] argv) {

//		BasicModelChecking tt = new BasicModelChecking();
//		KB kb = new ModusPonensKB();
//		tt.entails(kb, test);
		new ModusPonensKB().dump();
	}

}
