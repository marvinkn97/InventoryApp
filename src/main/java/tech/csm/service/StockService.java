package tech.csm.service;

import tech.csm.entity.StockDetailsVO;

import java.util.List;

public interface StockService {

    String insertStock(StockDetailsVO stockDetailsVO);
    List<StockDetailsVO> getAllStocks();
    StockDetailsVO getStockById(String id);
    String updateStock(StockDetailsVO stockDetailsVO);
    String deleteStock(String id);
}
