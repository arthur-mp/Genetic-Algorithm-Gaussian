package individualPerm;

import individual.Individual;
import individual.IndividualFactory;

public class IndividualPermFactory implements IndividualFactory {
    private int dimension;

    public IndividualPermFactory(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public Individual getIndividual() {
        return new IndividualPerm(dimension);
    }

}