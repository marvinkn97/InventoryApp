package tech.csm.service;

import tech.csm.entity.MaterialMasterVO;

import java.util.List;

public interface MaterialMasterService {
     String insertMaterial(MaterialMasterVO materialMasterVO);
     List<MaterialMasterVO> getAllMaterials();
     MaterialMasterVO getMaterialById(Integer id);
     MaterialMasterVO getMaterialByName(String name);
     boolean existsMaterialByName(String name);
}
