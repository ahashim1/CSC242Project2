package pl.examples;

import pl.core.*;
import pl.prover.BasicModelChecking;

public class ModusPonensKB extends KB {

	public ModusPonensKB() {
		super();

		Symbol p = intern("P");
		Symbol q = intern("Q");
		add(p);
		add(new Implication(p, q));
	}
	
	public static void main(String[] argv) {

		ModusPonensKB kb = new ModusPonensKB();
		Symbol q = kb.intern("Q");
		Sentence s = q;

		BasicModelChecking bmc = new BasicModelChecking();
		if (bmc.entails(kb, s)){
			System.out.println("Modus Ponens Maintains Entailment");
		}else{
			System.out.println("Modus Ponens Maintains Entailment");
		}

//		new ModusPonensKB().dump();

	}

}
