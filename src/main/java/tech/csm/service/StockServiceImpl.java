package tech.csm.service;

import tech.csm.dao.MaterialMasterDao;
import tech.csm.dao.MaterialMasterJdbcImpl;
import tech.csm.dao.StockDetailsDao;
import tech.csm.dao.StockDetailsJdbcImpl;
import tech.csm.entity.MaterialMaster;
import tech.csm.entity.StockDetails;
import tech.csm.entity.StockDetailsVO;
import tech.csm.util.StockUtil;

import java.util.ArrayList;
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
        List<StockDetails> stockDetailsList = stockDetailsDao.getAllStocks();

        List<StockDetailsVO> stockDetailsVOList = null;

        if (stockDetailsList != null) {
            stockDetailsVOList = new ArrayList<>();

            for (StockDetails stockDetails : stockDetailsList) {
                StockDetailsVO stockDetailsVO = StockUtil.mapStockEntityToVO(stockDetails);
                stockDetailsVOList.add(stockDetailsVO);
            }
        }
        return stockDetailsVOList;
    }

    @Override
    public StockDetailsVO getStockById(String id) {
        StockDetails stockDetails = stockDetailsDao.getStockById(id);
        StockDetailsVO stockDetailsVO = null;
        if (stockDetails != null) {
            stockDetailsVO = StockUtil.mapStockEntityToVO(stockDetails);
        }
        return stockDetailsVO;
    }

    @Override
    public String updateStock(StockDetailsVO stockDetailsVO) {
        String output = null;

        if (stockDetailsVO != null) {
            StockDetails stockDetails = stockDetailsDao.getStockById(stockDetailsVO.getStockId());

            MaterialMaster materialMaster = materialMasterDao.getMaterialById(Integer.parseInt(stockDetailsVO.getMaterialMasterVO().getMaterialId()));
            stockDetails.setMaterialMaster(materialMaster);

            stockDetails.setQuantity(Integer.parseInt(stockDetailsVO.getQuantity()));

            int update = stockDetailsDao.updateStock(stockDetails);

            if (update == 1) {
                output = "update successful";
            } else {
                output = "Update not successful";
            }
        }
        return output;
    }

    @Override
    public void deleteStock(String id) {
        StockDetails stockDetails = stockDetailsDao.getStockById(id);

        if (stockDetails != null) {
            int delete = stockDetailsDao.deleteStock(stockDetails.getStockId());
            if (delete == 1) {
                System.out.printf("Stock with id [%s] deleted successfully", id);
            } else {
                System.out.println("Error while deleting");
            }
        } else {
            System.out.printf("Stock with given id [%s] not found", id);
        }
    }

}
