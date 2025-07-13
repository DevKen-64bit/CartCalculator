package Lib;
import java.util.ArrayList;
 
public class ShoppingCartCalculator {

    /**
     * เขียน Javadoc ที่นี่เพื่ออธิบายกฎการทำงานและกรณีพิเศษ:
     * - จะทำอย่างไรถ้า items เป็น null หรือ empty?
     * - จะทำอย่างไรถ้า CartItem มี price หรือ quantity ติดลบ?
     * - กฎส่วนลด BOGO (ซื้อ 1 แถม 1)
     * - กฎส่วนลด BULK (ซื้อ >= 6 ชิ้น ลด 10%)
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        
        //เช็คว่าเป็น null หรือ empty หรือไม่
        if(items == null || items.isEmpty()){
            return 0.0;
        }
        double total = 0;
        for (CartItem item : items) {
        double price = item.price();
        int qty = item.quantity();
        String name = item.name();
        //เช็คว่ามี price หรือ quantity  ติดลบไหม และ หรือ name เป็นค่าว่างหรือไม่มีหรือเปล่า
        if(qty < 0 || qty < 0 || name == null || name == " " )
        {
        return 0.0;
        }
        
        //คำนวณราคาปกติ
        if(item.sku().equals("NORMAL"))
        {  
            total +=(price*qty); // รวมราคาที่ต้องจ่ายจริง
        }   
        //คำนวณราคาแบบ Bulk
       else if(item.sku().equals("BULK")) 
        {
            if (qty >= 6)
            {
                price *= 0.9; // ลด 10%
            }
                total += price * qty; // รวมราคาที่ต้องจ่ายจริง
    
        }//คำนวณราคาแบบ BOGO
        else if(item.sku().equals("BOGO")){
             double discountqty = (qty/2)+(qty%2); //หาจำนวนสินค้าที่ต้องจ่าย
             total += discountqty*price; // รวมราคาที่ต้องจ่ายจริง
        }else{
            if(item.sku()!="NORMAL" || item.sku() != "BOGO" || item.sku() != "BULK"){
                return 0.0;
            }
        }

        

        
        }
        
       

        
        return total;
        

        

        
        
      
    }
}