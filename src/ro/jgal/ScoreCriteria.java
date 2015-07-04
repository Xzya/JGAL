package ro.jgal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Terminates when the given score is reached.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
public class ScoreCriteria implements TerminationCriteria {

    double score;
    int roundDecimals;

    /**
     * Score Criteria. Terminates when the given score is reached.
     * 
     * @param score - target score
     * @param roundDecimals - number of decimas to be rounded to
     */
    public ScoreCriteria(double score, int roundDecimals) {
        this.score = score;
        this.roundDecimals = roundDecimals;
    }

    /**
     * Checks if the termination criteria has been reached.
     * 
     * @param pop - checked population
     * @return - true if criteria is reached
     */
    @Override
    public boolean terminate(Population pop) {

        BigDecimal value = new BigDecimal(pop.getBestIndividual().getRawFitnessValue());
        value = value.setScale(roundDecimals, RoundingMode.HALF_UP);

        BigDecimal target = new BigDecimal(score);
        target = target.setScale(roundDecimals, RoundingMode.HALF_UP);

        return value.equals(target);
    }

}
