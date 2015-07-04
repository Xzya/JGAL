package ro.jgal;

public class TerminationCriterias {

    /**
     * Convergence Criteria. Terminates when the population converges.
     * 
     * @return - termination criteria
     */
    public static TerminationCriteria convergenceCriteria() {
        return new ConvergenceCriteria();
    }

    /**
     * Score Criteria. Terminates when the given score is reached.
     * 
     * @param score - target score
     * @param roundDecimals - number of decimas to be rounded to
     * @return - termination criteria
     */
    public static TerminationCriteria scoreCriteria(double score, int roundDecimals) {
        return new ScoreCriteria(score, roundDecimals);
    }

}
