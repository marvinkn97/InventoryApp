package tech.csm.service;

import tech.csm.dao.MaterialMasterDao;
import tech.csm.dao.MaterialMasterJdbcImpl;
import tech.csm.dao.StockDetailsDao;
import tech.csm.dao.StockDetailsJdbcImpl;
import tech.csm.entity.MaterialMaster;
import tech.csm.entity.StockDetails;
import tech.csm.entity.StockDetailsVO;

import java.util.List;

public class StockServiceImpl implements StockService {

    private final StockDetailsDao stockDetailsDao;
    private final MaterialMasterDao materialMasterDao;

    public StockServiceImpl() {
        this.materialMasterDao = new MaterialMasterJdbcImpl();
        this.stockDetailsDao = new StockDetailsJdbcImpl();
    }

    @Override
    public String insertStock(StockDetailsVO stockDetailsVO) {

        if (stockDetailsVO != null) {
            StockDetails stockDetails = new StockDetails();
            stockDetails.setStockId(stockDetailsVO.getStockId());
            stockDetails.setQuantity(Integer.parseInt(stockDetailsVO.getQuantity()));

            MaterialMaster materialMaster = materialMasterDao.getMaterialById(Integer.parseInt(stockDetailsVO.getMaterialMasterVO().getMaterialId()));

            stockDetails.setMaterialMaster(materialMaster);

            stockDetailsDao.insertStock(stockDetails);
        }

        return "Stock created successfully";
    }

    @Override
    public List<StockDetailsVO> getAllStocks() {
        return null;
    }

    @Override
    public StockDetailsVO getStockById(String id) {
        return null;
    }

    @Override
    public String updateStock(StockDetailsVO stockDetailsVO) {
        return null;
    }

    @Override
    public String deleteStock(String id) {
        return null;
    }
}
