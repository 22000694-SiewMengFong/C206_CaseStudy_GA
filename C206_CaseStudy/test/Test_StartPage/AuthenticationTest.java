package Test_StartPage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import HomePage.AdminUser;
import HomePage.NormalUser;
import HomePage.VenderUser;

public class AuthenticationTest {
    private NormalUser user1;
    private NormalUser user2;
    private NormalUser user3;
    private NormalUser user4;
    private AdminUser admin1;
    private AdminUser admin2;
    private VenderUser vendor1;
    private VenderUser vendor2;

    private ArrayList<NormalUser> normalUserList;
    private ArrayList<AdminUser> adminUserList;
    private ArrayList<VenderUser> venderUserList;
    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testEnterName() {

        fail("Not yet implemented");
    }

}