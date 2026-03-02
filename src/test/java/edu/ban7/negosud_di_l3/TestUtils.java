package edu.ban7.negosud_di_l3;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class TestUtils {

    public static boolean constraintExist(
            Set<ConstraintViolation<Object>> listViolatedConstraints,
            String propertyName,
            String constraintName
    ){
        return listViolatedConstraints.stream()
                .filter(contrainte -> contrainte.getPropertyPath()
                        .toString()
                        .equals(propertyName))
                .anyMatch(contrainte -> contrainte.getConstraintDescriptor()
                        .getAnnotation()
                        .annotationType()
                        .getSimpleName()
                        .equals(constraintName));
    }

}
