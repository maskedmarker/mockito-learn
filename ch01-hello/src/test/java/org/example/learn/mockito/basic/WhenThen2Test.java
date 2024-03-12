package org.example.learn.mockito.basic;

import org.junit.Test;

import java.util.AbstractList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

/**
 * when the method return type is not void, we can use when().thenX().
 * when the method return type is void, we can use doX().when().m()
 *
 */
public class WhenThen2Test {

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
    public void whenSimpleReturnBehaviourConfigured_thenCorrect() {
        final MyList listMock = mock(MyList.class);
        when(listMock.add(anyString())).thenReturn(false);

        final boolean added = listMock.add(randomAlphabetic(6));
        assertFalse(added);
    }

    @Test
    public void whenSimpleReturnBehaviourConfiguredAlternatively_thenCorrect() {
        final MyList listMock = mock(MyList.class);
        doReturn(false).when(listMock).add(anyString());

        final boolean added = listMock.add(randomAlphabetic(6));
        assertFalse(added);
    }

    @Test
    public void givenMethodIsConfiguredToThrowException_whenCallingMethod_thenExceptionIsThrown() {
        final MyList listMock = mock(MyList.class);
        when(listMock.add(anyString())).thenThrow(IllegalStateException.class);

        assertThrows(IllegalStateException.class, () -> listMock.add(randomAlphabetic(6)));
    }

    @Test
    public void givenMethodHasNoReturnType_whenCallingMethod_thenExceptionIsThrown() {
        final MyList listMock = mock(MyList.class);
        doThrow(NullPointerException.class).when(listMock).clear();

        assertThrows(NullPointerException.class, () -> listMock.clear());
    }

    @Test
    public void givenBehaviorIsConfiguredToThrowExceptionOnSecondCall_whenCallingTwice_thenExceptionIsThrown() {
        final MyList listMock = mock(MyList.class);
        when(listMock.add(anyString()))
                .thenReturn(false)
                .thenThrow(IllegalStateException.class);

        assertThrows(IllegalStateException.class, () -> {
            listMock.add(randomAlphabetic(6));
            listMock.add(randomAlphabetic(6));
        });
    }

    @Test(expected = Test.None.class)
    public void givenBehaviorIsConfiguredToThrowExceptionOnSecondCall_whenCallingOnlyOnce_thenNoExceptionIsThrown() {
        final MyList listMock = mock(MyList.class);
        when(listMock.add(anyString()))
                .thenReturn(false)
                .thenThrow(IllegalStateException.class);

        listMock.add(randomAlphabetic(6));
//        assertThatNoException().isThrownBy(() -> listMock.add(randomAlphabetic(6)));
    }

    @Test
    public void whenSpyBehaviourConfigured_thenCorrect() {
        final MyList instance = new MyList();
        final MyList spy = spy(instance);

        doThrow(NullPointerException.class).when(spy).size();

        assertThrows(NullPointerException.class, () -> spy.size());
    }

    @Test
    public void whenMockMethodCallIsConfiguredToCallTheRealMethod_thenRealMethodIsCalled() {
        final MyList listMock = mock(MyList.class);
        when(listMock.size()).thenCallRealMethod();

//        assertThat(listMock).hasSize(1);
        assertEquals(1, listMock.size());
    }

    @Test
    public void whenMockMethodCallIsConfiguredWithCustomAnswer_thenCustomerAnswerIsCalled() {
        final MyList listMock = mock(MyList.class);
        doAnswer(invocation -> "Always the same").when(listMock).get(anyInt());

        final String element = listMock.get(1);
//        assertThat(element).isEqualTo("Always the same");
        assertEquals("Always the same", element);
    }
}
