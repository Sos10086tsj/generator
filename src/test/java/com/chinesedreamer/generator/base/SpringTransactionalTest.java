package com.chinesedreamer.generator.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class SpringTransactionalTest extends AbstractTransactionalJUnit4SpringContextTests{

}
