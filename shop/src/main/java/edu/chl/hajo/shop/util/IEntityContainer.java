    
package edu.chl.hajo.shop.util;

import java.util.List;

/**
 * Basic contract for containers of Products, Customers, etc
 *
 * @author hajo
 * @param <T> type of elements in container
 * @param <K> K is type of id (primary key)
 */
public interface IEntityContainer<T extends IEntity<K>, K> {

    public void create(T t);

    public void delete(K id);

    public void update(T t);

    public T find(K id);

    public List<T> findAll();

    public List<T> findRange(int first, int n );

    public int count();
   
}
