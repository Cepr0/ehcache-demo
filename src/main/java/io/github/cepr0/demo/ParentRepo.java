package io.github.cepr0.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sergei Poznanski, 2018-03-18
 */
@RepositoryRestResource//(exported = false)
@Transactional(readOnly = true)
public interface ParentRepo extends JpaRepository<Parent, Integer>, JpaSpecificationExecutor<Parent> {
}
