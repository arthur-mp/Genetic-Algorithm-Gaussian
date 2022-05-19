package individualPerm;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import individual.Individual;

public class IndividualPerm extends Individual {

    protected List<Double> dimensions;
    protected int minDomain;
    protected int maxDomain;

    protected IndividualPerm(int dimension) {
        this.dimensions = new ArrayList<>();
        this.minDomain = -dimension;
        this.maxDomain = dimension;

        Random random = new Random();

        for (double i = 0; i < (dimension); i++) {
            double value = random.nextDouble(this.maxDomain * 2) + this.minDomain;

            this.dimensions.add(value);
        }
    }

    protected IndividualPerm(List<Double> dimension) {
        this.dimensions = dimension;
        this.minDomain = -dimension.size();
        this.maxDomain = dimension.size();
    }

    @Override
    public Double evaluate() {
        Double evaluation = 0.0;
        int beta = 10; // default value optitional
        Double calculation = 0.0;

        for (int i = 1; i <= this.dimensions.size(); i++) {

            calculation = 0.0;

            for (int j = 1; j <= this.dimensions.size(); j++) {

                calculation += ((j + beta) *
                        (Math.pow(this.dimensions.get(j - 1), i) - (1 / (Math.pow(j, i)))));

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

            if (value < this.minDomain)
                value = (this.minDomain);
            else if (value > this.maxDomain)
                value = this.maxDomain;

            dimensionChildren1.add(value);

            alpha = random.nextGaussian(0, sigma);

            value = individualPerm.dimensions.get(i)
                    + (alpha * Math.abs(this.dimensions.get(i) - individualPerm.dimensions.get(i)));

            if (value < this.minDomain)
                value = (this.minDomain);
            else if (value > this.maxDomain)
                value = this.maxDomain;

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
                if ((cloneGenesParents.get(i) + mutate) < this.minDomain)
                    mutate = this.minDomain;
                else if ((cloneGenesParents.get(i) + mutate) > this.maxDomain)
                    mutate = this.maxDomain;

                cloneGenesParents.set(i, (mutate));
                mutanted = true;
            }
        }

        if (!mutanted) {
            int positionRandom = random.nextInt(0, cloneGenesParents.size());
            mutate = random.nextGaussian(0, sigma);

            if ((mutate) < this.minDomain)
                mutate = this.minDomain;
            else if ((mutate) > this.maxDomain)
                mutate = this.maxDomain;

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
