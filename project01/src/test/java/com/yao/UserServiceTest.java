package com.yao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试类，测试 UserService 业务方法
 */
@DisplayName("测试身份证信息类")
public class UserServiceTest {
    @DisplayName("测试年龄")
    @Test
    public void testGetAge() {
        int age = new UserService().getAge("320123199001014567");
        System.out.println("年龄：" + age);
    }

    @DisplayName("测试性别")
    @Test
    public void testGetGender1() {
        String gender = new UserService().getGender("320123199001014567");
        // 断言
        assertEquals("女", gender, "性别计算错误");
    }

    @DisplayName("参数化测试性别")
    @ParameterizedTest
    @ValueSource(strings = {"320123199001014567", "320123199210104599"})
    public void testGetGender2(String idCard) {
        String gender = new UserService().getGender(idCard);
        System.out.println("性别：" + gender);
    }
}
