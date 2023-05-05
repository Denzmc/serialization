package task2017;

import java.io.*;

/* 
Десериализация
На вход подается поток, в который записан сериализованный объект класса A либо класса B.
Десериализуй объект в методе getOriginalObject так,
чтобы в случае возникновения исключения было выведено сообщение на экран и возвращен null.
Реализуй интерфейс Serializable где необходимо.


Requirements:
1. Класс B должен быть потомком класса A.
2. Класс A должен поддерживать интерфейс Serializable.
3. Класс B не должен явно поддерживать интерфейс Serializable.
4. Метод getOriginalObject должен возвращать объект типа A полученный из потока ObjectInputStream.
5. Метод getOriginalObject должен возвращать null, если при попытке десериализации не был получен объект типа A.
6. Метод getOriginalObject должен возвращать null, если при попытке десериализации возникло исключение.*/

public class Solution implements Serializable{

    public A getOriginalObject(ObjectInputStream objectStream) throws IOException {
        try {
            A a = (A) objectStream.readObject();
        } catch (Exception e) {
            return null;
        } finally {
            objectStream.close();
        }

        return null;
    }

    public class A implements Serializable{
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) throws IOException {
        File OUTPUT_FILE = new File("E:/a.txt");
        A a = new Solution().new A();
        FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_FILE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(a);

        FileInputStream fileInputStream = new FileInputStream(OUTPUT_FILE);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Solution s = new Solution();
        A a1 = s.getOriginalObject(objectInputStream);
    }
}
