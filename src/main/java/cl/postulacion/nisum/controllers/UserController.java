package cl.postulacion.nisum.controllers;


import cl.postulacion.nisum.models.User;
import cl.postulacion.nisum.services.IUserService;
import cl.postulacion.nisum.utils.UserValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
@Api(tags = "Controlador de Usuarios", value = "Administracion Usuario")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    @ApiOperation(value = "to get all users", response = User.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Getting all users"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Something went wrong")
            })
    public ResponseEntity<List<User>> users(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{email}")
    @ApiOperation(value = "to get all users", response = User.class)
    public ResponseEntity<?> userByEmail(@PathVariable String email){
        User user = this.userService.findUserByEmail(email);
        if(Objects.isNull(user)) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error al buscar el usuario");
            response.put("error","El correo no esta registrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create user", response = User.class)
    public ResponseEntity<?> create(@RequestBody User user){
        Map<String, Object> response = new HashMap<>();
        User findUser = this.userService.findUserByEmail(user.getEmail());

        if(findUser != null) {
            response.put("mensaje", "Error al crear el usuario, el correo ya registrado");
            response.put("error","El correo ya registrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }

        if(!UserValidator.emailValidate(user.getEmail())){
            response.put("mensaje", "Error al crear el usuario");
            response.put("error","El correo tiene formato incorrecto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if(!UserValidator.passwordValidate(user.getPassword())){
            response.put("mensaje", "Error al crear el usuario");
            response.put("error","La Contraseña tiene formato incorrecto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            this.userService.createUser(user);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al crear el usuario");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @PutMapping("/update/{email}")
    @ApiOperation(value = "Update user", response = User.class)
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable String email){
        Map<String, Object> response = new HashMap<>();

        User findUser = this.userService.findUserByEmail(email);

        if(findUser == null) {
            response.put("mensaje", "Error al actualizar el usuario");
            response.put("error","Usuario no encontrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        if(!UserValidator.passwordValidate(user.getPassword())){
            response.put("mensaje", "Error al actualizar el usuario");
            response.put("error","La Contraseña tiene formato incorrecto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            user.setId(findUser.getId());
            user.setModified(new Date());
            this.userService.save(user);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar el usuario");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_MODIFIED);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> delete(@PathVariable String email){
        Map<String, Object> response = new HashMap<>();

        User findUser = this.userService.findUserByEmail(email);

        if(findUser == null){
            response.put("mensaje", "Error al eliminar el usuario");
            response.put("error","Usuario no encontrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try{
            this.userService.deleteUser(findUser);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar el usuario");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<User>(findUser, HttpStatus.OK);
    }
}
