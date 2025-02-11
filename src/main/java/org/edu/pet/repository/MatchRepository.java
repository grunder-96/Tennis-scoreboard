package org.edu.pet.repository;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.edu.pet.model.entity.Match;
import org.edu.pet.service.condition.CriteriaCondition;
import org.edu.pet.util.TransactionUtil;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchRepository extends SharedRepositoryImpl<Match> {

    private static final MatchRepository INSTANCE = new MatchRepository();

    public long countBy(CriteriaCondition<Match> condition) {
        return TransactionUtil.executeInTransaction(
          session -> {
              HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
              JpaCriteriaQuery<Long> query = cb.createQuery(Long.class);

              JpaRoot<Match> match = query.from(Match.class);

              query.select(cb.count(match));

              if (condition != null) {
                  Predicate predicate = condition.toPredicate(match, cb);
                  if (predicate != null) {
                      query.where(predicate);
                  }
              }

              return session.createQuery(query).getSingleResult();
          },
          session
        );
    }

    public List<Match> findBy(CriteriaCondition<Match> condition, int offset, int limit) {
        return TransactionUtil.executeInTransaction(
                session -> {
                    HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
                    JpaCriteriaQuery<Match> query = cb.createQuery(Match.class);

                    JpaRoot<Match> match = query.from(Match.class);

                    match.fetch("player1", JoinType.INNER);
                    match.fetch("player2", JoinType.INNER);
                    match.fetch("winner", JoinType.INNER);

                    if (condition != null) {
                        Predicate predicate = condition.toPredicate(match, cb);
                        if (predicate != null) {
                            query.where(predicate);
                        }
                    }

                    query.orderBy(cb.desc(match.get("id")));

                    return session.createQuery(query)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
                },
                session
        );
    }

    public static MatchRepository getInstance() {
        return INSTANCE;
    }
}