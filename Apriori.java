import java.util.*;

public class Apriori {

    static List<Set<String>> transactions = new ArrayList<>();

    public static void main(String[] args) {

        // Sample transactions
        transactions.add(new HashSet<>(Arrays.asList("Milk", "Bread", "Butter")));
        transactions.add(new HashSet<>(Arrays.asList("Bread", "Butter")));
        transactions.add(new HashSet<>(Arrays.asList("Milk", "Bread")));
        transactions.add(new HashSet<>(Arrays.asList("Milk", "Butter")));
        transactions.add(new HashSet<>(Arrays.asList("Bread", "Butter")));

        int minSupport = 2;

        System.out.println("Frequent Itemsets:");
        apriori(minSupport);
    }

    static void apriori(int minSupport) {

        // Step 1: Get unique items
        Set<String> items = new HashSet<>();
        for (Set<String> transaction : transactions) {
            items.addAll(transaction);
        }

        // Step 2: Generate 1-itemsets
        Map<Set<String>, Integer> freqMap = new HashMap<>();

        for (String item : items) {
            Set<String> itemSet = new HashSet<>();
            itemSet.add(item);

            int count = getSupport(itemSet);
            if (count >= minSupport) {
                freqMap.put(itemSet, count);
            }
        }

        print(freqMap);

        // Step 3: Generate 2-itemsets
        List<String> itemList = new ArrayList<>(items);

        Map<Set<String>, Integer> pairMap = new HashMap<>();

        for (int i = 0; i < itemList.size(); i++) {
            for (int j = i + 1; j < itemList.size(); j++) {

                Set<String> pair = new HashSet<>();
                pair.add(itemList.get(i));
                pair.add(itemList.get(j));

                int count = getSupport(pair);
                if (count >= minSupport) {
                    pairMap.put(pair, count);
                }
            }
        }

        print(pairMap);
    }

    // Function to calculate support
    static int getSupport(Set<String> itemSet) {
        int count = 0;

        for (Set<String> transaction : transactions) {
            if (transaction.containsAll(itemSet)) {
                count++;
            }
        }
        return count;
    }

    // Print itemsets
    static void print(Map<Set<String>, Integer> map) {
        for (Map.Entry<Set<String>, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> Support: " + entry.getValue());
        }
    }
}