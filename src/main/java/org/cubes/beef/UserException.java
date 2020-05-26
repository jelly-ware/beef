package org.cubes.beef;

import javax.ws.rs.core.Response;

import org.cubes.beef.Beef.UncheckedException;

public class UserException extends UncheckedException {
    private static final long serialVersionUID = 7918826341720024388L;

    @Override
    public Response.Status httpStatus() {
        return Response.Status.UNAUTHORIZED;
    }

    public UserException(org.cubes.beef.Error.Builder error, Throwable cause) {
        super(error, cause);
    }

    public UserException(org.cubes.beef.Error.Builder error) {
        super(error);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    public UserException() {
    }
}