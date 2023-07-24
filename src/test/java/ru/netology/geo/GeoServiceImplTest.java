package ru.netology.geo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

public class GeoServiceImplTest {
    public GeoServiceImpl geoService;

    public static Stream<Arguments> geoServiceArguments() {
        return Stream.of(
            Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
            Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
            Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
            Arguments.of("172.0.32.111", new Location("Moscow", Country.RUSSIA, null, 0)),
            Arguments.of("96.44.183.159", new Location("New York", Country.USA, null, 0))
        );
    }

    @BeforeEach
    public void initTest() {
        this.geoService = new GeoServiceImpl();
    }

    @AfterEach
    public void finalizeTest() {
        this.geoService = null;
    }

    @ParameterizedTest
    @MethodSource("geoServiceArguments")
    public void testByIp(String ip, Location expected) {
        // when
        final Location actual = this.geoService.byIp(ip);

        // assert
        assertThat(expected, hasProperty("city", equalTo(actual.getCity())));
        assertThat(expected, hasProperty("country", equalTo(actual.getCountry())));
        assertThat(expected, hasProperty("street", equalTo(actual.getStreet())));
        assertThat(expected, hasProperty("building", equalTo(actual.getBuilding())));
    }
}
