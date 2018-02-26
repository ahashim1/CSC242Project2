package pl.prover;

import pl.core.*;


import java.util.ArrayList;

public class BasicModelChecking implements Prover {


    public boolean entails(KB kb, Sentence alpha){

        ArrayList<Symbol> symbols = new ArrayList(kb.symbols());
        PLModel model = new PLModel();
        return truthTableCheckAll(kb, alpha, symbols, model);
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
            symbols.remove(0);

            PLModel copyModel1 = new PLModel(model);
            PLModel copyModel2 = new PLModel(model);

            copyModel1.set(P, true);
            copyModel2.set(P, false);
            return truthTableCheckAll(kb, alpha, symbols, copyModel1) && truthTableCheckAll(kb, alpha, symbols, copyModel2);
        }
    }

    public boolean isPLTrue(KB kb, Model model){
        return model.satisfies(kb);
    }

    public boolean isPLTrue(Sentence alpha, Model model){
        return model.satisfies(alpha);
    }


}
