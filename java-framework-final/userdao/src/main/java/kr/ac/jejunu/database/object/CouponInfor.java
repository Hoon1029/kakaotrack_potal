package kr.ac.jejunu.database.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponInfor {
    private Integer id;
    private Integer shopId;
    private String name;
    private Integer maxStampNum;
    private Integer productId;
    private String backgoundImgId;
    private String stampImgId;
}
