package boojongmin.locationsearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SameNameModel {
    private String keyword;
    private List<String> region;
    @JsonProperty("selected_region")
    private String selectedRegion;

}
