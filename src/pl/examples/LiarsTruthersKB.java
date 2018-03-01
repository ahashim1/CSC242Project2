package pl.examples;

import pl.core.*;
import pl.prover.BasicModelChecking;
import pl.prover.WalkSAT;

public class LiarsTruthersKB extends KB {

    private LiarsTruthersKB() {
        //  Create the symbols
        Symbol amy = intern("Amy");
        Symbol bob = intern("Bob");
        Symbol cal = intern("Cal");

        //  Set up the conditions
        add(new Biconditional(amy, new Conjunction(amy, cal)));
        add(new Biconditional(bob, new Negation(cal)));
        add(new Biconditional(cal, new Disjunction(new Negation(amy), bob)));
    }

    private static void checkBMC(LiarsTruthersKB kb, Symbol toCheck,
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

    private static void checkLiarsTruthers(LiarsTruthersKB kb) {

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
        LiarsTruthersKB kb = new LiarsTruthersKB();
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
        kb = new LiarsTruthersKB();
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
    }

    private static void solveLiarsTruthers(){

        LiarsTruthersKB kb = new LiarsTruthersKB();
        WalkSAT wSAT = new WalkSAT();
        Symbol amy = kb.intern("Amy");
        Symbol bob = kb.intern("Bob");
        Symbol cal = kb.intern("Cal");

        solveLTSymbol(amy, wSAT);

        solveLTSymbol(bob, wSAT);

        solveLTSymbol(cal, wSAT);
    }

    public static void main(String[] args) {
        LiarsTruthersKB kb = new LiarsTruthersKB();

        //  Checking using basic model checking
        checkLiarsTruthers(kb);

        //  Check using walkSAT
        solveLiarsTruthers();
    }
}
