package royal.gambit.zadanie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import royal.gambit.zadanie.DTOs.ShowTaskDTO;


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
    ShowTaskDTO filter = ShowTaskDTO.builder().content("Content1").build();

    this.mockMvc.perform(get("/tasks")
        .flashAttr("showTaskDTO", filter))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].content", is(filter.getContent())));

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
        .andExpect(jsonPath("$.id", is(1)));
  }

  @Test
  @Sql(scripts = "classpath:sql/insertSingleTask.sql")
  public void findTaskWithInexistingId() throws Exception {
    mockMvc.perform(get("/tasks/2"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.title", is("Nonexistent Record Error!")));
  }
}
