package com.alexnedelcu.wildfirenavigator.data;

import com.alexnedelcu.wildfirenavigator.data.type.Fire;

public abstract class FireFilter {
	public abstract boolean isValid(Fire fire);  
}
