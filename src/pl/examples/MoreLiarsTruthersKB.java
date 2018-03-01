package pl.examples;


import pl.core.*;
import pl.prover.BasicModelChecking;
import pl.prover.WalkSAT;

public class MoreLiarsTruthersKB extends KB {

    private MoreLiarsTruthersKB() {
        //  Initialize all the people as symbols
        Symbol amy = intern("Amy");
        Symbol bob = intern("Bob");
        Symbol cal = intern("Cal");
        Symbol dee = intern("Dee");
        Symbol eli = intern("Eli");
        Symbol fay = intern("Fay");
        Symbol gil = intern("Gil");
        Symbol hal = intern("Hal");
        Symbol ida = intern("Ida");
        Symbol jay = intern("Jay");
        Symbol kay = intern("Kay");
        Symbol lee = intern("Lee");

        //  The biconditional list
        //  This took forever
        add(new Biconditional(amy, new Conjunction(hal, ida)));
        add(new Biconditional(bob, new Conjunction(amy, lee)));
        add(new Biconditional(cal, new Conjunction(bob, gil)));
        add(new Biconditional(dee, new Conjunction(eli, lee)));
        add(new Biconditional(eli, new Conjunction(cal, hal)));
        add(new Biconditional(fay, new Conjunction(dee, ida)));
        add(new Biconditional(gil, new Conjunction(new Negation(eli), new
                Negation(jay))));
        add(new Biconditional(hal, new Conjunction(new Negation(fay), new
                Negation(kay))));
        add(new Biconditional(ida, new Conjunction(new Negation(gil), new
                Negation(kay))));
        add(new Biconditional(jay, new Conjunction(new Negation(amy), new
                Negation(cal))));
        add(new Biconditional(kay, new Conjunction(new Negation(dee), new
                Negation(fay))));
        add(new Biconditional(lee, new Conjunction(new Negation(bob), new
                Negation(jay))));
    }

    private static void runBMC(MoreLiarsTruthersKB kb, Symbol toCheck,
                                 BasicModelChecking bmc) {
        if (bmc.entails(kb, toCheck)) {
            System.out.println(toCheck.toString() + " is a truther.");
        } else if (bmc.entails(kb, new Negation(toCheck))) {
            System.out.println(toCheck.toString() + " is a liar.");
        } else {
            System.out.println("We do not know whether " + toCheck.toString()
                            + " is a liar or truther.");
        }
    }

    private static void checkLiarsTruthers(MoreLiarsTruthersKB kb) {

        BasicModelChecking bmc = new BasicModelChecking();

        //  Testing the checker by evaluating each symbol
        Symbol amy = kb.intern("Amy");
        runBMC(kb, amy, bmc);

        Symbol bob = kb.intern("Bob");
        runBMC(kb, bob, bmc);

        Symbol cal = kb.intern("Cal");
        runBMC(kb, cal, bmc);

        Symbol dee = kb.intern("Dee");
        runBMC(kb, dee, bmc);

        Symbol eli = kb.intern("Eli");
        runBMC(kb, eli, bmc);

        Symbol fay = kb.intern("Fay");
        runBMC(kb, fay, bmc);

        Symbol gil = kb.intern("Gil");
        runBMC(kb, gil, bmc);

        Symbol hal = kb.intern("Hal");
        runBMC(kb, hal, bmc);

        Symbol ida = kb.intern("Ida");
        runBMC(kb, ida, bmc);

        Symbol jay = kb.intern("Jay");
        runBMC(kb, jay, bmc);

        Symbol kay = kb.intern("Kay");
        runBMC(kb, kay, bmc);

        Symbol lee = kb.intern("Lee");
        runBMC(kb, lee, bmc);
    }

    private static void solveLTSymbol(Symbol toCheck, WalkSAT wSAT) {
        MoreLiarsTruthersKB kb = new MoreLiarsTruthersKB();
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
        kb = new MoreLiarsTruthersKB();
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

    private static void solveLiarsTruthers() {
        MoreLiarsTruthersKB kb = new MoreLiarsTruthersKB();
        WalkSAT wSAT = new WalkSAT();
        Symbol amy = kb.intern("Amy");
        Symbol bob = kb.intern("Bob");
        Symbol cal = kb.intern("Cal");
        Symbol dee = kb.intern("Dee");
        Symbol eli = kb.intern("Eli");
        Symbol fay = kb.intern("Fay");
        Symbol gil = kb.intern("Gil");
        Symbol hal = kb.intern("Hal");
        Symbol ida = kb.intern("Ida");
        Symbol jay = kb.intern("Jay");
        Symbol kay = kb.intern("Kay");
        Symbol lee = kb.intern("Lee");

        solveLTSymbol(amy, wSAT);

        solveLTSymbol(bob, wSAT);

        solveLTSymbol(cal, wSAT);

        solveLTSymbol(dee, wSAT);

        solveLTSymbol(eli, wSAT);

        solveLTSymbol(fay, wSAT);

        solveLTSymbol(gil, wSAT);

        solveLTSymbol(hal, wSAT);

        solveLTSymbol(ida, wSAT);

        solveLTSymbol(jay, wSAT);

        solveLTSymbol(kay, wSAT);

        solveLTSymbol(lee, wSAT);
    }

    public static void main(String[] args) {
        MoreLiarsTruthersKB kb = new MoreLiarsTruthersKB();

        //  Check using basic model checking
        checkLiarsTruthers(kb);

        //  Check using walkSAT
        solveLiarsTruthers();
    }
}
