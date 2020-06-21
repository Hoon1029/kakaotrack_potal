package kr.ac.jejunu.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Shop {
    Integer id;
    String ownerId;
    String name;
    String address;
    double locateX;
    double locateY;
}
