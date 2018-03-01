package pl.prover;



import pl.core.*;
import pl.cnf.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.Random;

public class WalkSAT implements Solver {

    Random rand = new Random();
    public Model solve(KB kb){
        Set<Clause> clauses = CNFConverter.convert(kb);


        return walkSAT(clauses, 0.5, 100000);
    }


    private Model walkSAT(Set<Clause> clauses, double p, int max_flips){

        PLModel model = getRandomlyAssignedModel(clauses);
        for (int i = 0; i < max_flips; i++){

            if (areClausesSatisfied(model, clauses)) return model;

            Clause clause = randomlySelectFalseClause(clauses, model);

            if (p > rand.nextDouble()){
                Symbol s = getRandomSymbolFromClause(clause);
                flipSymbol(model, s);
            }else{
                flipSymbolToMaximizeSatisfiedClauses(clauses, clause, model);
            }


        }

        return null;
    }

    private PLModel getRandomlyAssignedModel(Set<Clause> clauses){
        PLModel model = new PLModel();

        for (Clause clause: clauses){
            for (int i = 0; i<clause.size(); i++){
                Symbol s = clause.get(i).getContent();
                boolean value = rand.nextBoolean();
                model.set(s, value);
            }
        }

        return model;
    }

    private void flipSymbolToMaximizeSatisfiedClauses(Set<Clause> clauses, Clause randomClause, PLModel model){
        int bestIndex = -1;
        int maxSatisfiedClauses = -1;


        for (int i = 0; i<randomClause.size(); i++){
            Symbol s = randomClause.get(i).getContent();
            flipSymbol(model, s);
            int satisfiedClauses = satisfiedClausesCount(clauses, model);
            if (satisfiedClauses > maxSatisfiedClauses){
                maxSatisfiedClauses = satisfiedClauses;
                bestIndex = i;
            }

            flipSymbol(model, s);

        }

        Symbol s = randomClause.get(bestIndex).getContent();
        flipSymbol(model, s);


    }

    private int satisfiedClausesCount(Set<Clause> clauses, PLModel model){
        int count = 0;
        for (Clause clause: clauses){
            if (clause.isSatisfiedBy(model));{
                count++;
            }
        }

        return count;
    }

    private void flipSymbol(PLModel model, Symbol s){
        Boolean value = model.get(s);
        model.set(s, !value);
    }

    private Symbol getRandomSymbolFromClause(Clause clause){
        int randomIndex = rand.nextInt(clause.size());
        return clause.get(randomIndex).getContent();
    }

    private ArrayList<Clause> getFalseClauses(Set<Clause> clauses, PLModel model){
        ArrayList<Clause> falseClauses = new ArrayList<>();
        for (Clause clause: clauses){
            if (!clause.isSatisfiedBy(model)){
                falseClauses.add(clause);
            }
        }

        return falseClauses;

    }
    private Clause randomlySelectFalseClause(Set<Clause> clauses, PLModel model){
        ArrayList<Clause> falseClauses = getFalseClauses(clauses, model);
        return falseClauses.get(rand.nextInt(falseClauses.size()));

    }

    private boolean areClausesSatisfied(PLModel model, Set<Clause> clauses){

        for (Clause clause: clauses){
//            model.dump();
//            System.out.println(clause.toString());
//            System.out.println(clause.isSatisfiedBy(model));
//            System.out.println("*******");
            if (!clause.isSatisfiedBy(model)){

                return false;
            }
        }

        return true;
    }
}
