import java.util.Arrays;
import java.util.stream.Stream;

public class EmployeeData {

    public static Stream<Employee> readAll(){
        return Stream.of(
                new Employee(100,"Mike","mike@cydeo.com", Arrays.asList("2029124536","2023464768")),
                new Employee(101,"Ozzy","ozzy@cydeo.com", Arrays.asList("7039124536","7033464768")),
                new Employee(102,"Peter","peter@cydeo.com", Arrays.asList("5169124536","5163464768"))

        );
    }
}
