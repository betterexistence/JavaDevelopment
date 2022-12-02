import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.rangeClosed;

public class Stream2 {
    public static void main(String[] args) {

        List<String> list = Arrays.stream(args)
                .filter(s -> s.length() <= 2)
                .collect(Collectors.toList());

            IntStream.of(1,3,2,3,5,6,7,78,5,31)
                    .map(value -> value + 11)
                    .filter(value -> value > 15)
                    .limit(3)
                    .forEach(System.out::println);

        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 = list1.parallelStream()
                .filter(x -> x - 3 < 5)
                .map(x -> x * 3)
                .collect(Collectors.toList());
        System.out.println(list2);

        List<Integer> list3 = Collections.singletonList(IntStream.range(0, 10)
                .parallel()
                .map(x -> x * 10)
                .sum());
        System.out.println(list3);

        List<Integer> ints = new ArrayList<>();
        IntStream.range(0, 100)
                .parallel()
                .forEach(i -> ints.add(i));
        System.out.println(ints.size());

        Stream.generate(() -> Math.random() * Math.random())
                .limit(10)
                .forEach(System.out::println);

        Stream.iterate(2, x -> x + 6)
                .filter(x -> x < 40)
                .limit(5)
                .forEach(System.out::println);

        Stream.Builder<Integer> sb = Stream.<Integer>builder()
                .add(5)
                .add(2);
        for(int i = 2; i <=8; i *=2){
          sb.accept(i);
        }
        sb.build().forEach(System.out::println);

        Stream.of("11","12","32")
                .map(x -> Integer.parseInt(x, 16))
                .forEach(System.out::println);

        Stream.of(1,2,3,4,5,6,7,8,9)
                .flatMap(x -> {
                    switch (x % 3){
                        case 0: return Stream.of(x, x*x, x*x*2);
                        case 1: return Stream.of(x);
                        default: return Stream.empty();
                    }
                })
                .forEach(System.out::println);

        //Skip
        IntStream.range(0, 10)
                .limit(5)
                .skip(4)
                .forEach(System.out::println);

        //sorted
        Stream.of(120, 410, 85, 32, 314, 12)
                .sorted()
                .forEach(System.out::println);

        Stream.of(120, 410, 85, 32, 314, 12)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        //distinct
        IntStream.concat(
                        IntStream.range(2, 5),
                        IntStream.range(0, 4))
                .distinct()
                .forEach(System.out::println);

        //peek
        Stream.of(0, 3, 0, 0, 5)
                .peek(x -> System.out.format("before distinct: %d%n", x))
                .distinct()
                .peek(x -> System.out.format("after distinct: %d%n", x))
                .map(x -> x * x)
                .forEach(x -> System.out.format("after map: %d%n", x));

        //boxed
        DoubleStream.of(0.1, Math.PI)
                .boxed()
                .map(Object::getClass)
                .forEach(System.out::println);

        //count
        long count = IntStream.range(0, 10)
                .flatMap(x -> IntStream.range(0, x))
                .count();
        System.out.println(count);

        //rangeClosed
        System.out.println(
                rangeClosed(-3, 6)
                        .count()
        );

        System.out.println(
                Stream.of(0, 2, 9, 13, 5, 11)
                        .map(x -> x * 2)
                        .filter(x -> x % 2 == 1)
                        .count()
        );

        //collect
        List<Integer> listCollect = Stream.of(1, 2, 3)
                .collect(Collectors.toList());
        System.out.println(listCollect);

        String s = Stream.of(1, 2, 3)
                .map(String::valueOf)
                .collect(Collectors.joining("-", "<", ">"));
        System.out.println(s);

        List<String> listCollect2 = Stream.of("a", "b", "c", "d")
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(listCollect2);

        //Object[]toArray()
        String[] elements = Stream.of("a", "b", "c", "d")
                .toArray(String[]::new);
        System.out.println(elements);

        //reduce
        int sum = Stream.of(1, 2, 3, 4, 5)
                .reduce(10, (acc, x) -> acc + x);
        System.out.println(sum);

        //optinalreduce
        Optional<Integer> result = Stream.<Integer>empty()
                .reduce((acc, x) -> acc + x);
        System.out.println(result.isPresent()); //false

        Optional<Integer> sum1 = Stream.of(1, 2, 3, 4, 5)
                .reduce((acc, x) -> acc + x);
        System.out.println(sum1.get());

        int product = IntStream.range(0, 10)
                .filter(x -> x % 4 == 0)
                .reduce((acc, x) -> acc * x)
                .getAsInt();
        System.out.println(product);

        //min/max
        int min = Stream.of(20, 11, 45, 78, 13)
                .min(Integer::compare).get();
        System.out.println(min);

        int max = Stream.of(20, 11, 45, 78, 13)
                .max(Integer::compare).get();
        System.out.println(max);


    }
}
