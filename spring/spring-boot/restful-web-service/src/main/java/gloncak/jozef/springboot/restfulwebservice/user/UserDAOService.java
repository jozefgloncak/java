package gloncak.jozef.springboot.restfulwebservice.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class UserDAOService implements UserDAO {

    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;
    private static int postCount = 6;

    private static final ArrayList<Post> postsOfUser1 = new ArrayList<>();
    private static final ArrayList<Post> postsOfUser2 = new ArrayList<>();
    private static final ArrayList<Post> postsOfUser3 = new ArrayList<>();

    static {
        postsOfUser1.add(new Post(1, "post1"));
        postsOfUser1.add(new Post(2, "post45"));
        postsOfUser2.add(new Post(3, "post14"));
        postsOfUser2.add(new Post(4, "post5"));
        postsOfUser3.add(new Post(5, "post7"));
        postsOfUser3.add(new Post(6, "post189"));

        users.add(new User(1, "Adam", LocalDate.now(), postsOfUser1));
        users.add(new User(2, "Eve", LocalDate.now(), postsOfUser2));
        users.add(new User(3, "Jack", LocalDate.now(), postsOfUser3));
    }

    @Override
    public List<User> findUsers() {
        return users;
    }

    public User saveUser(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public Optional<User> deleteUser(int id) {
        for(int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                return Optional.of(users.remove(i));
            }
        }
        return Optional.empty();
    }

    public User findUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public Optional<List<Post>> findPosts(int userId) {
        return users.stream()
                .filter(user -> user.getId() == userId)
                .map(user -> user.getPosts())
                .findFirst();
    }

    public Post saveUserPost(int userId, Post post) {
        User user = findUser(userId);

        if (user != null) {
            if (post.getId() == null) {
                post.setId(++postCount);
            }
            user.getPosts().add(post);
            return post;
        }
        return null;
    }

    public Optional<Post> findPost(int userId, int postId) {
        User user = findUser(userId);
        if (user != null) {
            return user.getPosts().stream().filter(post -> post.getId().equals(postId)).findFirst();
        }
        throw new UserNotFoundException(userId);
    }


}
