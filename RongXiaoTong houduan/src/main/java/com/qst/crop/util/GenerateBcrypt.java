package com.qst.crop.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateBcrypt {
    public static void main(String[] args) {
        // 你的明文密码
        String plainPassword = "ljy200312275797";
        // 生成BCrypt哈希
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String bcryptHash = encoder.encode(plainPassword);
        // 输出的哈希值就是要存到数据库的内容
        System.out.println("BCrypt哈希：" + bcryptHash);
    }
}