package tests;

import java.util.List;

public class JavaEssentialsWork {

    public static void main(String[] args) {
        JavaEssentialsWork work = new JavaEssentialsWork();
        work.JavaOperations();
    }

      private static void  JavaOperations() {

          //операции над данными

          String conditionUsed = "Used";
          String conditionNew = "New";
          int basePrice = 4000;
          int tax = 150;
          double delivery = 99.99;
          int customFee = 2;
          int discount = 10;
          double totalPrice = basePrice + tax + delivery * customFee - discount;
//
//          Boolean tradeInAvailable = conditionNew != "New" && conditionUsed == "Used";
//            System.out.println(tradeInAvailable);

          List<String> condition = List.of("Used", "New");  // Такой же рабочий вариант, сделал и для List
          Boolean tradeInAvailable = !condition.contains("New") && condition.contains("Used");
          System.out.println(tradeInAvailable);

          System.out.println("Ваша цена за устройство: " + totalPrice);


          boolean saleApproved = true;
          boolean saleRejected = false;

          int age = 18;
          int fine = 5000;
          int alcogholSaleAge = 21;

          if (age >= alcogholSaleAge) {
              System.out.println(saleApproved);
          } else if (age < alcogholSaleAge && saleApproved) {
              System.out.println("Вам штрафик" + ' ' + fine + ' ' + "рубликов");
          } else {
              System.out.println(saleRejected);
          }


          // блок с переполнениями
              double a = 1e308;
              double b = 10;
              double result = a * b; // переполнение

              System.out.println(result); // положительный результат переполнения

              double negativeOverflow = -a * b;
              System.out.println(negativeOverflow); // отрицательный результат переполнения
          }
      }






