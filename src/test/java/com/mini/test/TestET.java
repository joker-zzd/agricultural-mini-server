package com.mini.test;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestET {
    private int price;
    private int count;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Nested
    class DebugPractice{
        @Test
        public void DebugPracticeTest(){
            TestET order = new TestET();
            order.setPrice(100);
            order.setCount(2);
            int total = calcTotal(order);
            System.out.println("total = " + total); // 你期望 200，实际不是
        }

        private static int calcTotal(TestET order) {

            int total = 0;

            if (order.getCount() > 1) {
                total = order.getPrice();   // ❌ BUG：少乘了 count
            }

            return total;
        }

    }
}
