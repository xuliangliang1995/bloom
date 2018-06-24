package com.bloom.domain.gardener.meta;

public enum HighGradeRole {
	Administrator(2);
	private int roleId;

	private HighGradeRole(int roleId) {
		this.roleId = roleId;
	}
	
	public int value() {
		return roleId;
	}

}
