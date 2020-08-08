package com.tinytongtong.androidstudy.java.equalsandhashcode;

import java.util.Objects;

/**
 * @Description: equals方法测试
 * @Author tinytongtong
 * @Date 2020/8/8 6:10 PM
 * @Version
 */
public class EqualsTest {
    public static void main(String[] args) {
        Employee e = new Employee();
        e.name = "abc";
        e.salary = 100;
        e.hireDay = "20200806";
        Manager m = new Manager();
        m.name = "abc";
        m.salary = 100;
        m.hireDay = "20200806";
        m.bonus = 10086;

        System.out.println("e.equals(m):" + e.equals(m));
        System.out.println("m.equals(e):" + m.equals(e));
    }
}

class Employee {
    public String name;
    public int salary;
    public String hireDay;

    @Override
    public boolean equals(Object o) {
        // a quick test to be if the objects are identical
        if (this == o) return true;

        // must return false if the explicit parameter is null
        if (o == null) return false;

        // if the classes don't match, they can't be equal
//        if (getClass() != o.getClass()) return false;

        // 破坏对称性
        if (!(o instanceof Employee)) return false;

        // now we know o is a non-null Employee
        Employee employee = (Employee) o;

        // test whether the fields have identical values
        return salary == employee.salary &&
                Objects.equals(name, employee.name) &&
                Objects.equals(hireDay, employee.hireDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, hireDay);
    }
}

class Manager extends Employee {
    public int bonus;

    @Override
    public boolean equals(Object o) {
        // if the classes don't match, they can't be equal
//        if (getClass() != o.getClass()) return false;

        // 破坏对称性
        if (!(o instanceof Manager)) return false;

        if (!super.equals(o)) return false;

        Manager manager = (Manager) o;
        return bonus == manager.bonus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bonus);
    }
}
