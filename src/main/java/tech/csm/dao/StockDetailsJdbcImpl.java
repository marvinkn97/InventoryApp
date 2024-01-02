package tech.csm.dao;

import tech.csm.entity.MaterialMaster;
import tech.csm.entity.StockDetails;
import tech.csm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDetailsJdbcImpl implements StockDetailsDao {

    private final Connection connection;
    private final MaterialMasterDao materialMasterDao;

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

                    MaterialMaster materialMaster = materialMasterDao.getMaterialById(resultSet.getInt("material_id"));

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

            if (resultSet.next()) {
                stockDetails = new StockDetails();
                stockDetails.setStockId(resultSet.getString("stock_id"));

                MaterialMaster materialMaster = materialMasterDao.getMaterialById(resultSet.getInt("material_id"));

                stockDetails.setMaterialMaster(materialMaster);

                stockDetails.setQuantity(resultSet.getInt("qty"));

            } else {
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
        final String sql = "UPDATE t_stock_details SET material_id = ?, qty = ? WHERE stock_id = ?";
        int rowsAffected;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, stockDetails.getMaterialMaster().getMaterialId());
            preparedStatement.setInt(2, stockDetails.getQuantity());
            preparedStatement.setString(3, stockDetails.getStockId());

            rowsAffected = preparedStatement.executeUpdate();
            System.out.println("JDBC UPDATE RESULT = " + rowsAffected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowsAffected;
    }

    @Override
    public Integer deleteStock(String id) {
        final String sql = "DELETE FROM t_stock_details WHERE stock_id = ?";
        int rowsAffected;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            rowsAffected = preparedStatement.executeUpdate();
            System.out.println("DELETE RESULT = " + rowsAffected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowsAffected;
    }

}
