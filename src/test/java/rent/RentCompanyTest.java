package rent;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RentCompanyTest {

    @DisplayName("팩토리 메소드로 회사 인스턴스를 만든다.")
    @Test
    public void createCompanyWithFactoryMethod() {
        //then
        Assertions.assertAll(
                () -> assertThatCode(RentCompany::create),
                () -> assertThat(RentCompany.create()).isInstanceOf(RentCompany.class)
        );
    }
}
