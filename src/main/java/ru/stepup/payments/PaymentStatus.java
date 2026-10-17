package ru.stepup.payments;

public enum PaymentStatus {
    CREATED("Создан"),
    SUCCESS("Успешно завершён"),
    FAILED("Ошибка");

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
