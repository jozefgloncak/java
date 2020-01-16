package gloncak.jozef.springboot.restfulwebservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResource {

    @Autowired
    UserDAOService userDAOService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userDAOService.findUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = userDAOService.findUser(id);

        return ResponseEntity.of(Optional.ofNullable(user));
    }

    @GetMapping("/2users/{id}")
    public User getUser2nd(@PathVariable int id) {
        User user = userDAOService.findUser(id);
        if (user == null) {
            throw new UserNotFoundException(id, ServletUriComponentsBuilder.fromCurrentRequest().build().toString());
        }
        return user;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        userDAOService.saveUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        //return status and link where resource can be found
        return      ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/users/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable int userId) {
        return ResponseEntity.of(userDAOService.deleteUser(userId));

    }

    @RequestMapping(method = RequestMethod.GET, path = "/users/{userId}/posts")
    public ResponseEntity<List<Post>> getPosts(@PathVariable int userId) {
        return ResponseEntity.of(userDAOService.findPosts(userId));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/users/{userId}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int userId, @RequestBody Post post) {
        Post postStored = userDAOService.saveUserPost(userId, post);
        if (postStored == null) {
            throw new UserNotFoundException(userId, ServletUriComponentsBuilder.fromCurrentRequest().toString());
        }
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{postId}").buildAndExpand(post.getId()).toUri()).build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users/{userId}/posts/{postId}")
    public ResponseEntity<Post> postDetails(@PathVariable int userId, @PathVariable int postId) {
        Optional<Post> post = userDAOService.findPost(userId, postId);
        if (!post.isPresent()) {
            throw new PostNotFoundException(postId,
                    ServletUriComponentsBuilder.fromCurrentRequest().build().toString());
        }
        return ResponseEntity.of(post);
    }
}
