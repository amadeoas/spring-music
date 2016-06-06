package com.aas.music.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


/**
 * Simple test to make sure that Bean Validation is working (useful when 
 * upgrading to a new version of Hibernate Validator/ Bean Validation).
 *
 * @author Amadeo Asco
 */
public class ValidatorTests {

    private Validator createValidator() {
        final LocalValidatorFactoryBean localValidatorFactoryBean 
        		= new LocalValidatorFactoryBean();

        localValidatorFactoryBean.afterPropertiesSet();

        return localValidatorFactoryBean;
    }

    @Test
    public void shouldNotValidateWhenNameEmpty() {
        LocaleContextHolder.setLocale(Locale.ENGLISH);

        final EqInstrument instrument = new EqInstrument();

        instrument.setName("");

        final Validator validator = createValidator();
        final Set<ConstraintViolation<EqInstrument>> constraintViolations 
        		= validator.validate(instrument);

        assertThat(constraintViolations.size()).isEqualTo(1);

        final ConstraintViolation<EqInstrument> violation 
        		= constraintViolations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
        assertThat(violation.getMessage()).isEqualTo("may not be empty");
    }

}
