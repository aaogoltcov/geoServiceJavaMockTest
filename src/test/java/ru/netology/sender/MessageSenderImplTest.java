package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {
    @Test
    public void testMessageSenderForRussia() {
        // given
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        final String givenIpAddress = "172.123.12.19";
        final Location expectedLocation = new Location("Moscow", Country.RUSSIA, null, 0);
        final String expected = "Добро пожаловать";
        Map<String, String> headers = new HashMap<>();

        // when
        Mockito.when(geoService.byIp(givenIpAddress)).thenReturn(expectedLocation);
        Mockito.when(localizationService.locale(expectedLocation.getCountry())).thenReturn(expected);
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, givenIpAddress);

        final String actual = messageSender.send(headers);

        //assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMessageSenderForInternational() {
        // given
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        final String givenIpAddress = "96.123.12.19";
        final Location expectedLocation = new Location("New York", Country.USA, null, 0);
        final String expected = "Welcome";
        Map<String, String> headers = new HashMap<>();

        // when
        Mockito.when(geoService.byIp(givenIpAddress)).thenReturn(expectedLocation);
        Mockito.when(localizationService.locale(expectedLocation.getCountry())).thenReturn(expected);
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, givenIpAddress);

        final String actual = messageSender.send(headers);

        //assert
        Assertions.assertEquals(expected, actual);
    }
}
