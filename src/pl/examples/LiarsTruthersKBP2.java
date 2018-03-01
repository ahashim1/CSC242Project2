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


    private static void solveLiarsTruthers(){

        LiarsTruthersKBP2 kb = new LiarsTruthersKBP2();
        WalkSAT wSAT = new WalkSAT();
        Symbol amy = kb.intern("Amy");
        Symbol bob = kb.intern("Bob");
        Symbol cal = kb.intern("Cal");

        // Test amy truther
        kb = new LiarsTruthersKBP2();
        kb.add(amy);
        Model model = wSAT.solve(kb);
        if (model != null){
            System.out.println("Found a solution where Amy is a truther: ");

            model.dump();
        }else{

            System.out.println("Greater than max_flips, could not find solution where the amy is a truther");
        }

        // test amy liar
        kb = new LiarsTruthersKBP2();
        kb.add(new Negation(amy));
        model = wSAT.solve(kb);
        if (model != null){
            System.out.println("Found a solution where Amy is a liar: ");

            model.dump();
        }else{

            System.out.println("Greater than max_flips, could not find solution where the amy is a liar");
        }


        // Test bob truther
        kb = new LiarsTruthersKBP2();
        kb.add(bob);
        model = wSAT.solve(kb);
        if (model != null){
            System.out.println("Found a solution where Bob is a truther: ");

            model.dump();
        }else{

            System.out.println("Greater than max_flips, could not find solution where the Bob is a truther");
        }

        // test bob liar
        kb = new LiarsTruthersKBP2();
        kb.add(new Negation(bob));
        model = wSAT.solve(kb);
        if (model != null){
            System.out.println("Found a solution where Bob is a liar: ");

            model.dump();
        }else{

            System.out.println("Greater than max_flips, could not find solution where the Bob is a liar");
        }


        // Test cal truther
        kb = new LiarsTruthersKBP2();
        kb.add(cal);
        model = wSAT.solve(kb);
        if (model != null){
            System.out.println("Found a solution where Cal is a truther: ");

            model.dump();
        }else{

            System.out.println("Greater than max_flips, could not find solution where the Cal is a truther");
        }

        // test cal liar
        kb = new LiarsTruthersKBP2();
        kb.add(new Negation(cal));
        model = wSAT.solve(kb);
        if (model != null){
            System.out.println("Found a solution where Cal is a liar: ");

            model.dump();
        }else{

            System.out.println("Greater than max_flips, could not find solution where the Cal is a liar");
        }
    }

    public static void main(String[] args) {
        LiarsTruthersKBP2 kb = new LiarsTruthersKBP2();
        checkLiarsTruthers(kb);
        solveLiarsTruthers();
    }
}
