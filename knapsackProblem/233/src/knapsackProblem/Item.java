package knapsackProblem;

/**
 * Klasa knapsackProblem.Item pozwala stworzyc przedmiot, o podanej wartosci oraz wadze
 *
 * @author Maksymilian Guzdziol
 */
public class Item {

    /** zmienna przechowujaca wartosc przedmiotu*/
    public double itemValue;
    /** zmienna przechowujaca wage przedmiotu*/
    public int itemWeight;
    /** zmienna przechowujaca ID przedmiotu*/
    public int itemID;
    /** licznik przedmiotow*/
    private static int counter=1;

    /** tworzy przedmiot o podanej wartosci i wadze oraz przypisuje mu ID
     *
     * @param itemValue
     * Wartosc przedmiotu
     * @param itemWeight
     * Waga przedmiotu
     */
    public Item(double itemValue, int itemWeight) {
        this.itemValue = itemValue;
        this.itemWeight = itemWeight;
        this.itemID = counter;
        setCounter(counter+1);
    }

    /**
     *
     * @return aktualna ilosc przedmiotow
     */
    public static int getCounter() {
        return counter;
    }

    /**
     *
     * @param counter
     * Parametr ustawiajacy aktualna ilosc obiektow
     */
    public static void setCounter(int counter) {
        Item.counter = counter;
    }

    /**
     *
     * @param itemValue
     * Parametr ustawiajacy wartosc przedmiotu
     */
    public void setItemValue(double itemValue) {
        this.itemValue = itemValue;
    }

    /**
     *
     * @param itemWeight
     * Parametr ustawiajacy wage przedmiotu
     */
    public void setItemWeight(int itemWeight) {
        this.itemWeight = itemWeight;
    }

    /**
     *
     * @return wartosc przedmiotu
     */
    public double getItemValue() {
        return itemValue;
    }

    /**
     *
     * @return wage przedmiotu
     */
    public int getItemWeight() {
        return itemWeight;
    }

    /**
     *
     * @param itemID
     * Parametr ustawiajacy ID przedmiotu
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     *
     * @return ID przedmiotu
     */
    public int getItemID() {
        return itemID;
    }
}
