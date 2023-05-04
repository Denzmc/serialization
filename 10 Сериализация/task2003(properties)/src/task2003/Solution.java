package task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/* 
Знакомство с properties
В методе main() происходит считывание пути к файлу с консоли и заполнение runtimeStorage данными из файла.
Тебе необходимо в методе save() реализовать логику записи в файл для карты runtimeStorage,
а в методе load() - логику чтения из файла для runtimeStorage.
Файл должен быть в формате .properties. Комментарии в файле игнорируй.
Про .properties прочитай в вики.
Подсказка: используй объект класса Properties.


Requirements:
1. Метод save() должен сохранять карту runtimeStorage в параметр outputStream.
2. Метод load() должен восстанавливать состояние карты runtimeStorage из параметра inputStream.*/

public class Solution {

    public static Map<String, String> runtimeStorage = new HashMap<>();

    public static void save(OutputStream outputStream) throws Exception {
        Properties props = new Properties();
        for (String key:  runtimeStorage.keySet()){
            props.setProperty(key, runtimeStorage.get(key));
        }
        props.store(outputStream, null);
        outputStream.close();
    }

    public static void load(InputStream inputStream) throws IOException {
        Properties props = new Properties();
        props.load(inputStream);
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileInputStream fis = new FileInputStream(reader.readLine());
             FileOutputStream fos = new FileOutputStream("E:/data.properties") ) {

//            load(fis);

            runtimeStorage.put("name","Victory");
            runtimeStorage.put("age","18");
            runtimeStorage.put("salary","199");

            save(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(runtimeStorage);
    }
}
