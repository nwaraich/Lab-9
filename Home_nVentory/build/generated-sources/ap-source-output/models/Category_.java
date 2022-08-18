package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Item;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-08-09T16:38:01")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile ListAttribute<Category, Item> itemList;
    public static volatile SingularAttribute<Category, String> categoryName;
    public static volatile SingularAttribute<Category, Integer> categoryId;

}