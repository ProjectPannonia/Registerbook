package com.registerbook.registerbook.junitTest.memberControllerTest;

import com.registerbook.registerbook.controller.MemberController;
import com.registerbook.registerbook.service.register.MemberServiceImplementation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class WriteMembersToFileTest {
    @Mock
    MemberServiceImplementation memberServiceImplementation;
    @InjectMocks
    MemberController memberController;

    @Before
    public void init(){

    }
    @Test
    public void test1(){

    }
    @After
    public void setToNull(){

    }
}
