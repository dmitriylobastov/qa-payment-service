package ru.stepup.payments;

public abstract class PaymentMethod {

    protected final String ownerName;

    public PaymentMethod(String ownerName) {
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Имя владельца не может быть пустое");
        }
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public abstract String getType();

    public void printInfo() {
        System.out.println("Тип: " + getType() + ", Владелец: " + ownerName);
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "ownerName='" + ownerName + '\'' +
                '}';
    }
}
