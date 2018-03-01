package pl.examples;


import pl.core.*;
import pl.prover.BasicModelChecking;

public class MoreLiarsTruthersKB extends KB {

    private MoreLiarsTruthersKB() {
        //  Initialize all the people as symbols
        Symbol amy = intern("Amy is a truther.");
        Symbol bob = intern("Bob is a truther.");
        Symbol cal = intern("Cal is a truther.");
        Symbol dee = intern("Dee is a truther.");
        Symbol eli = intern("Eli is a truther.");
        Symbol fay = intern("Fay is a truther.");
        Symbol gil = intern("Gil is a truther.");
        Symbol hal = intern("Hal is a truther.");
        Symbol ida = intern("Ida is a truther.");
        Symbol jay = intern("Jay is a truther.");
        Symbol kay = intern("Kay is a truther.");
        Symbol lee = intern("Lee is a truther.");

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

    private static void checkLiarsTruthers(MoreLiarsTruthersKB kb) {

        BasicModelChecking bmc = new BasicModelChecking();

        //  Testing the checker by evaluating each symbol
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

        Symbol dee = kb.intern("Dee is a truther.");
        if (bmc.entails(kb, dee)) {
            System.out.println("Dee is a truther.");
        } else if (bmc.entails(kb, new Negation(dee))) {
            System.out.println("Dee is a liar.");
        } else {
            System.out.println("We do not know if Dee is a liar or truther.");
        }

        Symbol eli = kb.intern("Eli is a truther.");
        if (bmc.entails(kb, eli)) {
            System.out.println("Eli is a truther.");
        } else if (bmc.entails(kb, new Negation(eli))) {
            System.out.println("Eli is a liar.");
        } else {
            System.out.println("We do not know if Eli is a liar or truther.");
        }

        Symbol fay = kb.intern("Fay is a truther.");
        if (bmc.entails(kb, fay)) {
            System.out.println("Fay is a truther.");
        } else if (bmc.entails(kb, new Negation(fay))) {
            System.out.println("Fay is a liar.");
        } else {
            System.out.println("We do not know if Fay is a liar or truther.");
        }

        Symbol gil = kb.intern("Gil is a truther.");
        if (bmc.entails(kb, gil)) {
            System.out.println("Gil is a truther.");
        } else if (bmc.entails(kb, new Negation(gil))) {
            System.out.println("Gil is a liar.");
        } else {
            System.out.println("We do not know if Gil is a liar or truther.");
        }

        Symbol hal = kb.intern("Hal is a truther.");
        if (bmc.entails(kb, hal)) {
            System.out.println("Hal is a truther.");
        } else if (bmc.entails(kb, new Negation(hal))) {
            System.out.println("Hal is a liar.");
        } else {
            System.out.println("We do not know if Hal is a liar or truther.");
        }

        Symbol ida = kb.intern("Ida is a truther.");
        if (bmc.entails(kb, ida)) {
            System.out.println("Ida is a truther.");
        } else if (bmc.entails(kb, new Negation(ida))) {
            System.out.println("Ida is a liar.");
        } else {
            System.out.println("We do not know if Ida is a liar or truther.");
        }

        Symbol jay = kb.intern("Jay is a truther.");
        if (bmc.entails(kb, jay)) {
            System.out.println("Jay is a truther.");
        } else if (bmc.entails(kb, new Negation(jay))) {
            System.out.println("Jay is a liar.");
        } else {
            System.out.println("We do not know if Jay is a liar or truther.");
        }

        Symbol kay = kb.intern("Kay is a truther.");
        if (bmc.entails(kb, kay)) {
            System.out.println("Kay is a truther.");
        } else if (bmc.entails(kb, new Negation(kay))) {
            System.out.println("Kay is a liar.");
        } else {
            System.out.println("We do not know if Kay is a liar or truther.");
        }

        Symbol lee = kb.intern("Lee is a truther.");
        if (bmc.entails(kb, lee)) {
            System.out.println("Lee is a truther.");
        } else if (bmc.entails(kb, new Negation(lee))) {
            System.out.println("Lee is a liar.");
        } else {
            System.out.println("We do not know if Lee is a liar or truther.");
        }
    }

    public static void main(String[] args) {
        MoreLiarsTruthersKB kb = new MoreLiarsTruthersKB();
        checkLiarsTruthers(kb);
    }
}
