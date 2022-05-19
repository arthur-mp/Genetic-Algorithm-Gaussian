
import ag.Ag;
import individual.Individual;
import individual.IndividualFactory;
//import individualPerm.IndividualPermFactory;
import individualSchwefel.IndividualSchwefelFactory;

public class AgRunner {
    public static void main(String[] args) throws Exception {
        int nPop = 20;
        int dimension = 2;
        int nGer = 2000;
        int nElite = 4;
        Double shift;

        /*
         * IndividualFactory individualFactory = new IndividualPermFactory(dimension);
         * 
         * Ag ag = new Ag();
         * 
         * Individual individual = ag.run(nPop, individualFactory, nElite, nGer);
         */

        /*
         *
         * Function Schwefel
         *
         */
        shift = 0.0;

        IndividualFactory individualFactory = new IndividualSchwefelFactory(dimension);

        Ag ag = new Ag();

        Individual individual = ag.run(nPop, individualFactory, nElite, nGer, shift);

    }

}
