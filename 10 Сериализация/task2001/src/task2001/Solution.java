package task2001;

import java.io.*;
import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/* 
Читаем и пишем в файл: Human
Часто необходимо сохранять состояние работы программы.
До появления сериализации каждый делал это своим способом. В этой задаче нужно сохранить в файл
состояние работы программы и вычитать состояние из файла без использования сериализации.

Реализуй логику записи в файл и чтения из файла для класса Human.
Поле name в классе Human не может быть пустым.
Метод main реализован только для тебя и не участвует в тестировании.


Requirements:
1. Логика чтения/записи реализованная в методах save/load должна работать корректно в случае,
если список assets пустой.
2. Логика чтения/записи реализованная в методах save/load должна работать корректно в случае,
если поле name и список assets не пустые.
3. Класс Solution.Human не должен поддерживать интерфейс Serializable.
4. Класс Solution.Human должен быть публичным.
5. Класс Solution.Human не должен поддерживать интерфейс Externalizable.*/

public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("E:/a.txt", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99),
                    new Asset("car", 2999.99));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();
            System.out.println(somePerson.equals(ivanov));

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }

    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public void save(OutputStream outputStream) throws Exception {
            List<Integer> bytes = new ArrayList<>();
            char[] charsName = this.name.toCharArray();
            for (char c: charsName){
                bytes.add((int) c);
            }
            bytes.add(33);
            if (this.assets.size() > 0){
                for (Asset asset: assets){
                    char[] chars = asset.getName().toCharArray();
                    for (char c: chars){
                        bytes.add((int) c);
                    }
                    bytes.add(33);
                    char[] price = Double.toString(asset.getPrice()).toCharArray();
                    for (int a : price){
                        bytes.add(a);
                    }
                    bytes.add(33);
                }
            }
            for (int b: bytes){
                outputStream.write(b);
            }
        }

        public void load(InputStream inputStream) throws Exception {
            byte[] buffer = new byte[256];
            while (inputStream.available() > 0){
                inputStream.read(buffer);
            }

            StringBuilder sb= new StringBuilder();
            for (int a: buffer){
                if (a != 0) sb.append((char) a);
            }
            String str = sb.toString();
            String[] strings = str.split("!");
            this.name = strings[0];
            List<Asset> ass = null;
            if (strings.length > 1){
                ass = new ArrayList<>();
                for (int i = 1; i < strings.length; i+=2) {
                    Asset asset = new Asset(strings[i], Double.parseDouble(strings[i + 1]));
                    ass.add(asset);
                }
            }
            if (ass != null)
                this.assets.addAll(ass);
        }
    }
}
