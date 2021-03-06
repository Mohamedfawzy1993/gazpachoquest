/*
 * Copyright (c) 2014 antoniomariasanchez at gmail.com. All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and is
 * available at http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors: antoniomaria - initial API and implementation
 */
package net.sf.gazpachoquest.domain.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.gazpachoquest.domain.core.Questionnaire;
import net.sf.gazpachoquest.domain.permission.UserPermission;
import net.sf.gazpachoquest.domain.support.AbstractSecurizable;
import net.sf.gazpachoquest.jpa.converter.GenderTypeConverter;
import net.sf.gazpachoquest.jpa.converter.MapToStringConverter;
import net.sf.gazpachoquest.types.Gender;
import net.sf.gazpachoquest.types.Language;

import org.eclipse.persistence.annotations.Converter;
/**
 *  @assoc 1 fills 1..* net.sf.gazpachoquest.domain.core.Questionnaire
 *  @assoc 0..* has 0..* Role
 */
@SuppressWarnings({ "serial" })
@Entity
@Table(name = "users")
public class User extends AbstractSecurizable<UserPermission> {

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String givenNames;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String apiKey;

    @Column(nullable = false, unique = true)
    private String secret;

    @Enumerated(EnumType.STRING)
    private Language preferredLanguage;

    @Convert(converter = GenderTypeConverter.class)
    private Gender gender;

    @OneToMany(mappedBy = "respondent", fetch = FetchType.LAZY)
    private final Set<Questionnaire> questionnaires = new HashSet<Questionnaire>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_group", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "group_id", referencedColumnName = "id") })
    private final Set<Group> groups = new HashSet<Group>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
    private final Set<Role> roles = new HashSet<Role>();

    @Column(name = "attributes")
    @Converter(name = "map-to-string-converter", converterClass = MapToStringConverter.class)
    @org.eclipse.persistence.annotations.Convert(value = "map-to-string-converter")
    private Map<String, String> attributes = new HashMap<String, String>();

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Directory directory;
    
    public User() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Questionnaire> getQuestionnaires() {
        return Collections.unmodifiableSet(questionnaires);
    }

    public Set<Group> getGroups() {
        return Collections.unmodifiableSet(groups);
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getGivenNames() {
        return givenNames;
    }

    public void setGivenNames(String givenNames) {
        this.givenNames = givenNames;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Language getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(Language preferedLanguage) {
        preferredLanguage = preferedLanguage;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }


    protected void addRole(Role role) {
        roles.add(role);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Transient
    public String getFullName() {
        return new StringBuilder().append(givenNames).append(" ").append(surname).toString();
    }

    public static Builder with() {
        return new Builder();
    }
    
    /**
     * @hidden 
     */
    public static class Builder {
        private Integer id;
        private String username;
        private String password;
        private String givenNames;
        private String surname;
        private String email;
        private String apiKey;
        private Language preferredLanguage;
        private Gender gender;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
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

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder preferredLanguage(Language preferredLanguage) {
            this.preferredLanguage = preferredLanguage;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.username = username;
            user.password = password;
            user.givenNames = givenNames;
            user.surname = surname;
            user.email = email;
            user.apiKey = apiKey;
            user.preferredLanguage = preferredLanguage;
            user.gender = gender;
            return user;
        }
    }
}
