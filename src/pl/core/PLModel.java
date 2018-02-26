package pl.core;

import com.sun.org.apache.xpath.internal.operations.Bool;
import pl.cnf.*;
import java.util.*;
import java.util.HashMap;


public class PLModel implements Model {

    HashMap<Symbol, Boolean> truthTable;

    public PLModel(){
        truthTable = new HashMap<>();
    }

    public PLModel(HashMap<Symbol, Boolean> hmap){
        truthTable = hmap;
    }

    /**
     * Set the value assigned to the given PropositionSymbol in this
     * Model to the given boolean VALUE.
     */
    public void set(Symbol sym, boolean value){
        truthTable.put(sym, value);
    }

    public PLModel copy(){
        HashMap<Symbol, Boolean> hmap = new HashMap<>();
        truthTable.forEach((k, v) -> {
                hmap.put(k, v);
        }
        );

        return new PLModel(hmap);

    }



    /**
     * Returns the boolean value associated with the given PropositionalSymbol
     * in this Model.
     */
    public boolean get(Symbol sym){
        return truthTable.get(sym);
    }

    /**
     * Return true if this Model satisfies (makes true) the given KB.
     */
    public boolean satisfies(KB kb){

        for (Sentence s: kb.sentences){
            if (!s.isSatisfiedBy(this)){
                return false;
            }
        }

        return true;
    }

    /**
     * Return true if this Model satisfies (makes true) the given Sentence.
     */
    public boolean satisfies(Sentence sentence){

        return sentence.isSatisfiedBy(this);
    }

    /**
     * Print the assignments in this Model to System.out.
     */
    public void dump(){
        truthTable.forEach((k, v) -> {
                System.out.println(k + ": " + v);
            }
        );
    }


}
