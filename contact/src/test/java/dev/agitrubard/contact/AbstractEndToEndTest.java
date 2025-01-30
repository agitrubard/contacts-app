package dev.agitrubard.contact;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@AutoConfigureMockMvc
@Profile("test")
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public abstract class AbstractEndToEndTest {

    protected final CustomMockMvc customMockMvc;

}
