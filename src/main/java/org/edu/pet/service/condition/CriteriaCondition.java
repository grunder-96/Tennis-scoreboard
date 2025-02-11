package org.edu.pet.service.condition;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@FunctionalInterface
public interface CriteriaCondition<R> {

    Predicate toPredicate(Root<R> root, CriteriaBuilder cb);

    default CriteriaCondition<R> and(CriteriaCondition<R> other) {
        return (root, cb) -> {
            Predicate thisPredicate = this.toPredicate(root, cb);
            Predicate otherPredicate = other.toPredicate(root, cb);
            return thisPredicate == null ? otherPredicate :
                    otherPredicate == null ? thisPredicate :
                            cb.and(thisPredicate, otherPredicate);
        };
    }

    default CriteriaCondition<R> or(CriteriaCondition<R> other) {
        return (root, cb) -> {
            Predicate thisPredicate = this.toPredicate(root, cb);
            Predicate otherPredicate = other.toPredicate(root, cb);
            return thisPredicate == null ? otherPredicate :
                    otherPredicate == null ? thisPredicate :
                            cb.or(thisPredicate, otherPredicate);
        };
    }

    default CriteriaCondition<R> not() {
        return (root, cb) -> {
            Predicate predicate = this.toPredicate(root, cb);
            return predicate == null ? null : cb.not(predicate);
        };
    }

    static <T> CriteriaCondition<T> where(CriteriaCondition<T> criteriaCondition) {
        return criteriaCondition;
    }
}