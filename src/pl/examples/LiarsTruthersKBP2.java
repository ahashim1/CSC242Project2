package pl.examples;


import pl.core.*;
import pl.prover.BasicModelChecking;
import pl.prover.WalkSAT;

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


    private static void solveLiarsTruthers(){

        LiarsTruthersKBP2 kb = new LiarsTruthersKBP2();
        WalkSAT wSAT = new WalkSAT();
        Symbol amy = kb.intern("Amy is a truther.");
        Symbol bob = kb.intern("Bob is a truther.");
        Symbol cal = kb.intern("Cal is a truther.");

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
