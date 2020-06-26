package kr.ac.jejunu.database.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    private Integer id;
    private Integer shopId;
    private String name;
    private Integer price;
    private Boolean sellFlag;
}
