package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Lorden on 04.02.2024
 */

@Component
public class Worker {

    @RecoverException()
    public int work() {
        System.out.println("work, work...");
        return 18 / 0;
    }

    @Timer

    public void lunch() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("Eat " + i);
            Thread.sleep(100);
        }
    }

    @Timer
    @RecoverException(noRecoverFor = {IndexOutOfBoundsException.class})
//    @RecoverException
    public void rest() throws InterruptedException {
        System.out.println("I tyt");
        Thread.sleep(1000);
        throw new ArrayIndexOutOfBoundsException();

    }

}
