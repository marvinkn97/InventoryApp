package tech.csm.service;

import tech.csm.dao.MaterialMasterDao;
import tech.csm.dao.MaterialMasterJdbcImpl;
import tech.csm.entity.MaterialMaster;
import tech.csm.entity.MaterialMasterVO;
import tech.csm.util.StockUtil;

import java.util.ArrayList;
import java.util.List;

public class MaterialMasterServiceImpl implements MaterialMasterService {
    private final MaterialMasterDao materialMasterDao;

    public MaterialMasterServiceImpl() {
        this.materialMasterDao = new MaterialMasterJdbcImpl();
    }

    @Override
    public String insertMaterial(MaterialMasterVO materialMasterVO) {
        int id = 0;
        if (materialMasterVO != null) {
            MaterialMaster materialMaster = new MaterialMaster();
            materialMaster.setMaterialName(materialMasterVO.getMaterialName());
            id = materialMasterDao.insertMaterial(materialMaster);
        }
        return "Material created successfully with id " + id;
    }

    @Override
    public List<MaterialMasterVO> getAllMaterials() {

        List<MaterialMaster> materialMasterList = materialMasterDao.getAllMaterials();
//        System.out.println(materialMasterList.size());

        List<MaterialMasterVO> materialMasterVOList = null;

        if (materialMasterList != null) {
            materialMasterVOList = new ArrayList<>();

            for (MaterialMaster materialMaster : materialMasterList) {
                MaterialMasterVO materialMasterVO = StockUtil.mapMaterialEntityToVO(materialMaster);
                materialMasterVOList.add(materialMasterVO);
            }
        }
        return materialMasterVOList;
    }

    @Override
    public MaterialMasterVO getMaterialById(Integer id) {
        MaterialMasterVO materialMasterVO = null;

        MaterialMaster materialMaster = materialMasterDao.getMaterialById(id);

        if (materialMaster != null) {
            materialMasterVO = StockUtil.mapMaterialEntityToVO(materialMaster);
        }

        return materialMasterVO;
    }

    @Override
    public MaterialMasterVO getMaterialByName(String name) {
        MaterialMasterVO materialMasterVO = null;

        MaterialMaster materialMaster = materialMasterDao.getMaterialByName(name);

        if (materialMaster != null) {
            materialMasterVO = StockUtil.mapMaterialEntityToVO(materialMaster);
        }

        return materialMasterVO;
    }

    @Override
    public boolean existsMaterialByName(String name) {
        Long count = materialMasterDao.getAllMaterials().stream()
                .map(materialMaster -> materialMaster.getMaterialName())
                .filter(n -> n.equalsIgnoreCase(name))
                .count();
        return count != null && count > 0;
    }

}
