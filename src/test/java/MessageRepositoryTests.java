import com.tcs.edu.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.helper.Severity;
import com.tcs.edu.processor.OrderedDistinctedMessageService;
import com.tcs.edu.repository.HashMapMessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Created on 01.06.2022
 * Класс с тестами поиска Сообщений
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
        public void findMessageByCorrectKey() {
            //region Act
            try {
                service.logMessage(m1);
            } catch (LogException e) {
                e.printStackTrace();
            }
            final UUID key = m1.getId();
            //endregion
            //region Then
            Assertions.assertTrue(m1.equals(service.findByPrimaryKey(key)));
            //endregion
        }

        @Test
        public void findMessageByWrongKey() {
            //region Act
            try {
                service.logMessage(m1);
            } catch (LogException e) {
                e.printStackTrace();
            }
            final UUID key = UUID.randomUUID();
            //endregion
            //region Then
            Assertions.assertFalse(m1.equals(service.findByPrimaryKey(key)));
            //endregion
        }
    }

    @Test
    public void findAllMessages() {
        //region Given
        Message m2 = new Message("Hello!", Severity.MINOR);
        //endregion
        //region Act
        try {
            service.logMessage(m1);
            service.logMessage(m2);
        } catch (LogException e) {
            e.printStackTrace();
        }
        //region Then
        org.assertj.core.api.Assertions.assertThat(service.findAll()).contains(m1)
                .contains(m2)
                .hasSize(2);
        //endregion
    }

    @Nested
    public class FindMessagesBySeverity {
        @Test
        public void findMessageByExistedSeverity() {
            //region Given
            Message m2 = new Message("Hello!", Severity.MAJOR);
            Message m3 = new Message("Hello!", Severity.MAJOR);
            //endregion
            //region Act
            try {
                service.logMessage(m1);
                service.logMessage(m2);
                service.logMessage(m3);
            } catch (LogException e) {
                e.printStackTrace();
            }
            //endregion
            //region Then
            org.assertj.core.api.Assertions.assertThat(service.findBySeverity(Severity.MAJOR)).
                    containsExactlyInAnyOrder(m2, m3);
            //endregion
        }

        @Test
        public void findMessageByNotExistedSeverity() {
            //region Given
            Message m2 = new Message("Hello!", Severity.MAJOR);
            //endregion
            //region Act
            try {
                service.logMessage(m1);
                service.logMessage(m2);
            } catch (LogException e) {
                e.printStackTrace();
            }
            //endregion
            //region Then
            org.assertj.core.api.Assertions.assertThat(service.findBySeverity(Severity.MINOR)).isEmpty();
            //endregion
        }
    }
}
