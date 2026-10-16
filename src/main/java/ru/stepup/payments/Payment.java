package ru.stepup.payments;

public class Payment {
    private final String cardNumber;

    private final double amount;

    private PaymentStatus status;

    public Payment(String cardNumber, double amount){
        if (cardNumber == null || !cardNumber.matches("\\d{16}")){
            throw new IllegalArgumentException("Номер карты должен состоять из 16 цифр");
        }
        if (amount <=0) {
            throw new IllegalArgumentException("Сумма платежа должна быть больше нуля");
        }
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.status = PaymentStatus.CREATED;
    }

    public void markAsSuccess(){
        if(status !=PaymentStatus.CREATED) {
            throw new IllegalArgumentException("Можно завершить только платёж в статусе CREATED");
        }
        this.status = PaymentStatus.SUCCESS;
    }

    public void markAsFailed(){
        if(status !=PaymentStatus.CREATED) {
            throw new IllegalArgumentException("Можно завершить только платёж в статусе CREATED");
        }
        this.status = PaymentStatus.FAILED;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "cardNumber='" + cardNumber + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                "(" + status.getDescription() + ")" +
                '}';
    }
}
