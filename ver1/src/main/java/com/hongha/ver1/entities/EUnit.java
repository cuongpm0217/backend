package com.hongha.ver1.entities;

public enum EUnit {
	EUnit_PIECE("cái"),
	EUnit_SET("bộ"),
	EUnit_WORK("công");
	public final String label;

    private EUnit(String label) {
        this.label = label;
    }
}
