package tech.csm.service;

import tech.csm.dao.MaterialMasterDao;
import tech.csm.dao.MaterialMasterJdbcImpl;
import tech.csm.entity.MaterialMaster;
import tech.csm.entity.MaterialMasterVO;

import java.util.ArrayList;
import java.util.List;

public class MaterialMasterServiceImpl implements MaterialMasterService{
    private MaterialMasterDao materialMasterDao;

    public MaterialMasterServiceImpl() {
        this.materialMasterDao = new MaterialMasterJdbcImpl();
    }

    @Override
    public String insertMaterial(MaterialMasterVO materialMasterVO) {
        int id = 0;
        if(materialMasterVO != null){
            MaterialMaster materialMaster = new MaterialMaster();
            materialMaster.setMaterialName(materialMasterVO.getMaterialName());
            id  = materialMasterDao.insertMaterial(materialMaster);
        }
        return "Material created successfully with id " + id;
    }

    @Override
    public List<MaterialMasterVO> getAllMaterials() {

        List<MaterialMaster> materialMasterList = materialMasterDao.getAllMaterials();
        System.out.println(materialMasterList.size());

        List<MaterialMasterVO> materialMasterVOList = null;

        if(materialMasterList!= null){
            materialMasterVOList = new ArrayList<>();

            for (MaterialMaster materialMaster : materialMasterList){
                MaterialMasterVO materialMasterVO = new MaterialMasterVO();
                        materialMasterVO.setMaterialId(materialMaster.getMaterialId().toString());
                        materialMasterVO.setMaterialName(materialMaster.getMaterialName());
                        materialMasterVOList.add(materialMasterVO);
            }
        }
        return materialMasterVOList;
    }

    @Override
    public MaterialMasterVO getMaterialById(Integer id) {
        return null;
    }

    @Override
    public MaterialMasterVO getMaterialByName(String name) {
        return null;
    }
}
