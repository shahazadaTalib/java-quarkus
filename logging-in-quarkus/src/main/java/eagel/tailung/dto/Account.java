package eagel.tailung.dto;

public class Account {
    long accountNumber;
    String name;
    long aadharNUmber;
    int amount;

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAadharNUmber() {
        return aadharNUmber;
    }

    public void setAadharNUmber(long aadharNUmber) {
        this.aadharNUmber = aadharNUmber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", name='" + name + '\'' +
                ", aadharNUmber=" + aadharNUmber +
                ", amount=" + amount +
                '}';
    }
}
