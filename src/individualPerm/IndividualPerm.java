package individualPerm;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import individual.Individual;

public class IndividualPerm extends Individual {

    protected List<Double> dimensions;

    protected IndividualPerm(int dimension) {
        this.dimensions = new ArrayList<>();

        List<Double> dimensionRandom = new ArrayList<>();

        Random random = new Random();

        for (double i = 0; i <= (dimension + dimension); i++) {
            dimensionRandom.add(i);
        }

        for (double i = 0; i < (dimension); i++) {
            int index = random.nextInt(dimensionRandom.size());
            double value = (dimensionRandom.get(index) - dimension);

            if (value < (dimension * -1))
                value = (dimension * -1);
            else if (value > dimension)
                value = dimension;

            this.dimensions.add(value);
            dimensionRandom.remove(index);
        }
    }

    protected IndividualPerm(List<Double> dimension) {
        this.dimensions = dimension;
    }

    @Override
    public Double evaluate() {
        Double evaluation = 0.0;
        int beta = 10; // default value
        Double calculation = 0.0;

        for (int i = 1; i < this.dimensions.size(); i++) {
            for (int j = 1; j < this.dimensions.size(); j++) {
                calculation = ((j + beta) *
                        (Math.pow(this.dimensions.get(j), i) - (1 / (Math.pow(j, i)))));
            }
            calculation = Math.pow(calculation, 2);
            evaluation += calculation;
        }

        return evaluation;
    }

    @Override
    public List<Individual> crossoverBLX(Individual individual) {
        IndividualPerm individualPerm = (IndividualPerm) individual;
        Random random = new Random();
        double sigma = 0.1;
        double alpha;
        double value;

        // Filhos resultantes do Crossover
        List<Individual> childrenCrossover = new ArrayList<>();

        IndividualPerm children1;
        IndividualPerm children2;

        List<Double> dimensionChildren1 = new ArrayList<>();
        List<Double> dimensionChildren2 = new ArrayList<>();

        for (int i = 0; i < this.dimensions.size(); i++) {
            alpha = random.nextGaussian(0, sigma);

            value = this.dimensions.get(i)
                    + (alpha * Math.abs(this.dimensions.get(i) - individualPerm.dimensions.get(i)));

            if (value < (dimensions.size() * -1))
                value = (dimensions.size() * -1);
            else if (value > dimensions.size())
                value = dimensions.size();

            dimensionChildren1.add(value);

            alpha = random.nextGaussian(0, sigma);

            value = individualPerm.dimensions.get(i)
                    + (alpha * Math.abs(this.dimensions.get(i) - individualPerm.dimensions.get(i)));

            if (value < (dimensions.size() * -1))
                value = (dimensions.size() * -1);
            else if (value > dimensions.size())
                value = dimensions.size();

            dimensionChildren2.add(value);
        }

        children1 = new IndividualPerm(dimensionChildren1);
        children1.getEvaluation();

        children2 = new IndividualPerm(dimensionChildren2);
        children2.getEvaluation();

        childrenCrossover.add(children1);
        childrenCrossover.add(children2);

        return childrenCrossover;
    }

    @Override
    public Individual getMutant() {
        Random random = new Random();
        double sigma = 0.1;
        double mutate;
        boolean mutanted = false;

        Individual mutant;

        List<Double> cloneGenesParents = new ArrayList<>(this.dimensions);

        for (int i = 0; i < cloneGenesParents.size(); i++) {
            mutate = random.nextGaussian(0, sigma);

            if (mutate <= 0.1) {
                if ((cloneGenesParents.get(i) + mutate) < (dimensions.size() * -1))
                    mutate = (dimensions.size() * -1);
                else if ((cloneGenesParents.get(i) + mutate) > dimensions.size())
                    mutate = dimensions.size();

                cloneGenesParents.set(i, (mutate));
                mutanted = true;
            }
        }

        if (!mutanted) {
            int positionRandom = random.nextInt(0, cloneGenesParents.size());
            mutate = random.nextGaussian(0, sigma);

            cloneGenesParents.set(positionRandom, (cloneGenesParents.get(positionRandom) + mutate));

        }

        mutant = new IndividualPerm(cloneGenesParents);

        mutant.getEvaluation();

        return mutant;
    }

    @Override
    public String toString() {
        DecimalFormat evaluationFormat = new DecimalFormat("0.00000000000000000000");
        return this.dimensions.toString() + " " + evaluationFormat.format(this.evaluation).toString();
    }

    public List<Double> getDimensions() {
        return this.dimensions;
    }

}
