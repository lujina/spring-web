import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.spring_web.dao.impl.PersonDao;
import com.demo.spring_web.model.PersonEntity;

public class SpringHibernateMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		PersonDao personDao =  context.getBean(PersonDao.class);
		PersonEntity person = new PersonEntity();
		person.setName("lujina");
		personDao.save(person);
		System.out.println("Person::" + person);
		List<PersonEntity> list = personDao.list();
		for(PersonEntity p : list){
			System.out.println("person List::"+p);
		}
		//关闭资源
        context.close();
	}

}
