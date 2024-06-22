package gr.aueb.cf.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieDTO {

    private String title;

    private String description;

    private String type;

    private int duration;

    private String director;

    private MovieUserDTO movieUserDTO;
}
