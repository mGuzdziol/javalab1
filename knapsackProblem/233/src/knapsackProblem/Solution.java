package knapsackProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa zawierajaca rozwiazania problemu plecakowego
 *
 * @author Maksymilian Guzdziol
 */
public class Solution {

    /**
     * Najlepsza uzyskana wartosc
     */
    public double bestValue=0;
    /**
     * Waga plecaka
     */
    public int bestWeight=0;
    /**
     * ID przedmiotow towrzacych najlepsze uzyskane rozwiazanie
     */
    public List<Integer> bestItemID = new ArrayList<>();

    /**
     *
     * @return najlepsze uzyskane rozwiazanie
     */
    public double getBestValue() {
        return bestValue;
    }

    /**
     *
     * @param bestValue
     * Parametr ustawia najlepsze dotychczasowe rozwiazanie
     */
    public void setBestValue(double bestValue) {
        this.bestValue = bestValue;
    }

    /**
     *
     * @return wage plecaka
     */
    public int getBestWeight() {
        return bestWeight;
    }

    /**
     *
     * @param bestWeight
     * Parametr ustawia wage plecaka
     */
    public void setBestWeight(int bestWeight) {
        this.bestWeight = bestWeight;
    }

    /**
     *
     * @return liste ID przedmiotow tworzace najlepsze dotychczasowe rozwiazanie
     */
    public List<Integer> getBestItemID() {
        return bestItemID;
    }

    /**
     *
     * @param bestItemID
     * Parametr ustawia liste ID przedmiotow tworzace najlepsze dotychczasowe rozwiazanie
     */
    public void setBestItemID(List<Integer> bestItemID) {
        this.bestItemID = bestItemID;
    }
}
