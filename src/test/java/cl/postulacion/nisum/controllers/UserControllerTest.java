package cl.postulacion.nisum.controllers;

import cl.postulacion.nisum.models.Phone;
import cl.postulacion.nisum.models.User;
import cl.postulacion.nisum.services.IUserService;
import cl.postulacion.nisum.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userService;

    @Autowired
    @InjectMocks
    private UserController userController;

    @Before("")
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        Set<Phone> phonesA = new HashSet<>();
        phonesA.add(new Phone("123123", "7", "52"));
        phonesA.add(new Phone("123321", "6", "53"));

        List<User> fakeUserList = Arrays.asList(
                new User("Alex 2", "a2@gmail.com", "A123qwe", phonesA));

        when(userService.findAll()).thenReturn(fakeUserList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/list"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnUsers() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Set<Phone> phonesA = new HashSet<>();
        phonesA.add(new Phone("123123", "7", "52"));
        phonesA.add(new Phone("1223123", "27", "522"));

        List<User> fakeUserList = Arrays.asList(
                new User("Alex Soto", "asoto@gmail.com", "A123qwe", phonesA),
                new User("Alex 2", "a2@gmail.com", "A123qwe", phonesA));

        when(userService.findAll()).thenReturn(fakeUserList);

        ResponseEntity<List<User>> responseEntity = userController.users();
        List<User> responseEntity2 = userController.users().getBody();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity2.get(0).getName()).isEqualTo(fakeUserList.get(0).getName());
    }

    @Test
    public void shouldReturnUserByEmail() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Set<Phone> phonesA = new HashSet<>();
        phonesA.add(new Phone("123123", "7", "52"));
        phonesA.add(new Phone("1223123", "27", "522"));

        User alex = new User("Alex Soto", "asoto@gmail.com", "A123qwe", phonesA);

        when(userService.findUserByEmail("asoto@gmail.com")).thenReturn(alex);

        ResponseEntity<?> responseEntity = userController.userByEmail("asoto@gmail.com");

        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/asoto@gmail.com"))
                .andDo(print())
                .andExpect(status().isOk());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(alex);
    }
}
