package tech.csm.dao;

import tech.csm.entity.MaterialMaster;
import tech.csm.util.DBUtil;

import java.sql.*;
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
    public List<MaterialMaster> getAllMaterial() {
        return null;
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
