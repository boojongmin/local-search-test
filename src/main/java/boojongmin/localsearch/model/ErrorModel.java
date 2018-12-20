package boojongmin.localsearch.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ErrorModel<T> {
    private String cause;
    private T data;

}
