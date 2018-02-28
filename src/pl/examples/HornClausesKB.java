package pl.examples;


import pl.core.*;
import pl.prover.BasicModelChecking;

public class HornClausesKB extends KB {

    public HornClausesKB() {
        Symbol mythical = intern("Mythical");
        Symbol immortal = intern("Immortal");
        Symbol mammal = intern("Mammal");
        Symbol horned = intern("Horned");
        Symbol magical = intern("Magical");

        // if mythical then immortal
        add(new Implication(mythical, immortal));
        // if not mythical then mortal and mammal
        add(new Implication(new Negation(mythical), new Conjunction(new Negation(immortal), mammal)));
        // if immortal or mammal then horned
        add(new Implication(new Disjunction(immortal, mammal), horned));
        // if horned then magical
        add(new Implication(horned, magical));
    }

    public static void main(String[] args) {
        HornClausesKB kb = new HornClausesKB();
        Sentence s;
        Symbol mythical = kb.intern("Mythical");
        s = mythical;
        BasicModelChecking bmc = new BasicModelChecking();
        if (bmc.entails(kb, s)){
            System.out.println("The unicorn is mythical");
        }else{
            System.out.println("We don't know if the unicorn is mythical or not");
        }


        Symbol magical = kb.intern("Magical");
        s = magical;
        if (bmc.entails(kb, s)){
            System.out.println("The unicorn is magical");
        }else{
            System.out.println("We don't know if the unicorn is magical or not");
        }


        Symbol horned = kb.intern("Horned");
        s = horned;
        if (bmc.entails(kb, s)){
            System.out.println("The unicorn is horned");
        }else{
            System.out.println("We don't know if the unicorn is horned or not");
        }

        // mythical
        // magical
        // horned
    }
}
