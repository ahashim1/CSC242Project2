package pl.core;

import pl.cnf.CNFConverter;

import java.util.HashMap;

public class PLModel implements Model {

    HashMap<Symbol, Boolean> truthTable;

    public PLModel(){
        truthTable = new HashMap<>();
    }

    public PLModel(PLModel otherModel){
        truthTable = otherModel.truthTable;
    }
    /**
     * Set the value assigned to the given PropositionSymbol in this
     * Model to the given boolean VALUE.
     */
    public void set(Symbol sym, boolean value){
        truthTable.put(sym, value);
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

        return true;
    }

    /**
     * Return true if this Model satisfies (makes true) the given Sentence.
     */
    public boolean satisfies(Sentence sentence){
        CNFConverter.convert(sentence);
        return true;
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
