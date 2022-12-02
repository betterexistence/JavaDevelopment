public class Main {
    public static void main(String[] args) {
        Pair<String, String> pair = Pair.of("456", "123");
        String i = pair.getFirst(); // 1
        String s = pair.getSecond(); // "hello"

        Pair<String, String> pair2 = Pair.of("456", "123");
        boolean mustBeTrue = pair.equals(pair2); // true!
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!

        System.out.println(i);
        System.out.println(s);
        System.out.println(mustBeTrue);
        System.out.println(mustAlsoBeTrue);
    }
}

class Pair<T, V>{
    private T numberPair;
    private V message;
    public T getFirst(){
        return numberPair;
    }
    public V getSecond(){
        return message;
    }
    private Pair(T numberPair, V message) {
        this.numberPair = numberPair;
        this.message = message;
    }
    public static <T, V> Pair<T,V> of(T num, V msg){
        return new Pair<T, V>(num,  msg);
    }

    @Override
    public int hashCode() {
        return (message == null ? 0 : message.hashCode()) + (numberPair == null ? 0 : numberPair.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        if(this.numberPair == null && this.message == null && ((Pair<?, ?>) obj).numberPair == null && ((Pair<?, ?>) obj).message == null) return true;
        if(this.numberPair == null || this.message == null) return false;
        else return this.numberPair.equals(((Pair<?, ?>) obj).numberPair) && this.message.equals(((Pair<?, ?>) obj).message);
    }
}
