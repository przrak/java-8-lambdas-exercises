package com.insightfullogic.java8.exercises.chapter2;

import com.insightfullogic.java8.exercises.Exercises;

import javax.swing.*;
import javax.swing.text.DateFormatter;

import java.text.SimpleDateFormat;

import static java.lang.ThreadLocal.withInitial;

public class Question2
{

    public static ThreadLocal<DateFormatter> formatter
        = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));

    public static void main(String[] args)
    {
        JButton button = new JButton();
        button.addActionListener(event ->
            System.out.println(event.getActionCommand()));
    }


}
