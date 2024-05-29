package gr.aueb.cf.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @NonNull
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "duration")
    private int duration;

    @Column(name = "director")
    private String director;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "likes")
    private int likes;

    @Column(name = "hates")
    private int hates;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MovieUsers movieUser;

    @JsonIgnore
    @OneToMany(mappedBy = "movieLiked", cascade = CascadeType.ALL)
    private List<Likes> movieLikes;

    @JsonIgnore
    @OneToMany(mappedBy = "movieHated", cascade = CascadeType.ALL)
    private List<Hates> movieHates;
}
