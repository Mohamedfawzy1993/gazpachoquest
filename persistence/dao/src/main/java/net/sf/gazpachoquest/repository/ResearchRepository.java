/*******************************************************************************
 * Copyright (c) 2014 antoniomariasanchez at gmail.com.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     antoniomaria - initial API and implementation
 ******************************************************************************/
package net.sf.gazpachoquest.repository;

import java.util.List;

import net.sf.gazpachoquest.domain.core.Research;
import net.sf.gazpachoquest.repository.support.GenericRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResearchRepository extends GenericRepository<Research> {

    @Query("select distinct s from Research s join s.questionnaires q join q.questionnaireDefinition qd where qd.id = :questionnaireDefinitionId")
    List<Research> findByQuestionnaireDefinition(@Param("questionnaireDefinitionId")
    Integer questionnaireDefinitionId);

    @Query("select distinct r from ResearchPermission p join p.target r where p.user.id = :userId and mod(p.mask / :bitMask, 2) = 1")
    List<Research> findByAllAvailableFor(@Param("userId")
    Integer userId, @Param("bitMask")
    int bitMask);

}
