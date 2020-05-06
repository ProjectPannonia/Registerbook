package com.registerbook.registerbook;

import com.registerbook.registerbook.repository.MemberJpaRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

public class TestRepositoryFunctions {

    @Before
    public void init(){
        MemberJpaRepository mjr = Mockito.mock(MemberJpaRepository.class);
    }

    @Test
    public void test(){
        //when(mjr.)
    }
}
