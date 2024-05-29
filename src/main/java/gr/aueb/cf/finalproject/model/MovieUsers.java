package gr.aueb.cf.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie_users")
public class MovieUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @NonNull
    @Column(name = "username", nullable = false)
    private String username;

    @NotBlank
    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @NonNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "birthdate")
    private String birthDate;

    @JsonIgnore
    @OneToMany(mappedBy = "movieUser", cascade = CascadeType.ALL)
    private List<Movies> moviesList;

    @JsonIgnore
    @OneToMany(mappedBy = "movieUserLiked", cascade = CascadeType.ALL)
    private List<Likes> likes;

    @JsonIgnore
    @OneToMany(mappedBy = "movieUserHated", cascade = CascadeType.ALL)
    private List<Hates> hates;
}
