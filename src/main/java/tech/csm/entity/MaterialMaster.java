package tech.csm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class MaterialMaster implements Serializable {
    private Integer materialId;
    private String materialName;
}
