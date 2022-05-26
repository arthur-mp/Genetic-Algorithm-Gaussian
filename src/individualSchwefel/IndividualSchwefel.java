package individualSchwefel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import individual.Individual;

public class IndividualSchwefel extends Individual {

    protected List<Double> dimensions;
    protected int minDomain;
    protected int maxDomain;

    protected IndividualSchwefel(int dimension) {
        this.dimensions = new ArrayList<>();
        this.minDomain = -500;
        this.maxDomain = 500;

        Random random = new Random();

        for (double i = 0; i < (dimension); i++) {
            double value = random.nextDouble(this.maxDomain * 2) + this.minDomain;

            this.dimensions.add(value);
        }
    }

    protected IndividualSchwefel(List<Double> dimension) {
        this.dimensions = dimension;
    }

    @Override
    public Double evaluate() {
        Double evaluation = 0.0;
        Double sum = 0.0;

        for (int i = 0; i < this.dimensions.size(); i++) {
            sum += (this.dimensions.get(i) * Math.sin(Math.sqrt(Math.abs(this.dimensions.get(i)))));
        }

        evaluation = ((418.9829 * dimensions.size()) - (sum));

        return evaluation;
    }

    @Override
    public List<Individual> crossoverBLX(Individual individual) {
        IndividualSchwefel individualSchwefel = (IndividualSchwefel) individual;
        Random random = new Random();
        double sigma = 0.5;
        double alpha;
        double value;

        // Filhos resultantes do Crossover
        List<Individual> childrenCrossover = new ArrayList<>();

        IndividualSchwefel children1;
        IndividualSchwefel children2;

        List<Double> dimensionChildren1 = new ArrayList<>();
        List<Double> dimensionChildren2 = new ArrayList<>();

        for (int i = 0; i < this.dimensions.size(); i++) {
            alpha = random.nextGaussian(0, sigma);

            value = this.dimensions.get(i)
                    + (alpha * Math.abs(this.dimensions.get(i) - individualSchwefel.dimensions.get(i)));

            if (value < this.minDomain)
                value = (this.minDomain);
            else if (value > this.maxDomain)
                value = this.maxDomain;

            dimensionChildren1.add(value);

            alpha = random.nextGaussian(0, sigma);

            value = individualSchwefel.dimensions.get(i)
                    + (alpha * Math.abs(this.dimensions.get(i) - individualSchwefel.dimensions.get(i)));

            if (value < this.minDomain)
                value = (this.minDomain);
            else if (value > this.maxDomain)
                value = this.maxDomain;

            dimensionChildren2.add(value);
        }

        children1 = new IndividualSchwefel(dimensionChildren1);
        children1.getEvaluation();

        children2 = new IndividualSchwefel(dimensionChildren2);
        children2.getEvaluation();

        childrenCrossover.add(children1);
        childrenCrossover.add(children2);

        return childrenCrossover;
    }

    @Override
    public Individual getMutant() {
        Random random = new Random();
        double mutate;
        double r;
        boolean mutanted = false;

        Individual mutant;

        List<Double> cloneGenesParents = new ArrayList<>(this.dimensions);

        for (int i = 0; i < cloneGenesParents.size(); i++) {
            mutate = random.nextDouble();

            if (mutate <= 0.1) {
                if ((cloneGenesParents.get(i) + mutate) < this.minDomain)
                    mutate = this.minDomain;
                else if ((cloneGenesParents.get(i) + mutate) > this.maxDomain)
                    mutate = this.maxDomain;

                r = random.nextGaussian();
                cloneGenesParents.set(i, (cloneGenesParents.get(i) + r));
                mutanted = true;
            }
        }

        if (!mutanted) {
            int positionRandom = random.nextInt(0, cloneGenesParents.size());
            r = random.nextGaussian();

            cloneGenesParents.set(positionRandom, (cloneGenesParents.get(positionRandom) + r));

        }

        mutant = new IndividualSchwefel(cloneGenesParents);

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
