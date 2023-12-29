package tech.csm.controller;

import tech.csm.entity.MaterialMasterVO;
import tech.csm.service.MaterialMasterService;
import tech.csm.service.MaterialMasterServiceImpl;

import java.util.List;
import java.util.Scanner;

public class StockController {
    private static MaterialMasterService materialMasterService = new MaterialMasterServiceImpl();
    static Scanner ss = new Scanner(System.in);
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {



        System.out.println("******************************" +
                           "\n*     Stock Information      *" +
                           "\n******************************" +
                           "\n1. Insert Material" +
                           "\n2. Insert Stock" +
                           "\n3. Update Stock" +
                           "\n4. Display Stock " +
                           "\n5. Display Materials " +
                           "\n6. Exit" +
                           "\nEnter Choice 1 - 6");

        int choice = sc.nextInt() ;

        switch (choice){
            case 1:
                System.out.println("Enter name of material");
                String name = ss.nextLine();

                MaterialMasterVO materialMasterVO = new MaterialMasterVO();

                if(!name.isEmpty() && !name.isBlank()){
                    materialMasterVO.setMaterialName(name);
                }
                String msg = materialMasterService.insertMaterial(materialMasterVO);
                System.out.println(msg);
                break;
            case 2:
                System.out.println("Choose material name");
                printMaterialNames();

                System.out.println("Enter Stock ID");
                String id = ss.nextLine().trim();
                int qty = sc.nextInt();


                break;
            case  5:
                List<MaterialMasterVO> materialMasterVOList = materialMasterService.getAllMaterials();
                for (MaterialMasterVO x : materialMasterVOList){
                    System.out.println(x);
                }
                break;
            default:
                System.out.println("Invalid choice please try again");
        }

    }

    public static void printMaterialNames(){
        List<MaterialMasterVO> materialMasterVOList =  materialMasterService.getAllMaterials();

        for (MaterialMasterVO materialMasterVO : materialMasterVOList){
            System.out.println(materialMasterVO.getMaterialName());
        }
    }

}
