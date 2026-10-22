package ru.stepup.payments;

/**
 * Принимает и оркестрирует платежи
 */
public class PaymentService {

    private final PaymentGateway gateway;

    private final PaymentRepository repository;

    /**
     * Создаёт сервис с заданными зависимостями.
     *
     * @param gateway           платежный шлюз
     * @param paymentRepository сохранение платежей
     */
    public PaymentService(PaymentGateway gateway, PaymentRepository paymentRepository) {
        if (gateway == null) {
            throw new IllegalArgumentException("Платежный шлюз не может отсутствовать");
        }
        if (paymentRepository == null) {
            throw new IllegalArgumentException("Платеж не может отсутствовать");
        }
        this.gateway = gateway;
        this.repository = paymentRepository;
    }

    /**
     * Запускает процесс оплаты
     *
     * @param cardNumber номер карты (16 цифр)
     * @param amount     сумма платежа (больше нуля)
     * @return возвращает платеж в финальном статусе
     */

    public Payment processPayment(String cardNumber, double amount) {
        Payment payment = new Payment(cardNumber, amount);
        boolean success = gateway.charge(cardNumber, amount);
        if (success) {
            payment.markAsSuccess();
            repository.save(payment);
        } else {
            payment.markAsFailed();
        }
        return payment;

    }
}
