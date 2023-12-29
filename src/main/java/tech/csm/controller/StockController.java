package tech.csm.controller;

import tech.csm.entity.MaterialMasterVO;
import tech.csm.entity.StockDetailsVO;
import tech.csm.service.MaterialMasterService;
import tech.csm.service.MaterialMasterServiceImpl;
import tech.csm.service.StockService;
import tech.csm.service.StockServiceImpl;

import java.util.List;
import java.util.Scanner;

public class StockController {
    private static MaterialMasterService materialMasterService = new MaterialMasterServiceImpl();
    private static StockService stockService = new StockServiceImpl();
    static Scanner ss = new Scanner(System.in);
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {

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

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter name of material");
                    String name = ss.nextLine();

                    MaterialMasterVO materialMasterVO = new MaterialMasterVO();

                    if (!name.isEmpty() && !name.isBlank()) {
                        materialMasterVO.setMaterialName(name);
                    }
                    String msg = materialMasterService.insertMaterial(materialMasterVO);
                    System.out.println(msg);
                    break;
                case 2:
                    insertStock();
                    break;
                case 5:
                    List<MaterialMasterVO> materialMasterVOList = materialMasterService.getAllMaterials();
                    for (MaterialMasterVO x : materialMasterVOList) {
                        System.out.println(x);
                    }
                    break;
                default:
                    System.out.println("Invalid choice please try again");
            }
        } while (choice != 6);

    }

    public static void printMaterialNames() {
        List<MaterialMasterVO> materialMasterVOList = materialMasterService.getAllMaterials();

        for (MaterialMasterVO materialMasterVO : materialMasterVOList) {
            System.out.println(materialMasterVO.getMaterialName());
        }
    }

    public static void insertStock() {
        StockDetailsVO stockDetailsVO = new StockDetailsVO();

        System.out.println("Enter Stock ID");

        String id = ss.nextLine().trim().toUpperCase();

        StockDetailsVO exists = stockService.getStockById(id);

        //check if entered ID is already taken
        if (exists != null) {
            System.out.println("ID already taken");
            return;
        } else {

            System.out.println("Do you want to insert?...yes or no");

            String proceed = ss.nextLine().trim();

            if (!proceed.equalsIgnoreCase("yes")) {
                System.out.println("Exiting");
                return;
            }
            System.out.println("Choose material name");
            printMaterialNames();
            String materialName = ss.nextLine().trim();

            stockDetailsVO.setStockId(id);

            if (materialMasterService.existsMaterialByName(materialName)) {
                MaterialMasterVO materialMasterVO = materialMasterService.getMaterialByName(materialName);
                stockDetailsVO.setMaterialMasterVO(materialMasterVO);
            } else {
                System.out.println("Material name does not exist");
                return;
            }

            System.out.println("Enter quantity");
            String qty = ss.nextLine().trim();

            stockDetailsVO.setQuantity(qty);

            stockService.insertStock(stockDetailsVO);

        }
    }

}

