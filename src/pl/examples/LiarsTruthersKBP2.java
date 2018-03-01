package pl.examples;

import pl.core.*;
import pl.prover.BasicModelChecking;
import pl.prover.WalkSAT;

public class LiarsTruthersKBP2 extends KB {

    private LiarsTruthersKBP2() {
        Symbol amy = intern("Amy");
        Symbol bob = intern("Bob");
        Symbol cal = intern("Cal");

        add(new Biconditional(amy, new Negation(cal)));
        add(new Biconditional(bob, new Conjunction(amy, cal)));
        add(new Biconditional(cal, new Conjunction(amy, cal)));
    }

    private static void checkBMC(LiarsTruthersKBP2 kb, Symbol toCheck,
                                 BasicModelChecking bmc) {
        //  Does the checking for each symbol
        if (bmc.entails(kb, toCheck)) {
            System.out.println(toCheck.toString() + " is a truther.");
        } else if (bmc.entails(kb, new Negation(toCheck))) {
            System.out.println(toCheck.toString() + " is a liar.");
        } else {
            System.out.println("We do not know whether " + toCheck.toString()
                    + " is a liar or truther.");
        }
    }

    private static void checkLiarsTruthers(LiarsTruthersKBP2 kb) {

        BasicModelChecking bmc = new BasicModelChecking();

        //  Test everything
        Symbol amy = kb.intern("Amy");
        checkBMC(kb, amy, bmc);

        Symbol bob = kb.intern("Bob");
        checkBMC(kb, bob, bmc);

        Symbol cal = kb.intern("Cal");
        checkBMC(kb, cal, bmc);
    }

    private static void solveLTSymbol(Symbol toCheck, WalkSAT wSAT) {
        LiarsTruthersKBP2 kb = new LiarsTruthersKBP2();
        kb.add(toCheck);
        Model model = wSAT.solve(kb);

        //  Check if solution for truther exists
        if (model != null) {
            System.out.println("Found solution where " + toCheck.toString() +
                    " is a truther: ");
            model.dump();
        } else {
            System.out.println("Greater than max_flips, could not find " +
                    "solution where " + toCheck.toString() + " is a truther");
        }

        //  Check if solution for liar exists
        kb = new LiarsTruthersKBP2();
        kb.add(new Negation(toCheck));
        model = wSAT.solve(kb);
        if (model != null) {
            System.out.println("Found solution where " + toCheck.toString() +
                    " is a liar: ");
            model.dump();
        } else {
            System.out.println("Greater than max_flips, could not find " +
                    "solution where " + toCheck.toString() + " is a liar.");
        }
        System.out.println();
    }

    private static void solveLiarsTruthers(){

        LiarsTruthersKBP2 kb = new LiarsTruthersKBP2();
        WalkSAT wSAT = new WalkSAT();
        Symbol amy = kb.intern("Amy");
        Symbol bob = kb.intern("Bob");
        Symbol cal = kb.intern("Cal");

        solveLTSymbol(amy, wSAT);

        solveLTSymbol(bob, wSAT);

        solveLTSymbol(cal, wSAT);
    }

    public static void main(String[] args) {
        LiarsTruthersKBP2 kb = new LiarsTruthersKBP2();
        checkLiarsTruthers(kb);
        solveLiarsTruthers();
    }
}
