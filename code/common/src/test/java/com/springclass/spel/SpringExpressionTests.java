package com.springclass.spel;

import com.springclass.configuration.SpelConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = SpelConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringExpressionTests {

    @Test
    public void noop(){
        //DOH!
    }

} // The End...
