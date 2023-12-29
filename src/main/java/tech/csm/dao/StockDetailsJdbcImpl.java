package tech.csm.dao;

import tech.csm.entity.MaterialMaster;
import tech.csm.entity.StockDetails;
import tech.csm.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDetailsJdbcImpl implements StockDetailsDao{

    private final Connection connection;
    private MaterialMasterDao materialMasterDao;

    public StockDetailsJdbcImpl() {
        this.materialMasterDao = new MaterialMasterJdbcImpl();
        this.connection = DBUtil.getConnection();
    }

    @Override
    public Integer insertStock(StockDetails stockDetails) {
        final String sql = "INSERT INTO t_stock_details (stock_id, material_id, qty) values(?, ?, ?)";
        int rowsAffected = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, stockDetails.getStockId());
            preparedStatement.setInt(2, stockDetails.getMaterialMaster().getMaterialId());
            preparedStatement.setInt(3, stockDetails.getQuantity());

             rowsAffected = preparedStatement.executeUpdate();

            System.out.println("JDBC Insert Result = " + rowsAffected);

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsAffected;
    }

    @Override
    public List<StockDetails> getAllStocks() {
        final String sql = "SELECT stock_id, material_id, qty from t_stock_details";
        List<StockDetails> stockDetailsList = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                stockDetailsList = new ArrayList<>();
                do {
                    StockDetails stockDetails = new StockDetails();
                    stockDetails.setStockId(resultSet.getString("stock_id"));

                    MaterialMaster  materialMaster = materialMasterDao.getMaterialById(resultSet.getInt("material_id"));

                    stockDetails.setMaterialMaster(materialMaster);

                    stockDetails.setQuantity(resultSet.getInt("qty"));

                    stockDetailsList.add(stockDetails);

                } while (resultSet.next());
            } else {
                System.out.println("No records found");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockDetailsList;
    }

    @Override
    public StockDetails getStockById(String id) {
        final String sql = "SELECT stock_id, material_id, qty from t_stock_details WHERE stock_id = ?";
        StockDetails stockDetails = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                stockDetails = new StockDetails();
                stockDetails.setStockId(resultSet.getString("stock_id"));

                MaterialMaster materialMaster = materialMasterDao.getMaterialById(resultSet.getInt("material_id"));

                stockDetails.setMaterialMaster(materialMaster);

                stockDetails.setQuantity(resultSet.getInt("qty"));

            }else{
                System.out.println("No record with given id found");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockDetails;
    }

    @Override
    public Integer updateStock(StockDetails stockDetails) {
        return null;
    }

    @Override
    public Integer deleteStock(String id) {
        return null;
    }

}
