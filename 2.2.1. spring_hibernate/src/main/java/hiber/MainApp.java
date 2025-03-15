package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");

      Car car1 = new Car("model1", "series1");
      Car car2 = new Car("model2", "series2");
      Car car3 = new Car("model3", "series3");

      user1.setCar(car1);
      car1.setUser(user1);

      user2.setCar(car2);
      car2.setUser(user2);

      user3.setCar(car3);
      car3.setUser(user3);

      UserService userService = context.getBean(UserService.class);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User userWithCar1 = userService.getUserByCar(car1);
      System.out.println("User with car1: " + userWithCar1.toString());
      User userWithCar2 = userService.getUserByCar(car2);
      System.out.println("User with car2: " + userWithCar2.toString());
      User userWithCar3 = userService.getUserByCar(car3);
      System.out.println("User with car3: " + userWithCar3.toString());

      context.close();
   }
}
