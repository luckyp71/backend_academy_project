package com.training.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimeProvider implements Serializable{

	private static final long serialVersionUID = -4908719579101675971L;

	public Date now() {
        return new Date();
    }
}