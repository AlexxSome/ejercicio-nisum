package cl.postulacion.nisum.services;

import cl.postulacion.nisum.models.User;
import cl.postulacion.nisum.repositories.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService{
    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(User user){
        User findUser = this.userRepository.findByEmail(user.getEmail());

        Date now = new Date();
        user.setCreatedAt(now);
        user.setModified(now);
        user.setLastLogin(now);
        user.setActive(true);
        user.setToken(UUID.randomUUID().toString());

        return userRepository.save(user);
    }
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Transactional
    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
