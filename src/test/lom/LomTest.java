package test.lom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

class Dog {
    private String name;
    private int age;
}

public class LomTest {

    public static void main(String[] args) {
        Dog d = new Dog(null, 0);
        d.setName("이름변경");
        System.out.println(d.getName());
    }
}
