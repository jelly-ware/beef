package org.cubes.beef;

import org.cubes.beef.Beef.UncheckedException;

public class CustomException extends UncheckedException {
    private static final long serialVersionUID = 7653984050828149523L;

    public CustomException(org.cubes.beef.Error.Builder error, Throwable cause) {
        super(error, cause);
    }

    public CustomException(org.cubes.beef.Error.Builder error) {
        super(error);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException() {
    }
}