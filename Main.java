public class Main {
    public static void main(String[] args) {
        MyTreeSet<Integer, String> test = new MyTreeSet<>();
        test.put(100, "100");
        test.put(80, "80");
        test.put(135, "135");
        test.put(40, "40");
        test.put(90, "90");
        test.put(120, "120");
        test.put(170, "170");
        test.put(20, "20");
        test.put(60, "60");
        test.put(10, "10");
        test.put(50, "50");
        test.put(70, "70");
        test.put(55, "55");
        test.printTree();
        
    }
}