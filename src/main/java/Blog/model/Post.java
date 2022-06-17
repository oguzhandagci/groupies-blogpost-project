package Blog.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name= "post")
public class Post {
    public Post() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //    Form validation syntax for the title to not be empty
    @NotEmpty(message = "Please fill out title of the post.")
    @Column(name="title")
    private String title;


    //Form validation syntax for the content to not be empty
    @NotEmpty(message = "Please fill out content of the post.")
    @Column(name = "body", columnDefinition = "TEXT", nullable = false)
        private String body;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date", nullable = false,updatable = false)
    private Date currentDate;
@NotNull
@Column(name = "username")
private String username;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //Adding a user variable, should be assigned in new PostContoller createnewpost feature


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", creationDate=" + currentDate +
//                ", comments=" + comments +
//                ", comments=" + comments.stream().map(Comment::toString).collect(Collectors.joining(",")) +
//                ", username=" + user.getUsername() +
//                ", user=" + user + // this way it is making the inf loop
                '}';
    }
}