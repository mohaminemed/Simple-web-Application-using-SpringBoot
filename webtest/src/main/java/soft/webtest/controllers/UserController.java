package soft.webtest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soft.webtest.Database.UserDataAccess;
import soft.webtest.models.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userDataAccess.getAllUsers();
    }

    @Autowired
    UserDataAccess userDataAccess;

    @PostMapping("/setUser")
    public void setUser(@RequestBody User user) {
        System.out.println("ID : " + user.getId());
        System.out.println("FN : " + user.getFirstname());
        System.out.println("LN : " + user.getLastname());
        userDataAccess.insert(user);

    }

    @GetMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable int id) {
        userDataAccess.delete(id);
    }

    @PostMapping("/updateUser")
    public void updateUser(@RequestBody User user) {
        userDataAccess.update(user);
    }


    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable String id) {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "bouchiha", "med"));
        users.add(new User(1, "bouchiha", "mohamed"));
        users.add(new User(1, "bouchiha", "amine"));
        User user = users.stream().filter(u -> u.getId() == Integer.parseInt(id)).findAny().orElse(null);
        return user;
    }

    @GetMapping("/callUsers")
    public List<User> callUsers() {
        return userDataAccess.callUsers();
    }

    @GetMapping("/callUserById/{id}")
    public String callUserById(@PathVariable String id) {
        {
            return userDataAccess.callUserById(Integer.parseInt(id));
        }

    }
}
