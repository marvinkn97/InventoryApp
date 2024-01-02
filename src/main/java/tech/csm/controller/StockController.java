package tech.csm.controller;

import tech.csm.entity.MaterialMasterVO;
import tech.csm.entity.StockDetailsVO;
import tech.csm.service.MaterialMasterService;
import tech.csm.service.MaterialMasterServiceImpl;
import tech.csm.service.StockService;
import tech.csm.service.StockServiceImpl;
import tech.csm.util.DBUtil;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StockController {
    private static final MaterialMasterService materialMasterService = new MaterialMasterServiceImpl();
    private static final StockService stockService = new StockServiceImpl();
    static Scanner ss = new Scanner(System.in);
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println(
                    "******************************" +
                            "\n*     Stock Information      *" +
                            "\n******************************" +
                            "\n1. Insert Material" +
                            "\n2. Insert Stock" +
                            "\n3. Update Stock" +
                            "\n4. Display Stock " +
                            "\n5. Display Materials " +
                            "\n6. Delete Stock" +
                            "\n7. Exit" +
                            "\nEnter Choice 1 - 7");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    insertMaterial();
                    break;
                case 2:
                    insertStock();
                    break;
                case 3:
                    updateStock();
                    break;
                case 4:
                    displayAllStock();
                    break;
                case 5:
                    displayAllMaterials();
                    break;
                case 6:
                    deleteStock();
                    break;
                case 7:
                    exitProgram();
                    break;
                default:
                    System.out.println("Invalid choice please try again");
            }
        } while (choice != 7);

    }

    public static void insertMaterial() {
        System.out.println("Enter name of material");
        String name = ss.nextLine();

        MaterialMasterVO materialMasterVO = new MaterialMasterVO();

        if (!name.isEmpty() && !name.isBlank()) {
            materialMasterVO.setMaterialName(name);
        }
        String msg = materialMasterService.insertMaterial(materialMasterVO);
        System.out.println(msg);
    }

    public static void displayAllMaterials() {
        List<MaterialMasterVO> materialMasterVOList = materialMasterService.getAllMaterials();
        if (materialMasterVOList != null && !materialMasterVOList.isEmpty()) {
            System.out.println("Material ID | Material Name");
            System.out.println("-----------------------------");
            for (MaterialMasterVO x : materialMasterVOList) {
                System.out.println(x.getMaterialId() + " | " + x.getMaterialName());
            }
            waitForEnter();
        }
    }

    public static void printMaterialNames() {
        List<MaterialMasterVO> materialMasterVOList = materialMasterService.getAllMaterials();
        if (materialMasterVOList != null && !materialMasterVOList.isEmpty()) {
            for (MaterialMasterVO materialMasterVO : materialMasterVOList) {
                System.out.println(materialMasterVO.getMaterialName());
            }
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
            clearScreen();
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

            String msg = stockService.insertStock(stockDetailsVO);
            System.out.println(msg);

        }
    }

    public static void updateStock() {
        System.out.println("Enter Stock ID");

        String id = ss.nextLine().trim().toUpperCase();

        StockDetailsVO existingStock = stockService.getStockById(id);

        if (existingStock == null) {
            System.out.println("Invalid Stock ID");
            clearScreen();
        } else {
            displayAllStock();

            System.out.println("-------------------------");

            printNameAndQty();
            System.out.println("Enter new Material Name");
            String newMaterialName = ss.nextLine().trim();

            System.out.println("Enter new Quantity");
            String newQty = ss.nextLine().trim();

            System.out.println("Do you want to update? (yes/no)");

            String response = ss.nextLine().trim();

            if (response.equalsIgnoreCase("yes")) {
                if (!newMaterialName.isBlank() && !newMaterialName.isEmpty()) {
                    if (materialMasterService.existsMaterialByName(newMaterialName)) {

                        MaterialMasterVO materialMasterVO = materialMasterService.getMaterialByName(newMaterialName);

                        existingStock.setMaterialMasterVO(materialMasterVO);
                    } else {
                        System.out.println("Material name does not exist");
                        return;
                    }
                } else {
                    existingStock.setMaterialMasterVO(existingStock.getMaterialMasterVO());
                }
                if (!newQty.isBlank() && Integer.parseInt(newQty) > 0) {
                    existingStock.setQuantity(newQty);
                }
                String msg = stockService.updateStock(existingStock);
                System.out.println(msg);
            } else {
                clearScreen();
            }
        }
    }

    public static void printNameAndQty() {
        List<StockDetailsVO> stockDetailsVOList = stockService.getAllStocks();
        if (!stockDetailsVOList.isEmpty()) {
            System.out.println("Material Name  |  Quantity");
            for (StockDetailsVO x : stockDetailsVOList) {
                System.out.println(x.getMaterialMasterVO().getMaterialName() + " - " + x.getQuantity());
            }
        }
    }

    public static void displayAllStock() {
        List<StockDetailsVO> stockDetailsVOList = stockService.getAllStocks();
        if (stockDetailsVOList != null && !stockDetailsVOList.isEmpty()) {
            System.out.println("Stock ID | Material Name | Quantity");
            System.out.println("--------------------------------------");
            for (StockDetailsVO x : stockDetailsVOList) {
                System.out.println(x.getStockId() + " | " + x.getMaterialMasterVO().getMaterialName() + " | " + x.getQuantity());
            }
            //wait for enter key to continue
            waitForEnter();
        } else {
            System.out.println("No stocks found...please add some stock");
        }
    }

    public static void waitForEnter() {
        System.out.println("Press any key to continue");
        try {
            System.in.read();
            //clear the screen
            clearScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void deleteStock() {
        System.out.println("Enter stock id to delete");

        String id = ss.nextLine().trim().toUpperCase();

        StockDetailsVO stockDetailsVO = stockService.getStockById(id);

        if (stockDetailsVO != null) {
            System.out.println(stockDetailsVO);
            System.out.println("Are you sure you want to delete this record? (yes or no)");

            String response = ss.nextLine().trim();

            if (response.equalsIgnoreCase("yes")) {
                stockService.deleteStock(stockDetailsVO.getStockId());
            } else if (response.equalsIgnoreCase("no")) {
                clearScreen();
            } else {
                System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
            }

        }
    }

    public static void exitProgram() {
        System.out.println("Do you really want to exit? (yes/no)");
        String response = ss.nextLine().trim();

        if (response.equalsIgnoreCase("yes")) {
            DBUtil.closeConnection();
            System.out.println("Exiting the program...GoodBye!!");
            System.exit(0);
        } else if (response.equalsIgnoreCase("no")) {
            clearScreen();
        } else {
            System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
        }
    }
}

