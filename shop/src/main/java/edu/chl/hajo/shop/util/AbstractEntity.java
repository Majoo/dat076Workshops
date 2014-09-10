
package edu.chl.hajo.shop.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * Base class for all entities (later to be stored in database), 
 * Product, Order, etc
 * @author hajo
 */
public abstract class AbstractEntity implements IEntity<Long>, Serializable{

    private final Long id; 
    private static int counter = 1; 
   
    protected AbstractEntity(){
        // This is for now, later database will generate
        this.id = new Long(counter++);
    }
    
    protected AbstractEntity(Long id){
        this.id = id;
    }
    
    @Override
    public Long getId(){
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractEntity other = (AbstractEntity) obj;
        return Objects.equals(this.id, other.id);
    }

   
}
