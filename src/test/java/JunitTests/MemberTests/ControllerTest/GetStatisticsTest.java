package JunitTests.MemberTests.ControllerTest;

import com.registerbook.registerbook.controller.MemberController;
import com.registerbook.registerbook.service.register.MemberServiceImplementation;
import com.registerbook.registerbook.service.register.statistics.StatisticData;
import com.registerbook.registerbook.service.register.statistics.UpdatedStatistic.CountryAndQuantity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class GetStatisticsTest {
    @Mock
    MemberServiceImplementation memberServiceImplementation;
    @InjectMocks
    MemberController memberController;

    StatisticData testStatisticData;
    List<CountryAndQuantity> testCountryAndQuantity;

    @Before
    public void init(){
        testCountryAndQuantity = new ArrayList<>();
        testCountryAndQuantity.add(new CountryAndQuantity("Hungary",3));
        testCountryAndQuantity.add(new CountryAndQuantity("USA",5));
        testCountryAndQuantity.add(new CountryAndQuantity("USA",5));

        testStatisticData = new StatisticData(300,20,testCountryAndQuantity,10);
    }
    @Test
    public void test_getStatistics(){
        when(memberServiceImplementation.getStatistics()).thenReturn(testStatisticData);

        ResponseEntity response = memberController.getStatistics();
        HttpStatus responseStatus = response.getStatusCode();
        Object responseStatisticData = response.getBody();

        assertFalse(response == null);
        assertFalse(responseStatus == null);
        assertFalse(responseStatisticData == null);
        assertTrue(response instanceof ResponseEntity);
        assertTrue(responseStatisticData instanceof StatisticData);
        assertTrue(responseStatus instanceof HttpStatus);
        assertEquals(responseStatus,HttpStatus.OK);
        assertEquals(responseStatisticData,testStatisticData);

        verify(memberServiceImplementation,times(1)).getStatistics();
    }
    @After
    public void setToNull(){
        memberServiceImplementation = null;
        memberController = null;
        testStatisticData = null;
        testCountryAndQuantity = null;
    }
}
