package boojongmin.localsearch.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LocationSearchModel {
    private List<DocumentModel> documents;
    private MetaModel meta;
}
