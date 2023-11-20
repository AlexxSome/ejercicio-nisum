package cl.postulacion.nisum;

import cl.postulacion.nisum.models.Phone;
import cl.postulacion.nisum.models.User;
import cl.postulacion.nisum.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class LoadData {

    @Autowired
    private IUserService userService;

    @Bean
    public void data(){
        Set<Phone> phonesA = new HashSet<>();
        phonesA.add(new Phone("123123", "7", "52"));
        phonesA.add(new Phone("123321", "6", "53"));


        User alex = new User("Alex Soto", "asoto@gmail.com", "A123qwe", phonesA);
        User alex2 = new User("Alex 2", "asoooo2@gmail.com", "A123qwe", phonesA);
        userService.createUser(alex);
    }

    @Bean
    public void data2(){
        Set<Phone> phonesA = new HashSet<>();
        phonesA.add(new Phone("123123", "7", "52"));
        phonesA.add(new Phone("123321", "6", "53"));


        User alex2 = new User("Alex 2", "asoooo2@gmail.com", "A123qwe", phonesA);
        userService.createUser(alex2);
    }
}
