package task2009;

import java.io.*;

/* 
Как сериализовать static?
Сделай так, чтобы сериализация класса ClassWithStatic была возможной.


Requirements:
1. Класс ClassWithStatic должен существовать внутри класса Solution.
2. Класс ClassWithStatic должен быть статическим.
3. Класс ClassWithStatic должен быть публичным.
4. Класс ClassWithStatic должен поддерживать интерфейс Serializable.*/

public class Solution {
    public static class ClassWithStatic implements Serializable {
        final long serialVersionUID = 1L;
        public static String staticString = "This is a static test string";
        public int i;
        public int j;
        public static void serializeStatic(ObjectOutputStream oos) throws IOException {
            oos.writeUTF(staticString);
        }
        public static void deserializeStatic(ObjectInputStream ois) throws IOException{
            ois.readUTF();
        }

        public ClassWithStatic(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "ClassWithStatic{" +
                    " i=" + i +
                    ", j=" + j +
                    "staticString = " + staticString +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClassWithStatic cws = new ClassWithStatic(1,2);
        String path = "E:/a.txt";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        ClassWithStatic.serializeStatic(oos);
        oos.writeObject(cws);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        ClassWithStatic.deserializeStatic(ois);
        ClassWithStatic cws2 = (ClassWithStatic) ois.readObject();
        System.out.println(cws2);

        oos.close();
        ois.close();
    }
}
