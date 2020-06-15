package com.vanhalen.logic;

import com.vanhalen.domain.Skittle;
import com.vanhalen.interfaces.MqttServiceInterface;
import com.vanhalen.repositories.SkittleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SkittleLogicTest {

    @InjectMocks
    private SkittleLogic _skittleLogic;

    @Mock
    private SkittleRepository _skittleRepository;

    @Mock
    MqttServiceInterface _mqttService;

    @Test
    public void getSkittles_Returns_Correct_Amount_Per_Color() throws Exception {
        //Arrange
        Skittle expectedResult = new Skittle(1, 2, 3, 4, 5);

        Mockito.when(_skittleRepository.findAll()).thenReturn(Arrays.asList(expectedResult));

        //Act
        Skittle actualResult = _skittleLogic.getSkittles();

        //Assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void sortSkittles_Sends_Mqtt_Message_And_Updates_Database() throws Exception {
        //Arrange
        Skittle skittle = new Skittle(5, 5, 5, 5, 5);
        Skittle sortSkittle = new Skittle(1, 1, 1, 1, 1);

        Mockito.when(_skittleRepository.findAll()).thenReturn(Arrays.asList(skittle));
        Mockito.doNothing().when(_mqttService).publishMessage(anyString(), any(byte[].class));

        //Act
        boolean actualResult = _skittleLogic.sortSkittles(sortSkittle);

        //Assert
        assertThat(actualResult).isEqualTo(true);
        assertThat(skittle.toString()).isEqualTo(new Skittle(4, 4, 4, 4, 4).toString());
    }
}
