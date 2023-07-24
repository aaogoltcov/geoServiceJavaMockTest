package ru.netology.i18n;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LocalizationServiceImplTest {
    public LocalizationServiceImpl localizationService;

    public static Stream<Arguments> localizationServiceArguments() {
        return Stream.of(
            Arguments.of(Country.USA, "Welcome"),
            Arguments.of(Country.GERMANY, "Welcome"),
            Arguments.of(Country.BRAZIL, "Welcome"),
            Arguments.of(Country.USA, "Welcome"),
            Arguments.of(Country.RUSSIA, "Добро пожаловать")
        );
    }

    @BeforeEach
    public void initTest() {
        this.localizationService = new LocalizationServiceImpl();
    }

    @AfterEach
    public void finalizeTest() {
        this.localizationService = null;
    }

    @ParameterizedTest
    @MethodSource("localizationServiceArguments")
    public void testLocale(Country country, String expected) {
        // when
        final String actual = this.localizationService.locale(country);

        // assert
        assertThat(expected, is(actual));
    }
}
