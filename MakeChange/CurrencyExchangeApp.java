/*
* CurrencyExchangeApp Class
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

public class CurrencyExchangeApp {

    public static void main(String[] args) {
        double amountToExchange = 6.5;
        System.out.println("Greedy Currency Exchange");
        List<Double> coinDenominations = Arrays.asList(5.0, 2.0, 1.0, 0.5, 0.2, 0.1, 0.05);
        GreedyCurrencyExchange greedyExchange = new GreedyCurrencyExchange(coinDenominations, amountToExchange);
        System.out.println(greedyExchange.getExchangeResult());

        System.out.println("\nCalculating All Possibilities");
        ExhaustiveCalculator exhaustiveCalculator = new ExhaustiveCalculator();
        System.out.println(exhaustiveCalculator.getAllPossibilities());

        System.out.println("\nBest Solution");
        System.out.println(exhaustiveCalculator.getBestSolution());
    }
}
