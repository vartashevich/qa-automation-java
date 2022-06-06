import com.tcs.edu.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.helper.Severity;
import com.tcs.edu.processor.OrderedDistinctedMessageService;
import com.tcs.edu.repository.HashMapMessageRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Created on 01.06.2022
 * Класс с тестами поиска Сообщений
 *
 * @author Viktor Artashevich
 */
@Nested
public class MessageRepositoryTests {
    private Message m1;
    private OrderedDistinctedMessageService service;

    @BeforeEach
    public void setUp() {
        m1 = new Message("Hello!", Severity.REGULAR);
        service = new OrderedDistinctedMessageService(
                new HashMapMessageRepository());
    }

    @Nested
    public class FindMessageByKey {
        @Test
        public void findMessageByCorrectKey() throws LogException {
            //region Act
            service.logMessage(m1);
            final UUID key = m1.getId();
            //endregion
            //region Then
            Assertions.assertThat(m1.equals(service.findByPrimaryKey(key))).isTrue();
            //endregion
        }

        @Test
        public void findMessageByWrongKey() throws LogException {
            //region Act
            service.logMessage(m1);
            final UUID key = UUID.randomUUID();
            //endregion
            //region Then
            Assertions.assertThat(service.findByPrimaryKey(key)).isNull();
            //endregion
        }
    }

    @Test
    public void findAllMessages() throws LogException {
        //region Given
        Message m2 = new Message("Hello!", Severity.MINOR);
        //endregion
        //region Act
        service.logMessage(m1);
        service.logMessage(m2);
        //region Then
        Assertions.assertThat(service.findAll()).contains(m1)
                .contains(m2)
                .hasSize(2);
        //endregion
    }

    @Nested
    public class FindMessagesBySeverity {
        @Test
        public void findMessageByExistedSeverity() throws LogException {
            //region Given
            Message m2 = new Message("Hello!", Severity.MAJOR);
            Message m3 = new Message("Hello!", Severity.MAJOR);
            //endregion
            //region Act
            service.logMessage(m1);
            service.logMessage(m2);
            service.logMessage(m3);
            //endregion
            //region Then
            Assertions.assertThat(service.findBySeverity(Severity.MAJOR)).
                    containsExactlyInAnyOrder(m2, m3);
            //endregion
        }

        @Test
        public void findMessageByNotExistedSeverity() throws LogException {
            //region Given
            Message m2 = new Message("Hello!", Severity.MAJOR);
            //endregion
            //region Act
            service.logMessage(m1);
            service.logMessage(m2);
            //endregion
            //region Then
            Assertions.assertThat(service.findBySeverity(Severity.MINOR)).isEmpty();
            //endregion
        }
    }
}
