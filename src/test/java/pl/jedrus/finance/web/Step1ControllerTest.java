//package pl.jedrus.finance.web;
//
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import pl.jedrus.finance.domain.User;
//import pl.jedrus.finance.service.UserService;
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class Step1ControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private UserService userService;
//
//
//
//    @Test
//    void name() {
//    }
//
////    @AfterEach
////    void tearDown() {
////        User testuser = userRepository.findByUsername("testuser");
////        userRepository.delete(testuser);
////    }
//
//
//    @Test
//    void get() {
//        createUser();
//
//    }
//
//    public void createUser() {
//        User user = new User();
//        user.setUsername("testuser");
//        user.setPassword("uuu111");
//        userService.saveUser(user);
//    }
//}