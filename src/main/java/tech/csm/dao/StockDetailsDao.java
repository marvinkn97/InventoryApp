package tech.csm.dao;

import tech.csm.entity.StockDetails;

import java.util.List;

public interface StockDetailsDao {
    Integer insertStock(StockDetails stockDetails);
    List<StockDetails> getAllStocks();
    StockDetails getStockById(String id);
    Integer updateStock(StockDetails stockDetails);
    Integer deleteStock(Integer id);
}
