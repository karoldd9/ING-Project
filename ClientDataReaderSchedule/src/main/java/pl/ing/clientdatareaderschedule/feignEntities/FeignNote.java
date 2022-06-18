package pl.ing.clientdatareaderschedule.feignEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeignNote {
    private Long customerId;
    private String type, title, content;
}
