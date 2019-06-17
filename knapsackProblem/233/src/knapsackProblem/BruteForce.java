package knapsackProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa rozwiazujaca problem plecakowy za pomoca metody Brute Force, implementujaca interfejs {@link Algorithms}.
 *
 * @author Maksymilian Guzdziol
 */
public class BruteForce implements Algorithms {

    /**
     * Metoda rozwiazujaca problem plecakowy metoda silowa(brute force). Sprawdza ona wszystkie przypadki i zapisuje najlepsze rozwiazanie obiekcie klasy knapsackProblem.Solution
     * @param instance
     * Obiekt klasy knapsackProblem.Instance zawierajcy pojemnosc plecaka oraz aktualna liste przedmiotow znajdujacych sie w plecaku
     * @param currWeight
     * Aktualna waga wszystkich przedmiotow znajdujacych sie w plecaku
     * @param currVal
     * Aktualna wartosc wszystkich przedmiotow znajdujacych sie w plecaku
     * @param itemId
     * Lista ID przedmiotow ktore znajduja sie w plecaku
     * @return zwraca obkiet klasy knapsackProblem.Solution ktory zawiera rozwiazanie problemu plecakowego
     * @see Instance
     */
    @Override
    public Solution solve(Instance instance, int currWeight, double currVal, List<Integer> itemId)
    {
        Solution solution = new Solution();

        for(int i=0; i<instance.getItemsArray().size(); i++)
        {
            if(!itemId.contains(instance.getItemsArray().get(i).getItemID()) && currWeight+instance.getItemsArray().get(i).getItemWeight()<=instance.getKnapsackCapacity())
            {
                itemId.add(instance.getItemsArray().get(i).getItemID());
                currWeight=currWeight+instance.getItemsArray().get(i).getItemWeight();
                currVal=currVal+instance.getItemsArray().get(i).getItemValue();

                Solution tmpSol=solve(instance, currWeight, currVal, itemId);

                if(tmpSol.getBestValue()>solution.getBestValue())
                    solution=tmpSol;

                itemId.remove(new Integer(instance.getItemsArray().get(i).getItemID()));
                currWeight=currWeight-instance.getItemsArray().get(i).getItemWeight();
                currVal=currVal-instance.getItemsArray().get(i).getItemValue();
            }
        }
        if(solution.getBestValue()<currVal)
        {
            solution.setBestValue(currVal);
            solution.setBestWeight(currWeight);
            solution.setBestItemID(new ArrayList<>(itemId));
        }
        return solution;
    }

    /**
     *
     * @return opis algorytmu
     */
    @Override
    public String description()
    {
        return "Przeglad zupelny - metoda nieefektywna obliczeniowo lecz optymalna(sprawdza kazdy przypadek). Zlozonosc obliczeniowa O(2^n)";
    }
}
