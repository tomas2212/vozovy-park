/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark;

import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import cz.muni.fi.pa165.vozovypark.entities.Reservation;
import java.util.Map;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;
import junit.framework.Test;

/**
 *
 * @author Tomas Svrcek
 */
public class ReservationDAOImplTest {

    private EntityManagerFactory factory;
    private EntityManager manager;

    public ReservationDAOImplTest() {
    }

    public void setUp() {
        factory = new EntityManagerFactory() {
            public EntityManager createEntityManager() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public EntityManager createEntityManager(Map map) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public CriteriaBuilder getCriteriaBuilder() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Metamodel getMetamodel() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public boolean isOpen() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void close() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Map<String, Object> getProperties() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Cache getCache() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public PersistenceUnitUtil getPersistenceUnitUtil() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };



        manager = new EntityManager() {
            public void persist(Object o) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public <T> T merge(T t) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void remove(Object o) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public <T> T find(Class<T> type, Object o) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public <T> T find(Class<T> type, Object o, Map<String, Object> map) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public <T> T find(Class<T> type, Object o, LockModeType lmt) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public <T> T find(Class<T> type, Object o, LockModeType lmt, Map<String, Object> map) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public <T> T getReference(Class<T> type, Object o) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void flush() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void setFlushMode(FlushModeType fmt) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public FlushModeType getFlushMode() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void lock(Object o, LockModeType lmt) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void lock(Object o, LockModeType lmt, Map<String, Object> map) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void refresh(Object o) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void refresh(Object o, Map<String, Object> map) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void refresh(Object o, LockModeType lmt) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void refresh(Object o, LockModeType lmt, Map<String, Object> map) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void clear() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void detach(Object o) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public boolean contains(Object o) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public LockModeType getLockMode(Object o) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void setProperty(String string, Object o) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Map<String, Object> getProperties() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Query createQuery(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public <T> TypedQuery<T> createQuery(CriteriaQuery<T> cq) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public <T> TypedQuery<T> createQuery(String string, Class<T> type) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Query createNamedQuery(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public <T> TypedQuery<T> createNamedQuery(String string, Class<T> type) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Query createNativeQuery(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Query createNativeQuery(String string, Class type) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Query createNativeQuery(String string, String string1) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void joinTransaction() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public <T> T unwrap(Class<T> type) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Object getDelegate() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void close() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public boolean isOpen() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public EntityTransaction getTransaction() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public EntityManagerFactory getEntityManagerFactory() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public CriteriaBuilder getCriteriaBuilder() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Metamodel getMetamodel() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }

    /*
     public Reservation getReservationByIdTest(Long id){
        
     return ; 
     }
     */
    
    /**
     * 
     *
     * @param reservation Object reservation
     */
    public void insertTest(Reservation reservation) {
        Car car = new Car();
        Employee employee = new Employee();
        reservation.setCar(car);
        reservation.setEmployee(employee);

        manager.getTransaction().begin();
        manager.persist(reservation);
        manager.getTransaction().commit();



    }
}
