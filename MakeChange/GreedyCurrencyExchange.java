/*
* GreedyCurrencyExchange Class
* Master 2 IOT 2023
*
* By Fiacre Tsevi and Mohamed Al Hamed
* September 2023
*/
import java.util.*;

public class GreedyCurrencyExchange {
    private double amountToExchange;
    private List<Double> coinDenominations = Arrays.asList(5.0, 2.0, 1.0, 0.5, 0.2, 0.1, 0.05);
    private LinkedList<String> exchangeDetails = new LinkedList<>();

    public GreedyCurrencyExchange(List<Double> coinDenominations, double amountToExchange) {
        this.coinDenominations = coinDenominations;
        this.amountToExchange = amountToExchange;
        calculateGreedyExchange();
    }

    private void calculateGreedyExchange() {
        for (double coin :
                coinDenominations) {
            double numCoins = Math.floor(amountToExchange / coin * 100 + 0.5) / 100;
            exchangeDetails.add(Math.floor(numCoins) + " Coins of " + coin + " Euros");
            amountToExchange = amountToExchange % coin;
        }
    }

    public LinkedList<String> getExchangeResult() {
        return exchangeDetails;
    }

    public String toString() {
        return exchangeDetails.toString();
    }
}
