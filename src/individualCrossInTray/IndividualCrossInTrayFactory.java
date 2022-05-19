package individualCrossInTray;

import individual.Individual;
import individual.IndividualFactory;

public class IndividualCrossInTrayFactory implements IndividualFactory {
    private int dimension;

    public IndividualCrossInTrayFactory(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public Individual getIndividual() {
        return new IndividualCrossInTray(dimension);
    }

}