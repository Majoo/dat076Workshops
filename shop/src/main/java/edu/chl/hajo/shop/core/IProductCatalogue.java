
package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.util.IEntityContainer;
import java.util.List;

/**
 * Interface to product catalogue
 * @author hajo
 */
public interface IProductCatalogue extends IEntityContainer<Product, Long> {

    public List<Product> getByName(String name);
     
}
