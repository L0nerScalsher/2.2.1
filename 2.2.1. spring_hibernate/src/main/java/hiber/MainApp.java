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

      User userWithM1S1 = userService.getUserByCar("model1", "series1");
      System.out.println("User with model1, series1: " + userWithM1S1.toString());
      User userWithM2S2 = userService.getUserByCar("model2", "series2");
      System.out.println("User with model2, series2: " + userWithM2S2.toString());
      User userWithM3S3 = userService.getUserByCar("model3", "series3");
      System.out.println("User with model3, series3: " + userWithM3S3.toString());

      context.close();
   }
}
