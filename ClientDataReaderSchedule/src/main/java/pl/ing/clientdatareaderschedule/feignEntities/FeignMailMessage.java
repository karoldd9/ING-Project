package pl.ing.clientdatareaderschedule.feignEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeignMailMessage {

    private String addressee, subject, body;
}
