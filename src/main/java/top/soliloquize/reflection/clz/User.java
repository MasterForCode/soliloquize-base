package top.soliloquize.reflection.clz;

/**
 * @author wb
 * @date 2020/2/24
 */
//@Data
public class User {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
