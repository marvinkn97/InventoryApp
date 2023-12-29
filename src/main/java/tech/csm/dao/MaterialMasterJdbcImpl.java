package tech.csm.dao;

import tech.csm.entity.MaterialMaster;
import tech.csm.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialMasterJdbcImpl implements MaterialMasterDao {

    private Connection connection;

    public MaterialMasterJdbcImpl() {
        this.connection = DBUtil.getConnection();
    }

    @Override
    public Integer insertMaterial(MaterialMaster materialMaster) {

        final String sql = "INSERT INTO t_material_master (material_name) values(?)";
        int generatedId;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, materialMaster.getMaterialName());

            int rowsAffected = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();
            generatedId = resultSet.getInt(1);

            System.out.println("JDBC Insert Result = " + rowsAffected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return generatedId;
    }

    @Override
    public List<MaterialMaster> getAllMaterials() {
        final String sql = "SELECT material_id, material_name from t_material_master";
        List<MaterialMaster> materialMasterList = null;
        try {
            PreparedStatement  preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                materialMasterList = new ArrayList<>();
                do{
                  MaterialMaster materialMaster = new MaterialMaster();
                  materialMaster.setMaterialId(resultSet.getInt("material_id"));
                  materialMaster.setMaterialName(resultSet.getString("material_name"));

                  materialMasterList.add(materialMaster);

                }while (resultSet.next());
            }else{
                System.out.println("No records found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return materialMasterList;
    }

    @Override
    public MaterialMaster getMaterialById(Integer id) {
        return null;
    }

    @Override
    public MaterialMaster getMaterialByName(String name) {
        return null;
    }
}
