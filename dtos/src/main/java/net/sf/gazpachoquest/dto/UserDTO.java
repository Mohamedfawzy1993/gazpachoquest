/*******************************************************************************
 * Copyright (c) 2014 antoniomariasanchez at gmail.com. All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and is
 * available at http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors: antoniomaria - initial API and implementation
 ******************************************************************************/
package net.sf.gazpachoquest.dto;

import net.sf.gazpachoquest.dto.support.AbstractAuditableDTO;
import net.sf.gazpachoquest.types.Gender;
import net.sf.gazpachoquest.types.Language;

public class UserDTO extends AbstractAuditableDTO {

    private static final long serialVersionUID = 7370786032611110768L;

    private String email;

    private String givenNames;

    private String surname;

    private String apiKey;

    private Language preferedLanguage;

    private Gender gender;

    private String username;

    public UserDTO() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public String getGivenNames() {
        return givenNames;
    }

    public String getSurname() {
        return surname;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setGivenNames(final String givenNames) {
        this.givenNames = givenNames;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Language getPreferedLanguage() {
        return preferedLanguage;
    }

    public void setPreferedLanguage(Language preferedLanguage) {
        this.preferedLanguage = preferedLanguage;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static Builder with() {
        return new Builder();
    }

    public static class Builder {
        private String email;
        private String givenNames;
        private String surname;
        private String apiKey;
        private Language preferedLanguage;
        private Gender gender;
        private String username;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder givenNames(String givenNames) {
            this.givenNames = givenNames;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder preferedLanguage(Language preferedLanguage) {
            this.preferedLanguage = preferedLanguage;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public UserDTO build() {
            UserDTO userDTO = new UserDTO();
            userDTO.email = email;
            userDTO.givenNames = givenNames;
            userDTO.surname = surname;
            userDTO.apiKey = apiKey;
            userDTO.preferedLanguage = preferedLanguage;
            userDTO.gender = gender;
            userDTO.username = username;
            return userDTO;
        }
    }
}