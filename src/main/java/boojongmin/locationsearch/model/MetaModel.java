package boojongmin.locationsearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MetaModel {
    @JsonProperty("is_end")
    private boolean isEnd;
    @JsonProperty("pageable_count")
    private int pageableCount;
    @JsonProperty("same_name")
    private SameNameModel sameName;
    @JsonProperty("total_count")
    private int totalCountCount;
}
