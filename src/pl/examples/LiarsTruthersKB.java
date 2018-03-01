package pl.examples;


import pl.core.*;
import pl.prover.BasicModelChecking;

public class LiarsTruthersKB extends KB {

    private LiarsTruthersKB() {
        //  Create the symbols
        Symbol amy = intern("Amy is a truther.");
        Symbol bob = intern("Bob is a truther.");
        Symbol cal = intern("Cal is a truther.");

        //  Set up the conditions
        add(new Biconditional(amy, new Conjunction(amy, cal)));
        add(new Biconditional(bob, new Negation(cal)));
        add(new Biconditional(cal, new Disjunction(new Negation(amy), bob)));
    }

    private static void checkLiarsTruthers(LiarsTruthersKB kb) {

        BasicModelChecking bmc = new BasicModelChecking();

        //  Test everything
        Symbol amy = kb.intern("Amy is a truther.");
        if (bmc.entails(kb, amy)) {
            System.out.println("Amy is a truther.");
        } else if (bmc.entails(kb, new Negation(amy))) {
            System.out.println("Amy is a liar.");
        } else {
            System.out.println("We do not know if Amy is a liar or truther.");
        }

        Symbol bob = kb.intern("Bob is a truther.");
        if (bmc.entails(kb, bob)) {
            System.out.println("Bob is a truther.");
        } else if (bmc.entails(kb, new Negation(bob))) {
            System.out.println("Bob is a liar.");
        } else {
            System.out.println("We do not know if Bob is a liar or truther.");
        }

        Symbol cal = kb.intern("Cal is a truther.");
        if (bmc.entails(kb, cal)) {
            System.out.println("Cal is a truther.");
        } else if (bmc.entails(kb, new Negation(cal))) {
            System.out.println("Cal is a liar.");
        } else {
            System.out.println("We do not know if Cal is a liar or truther.");
        }

    }

    public static void main(String[] args) {
        LiarsTruthersKB kb = new LiarsTruthersKB();
        checkLiarsTruthers(kb);
    }
}
