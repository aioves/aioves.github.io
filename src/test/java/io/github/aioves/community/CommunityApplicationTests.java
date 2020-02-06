package io.github.aioves.community;

import io.github.aioves.community.utils.Contents;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class CommunityApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(Contents.QUESTION_TITLE_IS_NULL + " -- " +Contents.QUESTION_TITLE_IS_NULL.length());
        System.out.println(Contents.MESSAGE + " -- " + Contents.MESSAGE.length());

    }

}
