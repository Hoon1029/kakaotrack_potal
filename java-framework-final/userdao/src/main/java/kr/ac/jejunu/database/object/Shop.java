package kr.ac.jejunu.database.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Shop {
    private Integer id;
    private String ownerId;
    private String name;
    private String address;
    private double locateX;
    private double locateY;
}
