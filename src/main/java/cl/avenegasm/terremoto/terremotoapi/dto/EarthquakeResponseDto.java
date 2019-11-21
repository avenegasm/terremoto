package cl.avenegasm.terremoto.terremotoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EarthquakeResponseDto implements Serializable {

    private String type;
    private List<FeatureDto> features;

}
