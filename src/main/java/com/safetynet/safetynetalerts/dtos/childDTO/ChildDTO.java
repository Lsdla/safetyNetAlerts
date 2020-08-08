package com.safetynet.safetynetalerts.dtos.childDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Yahia CHERIFI
 * This class is used to contain data retrieved from person entities
 * it is used mainly for storing data belonging to children
 * for more information about the annotation:
 * @see lombok
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ChildDTO {

    /**
     * The child's first name.
     */
    private String firstName;

    /**
     * The child's last name.
     */
    private String lastName;

    /**
     * the child's age.
     */
    private Double age;
}
