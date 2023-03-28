package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      CarService carService = context.getBean(CarService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 567321));
      User user2 = new User("User2", "Lastname2", "user2@mail.ru", new Car("Audi", 987650));
      User user3 = new User("User3", "Lastname3", "user3@mail.ru", new Car("Skoda", 567849));
      User user4 = new User("User4", "Lastname4", "user4@mail.ru", new Car("Kia", 453209));


      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " +user.getCar().toString());
         System.out.println();
      }


      System.out.println("-------------------------------------");

      User userRes1  = carService.getUser("BMW", 567321);
      User userRes2  = carService.getUser("Audi", 987650);
      User userRes3  = carService.getUser("Skoda", 567849);
      User userRes4  = carService.getUser("Kia", 453209);

      System.out.println("-------------------------------------");

      System.out.println(userRes1);
      System.out.println(userRes2);
      System.out.println(userRes3);
      System.out.println(userRes4);




      context.close();
   }
}
