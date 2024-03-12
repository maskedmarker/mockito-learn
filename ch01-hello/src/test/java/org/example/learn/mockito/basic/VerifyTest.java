package org.example.learn.mockito.basic;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.exceptions.verification.NoInteractionsWanted;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VerifyTest {

    public class MyList extends AbstractList<String> {
        @Override
        public String get(final int index) {
            return null;
        }

        @Override
        public int size() {
            return 1;
        }
    }

    @Test
    public void test0() {
        // Verify simple invocation on mock
        List<String> mockedList = mock(MyList.class);
        mockedList.size();

        verify(mockedList).size();
    }

    @Test
    public void test1() {
        // Verify the number of interactions with mock
        List<String> mockedList = mock(MyList.class);
        mockedList.size();
        verify(mockedList, times(1)).size();
    }

    @Test
    public void test2() {
        // Verify no interaction with the whole mock occurred
        List<String> mockedList = mock(MyList.class);
        verifyNoInteractions(mockedList);
    }

    @Test
    public void test3() {
        // Verify no interaction with a specific method occurred
        List<String> mockedList = mock(MyList.class);
        verify(mockedList, times(0)).size();
    }

    @Test
    public void test4() {
        // Verify there are no unexpected interactions — this should fail
        List<String> mockedList = mock(MyList.class);
        mockedList.size();
        mockedList.clear();

        verify(mockedList).size();
        assertThrows(NoInteractionsWanted.class, () -> verifyNoMoreInteractions(mockedList));
    }

    @Test
    public void test5() {
        // Verify the order of interactions
        List<String> mockedList = mock(MyList.class);
        mockedList.size();
        mockedList.add("a parameter");
        mockedList.clear();

        InOrder inOrder = Mockito.inOrder(mockedList);
        inOrder.verify(mockedList).size();
        inOrder.verify(mockedList).add("a parameter");
        inOrder.verify(mockedList).clear();
    }

    @Test
    public void test7() {
        // Verify an interaction has occurred at least a certain number of times
        List<String> mockedList = mock(MyList.class);
        mockedList.clear();
        mockedList.clear();
        mockedList.clear();

        verify(mockedList, atLeast(1)).clear();
        verify(mockedList, atMost(10)).clear();
    }

    @Test
    public void test8() {
        // Verify interaction with the exact argument
        List<String> mockedList = mock(MyList.class);
        mockedList.add("test");

        verify(mockedList).add("test");
    }

    @Test
    public void test9() {
        // Verify interaction with flexible/any argument
        List<String> mockedList = mock(MyList.class);
        mockedList.add("test");

        verify(mockedList).add(anyString());
    }

    @Test
    public void test6() {
        // Verify interaction using argument capture
        List<String> mockedList = mock(MyList.class);
        mockedList.add("someElement");

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockedList).add(argumentCaptor.capture());

        String capturedArgument = argumentCaptor.getValue();
        assertEquals(capturedArgument, "someElement");

    }
}
