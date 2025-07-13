package Lib;
import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));      // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }
        // Test 4: เช็คว่าเมื่อซื้อของมากกว่า 6 อย่างจะได้ส่วนลด10%
        ArrayList<CartItem> discountCart = new ArrayList<>();
        discountCart.add(new CartItem("BULK", "Bread", 30.0, 7));
        discountCart.add(new CartItem("BULK", "Milk", 20.0, 6));
        double total4 = ShoppingCartCalculator.calculateTotalPrice(discountCart);
        if(total4 == 297.0){
            System.out.println("PASSED: Discount cart total is correct (297.0)");
            passedCount++;
        }else {
            System.out.println("FAILED: Discount cart total expected 297.0 but got " + total4);
            failedCount++;
        }
        // Test 5: ทดสอบว่าถ้า price หรือ quatity ติดลบ
        ArrayList<CartItem> minusCart = new ArrayList<>();
        minusCart.add(new CartItem("NORMAL", "Bread", -30.0, 7));
        minusCart.add(new CartItem("NORMAL", "Milk", 20.0, -6));
        minusCart.add(new CartItem("NORMAL", "Ice", -5.0, -6));
        double total5 = ShoppingCartCalculator.calculateTotalPrice(minusCart);
        if(total5 == 0.0){
            System.out.println("PASSED: minus cart should return 0.0");
            passedCount++;
        }else {
            System.out.println("FAILED: minus cart total expected 0.0 but got " + total5);
            failedCount++;
        }
        // Test 6: ทดสอบการซื้อ 1 แถม 1 BOGO
        ArrayList<CartItem> BOGOCart = new ArrayList<>();
        BOGOCart.add(new CartItem("BOGO", "Bread", 10.0, 7));
        BOGOCart.add(new CartItem("BOGO", "Milk", 20.0, 6));
        BOGOCart.add(new CartItem("BOGO", "Ice", 5.0, 5));
        double total6 = ShoppingCartCalculator.calculateTotalPrice(BOGOCart);

        if(total6 == 115.0){
            System.out.println("PASSED: BOGO cart total is correct (115.0)");
            passedCount++;
        }else{
            System.out.println("FAILED: BOGO cart total expected 115.0 but got " + total6);
            failedCount++;
        }
        // Test 7: ทดสอบถ้ารหัสสินค้าเป็นอย่างอื่นที่ไม่ใช่ NORMAL, BULK, BOGO
        ArrayList<CartItem> skutestCart = new ArrayList<>();
        skutestCart.add(new CartItem("Hello world", "Water", 20.0, 5)); // SKU แปลก
        
        double total7 = ShoppingCartCalculator.calculateTotalPrice(skutestCart);

        if (total7 == 0.0) {
            System.out.println("PASSED: Invalid SKU should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Invalid SKU expected 0.0 but got " + total7);
            failedCount++;
        }
        // Test 8: เช็คว่าถ้า name เป็นค่า null หรือว่าง
        ArrayList<CartItem> invalidNameCart = new ArrayList<>();
        invalidNameCart.add(new CartItem("NORMAL", null, 20.0, 2)); // name = null
        invalidNameCart.add(new CartItem("NORMAL", "", 15.0, 1));   // name = ""

        double total8 = ShoppingCartCalculator.calculateTotalPrice(invalidNameCart);
        if (total8 == 0.0) {
            System.out.println("PASSED: Item with null or blank name returns 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Item with invalid name expected 0.0 but got " + total8);
            failedCount++;
        }


        


        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}