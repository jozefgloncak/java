package gloncak.jozef.springboot.restfulwebservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {

    @Autowired
    UserDAOService userDAOService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;


    @GetMapping("/jpa/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable int userId) {
        Optional<User> user = userRepository.findById(userId);

        return ResponseEntity.of(user);
    }

    @GetMapping("/jpa/2users/{id}")
    public User getUser2nd(@PathVariable int id) {
        User user = userDAOService.findUser(id);
        if (user == null) {
            throw new UserNotFoundException(id, ServletUriComponentsBuilder.fromCurrentRequest().build().toString());
        }
        return user;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        //return status and link where resource can be found
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/jpa/users/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userRepository.deleteById(userId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/jpa/users/{userId}/posts")
    public List<Post> getPosts(@PathVariable int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException(userId);
        }
        return user.get().getPosts();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/jpa/users/{userId}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int userId, @RequestBody Post post) {
        Optional<User> userOpt = userRepository.findById(userId);
        if(!userOpt.isPresent()) {
            throw new UserNotFoundException(userId);
        }

        User user = userOpt.get();
        post.setUser(user);
        postRepository.save(post);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/jpa/{postId}").buildAndExpand(post.getId()).toUri()).build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/jpa/users/{userId}/posts/{postId}")
    public ResponseEntity<Post> postDetails(@PathVariable int userId, @PathVariable int postId) {
        Optional<Post> post = userDAOService.findPost(userId, postId);
        if (!post.isPresent()) {
            throw new PostNotFoundException(postId,
                    ServletUriComponentsBuilder.fromCurrentRequest().build().toString());
        }
        return ResponseEntity.of(post);
    }
}
