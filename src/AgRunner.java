
import ag.Ag;
import individual.Individual;
import individual.IndividualFactory;
import individualPerm.IndividualPermFactory;
import individualCrossInTray.IndividualCrossInTrayFactory;
import individualSchwefel.IndividualSchwefelFactory;

public class AgRunner {
    public static void main(String[] args) throws Exception {
        int nPop = 20;
        int dimension = 2;
        int nGer = 10000;
        int nElite = 4;
        Double shift;

        /*
         *
         * Function Perm
         *
         */

        shift = 0.0;

        IndividualFactory individualFactory = new IndividualPermFactory(dimension);

        Ag ag = new Ag();

        Individual individual = ag.run(nPop, individualFactory, nElite, nGer, shift);

        /*
         *
         * Function Cross In Tray
         *
         */

        // shift = -2.1;

        // IndividualFactory individualFactory = new
        // IndividualCrossInTrayFactory(dimension);

        // Ag ag = new Ag();

        // Individual individual = ag.run(nPop, individualFactory, nElite, nGer, shift);

        /*
         *
         * Function Schwefel
         *
         */

        // shift = 0.0;

        // IndividualFactory individualFactory = new
        // IndividualSchwefelFactory(dimension);

        // Ag ag = new Ag();

        // Individual individual = ag.run(nPop, individualFactory, nElite, nGer, shift);

    }

}
