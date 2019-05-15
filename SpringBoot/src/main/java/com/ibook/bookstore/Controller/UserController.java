package com.ibook.bookstore.Controller;

import com.ibook.bookstore.Entity.Address;
import com.ibook.bookstore.Entity.User;
import com.ibook.bookstore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/api/test/user/{name}")
    public User getUserByName(@PathVariable("name") String name) {
        return userService.findUserByName(name);
    }

    @GetMapping("/api/user")
    public User findUserByEmail(@RequestParam(name = "email")String email) {
        return userService.findUserByEmail(email);
    }

    @PostMapping("/api/public/register")
    public User register(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/api/user/{name}/addresses/add")
    public User addAddress(@PathVariable("name") String name, @RequestBody Address address){
        return userService.addAddress(name, address);
    }

    @PutMapping("/api/user/{name}/addresses/modify")
    public User updateAddress(@PathVariable("name")String name, @RequestBody Map<String, String> addressData) {
        System.out.println(name);
        return userService.updateAddress(addressData);
    }

    @DeleteMapping("/api/user/{name}/addresses/delete")
    public void deleteAddress(@PathVariable("name")String name,
                              @RequestParam("id") String id){
        userService.deleteAddress(id);
    }

    @GetMapping("/api/admin/users/all")
    public Page<User> findUserByPage(@RequestParam(name = "page", defaultValue = "0") Integer page) {
        return userService.findUserByPage(page, 10);
    }

    @DeleteMapping("/api/admin/users/delete")
    public void deleteUser(@RequestParam(name = "name")String name) {
        userService.deleteUser(name);
    }

    @PutMapping("/api/admin/users/modify")
    public User updateUser(@RequestBody Map<String ,String> data) {
        return userService.updateUser(data);
    }

}