package gr.aueb.cf.finalproject.service.exceptions;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorMessageResponse {

    private String message;

    @Override
    public String toString() {
        return "Attention!! " + message;
    }
}
