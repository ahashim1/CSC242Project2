package pl.core;

import java.util.HashMap;


public class PLModel implements Model {

    // Underlying data structure is a hashmap for the truth table
    HashMap<Symbol, Boolean> truthTable;

    public PLModel(){
        truthTable = new HashMap<>();
    }

    public PLModel(HashMap<Symbol, Boolean> hmap){
        truthTable = hmap;
    }


    // Just set value by putting in hashmap
    public void set(Symbol sym, boolean value){
        truthTable.put(sym, value);
    }

    // To clone the model
    public PLModel copy(){
        HashMap<Symbol, Boolean> hmap = new HashMap<>();
        truthTable.forEach((k, v) -> {
                hmap.put(k, v);
        }
        );

        return new PLModel(hmap);

    }



    // Get from hashmap
    public boolean get(Symbol sym){
        return truthTable.get(sym);
    }

    // If every sentence is satisfied then return true
    public boolean satisfies(KB kb){

        for (Sentence s: kb.sentences){
            if (!s.isSatisfiedBy(this)){
                return false;
            }
        }

        return true;
    }

    // if the sentence is satisfied return true
    public boolean satisfies(Sentence sentence){

        return sentence.isSatisfiedBy(this);
    }

    // Iterate through truth table and print key and value pairs (not in order)
    public void dump(){
        truthTable.forEach((k, v) -> {
                System.out.println(k + ": " + v);
            }
        );
    }


}
