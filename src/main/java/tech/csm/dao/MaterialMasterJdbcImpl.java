package tech.csm.dao;

import tech.csm.entity.MaterialMaster;
import tech.csm.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialMasterJdbcImpl implements MaterialMasterDao {

    private final Connection connection;

    public MaterialMasterJdbcImpl() {
        this.connection = DBUtil.getConnection();
    }

    @Override
    public Integer insertMaterial(MaterialMaster materialMaster) {

        final String sql = "INSERT INTO t_material_master (material_name) values(?)";
        int generatedId = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, materialMaster.getMaterialName());

            int rowsAffected = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();
            generatedId = resultSet.getInt(1);

            System.out.println("JDBC Insert Result = " + rowsAffected);

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
           e.printStackTrace();
        }

        return generatedId;
    }

    @Override
    public List<MaterialMaster> getAllMaterials() {
        final String sql = "SELECT material_id, material_name from t_material_master";
        List<MaterialMaster> materialMasterList = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                materialMasterList = new ArrayList<>();
                do {
                    MaterialMaster materialMaster = new MaterialMaster();
                    materialMaster.setMaterialId(resultSet.getInt("material_id"));
                    materialMaster.setMaterialName(resultSet.getString("material_name"));

                    materialMasterList.add(materialMaster);

                } while (resultSet.next());
            } else {
                System.out.println("No records found");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materialMasterList;
    }

    @Override
    public MaterialMaster getMaterialById(Integer id) {
        final String sql = "SELECT material_id, material_name from t_material_master WHERE material_id = ?";
        MaterialMaster materialMaster = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            materialMaster = new MaterialMaster();
            materialMaster.setMaterialId(resultSet.getInt("material_id"));
            materialMaster.setMaterialName(resultSet.getString("material_name"));

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materialMaster;
    }

    @Override
    public MaterialMaster getMaterialByName(String name) {
        final String sql = "SELECT material_id, material_name from t_material_master WHERE material_name = ?";
        MaterialMaster materialMaster = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            materialMaster = new MaterialMaster();
            materialMaster.setMaterialId(resultSet.getInt("material_id"));
            materialMaster.setMaterialName(resultSet.getString("material_name"));

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materialMaster;
    }

}
