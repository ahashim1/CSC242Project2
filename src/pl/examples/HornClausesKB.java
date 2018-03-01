package pl.examples;


import pl.core.*;
import pl.prover.*;
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


//         mythical

        Symbol mythical = kb.intern("Mythical");
        s = mythical;
        BasicModelChecking bmc = new BasicModelChecking();
        if (bmc.entails(kb, s)){
            System.out.println("The unicorn is mythical");
        }else{
            System.out.println("We don't know if the unicorn is mythical or not");
        }

        // magical
        Symbol magical = kb.intern("Magical");
        s = magical;
        if (bmc.entails(kb, s)){
            System.out.println("The unicorn is magical");
        }else{
            System.out.println("We can't prove that the unicorn is magical");
        }


        // horned
        Symbol horned = kb.intern("Horned");
        s = horned;
        if (bmc.entails(kb, s)){
            System.out.println("The unicorn is horned");
        }else{
            System.out.println("We don't know if the unicorn is horned or not");
        }


        WalkSAT wSAT = new WalkSAT();
        kb.add(mythical);

        Model model = wSAT.solve(kb);
        if (model != null){
            System.out.println("The unicorn can be mythical in the following case:");

            model.dump();
        }else{

            System.out.println("Greater than max_flips, could not find solution where the unicorn is mythical");
        }


        kb = new HornClausesKB();
        kb.add(magical);
        model = wSAT.solve(kb);
        if (model != null){
            System.out.println("The unicorn can be magical in the following model:");

            model.dump();
        }else{

            System.out.println("Greater than max_flips, could not find solution where the unicorn is magical");
        }

        kb = new HornClausesKB();
        kb.add(horned);
        model = wSAT.solve(kb);
        if (model != null){
            System.out.println("The unicorn can be horned in the following model:");

            model.dump();
        }else{

            System.out.println("Greater than max_flips, could not find solution where the unicorn is horned");
        }

    }
}
