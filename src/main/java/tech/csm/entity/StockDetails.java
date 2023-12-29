package tech.csm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class StockDetails implements Serializable {
    private String stockId;
    private Integer quantity;
    private MaterialMaster materialMaster;
}
