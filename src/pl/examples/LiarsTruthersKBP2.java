package pl.examples;


import pl.core.*;
import pl.prover.BasicModelChecking;

public class LiarsTruthersKBP2 extends KB {

    private LiarsTruthersKBP2() {
        Symbol amy = intern("Amy is a truther.");
        Symbol bob = intern("Bob is a truther.");
        Symbol cal = intern("Cal is a truther.");

        add(new Biconditional(amy, new Negation(cal)));
        add(new Biconditional(bob, new Conjunction(amy, cal)));
        add(new Biconditional(cal, new Conjunction(amy, cal)));
    }

    private static void checkLiarsTruthers(LiarsTruthersKBP2 kb) {

        BasicModelChecking bmc = new BasicModelChecking();

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
        LiarsTruthersKBP2 kb = new LiarsTruthersKBP2();
        checkLiarsTruthers(kb);
    }
}
