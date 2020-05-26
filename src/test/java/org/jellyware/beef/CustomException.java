package org.jellyware.beef;

import org.jellyware.beef.Beef.UncheckedException;

public class CustomException extends UncheckedException {
    private static final long serialVersionUID = 7653984050828149523L;

    public CustomException(org.jellyware.beef.Error.Builder error, Throwable cause) {
        super(error, cause);
    }

    public CustomException(org.jellyware.beef.Error.Builder error) {
        super(error);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException() {
    }
}