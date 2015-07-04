package ro.jgal;

/**
 * Statistics object. Keeps the min/max/avg/sum statistics
 * of a generation.
 * 
 * @author Mihail Cristian Dumitru
 *
 */
public class Statistics {

    int gen;
    double min;
    double max;
    double avg;
    double sum;

    public int getGen() {
        return gen;
    }
    public void setGen(int gen) {
        this.gen = gen;
    }
    public double getMin() {
        return min;
    }
    public void setMin(double min) {
        this.min = min;
    }
    public double getMax() {
        return max;
    }
    public void setMax(double max) {
        this.max = max;
    }
    public double getAvg() {
        return avg;
    }
    public void setAvg(double avg) {
        this.avg = avg;
    }
    public double getSum() {
        return sum;
    }
    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        String stats = "Gen. " + gen + " : Max/Min/Avg [" + max + "/" + min + "/" + avg + "]";
        return stats;
    }

}
