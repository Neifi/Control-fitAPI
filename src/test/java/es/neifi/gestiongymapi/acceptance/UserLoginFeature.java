package es.neifi.gestiongymapi.acceptance;

import es.neifi.controlfitAPI.GestionGymApiApplication;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = GestionGymApiApplication.class
)
@AutoConfigureMockMvc
public class UserLoginFeature {

  @LocalServerPort
  private String port;

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(roles = "ADMIN", username = "admin", password = "admin")
  public void user_should_login_with_valid_credentials() throws Exception {
    String loginForm = "{\"username\":\"admin\",\"password\":\"admin\"}";
    String userJson = "{\n" +
            "    \"id_gimnasio\":\"1\",\n" +
            "    \"username\":\"admin\",\n" +
            "    \"password\":\"admin\",\n" +
            "    \"dni\":\"12345158Y\",\n" +
            "    \"nombre\":\"name\",\n" +
            "    \"apellidos\":\"surname\",\n" +
            "    \"fecha_nacimiento\":\"27/11/2021\",\n" +
            "    \"fecha_inscripcion\":\"27/11/2021\",\n" +
            "    \"email\":\"email@email.com\",\n" +
            "    \"calle\":\"calle\",\n" +
            "    \"codigo_postal\":\"08241\",\n" +
            "    \"ciudad\":\"ciudad\",\n" +
            "    \"provincia\":\"baasdf\"\n" +
            "}".trim();
    mockMvc.perform(MockMvcRequestBuilders.post("/api/cliente")
                    .content(userJson)
                    .accept(MediaType.ALL))
            .andExpect(status().isCreated());

    mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                    .content(loginForm)
                    .accept(MediaType.ALL))
            .andExpect(status().isOk());

  }
}
