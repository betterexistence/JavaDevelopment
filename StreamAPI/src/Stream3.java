import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sun.security.util.ArrayUtil;

public class Stream3 {
    public static void main(String[] args) {

        //exercise 1
        List<String> list2 =  new ArrayList<>(Arrays.asList("q", "w", "e", "z", "x", "c"));
        list2.stream();
        Stream.of("q", "w", "e", "z", "x", "c");

        //exercise 2
        Stream.of("q", "w", "e", "z", "x", "c")
                .forEach(System.out::println);
        //exercise 3
        Stream.of("q", "w", "e", "z", "x", "c")
                .map(el -> el.toUpperCase())
                .forEach(System.out::println);
        //exercise 4
        Stream.concat(
                        Stream.of("q", "w", "e", "z", "x", "c"),
                        Stream.of("x", "b", "c", "a", "d", "c")
                )
                .distinct()
                .map(el -> el.toUpperCase())
                .forEach(System.out::print);
        //exercise 5
        Stream.of(12,6,37,23,78,3,42,15,5,19)
                .skip(3)
                .limit(5)
                .forEach(System.out::println);
        //exercise 6
        Stream.of(12,6,37,23,78,3,42,15,5,19)
                .sorted(Collections.reverseOrder())   //78 42 37 23 19 15 12 6 5 3
                .skip(3)
                .limit(5)
                .forEach(System.out::println);
        //exercise 7
        System.out.println(Stream.of("micchbel", "vblex", "bvAbna", "jackaa", "robeaat")
                .peek(System.out::println).collect(Collectors.toList()).stream()
                .filter(s -> {
                    for(int i = 0; i < s.toCharArray().length-1; i++)
                        for (int j = 0; j < s.toCharArray().length; j++)
                            if (s.charAt(i) != s.charAt(j))
                                if(Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j)))
                                    return true;
                    return false;
                })
                .findAny().orElse("not found"));
    }
}
