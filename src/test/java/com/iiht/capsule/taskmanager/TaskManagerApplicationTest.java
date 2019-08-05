package com.iiht.capsule.taskmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.iiht.capsule.taskmanager.TaskManagerApplication;
import com.iiht.capsule.taskmanager.entity.ParentTask;
import com.iiht.capsule.taskmanager.entity.Task;
import com.iiht.capsule.taskmanager.model.ParentTaskDto;
import com.iiht.capsule.taskmanager.model.TaskDto;
import com.iiht.capsule.taskmanager.repository.TaskRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = TaskManagerApplication.class)
@ActiveProfiles("test")
public class TaskManagerApplicationTest {

    @Value("${local.server.port}")
    private Integer port;
    private String baseUrl;
    private TestRestTemplate testRestTemplate;

    @Autowired
    private TaskRepository taskRepository;


    @Before
    public void setUp() throws Exception {
        baseUrl = "http://localhost:".concat(port.toString()).concat("/taskmanager/api");
        testRestTemplate = new TestRestTemplate();

        /*Init Parent Task*/
        ParentTask parentTask = new ParentTask();
        parentTask.setParentTask("Requirement Gathering");

        /*Init Task*/
        Task task = new Task();
        task.setTask("UI Design for Retail Customers");
        task.setStartDate(new Date());
        task.setEndDate(new Date());
        task.setPriority(15);
        task.setParentTask(parentTask);
        task.setInProgress(true);
        taskRepository.save(task);

    }

    @Test
    public void getAllTasksTest() throws Exception {
        ResponseEntity<String> response = testRestTemplate.getForEntity(baseUrl.toString().concat("/task"), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        List<TaskDto> taskDto = convertJsonToTaskDto(response.getBody());
        assertThat(taskDto.size(), greaterThanOrEqualTo(1));
    }

    @Test
    public void getAllParentTasksTest() throws Exception {
        ResponseEntity<String> response = testRestTemplate.getForEntity(baseUrl.toString().concat("/parenttask"), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        List<ParentTaskDto> parentTaskDto = convertJsonToParentTaskDto(response.getBody());
        assertThat(parentTaskDto.size(), greaterThanOrEqualTo(1));
    }

    @Test
    public void addTaskTest() throws Exception {
        /*Init TaskDto Object*/
        TaskDto taskDtoInit = new TaskDto();
        taskDtoInit.setTask("UI Design for Corporate Customers");
        taskDtoInit.setStartDate(new Date());
        taskDtoInit.setEndDate(new Date());
        taskDtoInit.setPriority(15);
        taskDtoInit.setParentId(new Long(1));
        taskDtoInit.setInProgress(true);

        ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl.toString().concat("/task/add"), taskDtoInit, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));

        List<TaskDto> taskDto = convertJsonToTaskDto(response.getBody());
        assertThat(taskDto.size(), equalTo(1));
    }

    @Test
    public void updateTaskTest() throws Exception {
        /*Init TaskDto Object*/
        TaskDto taskDtoInit = new TaskDto();
        taskDtoInit.setTaskId(new Long(1));
        taskDtoInit.setTask("UI Design for Corporate Customers & Retail Customers");
        taskDtoInit.setStartDate(new Date());
        taskDtoInit.setEndDate(new Date());
        taskDtoInit.setPriority(15);
        taskDtoInit.setParentId(new Long(1));
        taskDtoInit.setInProgress(true);

        ResponseEntity<String> response = testRestTemplate.exchange(baseUrl.toString().concat("/task/update"), HttpMethod.PUT, new HttpEntity<>(taskDtoInit), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        List<TaskDto> taskDto = convertJsonToTaskDto(response.getBody());
        assertThat(taskDto.size(), equalTo(1));
    }

    @Test
    public void endTaskTest() throws Exception {
        /*Init TaskDto Object*/
        TaskDto taskDtoInit = new TaskDto();
        taskDtoInit.setTaskId(new Long(1));
        taskDtoInit.setTask("UI Design for Corporate Customers & Retail Customers");
        taskDtoInit.setStartDate(new Date());
        taskDtoInit.setEndDate(new Date());
        taskDtoInit.setPriority(15);
        taskDtoInit.setParentId(new Long(1));
        taskDtoInit.setInProgress(false);

        ResponseEntity<String> response = testRestTemplate.exchange(baseUrl.toString().concat("/task/end"), HttpMethod.PUT, new HttpEntity<>(taskDtoInit), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        List<TaskDto> taskDto = convertJsonToTaskDto(response.getBody());
        assertThat(taskDto.size(), equalTo(1));
    }

    private List<TaskDto> convertJsonToTaskDto(String json) throws Exception        {
        ObjectMapper mapper = new ObjectMapper();
        if(!json.startsWith("["))
            json = "["+json+"]";
        return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, TaskDto.class));
    }

    private List<ParentTaskDto> convertJsonToParentTaskDto(String json) throws Exception        {
        ObjectMapper mapper = new ObjectMapper();
        if(!json.startsWith("["))
            json = "["+json+"]";
        return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, ParentTaskDto.class));
    }

    @After
    public void tearDown() throws Exception {
        baseUrl = null;
        testRestTemplate = null;
    }
}
