package co.edu.uniajc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class EntityServiceTest {

    @Mock
    private EntityRepository entityRepositoryMock;

    private EntityService entityService;

    @BeforeEach
    void setUp() {
        entityService = new EntityService(entityRepositoryMock);
    }

    @Test
    void When_CreateEntity_Expect_CreateEntity_In_BD() {
        var entity = EntityModel.builder()
                .id(1L)
                .name("prueba")
                .description("Descripción de prueba")
                .category("Categoría A")
                .quantity(10)
                .price(BigDecimal.valueOf(99.99))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        given(entityRepositoryMock.save(entity)).willReturn(entity);

        entityService.create(entity);
    }

}