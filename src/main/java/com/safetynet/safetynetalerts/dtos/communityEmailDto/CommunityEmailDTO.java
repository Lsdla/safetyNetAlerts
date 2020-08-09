package com.safetynet.safetynetalerts.dtos.communityEmailDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Yahia CHERIFI
 * This class is used to contain data retrieved from person entities
 * it is used mainly for storing email address of persons living in a given city
 * for more information about the annotation:
 * @see lombok
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class CommunityEmailDTO {

    /**
     * The person's email address.
     */
    private String email;
}
