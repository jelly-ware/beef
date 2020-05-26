package org.jellyware.beef;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.jellyware.beef.Beef.UncheckedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * @author Jotter
 *
 */
@DisplayName("Beef")
class BeefSpec {
    @Test
    @DisplayName("wrap should return identity for objects assignable to UncheckedException")
    void wrapBeef() {
        try {
            throw Beef.config().build();
        } catch (Exception e) {
            assertThat(e).isEqualTo(Beef.wrap(e));
        }
    }

    @Test
    @DisplayName("wrap should return Beef for objects not assignable to UncheckedException")
    void wrapException() {
        try {
            throw new NullPointerException();
        } catch (Exception e) {
            assertThat(Beef.class).isAssignableFrom(Beef.wrap(e).getClass());
        }
    }

    @Test
    @DisplayName("cause hierarchy must be consistent with Exception and Error")
    void consistentErrorHierachy() {
        try {
            nestedBeef();
        } catch (Exception e) {
            // throwable
            var throwableCount = 0;
            Throwable throwable = e;
            while (throwable.getCause() != null) {
                throwableCount = throwableCount + 1;
                throwable = throwable.getCause();
            }
            // error
            int errorCount = 0;
            var error = Beef.wrap(e).error();
            while (error.getCause() != null) {
                errorCount++;
                error = error.getCause();
            }
            assertThat(throwableCount).isGreaterThan(0);
            assertThat(throwableCount).isEqualTo(errorCount);
        }
    }

    @Test
    @DisplayName("uncheck should return identity for unchecked exception")
    void uncheck() {
        var exception = new ArithmeticException();
        assertThat(exception).isEqualTo(Beef.uncheck(exception));
    }

    @Test
    @DisplayName("uncheck should return UncheckedException for checked exception")
    void uncheckForChecked() {
        assertThat(UncheckedException.class).isEqualTo(Beef.uncheck(new FileNotFoundException()).getClass());
    }

    @Nested
    @DisplayName("convenience builder")
    class ConvenienceBuilder {
        @Test
        @DisplayName("config should throw ConfigException")
        void config() {
            assertThrows(ConfigException.class, () -> {
                throw Beef.config().build();
            });
        }

        @Test
        @DisplayName("internal should throw InternalException")
        void internal() {
            assertThrows(InternalException.class, () -> {
                throw Beef.internal().build();
            });
        }

        @Test
        @DisplayName("user should throw UserException")
        void user() {
            assertThrows(UserException.class, () -> {
                throw Beef.user().build();
            });
        }

        @Test
        @DisplayName("validation should throw ValidationException")
        void validation() {
            assertThrows(ValidationException.class, () -> {
                throw Beef.validation().build();
            });
        }

        @Test
        @DisplayName("for custom exceptions should throw exception of provided class")
        void custom() {
            assertThrows(CustomException.class, () -> {
                throw Beef.of(CustomException.class).build();
            });
        }

        @Test
        @DisplayName("with cause should throw UncheckedException for provided cause")
        void cause() {
            try {
                new FileNotFoundException();
            } catch (Exception e) {
                var b = Beef.of(e);
                assertThrows(UncheckedException.class, () -> b.following(new Exception()));
                assertThat(e).isEqualTo(b.build().getCause());
            }
        }

        @Test
        @DisplayName("raise unchecked should throw UncheckedException for checked exception")
        void rethrow() {
            assertThrows(UncheckedException.class, () -> {
                try {
                    throw new FileNotFoundException();
                } catch (Exception e) {
                    throw Beef.uncheck(e);
                }
            });
        }

        @Test
        @DisplayName("raise unchecked should throw identical exception for unchecked exception")
        void rethrowIdentical() {
            assertThrows(ArithmeticException.class, () -> {
                try {
                    throw new ArithmeticException();
                } catch (Exception e) {
                    throw Beef.uncheck(e);
                }
            });
        }
    }

    public static void nested() {
        try {
            throw new FileNotFoundException();
        } catch (Exception e) {
            try {
                throw new ArithmeticException();
            } catch (Exception e1) {
                e1.initCause(e);
                try {
                    throw new NullPointerException();
                } catch (Exception e2) {
                    e2.initCause(e1);
                    throw e2;
                }
            }
        }
    }

    public static void nestedBeef() {
        try {
            throw new FileNotFoundException();
        } catch (Exception e) {
            try {
                throw new ArithmeticException();
            } catch (Exception e1) {
                e1.initCause(e);
                try {
                    throw new FileNotFoundException();
                } catch (Exception e2) {
                    e2.initCause(e1);
                    try {
                        throw Beef.of().build();
                    } catch (Exception e3) {
                        e3.initCause(e2);
                        try {
                            throw new NullPointerException();
                        } catch (Exception e4) {
                            e4.initCause(e3);
                            throw e4;
                        }
                    }
                }
            }
        }
    }
}
