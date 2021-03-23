public class SSListLauncher{
    public static void main(String[] args){
        SLList<String> s1 = new SLList<>("Hello");
        s1.addFirst("Hi");
        System.out.println(s1.size());
    }
}