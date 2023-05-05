package task2021;

import java.io.*;

/* 
Сериализация под запретом
Запрети сериализацию класса SubSolution используя NotSerializableException.
Сигнатуры классов менять нельзя.


Requirements:
1. Класс Solution должен поддерживать интерфейс Serializable.
2. Класс SubSolution должен быть создан внутри класса Solution.
3. Класс SubSolution должен быть потомком класса Solution.
4. При попытке сериализовать объект типа SubSolution
 должно возникать исключение NotSerializableException.
5. При попытке десериализовать объект типа SubSolution
должно возникать исключение NotSerializableException.*/

public class Solution implements Serializable {
    public static class SubSolution extends Solution {
        private void writeObject(ObjectOutputStream out) throws IOException, InterruptedException {
            throw new NotSerializableException();
        }
        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            throw new NotSerializableException();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SubSolution sub = new SubSolution();
        FileOutputStream fileOutput = new FileOutputStream("E:/a.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);

        outputStream.writeObject(sub);

        FileInputStream fiStream = new FileInputStream("E:/a.txt");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);

        SubSolution loadedObject = (SubSolution) objectStream.readObject();

        fiStream.close();
        objectStream.close();
    }
}
