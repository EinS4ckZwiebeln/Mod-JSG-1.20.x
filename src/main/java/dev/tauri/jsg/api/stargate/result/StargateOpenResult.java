package dev.tauri.jsg.api.stargate.result;

public enum StargateOpenResult {
	OK,
	NOT_ENOUGH_POWER,
	ADDRESS_MALFORMED,
	ABORTED,
	ABORTED_BY_EVENT,
	CALLER_HUNG_UP,
	GATE_BURRIED,
	TARGET_GATE_BURRIED,
	SAME_DIMENSION,
	SYMBOL_ADDED;
	
	public boolean ok() {
		return this == OK;
	}
}
