package edu.hibernate.demo;


import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;

public class App 
{
	public static void main( String[] args )
    {
      Configuration configure=new Configuration();
      configure.configure("hibernate.cfg.xml");
      SessionFactory sessionFactory=configure.buildSessionFactory();
      
      //insertEmployee(sessionFactory);
      //prinTotalSalary(sessionFactory);
      //prinAverageSalary(sessionFactory);
      
      
      //testSaveVsUpdate(sessionFactory);
      insertProduct(sessionFactory);
      //updateProduct(sessionFactory);
      //listAllProduct(sessionFactory);
      //deleteProduct(sessionFactory);
      //testGetVsLoad(sessionFactory);
      //testUpdateVsMerge(sessionFactory);
      sessionFactory.close();
      
    }
    
    public static void insertEmployee(SessionFactory sessionFactory){
    	Session session=sessionFactory.openSession();
    	Transaction tx=session.beginTransaction();
    	Employee e1=new Employee("Tom",  2000, "Pune");
    	session.save(e1);
    	Employee e2=new Employee("John",  6000, "Pune");
    	session.save(e2);
    	Employee e3=new Employee("Clark",  6444, "Mumbai");
    	session.save(e3);
    	Employee e4=new Employee("Adam",  1000, "Mumbai");
    	session.save(e4);
    	tx.commit();
    	session.close();
    }
    
    public static void prinAverageSalary(SessionFactory sessionFactory){
    	Session session=sessionFactory.openSession();
    	Criteria cr = session.createCriteria(Employee.class); 
   
    	//double sum =(Double)session.createCriteria(Employee.class).setProjection(Projections.avg("sal")).uniqueResult(); 
    	//System.out.println("Average Salary: "+sum);
    	
    	cr.add(Restrictions.gt("sal", 6000.0));
    	for(Employee e:(List<Employee>)cr.list())
    		System.out.println(e);
    	
    	
    }
    
    public static void prinTotalSalary(SessionFactory sessionFactory){
    	Session session=sessionFactory.openSession();
    	Criteria cr = session.createCriteria(Employee.class); 
   
    	double sum =(Double)session.createCriteria(Employee.class).setProjection(Projections.sum("sal")).uniqueResult(); 
    	System.out.println("Total Salary: "+sum);
    	
    	
    	Query ascOrderBysal=session.getNamedQuery("ascOrderBySal");
    	
    	
    	/*cr.addOrder(Order.asc("sal")); 
    	for(Employee e:(List<Employee>)cr.list())
    		System.out.println(e);*/  
    	
    	List<Object> list=(List<Object>)cr.setProjection(Projections.projectionList().add(Projections.sum("sal")).add(Projections.groupProperty("city"))).list();
    	//Query groupQuery=session.createQuery("SELECT city, SUM(sal) FROM Employee GROUP BY city");
    	//List<Object> list=groupQuery.list();
    	
    	//Iterator<Object> iter = list.iterator();
    	Iterator<Object> iter = ascOrderBysal.list().iterator();
        while (iter.hasNext())
        {
            Object[] obj = (Object[]) iter.next();
            for (int i=0;i<obj.length;i++)
            {
                System.out.print("\t"+obj[i]);
            }
            System.out.println();
        }
    						
    }
    
    
    public static void testGetVsLoad(SessionFactory sessionFactory){
    	Session session=sessionFactory.openSession();
    	Product product=session.load(Product.class, 1L);
    	session.close();
    	product.setPrice(100);
    	session=sessionFactory.openSession();
    	Transaction tx=session.beginTransaction();
    	session.save(product);
    	tx.commit();
    	session.close();
    }
    
    public static void testUpdateVsMerge(SessionFactory sessionFactory){
    	Session session=sessionFactory.openSession();
    	Product product=session.get(Product.class, 1L);
    	System.out.println(product);
    	session.close();
    	
    	product.setPrice(10);
    	
    	session=sessionFactory.openSession();
    	Transaction tx=session.beginTransaction();
    	Product product2=session.get(Product.class, 1L);
    	session.update(product);
    	tx.commit();
    	session.close();
    }
    
    public static void testSaveVsUpdate(SessionFactory sessionFactory){
    	Session session=sessionFactory.openSession();
    	Transaction tx=session.beginTransaction();
    	Product product1=new Product("Chair", 4999.0d);
    	session.persist(product1);
    	Product product2=new Product("Table", 7999.0d);
    	session.save(product2);
    	tx.commit();
    	session.close();
    }
    
    
    
    public static void insertProduct(SessionFactory sessionFactory){
    	Session session=sessionFactory.openSession();
    	Transaction tx=session.beginTransaction();
    	Product product1=new Product("Chair", 4999.0d);
    	
    	SQLQuery sqlQuery=session.createSQLQuery("INSERT INTO PRODUCT_MASTER VALUES (5,'LAPTOP', 100)");
    	//sqlQuery.executeUpdate();
    	session.save(product1);
    	Product product2=new Product("Table", 7999.0d);
    	session.save(product2);
    	tx.commit();
    	session.close();
    }
    
    public static void updateProduct(SessionFactory sessionFactory){
    	Session session=sessionFactory.openSession();
    	Transaction tx=session.beginTransaction();
    	Product product=session.load(Product.class, 1L);
    	product.setPrice(5000);
    	session.save(product);
    	tx.commit();
    	session.close();
    }
    
    public static void listAllProduct(SessionFactory sessionFactory){
    	Session session=sessionFactory.openSession();
    	Query query=session.createQuery("from Product");
    	for(Product product:(List<Product>)query.list())
    		System.out.println(product);
    	session.close();
    }
    
    public static void deleteProduct(SessionFactory sessionFactory){
    	Session session=sessionFactory.openSession();
    	Transaction tx=session.beginTransaction();
/*    	Product product=session.load(Product.class, 1L);
    	session.delete(product);
    	tx.commit();
    	session.close();*/
    	
    	Query query=session.createQuery("delete Product where id=:id");
    	query.setParameter("id", 1L);
    	query.executeUpdate();
    	session.close();
    }
}
