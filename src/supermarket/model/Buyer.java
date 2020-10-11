package supermarket.model;

public class Buyer {
    private double money;
    private double virtualAccount;
    private double account;
    private String name;
    private double quantity;

    public Buyer(String name, int money) {
        this.money = money;
        this.name = name;
        this.virtualAccount = money;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public double getVirtualAccount() {
        return virtualAccount;
    }

    public double getAccount() {
        return account;
    }

    public void setMoney(double m) {
        this.money = m;
    }

    public void setVirtualAccount(double virtualAccount) {
        this.virtualAccount = virtualAccount;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getMoney() {
        return money;
    }
}

