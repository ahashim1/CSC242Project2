package pl.prover;



import pl.core.*;
import pl.cnf.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.Random;

public class WalkSAT implements Solver {

    Random rand = new Random();
    public Model solve(KB kb){

        // Converts kb to clauses
        Set<Clause> clauses = CNFConverter.convert(kb);

        // Sets p to 0.5 and max_flips to 100000 for WalkSAT
        return walkSAT(clauses, 0.5, 100000);
    }

    // WalkSAT algorithm
    private Model walkSAT(Set<Clause> clauses, double p, int max_flips){

//        assigns a random model from clauses
        PLModel model = getRandomlyAssignedModel(clauses);

// Iterates until max_flips
        for (int i = 0; i < max_flips; i++){

            // If all the clauses are satisfied, you have found a solution!
            if (areClausesSatisfied(model, clauses)) return model;

            // Randomly selects a false clause
            Clause clause = randomlySelectFalseClause(clauses, model);

            // With probability p, flip a random symbol from clause (to avoid local minima)
            if (p > rand.nextDouble()){
                Symbol s = getRandomSymbolFromClause(clause);
                flipSymbol(model, s);
            // Otherwise flip the symbol that maximizes the number of satisfied clauses.
            }else{
                flipSymbolToMaximizeSatisfiedClauses(clauses, clause, model);
            }


        }

        return null;
    }

    // Assigns random boolean to each symbol from clauses
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

    // Counts the number of satisfied clauses for each clause after each symbol in the randomClause is flipped
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
            // Flips it back
            flipSymbol(model, s);

        }

        // Flips the best one
        Symbol s = randomClause.get(bestIndex).getContent();
        flipSymbol(model, s);


    }

    // Gets how many clauses are satisfied by the model
    private int satisfiedClausesCount(Set<Clause> clauses, PLModel model){
        int count = 0;
        for (Clause clause: clauses){
            if (clause.isSatisfiedBy(model)){
                count++;
            }
        }

        return count;
    }

    // Flips the symbol in the model
    private void flipSymbol(PLModel model, Symbol s){
        Boolean value = model.get(s);
        model.set(s, !value);
    }

    // Gets a random symbol from clause
    private Symbol getRandomSymbolFromClause(Clause clause){
        int randomIndex = rand.nextInt(clause.size());
        return clause.get(randomIndex).getContent();
    }

    // Returns every false clause in an array list
    private ArrayList<Clause> getFalseClauses(Set<Clause> clauses, PLModel model){
        ArrayList<Clause> falseClauses = new ArrayList<>();
        for (Clause clause: clauses){
            if (!clause.isSatisfiedBy(model)){
                falseClauses.add(clause);
            }
        }

        return falseClauses;

    }

    // Uses the previous two methods
    private Clause randomlySelectFalseClause(Set<Clause> clauses, PLModel model){
        ArrayList<Clause> falseClauses = getFalseClauses(clauses, model);
        return falseClauses.get(rand.nextInt(falseClauses.size()));

    }

    // Checks if all the clauses are satisfied.
    private boolean areClausesSatisfied(PLModel model, Set<Clause> clauses){

        for (Clause clause: clauses){
            if (!clause.isSatisfiedBy(model)){

                return false;
            }
        }

        return true;
    }
}
