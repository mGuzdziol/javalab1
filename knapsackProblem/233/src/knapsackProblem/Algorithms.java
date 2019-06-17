package knapsackProblem;

import java.util.List;

/**
 * Interfejs ktory nadaje wymogi jakie musza spelnic klasy {@link BruteForce} i {@link Greedy}
 *
 * @author Maksymilian Guzdziol
 */
public interface Algorithms {

    /**
     * Publiczna abstrakcyjna metoda narzucajaca implementacje(algorytmow) klasie {@link BruteForce} i {@link Greedy}
     *@param instance
     * Obiekt klasy knapsackProblem.Instance zawierajcy pojemnosc plecaka oraz aktualna liste przedmiotow znajdujacych sie w plecaku
     * @param currWeight
     * Aktualna waga wszystkich przedmiotow znajdujacych sie w plecaku
     * @param currVal
     * Aktualna wartosc wszystkich przedmiotow znajdujacych sie w plecaku
     * @param itemId
     * Lista ID przedmiotow ktore znajduja sie w plecaku
     * @return zwraca obkiet klasy knapsackProblem.Solution ktory zawiera rozwiazanie problemu plecakowego
     */
    public Solution solve(Instance instance, int currWeight, double currVal, List<Integer> itemId);

    /**
     *
     * @return Publiczna abstrakcyjna metoda narzucajaca implementacje opisu algorytmow klasie {@link BruteForce} i {@link Greedy}
     */
    public String description();
}
