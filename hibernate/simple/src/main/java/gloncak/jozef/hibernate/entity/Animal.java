package gloncak.jozef.hibernate.entity;

public class Animal {
    private int id;
    private String name;
    private short age;
    private boolean gender;

    public Animal() {
    }

    public Animal(String name, short age, boolean gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", gender=" + gender + '}';
    }
}
