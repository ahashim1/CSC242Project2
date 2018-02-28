package pl.examples;


import pl.core.*;
import pl.prover.BasicModelChecking;

public class LiarsTruthersKB extends KB {

    public LiarsTruthersKB() {
        Symbol amy = intern("Amy is a truther.");
        Symbol bob = intern("Bob is a truther.");
        Symbol cal = intern("Cal is a truther.");

        add(new Biconditional(amy, new Conjunction(amy, cal)));
        add(new Biconditional(bob, new Negation(cal)));
        add(new Biconditional(cal, new Disjunction(amy, bob)));
    }

    public static void main(String[] args) {
        LiarsTruthersKB kb = new LiarsTruthersKB();
        Symbol s;

        BasicModelChecking bmc = new BasicModelChecking();

        Symbol amy = kb.intern("Amy is a truther.");
        s = amy;

        if (bmc.entails(kb, s)) {
            System.out.println("Amy is a truther");
        } else {
            System.out.println("We do not know if Amy is a liar or truther.");
        }

        Symbol bob = kb.intern("Bob is a truther.");
        s = bob;
        if (bmc.entails(kb, s)) {
            System.out.println("Bob is a truther.");
        } else {
            System.out.println("We do not know if Bob is a liar or truther.");
        }

        Symbol cal = kb.intern("Cal is a truther.");
        s = cal;
        if (bmc.entails(kb, s)) {
            System.out.println("Cal is a truther.");
        } else {
            System.out.println("We do not know if Cal is a liar or truther.");
        }
    }
}
