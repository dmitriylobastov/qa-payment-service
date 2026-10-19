import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.stepup.payments.Payment;
import ru.stepup.payments.PaymentCard;
import ru.stepup.payments.PaymentStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaymentTest {

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment("1234567890123456", 10.58);
    }

    @Test
    void shouldCreatePaymentWithCreatedStatus() {
        assertThat(payment.getStatus())
                .isEqualTo(PaymentStatus.CREATED);
        assertThat(payment.getAmount())
                .isEqualTo(10.58);
        assertThat(payment.getCardNumber())
                .isEqualTo("1234567890123456");
    }

    @Test
    void shouldChangeStatusToSuccess() {
        payment.markAsSuccess();
        assertThat(payment.getStatus())
                .isEqualTo(PaymentStatus.SUCCESS);
    }

    @Test
    void shouldChangeStatusToFailed() {
        payment.markAsFailed();
        assertThat(payment.getStatus())
                .isEqualTo(PaymentStatus.FAILED);
    }

    @Test
    void shouldThrowWhenCardNumberIsNull() {
        assertThatThrownBy(() -> new Payment(null, 100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Номер карты должен состоять из 16 цифр");
    }

    @Test
    void shouldThrowWhenCardNumberHasWrongLength() {
        assertThatThrownBy(() -> new Payment("123", 100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Номер карты должен состоять из 16 цифр");
    }

    @Test
    void shouldThrowWhenAmountIsZero() {
        assertThatThrownBy(() -> new Payment("1234567890123456", 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Сумма платежа должна быть больше нуля");
    }

    @Test
    void shouldThrowWhenAmountIsNegative() {
        assertThatThrownBy(() -> new Payment("1234567890123456", -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Сумма платежа должна быть больше нуля");

    }

    @Test
    void shouldThrowWhenMarkAsSuccessAfterSuccess() {
        payment.markAsSuccess();
        assertThatThrownBy(() -> payment.markAsSuccess())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void shouldThrowWhenMarkAsSuccessAfterFailed() {
        payment.markAsFailed();
        assertThatThrownBy(() -> payment.markAsSuccess())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("FAILED");
    }

    @Test
    void shouldThrowWhenMarkAsFailedAfterSuccess() {
        payment.markAsSuccess();
        assertThatThrownBy(() -> payment.markAsFailed())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("SUCCESS");
    }
}
