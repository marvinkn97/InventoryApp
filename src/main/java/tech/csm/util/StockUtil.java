package tech.csm.util;

import tech.csm.entity.MaterialMaster;
import tech.csm.entity.MaterialMasterVO;

public class StockUtil {

    public static MaterialMasterVO mapMaterialEntityToVO(MaterialMaster materialMaster) {
        MaterialMasterVO materialMasterVO = new MaterialMasterVO();
        materialMasterVO.setMaterialId(materialMaster.getMaterialId().toString());
        materialMasterVO.setMaterialName(materialMaster.getMaterialName());

        return materialMasterVO;
    }

}
