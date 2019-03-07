package com.codecool.todorefact;

import com.codecool.todorefact.entity.Todo;
import com.codecool.todorefact.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class TodorefactApplication {

    @Autowired
    private TodoRepository todoRepository;

    public static void main(String[] args) {
        SpringApplication.run(TodorefactApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
      return args -> {
          Todo first_item = Todo.builder()
                  .title("First Item")
                  .status(false)
                  .build();
          Todo second_item = Todo.builder()
                  .title("Second Item")
                  .status(false)
                  .build();
          Todo third_item = Todo.builder()
                  .title("Third Item")
                  .status(false)
                  .build();

          todoRepository.save(first_item);
          todoRepository.save(second_item);
          todoRepository.save(third_item);


      };
    }

}
