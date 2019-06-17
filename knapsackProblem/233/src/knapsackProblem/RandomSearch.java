package knapsackProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasa rozwiazujaca problem plecakowy za pomoca przegladu losowego, implementujaca interfejs {@link Algorithms}.
 */
public class RandomSearch implements Algorithms {

    /**
     *
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
    public Solution solve(Instance instance, int currWeight, double currVal, List<Integer> itemId) {

        Solution solution= new Solution();

        for(int i=0; i<100; i++)
        {
            while (currWeight<=instance.getKnapsackCapacity())
            {
                int r=new Random().nextInt(instance.getItemsArray().size());
                if(!itemId.contains(instance.getItemsArray().get(r).getItemID()) && currWeight+instance.getItemsArray().get(r).getItemWeight()<=instance.getKnapsackCapacity())
                {
                    itemId.add(instance.getItemsArray().get(r).getItemID());
                    currWeight=currWeight+instance.getItemsArray().get(r).getItemWeight();
                    currVal=currVal+instance.getItemsArray().get(r).getItemValue();
                }
                else
                    break;
            }
            if(solution.getBestValue()<currVal)
            {
                solution.setBestValue(currVal);
                solution.setBestWeight(currWeight);
                solution.setBestItemID(new ArrayList<>(itemId));
            }
            itemId.clear();
            currWeight=0;
            currVal=0;
        }

        return solution;
    }

    /**
     *
     * @return opis algorytmu
     */
    @Override
    public String description() {
        return "Przeglad losowy - algorytm wybiera losowe przedmiotu do momentu az nie zabraknie miejsca w plecaku. Algorytm powatarzany jest 100 razy.";
    }
}
