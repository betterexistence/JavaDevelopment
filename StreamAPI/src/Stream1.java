import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream1 {
    public static void main(String[] args) {
        System.out.println(Stream.of("michael", "alex", "anna", "jack", "robert")
                .map(Person::new)
                .mapToInt(person -> person.age)
                        .peek(age -> System.out.print(age + " "))
                .sum());
        System.out.println(Stream.of("michMbel", "blex", "bnna", "jack", "robert")
                .map(Person::new)
                .peek(System.out::println)
                .map(person -> person.name.toLowerCase() + person.surname.toLowerCase())
                .map(p -> p.chars().mapToObj(c -> (char) c).toArray(Character[]::new))
                .flatMap(Arrays::stream)
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey());
    }
    public static class Person{
        private final String name;
        private final String surname;
        private final int age;
        public int getAge() {
            Random r = new Random();
            return new Random().nextInt(40) + 20;
        }
        public String getSurname() {
            Random random = new Random();
            int surnameLen = (int) (3 + Math.random() * 5);
            StringBuilder surname = new StringBuilder();
            for(int i = 0; i < surnameLen; i++){
                char c = (char)(random.nextInt(26) + 'a');
                surname.append(c);
            }
            return surname.toString();
        }
        public Person(String name){
            this.name = name;
            this.surname = getSurname();
            this.age = getAge();
        }
        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}

