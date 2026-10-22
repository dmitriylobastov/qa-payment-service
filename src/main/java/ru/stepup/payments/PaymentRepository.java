package ru.stepup.payments;

import java.util.Optional;

public interface PaymentRepository {

    /**
     * Сохраняет платёж в хранилище.
     *
     * @param payment платёж для сохранения; не {@code null}
     */
    void save(Payment payment);

    /**
     * Ищет платёж по идентификатору.
     *
     * @param id идентификатор платежа
     * @return {@link Optional} с платежом, если найден; пустой {@link Optional}, если нет
     */
    Optional<Payment> findById(long id);
}
