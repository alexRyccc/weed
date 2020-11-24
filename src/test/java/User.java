import com.weed.loginfo.annotion.Check;
import com.weed.loginfo.annotion.CheckField;

/**
 * @Author Alexryc
 * @Date 2020/10/30/030 16:13
 * @Version 1.0
 */
public class User {
    @CheckField(name = "用户名", check = Check.checks)
    private String name;
    @CheckField(name = "用户年龄", check = Check.checks)
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

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
