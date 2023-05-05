package task2006;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Как сериализовать?
Сделай так, чтобы сериализация класса Human была возможной.


Requirements:
1. Класс Human должен существовать внутри класса Solution.
2. Класс Human должен быть статическим.
3. Класс Human должен быть публичным.
4. Класс Human должен поддерживать интерфейс Serializable.*/

public class Solution {
    public static class Human implements Serializable{
        public String name;
        public List<String> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, String... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public String toString() {
            return "Human{" +
                    "name='" + name + '\'' +
                    ", assets=" + assets +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:/a.txt"));
        Human human = new Human("John", "car", "home");

        oos.writeObject(human);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("E:a.txt"));
        Human human1 = (Human) ois.readObject();
        System.out.println(human1);
    }
}
