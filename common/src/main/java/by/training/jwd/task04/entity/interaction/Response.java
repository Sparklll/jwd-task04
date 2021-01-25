package by.training.jwd.task04.entity.interaction;

import java.io.Serializable;
import java.util.Objects;

public class Response implements Serializable {
    private static final long serialVersionUID = 5356979895754657259L;
    private static final Response TEST_RESPONSE = new Response(TransferType.TEST);

    private TransferType transferType = TransferType.DATA;
    private String parameters;

    public Response() {
    }

    public Response(String parameters) {
        this.parameters = parameters;
    }

    private Response(TransferType transferType) {
        this.transferType = TransferType.TEST;
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

    public static Response getTestResponse() {
        return TEST_RESPONSE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return transferType == response.transferType
                && Objects.equals(parameters, response.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transferType, parameters);
    }
}
