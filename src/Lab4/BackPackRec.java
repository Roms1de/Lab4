package Lab4;

import java.util.ArrayList;
import java.util.List;


public class BackPackRec {
    static class KnapsackResult {
        int maxValue;
        List<Item> items;

        public KnapsackResult(int maxValue, List<Item> items) {
            this.maxValue = maxValue;
            this.items = items;
        }
    }

    public static KnapsackResult knapsackRec(ArrayList<Item> listOfItems, int length, int maxSize) {
        if (length <= 0) {
            return new KnapsackResult(0, new ArrayList<>());
        } else if (listOfItems.get(length - 1).weight > maxSize) {
            return knapsackRec(listOfItems, length - 1, maxSize);
        } else {
            KnapsackResult withoutItem = knapsackRec(listOfItems, length - 1, maxSize);
            KnapsackResult withItem = knapsackRec(listOfItems, length - 1, maxSize - listOfItems.get(length - 1).weight);
            int newValue = withItem.maxValue + listOfItems.get(length - 1).price;
            if (newValue > withoutItem.maxValue) {
                List<Item> selectedItems = new ArrayList<>(withItem.items);
                selectedItems.add(listOfItems.get(length - 1));
                return new KnapsackResult(newValue, selectedItems);
            } else {
                return withoutItem;
            }
        }
    }
}
