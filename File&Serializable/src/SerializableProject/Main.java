package SerializableProject;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializableClass serializableClass = new SerializableClass("asd", 18, true);
        outputStream(serializableClass);
        inputStream();
    }

    private static void outputStream(SerializableClass serializableClass) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("d:/out.txt"));

        int countFields = 0;
        List<String> listFields = new ArrayList<>();
        Field[] fields = serializableClass.getClass().getDeclaredFields();
        for (Field field : fields)
            if (!Modifier.toString(field.getModifiers()).contains("transient") && !Modifier.toString(field.getModifiers()).contains("final")) {
                countFields++;
                listFields.add(field.getName());
            }

        objectOutputStream.writeInt(countFields);
        objectOutputStream.writeObject(listFields);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    private static void inputStream() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream( new FileInputStream("d:/out.txt"));
        System.out.println("Количество полей: " + objectInputStream.readInt());
        System.out.println("Поля: " + objectInputStream.readObject());
    }
}

class SerializableClass implements Serializable {

    private static final long SerialVersionUID = 1;

    private transient String name;
    private int age;
    private boolean sex;

    public SerializableClass(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public boolean isSex() {
        return sex;
    }
    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "SerializableClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
