package pl.prover;

import pl.core.KB;
import pl.core.Model;
import pl.core.PLModel;
import pl.cnf.*;
import java.util.Set;

public class WalkSAT implements Solver {

    public Model solve(KB kb){
        return new PLModel();
    }


    public Model walkSAT(Set<Clause> clauses, float p, int max_flips){
        PLModel model = new PLModel();
        for (int i = 0; i<max_flips; i++){
            if (model.satisfies(clauses)) return model;

            Clause clause = randomly selected clause from clauses that is false;
            with probability p flip value in model of a randomly selected symbol from clause;
            else flip whichever symbol in clause maximizes number of satisfied clauses




        }

        return null;
    }
}
