import java.util.Arrays;
import java.util.List;

public class TaskCharacter {

    public static void main(String[] args) {

        List<String> words = Arrays.asList("Java","Apple","Honda","Developer");

        String x = "Java";
        System.out.println(x.length());

        words.stream()
                .map(String::length)
                .forEach(System.out::println);


    }
}
