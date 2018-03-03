package pl.prover;

import pl.core.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BasicModelChecking implements Prover {


    // Checks if the query is entailed by the knowledge base
    public boolean entails(KB kb, Sentence alpha){

        ArrayList<Symbol> symbols = new ArrayList<>(kb.symbols());
        return truthTableCheckAll(kb, alpha, symbols, new PLModel());
    }

    // Truth Table Enumeration Method
    private boolean truthTableCheckAll(KB kb, Sentence alpha, ArrayList<Symbol> symbols, PLModel model){
        //Base case, if empty, and if the model satisfies the knowledge base, return if it also satisfies the query.
        // Otherwise, return true when the kb is always false.
        if (symbols.isEmpty()){
            if (isPLTrue(kb, model)){
                return isPLTrue(alpha, model);
            }else {
                return true;
            }
        }else{

            // Get the first symbol
            Symbol P = symbols.get(0);
            symbols = new ArrayList<Symbol>(symbols.subList(1, symbols.size()));

            // Make a copy of the model
            PLModel copyModel1 = model.copy();
            PLModel copyModel2 = model.copy();

            // Assign one copy to true and the other to false
            copyModel1.set(P, true);
            copyModel2.set(P, false);

            // Check the truth table again recursively. (Builds the truth table while iterating through each symbol)
            return truthTableCheckAll(kb, alpha, symbols, copyModel1) && truthTableCheckAll(kb, alpha, symbols, copyModel2);
        }
    }


    // Checks if the propisitional logic knowledge base or query are satisfied by the model.
    private boolean isPLTrue(KB kb, PLModel model){
        return model.satisfies(kb);
    }
    private boolean isPLTrue(Sentence alpha, PLModel model){
        return model.satisfies(alpha);
    }


}
