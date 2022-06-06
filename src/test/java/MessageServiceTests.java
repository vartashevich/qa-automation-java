import com.tcs.edu.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.helper.Doubling;
import com.tcs.edu.helper.MessageOrder;
import com.tcs.edu.helper.Severity;
import com.tcs.edu.processor.OrderedDistinctedMessageService;
import com.tcs.edu.repository.HashMapMessageRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Created on 01.06.2022
 * Класс с тестами обработки Сообщений
 *
 * @author Viktor Artashevich
 */
public class MessageServiceTests {
    private Message m1;
    private OrderedDistinctedMessageService service;

    @BeforeEach
    public void setUp() {
        m1 = new Message("Hello1!", Severity.MAJOR);
        service = new OrderedDistinctedMessageService(
                new HashMapMessageRepository());
    }

    @Nested
    public class DoubleMessageTest {
        @Test
        public void doubleNullMessageTest() throws LogException {
            //region Act
            service.logMessage(MessageOrder.ASC, Doubling.DOUBLES, m1);
            //endregion
            //region Then
            //   Assertions.assertThat(service.findAll()).containsExactlyInAnyOrder(m1, m1);
            Assertions.assertThatThrownBy(() -> {
                        service.logMessage(null);
                    })
                    .hasMessage("Невалидный параметр")
                    .isInstanceOf(LogException.class);
            //endregion
        }

        @Test
        public void doubleNotNullMessageTest() throws LogException {
            //region Act
            service.logMessage(MessageOrder.ASC, Doubling.DOUBLES, m1);
            //endregion
            //region Then
            Assertions.assertThat(service.findAll()).containsExactlyInAnyOrder(m1, m1);
            //endregion
        }
    }

    @Nested
    public class DistinctMessageTest {
        @Test
        public void distinctMessageTest() throws LogException {
            //region Act
            service.logMessage(MessageOrder.ASC, Doubling.DISTINCT, m1);
            //endregion
            //region Then
            Assertions.assertThat(service.findAll()).containsExactlyInAnyOrder(m1);
            //endregion
        }
    }

    @Nested
    public class DefaultMessageTest {
        @Test
        public void defaultMessageTest() throws LogException {
            //region Act
            service.logMessage(MessageOrder.ASC, Doubling.DEFAULT, m1);
            //endregion
            //region Then
            Assertions.assertThat(service.findAll()).containsExactlyInAnyOrder(m1);
            //endregion
        }
    }
}

