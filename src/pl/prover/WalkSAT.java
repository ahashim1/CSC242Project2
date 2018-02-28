package pl.prover;



import pl.core.*;
import pl.cnf.*;
import java.util.Set;
import java.util.Random;

public class WalkSAT implements Solver {

    public Model solve(KB kb){

        Set<Clause> clauses = CNFConverter.convert(kb);

        return walkSAT(clauses, 0.5, 1000);
    }


    public Model walkSAT(Set<Clause> clauses, double p, int max_flips){

        PLModel model = new PLModel();
        for (int i = 0; i<max_flips; i++){

            if (areClausesSatisfied(model, clauses)) return model;

            Clause clause = randomlySelectFalseClause(clauses, model);

            Random rand = new Random();
            if (rand.nextInt() > p){
                Symbol s = getRandomSymbolFromClause(clause);
                flipSymbol(model, s);
            }else{
                flipSymbolToMaximizeSatisfiedClauses(clauses, clause, model);
            }
        }

        return null;
    }

    public void flipSymbolToMaximizeSatisfiedClauses(Set<Clause> clauses, Clause randomClause, PLModel model){
        int bestIndex = 0;
        int maxSatisfiedClauses = 0;


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

    public int satisfiedClausesCount(Set<Clause> clauses, PLModel model){
        int count = 0;
        for (Clause clause: clauses){
            if (clause.isSatisfiedBy(model));{
                count++;
            }
        }

        return count;
    }

    public void flipSymbol(PLModel model, Symbol s){
        Boolean value = model.get(s);
        model.set(s, !value);
    }

    public Symbol getRandomSymbolFromClause(Clause clause){
        Random rand = new Random();
        int randomIndex = rand.nextInt(clause.size());
        return clause.get(randomIndex).getContent();
    }
    public Clause randomlySelectFalseClause(Set<Clause> clauses, PLModel model){
        Random rand = new Random();
        int randomIndex = rand.nextInt(clauses.size());
        int index = 0;

        Clause randomClause = null;
        for (Clause clause: clauses){
            if (index == randomIndex){
                randomClause = clause;
                break;
            }
        }

        if (randomClause.isSatisfiedBy(model)){
            return randomlySelectFalseClause(clauses, model);
        }else{
            return randomClause;
        }

    }

    public boolean areClausesSatisfied(PLModel model, Set<Clause> clauses){
        for (Clause clause: clauses){
            if (!clause.isSatisfiedBy(model)){
                return false;
            }
        }

        return true;
    }
}
