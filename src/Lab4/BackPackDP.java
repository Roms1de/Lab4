package Lab4;

import java.util.ArrayList;
import java.util.List;

public class BackPackDP {

    public static BackPackRec.KnapsackResult knapsackDP(ArrayList<Item> listOfItems, int length, int maxSize) {
        if (length <= 0 || maxSize <= 0) {
            return new BackPackRec.KnapsackResult(0, new ArrayList<>());
        }
        int[][] m = new int[length + 1][maxSize + 1];
        for (int j = 0; j <= maxSize; j++) {
            m[0][j] = 0;
        }
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= maxSize; j++) {
                if (listOfItems.get(i - 1).weight > j) {
                    m[i][j] = m[i - 1][j];
                } else {
                    m[i][j] = Math.max(m[i - 1][j], m[i - 1][j - listOfItems.get(i - 1).weight] + listOfItems.get(i - 1).price);
                }
            }
        }

        int totalValue = m[length][maxSize];
        List<Item> selectedItems = new ArrayList<>();
        int remainingWeight = maxSize;
        for (int i = length; i > 0 && totalValue > 0; i--) {
            if (totalValue != m[i - 1][remainingWeight]) {
                selectedItems.add(listOfItems.get(i - 1));
                totalValue -= listOfItems.get(i - 1).price;
                remainingWeight -= listOfItems.get(i - 1).weight;
            }
        }

        return new BackPackRec.KnapsackResult(m[length][maxSize], selectedItems);
    }
}