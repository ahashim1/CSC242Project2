package pl.prover;

import pl.core.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BasicModelChecking implements Prover {


    public boolean entails(KB kb, Sentence alpha){

        ArrayList<Symbol> symbols = new ArrayList(kb.symbols());

        // Remove Duplicates
        Set<Symbol> hs = new HashSet<>();
        hs.addAll(symbols);
        symbols.clear();
        symbols.addAll(hs);


        return truthTableCheckAll(kb, alpha, symbols, new PLModel());
    }

    public boolean truthTableCheckAll(KB kb, Sentence alpha, ArrayList<Symbol> symbols, PLModel model){
        if (symbols.isEmpty()){
            if (isPLTrue(kb, model)){
                return isPLTrue(alpha, model);
            }else {
                return true;
            }
        }else{

            Symbol P = symbols.get(0);
            symbols = new ArrayList<>(symbols.subList(1, symbols.size()));


            PLModel copyModel1 = model.copy();
            PLModel copyModel2 = model.copy();

            copyModel1.set(P, true);
            copyModel2.set(P, false);


            return truthTableCheckAll(kb, alpha, symbols, copyModel1) && truthTableCheckAll(kb, alpha, symbols, copyModel2);
        }
    }

    public boolean isPLTrue(KB kb, PLModel model){
        return model.satisfies(kb);
    }

    public boolean isPLTrue(Sentence alpha, PLModel model){
        return model.satisfies(alpha);
    }


}
