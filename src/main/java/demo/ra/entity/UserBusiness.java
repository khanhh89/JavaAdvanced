package demo.ra.entity;

import demo.ra.business.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserBusiness {
    private static UserBusiness instance;
    private List<User> userList = new ArrayList<>();

    private UserBusiness() {}

    public static UserBusiness getInstance() {
        if (instance == null) {
            instance = new UserBusiness();
        }
        return instance;
    }

    public List<User> getAll() {
        return userList;
    }

    public boolean add(User user) {
        if (userList.stream().anyMatch(u -> u.getUserId().equals(user.getUserId()))) {
            return false;
        }
        return userList.add(user);
    }

    public Optional<User> findById(String id) {
        return userList.stream().filter(u -> u.getUserId().equalsIgnoreCase(id)).findFirst();
    }

    public List<User> searchByName(String name) {
        return userList.stream()
                .filter(u -> u.getUserName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean delete(String id) {
        return userList.removeIf(u -> u.getUserId().equalsIgnoreCase(id));
    }

    public List<User> filterAdmin() {
        return userList.stream()
                .filter(u -> "ADMIN".equalsIgnoreCase(u.getRole()))
                .collect(Collectors.toList());
    }

    public List<User> getSortedByScore() {
        return userList.stream()
                .sorted(Comparator.comparingDouble(User::getScore).reversed())
                .collect(Collectors.toList());
    }
}
