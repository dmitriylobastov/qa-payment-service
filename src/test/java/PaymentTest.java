import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.stepup.payments.Payment;
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
    void shouldCreatePaymentWithCreatedStatus(){
        assertThat(payment.getStatus())
                .isEqualTo(PaymentStatus.CREATED);
        assertThat(payment.getAmount())
                .isEqualTo(10.58);
        assertThat(payment.getCardNumber())
                .isEqualTo("1234567890123456");
    }

}
