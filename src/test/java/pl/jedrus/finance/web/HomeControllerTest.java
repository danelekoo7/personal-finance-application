package pl.jedrus.finance.web;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HomeControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void should_Redirect_WhenNotAuthenticatedUserRequest() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().is3xxRedirection());
    }


    @Test
    @WithMockUser(username="a",roles="USER")
    public void should_ReturnStatusOk_WhenAuthenticatedUserRequest() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="a",roles="ADMIN")
    public void should_ReturnForbiddenStatus_WhenUserWithWrongRolesRequest() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username="a",roles="USER")
    public void should_ReturnHomeView_WhenAuthenticatedUserRequest() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

}