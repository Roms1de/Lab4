package Lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GreedyAlgorithm {

    public static BackPackRec.KnapsackResult knapsackGreedy(ArrayList<Item> listOfItems, int maxSize) {
        Collections.sort(listOfItems, Comparator.comparingDouble(item -> getPrice((Item) item) / getWeight((Item) item)).reversed());

        int totalWeight = 0;
        int totalValue = 0;
        List<Item> selectedItems = new ArrayList<>();

        // Проходим по отсортированному списку и добавляем предметы в рюкзак, начиная с самых выгодных
        for (Item item : listOfItems) {
            if (totalWeight + item.getWeight() <= maxSize) {
                selectedItems.add(item);
                totalWeight += item.getWeight();
                totalValue += item.getPrice();
            }
        }

        return new BackPackRec.KnapsackResult(totalValue, selectedItems);
    }

    // Метод для получения цены предмета
    private static double getPrice(Item item) {
        return item.getPrice();
    }

    // Метод для получения веса предмета
    private static double getWeight(Item item) {
        return item.getWeight();
    }
}