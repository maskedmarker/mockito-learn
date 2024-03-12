package org.example.learn.mockito.basic;

import org.example.learn.mockito.dao.StockDao;
import org.example.learn.mockito.model.po.Stock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * when-then/do-when这两种范式使用不同的场景.
 *
 * when-then范式
 * 1.不适用返回值为void的方法(因为when的入参不能是Void)
 * 2.可以链式追加then-then-then,根据调用次数不同表现不同行为
 * 3.无法避免在when中发生方法副作用.
 *
 * do-when范式
 * 1.返回值为void的场景也使用
 * 2.无法链式追加then-then-then,do-when会覆盖前面定义的行为.
 * 3.可以避免在when中发生方法副作用(因为就没有发生方法调用).
 *
 */
public class WhenThen0Test {

    @Mock
    private StockDao stockDao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void test0() {
        // Required type: T Provided: void 当方法的返回类型是void的时候,就需要do-when范式
//        when(stockDao.update(null)).thenReturn(any());
        doThrow(RuntimeException.class).when(stockDao).update(any());
    }

    @Test
    public void test1() {
        // 第一次定义行为
        doThrow(RuntimeException.class).when(stockDao).update(any());
        // 覆盖第一次的行为定义
        doThrow(IllegalStateException.class).when(stockDao).update(any());

        assertThrows(IllegalStateException.class, () -> stockDao.update(null));
    }

    @Test
    public void test10() {
        // then-then-then的写法范式不会覆盖前面的定义
        // 定义调用第1次/第2次/第3+次的返回
        Stock first = new Stock("-1", null, -1);
        Stock second = new Stock("-2", null, -2);
        when(stockDao.query(anyInt()))
                .thenReturn(first)
                .thenReturn(second)
                .thenThrow(IllegalArgumentException.class);

        assertEquals(first, stockDao.query(anyInt()));
        assertEquals(second, stockDao.query(anyInt()));
        assertThrows(IllegalArgumentException.class, () -> stockDao.query(0));
        assertThrows(IllegalArgumentException.class, () -> stockDao.query(0));
        assertThrows(IllegalArgumentException.class, () -> stockDao.query(0));
        assertThrows(IllegalArgumentException.class, () -> stockDao.query(0));
    }

    @Test
    public void test2() {
        doThrow(RuntimeException.class)
                .when(stockDao)
                .query(anyInt());

        // 前面已经定义了,当调用query方法时会抛异常,所以下面的when-then的when会抛异常,这时候就需要do-when范式了.
        when(stockDao.query(anyInt()))
                .thenThrow(IllegalStateException.class);
//        doThrow(IllegalStateException.class)
//                .when(stockDao)
//                .query(anyInt());
//
//        assertThrows(IllegalStateException.class, () -> stockDao.query(0));
    }
}
