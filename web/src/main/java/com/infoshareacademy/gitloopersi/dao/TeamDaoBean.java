package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.Team;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class TeamDaoBean {

  @PersistenceContext
  EntityManager entityManager;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public void addTeam(Team team) {
    logger.info("Team object {} is to be merged to DB", team.getName());
    entityManager.persist(team);
  }

  public Team editTeam(Team team) {
    logger.info("Team object id={} is to be updated in DB", team.getId());
    return entityManager.merge(team);
  }

  public Team getTeamById(Long id) {
    logger.info("Team object id={} is to be get from DB", id);
    return entityManager.find(Team.class, id);
  }

  public void deleteTeamById(Long id) {
    logger.info("Team object id={} is to be deleted from DB", id);
    Team team = getTeamById(id);
    if (team != null) {
      entityManager.remove(team);
    } else {
      logger.warn("An attempt to delete non-existing team object id={} has occured", id);
    }
  }

  public List<Team> getTeamsList() {
    Query query = entityManager
        .createNamedQuery("Team.findAll");
    return query.getResultList();
  }

  public long getEmployeeCountInTeam(Long id) {
    Query query = entityManager.createNamedQuery("Team.findEmployeeCountInTeam");
    query.setParameter("id", id);
    long employeeCount = (long) query.getSingleResult();
    logger.info("Employee count in team id={} is {}", id, employeeCount);
    return employeeCount;
  }

  public Team getTeamByName(String name) {
    Query query = entityManager.createNamedQuery("Team.findTeamByName");
    query.setParameter("name", name);
    return (Team) query.getResultList().stream().findFirst().orElse(null);
  }

  public Team getTeamByEmployeeId(Long id) {
    Query query = entityManager.createNamedQuery("Team.findTeamByEmployeeId");
    query.setParameter("id", id);
    return (Team) query.getResultList().stream().findFirst().orElse(null);
  }
}