package a1.anicholas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Random ran = new Random();
    private static final FoodItems[] foodItems = FoodItems.values();
    private static final Tools[] tools = Tools.values();
    private static final ToolUses[] toolUses = ToolUses.values();
    private static final MaterialItems[] materialItems = MaterialItems.values();
    private static final MunitionItems[] munitionItems = MunitionItems.values();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Item> items = new ArrayList<>();
        System.out.print("How many items do you want: ");
        int itemCnt = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < itemCnt; i++) {
            int type = ran.nextInt(2);
            switch (type) {
                case 0 -> items.add(genFood());
                case 1 -> items.add(genTool());
                case 2 -> items.add(genMaterials());
                case 3 -> items.add(genMunitions());
            }
        }

        for (Item i : items) {
            System.out.println(i);
        }

    }

    public static Food genFood() {
        int foodIndex = ran.nextInt(foodItems.length);
        String foodName = foodItems[foodIndex].toString();
        float foodPrice = ran.nextFloat(10);
        int foodQty = ran.nextInt(30);
        int foodUses = ran.nextInt(6);
        float healthGain = ran.nextInt(20);
        return new Food(foodName, foodPrice, foodQty, foodUses, healthGain);
    }

    public static Tool genTool() {
        int toolIndex = ran.nextInt(tools.length);
        String toolName = tools[toolIndex].toString();
        float toolPrice = ran.nextFloat(200);
        int toolQty = ran.nextInt(15);
        String use = toolUses[toolIndex].toString();
        return new Tool(toolName, toolPrice, toolQty, use);
    }

    public static Materials genMaterials() {
        int materialIndex = ran.nextInt(materialItems.length);
        String materialName = materialItems[materialIndex].toString();
        float materialPrice = ran.nextFloat(5000);
        int materialQty = ran.nextInt(50);
        float strength = ran.nextInt(1000);
        return new Materials(materialName, materialPrice, materialQty, strength);
    }

    public static Munitions genMunitions() {
        int munitionIndex = ran.nextInt(munitionItems.length);
        String munitionName = munitionItems[munitionIndex].toString();
        float munitionPrice = ran.nextFloat(5000);
        int munitionQty = ran.nextInt(2);
        float damage = ran.nextInt(1000);
        return new Munitions(munitionName, munitionPrice, munitionQty, damage);
    }
}
