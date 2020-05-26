package org.jellyware.beef;

import org.jellyware.beef.Beef.UncheckedException;

public class ConfigException extends UncheckedException {
    private static final long serialVersionUID = -6547172699595389978L;

    public ConfigException(org.jellyware.beef.Error.Builder error, Throwable cause) {
        super(error, cause);
    }

    public ConfigException(org.jellyware.beef.Error.Builder error) {
        super(error);
    }

    public ConfigException(Throwable cause) {
        super(cause);
    }

    public ConfigException() {
    }
}