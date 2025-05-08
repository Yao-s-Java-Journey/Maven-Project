package com.yao;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class UserService {
    /**
     * 给定一个身份证号，计算出该用户的年龄
     * @param idCard 身份证号
     * @return 年龄
     */
    public Integer getAge(String idCard) {
        String birthday = idCard.substring(6, 14);
        LocalDate parse = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyyMMdd"));
        return Period.between(parse, LocalDate.now()).getYears();
    }

    /**
     * 给定一个身份证号，计算用户性别
     * @param idCard 身份证号
     * @return 性别
     */
    public String getGender(String idCard){
        return Integer.parseInt(idCard.substring(16, 17)) % 2 == 0 ? "女" : "男";
    }
}
