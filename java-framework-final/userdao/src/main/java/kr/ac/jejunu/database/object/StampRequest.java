package kr.ac.jejunu.database.object;

import com.sun.xml.bind.v2.schemagen.xmlschema.ExplicitGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StampRequest {
    private Integer id;
    private Integer couponInforId;
    private String customerId;
    private Integer stampNum;

    //not used in db, just for tranport date
    private String productName;
}
