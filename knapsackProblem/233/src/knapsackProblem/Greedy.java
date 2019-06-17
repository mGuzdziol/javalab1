package knapsackProblem;

import java.util.*;

/**
 * Klasa rozwiazujaca problem plecakowy za pomoca algorytmu zachlannego, implementujaca {@link Algorithms}
 *
 * @author Maksymilian Guzdziol
 */
public class Greedy implements Algorithms {

    /**
     * Metoda rozwizujaca problem plecakowy za pomoca algorytmu zachlannego.
     *@param instance
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
        List<Item> sortedList = instance.items;

        Collections.sort(sortedList, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return (o1.getItemValue()/o1.getItemWeight()) < (o2.getItemValue()/o2.getItemWeight()) ? 1 : -1;
            }
        });

        for(int i=0; i<instance.getItemsArray().size();i++)
        {
            if(currWeight+sortedList.get(i).getItemWeight()<=instance.getKnapsackCapacity())
            {
                itemId.add(sortedList.get(i).getItemID());
                currWeight=currWeight+sortedList.get(i).getItemWeight();
                currVal=currVal+sortedList.get(i).getItemValue();
            }
        }

        solution.setBestValue(currVal);
        solution.setBestWeight(currWeight);
        solution.setBestItemID(itemId);

        return solution;
    }

    /**
     *
     * @return opis algorytmu
     */
    @Override
    public String description()
    {
        return "Algorytm zachlanny - sortuje elementy w kolejnosci malejacej porownujac stosunek wartosci do wagi przedmiotu. Nastepnie wstawia je kolejno" +
                "do plecaka zaczynajac od przedmiotu o najwiekszym ilorazie. Jesli jakis element nie miesci sie w plecaku, zostaje on pomijany a algorytm przechodzi do" +
                "nastepnego. Algorytm nie jest optymalny. Zlozonosc obliczeniowa O(n*logn)";
    }
}
