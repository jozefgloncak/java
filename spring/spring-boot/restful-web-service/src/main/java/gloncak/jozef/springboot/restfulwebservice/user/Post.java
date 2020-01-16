package gloncak.jozef.springboot.restfulwebservice.user;

public class Post {
    private Integer id;
    private String post;

    public Post(Integer id, String post) {
        this.post = post;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
