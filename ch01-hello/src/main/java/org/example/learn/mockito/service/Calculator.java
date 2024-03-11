package org.example.learn.mockito.service;

import java.util.stream.DoubleStream;

public class Calculator {

    public double avg(double... doubles) {
        return DoubleStream.of(doubles).average().getAsDouble();
    }
}
