package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.CategoryParent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents the repository for CategoryParent entities,
 * extending the JpaRepository interface from Spring Data JPA.
 */
public interface CategoryParentRepository extends JpaRepository<CategoryParent, Long> {
}
