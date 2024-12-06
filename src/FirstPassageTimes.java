import java.util.Random;

public class FirstPassageTimes {

  public static void main(String[] args) {

    final double HEAD_PROBABILITY = 0.5;  //initial prob of flipping heads
    final int NUMBER_RUNS = 100;  //num of runs to simulate
    final int TWENTY_HEADS = 20;  //after 20 heads are flipped, a run ends

    int[] runs = new int[NUMBER_RUNS];  //array holding data for each run

    simulateRuns(HEAD_PROBABILITY, TWENTY_HEADS, runs);   //calling method that "flips the coins" ands stores results in runs array

    double mean = findMean(runs);

    selectionSort(runs);

    System.out.println("First 10 Run Lengths:");
    outputFirst10Runs(runs);

    double expectedMean = TWENTY_HEADS / HEAD_PROBABILITY;
    System.out.println("\nActual Mean: " + mean);
    System.out.println("Expected Mean: " + expectedMean);
    System.out.println("Ratio (Actual Mean / Expected Mean): " + (mean / expectedMean));
  }

  //flipping the coins
  public static void simulateRuns(double headProbability, int twentyHeads, int[] runs) {
    Random random = new Random();

    for (int i = 0; i < runs.length; i++) {    //loop thru runs array
      runs[i] = doRun(random, headProbability, twentyHeads);    //call doRun helper method
    }
  }

  //helper method for simulateRuns ^^
  static int doRun(Random random, double headProbability, int twentyHeads) {
    int heads = 0;
    int flips = 0;

    while (heads < twentyHeads) {
      flips++;
      if (random.nextDouble() <= headProbability) {   //flipping until heads reaches 20, then records number of flips taken
        heads++;
      }
    }
    return flips;   //once all runs are done, simulateRuns finishes and runs array in "main" is filled with the data
  }

  //find mean of the runs
  static double findMean(int[] runs) {
    int sum = 0;
    for (int run : runs) {
      sum += run;
    }
    return (double) sum / runs.length;  //calculates mean and returns it back to main under "mean"
  }

  //outputt first 10 runs
  static void outputFirst10Runs(int[] runs) {
    int limit = Math.min(10, runs.length);
    for (int i = 0; i < limit; i++) {
      System.out.print(runs[i] + ", ");
    }
    System.out.println();
  }

  //sort runs array
  static void selectionSort(int[] runs) {
    for (int sorted_count = 0; sorted_count < runs.length - 1; sorted_count++) {
      int min_index = find_min_index(runs, sorted_count, runs.length - 1); //find index of the minimum value
      swap(runs, sorted_count, min_index); //swap current element with the smallest
    }
  }

  //helper method for selectionSort (find smallest value index)
  static int find_min_index(int[] array, int start, int end) {
    int min_index = start;
    for (int sorted_count = start + 1; sorted_count <= end; sorted_count++) {
      if (array[sorted_count] < array[min_index]) {
        min_index = sorted_count;
      }
    }
    return min_index;
  }

  /**
   * helper method for selectionSort (swap two values in array to help with sorting)
   * @param array
   * @param first_index
   * @param second_index
   */
  static void swap(int[] array, int first_index, int second_index) {
    int temp = array[first_index];
    array[first_index] = array[second_index];
    array[second_index] = temp;
  }
}
