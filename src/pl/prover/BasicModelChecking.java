package pl.prover;

import pl.core.*;


import java.util.ArrayList;

public class BasicModelChecking implements Prover {


    public boolean entails(KB kb, Sentence alpha){

        ArrayList<Symbol> symbols = new ArrayList(kb.symbols());


        return truthTableCheckAll(kb, alpha, symbols, );
    }

    public boolean truthTableCheckAll(KB kb, Sentence alpha, ArrayList<Symbol> symbols, Model model){
        if (symbols.isEmpty()){
            if (isPLTrue(kb, model)){
                return isPLTrue(alpha, model);
            }else {
                return true;
            }
        }else{
            Symbol P = symbols.get(0);
            symbols.remove(0);
            return truthTableCheckAll(kb, alpha, symbols, model.set(P, true));
        }
    }

    public boolean isPLTrue(KB kb, Model model){
        return model.satisfies(kb);
    }

    public boolean isPLTrue(Sentence alpha, Model model){
        return model.satisfies(alpha);
    }


}
