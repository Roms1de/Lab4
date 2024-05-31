package Lab4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//Сделанно
public class lab4 {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Item> listOfItem = new ArrayList<>();
        Backpack backpack = new Backpack();

        Scanner in = new Scanner(System.in);
        int ans;


        do {
            System.out.println("--Меню--");
            System.out.println("    1. Запись объектов из файла в список");
            System.out.println("    2. Добавление объекта в ручную");
            System.out.println("    3. Задание размера рюкзака");
            System.out.println("    4. Изменение существующего объекта");
            System.out.println("    5. Удаление объекта");
            System.out.println("    6. Просмотр содержимого списка");
            System.out.println("    7. Выбор способа решения задачи");
            System.out.println("    8. Сравнение времени работы алгоритмов");
            System.out.println("    9. Выход");
            System.out.print("Выберите пункт меню: ");
            ans = in.nextInt();


            switch (ans) {
                case 1:
                    in.nextLine();
                    System.out.print("Укажите путь файла из которого записать в список: ");
                    String new_path_in = in.nextLine().trim();
                    if (new_path_in.startsWith("\"") && new_path_in.endsWith("\"")) {
                        new_path_in = new_path_in.substring(1, new_path_in.length() - 1);
                    }
                    File files = new File(new_path_in);
                    System.out.println("~Начинаем запись в список~");
                    System.out.print(".");
                    Thread.sleep(300);
                    System.out.print(".");
                    Thread.sleep(300);
                    System.out.println(".");


                    if (!listOfItem.isEmpty()) {
                        System.out.println("Список объектов не пуст, будет выполнено его очищенеи");
                        for (int k = listOfItem.size() - 1; k > 0; k--) {
                            listOfItem.remove(k);
                        }
                        if (listOfItem.isEmpty()) {
                            System.out.println("Список очищен");
                        }
                    }

                    if (!files.exists())
                        System.out.println("~Такого файла не существует~");
                    else if (!files.canWrite())
                        System.out.println("~Файл не подходит для записи~");
                    else if (files.isDirectory())
                        System.out.println("~Это не файл~");
                    else {
                        try (BufferedReader reader = new BufferedReader(new FileReader(files))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                String[] new_line = line.split(";   ~");
                                String name = new_line[0].split(" ")[1];
                                int price = Integer.parseInt(new_line[1].split(" ")[1]);
                                int weight = Integer.parseInt(new_line[2].split(" ")[1]);
                                listOfItem.add(new Item(name, price, weight));
                            }
                            System.out.println("--Запись выполнена успешно!");
                            backpack.setListOfObjects(listOfItem);
                        } catch (IOException e) {
                            System.out.println("Ошибка при чтении из файла: " + e.getMessage());
                            e.printStackTrace();
                        }

                    }
                    break;
                case 2:
                    System.out.print("Сколько объектов добавить: ");
                    int numberOfObject = in.nextInt();
                    int index = 1;

                    do {
                        System.out.println("-----Заполнение объекта-" + index + "-----");
                        listOfItem.add(new Item());
                        numberOfObject--;
                        index++;
                    } while (numberOfObject > 0);

                    backpack.setListOfObjects(listOfItem);
                    break;
                case 3:
                    System.out.print("Введите разрме рюкзака: ");
                    backpack.setSize(in.nextInt());
                    System.out.println("Размер рюкзака успешно задан - " + backpack.getSize());
                    break;
                case 4:
                    for (int i = 0; i < listOfItem.size(); i++) {
                        System.out.print(Integer.toString(i + 1));
                        listOfItem.get(i).getItem();
                    }
                    System.out.println("\nКакой объект изменить?");
                    System.out.print("    Номер: ");
                    int n = in.nextInt() - 1;

                    listOfItem.set(n, new Item());
                    System.out.println("\nОбъект успешно изменен");

                    for (int i = 0; i < listOfItem.size(); i++) {
                        System.out.print(Integer.toString(i + 1));
                        listOfItem.get(i).getItem();
                    }
                    System.out.println();
                    break;
                case 5:
                    in.nextLine();
                    System.out.println("    1. Удалить определенный объект");
                    System.out.println("    2. Удалить весь список");
                    System.out.print("Выберите пунт меню: ");
                    int localAns = in.nextInt();


                    switch (localAns) {
                        case 1:
                            System.out.print("Укажите номер элемента, который нужно удалить: ");
                            int indexOfObeject = in.nextInt() - 1;
                            listOfItem.remove(indexOfObeject);
                            System.out.println("Объект номер " + indexOfObeject + " удален.");
                            break;
                        case 2:
                            while (!listOfItem.isEmpty()) {
                                listOfItem.remove(listOfItem.size() - 1);
                            }
                            if (listOfItem.isEmpty()) {
                                System.out.println("Список успешно ощичен");
                            } else {
                                System.out.println("Ошибка");
                            }
                            break;
                    }
                    break;
                case 6:
                    if (listOfItem.isEmpty())
                        System.out.println("--Список пуст--");

                    for (int i = 0; i < listOfItem.size(); i++) {
                        System.out.print(Integer.toString(i + 1));
                        listOfItem.get(i).getItem();
                    }
                    break;
                case 7:
                    if (backpack.size == 0) {
                        System.out.println("Размер рюкзака не задан, введите размер");
                        break;
                    }
                    in.nextLine();
                    int loc_ans;

                    System.out.println("    1. Рекурсивный метод");
                    System.out.println("    2. Метод динамического программирования");
                    System.out.println("    3. Метод жадного алгоритма(Макс соотношение цена/вес");

                    System.out.print("Выберите метод: ");
                    loc_ans = in.nextInt();

                    switch (loc_ans) {
                        case 1:
                            BackPackRec.KnapsackResult result = BackPackRec.knapsackRec(listOfItem, listOfItem.size(), backpack.size);
                            backpack.setCurretnSize(result, backpack.size);
                            System.out.println("Максимальная сумма: " + result.maxValue);
                            System.out.println("Текущий размер рюкзака: " + backpack.currentSize + "/" + backpack.size);
                            System.out.println("--Объекты, которые вошли в рюкзак");
                            for (int ind = 0; ind < result.items.size(); ind++) {
                                System.out.print(Integer.toString(ind + 1));
                                result.items.get(ind).getItem();
                            }
                            break;
                        case 2:
                            BackPackRec.KnapsackResult result1 = BackPackDP.knapsackDP(listOfItem, listOfItem.size(), backpack.size);
                            backpack.setCurretnSize(result1, backpack.size);
                            System.out.println("Максимальная сумма: " + result1.maxValue);
                            System.out.println("Текущий размер рюкзака: " + backpack.currentSize + "/" + backpack.size);
                            System.out.println("--Объекты, которые вошли в рюкзак");
                            for (int ind = 0; ind < result1.items.size(); ind++) {
                                System.out.print(Integer.toString(ind + 1));
                                result1.items.get(ind).getItem();
                            }
                            break;
                        case 3:
                            BackPackRec.KnapsackResult result2 = GreedyAlgorithm.knapsackGreedy(listOfItem, backpack.size);
                            backpack.setCurretnSize(result2, backpack.size);
                            System.out.println("Максимальная сумма: " + result2.maxValue);
                            System.out.println("Текущий размер рюкзака: " + backpack.currentSize + "/" + backpack.size);
                            System.out.println("--Объекты, которые вошли в рюкзак");
                            for (int ind = 0; ind < result2.items.size(); ind++) {
                                System.out.print(Integer.toString(ind + 1));
                                result2.items.get(ind).getItem();
                            }
                            break;
                    }
                    break;
                case 8:
                    long executionTime;

                    long startTimeRec = System.currentTimeMillis();
                    BackPackRec.KnapsackResult result = BackPackRec.knapsackRec(listOfItem, listOfItem.size(), backpack.size);
                    long endTimeRec = System.currentTimeMillis();
                    executionTime = endTimeRec - startTimeRec;
                    System.out.println("Время выполнения рекурсивного метода: " + executionTime + "мс");

                    long startTimeDP = System.currentTimeMillis();
                    BackPackRec.KnapsackResult result1 = BackPackDP.knapsackDP(listOfItem, listOfItem.size(), backpack.size);
                    long endTimeDP = System.currentTimeMillis();
                    executionTime = endTimeDP - startTimeDP;
                    System.out.println("Время выполнения динамического метода: " + executionTime + "мс");


                    long startTimeGreedy = System.currentTimeMillis();
                    BackPackRec.KnapsackResult result2 = GreedyAlgorithm.knapsackGreedy(listOfItem, backpack.size);
                    long endTimeGreedy = System.currentTimeMillis();
                    executionTime = endTimeGreedy - startTimeGreedy;
                    System.out.println("Время выполнения жадного метода: " + executionTime + "мс");

                    break;
            }
        }
        while (ans != 9);
    }
}


class Item {
    public String name;
    public int price;
    public int weight;


    Item(String name, int price, int weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    Item() {
        fillItem();
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    void fillItem() {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите имя: ");
        this.name = in.nextLine();
        System.out.print("Введите цену: ");
        this.price = in.nextInt();
        System.out.print("Введите вес: ");
        this.weight = in.nextInt();
    }

    void getItem() {
        System.out.println(")-- Имя: " + name + "; Цена: " + price + "; Вес: " + weight);
    }
}

class Backpack {
    int size = 0;
    int currentSize;
    ArrayList<Item> ListOfObjects;

    int getSize() {
        return size;
    }

    void setSize(int size) {
        this.size = size;
    }

    void setListOfObjects(ArrayList<Item> list) {
        this.ListOfObjects = list;
    }

    void setCurretnSize(BackPackRec.KnapsackResult result, int sizeOfBackpack) {
        int summOfWeight = 0;
        for (int ind = 0; ind < result.items.size(); ind++) {
            summOfWeight += result.items.get(ind).weight;
        }
        this.currentSize = sizeOfBackpack - summOfWeight;
    }

}
