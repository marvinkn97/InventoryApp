package tech.csm.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockDetailsVO {
    private String stockId;
    private String quantity;
    private MaterialMasterVO materialMasterVO;
}
