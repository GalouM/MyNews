package com.galou.mynews;

import android.support.annotation.NonNull;

import org.mockito.configuration.DefaultMockitoConfiguration;
import org.mockito.internal.stubbing.defaultanswers.ReturnsEmptyValues;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import io.reactivex.Observable;
import io.reactivex.Single;

public class MockitoConfiguration extends DefaultMockitoConfiguration {
    public Answer<Object> getDefaultAnswer() {
        return new ReturnsEmptyValues() {
            @Override
            public Object answer(InvocationOnMock inv) {
                Class<?> type = inv.getMethod().getReturnType();
                if (type.isAssignableFrom(Observable.class)) {
                    return Observable.error(createException(inv, "Observable"));
                } else if (type.isAssignableFrom(Single.class)) {
                    return Single.error(createException(inv, "Single"));
                } else if (type.isAssignableFrom(Observable.class)) {
                    return Observable.error(createException(inv, "Observable"));
                } else if (type.isAssignableFrom(Single.class)) {
                    return Single.error(createException(inv, "Single"));
                } else {
                    return super.answer(inv);
                }
            }
        };
    }

    @NonNull
    private RuntimeException createException(InvocationOnMock invocation, String className) {
        String s = invocation.toString();
        return new RuntimeException("No mock defined for invocation " + s +
                "\nwhen(" + s.substring(0, s.length() - 1) +
                ").thenReturn(" + className + ".just());");
    }
}
