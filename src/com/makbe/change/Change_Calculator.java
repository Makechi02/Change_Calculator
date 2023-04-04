package com.makbe.change;

public class Change_Calculator {

    public Change_Calculator(UI obj, int charged, int paid) {
        calculateBalance(obj, charged, paid);
    }

    private void calculateBalance(UI obj, int amount, int paid) {
        obj.details_area.setText("");
        int balance;

        if (amount > paid) {
            obj.details_area.append("Amount paid is less by: " + (amount - paid) + "\n");
            return;
        } else
            balance = paid - amount;

        obj.details_area.append("Amount charged: " + amount + "\n");
        obj.details_area.append("Amount paid: " + paid + "\n");
        obj.details_area.append("Balance: " + balance + "\n\n");
        denominations(obj, balance);
    }

    private int recursive(int balance, int amt) {
        return balance / amt;
    }

    public void denominations(UI obj, int balance) {
        int i = 0;
        int[] dens = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
        do {
            if ((recursive(balance, dens[i]) != 0)) {
                if (i < 5)
                    obj.details_area.append(dens[i] + " notes: " + recursive(balance, dens[i]) + "\n");
                else
                    obj.details_area.append(dens[i] + " coins: " + recursive(balance, dens[i]) + "\n");
                balance = balance % dens[i];
            }
            i++;
        } while (i < dens.length);
    }

}
