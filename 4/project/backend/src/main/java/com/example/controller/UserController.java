// backend/src/main/java/com/example/controller/UserController.java
package com.example.controller;

import com.example.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    // 模拟数据
    private Map<Integer, User> userMap = new HashMap<>();

    @GetMapping("/info")
    public Map<String, Object> getUserInfo() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "success");
        result.put("data", Map.of("name", "张三", "age", 25, "email", "zhangsan@example.com"));
        return result;
    }

    @GetMapping("/list")
    public Map<String, Object> getUserList() {
        Map<String, Object> result = new HashMap<>();
        List<User> list = Arrays.asList(
                createUser(1, "张三", "zhangsan@test.com"),
                createUser(2, "李四", "lisi@test.com"));
        result.put("code", 200);
        result.put("data", list);
        return result;
    }

    private User createUser(int id, String name, String email) {
        User user = new User();
        user.setId(id);
        user.setUsername(name);
        user.setEmail(email);
        return user;
    }
}