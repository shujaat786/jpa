package atul.backend.jpahibernate.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Annotation to exclude repository interfaces from being picked up and thus in consequence getting an instance being
 * created.
 * <p/>
 * This will typically be used when providing an extended base interface for all repositories in combination with a
 * custom repository base class to implement methods declared in that intermediate interface. In this case you typically
 * derive your concrete repository interfaces from the intermediate one but don't want to create a Spring bean for the
 * intermediate interface.
 */

@NoRepositoryBean
public interface JPAHibernateRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
