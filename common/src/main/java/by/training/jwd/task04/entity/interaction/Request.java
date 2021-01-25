package by.training.jwd.task04.entity.interaction;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    private static final long serialVersionUID = 7140131267089837865L;
    private static final Request TEST_REQUEST = new Request(TransferType.TEST);

    private TransferType transferType;
    private String parameters;

    public Request() {
    }

    public Request(String parameters) {
        this.parameters = parameters;
    }

    private Request(TransferType transferType) {
        this.transferType = transferType;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public static Request getTestRequest() {
        return TEST_REQUEST;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return transferType == request.transferType
                && Objects.equals(parameters, request.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transferType, parameters);
    }
}
