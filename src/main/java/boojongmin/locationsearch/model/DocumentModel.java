package boojongmin.locationsearch.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DocumentModel {
    @JsonProperty("address_name")
    private String addressName;
    @JsonProperty("category_group_code")
    private String categoryGroupCode;
    @JsonProperty("category_group_name")
    private String categoryGroupName;
    @JsonProperty("category_name")
    private String categoryName;
    @JsonProperty("distance")
    private double distance;
    private long id;
    private String phone;
    @JsonProperty("place_name")
    private String placeName;
    @JsonProperty("place_url")
    private String place_url;
    @JsonProperty("road_address_name")
    private String road_address_name;
    private double x;
    private double y;
}
