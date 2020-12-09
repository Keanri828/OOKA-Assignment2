package org.hbrs.ooka.ws2020.uebung2.logger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

@Target(value={METHOD,CONSTRUCTOR,FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
