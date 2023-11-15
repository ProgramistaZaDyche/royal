package royal.gambit.zadanie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import royal.gambit.zadanie.Entities.TaskEntity;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TasksControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
      public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
  public void findTasksWithEmptyTable() throws Exception {
        this.mockMvc.perform(get("/tasks"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
    }

  @Test
  @Sql(scripts = "classpath:sql/insertTwoTasks.sql")
  public void findTasksWithoutFilter() throws Exception {
    this.mockMvc.perform(get("/tasks"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  @Sql(scripts = "classpath:sql/insertTwoTasks.sql")
  public void findTasksWithFilter() throws Exception {
    TaskEntity filter = TaskEntity.builder().content("Content1").build();
    ObjectMapper objectMapper = new ObjectMapper();
    String filterJson = objectMapper.writeValueAsString(filter);
    
    this.mockMvc.perform(get("/tasks")
        .contentType(MediaType.APPLICATION_JSON)
        .content(filterJson))
        .andExpect(status().isOk());
  }

  @Test
  public void findTaskWithEmptyTable() throws Exception {
    this.mockMvc.perform(get("/tasks/1"))
              .andExpect(status().isNotFound());
  }

  @Test
  @Sql(scripts = "classpath:sql/insertSingleTask.sql")
  public void findTaskWithExistingId() throws Exception {
    this.mockMvc.perform(get("/tasks/1"))
        .andExpect(status().isOk())
        .andExpect(content().json(
          "{" +
          "\"id\":1," +
          "\"content\":\"Test Content\"," +
          "\"creationDate\":\"2012-12-21\"," +
          "\"editionDate\":\"2012-12-21\"" +
          "}"));
  }

  @Test
  @Sql(scripts = "classpath:sql/insertSingleTask.sql")
  public void findTaskWithInexistingId() throws Exception {
    mockMvc.perform(get("/tasks/2"))
        .andExpect(status().isNotFound())
        .andExpect(content().json("{\"title\":\"Nonexistent Record Error!\"}"));
  }
}
