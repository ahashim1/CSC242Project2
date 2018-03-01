package pl.examples;

import pl.core.*;
import pl.prover.BasicModelChecking;
import pl.prover.WalkSAT;

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
//		Sentence s = kb.intern("Q");


//		kb.add(s);


		WalkSAT wSAT = new WalkSAT();
		Model model = wSAT.solve(kb);
		if (model != null){
			System.out.println("Found a solution: ");
			model.dump();
		}else{
			System.out.println("Greater than max_flips");
		}

//		BasicModelChecking bmc = new BasicModelChecking();
//
//		if (bmc.entails(kb, s)){
//
//			System.out.println("Modus Ponens Maintains Entailment");
//		}else{
//			System.out.println("Modus Ponens Maintains Entailment");
//		}


//		new ModusPonensKB().dump();

	}

}
