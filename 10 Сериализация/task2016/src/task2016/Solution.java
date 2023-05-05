package task2016;



/* 
Минимум изменений
Используя минимум изменений кода сделайте так, чтобы сериализация класса C стала возможной.


Requirements:
1. В классе Solution должен существовать класс A.
2. В классе Solution должен существовать класс B.
3. В классе Solution должен существовать класс C.
4. Класс B должен быть потомком класса A.
5. Класс C должен быть потомком класса B.
6. Класс A должен поддерживать интерфейс Serializable.
7. Класс B не должен явно поддерживать интерфейс Serializable.
8. Класс C не должен явно поддерживать интерфейс Serializable.*/

import java.io.*;

public class Solution implements Serializable{
    public class A implements Serializable {
        String name = "A";

        public A(String name) {
            this.name += name;
        }

        @Override
        public String toString() {
            return name;
        }


    }

    public class B extends A {
        String name = "B";

        public B(String name) {
            super(name);
            this.name += name;
        }
    }

    public class C extends B {
        String name ;

        public C(String name) {
            super(name);
            this.name = name;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        C c = new Solution().new C("C");
        System.out.println(c);

        File OUTPUT_FILE = new File("E:/a.txt");

        FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_FILE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(c);

        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();

        FileInputStream fileInputStream
                = new FileInputStream(OUTPUT_FILE);
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);

        C c1 = (C) objectInputStream.readObject();

        System.out.println(c1);

        objectInputStream.close();
        fileInputStream.close();
    }
}
