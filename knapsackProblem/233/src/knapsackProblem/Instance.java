package knapsackProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa knapsackProblem.Instance przechowuje liste przedmiotow oraz okresla pojemnosc plecaka
 *
 * @author Maksymilian Guzdziol
 */
public class Instance {

    /**
     * Pojemnosc plecaka
     */
    public int knapsackCapacity;
    /**
     * Lista przedmiotow w plecaku
     */
    public List<Item> items = new ArrayList<Item>();

    /**
     * Dodaje przedmiot do listy(plecaka)
     * @param item przedmiot dodany do listy
     * @see Item
     */
    public void addItem(Item item)
    {
        items.add(item);
    }

    /**
     * Usuwa przedmiot z listy od podanym ID
     * @param id ID przedmiotu
     */
    public void removeItem(int id)
    {
        for(Item it : items)
        {
            if(it.getItemID()==id)
            {
                items.remove(it);
                Item.setCounter(Item.getCounter()-1);

                int counter=1;
                for(Item i : items)
                {
                    i.setItemID(counter);
                    counter++;
                }

                break;
            }
        }
    }

    /**
     *
     * @param value
     * wartosc przedmiotu
     * @param weight
     * waga przedmiotu
     * @return przedmiot o podanej wadze i wartosci
     */
    public Item getItem(float value, int weight)
    {
        for(Item it : items)
        {
            if(it.getItemWeight()==weight && it.getItemValue()==value)
            {
                return it;
            }
        }
        return null;
    }

    /**
     *
     * @param id
     * ID przedmiotu
     * @return przedmiot o podanym ID
     */
    public Item getItem(int id)
    {
        for(Item it : items)
        {
            if(it.getItemID()==id)
            {
                return it;
            }
        }
        return null;
    }

    /**
     *
     * @param knapsackCapacity
     * Parametr ustawiajacy pojemnosc plecaka
     */
    public void setKnapsackCapacity(int knapsackCapacity) {
        this.knapsackCapacity = knapsackCapacity;
    }

    /**
     *
     * @return Zwraca pojemnosc plecaka
     */
    public int getKnapsackCapacity() {
        return knapsackCapacity;
    }

    /**
     *
     * @param items
     * Parametr ustawiajacy liste przedmiotow
     */
    public void setItemsArray(List<Item> items) {
        this.items = items;
    }

    /**
     *
     * @return liste przedmiotow
     */
    public List<Item> getItemsArray() {
        return items;
    }
}
