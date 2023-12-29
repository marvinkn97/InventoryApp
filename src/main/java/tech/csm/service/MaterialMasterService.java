package tech.csm.service;

import tech.csm.entity.MaterialMasterVO;

import java.util.List;

public interface MaterialMasterService {
    public String insertMaterial(MaterialMasterVO materialMasterVO);
    public List<MaterialMasterVO> getAllMaterials();
    public MaterialMasterVO getMaterialById(Integer id);
    public MaterialMasterVO getMaterialByName(String name);
}
