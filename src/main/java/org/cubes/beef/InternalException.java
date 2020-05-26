package org.cubes.beef;

import org.cubes.beef.Beef.UncheckedException;

public class InternalException extends UncheckedException {
    private static final long serialVersionUID = 7653984050828149589L;

    public InternalException(org.cubes.beef.Error.Builder error, Throwable cause) {
        super(error, cause);
    }

    public InternalException(org.cubes.beef.Error.Builder error) {
        super(error);
    }

    public InternalException(Throwable cause) {
        super(cause);
    }

    public InternalException() {
    }
}