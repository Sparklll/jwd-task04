package by.training.jwd.task04.entity.interaction;

import java.io.Serializable;
import java.util.Objects;

public class Response implements Serializable {
    private static final long serialVersionUID = 5356979895754657259L;
    private static final Response TEST_RESPONSE = new Response(TransferType.TEST);

    private TransferType transferType = TransferType.DATA;
    private String parameters = "";

    public Response() {
    }

    public Response(String parameters) {
        this.parameters = parameters;
    }

    public Response(TransferType transferType) {
        this.transferType = transferType;
    }

    public Response(TransferType transferType, String parameters) {
        this.transferType = transferType;
        this.parameters = parameters;
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
