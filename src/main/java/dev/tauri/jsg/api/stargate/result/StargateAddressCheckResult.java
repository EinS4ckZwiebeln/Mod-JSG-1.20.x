package dev.tauri.jsg.api.stargate.result;

public enum StargateAddressCheckResult {
    OK,
    MALFORMED,
    TARGET_BUSY,
    NOT_ENOUGH_POWER,
    SAME_DIMENSION;

    public StargateOpenResult toOpenResult() {
        return switch (this) {
            case OK -> StargateOpenResult.OK;
            case MALFORMED -> StargateOpenResult.ADDRESS_MALFORMED;
            case TARGET_BUSY -> StargateOpenResult.CALLER_HUNG_UP;
            case NOT_ENOUGH_POWER -> StargateOpenResult.NOT_ENOUGH_POWER;
            case SAME_DIMENSION -> StargateOpenResult.SAME_DIMENSION;
        };
    }

    public StargateConnectResult toConnectResult() {
        return switch (this) {
            case OK -> StargateConnectResult.OK;
            case MALFORMED -> StargateConnectResult.ADDRESS_MALFORMED_SGN_OK;
            case TARGET_BUSY -> StargateConnectResult.TARGET_BUSY;
            case NOT_ENOUGH_POWER -> StargateConnectResult.NOT_ENOUGH_POWER;
            case SAME_DIMENSION -> StargateConnectResult.SAME_DIMENSION;
        };
    }
}
