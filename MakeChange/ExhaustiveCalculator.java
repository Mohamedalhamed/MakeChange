/*
* ExhaustiveCalculator Class
* Master 2 IOT 2023
*
* By Fiacre Tsevi and Mohamed Al Hamed
* September 2023
*/
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ExhaustiveCalculator {
    private double targetAmount = 15.5;
    private List<Double> coinDenominations = Arrays.asList(5.0, 2.0, 1.5);
    private LinkedList<Double> currentCombination = new LinkedList<>();

    private LinkedList<String> potentialSolution = new LinkedList<>();
    private LinkedList<String> allSolutions = new LinkedList<>();

    private int bestCost = 0;

    public ExhaustiveCalculator() {
        try {
            PrintWriter writer = new PrintWriter("Output_allSolutions.txt", "UTF-8");
            calculateMultiplePossibilities(targetAmount, 0, writer);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public ExhaustiveCalculator(List<Double> coinDenominations, double targetAmount) {
        this.coinDenominations = coinDenominations;
        this.targetAmount = targetAmount;

        try {
            PrintWriter writer = new PrintWriter("Output_allSolutions.txt", "UTF-8");
            calculateMultiplePossibilities(targetAmount, 0, writer);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void calculateBestSolutionWithoutCut() {
        int newCost = 0;

        for (int i = 0; i < currentCombination.size(); i++) {
            newCost += currentCombination.get(i);
        }

        if (bestCost == 0) {
            bestCost = newCost;
        }
        if (newCost <= bestCost) {
            this.potentialSolution = new LinkedList<>();
            this.potentialSolution.add(currentCombination.toString());
            bestCost = newCost;
        }
    }

    private void calculateBestSolutionWithCut() {
        int newCost = 0;
        while (newCost <= bestCost) {
            for (int i = 0; i < currentCombination.size(); i++) {
                newCost += currentCombination.get(i);
            }

            if (bestCost == 0) {
                bestCost = newCost;
            }
            if (newCost <= bestCost) {
                this.potentialSolution = new LinkedList<>();
                this.potentialSolution.add(currentCombination.toString());
                bestCost = newCost;
            }
        }
    }

    private void calculateMultiplePossibilities(double target, int index, PrintWriter writer) {
        if (target == 0) {
            writer.println(currentCombination.toString());
            allSolutions.add(currentCombination.toString());

            long startTime = System.nanoTime();
            calculateBestSolutionWithCut();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("Solution with Cut for: " + currentCombination.toString() + " takes: " + duration);

            startTime = System.nanoTime();
            calculateBestSolutionWithoutCut();
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            System.out.println("Solution without Cut for: " + currentCombination.toString() + " takes: " + duration);
        } else {
            if (index >= coinDenominations.size()) {
            } else {
                for (int j = 0; j <= (int) Math.floor(target / coinDenominations.get(index)); j++) {
                    currentCombination.add((double) j);
                    calculateMultiplePossibilities(target - j * coinDenominations.get(index), index + 1, writer);
                    currentCombination.removeLast();
                }
            }
        }
    }

    public LinkedList<String> getBestSolution() {
        return this.potentialSolution;
    }

    public LinkedList<String> getAllPossibilities() {
        return allSolutions;
    }
}
