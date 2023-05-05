package task2011;

import java.io.*;

/* 
Externalizable для апартаментов
Реализуй интерфейс Externalizable в классе Apartment.


Requirements:
1. Класс Solution.Apartment должен поддерживать интерфейс Externalizable.
2. В классе Solution.Apartment должен быть реализован метод writeExternal с одним параметром типа ObjectOutput.
3. В классе Solution.Apartment должен быть реализован метод readExternal с одним параметром типа ObjectInput.
4. В методе writeExternal, на полученном в качестве параметра объекте типа ObjectOutput
должен быть вызван метод writeObject с параметром address.
5. В методе writeExternal, на полученном в качестве параметра объекте типа ObjectOutput
 должен быть вызван метод writeInt с параметром year.
6. Метод readExternal должен корректно восстанавливать из ObjectInput значение поля address.
7. Метод readExternal должен корректно восстанавливать из ObjectInput значение поля year.*/

public class Solution {

    public static class Apartment implements Externalizable {

        private String address;
        private int year;

        /**
         * Mandatory public no-arg constructor.
         */
        public Apartment(){}

        public Apartment(String addr, int y) {
            address = addr;
            year = y;
        }

        /**
         * Prints out the fields used for testing!
         */
        public String toString() {
            return ("Address: " + address + "\n" + "Year: " + year);
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeUTF(this.address);
            out.writeInt(this.year);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            this.address = in.readUTF();
            this.year = in.readInt();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File OUTPUT_FILE = new File("E:/a.txt");
        Apartment apartment = new Apartment("Chelyabinsk", 1987);

        FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_FILE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        apartment.writeExternal(objectOutputStream);

        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();

        FileInputStream fileInputStream
                = new FileInputStream(OUTPUT_FILE);
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);

        Apartment apartment1 = new Apartment();
        apartment1.readExternal(objectInputStream);

        System.out.println(apartment1);

        objectInputStream.close();
        fileInputStream.close();
    }
}
