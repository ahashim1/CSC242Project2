package pl.driver;

import pl.examples.*;

public class Driver {
        final static String[] possibleArgs = {"Modus Ponens", "Wumpus World", "Horn Clauses", "Liars Truthers A"
                , "Liars Truthers B", "More Liars Truthers"};

        public static void main(String[] args) {
            if (args.length == 0){
                for (String s: possibleArgs){
                    String[] arguments = new String[1];
                    arguments[0] = s;
                    main(arguments);
                    System.out.println();
                }

            }else if (args.length == 1) {
                if (args[0] == possibleArgs[0]){
                    System.out.println("***** Modus Ponens Problem *****");
                    ModusPonensKB kb = new ModusPonensKB();
                    kb.main(null);
                }else if (args[0] == possibleArgs[1]){
                    System.out.println("***** Wumpus World Problem *****");
                    WumpusWorldKB kb = new WumpusWorldKB();
                    kb.main(null);
                }else if (args[0] == possibleArgs[2]){
                    System.out.println("***** Horn Clauses Problem *****");
                    HornClausesKB kb = new HornClausesKB();
                    kb.main(null);
                }else if (args[0] == possibleArgs[3]){
                    System.out.println("***** Liars Truthers Part A Problem *****");

                    LiarsTruthersKB kb = new LiarsTruthersKB();
                    kb.main(null);
                }else if (args[0] == possibleArgs[4]){
                    System.out.println("***** Liars Truthers Part B Problem *****");

                    LiarsTruthersKBP2 kb = new LiarsTruthersKBP2();
                    kb.main(null);
                }else if (args[0] == possibleArgs[5]){
                    System.out.println("***** More Liars Truthers Problem *****");

                    MoreLiarsTruthersKB kb = new MoreLiarsTruthersKB();
                    kb.main(null);
                }else{
                    System.err.println("Invalid argument");
                }

            }else{
                System.err.println("Too many arguments");
            }
        }

}
