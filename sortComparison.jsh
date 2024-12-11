int getCardValue(String card) {
        String numberPart = card.substring(0, card.length() - 1);
        return Integer.parseInt(numberPart);
}
int compareSuits(char suit1, char suit2) {
        String suits = "HCDS";
        int index1 = suits.indexOf(suit1);
        int index2 = suits.indexOf(suit2);
        return Integer.compare(index1, index2);
}
int cardCompare(String card1, String card2) {
        int value1 = getCardValue(card1);
        int value2 = getCardValue(card2);
        
        char suit1 = card1.charAt(card1.length() - 1);
        char suit2 = card2.charAt(card2.length() - 1);
        
        int suitComparison = compareSuits(suit1, suit2);
        if (suitComparison != 0) {
                return suitComparison;
        }
        
        return Integer.compare(value1, value2);
}
ArrayList<String> bubbleSort(ArrayList<String> array) {
        for (int i = 0; i < array.size(); i++) {
                for (int j = 0; j < array.size() - i - 1; j++) {
                        if (cardCompare(array.get(j), array.get(j + 1)) > 0) {
                                String temp = array.get(j);
                                array.set(j, array.get(j + 1));
                                array.set(j + 1, temp);
                        }
                }
        }
        return array;
}
ArrayList<String> mergeSort(ArrayList<String> array) {
        if (array.size() <= 1) {
                return array;
        }
        
        int middle = array.size() / 2;
        ArrayList<String> left = new ArrayList<>(array.subList(0, middle));
        ArrayList<String> right = new ArrayList<>(array.subList(middle, array.size()));
        
        left = mergeSort(left);
        right = mergeSort(right);
        
        return merge(left, right);
}
ArrayList<String> merge(ArrayList<String> left, ArrayList<String> right) {
        ArrayList<String> result = new ArrayList<>();
        while (!left.isEmpty() && !right.isEmpty()) {
                if (cardCompare(left.get(0), right.get(0)) <= 0) {
                        result.add(left.remove(0));
                } else {
                        result.add(right.remove(0));
                }
        }
        result.addAll(left);
        result.addAll(right);
        return result;
}
long measureBubbleSort(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        ArrayList<String> cards = new ArrayList<>(lines);
        
        long startTime = System.currentTimeMillis();
        
        bubbleSort(cards);
        
        long endTime = System.currentTimeMillis();
        
        return endTime - startTime;
}
long measureMergeSort(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        ArrayList<String> cards = new ArrayList<>(lines);
        
        long startTime = System.currentTimeMillis();
        
        mergeSort(cards);
        
        long endTime = System.currentTimeMillis();
        
        return endTime - startTime;
}
void sortComparison(String[] filenames) throws IOException {
        for (String filename : filenames) {
                long bubbleTime = measureBubbleSort(filename);
                long mergeTime = measureMergeSort(filename);
                System.out.printf("File: %s, Bubble Sort: %d ms, Merge Sort: %d ms%n", filename, bubbleTime, mergeTime);
        }
}
cardCompare("4H", "7S");
-1
cardCompare("4H", "7S");
cardCompare("4H", "3S");
var list = new ArrayList<String>(List.of("4H", "3S", "7S", "8C", "2D", "3H"));
bubbleSort(list);
mergeSort(list);
measureBubbleSort("sort10000.txt");
measureMergeSort("sort10000.txt");
sortComparison(new String[]{"sort10.txt", "sort100.txt", "sort10000.txt"});