package com.example.demo0605.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo0605.entity.User;
import com.example.demo0605.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserMapper userMapper;
    @RequestMapping("/add")
    public String add(Model model, @ModelAttribute User user){
        System.out.println(user);
        String result="";
        LambdaQueryWrapper<User> userLambdaQueryWrapper=new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getId,user.getId());
        User user1=userMapper.selectOne(userLambdaQueryWrapper);
        if(user1!=null){
            result="添加失败！"+user.getId()+"号已经存在！";
        }else {
        userMapper.insert(user);
            result="添加成功！";
        }
        model.addAttribute("r",result);
        return "index";
    }
}
