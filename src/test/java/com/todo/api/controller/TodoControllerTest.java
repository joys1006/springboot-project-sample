package com.todo.api.controller;

import com.todo.api.dto.response.TodoitemsResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {
            TodoController.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private TodoController todoController;

    @Test
    public void testFoo() throws Exception {
        List<TodoitemsResponseDTO> actual = todoController.getLists(1, 10);

//        assertThat(mockMvc).isNotNull();
//        mockMvc.perform(get("/todos"))
//                .andExpect(status().isOk())
//                .andExpect(content().)
//                .andDo(print());
    }

}
