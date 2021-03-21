package ee.taltech.iti0202.personstatistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvPersonMapper {

    public List<Person> mapToPersons(String path) {
        Path p = Path.of(path);
        List<List<String>> result;
        List<Person> persons = new LinkedList<>();
        try {
            result = Files.readAllLines(Paths.get(path))
                    .stream()
                    .map(line -> Arrays.asList(line.split(",")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new CsvToPersonMappingException("No such file", new IOException());
        }
        for (List<String> person : result.subList(1, result.size())) {
            Person person1 = new PersonBuilder()
                    .withName(person.get(0))
                    .withLastName(person.get(1))
                    .withAge(Integer.parseInt(person.get(2)))
                    .withGender(Person.Gender.valueOf(person.get(3)))
                    .withHeight(Double.valueOf(person.get(4)))
                    .withOccupation(person.get(5))
                    .withNation(person.get(6))
                    .build();
            persons.add(person1);
        }
        return persons;
    }

}
