import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenericCollection {
    public static void main(String[] args) {
        List<String> collectionString = new ArrayList<>();
        collectionString.add("45");
        collectionString.add("123");
        collectionString.add("456");

        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        };

        ClassOfGeneric<String, Integer> classOfGeneric = new ClassOfGeneric<>(function, collectionString);
        System.out.println(classOfGeneric.getCollectionV());
    }
}

class ClassOfGeneric<T, V>{
    private List<V> collectionV;

    public List<V> getCollectionV() {
        return collectionV;
    }
    public void setCollectionV(List<V> collectionV) {
        this.collectionV = collectionV;
    }

    ClassOfGeneric(Function<T, V> function, List<T> collectionT) {
        this.collectionV = collectionT.stream().map(function).collect(Collectors.toList());
    }
}


