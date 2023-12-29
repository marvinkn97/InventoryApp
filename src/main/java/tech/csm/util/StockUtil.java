package tech.csm.util;

import tech.csm.entity.MaterialMaster;
import tech.csm.entity.MaterialMasterVO;
import tech.csm.entity.StockDetails;
import tech.csm.entity.StockDetailsVO;
import tech.csm.service.MaterialMasterService;
import tech.csm.service.MaterialMasterServiceImpl;

public class StockUtil {

    private static MaterialMasterService materialMasterService = new MaterialMasterServiceImpl();

    public static MaterialMasterVO mapMaterialEntityToVO(MaterialMaster materialMaster) {
        MaterialMasterVO materialMasterVO = new MaterialMasterVO();
        materialMasterVO.setMaterialId(materialMaster.getMaterialId().toString());
        materialMasterVO.setMaterialName(materialMaster.getMaterialName());

        return materialMasterVO;
    }

    public static StockDetailsVO mapStockEntityToVO(StockDetails stockDetails) {

        StockDetailsVO stockDetailsVO = new StockDetailsVO();
        stockDetailsVO.setStockId(stockDetails.getStockId());
        stockDetailsVO.setQuantity(stockDetails.getQuantity().toString());

        MaterialMasterVO materialMasterVO = materialMasterService.getMaterialById(stockDetails.getMaterialMaster().getMaterialId());

        stockDetailsVO.setMaterialMasterVO(materialMasterVO);

        return stockDetailsVO;
    }

}
