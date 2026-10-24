package ru.stepup.payments;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.stepup.payments.PaymentGateway;
import ru.stepup.payments.PaymentRepository;
import ru.stepup.payments.PaymentService;
import ru.stepup.payments.Payment;
import ru.stepup.payments.PaymentStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private PaymentRepository repository;

    @InjectMocks
    private PaymentService service;

    @Test
    void shouldReturnSuccessWhenGatewayApproves() {
        when(paymentGateway.charge("1234567890123456", 100)).thenReturn(true);
        Payment result = service.processPayment("1234567890123456", 100);
        assertThat(result.getStatus()).isEqualTo(PaymentStatus.SUCCESS);
        verify(repository).save(result);
    }

    @Test
    void shouldReturnFailedWhenGatewayDeclines() {
        when(paymentGateway.charge("1234567890123400", 50)).thenReturn(false);
        Payment result = service.processPayment("1234567890123400", 50);
        assertThat(result.getStatus()).isEqualTo(PaymentStatus.FAILED);
        verify(repository, never()).save(any());
    }

    @Test
    void shouldCallGatewayWithCorrectArguments() {
        when(paymentGateway.charge("1234567890123456", 100)).thenReturn(true);
        service.processPayment("1234567890123456", 100);
        verify(paymentGateway).charge("1234567890123456", 100);
    }
}
