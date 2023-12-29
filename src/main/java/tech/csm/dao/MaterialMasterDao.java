package tech.csm.dao;

import tech.csm.entity.MaterialMaster;

import java.util.List;

public interface MaterialMasterDao {
     Integer insertMaterial(MaterialMaster materialMaster);
     List<MaterialMaster> getAllMaterials();
     MaterialMaster getMaterialById(Integer id);
     MaterialMaster getMaterialByName(String name);
}
