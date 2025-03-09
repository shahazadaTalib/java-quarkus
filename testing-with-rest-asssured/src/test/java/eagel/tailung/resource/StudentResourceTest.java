package eagel.tailung.resource;

import com.fasterxml.jackson.jakarta.rs.json.annotation.JSONP;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.*;
@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentResourceTest {
    //@Order(1)
    @Test
    void getStudentList() {
        RestAssured
                .given()
                .when()
                .get("/getStudentList")
                .then()
                .body("size()", equalTo(4))
                .body("name",hasItems("Rahul","Mohit"))
                .body("branch",hasItems("CS","EE"));
    }
   // @Order(2)
    @Test
    void getStudentListByBranch() {
        RestAssured.given().when().get("/csstudent/{branch}","CS").then().body("size()",equalTo(2)).body("branch",hasItems("CS")).body("name",hasItems("Ra hul","Aakansha"));
    }

    @Test
    void getCSStudentList() {
        RestAssured.given().when().get("csstudent").then().body("size()",equalTo(2)).body("name",hasItems("Rahul","Aakansha"));
    }
    @Order(1)
    @Test
    void addStudent() {
        JsonObject jsonObject = Json.createObjectBuilder().add("studentId",10L).add("name","Musk").add("branch","Cs").build();
        RestAssured.given().contentType(MediaType.APPLICATION_JSON).body(jsonObject.toString()).when().post("addStudent").then().statusCode(Response.Status.CREATED.getStatusCode());
    }
    @Order(2)
    @Test
    void getStudentByStudentId() {
        RestAssured.given().when().get("student/{studentId}",10L).then()
                .body("size()",equalTo(1))
                .body("name",hasItem("musk"));
    }
    @Order(3)
    @Test
    void getTestStudentList() {
        RestAssured.given().when().get("getAllstudentTest").then().body("size()",equalTo(6));
    }
}