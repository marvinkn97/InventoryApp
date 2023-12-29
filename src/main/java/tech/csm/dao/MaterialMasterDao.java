package tech.csm.dao;

import tech.csm.entity.MaterialMaster;

import java.util.List;

public interface MaterialMasterDao {
    public Integer insertMaterial(MaterialMaster materialMaster);
    public List<MaterialMaster> getAllMaterial();
    public MaterialMaster getMaterialById(Integer id);
    public MaterialMaster getMaterialByName(String name);
}
