package individualSchwefel;

import individual.Individual;
import individual.IndividualFactory;

public class IndividualSchwefelFactory implements IndividualFactory {
    private int dimension;

    public IndividualSchwefelFactory(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public Individual getIndividual() {
        return new IndividualSchwefel(dimension);
    }

}