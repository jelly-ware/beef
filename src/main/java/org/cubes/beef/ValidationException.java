package org.cubes.beef;

import javax.ws.rs.core.Response;

import org.cubes.beef.Beef.UncheckedException;

public class ValidationException extends UncheckedException {
    private static final long serialVersionUID = 7653984050828149589L;

    @Override
    public Response.Status httpStatus() {
        return Response.Status.BAD_REQUEST;
    }

    public ValidationException(org.cubes.beef.Error.Builder error, Throwable cause) {
        super(error, cause);
    }

    public ValidationException(org.cubes.beef.Error.Builder error) {
        super(error);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException() {
    }
}